package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.architectsassembly.block.enums.VerticalSlabType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class TranslucentVerticalSlabBlock extends VerticalSlabBlock {
    public TranslucentVerticalSlabBlock(Properties settings) {
        super(settings);
    }

    protected boolean skipRendering(BlockState state, BlockState other, Direction direction) {
        if (other.is(this)) {

            VerticalSlabType slabType = state.getValue(VerticalSlabBlock.TYPE);
            VerticalSlabType otherType = other.getValue(VerticalSlabBlock.TYPE);

            if (slabType != VerticalSlabType.DOUBLE) {
                if (direction == slabType.getDirection().getOpposite() && (otherType == slabType.getOpposite() || otherType == VerticalSlabType.DOUBLE)) {
                    return true;
                } else return (slabType.getPerpendiculars().contains(direction) || direction.getAxis() == Direction.Axis.Y) && slabType == otherType;
            } else {
                return otherType.getDirection() == direction || otherType == VerticalSlabType.DOUBLE;
            }
            //return state.get(VerticalSlabBlock.TYPE) != VerticalSlabType.DOUBLE || other.get(VerticalSlabBlock.TYPE) == VerticalSlabType.DOUBLE;
        }
        return super.skipRendering(state, other, direction);
    }
}
