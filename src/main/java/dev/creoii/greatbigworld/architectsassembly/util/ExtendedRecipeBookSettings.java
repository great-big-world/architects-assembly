package dev.creoii.greatbigworld.architectsassembly.util;

import com.mojang.serialization.MapCodec;
import net.minecraft.stats.RecipeBookSettings;

public interface ExtendedRecipeBookSettings {
    MapCodec<RecipeBookSettings.TypeSettings> KILN_MAP_CODEC = RecipeBookSettings.TypeSettings.codec("isKilnGuiOpen", "isKilnFilteringCraftable");

    RecipeBookSettings.TypeSettings great_big_world$kiln();

    void great_big_world$setKiln(RecipeBookSettings.TypeSettings kiln);
}
