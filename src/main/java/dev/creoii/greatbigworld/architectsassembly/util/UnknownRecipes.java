package dev.creoii.greatbigworld.architectsassembly.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.recipe.RecipeEntry;

import java.util.Set;

@Environment(EnvType.CLIENT)
public interface UnknownRecipes {
    Set<RecipeEntry<?>> gbw$getUnknownRecipes();
}
