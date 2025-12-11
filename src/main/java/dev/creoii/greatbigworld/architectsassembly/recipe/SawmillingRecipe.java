package dev.creoii.greatbigworld.architectsassembly.recipe;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.display.SlotDisplay;

public class SawmillingRecipe extends SingleItemRecipe {
    public SawmillingRecipe(String group, Ingredient input, ItemStack output) {
        super(group, input, output);
    }

    @Override
    public RecipeSerializer<? extends SingleItemRecipe> getSerializer() {
        return ArchitectsAssemblyRecipes.SAWMLLING_SERIALIZER;
    }

    @Override
    public RecipeType<? extends SingleItemRecipe> getType() {
        return ArchitectsAssemblyRecipes.SAWMILLING;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    public SlotDisplay createResultDisplay() {
        return new SlotDisplay.ItemStackSlotDisplay(result());
    }
}
