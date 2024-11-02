package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import net.minecraft.recipe.CuttingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArchitectsAssemblyRecipes {
    public static final RecipeType<SawmillingRecipe> SAWMILLING = new RecipeType<>() {
        @Override
        public String toString() {
            return "sawmilling";
        }
    };
    public static final RecipeSerializer<SawmillingRecipe> SAWMLLING_SERIALIZER = new CuttingRecipe.Serializer<>(SawmillingRecipe::new);

    public static void register() {
        Registry.register(Registries.RECIPE_TYPE, new Identifier(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMILLING);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMLLING_SERIALIZER);
    }
}
