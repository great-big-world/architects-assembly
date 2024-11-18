package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.architectsassembly.block.enums.VerticalSlabType;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;

public class TranslucentVerticalSlabBlock extends VerticalSlabBlock {
    public TranslucentVerticalSlabBlock(Settings settings) {
        super(settings);
    }

    protected boolean isSideInvisible(BlockState state, BlockState other, Direction direction) {
        if (other.isOf(this)) {
            return state.get(VerticalSlabBlock.TYPE) != VerticalSlabType.DOUBLE || other.get(VerticalSlabBlock.TYPE) == VerticalSlabType.DOUBLE;
        }
        return super.isSideInvisible(state, other, direction);
    }
}
