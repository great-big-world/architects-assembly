package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({
        AnvilBlock.class,
        BannerBlock.class,
        BedBlock.class,
        ShulkerBoxBlock.class,
        WallBannerBlock.class,
        BellBlock.class,
        BrewingStandBlock.class,
        CakeBlock.class,
        ComposterBlock.class,
        DaylightDetectorBlock.class,
        DoorBlock.class,
        EndPortalFrameBlock.class,
        FenceGateBlock.class,
        GrindstoneBlock.class,
        HopperBlock.class,
        LecternBlock.class,
        PistonBlock.class,
        PistonHeadBlock.class,
        StonecutterBlock.class,
        TurtleEggBlock.class
})
public class BlockAppendPropertiesMixin {
    @Inject(method = "appendProperties", at = @At("TAIL"))
    private void gbw$appendWaterlogged(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Fluidloggable.FLUIDLOGGED);
    }
}
