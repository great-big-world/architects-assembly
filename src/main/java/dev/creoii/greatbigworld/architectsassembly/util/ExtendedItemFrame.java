package dev.creoii.greatbigworld.architectsassembly.util;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BlockStateManagers;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public interface ExtendedItemFrame {
    BooleanProperty DYED = BooleanProperty.of("dyed");

    @Nullable
    DyeColor gbw$getColor();

    void gbw$setColor(DyeColor color);

    boolean gbw$isWaxed();

    void gbw$setWaxed(boolean waxed);

    static BlockState getStateForItemFrame(boolean hasGlow, boolean hasMap, boolean isDyed) {
        return BlockStateManagers.getStateForItemFrame(hasGlow, hasMap).with(DYED, isDyed);
    }
}
