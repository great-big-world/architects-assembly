package dev.creoii.greatbigworld.architectsassembly.util;

import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import net.minecraft.world.item.crafting.SelectableRecipe;

public interface SawmillingRecipeManager {
    SelectableRecipe.SingleInputSet<SawmillingRecipe> gbw$getSawmillingRecipes();
}
