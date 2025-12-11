package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LightBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "fluidloggable$"))
public abstract class LightBlockMixin extends Block implements SimpleWaterloggedBlock {
    @Shadow @Final public static IntegerProperty LEVEL;
    @Shadow @Final public static BooleanProperty WATERLOGGED;

    public LightBlockMixin(Properties settings) {
        super(settings);
    }

    public boolean canPlaceLiquid(@Nullable LivingEntity filler, BlockGetter world, BlockPos pos, BlockState state, Fluid fluid) {
        return Fluidloggable.defaultCanFillWithFluid(state);
    }

    public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        return Fluidloggable.defaultTryFillWithFluid(world, pos, state, fluidState);
    }

    public ItemStack pickupBlock(@Nullable LivingEntity drainer, LevelAccessor world, BlockPos pos, BlockState state) {
        return Fluidloggable.defaultTryDrainFluid(world, pos, state);
    }

    public Optional<SoundEvent> getPickupSound() {
        return Fluidloggable.defaultGetBucketFillSound();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void gbw$setFluidloggableDefaultState(Properties settings, CallbackInfo ci) {
        registerDefaultState(stateDefinition.any().setValue(LEVEL, 15).setValue(WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    private void gbw$addFluidloggableProperty(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Fluidloggable.FLUIDLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getLevel().getFluidState(ctx.getClickedPos()).getType()));
    }

    @Redirect(method = "updateShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;"))
    private Comparable<?> gbw$fixFluidloggableStateForNeighborUpdate(BlockState instance, Property<?> property) {
        return instance.getValue(Fluidloggable.FLUIDLOGGED) != FluidType.EMPTY;
    }

    @Inject(method = "getFluidState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggableFluidState(BlockState state, CallbackInfoReturnable<FluidState> cir) {
        if (state.getValue(Fluidloggable.FLUIDLOGGED).getFluid() instanceof FlowingFluid flowableFluid) {
            cir.setReturnValue(flowableFluid.getSource(false));
        }
    }
}
