package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DoorBlock.class)
public class DoorBlockMixin {
    @Shadow @Final public static EnumProperty<DoubleBlockHalf> HALF;

    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    private void gbw$fixDoorPlacementForWater(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = cir.getReturnValue();
        if (state == null || !state.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            return;

        cir.setReturnValue(state.setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getLevel().getFluidState(ctx.getClickedPos()).getType())));
    }

    @Inject(method = "updateShape", at = @At("RETURN"), cancellable = true)
    private void gbw$fixDoorNeighborUpdateForWater(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random, CallbackInfoReturnable<BlockState> cir) {
        BlockState returnState = cir.getReturnValue();
        if (returnState.isAir() || !returnState.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            return;

        FluidType fluidType = state.getValue(Fluidloggable.FLUIDLOGGED);
        if (fluidType != FluidType.EMPTY) {
            tickView.scheduleTick(pos, fluidType.getFluid(), fluidType.getFluid().getTickDelay(world));
        }

        cir.setReturnValue(returnState.setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(world.getFluidState(pos).getType())));
    }

    @Inject(method = "setPlacedBy", at = @At("HEAD"), cancellable = true)
    private void gbw$fixDoorPlacementForWater(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        world.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(world.getFluidState(pos.above()).getType())), Block.UPDATE_ALL);
        ci.cancel();
    }
}
