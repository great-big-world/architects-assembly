package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
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

    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixDoorPlacementForWater(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir, @Local BlockPos blockPos, @Local World world) {
        BlockState state = cir.getReturnValue();
        if (state == null || !state.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            return;

        cir.setReturnValue(state.with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(world.getFluidState(blockPos).getFluid())));
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("RETURN"), cancellable = true)
    private void gbw$fixDoorNeighborUpdateForWater(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        BlockState returnState = cir.getReturnValue();
        if (returnState.isAir() || !returnState.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            return;

        cir.setReturnValue(returnState.with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(world.getFluidState(pos).getFluid())));
    }

    @Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
    private void gbw$fixDoorPlacementForWater(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        world.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER).with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(world.getFluidState(pos.up()).getFluid())), 3);
        ci.cancel();
    }
}
