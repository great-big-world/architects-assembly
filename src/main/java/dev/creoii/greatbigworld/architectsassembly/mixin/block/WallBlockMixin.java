package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.block.VerticalSlabBlock;
import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.block.enums.VerticalSlabType;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyTags;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(WallBlock.class)
@Implements(@Interface(iface = Fluidloggable.class, prefix = "fluidloggable$"))
public abstract class WallBlockMixin extends Block implements SimpleWaterloggedBlock {
    @Shadow @Final public static EnumProperty<WallSide> WEST;
    @Shadow @Final public static BooleanProperty UP;
    @Shadow @Final public static EnumProperty<WallSide> NORTH;
    @Shadow @Final public static EnumProperty<WallSide> EAST;
    @Shadow @Final public static EnumProperty<WallSide> SOUTH;
    @Shadow @Final public static BooleanProperty WATERLOGGED;

    public WallBlockMixin(Properties settings) {
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
        registerDefaultState(stateDefinition.any().setValue(UP, true).setValue(NORTH, WallSide.NONE).setValue(EAST, WallSide.NONE).setValue(SOUTH, WallSide.NONE).setValue(WEST, WallSide.NONE).setValue(WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
    }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    private void gbw$addFluidloggableProperty(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Fluidloggable.FLUIDLOGGED);
    }

    /*@WrapOperation(method = "getCollisionShape", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
    private <V> V gbw$modifyCollisionState(Map<BlockState, VoxelShape> instance, Object object, Operation<V> original) {
        BlockState state = (BlockState) object;
        return original.call(instance, state.with(WATERLOGGED, false).with(SnowyHelper.SNOW_LAYERS, Math.max(0, state.get(SnowyHelper.SNOW_LAYERS) - 1)));
    }*/

    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggablePlacementState(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir, @Local FluidState fluidState) {
        cir.setReturnValue(cir.getReturnValue().setValue(WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(fluidState.getType())));
    }

    @Inject(method = "updateShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/world/level/ScheduledTickAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/block/state/BlockState;", at = @At("HEAD"))
    private void gbw$fixFluidloggableStateForNeighborUpdate(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random, CallbackInfoReturnable<BlockState> cir) {
        FluidType fluidType = state.getValue(Fluidloggable.FLUIDLOGGED);
        if (fluidType != FluidType.EMPTY) {
            tickView.scheduleTick(pos, fluidType.getFluid(), fluidType.getFluid().getTickDelay(world));
        }
    }

    @Inject(method = "getFluidState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggableFluidState(BlockState state, CallbackInfoReturnable<FluidState> cir) {
        if (state.getValue(Fluidloggable.FLUIDLOGGED).getFluid() instanceof FlowingFluid flowableFluid) {
            cir.setReturnValue(flowableFluid.getSource(false));
        }
    }

    @Inject(method = "connectsTo", at = @At("RETURN"), cancellable = true)
    private void gbw$connectWallsToVerticalSlabs(BlockState state, boolean faceFullSquare, Direction side, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(ArchitectsAssemblyTags.VERTICAL_SLABS) && state.getValue(VerticalSlabBlock.TYPE).getDirection() != side)
            cir.setReturnValue(true);
    }

    @Inject(method = "isCovered", at = @At("HEAD"), cancellable = true)
    private static void gbw$useTallShapeForVerticalSlabs(VoxelShape aboveShape, VoxelShape tallShape, CallbackInfoReturnable<Boolean> cir) {
        if (VerticalSlabType.getShapes().contains(aboveShape))
            cir.setReturnValue(true);
    }
}
