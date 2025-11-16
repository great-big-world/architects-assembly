package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.architectsassembly.client.screen.SawmillScreenHandler;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyStats;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SawmillBlock extends StonecutterBlock {
    private static final Text TITLE = Text.translatable("container.sawmill");

    public SawmillBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }
        player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
        player.incrementStat(ArchitectsAssemblyStats.INTERACT_WITH_SAWMILL);
        return ActionResult.CONSUME;
    }

    @Override
    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new SawmillScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos)), TITLE);
    }
}
