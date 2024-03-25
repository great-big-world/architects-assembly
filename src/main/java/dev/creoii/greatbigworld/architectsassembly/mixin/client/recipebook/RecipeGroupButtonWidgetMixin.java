package dev.creoii.greatbigworld.architectsassembly.mixin.client.recipebook;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.ItemRenderHelper;
import dev.creoii.greatbigworld.architectsassembly.util.UnknownRecipes;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.recipebook.AnimatedResultButton;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(AnimatedResultButton.class)
public class RecipeGroupButtonWidgetMixin {
    @Shadow private int currentResultIndex;
    @Shadow private RecipeResultCollection resultCollection;

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItemWithoutEntity(Lnet/minecraft/item/ItemStack;II)V"))
    private void gbw$blackAllItems(DrawContext instance, ItemStack stack, int x, int y, @Local List<RecipeEntry<?>> list) {
        RecipeEntry<?> recipeEntry = list.get(currentResultIndex);
        if (((UnknownRecipes) resultCollection).gbw$getUnknownRecipes().contains(recipeEntry))
            ItemRenderHelper.drawItemSilhouette(instance, null, instance.client.world, stack, x, y, 0, 0);
        else
            instance.drawItemWithoutEntity(stack, x, y);
    }

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItem(Lnet/minecraft/item/ItemStack;IIII)V"))
    private void gbw$blackMultipleItemOutputs(DrawContext instance, ItemStack stack, int x, int y, int seed, int z, @Local List<RecipeEntry<?>> list) {
        RecipeEntry<?> recipeEntry = list.get(currentResultIndex);
        if (((UnknownRecipes) resultCollection).gbw$getUnknownRecipes().contains(recipeEntry))
            ItemRenderHelper.drawItemSilhouette(instance, null, instance.client.world, stack, x, y, seed, z);
        else
            instance.drawItem(stack, x, y, seed, z);
    }
}
