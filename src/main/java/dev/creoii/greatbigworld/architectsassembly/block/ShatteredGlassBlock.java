package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyItems;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblySoundEvents;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ShatteredGlassBlock extends TransparentBlock {
    public ShatteredGlassBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());
        if (stack.isOf(ArchitectsAssemblyItems.GLASS_SHARD)) {
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state));
            world.setBlockState(pos, ArchitectsAssemblyBlocks.GLASS.getDefaultState(), 3);
            world.playSound(player, pos, ArchitectsAssemblySoundEvents.BLOCK_GLASS_REPAIR, SoundCategory.BLOCKS, 1f, 1f);
            if (!player.isCreative())
                stack.decrement(1);
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient) {
            BlockPos pos = hit.getBlockPos();
            if (projectile.canModifyAt(world, pos) && projectile.canBreakBlocks(world) && projectile.getVelocity().length() > .4d) {
                world.breakBlock(pos, true, projectile);
            }
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance * .8f, .5f, entity.getDamageSources().fall());
        if (!world.isClient && fallDistance >= .6f && !entity.getType().isIn(ArchitectsAssemblyTags.LIGHTWEIGHT_ENTITIES)) {
            world.breakBlock(pos, !(entity instanceof PlayerEntity player) || !player.isCreative(), entity);
        }
    }
}
