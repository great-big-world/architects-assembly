package dev.creoii.greatbigworld.architectsassembly.util;

import com.google.common.collect.ImmutableMap;
import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public interface Fluidloggable extends BucketPickup, LiquidBlockContainer {
    EnumProperty<FluidType> FLUIDLOGGED = EnumProperty.create("fluid", FluidType.class);
    Map<Fluid, FluidType> FLUIDS = new ImmutableMap.Builder<Fluid, FluidType>()
            .put(Fluids.EMPTY, FluidType.EMPTY)
            .put(Fluids.WATER, FluidType.WATER)
            .put(Fluids.FLOWING_WATER, FluidType.EMPTY)
            .put(Fluids.LAVA, FluidType.LAVA)
            .put(Fluids.FLOWING_LAVA, FluidType.EMPTY)
            .build();

    default boolean canPlaceLiquid(@Nullable LivingEntity filler, BlockGetter world, BlockPos pos, BlockState state, Fluid fluid) {
        return defaultCanFillWithFluid(state);
    }

    default boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        return defaultTryFillWithFluid(world, pos, state, fluidState);
    }

    default ItemStack pickupBlock(@Nullable LivingEntity drainer, LevelAccessor world, BlockPos pos, BlockState state) {
        return defaultTryDrainFluid(world, pos, state);
    }

    default Optional<SoundEvent> getPickupSound() {
        return defaultGetBucketFillSound();
    }

    static boolean defaultCanFillWithFluid(BlockState state) {
        return state.getValue(FLUIDLOGGED) == FluidType.EMPTY;
    }

    static boolean defaultTryFillWithFluid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!world.isClientSide()) {
            world.setBlock(pos, state.setValue(FLUIDLOGGED, FLUIDS.get(fluidState.getType())), 3);
            world.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(world));
        }
        return true;
    }

    static ItemStack defaultTryDrainFluid(LevelAccessor world, BlockPos pos, BlockState state) {
        FluidType fluidType = state.getValue(FLUIDLOGGED);
        if (fluidType != FluidType.EMPTY) {
            world.setBlock(pos, state.setValue(FLUIDLOGGED, FluidType.EMPTY), 3);
            if (!state.canSurvive(world, pos)) {
                world.destroyBlock(pos, true);
            }

            return fluidType.getBucket().get();
        } else {
            return ItemStack.EMPTY;
        }
    }

    static Optional<SoundEvent> defaultGetBucketFillSound() {
        return Fluids.WATER.getPickupSound();
    }
}