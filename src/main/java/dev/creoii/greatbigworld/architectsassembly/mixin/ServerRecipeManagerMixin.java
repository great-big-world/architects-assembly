package dev.creoii.greatbigworld.architectsassembly.mixin;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.minecraft.recipe.*;
import net.minecraft.recipe.display.CuttingRecipeDisplay;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Mixin(ServerRecipeManager.class)
public abstract class ServerRecipeManagerMixin implements SawmillingRecipeManager {
    @Shadow @Final private static Logger LOGGER;
    @Shadow private static boolean isEnabled(FeatureSet features, Ingredient ingredient) {
        return false;
    }

    @Unique
    private static final List<Identifier> CRAFTING_RECIPES_TO_REMOVE = new ImmutableList.Builder<Identifier>()
            .add(Identifier.of("chiseled_deepslate"))
            .add(Identifier.of("chiseled_nether_bricks"))
            .add(Identifier.of("chiseled_polished_blackstone"))
            .add(Identifier.of("chiseled_quartz_block"))
            .add(Identifier.of("chiseled_red_sandstone"))
            .add(Identifier.of("chiseled_sandstone"))
            .add(Identifier.of("chiseled_stone_bricks"))
            .add(Identifier.of("chiseled_tuff"))
            .add(Identifier.of("chiseled_copper"))
            .add(Identifier.of("purpur_pillar"))
            .add(Identifier.of("quartz_pillar"))
            .add(Identifier.of("deepslate_tiles"))
            .add(Identifier.of("bamboo_mosaic"))
            .build();
    @Unique private CuttingRecipeDisplay.Grouping<SawmillingRecipe> sawmillingRecipes;
    @Unique private List<CuttingRecipeDisplay.GroupEntry<SawmillingRecipe>> preSawmillingRecipes;

    @Override
    public CuttingRecipeDisplay.Grouping<SawmillingRecipe> gbw$getSawmillingRecipes() {
        return sawmillingRecipes;
    }

    @Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Ljava/util/Collection;forEach(Ljava/util/function/Consumer;)V"))
    private void gbw$manageRecipes(Collection<RecipeEntry<?>> instance, Consumer<RecipeEntry<?>> consumer, @Local(argsOnly = true) FeatureSet features, @Local(ordinal = 0) List<CuttingRecipeDisplay.GroupEntry<StonecuttingRecipe>> list, @Local(ordinal = 1) List<ServerRecipeManager.PropertySetBuilder> list2) {
        preSawmillingRecipes = new ArrayList<>();
        instance.forEach(recipe -> {
            Recipe<?> recipe2 = recipe.value();
            if (!recipe2.isIgnoredInRecipeBook() && recipe2.getIngredientPlacement().hasNoPlacement()) {
                LOGGER.warn("Recipe {} can't be placed due to empty ingredients and will be ignored", recipe.id().getValue());
            } else {
                list2.forEach(builder -> builder.accept(recipe2));
                if (recipe2 instanceof StonecuttingRecipe stonecuttingRecipe) {
                    RecipeEntry<StonecuttingRecipe> recipeEntry = (RecipeEntry<StonecuttingRecipe>) recipe;
                    if (isEnabled(features, stonecuttingRecipe.ingredient()) && stonecuttingRecipe.createResultDisplay().isEnabled(features)) {
                        list.add(new CuttingRecipeDisplay.GroupEntry<>(stonecuttingRecipe.ingredient(), new CuttingRecipeDisplay<>(stonecuttingRecipe.createResultDisplay(), Optional.of(recipeEntry))));
                    }
                } else if (recipe2 instanceof SawmillingRecipe sawmillingRecipe) {
                    RecipeEntry<SawmillingRecipe> recipeEntry = (RecipeEntry<SawmillingRecipe>) recipe;
                    if (isEnabled(features, sawmillingRecipe.ingredient()) && sawmillingRecipe.createResultDisplay().isEnabled(features)) {
                        preSawmillingRecipes.add(new CuttingRecipeDisplay.GroupEntry<>(sawmillingRecipe.ingredient(), new CuttingRecipeDisplay<>(sawmillingRecipe.createResultDisplay(), Optional.of(recipeEntry))));
                    }
                }
            }
        });
    }

    @Inject(method = "initialize", at = @At("TAIL"))
    private void gbw$setSawmillingRecipes(FeatureSet features, CallbackInfo ci) {
        sawmillingRecipes = new CuttingRecipeDisplay.Grouping<>(preSawmillingRecipes);
    }

    @WrapWithCondition(method = "collectServerRecipes", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private static <E> boolean gbw$removeRecipes(List<ServerRecipeManager.ServerRecipe> instance, E e, @Local RecipeEntry<?> recipeEntry, @Local RecipeDisplayEntry recipeDisplayEntry) {
        return !CRAFTING_RECIPES_TO_REMOVE.contains(recipeEntry.id().getValue()) && recipeEntry.value().getType() == RecipeType.CRAFTING;
    }
}
