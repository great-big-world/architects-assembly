package dev.creoii.greatbigworld.architectsassembly.mixin.client.recipebook;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.ItemRenderHelper;
import dev.creoii.greatbigworld.architectsassembly.util.UnknownRecipes;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.recipebook.AnimatedResultButton;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AnimatedResultButton.class)
public abstract class AnimatedResultButtonMixin {
    @Shadow private RecipeResultCollection resultCollection;
    @Shadow protected abstract List<RecipeEntry<?>> getResults();
    @Shadow private int currentResultIndex;
    @Shadow public abstract RecipeEntry<?> currentRecipe();

    @Unique private static final Text UNKNOWN_RECIPE = Text.translatable("gui.recipebook.unknownRecipe");

    @Inject(method = "getTooltip", at = @At("HEAD"), cancellable = true)
    private void gbw$applyUnknownTooltips(CallbackInfoReturnable<List<Text>> cir) {
        if (currentResultIndex < getResults().size()) {
            if (((UnknownRecipes) resultCollection).gbw$getUnknownRecipes().contains(currentRecipe())) {
                cir.setReturnValue(List.of(UNKNOWN_RECIPE));
            }
        } else cir.setReturnValue(List.of(UNKNOWN_RECIPE));
    }

    @Inject(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor(F)I"), cancellable = true)
    private void gbw$stopIfDivBy0(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, @Local List<RecipeEntry<?>> list, @Local boolean bl) {
        if (list.isEmpty()) {
            if (bl)
                context.getMatrices().pop();
            ci.cancel();
        }
    }

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItemWithoutEntity(Lnet/minecraft/item/ItemStack;II)V"))
    private void gbw$blackAllItems(DrawContext instance, ItemStack stack, int x, int y, @Local List<RecipeEntry<?>> list) {
        if (list.isEmpty()) {
            instance.drawItemWithoutEntity(stack, x, y);
            return;
        }
        if (((UnknownRecipes) resultCollection).gbw$getUnknownRecipes().contains(currentRecipe()))
            ItemRenderHelper.drawItemSilhouette(instance, null, instance.client.world, stack, x, y, 0, 0);
        else instance.drawItemWithoutEntity(stack, x, y);
    }

    @Redirect(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItem(Lnet/minecraft/item/ItemStack;IIII)V"))
    private void gbw$blackMultipleItemOutputs(DrawContext instance, ItemStack stack, int x, int y, int seed, int z, @Local List<RecipeEntry<?>> list) {
        if (((UnknownRecipes) resultCollection).gbw$getUnknownRecipes().contains(currentRecipe()))
            ItemRenderHelper.drawItemSilhouette(instance, null, instance.client.world, stack, x, y, seed, z);
        else instance.drawItem(stack, x, y, seed, z);
    }

    @Inject(method = "currentRecipe", at = @At("HEAD"), cancellable = true)
    private void gbw$nullCurrentRecipe(CallbackInfoReturnable<RecipeEntry<?>> cir) {
        if (getResults().isEmpty())
            cir.setReturnValue(null);
    }
}
