package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(SlabBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "fluidloggable$"))
public abstract class SlabBlockMixin extends Block implements Waterloggable {
    @Shadow @Final public static EnumProperty<SlabType> TYPE;
    @Shadow @Final public static BooleanProperty WATERLOGGED;

    public SlabBlockMixin(Settings settings) {
        super(settings);
    }

    public boolean canFillWithFluid(@Nullable LivingEntity filler, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != SlabType.DOUBLE && Fluidloggable.defaultCanFillWithFluid(state);
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != SlabType.DOUBLE && Fluidloggable.defaultTryFillWithFluid(world, pos, state, fluidState);
    }

    public ItemStack tryDrainFluid(@Nullable LivingEntity drainer, WorldAccess world, BlockPos pos, BlockState state) {
        return Fluidloggable.defaultTryDrainFluid(world, pos, state);
    }

    public Optional<SoundEvent> getBucketFillSound() {
        return Fluidloggable.defaultGetBucketFillSound();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void gbw$setFluidloggableDefaultState(Settings settings, CallbackInfo ci) {
        setDefaultState(stateManager.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    private void gbw$addFluidloggableProperty(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Fluidloggable.FLUIDLOGGED);
    }

    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggablePlacementState1(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir, @Local BlockPos blockPos) {
        BlockState state = cir.getReturnValue();
        if (state.get(TYPE) == SlabType.DOUBLE) {
            cir.setReturnValue(cir.getReturnValue().with(WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
        } else
            cir.setReturnValue(cir.getReturnValue().with(WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getWorld().getFluidState(blockPos).getFluid())));
    }

    @Redirect(method = "getStateForNeighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;"))
    private Comparable<?> gbw$fixFluidloggableStateForNeighborUpdate(BlockState instance, Property<?> property) {
        return instance.get(Fluidloggable.FLUIDLOGGED) != FluidType.EMPTY;
    }

    @Inject(method = "getFluidState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggableFluidState(BlockState state, CallbackInfoReturnable<FluidState> cir) {
        if (state.get(Fluidloggable.FLUIDLOGGED).getFluid() instanceof FlowableFluid flowableFluid) {
            cir.setReturnValue(flowableFluid.getStill(false));
        }
    }
}
