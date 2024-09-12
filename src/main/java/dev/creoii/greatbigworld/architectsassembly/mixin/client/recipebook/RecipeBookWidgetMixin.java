package dev.creoii.greatbigworld.architectsassembly.mixin.client.recipebook;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.UnknownRecipes;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RecipeBookWidget.class)
public class RecipeBookWidgetMixin {
    @Shadow private @Nullable TextFieldWidget searchField;

    @Inject(method = "refreshResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/recipebook/RecipeBookResults;setResults(Ljava/util/List;Z)V"))
    private void gbw$filterUnknownRecipesOnSearch(boolean resetCurrentPage, CallbackInfo ci, @Local(ordinal = 1) List<RecipeResultCollection> list2) {
        if (searchField != null && !searchField.getText().isEmpty()) {
            list2.forEach(recipeResultCollection -> {
                recipeResultCollection.getAllRecipes().removeIf(recipeEntry -> {
                    return ((UnknownRecipes) recipeResultCollection).gbw$getUnknownRecipes().contains(recipeEntry);
                });
            });
            list2.removeIf(recipeResultCollection -> {
                return recipeResultCollection.getAllRecipes().isEmpty();
            });
        }
    }
}
