package dev.creoii.greatbigworld.architectsassembly.item;

import com.google.common.collect.ImmutableMap;
import dev.creoii.greatbigworld.architectsassembly.block.GlassBlock;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblySoundEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class PickaxeItem extends Item {
    public static final Map<Block, Supplier<Block>> CRACKED_BLOCKS = new ImmutableMap.Builder<Block, Supplier<Block>>()
            .put(Blocks.STONE_BRICKS, () -> Blocks.CRACKED_STONE_BRICKS)
            .put(Blocks.INFESTED_STONE_BRICKS, () -> Blocks.INFESTED_CRACKED_STONE_BRICKS)
            .put(Blocks.DEEPSLATE_BRICKS, () -> Blocks.CRACKED_DEEPSLATE_BRICKS)
            .put(Blocks.DEEPSLATE_TILES, () -> Blocks.CRACKED_DEEPSLATE_TILES)
            .put(Blocks.POLISHED_BLACKSTONE_BRICKS, () -> Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
            .put(Blocks.NETHER_BRICKS, () -> Blocks.CRACKED_NETHER_BRICKS)
            .put(Blocks.BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_BRICKS)
            .put(Blocks.MUD_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_MUD_BRICKS)
            .put(Blocks.QUARTZ_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_QUARTZ_BRICKS)
            .put(Blocks.RED_NETHER_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_RED_NETHER_BRICKS)
            .put(Blocks.END_STONE_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_END_STONE_BRICKS)
            .put(Blocks.GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GLASS)
            .put(Blocks.BROWN_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BROWN_GLASS)
            .put(Blocks.RED_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_RED_GLASS)
            .put(Blocks.ORANGE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_ORANGE_GLASS)
            .put(Blocks.YELLOW_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_YELLOW_GLASS)
            .put(Blocks.LIME_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIME_GLASS)
            .put(Blocks.GREEN_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GREEN_GLASS)
            .put(Blocks.CYAN_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_CYAN_GLASS)
            .put(Blocks.BLUE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLUE_GLASS)
            .put(Blocks.LIGHT_BLUE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_BLUE_GLASS)
            .put(Blocks.PINK_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PINK_GLASS)
            .put(Blocks.MAGENTA_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_MAGENTA_GLASS)
            .put(Blocks.PURPLE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PURPLE_GLASS)
            .put(Blocks.BLACK_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLACK_GLASS)
            .put(Blocks.GRAY_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GRAY_GLASS)
            .put(Blocks.LIGHT_GRAY_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_GRAY_GLASS)
            .put(Blocks.WHITE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_WHITE_GLASS)
            .put(ArchitectsAssemblyBlocks.GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GLASS)
            .put(ArchitectsAssemblyBlocks.BROWN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BROWN_GLASS)
            .put(ArchitectsAssemblyBlocks.RED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_RED_GLASS)
            .put(ArchitectsAssemblyBlocks.ORANGE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_ORANGE_GLASS)
            .put(ArchitectsAssemblyBlocks.YELLOW_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_YELLOW_GLASS)
            .put(ArchitectsAssemblyBlocks.LIME_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIME_GLASS)
            .put(ArchitectsAssemblyBlocks.GREEN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GREEN_GLASS)
            .put(ArchitectsAssemblyBlocks.CYAN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_CYAN_GLASS)
            .put(ArchitectsAssemblyBlocks.BLUE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLUE_GLASS)
            .put(ArchitectsAssemblyBlocks.LIGHT_BLUE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_BLUE_GLASS)
            .put(ArchitectsAssemblyBlocks.PINK_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PINK_GLASS)
            .put(ArchitectsAssemblyBlocks.MAGENTA_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_MAGENTA_GLASS)
            .put(ArchitectsAssemblyBlocks.PURPLE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PURPLE_GLASS)
            .put(ArchitectsAssemblyBlocks.BLACK_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLACK_GLASS)
            .put(ArchitectsAssemblyBlocks.GRAY_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GRAY_GLASS)
            .put(ArchitectsAssemblyBlocks.LIGHT_GRAY_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_GRAY_GLASS)
            .put(ArchitectsAssemblyBlocks.WHITE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_WHITE_GLASS)
            .build();

    public PickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.pickaxe(material, attackDamage, attackSpeed));
    }

    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        for (ItemUseAnimation itemUseAnimation : ItemUseAnimation.values()) {
            if (itemUseAnimation.name().equals("TOOL")) { // check if TOOL exists (from delay-tool-usage standalone mod)
                return itemUseAnimation;
            }
        }
        return super.getUseAnimation(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (canPlayerCrack(world, user) != null) {
            user.startUsingItem(hand);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onUseTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int i = getUseDuration(stack, user) - remainingUseTicks;

        if (i > 4) {
            BlockHitResult blockHitResult;
            if (user instanceof Player player && (blockHitResult = canPlayerCrack(world, player)) != null) {
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState state = world.getBlockState(pos);

                if (player instanceof ServerPlayer serverPlayer)
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);

                BlockState cracked;
                if (state.getBlock() instanceof GlassBlock glassBlock) {
                    cracked = glassBlock.getCracked();
                } else cracked = CRACKED_BLOCKS.get(state.getBlock()).get().withPropertiesOf(state);
                world.playSound(player, pos, ArchitectsAssemblySoundEvents.ITEM_PICKAXE_CRACK, SoundSource.BLOCKS, 1f, 1f);
                world.setBlock(pos, cracked, Block.UPDATE_ALL);
                world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, cracked));

                if (!player.isCreative()) {
                    stack.hurtAndBreak(1, player, player.getUsedItemHand());
                }

                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));

                if (world.isClientSide())
                    player.swing(player.getUsedItemHand());
            }
        }
    }

    @Unique
    @Nullable
    private BlockHitResult canPlayerCrack(Level world, Player player) {
        if (player.isSpectator())
            return null;

        HitResult hit = player.pick(player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE), 0f, false);
        if (hit instanceof BlockHitResult blockHitResult) {
            Block block = world.getBlockState(blockHitResult.getBlockPos()).getBlock();
            if (CRACKED_BLOCKS.containsKey(block) || block instanceof GlassBlock) {
                return blockHitResult;
            }
        }
        return null;
    }
}
