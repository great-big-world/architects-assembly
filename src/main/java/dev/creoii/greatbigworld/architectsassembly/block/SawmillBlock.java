package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.architectsassembly.client.screen.SawmillScreenHandler;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyStats;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SawmillBlock extends StonecutterBlock {
    private static final Component TITLE = Component.translatable("container.sawmill");

    public SawmillBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (world.isClientSide()) {
            return InteractionResult.SUCCESS;
        }
        player.openMenu(state.getMenuProvider(world, pos));
        player.awardStat(ArchitectsAssemblyStats.INTERACT_WITH_SAWMILL);
        return InteractionResult.CONSUME;
    }

    @Override
    @Nullable
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        return new SimpleMenuProvider((syncId, playerInventory, player) -> new SawmillScreenHandler(syncId, playerInventory, ContainerLevelAccess.create(world, pos)), TITLE);
    }
}
