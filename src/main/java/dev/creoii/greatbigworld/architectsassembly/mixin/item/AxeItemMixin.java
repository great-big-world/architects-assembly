package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyUseActions;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin extends MiningToolItem {
    @Shadow @Final protected static Map<Block, Block> STRIPPED_BLOCKS;
    @Shadow protected abstract Optional<BlockState> tryStrip(World world, BlockPos pos, @Nullable PlayerEntity player, BlockState state);

    public AxeItemMixin(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, Settings settings) {
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
        if (canPlayerStrip(world, user) != null) {
            user.setCurrentHand(hand);
        }
        return ActionResult.PASS;
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void gbw$cancelDefaultBehavior(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        cir.setReturnValue(ActionResult.PASS);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int i = getMaxUseTime(stack, user) - remainingUseTicks;

        if (i > 4 && i % 4 == 0) {
            BlockHitResult blockHitResult;
            if (user instanceof PlayerEntity player && (blockHitResult = canPlayerStrip(world, player)) != null) {
                BlockPos pos = blockHitResult.getBlockPos();

                if (player instanceof ServerPlayerEntity serverPlayer)
                    Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);

                Optional<BlockState> optional = tryStrip(world, pos, player, world.getBlockState(pos));
                if (optional.isPresent()) {
                    world.setBlockState(pos, optional.get(), 11);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, optional.get()));

                    if (!player.isCreative()) {
                        stack.damage(1, player, LivingEntity.getSlotForHand(player.getActiveHand()));
                    }

                    if (world.isClient)
                        player.swingHand(player.getActiveHand());
                }
            }
        }
    }

    @Unique
    @Nullable
    private BlockHitResult canPlayerStrip(World world, PlayerEntity player) {
        if (player.isSpectator())
            return null;

        HitResult hit = player.raycast(player.getAttributeValue(EntityAttributes.BLOCK_INTERACTION_RANGE), 0f, false);
        if (hit instanceof BlockHitResult blockHitResult) {
            BlockState state = world.getBlockState(blockHitResult.getBlockPos());
            if (STRIPPED_BLOCKS.containsKey(state.getBlock())) {
                return blockHitResult;
            } else if (Oxidizable.getDecreasedOxidationState(state).isPresent()) {
                return blockHitResult;
            } else if (Optional.ofNullable(HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().get(state.getBlock())).map(block -> block.getStateWithProperties(state)).isPresent()) {
                return blockHitResult;
            }
        }
        return null;
    }
}
