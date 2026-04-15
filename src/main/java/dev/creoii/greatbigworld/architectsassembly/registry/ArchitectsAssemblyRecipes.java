package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.recipe.FiringRecipe;
import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.*;

public class ArchitectsAssemblyRecipes {
    public static final RecipeType<SawmillingRecipe> SAWMILLING = new RecipeType<>() {
        @Override
        public String toString() {
            return "sawmilling";
        }
    };
    public static final RecipeType<FiringRecipe> FIRING = new RecipeType<>() {
        @Override
        public String toString() {
            return "kiln";
        }
    };
    public static final RecipeSerializer<SawmillingRecipe> SAWMLLING_SERIALIZER = new SingleItemRecipe.Serializer<>(SawmillingRecipe::new);
    public static final RecipeSerializer<FiringRecipe> FIRING_SERIALIZER = new AbstractCookingRecipe.Serializer<>(FiringRecipe::new, 200);
    public static final RecipeBookCategory SAWMILL_CATEGORY = new RecipeBookCategory();
    public static final RecipeBookCategory KILN_CATEGORY = new RecipeBookCategory();

    public static void register() {
        Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMILLING);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "firing"), FIRING);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMLLING_SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "firing"), FIRING_SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmill"), SAWMILL_CATEGORY);
        Registry.register(BuiltInRegistries.RECIPE_BOOK_CATEGORY, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "kiln"), KILN_CATEGORY);
    }
}
