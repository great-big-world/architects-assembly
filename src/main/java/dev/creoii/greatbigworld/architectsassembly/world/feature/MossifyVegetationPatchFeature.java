package dev.creoii.greatbigworld.architectsassembly.world.feature;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;

public class MossifyVegetationPatchFeature extends VegetationPatchFeature {
    public static final Map<Block, Block> MOSSY_CONVERSIONS = new ImmutableMap.Builder<Block, Block>()
            .put(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE)
            .put(Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB)
            .put(Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS)
            .put(Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL)
            .put(ArchitectsAssemblyBlocks.VERTICAL_COBBLESTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB)
            .put(Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS)
            .put(Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB)
            .put(Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS)
            .put(Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL)
            .put(ArchitectsAssemblyBlocks.VERTICAL_STONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB)
            .put(Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_MOSSY_STONE_BRICKS)
            .put(Blocks.BRICKS, ArchitectsAssemblyBlocks.MOSSY_BRICKS)
            .put(Blocks.BRICK_SLAB, ArchitectsAssemblyBlocks.MOSSY_BRICK_SLAB)
            .put(Blocks.BRICK_STAIRS, ArchitectsAssemblyBlocks.MOSSY_BRICK_STAIRS)
            .put(Blocks.BRICK_WALL, ArchitectsAssemblyBlocks.MOSSY_BRICK_WALL)
            .put(ArchitectsAssemblyBlocks.VERTICAL_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_BRICK_SLAB)
            .build();

    public MossifyVegetationPatchFeature(Codec<VegetationPatchConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<VegetationPatchConfiguration> context) {
        WorldGenLevel world = context.level();
        VegetationPatchConfiguration config = context.config();
        RandomSource random = context.random();
        BlockPos pos = context.origin();
        Predicate<BlockState> predicate = state -> state.is(config.replaceable) || MOSSY_CONVERSIONS.containsKey(state.getBlock());
        int i = config.xzRadius.sample(random) + 1;
        int j = config.xzRadius.sample(random) + 1;
        Set<BlockPos> positions = placeGroundPatch(world, config, random, pos, predicate, i, j);
        return !positions.isEmpty();
    }

    protected Set<BlockPos> placeGroundPatch(WorldGenLevel world, VegetationPatchConfiguration config, RandomSource random, BlockPos pos, Predicate<BlockState> replaceable, int radiusX, int radiusZ) {
        BlockPos.MutableBlockPos mutable = pos.mutable();
        BlockPos.MutableBlockPos mutable2 = mutable.mutable();
        Direction direction = config.surface.getDirection();
        Direction oppositeDirection = direction.getOpposite();
        HashSet<BlockPos> set = new HashSet<>();
        for (int i = -radiusX; i <= radiusX; ++i) {
            boolean bl = i == -radiusX || i == radiusX;
            for (int j = -radiusZ; j <= radiusZ; ++j) {
                int k;
                boolean bl2 = j == -radiusZ || j == radiusZ;
                boolean bl3 = bl && bl2;
                boolean bl4 = (bl || bl2) && !bl3;
                if (bl3 || bl4 && (config.extraEdgeColumnChance == 0f || random.nextFloat() > config.extraEdgeColumnChance))
                    continue;
                mutable.setWithOffset(pos, i, 0, j);
                for (k = 0; world.isStateAtPosition(mutable, BlockBehaviour.BlockStateBase::isAir) && k < config.verticalRange; ++k) {
                    mutable.move(direction);
                }
                for (k = 0; world.isStateAtPosition(mutable, state -> !state.isAir()) && k < config.verticalRange; ++k) {
                    mutable.move(oppositeDirection);
                }
                mutable2.setWithOffset(mutable, config.surface.getDirection());
                BlockState blockState = world.getBlockState(mutable2);
                if (!world.isEmptyBlock(mutable) || (!blockState.isFaceSturdy(world, mutable2, config.surface.getDirection().getOpposite()) && !MOSSY_CONVERSIONS.containsKey(blockState.getBlock())))
                    continue;
                int depth = config.depth.sample(random) + (config.extraBottomBlockChance > 0f && random.nextFloat() < config.extraBottomBlockChance ? 1 : 0);
                if (!placeGround(world, config, replaceable, random, mutable2, depth))
                    continue;
                set.add(mutable2.immutable());
            }
        }
        return set;
    }

    @Override
    protected boolean placeGround(WorldGenLevel world, VegetationPatchConfiguration config, Predicate<BlockState> replaceable, RandomSource random, BlockPos.MutableBlockPos pos, int depth) {
        for (int i = 0; i < depth; ++i) {
            BlockState state = world.getBlockState(pos);
            if (MOSSY_CONVERSIONS.containsKey(state.getBlock())) {
                world.setBlock(pos, MOSSY_CONVERSIONS.get(state.getBlock()).withPropertiesOf(state), Block.UPDATE_CLIENTS);
            }
            pos.move(config.surface.getDirection());
        }
        return true;
    }
}
