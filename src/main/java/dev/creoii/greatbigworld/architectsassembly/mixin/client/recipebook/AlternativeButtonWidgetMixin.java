package dev.creoii.greatbigworld.architectsassembly.mixin.client.recipebook;

import dev.creoii.greatbigworld.architectsassembly.util.ItemRenderHelper;
import dev.creoii.greatbigworld.architectsassembly.util.RecipeResultCollectionView;
import dev.creoii.greatbigworld.architectsassembly.util.UnknownRecipes;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.recipebook.RecipeAlternativesWidget;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipeAlternativesWidget.AlternativeButtonWidget.class)
public class AlternativeButtonWidgetMixin implements RecipeResultCollectionView {
    @Unique private RecipeResultCollection gbw$results;
    @Shadow @Final RecipeEntry<?> recipe;

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItem(Lnet/minecraft/item/ItemStack;II)V", remap = false))
    private void gbw$blackoutItemTexture(DrawContext instance, ItemStack stack, int x, int y) {
        if (((UnknownRecipes) gbw$results).gbw$getUnknownRecipes().contains(recipe))
            ItemRenderHelper.drawItemSilhouette(instance, null, instance.client.world, stack, x, y, 0, 0);
        else instance.drawItem(stack, x, y);
    }

    @Override
    public void gbw$setResults(RecipeResultCollection results) {
        this.gbw$results = results;
    }
}
