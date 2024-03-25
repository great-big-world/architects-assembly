package dev.creoii.greatbigworld.architectsassembly.util;

import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public interface ExtendedItemFrame {
    @Nullable
    DyeColor gbw$getColor();

    void gbw$setColor(DyeColor color);

    boolean gbw$isWaxed();

    void gbw$setWaxed(boolean waxed);
}
