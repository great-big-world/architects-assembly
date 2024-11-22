package dev.creoii.greatbigworld.architectsassembly.util.state;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConsolidatedBlockStateManager {
    private final Class<?> blockClass;
    private final StateManager<Block, BlockState> stateManager;
    private final List<Block> blocks;

    protected ConsolidatedBlockStateManager(Class<?> blockClass, StateManager<Block, BlockState> stateManager) {
        this.blockClass = blockClass;
        this.stateManager = stateManager;
        this.blocks = Lists.newArrayList();
    }

    public StateManager<Block, BlockState> getStateManager() {
        return stateManager;
    }

    public void addBlock(Block block) {
        if (block.getClass() == blockClass)
            blocks.add(block);
    }

    public void addBlocks(Block... blocks) {
        for (Block block : blocks) {
            addBlock(block);
        }
    }

    public BlockState getDefaultState() {
        return stateManager.getDefaultState();
    }

    @Nullable
    public BlockState getDefaultState(Block block) {
        if (blocks.contains(block)) {
            BlockState templateState = stateManager.getDefaultState();
            BlockState defaultState = block.getDefaultState();
            for (Property<?> property : templateState.getProperties()) {
                if (property instanceof BooleanProperty booleanProperty) {
                    defaultState = copyBooleanProperty(defaultState, templateState, booleanProperty);
                } else if (property instanceof IntProperty intProperty) {
                    defaultState = copyIntProperty(defaultState, templateState, intProperty);
                } else if (property instanceof DirectionProperty directionProperty) {
                    defaultState = copyEnumProperty(defaultState, templateState, directionProperty);
                } else if (property instanceof EnumProperty<?> enumProperty) {
                    defaultState = copyEnumProperty(defaultState, templateState, enumProperty);
                }
            }
            return defaultState;
        }
        return null;
    }

    private BlockState copyBooleanProperty(BlockState to, BlockState from, BooleanProperty property) {
        return to.with(property, from.get(property));
    }

    private BlockState copyIntProperty(BlockState to, BlockState from, IntProperty property) {
        return to.with(property, from.get(property));
    }

    private <T extends Enum<T> & StringIdentifiable> BlockState copyEnumProperty(BlockState to, BlockState from, EnumProperty<T> property) {
        return to.with(property, from.get(property));
    }
}
