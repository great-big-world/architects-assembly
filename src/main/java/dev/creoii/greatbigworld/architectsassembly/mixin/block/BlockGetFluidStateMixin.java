package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.*;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;

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
public abstract class BlockGetFluidStateMixin extends Block implements Fluidloggable {
    public BlockGetFluidStateMixin(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        if (state.get(Fluidloggable.FLUIDLOGGED).getFluid() instanceof FlowableFluid flowableFluid) {
            return flowableFluid.getStill(false);
        }
        return super.getFluidState(state);
    }
}
