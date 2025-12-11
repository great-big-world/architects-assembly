package dev.creoii.greatbigworld.architectsassembly.block.enums;

import java.util.Arrays;
import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public enum VerticalSlabType implements StringRepresentable {
    NORTH(Direction.NORTH),
    SOUTH(Direction.SOUTH),
    WEST(Direction.WEST),
    EAST(Direction.EAST),
    DOUBLE(null);

    private final String name;
    private final Direction direction;
    private final VoxelShape shape;

    VerticalSlabType(Direction direction) {
        name = direction == null ? "double" : direction.getSerializedName();
        this.direction = direction;

        if (direction == null)
            shape = Shapes.block();
        else {
            double min = 0d;
            double max = 8d;
            if (direction.getAxisDirection() == Direction.AxisDirection.NEGATIVE) {
                min = 8d;
                max = 16d;
            }

            if (direction.getAxis() == Direction.Axis.X) {
                shape = Block.box(min, 0d, 0d, max, 16d, 16d);
            } else
                shape = Block.box(0d, 0d, min, 16d, 16d, max);
        }
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public Direction getDirection() {
        return direction;
    }

    public VoxelShape getShape() {
        return shape;
    }

    public VerticalSlabType getOpposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            case EAST -> WEST;
            case DOUBLE -> DOUBLE;
        };
    }

    public List<Direction> getPerpendiculars() {
        return switch (this) {
            case NORTH, SOUTH -> List.of(Direction.WEST, Direction.EAST);
            case WEST, EAST -> List.of(Direction.NORTH, Direction.SOUTH);
            case DOUBLE -> List.of();
        };
    }

    public static VerticalSlabType fromDirection(Direction direction) {
        for (VerticalSlabType type : VerticalSlabType.values())
            if (direction == type.direction)
                return type;

        return null;
    }

    public static List<VoxelShape> getShapes() {
        return Arrays.stream(values()).map(type -> type.shape).toList();
    }
}
