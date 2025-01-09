package dev.creoii.greatbigworld.architectsassembly.block;

import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import dev.creoii.greatbigworld.floraandfauna.util.SnowyHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.WallShape;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;

public class TemplateWallBlock extends Block {
    public static final BooleanProperty UP = Properties.UP;
    public static final EnumProperty<WallShape> EAST_SHAPE = Properties.EAST_WALL_SHAPE;
    public static final EnumProperty<WallShape> NORTH_SHAPE = Properties.NORTH_WALL_SHAPE;
    public static final EnumProperty<WallShape> SOUTH_SHAPE = Properties.SOUTH_WALL_SHAPE;
    public static final EnumProperty<WallShape> WEST_SHAPE = Properties.WEST_WALL_SHAPE;

    public TemplateWallBlock(Settings settings) {
        super(settings);
        setDefaultState(stateManager.getDefaultState().with(UP, true).with(EAST_SHAPE, WallShape.NONE).with(WEST_SHAPE, WallShape.NONE).with(NORTH_SHAPE, WallShape.NONE).with(SOUTH_SHAPE, WallShape.NONE).with(SnowyHelper.SNOW_LAYERS, 0).with(Fluidloggable.FLUIDLOGGED, FluidType.EMPTY));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, NORTH_SHAPE, EAST_SHAPE, WEST_SHAPE, SOUTH_SHAPE, Fluidloggable.FLUIDLOGGED, SnowyHelper.SNOW_LAYERS);
    }
}
