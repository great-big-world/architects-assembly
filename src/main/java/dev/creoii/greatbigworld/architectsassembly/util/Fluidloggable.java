package dev.creoii.greatbigworld.architectsassembly.util;

import com.google.common.collect.ImmutableMap;
import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public interface Fluidloggable extends FluidDrainable, FluidFillable {
    EnumProperty<FluidType> FLUIDLOGGED = EnumProperty.of("fluid", FluidType.class);
    Map<Fluid, FluidType> FLUIDS = new ImmutableMap.Builder<Fluid, FluidType>()
            .put(Fluids.EMPTY, FluidType.EMPTY)
            .put(Fluids.WATER, FluidType.WATER)
            .put(Fluids.FLOWING_WATER, FluidType.EMPTY)
            .put(Fluids.LAVA, FluidType.LAVA)
            .put(Fluids.FLOWING_LAVA, FluidType.EMPTY)
            .build();

    default boolean canFillWithFluid(@Nullable LivingEntity filler, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return defaultCanFillWithFluid(state);
    }

    default boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return defaultTryFillWithFluid(world, pos, state, fluidState);
    }

    default ItemStack tryDrainFluid(@Nullable LivingEntity drainer, WorldAccess world, BlockPos pos, BlockState state) {
        return defaultTryDrainFluid(world, pos, state);
    }

    default Optional<SoundEvent> getBucketFillSound() {
        return defaultGetBucketFillSound();
    }

    static boolean defaultCanFillWithFluid(BlockState state) {
        return state.get(FLUIDLOGGED) == FluidType.EMPTY;
    }

    static boolean defaultTryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!world.isClient()) {
            world.setBlockState(pos, state.with(FLUIDLOGGED, FLUIDS.get(fluidState.getFluid())), 3);
            world.scheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
        }
        return true;
    }

    static ItemStack defaultTryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        FluidType fluidType = state.get(FLUIDLOGGED);
        if (fluidType != FluidType.EMPTY) {
            world.setBlockState(pos, state.with(FLUIDLOGGED, FluidType.EMPTY), 3);
            if (!state.canPlaceAt(world, pos)) {
                world.breakBlock(pos, true);
            }

            return fluidType.getBucket().get();
        } else {
            return ItemStack.EMPTY;
        }
    }

    static Optional<SoundEvent> defaultGetBucketFillSound() {
        return Fluids.WATER.getBucketFillSound();
    }
}