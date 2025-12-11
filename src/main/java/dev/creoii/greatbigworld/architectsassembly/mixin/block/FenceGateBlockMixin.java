package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceGateBlock.class)
public class FenceGateBlockMixin {
    @Shadow @Final public static BooleanProperty OPEN;

    @SuppressWarnings("deprecation")
    @Inject(method = "isPathfindable", at = @At("HEAD"), cancellable = true)
    private void gbw$fixNavigationForWaterGate(BlockState state, PathComputationType type, CallbackInfoReturnable<Boolean> cir) {
        if (type == PathComputationType.WATER) {
            if (state.getProperties().contains(Fluidloggable.FLUIDLOGGED)) {
                cir.setReturnValue(state.getValue(Fluidloggable.FLUIDLOGGED).getFluid().is(FluidTags.WATER) && state.getValue(OPEN));
            }
        }
    }
}
