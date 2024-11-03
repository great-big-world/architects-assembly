package dev.creoii.greatbigworld.architectsassembly.recipe;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CuttingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.world.World;

public class SawmillingRecipe extends CuttingRecipe {
    public SawmillingRecipe(String group, Ingredient input, ItemStack output) {
        super(ArchitectsAssemblyRecipes.SAWMILLING, ArchitectsAssemblyRecipes.SAWMLLING_SERIALIZER, group, input, output);
    }

    @Override
    public boolean matches(SingleStackRecipeInput input, World world) {
        return ingredient.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ArchitectsAssemblyBlocks.SAWMILL);
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }
}
