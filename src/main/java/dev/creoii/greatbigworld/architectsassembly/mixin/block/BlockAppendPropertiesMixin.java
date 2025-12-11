package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
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
        PistonBaseBlock.class,
        PistonHeadBlock.class,
        StonecutterBlock.class,
        TurtleEggBlock.class
})
public class BlockAppendPropertiesMixin {
    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    private void gbw$appendWaterlogged(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(Fluidloggable.FLUIDLOGGED);
    }
}
