package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.registry.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({
        BedBlock.class,
        CakeBlock.class,
        DoorBlock.class
})
public class BlockCanPathfindThroughMixin {
    @SuppressWarnings("deprecation")
    @Inject(method = "canPathfindThrough", at = @At("HEAD"), cancellable = true)
    private void gbw$fixNavigationForWater(BlockState state, NavigationType type, CallbackInfoReturnable<Boolean> cir) {
        if (type == NavigationType.WATER) {
            if (state.getProperties().contains(Fluidloggable.FLUIDLOGGED)) {
                cir.setReturnValue(state.get(Fluidloggable.FLUIDLOGGED).getFluid().isIn(FluidTags.WATER));
            }
        }
    }
}
