package dev.creoii.greatbigworld.architectsassembly.mixin;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillRecipeDisplay;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.RecipeDisplays;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeDisplays.class)
public class RecipeDisplaysMixin {
    @Inject(method = "bootstrap", at = @At("HEAD"))
    private static void gbw$registerSawmillRecipeDisplay(Registry<RecipeDisplay.Type<?>> registry, CallbackInfoReturnable<RecipeDisplay.Type<?>> cir) {
        Registry.register(registry, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmill"), SawmillRecipeDisplay.TYPE);
    }
}
