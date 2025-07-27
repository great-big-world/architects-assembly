package dev.creoii.greatbigworld.architectsassembly.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.tick.ScheduledTickView;

public class CopperPressurePlateBlock extends OxidizableBlock {
    private static final VoxelShape PRESSED_SHAPE = Block.createColumnShape(14f, 0f, .5f);
    private static final VoxelShape DEFAULT_SHAPE = Block.createColumnShape(14f, 0f, 1f);
    protected static final Box BOX = Block.createColumnShape(14f, 0f, 4f).getBoundingBoxes().getFirst();
    public static final EnumProperty<State> STATE = EnumProperty.of("state", State.class);
    private final Oxidizable.OxidationLevel oxidationLevel;

    public CopperPressurePlateBlock(Oxidizable.OxidationLevel oxidationLevel, AbstractBlock.Settings settings) {
        super(oxidationLevel, settings);
        this.oxidationLevel = oxidationLevel;
        setDefaultState(stateManager.getDefaultState().with(STATE, State.UP));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(STATE) == State.DOWN ? PRESSED_SHAPE : DEFAULT_SHAPE;
    }

    public boolean canMobSpawnInside(BlockState state) {
        return true;
    }

    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return hasTopRim(world, blockPos) || sideCoversSmallSquare(world, blockPos, Direction.UP);
    }

    protected int getTickRate() {
        return switch (oxidationLevel) {
            case OXIDIZED -> 30;
            case WEATHERED -> 20;
            case EXPOSED -> 10;
            case UNAFFECTED -> 0;
        };
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler) {
        if (!world.isClient) {
            world.setBlockState(pos, state.with(STATE, State.DOWN), 2);
            world.scheduleBlockTick(new BlockPos(pos), this, getTickRate());
        }
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(STATE) == State.DOWN && getEntityCount(world, BOX.offset(pos), LivingEntity.class) <= 0) {
            world.setBlockState(pos, state.with(STATE, State.POWERED), 2);
            world.scheduleBlockTick(new BlockPos(pos), this, 5);
            updateNeighbors(world, pos);

            world.playSound(null, pos, BlockSetType.COPPER.pressurePlateClickOn(), SoundCategory.BLOCKS);
            world.emitGameEvent(null, GameEvent.BLOCK_ACTIVATE, pos);
        } else if (state.get(STATE) == State.POWERED) {
            world.setBlockState(pos, state.with(STATE, State.UP), 2);
            updateNeighbors(world, pos);

            world.playSound(null, pos, BlockSetType.COPPER.pressurePlateClickOff(), SoundCategory.BLOCKS);
            world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    protected static int getEntityCount(World world, Box box, Class<? extends Entity> entityClass) {
        return world.getEntitiesByClass(entityClass, box, EntityPredicates.EXCEPT_SPECTATOR.and(entity -> !entity.canAvoidTraps())).size();
    }

    protected void updateNeighbors(World world, BlockPos pos) {
        world.updateNeighbors(pos, this);
        world.updateNeighbors(pos.down(), this);
    }

    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return getRedstoneOutput(state);
    }

    protected int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return direction == Direction.UP ? getRedstoneOutput(state) : 0;
    }

    private static int getRedstoneOutput(BlockState state) {
        return state.get(STATE) == State.POWERED ? 15 : 0;
    }

    protected boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    public enum State implements StringIdentifiable {
        UP,
        DOWN,
        POWERED;

        @Override
        public String asString() {
            return name().toLowerCase();
        }
    }
}
