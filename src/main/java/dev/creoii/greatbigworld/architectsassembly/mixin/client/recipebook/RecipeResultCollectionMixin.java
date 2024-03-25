package dev.creoii.greatbigworld.architectsassembly.mixin.client.recipebook;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import dev.creoii.greatbigworld.architectsassembly.util.UnknownRecipes;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.book.RecipeBook;
import net.minecraft.registry.DynamicRegistryManager;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Set;

@Mixin(RecipeResultCollection.class)
public class RecipeResultCollectionMixin implements UnknownRecipes {
    @Shadow @Final @Mutable
    private List<RecipeEntry<?>> recipes;
    @Unique
    private Set<RecipeEntry<?>> gbw$unknownRecipes;

    @Override
    public Set<RecipeEntry<?>> gbw$getUnknownRecipes() {
        return gbw$unknownRecipes;
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void gbw$initUnknownRecipesSet(DynamicRegistryManager registryManager, List<RecipeEntry<?>> recipes, CallbackInfo ci) {
        gbw$unknownRecipes = Sets.newHashSet();
    }

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/List;size()I"))
    private void gbw$makeRecipesMutable(DynamicRegistryManager registryManager, List<RecipeEntry<?>> recipes, CallbackInfo ci) {
        this.recipes = Lists.newArrayList(recipes);
    }

    @Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/book/RecipeBook;contains(Lnet/minecraft/recipe/RecipeEntry;)Z"))
    private boolean gbw$unlockAllRecipes(RecipeBook instance, @Nullable RecipeEntry<?> recipe) {
        if (!instance.contains(recipe))
            gbw$unknownRecipes.add(recipe);
        return recipe != null;
    }

    @Redirect(method = "computeCraftables", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/book/RecipeBook;contains(Lnet/minecraft/recipe/RecipeEntry;)Z"))
    private boolean gbw$computeAllRecipes(RecipeBook instance, @Nullable RecipeEntry<?> recipe) {
        return recipe != null;
    }
}
