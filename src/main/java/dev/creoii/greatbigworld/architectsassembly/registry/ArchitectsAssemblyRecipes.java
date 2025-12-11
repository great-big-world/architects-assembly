package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;

public class ArchitectsAssemblyRecipes {
    public static final RecipeType<SawmillingRecipe> SAWMILLING = new RecipeType<>() {
        @Override
        public String toString() {
            return "sawmilling";
        }
    };
    public static final RecipeSerializer<SawmillingRecipe> SAWMLLING_SERIALIZER = new SingleItemRecipe.Serializer<>(SawmillingRecipe::new);

    public static void register() {
        Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMILLING);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmilling"), SAWMLLING_SERIALIZER);
    }
}
