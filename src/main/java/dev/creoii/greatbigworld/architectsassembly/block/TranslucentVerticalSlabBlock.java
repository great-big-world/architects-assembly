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

            VerticalSlabType slabType = state.get(VerticalSlabBlock.TYPE);
            VerticalSlabType otherType = other.get(VerticalSlabBlock.TYPE);

            if (slabType != VerticalSlabType.DOUBLE) {
                if (direction == slabType.getDirection().getOpposite() && (otherType == slabType.getOpposite() || otherType == VerticalSlabType.DOUBLE)) {
                    return true;
                } else if ((slabType.getPerpendiculars().contains(direction) || direction.getAxis() == Direction.Axis.Y) && slabType == otherType) {
                    return true;
                }
            } else {
                if (otherType.getDirection() == direction || otherType == VerticalSlabType.DOUBLE) {
                    return true;
                }
            }

            return false;
            //return state.get(VerticalSlabBlock.TYPE) != VerticalSlabType.DOUBLE || other.get(VerticalSlabBlock.TYPE) == VerticalSlabType.DOUBLE;
        }
        return super.isSideInvisible(state, other, direction);
    }
}
