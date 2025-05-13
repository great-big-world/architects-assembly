package dev.creoii.greatbigworld.architectsassembly.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TorchBlock extends AbstractTorchBlock {
    protected static final MapCodec<SimpleParticleType> PARTICLE_TYPE_CODEC = Registries.PARTICLE_TYPE.getCodec().comapFlatMap(particleType -> {
        DataResult<SimpleParticleType> dataResult;
        if (particleType instanceof SimpleParticleType defaultParticleType) {
            dataResult = DataResult.success(defaultParticleType);
        } else dataResult = DataResult.error(() -> "Not a SimpleParticleType: " + particleType);
        return dataResult;
    }, particleType -> particleType).fieldOf("particle_options");
    public static final MapCodec<TorchBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(PARTICLE_TYPE_CODEC.forGetter(block -> {
            return block.particle;
        }), createSettingsCodec()).apply(instance, TorchBlock::new);
    });
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty WEST = Properties.WEST;
    public static final BooleanProperty UP = Properties.UP;
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.createCuboidShape(5.5d, 3d, 11d, 10.5d, 13d, 16d),
            Direction.SOUTH, Block.createCuboidShape(5.5d, 3d, 0d, 10.5d, 13d, 5d),
            Direction.WEST, Block.createCuboidShape(11d, 3d, 5.5d, 16.0, 13d, 10.5d),
            Direction.EAST, Block.createCuboidShape(0d, 3d, 5.5d, 5d, 13d, 10.5d),
            Direction.UP, SHAPE
    ));
    protected final SimpleParticleType particle;

    public TorchBlock(SimpleParticleType particle, Settings settings) {
        super(settings);
        this.particle = particle;
        setDefaultState(getStateManager().getDefaultState().with(UP, false).with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false));
    }

    @Override
    protected MapCodec<? extends AbstractTorchBlock> getCodec() {
        return CODEC;
    }

    private BooleanProperty toProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            case UP, DOWN -> UP;
        };
    }

    private boolean hasDirection(BlockState state, Direction direction) {
        if (!state.isOf(this))
            return false;

        return switch (direction) {
            case NORTH -> state.get(NORTH);
            case SOUTH -> state.get(SOUTH);
            case EAST -> state.get(EAST);
            case WEST -> state.get(WEST);
            case UP -> state.get(UP);
            case DOWN -> false;
        };
    }

    private Set<Direction> collectDirections(BlockState state) {
        Set<Direction> directions = new HashSet<>();

        if (state.get(NORTH))
            directions.add(Direction.NORTH);
        if (state.get(SOUTH))
            directions.add(Direction.SOUTH);
        if (state.get(EAST))
            directions.add(Direction.EAST);
        if (state.get(WEST))
            directions.add(Direction.WEST);
        if (state.get(UP))
            directions.add(Direction.UP);

        return directions;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.empty();

        for (Direction direction : collectDirections(state)) {
            shape = VoxelShapes.union(shape, BOUNDING_SHAPES.get(direction));
        }

        return shape;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Direction side = ctx.getSide();

        if (!state.isOf(this) && state.isReplaceable()) {
            if (canPlaceInDirection(world, pos, side))
                return getDefaultState().with(toProperty(side), true);
            return placeAtFirstValidDirection(getDefaultState(), world, pos, ctx.getPlacementDirections());
        } else {
            BlockState hitState = world.getBlockState(ctx.getHitResult().getBlockPos());
            Direction opposite = side.getOpposite();
            if (hitState.isOf(this)) {
                return placeAtFirstValidDirection(state, world, pos, ctx.getPlacementDirections());
            } else if (canPlaceInDirection(world, pos, side)) {
                if (hasDirection(state, side))
                    return placeAtFirstValidDirection(state, world, pos, ctx.getPlacementDirections());
                return state.with(toProperty(side), true);
            } else if (canPlaceInDirection(world, pos, opposite)) {
                if (hasDirection(state, opposite))
                    return placeAtFirstValidDirection(state, world, pos, ctx.getPlacementDirections());
                return state.with(toProperty(opposite), true);
            }
        }
        return null;
    }

    private BlockState placeAtFirstValidDirection(BlockState state, WorldView world, BlockPos pos, Direction[] placementDirections) {
        for (Direction direction : placementDirections) {
            if (!canPlaceInDirection(world, pos, direction) || direction == Direction.DOWN || hasDirection(state, direction))
                continue;

            return state.with(toProperty(direction), true);
        }
        return null;
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (state.get(NORTH) || state.get(SOUTH) || state.get(EAST) || state.get(WEST) || state.get(UP))
            return context.getStack().isOf(asItem());
        return true;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        BlockState updated = state;

        if (state.get(NORTH) && direction == Direction.SOUTH && !sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite()))
            updated = updated.with(NORTH, false);

        if (state.get(SOUTH) && direction == Direction.NORTH && !sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite()))
            updated = updated.with(SOUTH, false);

        if (state.get(EAST) && direction == Direction.WEST && !sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite()))
            updated = updated.with(EAST, false);

        if (state.get(WEST) && direction == Direction.EAST && !sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite()))
            updated = updated.with(WEST, false);

        if (state.get(UP) && direction == Direction.DOWN && !sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite()))
            updated = updated.with(UP, false);

        return updated;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (canPlaceInDirection(world, pos, direction))
                return true;
        }
        return false;
    }

    private boolean canPlaceInDirection(WorldView world, BlockPos pos, Direction direction) {
        if (direction == Direction.DOWN)
            return sideCoversSmallSquare(world, pos.offset(Direction.DOWN), Direction.UP);
        return sideCoversSmallSquare(world, pos.offset(direction.getOpposite()), direction);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double d = (double) pos.getX() + .5d;
        double e = (double) pos.getY() + .7d;
        double f = (double) pos.getZ() + .5d;

        for (Direction direction : collectDirections(state)) {
            if (direction == Direction.UP) {
                world.addParticleClient(ParticleTypes.SMOKE, d, e, f, 0d, 0d, 0d);
                world.addParticleClient(particle, d, e, f, 0d, 0d, 0d);
            } else {
                Direction opposite = direction.getOpposite();
                world.addParticleClient(ParticleTypes.SMOKE, d + .27d * (double)opposite.getOffsetX(), e + .22d, f + .27d * (double)opposite.getOffsetZ(), 0d, 0d, 0d);
                world.addParticleClient(this.particle, d + .27d * (double)opposite.getOffsetX(), e + .22d, f + .27d * (double)opposite.getOffsetZ(), 0d, 0d, 0d);
            }
        }
    }
}
