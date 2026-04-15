package dev.creoii.greatbigworld.architectsassembly.block;

import com.mojang.serialization.MapCodec;
import dev.creoii.greatbigworld.architectsassembly.block.entity.KilnBlockEntity;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlockEntities;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyStats;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class KilnBlock extends AbstractFurnaceBlock {
    public static final MapCodec<KilnBlock> CODEC = simpleCodec(KilnBlock::new);

    public KilnBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends AbstractFurnaceBlock> codec() {
        return CODEC;
    }

    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new KilnBlockEntity(blockPos, blockState);
    }

    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createFurnaceTicker(level, blockEntityType, ArchitectsAssemblyBlockEntities.KILN);
    }

    protected void openContainer(Level level, BlockPos blockPos, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof KilnBlockEntity kilnBlockEntity) {
            player.openMenu(kilnBlockEntity);
            player.awardStat(ArchitectsAssemblyStats.INTERACT_WITH_KILN);
        }
    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(LIT)) {
            double d = (double)blockPos.getX() + (double)0.5F;
            double e = blockPos.getY();
            double f = (double)blockPos.getZ() + (double)0.5F;
            if (randomSource.nextDouble() < 0.1) {
                level.playLocalSound(d, e, f, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = blockState.getValue(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52;
            double h = randomSource.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : h;
            double j = randomSource.nextDouble() * (double)6.0F / (double)16.0F;
            double k = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : h;
            level.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0F, 0.0F, 0.0F);
            level.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0F, 0.0F, 0.0F);
        }
    }
}
