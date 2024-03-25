package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({
        AnvilBlock.class,
        BannerBlock.class,
        BedBlock.class,
        BellBlock.class,
        EndPortalFrameBlock.class,
        FenceGateBlock.class,
        HopperBlock.class,
        LecternBlock.class,
        PistonBlock.class,
        ShulkerBoxBlock.class,
        StonecutterBlock.class,
        TurtleEggBlock.class,
        WallBannerBlock.class
})
public class BlockGetPlacementStateMixin {
    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixPlacementFluidState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = cir.getReturnValue();
        if (state == null || !state.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            return;

        cir.setReturnValue(state.with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid())));
    }
}
