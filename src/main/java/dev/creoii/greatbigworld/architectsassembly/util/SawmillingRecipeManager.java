package dev.creoii.greatbigworld.architectsassembly.util;

import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import net.minecraft.recipe.display.CuttingRecipeDisplay;

public interface SawmillingRecipeManager {
    CuttingRecipeDisplay.Grouping<SawmillingRecipe> gbw$getSawmillingRecipes();
}
