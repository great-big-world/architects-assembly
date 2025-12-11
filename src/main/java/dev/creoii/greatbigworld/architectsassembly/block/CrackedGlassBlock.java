package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblySoundEvents;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyTags;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class CrackedGlassBlock extends TransparentBlock {
    private final Supplier<BlockState> fixed;
    private final Supplier<Item> shard;

    public CrackedGlassBlock(Properties settings, Supplier<BlockState> fixed, Supplier<Item> shard) {
        super(settings);
        this.fixed = fixed;
        this.shard = shard;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        if (stack.is(shard.get())) {
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            world.setBlock(pos, fixed.get(), 3);
            world.playSound(player, pos, ArchitectsAssemblySoundEvents.BLOCK_GLASS_REPAIR, SoundSource.BLOCKS, 1f, 1f);
            stack.consume(1, player);
            if (!world.isClientSide())
                return InteractionResult.SUCCESS_SERVER;
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, world, pos, player, hit);
    }

    @Override
    public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!world.isClientSide()) {
            BlockPos pos = hit.getBlockPos();
            if (projectile.mayInteract((ServerLevel) world, pos) && projectile.mayBreak((ServerLevel) world) && projectile.getDeltaMovement().length() > .4d) {
                world.destroyBlock(pos, true, projectile);
            }
        }
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        entity.causeFallDamage(fallDistance * .8f, .5f, entity.damageSources().fall());
        if (!world.isClientSide() && fallDistance >= .6f && !entity.getType().is(ArchitectsAssemblyTags.LIGHTWEIGHT_ENTITIES)) {
            world.destroyBlock(pos, !(entity instanceof Player player) || !player.isCreative(), entity);
        }
    }
}
