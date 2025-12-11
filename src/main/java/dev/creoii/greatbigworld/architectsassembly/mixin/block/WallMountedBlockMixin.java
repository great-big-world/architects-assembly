package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FaceAttachedHorizontalDirectionalBlock.class)
public class WallMountedBlockMixin {
    @Inject(method = "getStateForPlacement", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void gbw$fixNavigationForWaterWallMounted(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = cir.getReturnValue();
        if (state != null && state.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            cir.setReturnValue(state.setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getLevel().getFluidState(ctx.getClickedPos()).getType())));
    }
}
