package dev.creoii.greatbigworld.architectsassembly.block.enums;

import net.minecraft.block.Block;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.Arrays;
import java.util.List;

public enum VerticalSlabType implements StringIdentifiable {
    NORTH(Direction.NORTH),
    SOUTH(Direction.SOUTH),
    WEST(Direction.WEST),
    EAST(Direction.EAST),
    DOUBLE(null);

    private final String name;
    private final Direction direction;
    private final VoxelShape shape;

    VerticalSlabType(Direction direction) {
        name = direction == null ? "double" : direction.asString();
        this.direction = direction;

        if (direction == null)
            shape = VoxelShapes.fullCube();
        else {
            double min = 0d;
            double max = 8d;
            if (direction.getDirection() == Direction.AxisDirection.NEGATIVE) {
                min = 8d;
                max = 16d;
            }

            if (direction.getAxis() == Direction.Axis.X) {
                shape = Block.createCuboidShape(min, 0d, 0d, max, 16d, 16d);
            } else
                shape = Block.createCuboidShape(0d, 0d, min, 16d, 16d, max);
        }
    }

    @Override
    public String asString() {
        return name;
    }

    public Direction getDirection() {
        return direction;
    }

    public VoxelShape getShape() {
        return shape;
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
