package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceGateBlock.class)
public class FenceGateBlockMixin {
    @Shadow @Final public static BooleanProperty OPEN;

    @Inject(method = "canPathfindThrough", at = @At("HEAD"), cancellable = true)
    private void gbw$fixNavigationForWater(BlockState state, BlockView world, BlockPos pos, NavigationType type, CallbackInfoReturnable<Boolean> cir) {
        if (type == NavigationType.WATER) {
            if (state.getProperties().contains(Fluidloggable.FLUIDLOGGED)) {
                cir.setReturnValue(state.get(Fluidloggable.FLUIDLOGGED).getFluid().isIn(FluidTags.WATER) && state.get(OPEN));
            }
            cir.setReturnValue(world.getFluidState(pos).isIn(FluidTags.WATER) && state.get(OPEN));
        }
    }
}
