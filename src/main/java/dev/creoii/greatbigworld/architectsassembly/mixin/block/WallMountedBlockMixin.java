package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WallMountedBlock.class)
public class WallMountedBlockMixin {
    @Inject(method = "getPlacementState", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void gbw$fixNavigationForWaterWallMounted(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = cir.getReturnValue();
        if (state != null && state.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            cir.setReturnValue(state.with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid())));
    }
}
