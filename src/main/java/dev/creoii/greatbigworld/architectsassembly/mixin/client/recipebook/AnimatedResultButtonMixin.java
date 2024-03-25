package dev.creoii.greatbigworld.architectsassembly.mixin.client.recipebook;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.UnknownRecipes;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.recipebook.AnimatedResultButton;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AnimatedResultButton.class)
public abstract class AnimatedResultButtonMixin {
    @Shadow private RecipeResultCollection resultCollection;
    @Shadow protected abstract List<RecipeEntry<?>> getResults();
    @Shadow private int currentResultIndex;

    @Inject(method = "getTooltip", at = @At("RETURN"), cancellable = true)
    private void gbw$applyUnknownTooltips(CallbackInfoReturnable<List<Text>> cir) {
        RecipeEntry<?> recipeEntry = getResults().get(currentResultIndex);
        if (((UnknownRecipes) resultCollection).gbw$getUnknownRecipes().contains(recipeEntry)) {
            cir.setReturnValue(List.of(Text.of("???")));
        }
    }

    @Inject(method = "renderWidget", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;floor(F)I"), cancellable = true)
    private void gbw$stopIfDivBy0(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci, @Local List<RecipeEntry<?>> list) {
        if (list.isEmpty()) {
            ci.cancel();
            context.getMatrices().pop();
        }
    }

    @Inject(method = "getResults", at = @At("RETURN"), cancellable = true)
    private void gbw$filterUnknownResults(CallbackInfoReturnable<List<RecipeEntry<?>>> cir) {
        List<RecipeEntry<?>> list = cir.getReturnValue();;
        RecipeEntry<?> temp = list.get(0);

        list.removeIf(recipeEntry -> ((UnknownRecipes) resultCollection).gbw$getUnknownRecipes().contains(recipeEntry));

        if (list.isEmpty())
            cir.setReturnValue(List.of(temp));
        else
            cir.setReturnValue(list);
    }
}
