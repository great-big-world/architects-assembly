package dev.creoii.greatbigworld.architectsassembly.recipe;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SingleStackRecipe;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.world.World;

public class SawmillingRecipe extends SingleStackRecipe {
    public SawmillingRecipe(String group, Ingredient input, ItemStack output) {
        super(group, input, output);
    }

    @Override
    public RecipeSerializer<? extends SingleStackRecipe> getSerializer() {
        return ArchitectsAssemblyRecipes.SAWMLLING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends SingleStackRecipe> getType() {
        return ArchitectsAssemblyRecipes.SAWMILLING;
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return new RecipeBookCategory();
    }

    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
        return ingredient.test(input.getStackInSlot(0));
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    public SlotDisplay createResultDisplay() {
        return new SlotDisplay.StackSlotDisplay(result());
    }
}
