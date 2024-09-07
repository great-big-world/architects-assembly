package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.block.VerticalSlabBlock;
import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.block.enums.VerticalSlabType;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyTags;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.*;
import net.minecraft.block.enums.WallShape;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
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

@Mixin(WallBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "fluidloggable$"))
public abstract class WallBlockMixin extends Block implements Waterloggable {
    @Shadow @Final public static EnumProperty<WallShape> WEST_SHAPE;
    @Shadow @Final public static BooleanProperty UP;
    @Shadow @Final public static EnumProperty<WallShape> NORTH_SHAPE;
    @Shadow @Final public static EnumProperty<WallShape> EAST_SHAPE;
    @Shadow @Final public static EnumProperty<WallShape> SOUTH_SHAPE;
    @Shadow @Final public static BooleanProperty WATERLOGGED;

    public WallBlockMixin(Settings settings) {
        super(settings);
    }

    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return Fluidloggable.defaultCanFillWithFluid(state);
    }

    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return Fluidloggable.defaultTryFillWithFluid(world, pos, state, fluidState);
    }

    public ItemStack tryDrainFluid(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        return Fluidloggable.defaultTryDrainFluid(world, pos, state);
    }

    public Optional<SoundEvent> getBucketFillSound() {
        return Fluidloggable.defaultGetBucketFillSound();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void gbw$setFluidloggableDefaultState(Settings settings, CallbackInfo ci) {
        setDefaultState(stateManager.getDefaultState().with(UP, true).with(NORTH_SHAPE, WallShape.NONE).with(EAST_SHAPE, WallShape.NONE).with(SOUTH_SHAPE, WallShape.NONE).with(WEST_SHAPE, WallShape.NONE).with(WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    private void gbw$addFluidloggableProperty(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Fluidloggable.FLUIDLOGGED);
    }

    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggablePlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir, @Local FluidState fluidState) {
        cir.setReturnValue(cir.getReturnValue().with(WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(fluidState.getFluid())));
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"))
    private void gbw$fixFluidloggableStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        FluidType fluidType = state.get(Fluidloggable.FLUIDLOGGED);
        if (fluidType != FluidType.EMPTY) {
            world.scheduleFluidTick(pos, fluidType.getFluid(), fluidType.getFluid().getTickRate(world));
        }
    }

    @Inject(method = "getFluidState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggableFluidState(BlockState state, CallbackInfoReturnable<FluidState> cir) {
        if (state.get(Fluidloggable.FLUIDLOGGED).getFluid() instanceof FlowableFluid flowableFluid) {
            cir.setReturnValue(flowableFluid.getStill(false));
        }
    }

    @Redirect(method = "getShapeMap", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;put(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap$Builder;"))
    private <K, V> ImmutableMap.Builder<BlockState, VoxelShape> gbw$fixWallShapeMap(ImmutableMap.Builder<BlockState, VoxelShape> instance, K key, V value) {
        for (FluidType fluidType : FluidType.values()) {
            instance.put(((BlockState) key).with(Fluidloggable.FLUIDLOGGED, fluidType), (VoxelShape) value);
        }
        return instance;
    }

    @Inject(method = "shouldConnectTo", at = @At("RETURN"), cancellable = true)
    private void gbw$connectWallsToVerticalSlabs(BlockState state, boolean faceFullSquare, Direction side, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(ArchitectsAssemblyTags.VERTICAL_SLABS) && state.get(VerticalSlabBlock.TYPE).getDirection() != side)
            cir.setReturnValue(true);
    }

    @Inject(method = "shouldUseTallShape", at = @At("HEAD"), cancellable = true)
    private static void gbw$useTallShapeForVerticalSlabs(VoxelShape aboveShape, VoxelShape tallShape, CallbackInfoReturnable<Boolean> cir) {
        if (VerticalSlabType.getShapes().contains(aboveShape))
            cir.setReturnValue(true);
    }
}
