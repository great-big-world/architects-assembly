package dev.creoii.greatbigworld.architectsassembly.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public class GlassBlock extends TransparentBlock {
    private final BlockState cracked;

    public GlassBlock(Properties settings, BlockState cracked) {
        super(settings);
        this.cracked = cracked;
    }

    public BlockState getCracked() {
        return cracked;
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(world, player, pos, state, blockEntity, tool);
        if (EnchantmentHelper.getEnchantmentsForCrafting(tool).keySet().stream().map(entry -> entry.unwrapKey().get()).toList().contains(Enchantments.SILK_TOUCH))
            return;
        if (!player.isCreative()) {
            world.setBlock(pos, cracked, 2);
            world.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
            world.levelEvent(2001, pos, Block.getId(state));
        }
    }
}
