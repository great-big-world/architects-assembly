package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({
        FenceBlock.class,
        PaneBlock.class
})
public abstract class FenceAndPaneBlockMixin extends HorizontalConnectingBlock {
    protected FenceAndPaneBlockMixin(float radius1, float radius2, float boundingHeight1, float boundingHeight2, float collisionHeight, Settings settings) {
        super(radius1, radius2, boundingHeight1, boundingHeight2, collisionHeight, settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void gbw$setFluidloggableDefaultState(Settings settings, CallbackInfo ci) {
        setDefaultState(stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    private void gbw$addFluidloggableProperty(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Fluidloggable.FLUIDLOGGED);
    }

    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    private void gbw$fixFluidloggablePlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(cir.getReturnValue().with(WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid())));
    }

    @Redirect(method = "getStateForNeighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;"))
    private Comparable<?> gbw$fixFluidloggableStateForNeighborUpdate(BlockState instance, Property<?> property) {
        return instance.get(Fluidloggable.FLUIDLOGGED) != FluidType.EMPTY;
    }
}
