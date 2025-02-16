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
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mixin(PickaxeItem.class)
public abstract class PickaxeItemMixin extends MiningToolItem {
    @Unique
    private static final Map<Supplier<Block>, Supplier<Block>> CRACKED_BLOCKS = new ImmutableMap.Builder<Supplier<Block>, Supplier<Block>>()
            .put(() -> Blocks.STONE_BRICKS, () -> Blocks.CRACKED_STONE_BRICKS)
            .put(() -> Blocks.INFESTED_STONE_BRICKS, () -> Blocks.INFESTED_CRACKED_STONE_BRICKS)
            .put(() -> Blocks.DEEPSLATE_BRICKS, () -> Blocks.CRACKED_DEEPSLATE_BRICKS)
            .put(() -> Blocks.DEEPSLATE_TILES, () -> Blocks.CRACKED_DEEPSLATE_TILES)
            .put(() -> Blocks.POLISHED_BLACKSTONE_BRICKS, () -> Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS)
            .put(() -> Blocks.NETHER_BRICKS, () -> Blocks.CRACKED_NETHER_BRICKS)
            .put(() -> Blocks.BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_BRICKS)
            .put(() -> Blocks.MUD_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_MUD_BRICKS)
            .put(() -> Blocks.QUARTZ_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_QUARTZ_BRICKS)
            .put(() -> Blocks.RED_NETHER_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_RED_NETHER_BRICKS)
            .put(() -> Blocks.END_STONE_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_END_STONE_BRICKS)
            .put(() -> ArchitectsAssemblyBlocks.GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.BROWN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BROWN_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.RED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_RED_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.ORANGE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_ORANGE_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.YELLOW_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_YELLOW_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.LIME_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIME_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.GREEN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GREEN_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.CYAN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_CYAN_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.BLUE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLUE_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.LIGHT_BLUE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_BLUE_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.PINK_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PINK_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.MAGENTA_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_MAGENTA_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.PURPLE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PURPLE_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.BLACK_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLACK_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.GRAY_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GRAY_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.LIGHT_GRAY_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_GRAY_GLASS)
            .put(() -> ArchitectsAssemblyBlocks.WHITE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_WHITE_GLASS)
            .build();
    @Unique
    private static Map<Block, Block> cachedCrackedBlocks = null;

    public PickaxeItemMixin(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, Settings settings) {
        super(material, effectiveBlocks, attackDamage, attackSpeed, settings);
    }

    public UseAction getUseAction(ItemStack stack) {
        return ArchitectsAssemblyUseActions.TOOL;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (canPlayerCrack(world, user) != null) {
            user.setCurrentHand(hand);
        }
        return ActionResult.PASS;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int i = getMaxUseTime(stack, user) - remainingUseTicks;

        if (i > 4 && i % 4 == 0) {
            BlockHitResult blockHitResult;
            if (user instanceof PlayerEntity player && (blockHitResult = canPlayerCrack(world, player)) != null) {
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState state = world.getBlockState(pos);

                if (player instanceof ServerPlayerEntity serverPlayer)
                    Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);

                if (cachedCrackedBlocks == null) {
                    cachedCrackedBlocks = new HashMap<>();
                    CRACKED_BLOCKS.forEach((blockSupplier, blockSupplier2) -> {
                        cachedCrackedBlocks.put(blockSupplier.get(), blockSupplier2.get());
                    });
                }

                BlockState cracked = cachedCrackedBlocks.get(state.getBlock()).getStateWithProperties(state);
                world.playSound(player, pos, ArchitectsAssemblySoundEvents.ITEM_PICKAXE_CRACK, SoundCategory.BLOCKS, 1f, 1f);
                world.setBlockState(pos, cracked, 11);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, cracked));

                if (!player.isCreative()) {
                    stack.damage(1, player, LivingEntity.getSlotForHand(player.getActiveHand()));
                }

                if (world.isClient)
                    player.swingHand(player.getActiveHand());
            }
        }
    }

    @Unique
    @Nullable
    private BlockHitResult canPlayerCrack(World world, PlayerEntity player) {
        if (player.isSpectator())
            return null;

        HitResult hit = player.raycast(player.getAttributeValue(EntityAttributes.BLOCK_INTERACTION_RANGE), 0f, false);
        if (hit instanceof BlockHitResult blockHitResult) {
            Block block = world.getBlockState(blockHitResult.getBlockPos()).getBlock();
            if (CRACKED_BLOCKS.containsKey(block)) {
                return blockHitResult;
            }
        }
        return null;
    }
}
