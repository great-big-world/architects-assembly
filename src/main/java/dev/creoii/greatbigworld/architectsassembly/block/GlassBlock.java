package dev.creoii.greatbigworld.architectsassembly.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class GlassBlock extends TransparentBlock {
    private final BlockState shattered;

    public GlassBlock(Settings settings, BlockState shattered) {
        super(settings);
        this.shattered = shattered;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        if (!player.isCreative() && !EnchantmentHelper.hasSilkTouch(tool)) {
            world.setBlockState(pos, shattered, 2);
            world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(state));
            world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
        }
    }
}
