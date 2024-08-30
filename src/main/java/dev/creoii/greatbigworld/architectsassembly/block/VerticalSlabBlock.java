package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import dev.creoii.greatbigworld.architectsassembly.block.enums.VerticalSlabType;
import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class VerticalSlabBlock extends Block implements Fluidloggable {
    public static final EnumProperty<VerticalSlabType> TYPE = EnumProperty.of("type", VerticalSlabType.class);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public VerticalSlabBlock(Settings settings) {
        super(settings.luminance(state -> state.get(FLUIDLOGGED) == FluidType.LAVA ? 15 : 0));
        setDefaultState(getDefaultState().with(TYPE, VerticalSlabType.NORTH).with(FACING, Direction.NORTH).with(FLUIDLOGGED, FluidType.EMPTY));
    }

    @Nullable
    public static Block fromSlab(Block block) {
        if (block instanceof SlabBlock) {
            String path = Registries.BLOCK.getId(block).getPath();
            Block verticalSlab = Registries.BLOCK.get(new Identifier(ArchitectsAssembly.NAMESPACE, "vertical_" + path));
            return verticalSlab == Blocks.AIR ? null : verticalSlab;
        }
        return null;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        Direction direction = rotation.rotate(state.get(TYPE).getDirection());
        return state.get(TYPE) == VerticalSlabType.DOUBLE ? state : state.with(TYPE, VerticalSlabType.fromDirection(direction)).with(FACING, direction);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        VerticalSlabType type = state.get(TYPE);
        if (type == VerticalSlabType.DOUBLE || mirror == BlockMirror.NONE)
            return state;

        if ((mirror == BlockMirror.LEFT_RIGHT && type.getDirection().getAxis() == Direction.Axis.Z) || (mirror == BlockMirror.FRONT_BACK && type.getDirection().getAxis() == Direction.Axis.X)) {
            Direction direction = state.get(TYPE).getDirection().getOpposite();
            return state.with(TYPE, VerticalSlabType.fromDirection(direction)).with(FACING, direction);
        }

        return state;
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return state.get(TYPE) != VerticalSlabType.DOUBLE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TYPE, FACING, FLUIDLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(TYPE).getShape();
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockPos pos = context.getBlockPos();
        BlockState state = context.getWorld().getBlockState(pos);
        Direction direction = getDirectionForPlacement(context);
        if (state.getBlock() == this) {
            return state.with(TYPE, VerticalSlabType.DOUBLE).with(FACING, direction).with(FLUIDLOGGED, FluidType.EMPTY);
        }

        FluidState fluid = context.getWorld().getFluidState(pos);
        return getDefaultState().with(FLUIDLOGGED, Fluidloggable.FLUIDS.get(fluid.getFluid())).with(TYPE, VerticalSlabType.fromDirection(direction));
    }

    private Direction getDirectionForPlacement(ItemPlacementContext context) {
        Direction side = context.getSide();
        if (side.getAxis() != Direction.Axis.Y)
            return side;

        BlockPos pos = context.getBlockPos();
        Vec3d vec3d = context.getHitPos().subtract(new Vec3d(pos.getX(), pos.getY(), pos.getZ())).subtract(.5d, 0d, .5d);
        return Direction.fromRotation(Math.atan2(vec3d.x, vec3d.z) * -180d / Math.PI).getOpposite();
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        VerticalSlabType type = state.get(TYPE);
        return type != VerticalSlabType.DOUBLE && context.getStack().isOf(asItem()) && (context.canReplaceExisting() && (context.getSide() == type.getDirection() && getDirectionForPlacement(context) == type.getDirection()) || (!context.canReplaceExisting() && context.getSide() != type.getDirection()));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        FluidType fluidType = state.get(FLUIDLOGGED);
        if (fluidType.getFluid() instanceof FlowableFluid flowableFluid) {
            return flowableFluid.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return state.get(TYPE) != VerticalSlabType.DOUBLE && Fluidloggable.super.tryFillWithFluid(world, pos, state, fluidState);
    }

    @Override
    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != VerticalSlabType.DOUBLE && Fluidloggable.super.canFillWithFluid(player, world, pos, state, fluid);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(FLUIDLOGGED) != FluidType.EMPTY) {
            Fluid fluid = state.get(FLUIDLOGGED).getFluid();
            world.scheduleFluidTick(pos, fluid, fluid.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return type == NavigationType.WATER && state.getFluidState().isIn(FluidTags.WATER);
    }
}
