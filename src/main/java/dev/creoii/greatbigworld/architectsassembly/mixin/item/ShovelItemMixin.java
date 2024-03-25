package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyUseActions;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
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

    public ShovelItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    public UseAction getUseAction(ItemStack stack) {
        return ArchitectsAssemblyUseActions.TOOL;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (canPlayerPath(world, user) != null) {
            user.setCurrentHand(hand);
        }
        return TypedActionResult.pass(stack);
    }

    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.BY, by = 2), cancellable = true)
    private void gbw_cancelDefaultBehavior(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, @Local World world, @Local BlockPos blockPos, @Local(ordinal = 0) BlockState blockState, @Local PlayerEntity playerEntity) {
        if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT)) {
            CampfireBlock.extinguish(playerEntity, world, blockPos, blockState);
            if (!world.isClient) {
                BlockState state = blockState.with(CampfireBlock.LIT, false);
                world.setBlockState(blockPos, state, 11);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, state));
                if (playerEntity != null) {
                    context.getStack().damage(1, playerEntity, (p) -> {
                        p.sendToolBreakStatus(context.getHand());
                    });
                }
            }

            cir.setReturnValue(ActionResult.success(world.isClient));
        }
        cir.setReturnValue(ActionResult.PASS);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int i = getMaxUseTime(stack) - remainingUseTicks;

        if (i > 5 && i % 4 == 0) {
            BlockHitResult blockHitResult;
            if (user instanceof PlayerEntity player && (blockHitResult = canPlayerPath(world, player)) != null) {
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState state = world.getBlockState(pos);

                if (player instanceof ServerPlayerEntity serverPlayer)
                    Criteria.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);

                if (!world.isClient) {
                    world.setBlockState(pos, PATH_STATES.get(state.getBlock()), 11);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state));

                    if (!player.isCreative()) {
                        stack.damage(1, player, p -> {
                            p.sendToolBreakStatus(p.getActiveHand());
                        });
                    }
                }

                player.swingHand(player.getActiveHand());
            }
        }
    }

    @Unique
    @Nullable
    private BlockHitResult canPlayerPath(World world, PlayerEntity player) {
        if (player.isSpectator())
            return null;

        HitResult hit = player.raycast(PlayerEntity.getReachDistance(player.isCreative()), 0f, false);
        if (hit instanceof BlockHitResult blockHitResult) {
            BlockState state = world.getBlockState(blockHitResult.getBlockPos());
            if (PATH_STATES.containsKey(state.getBlock())) {
                return blockHitResult;
            }
        }
        return null;
    }
}
