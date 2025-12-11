package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
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
        PistonBaseBlock.class,
        ShulkerBoxBlock.class,
        StonecutterBlock.class,
        TurtleEggBlock.class,
        WallBannerBlock.class
})
public class BlockGetPlacementStateMixin {
    @Inject(method = "getStateForPlacement", at = @At("RETURN"), cancellable = true)
    private void gbw$fixPlacementFluidState(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = cir.getReturnValue();
        if (state == null || !state.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            return;

        cir.setReturnValue(state.setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getLevel().getFluidState(ctx.getClickedPos()).getType())));
    }
}
