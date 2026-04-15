package dev.creoii.greatbigworld.architectsassembly.recipe;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyItems;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyRecipes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class FiringRecipe extends AbstractCookingRecipe {
    public FiringRecipe(String string, CookingBookCategory cookingBookCategory, Ingredient ingredient, ItemStack itemStack, float f, int i) {
        super(string, cookingBookCategory, ingredient, itemStack, f, i);
    }

    @Override
    protected Item furnaceIcon() {
        return ArchitectsAssemblyItems.KILN;
    }

    @Override
    public RecipeSerializer<FiringRecipe> getSerializer() {
        return ArchitectsAssemblyRecipes.FIRING_SERIALIZER;
    }

    @Override
    public RecipeType<FiringRecipe> getType() {
        return ArchitectsAssemblyRecipes.FIRING;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return ArchitectsAssemblyRecipes.KILN_CATEGORY;
    }
}
