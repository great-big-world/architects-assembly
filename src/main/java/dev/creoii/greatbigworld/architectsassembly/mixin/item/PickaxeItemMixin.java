package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.google.common.collect.ImmutableMap;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblySoundEvents;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyUseActions;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(PickaxeItem.class)
public abstract class PickaxeItemMixin extends MiningToolItem {
    @Unique
    private static final Map<Block, Block> CRACKED_BLOCKS = new ImmutableMap.Builder<Block, Block>()
            .put(Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS)
            .put(Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_CRACKED_STONE_BRICKS)
            .put(Blocks.DEEPSLATE_BRICKS, Blocks.CRACKED_DEEPSLATE_BRICKS)
            .put(Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES)
            .put(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
            .put(Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS)
            .put(Blocks.BRICKS, ArchitectsAssemblyBlocks.CRACKED_BRICKS)
            .put(Blocks.MUD_BRICKS, ArchitectsAssemblyBlocks.CRACKED_MUD_BRICKS)
            .put(Blocks.QUARTZ_BRICKS, ArchitectsAssemblyBlocks.CRACKED_QUARTZ_BRICKS)
            .put(Blocks.RED_NETHER_BRICKS, ArchitectsAssemblyBlocks.CRACKED_RED_NETHER_BRICKS)
            .put(Blocks.END_STONE_BRICKS, ArchitectsAssemblyBlocks.CRACKED_END_STONE_BRICKS)
            .build();

    public PickaxeItemMixin(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(material, effectiveBlocks, settings);
    }

    public UseAction getUseAction(ItemStack stack) {
        return ArchitectsAssemblyUseActions.TOOL;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (canPlayerCrack(world, user) != null) {
            user.setCurrentHand(hand);
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int i = getMaxUseTime(stack) - remainingUseTicks;

        if (i > 5 && i % 4 == 0) {
            BlockHitResult blockHitResult;
            if (user instanceof PlayerEntity player && (blockHitResult = canPlayerCrack(world, player)) != null) {
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState state = world.getBlockState(pos);

                if (player instanceof ServerPlayerEntity serverPlayer)
                    Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);

                BlockState cracked = CRACKED_BLOCKS.get(state.getBlock()).getStateWithProperties(state);
                world.playSound(player, pos, ArchitectsAssemblySoundEvents.ITEM_PICKAXE_CRACK, SoundCategory.BLOCKS, 1f, 1f);
                world.setBlockState(pos, cracked, 11);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, cracked));

                if (!player.isCreative()) {
                    stack.damage(1, player, LivingEntity.getSlotForHand(player.getActiveHand()));
                }

                player.swingHand(player.getActiveHand());
            }
        }
    }

    @Unique
    @Nullable
    private BlockHitResult canPlayerCrack(World world, PlayerEntity player) {
        if (player.isSpectator())
            return null;

        HitResult hit = player.raycast(player.getAttributeValue(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE), 0f, false);
        if (hit instanceof BlockHitResult blockHitResult) {
            Block block = world.getBlockState(blockHitResult.getBlockPos()).getBlock();
            if (CRACKED_BLOCKS.containsKey(block)) {
                return blockHitResult;
            }
        }
        return null;
    }
}
