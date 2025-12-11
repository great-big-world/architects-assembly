package dev.creoii.greatbigworld.architectsassembly.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperFullBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CopperPressurePlateBlock extends WeatheringCopperFullBlock {
    private static final VoxelShape PRESSED_SHAPE = Block.column(14f, 0f, .5f);
    private static final VoxelShape DEFAULT_SHAPE = Block.column(14f, 0f, 1f);
    protected static final AABB BOX = Block.column(14f, 0f, 4f).toAabbs().getFirst();
    public static final EnumProperty<State> STATE = EnumProperty.create("state", State.class);
    private final WeatheringCopper.WeatherState oxidationLevel;

    public CopperPressurePlateBlock(WeatheringCopper.WeatherState oxidationLevel, BlockBehaviour.Properties settings) {
        super(oxidationLevel, settings);
        this.oxidationLevel = oxidationLevel;
        registerDefaultState(stateDefinition.any().setValue(STATE, State.UP));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(STATE) == State.DOWN ? PRESSED_SHAPE : DEFAULT_SHAPE;
    }

    public boolean isPossibleToRespawnInThis(BlockState state) {
        return true;
    }

    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return direction == Direction.DOWN && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.below();
        return canSupportRigidBlock(world, blockPos) || canSupportCenter(world, blockPos, Direction.UP);
    }

    protected int getTickRate() {
        return switch (oxidationLevel) {
            case OXIDIZED -> 30;
            case WEATHERED -> 20;
            case EXPOSED -> 10;
            case UNAFFECTED -> 0;
        };
    }

    protected void onEntityCollision(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler) {
        if (!world.isClientSide()) {
            if (state.getValue(STATE) == State.UP) {
                world.playSound(null, pos, BlockSetType.COPPER.pressurePlateClickOn(), SoundSource.BLOCKS);
                world.gameEvent(entity, GameEvent.BLOCK_ACTIVATE, pos);
                world.setBlock(pos, state.setValue(STATE, State.DOWN), 2);
            }

            world.scheduleTick(new BlockPos(pos), this, getTickRate());
        }
    }

    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (state.getValue(STATE) == State.DOWN && getEntityCount(world, BOX.move(pos)) <= 0) {
            world.setBlock(pos, state.setValue(STATE, State.POWERED), 2);
            world.scheduleTick(new BlockPos(pos), this, 5);
            updateNeighbors(world, pos);

            world.playSound(null, pos, BlockSetType.COPPER.pressurePlateClickOn(), SoundSource.BLOCKS);
            world.gameEvent(null, GameEvent.BLOCK_ACTIVATE, pos);
        } else if (state.getValue(STATE) == State.POWERED) {
            world.setBlock(pos, state.setValue(STATE, State.UP), 2);
            updateNeighbors(world, pos);

            world.playSound(null, pos, BlockSetType.COPPER.pressurePlateClickOff(), SoundSource.BLOCKS);
            world.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    protected static int getEntityCount(Level world, AABB box) {
        return world.getEntitiesOfClass(LivingEntity.class, box, EntitySelector.NO_SPECTATORS.and(entity -> !entity.isIgnoringBlockTriggers())).size();
    }

    protected void updateNeighbors(Level world, BlockPos pos) {
        world.updateNeighborsAt(pos, this);
        world.updateNeighborsAt(pos.below(), this);
    }

    protected int getSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return getRedstoneOutput(state);
    }

    protected int getDirectSignal(BlockState state, BlockGetter world, BlockPos pos, Direction direction) {
        return direction == Direction.UP ? getRedstoneOutput(state) : 0;
    }

    private static int getRedstoneOutput(BlockState state) {
        return state.getValue(STATE) == State.POWERED ? 15 : 0;
    }

    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    public enum State implements StringRepresentable {
        UP,
        DOWN,
        POWERED;

        @Override
        public String getSerializedName() {
            return name().toLowerCase();
        }
    }
}
