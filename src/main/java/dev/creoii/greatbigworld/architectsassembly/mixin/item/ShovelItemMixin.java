package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyUseActions;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
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

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin extends MiningToolItem {
    @Shadow @Final protected static Map<Block, BlockState> PATH_STATES;

    public ShovelItemMixin(ToolMaterial material, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, Settings settings) {
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
        if (canPlayerPath(world, user) != null) {
            user.setCurrentHand(hand);
        }
        return ActionResult.PASS;
    }

    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.BY, by = 2), cancellable = true)
    private void gbw$cancelDefaultBehavior(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, @Local World world, @Local BlockPos blockPos, @Local(ordinal = 0) BlockState blockState, @Local PlayerEntity playerEntity) {
        if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT)) {
            CampfireBlock.extinguish(playerEntity, world, blockPos, blockState);
            if (!world.isClient) {
                BlockState state = blockState.with(CampfireBlock.LIT, false);
                world.setBlockState(blockPos, state, 11);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, state));
                if (playerEntity != null) {
                    context.getStack().damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
                }
                cir.setReturnValue(ActionResult.SUCCESS_SERVER);
            }
            cir.setReturnValue(ActionResult.SUCCESS);
        }
        cir.setReturnValue(ActionResult.PASS);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int i = getMaxUseTime(stack, user) - remainingUseTicks;

        if (i > 4 && i % 4 == 0) {
            BlockHitResult blockHitResult;
            if (user instanceof PlayerEntity player && (blockHitResult = canPlayerPath(world, player)) != null) {
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState state = world.getBlockState(pos);

                if (player instanceof ServerPlayerEntity serverPlayer)
                    Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);

                world.setBlockState(pos, PATH_STATES.get(state.getBlock()), 11);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state));

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
    private BlockHitResult canPlayerPath(World world, PlayerEntity player) {
        if (player.isSpectator())
            return null;

        HitResult hit = player.raycast(player.getAttributeValue(EntityAttributes.BLOCK_INTERACTION_RANGE), 0f, false);
        if (hit instanceof BlockHitResult blockHitResult) {
            BlockState state = world.getBlockState(blockHitResult.getBlockPos());
            if (PATH_STATES.containsKey(state.getBlock())) {
                return blockHitResult;
            }
        }
        return null;
    }
}
