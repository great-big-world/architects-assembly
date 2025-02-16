package dev.creoii.greatbigworld.architectsassembly.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class GlassBlock extends TransparentBlock {
    private final BlockState cracked;

    public GlassBlock(Settings settings, BlockState cracked) {
        super(settings);
        this.cracked = cracked;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        if (EnchantmentHelper.getEnchantments(tool).getEnchantments().stream().map(entry -> entry.getKey().get()).toList().contains(Enchantments.SILK_TOUCH))
            return;
        if (!player.isCreative()) {
            world.setBlockState(pos, cracked, 2);
            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(state));
            world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
        }
    }
}
