package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.enums.VerticalSlabType;
import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class VerticalSlabBlock extends Block implements Fluidloggable {
    public static final EnumProperty<VerticalSlabType> TYPE = EnumProperty.create("type", VerticalSlabType.class);
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public VerticalSlabBlock(Properties settings) {
        super(settings.lightLevel(state -> state.getValue(FLUIDLOGGED) == FluidType.LAVA ? 15 : 0));
        registerDefaultState(defaultBlockState().setValue(TYPE, VerticalSlabType.NORTH).setValue(FACING, Direction.NORTH).setValue(FLUIDLOGGED, FluidType.EMPTY));
    }

    @Nullable
    public static Block fromSlab(Block block) {
        if (block instanceof SlabBlock) {
            String path = BuiltInRegistries.BLOCK.getKey(block).getPath();
            Block verticalSlab = BuiltInRegistries.BLOCK.getValue(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_" + path));
            return verticalSlab == Blocks.AIR ? null : verticalSlab;
        }
        return null;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        Direction direction = rotation.rotate(state.getValue(TYPE).getDirection());
        return state.getValue(TYPE) == VerticalSlabType.DOUBLE ? state : state.setValue(TYPE, VerticalSlabType.fromDirection(direction)).setValue(FACING, direction);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        VerticalSlabType type = state.getValue(TYPE);
        if (type == VerticalSlabType.DOUBLE || mirror == Mirror.NONE)
            return state;

        if ((mirror == Mirror.LEFT_RIGHT && type.getDirection().getAxis() == Direction.Axis.Z) || (mirror == Mirror.FRONT_BACK && type.getDirection().getAxis() == Direction.Axis.X)) {
            Direction direction = state.getValue(TYPE).getDirection().getOpposite();
            return state.setValue(TYPE, VerticalSlabType.fromDirection(direction)).setValue(FACING, direction);
        }

        return state;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return state.getValue(TYPE) != VerticalSlabType.DOUBLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, FACING, FLUIDLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(TYPE).getShape();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState state = context.getLevel().getBlockState(pos);
        Direction direction = getDirectionForPlacement(context);
        if (state.getBlock() == this) {
            return state.setValue(TYPE, VerticalSlabType.DOUBLE).setValue(FACING, direction).setValue(FLUIDLOGGED, FluidType.EMPTY);
        }

        FluidState fluid = context.getLevel().getFluidState(pos);
        return defaultBlockState().setValue(FLUIDLOGGED, Fluidloggable.FLUIDS.get(fluid.getType())).setValue(TYPE, VerticalSlabType.fromDirection(direction));
    }

    private Direction getDirectionForPlacement(BlockPlaceContext context) {
        Direction side = context.getClickedFace();
        if (side.getAxis() != Direction.Axis.Y)
            return side;

        BlockPos pos = context.getClickedPos();
        Vec3 vec3d = context.getClickLocation().subtract(new Vec3(pos.getX(), pos.getY(), pos.getZ())).subtract(.5d, 0d, .5d);
        return Direction.fromYRot(Math.atan2(vec3d.x, vec3d.z) * -180d / Math.PI).getOpposite();
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        VerticalSlabType type = state.getValue(TYPE);
        return type != VerticalSlabType.DOUBLE && context.getItemInHand().is(asItem()) && (context.replacingClickedOnBlock() && (context.getClickedFace() == type.getDirection() && getDirectionForPlacement(context) == type.getDirection()) || (!context.replacingClickedOnBlock() && context.getClickedFace() != type.getDirection()));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        FluidType fluidType = state.getValue(FLUIDLOGGED);
        if (fluidType.getFluid() instanceof FlowingFluid flowableFluid) {
            return flowableFluid.getSource(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.getValue(TYPE) != VerticalSlabType.DOUBLE && Fluidloggable.super.placeLiquid(world, pos, state, fluidState);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable LivingEntity filler, BlockGetter world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.getValue(TYPE) != VerticalSlabType.DOUBLE && Fluidloggable.super.canPlaceLiquid(filler, world, pos, state, fluid);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(FLUIDLOGGED) != FluidType.EMPTY) {
            Fluid fluid = state.getValue(FLUIDLOGGED).getFluid();
            tickView.scheduleTick(pos, fluid, fluid.getTickDelay(world));
        }

        return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return type == PathComputationType.WATER && state.getFluidState().is(FluidTags.WATER);
    }
}
