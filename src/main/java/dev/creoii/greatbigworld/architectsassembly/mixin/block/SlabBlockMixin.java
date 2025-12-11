package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

@Mixin(SlabBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "fluidloggable$"))
public abstract class SlabBlockMixin extends Block implements SimpleWaterloggedBlock {
    @Shadow @Final public static EnumProperty<SlabType> TYPE;
    @Shadow @Final public static BooleanProperty WATERLOGGED;

    public SlabBlockMixin(Properties settings) {
        super(settings);
    }

    public boolean canPlaceLiquid(@Nullable LivingEntity filler, BlockGetter world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.getValue(TYPE) != SlabType.DOUBLE && Fluidloggable.defaultCanFillWithFluid(state);
    }

    public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.getValue(TYPE) != SlabType.DOUBLE && Fluidloggable.defaultTryFillWithFluid(world, pos, state, fluidState);
    }

    public ItemStack pickupBlock(@Nullable LivingEntity drainer, LevelAccessor world, BlockPos pos, BlockState state) {
        return Fluidloggable.defaultTryDrainFluid(world, pos, state);
    }

    public Optional<SoundEvent> getPickupSound() {
        return Fluidloggable.defaultGetBucketFillSound();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void gbw$setFluidloggableDefaultState(Properties settings, CallbackInfo ci) {
        registerDefaultState(stateDefinition.any().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    private void gbw$addFluidloggableProperty(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Fluidloggable.FLUIDLOGGED);
    }

    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggablePlacementState1(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir, @Local BlockPos blockPos) {
        BlockState state = cir.getReturnValue();
        if (state.getValue(TYPE) == SlabType.DOUBLE) {
            cir.setReturnValue(cir.getReturnValue().setValue(WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
        } else
            cir.setReturnValue(cir.getReturnValue().setValue(WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getLevel().getFluidState(blockPos).getType())));
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
