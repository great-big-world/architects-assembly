package dev.creoii.greatbigworld.architectsassembly.util;

import net.minecraft.client.resources.model.BlockStateDefinitions;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public interface ExtendedItemFrame {
    BooleanProperty DYED = BooleanProperty.create("dyed");

    @Nullable
    DyeColor gbw$getColor();

    void gbw$setColor(DyeColor color);

    static BlockState getStateForItemFrame(boolean hasGlow, boolean hasMap, boolean isDyed) {
        return BlockStateDefinitions.getItemFrameFakeState(hasGlow, hasMap).setValue(DYED, isDyed);
    }
}
