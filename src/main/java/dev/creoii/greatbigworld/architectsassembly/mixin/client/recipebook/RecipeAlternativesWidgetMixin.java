package dev.creoii.greatbigworld.architectsassembly.mixin.client.recipebook;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.RecipeResultCollectionView;
import net.minecraft.client.gui.screen.recipebook.RecipeAlternativesWidget;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.recipe.RecipeEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(RecipeAlternativesWidget.class)
public class RecipeAlternativesWidgetMixin {
    @Shadow private RecipeResultCollection resultCollection;

    @Redirect(method = "showAlternativesForResult", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 1))
    private <E> boolean gbw$viewRecipeResultCollection(List<RecipeAlternativesWidget.AlternativeButtonWidget> instance, E e, @Local(ordinal = 9) int q, @Local(ordinal = 10) int r, @Local RecipeEntry<?> recipeEntry, @Local(ordinal = 1) boolean bl2) {
        RecipeAlternativesWidget.AlternativeButtonWidget widget = ((RecipeAlternativesWidget) (Object) this).new AlternativeButtonWidget(q, r, recipeEntry, bl2);
        ((RecipeResultCollectionView) widget).gbw$setResults(resultCollection);
        return instance.add(widget);
    }
}
