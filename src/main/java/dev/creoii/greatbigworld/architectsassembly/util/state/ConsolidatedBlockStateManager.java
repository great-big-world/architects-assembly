package dev.creoii.greatbigworld.architectsassembly.util.state;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ConsolidatedBlockStateManager {
    private final Class<?> blockClass;
    private final Supplier<Block> registerBlock;
    private StateManager<Block, BlockState> stateManager;
    private final List<Block> blocks;
    private final Map<Block, BlockState> cachedDefaultStates;

    protected ConsolidatedBlockStateManager(Class<?> blockClass, Supplier<Block> registerBlock) {
        this.blockClass = blockClass;
        this.registerBlock = registerBlock;
        this.blocks = Lists.newArrayList();
        this.cachedDefaultStates = Maps.newHashMap();
    }

    public void init() {
        stateManager = registerBlock.get().getStateManager();
    }

    public StateManager<Block, BlockState> getStateManager() {
        return stateManager;
    }

    public void addBlock(Block block) {
        if (block.getClass().isAssignableFrom(blockClass)) {
            blocks.add(block);
        }
    }

    public void addBlocks(Block... blocks) {
        for (Block block : blocks) {
            addBlock(block);
        }
    }

    public BlockState getTemplateState() {
        return stateManager.getDefaultState();
    }

    private BlockState createDefaultState(Block block) {
        return new BlockState(block, new Reference2ObjectArrayMap<>(), MapCodec.of(Encoder.empty(), Decoder.unit(block::getDefaultState)));
    }

    @Nullable
    public BlockState getDefaultState(Block block) {
        if (cachedDefaultStates.containsKey(block)) {
            System.out.println("get cached state for " + block.getTranslationKey());
            return cachedDefaultStates.get(block);
        } else if (blocks.contains(block)) {
            System.out.println("get default state for " + block.getTranslationKey());
            BlockState templateState = createDefaultState(block);
            return cachedDefaultStates.put(block, copyPropertiesToBlock(block, templateState));
        }
        return null;
    }

    public static BlockState copyPropertiesToBlock(Block to, BlockState from) {
        BlockState copy = to.getDefaultState();
        for (Property<?> property : from.getProperties()) {
            copy = copyProperty(copy, from, property);
        }
        return copy;
    }

    private static <T extends Comparable<T>> BlockState copyProperty(BlockState to, BlockState from, Property<T> property) {
        if (to.contains(property))
            return to.with(property, from.get(property));
        return to;
    }
}
