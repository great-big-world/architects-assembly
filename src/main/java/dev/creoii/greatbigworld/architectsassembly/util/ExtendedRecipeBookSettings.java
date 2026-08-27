package dev.creoii.greatbigworld.architectsassembly.util;

import com.mojang.serialization.MapCodec;
import net.minecraft.stats.RecipeBookSettings;

public interface ExtendedRecipeBookSettings {
    MapCodec<RecipeBookSettings.TypeSettings> KILN_MAP_CODEC = RecipeBookSettings.TypeSettings.codec("isKilnGuiOpen", "isKilnFilteringCraftable");
    MapCodec<RecipeBookSettings.TypeSettings> OVEN_MAP_CODEC = RecipeBookSettings.TypeSettings.codec("isOvenGuiOpen", "isOvenFilteringCraftable");

    RecipeBookSettings.TypeSettings great_big_world$kiln();

    void great_big_world$setKiln(RecipeBookSettings.TypeSettings kiln);

    RecipeBookSettings.TypeSettings great_big_world$oven();

    void great_big_world$setOven(RecipeBookSettings.TypeSettings oven);
}
