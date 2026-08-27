package dev.creoii.greatbigworld.architectsassembly.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.farmsandfriends.recipe.OvenRecipe;
import dev.creoii.farmsandfriends.registry.FarmsAndFriendsRecipes;
import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyMenus;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyRecipes;
import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.function.Consumer;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin implements SawmillingRecipeManager {
    @Shadow @Final private static Logger LOGGER;
    @Shadow private static boolean isIngredientEnabled(FeatureFlagSet features, Ingredient ingredient) {
        return false;
    }

    @Unique
    private static final List<Identifier> CRAFTING_RECIPES_TO_REMOVE = new ImmutableList.Builder<Identifier>()
            .add(Identifier.parse("chiseled_deepslate"))
            .add(Identifier.parse("chiseled_nether_bricks"))
            .add(Identifier.parse("chiseled_polished_blackstone"))
            .add(Identifier.parse("chiseled_quartz_block"))
            .add(Identifier.parse("chiseled_red_sandstone"))
            .add(Identifier.parse("chiseled_sandstone"))
            .add(Identifier.parse("chiseled_stone_bricks"))
            .add(Identifier.parse("chiseled_tuff"))
            .add(Identifier.parse("chiseled_copper"))
            .add(Identifier.parse("purpur_pillar"))
            .add(Identifier.parse("quartz_pillar"))
            .add(Identifier.parse("deepslate_tiles"))
            .add(Identifier.parse("bamboo_mosaic"))
            .build();
    @Unique private SelectableRecipe.SingleInputSet<SawmillingRecipe> sawmillingRecipes;
    @Unique private List<SelectableRecipe.SingleInputEntry<SawmillingRecipe>> preSawmillingRecipes;

    @Override
    public SelectableRecipe.SingleInputSet<SawmillingRecipe> gbw$getSawmillingRecipes() {
        return sawmillingRecipes;
    }

    @SuppressWarnings("unchecked")
    @Redirect(method = "finalizeRecipeLoading", at = @At(value = "INVOKE", target = "Ljava/util/Collection;forEach(Ljava/util/function/Consumer;)V"))
    private void gbw$manageRecipes(Collection<RecipeHolder<?>> instance, Consumer<RecipeHolder<?>> consumer, @Local(argsOnly = true) FeatureFlagSet features, @Local(ordinal = 0) List<SelectableRecipe.SingleInputEntry<StonecutterRecipe>> list, @Local(ordinal = 1) List<RecipeManager.IngredientCollector> list2) {
        preSawmillingRecipes = new ArrayList<>();
        instance.forEach(recipe -> {
            Recipe<?> recipe2 = recipe.value();
            if (!recipe2.isSpecial() && recipe2.placementInfo().isImpossibleToPlace()) {
                LOGGER.warn("Recipe {} can't be placed due to empty ingredients and will be ignored", recipe.id().identifier());
            } else {
                list2.forEach(builder -> builder.accept(recipe2));
                if (recipe2 instanceof StonecutterRecipe stonecuttingRecipe) {
                    RecipeHolder<StonecutterRecipe> recipeEntry = (RecipeHolder<StonecutterRecipe>) recipe;
                    if (isIngredientEnabled(features, stonecuttingRecipe.input()) && stonecuttingRecipe.resultDisplay().isEnabled(features)) {
                        list.add(new SelectableRecipe.SingleInputEntry<>(stonecuttingRecipe.input(), new SelectableRecipe<>(stonecuttingRecipe.resultDisplay(), Optional.of(recipeEntry))));
                    }
                } else if (recipe2 instanceof SawmillingRecipe sawmillingRecipe) {
                    RecipeHolder<SawmillingRecipe> recipeEntry = (RecipeHolder<SawmillingRecipe>) recipe;
                    if (isIngredientEnabled(features, sawmillingRecipe.input()) && sawmillingRecipe.createResultDisplay().isEnabled(features)) {
                        preSawmillingRecipes.add(new SelectableRecipe.SingleInputEntry<>(sawmillingRecipe.input(), new SelectableRecipe<>(sawmillingRecipe.createResultDisplay(), Optional.of(recipeEntry))));
                    }
                }
            }
        });
    }

    @Inject(method = "finalizeRecipeLoading", at = @At("TAIL"))
    private void gbw$setSawmillingRecipes(FeatureFlagSet features, CallbackInfo ci) {
        sawmillingRecipes = new SelectableRecipe.SingleInputSet<>(preSawmillingRecipes);
    }

    @WrapOperation(method = "unpackRecipeInfo", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private static <E> boolean gbw$removeRecipes(List<RecipeManager.ServerDisplayInfo> instance, E e, Operation<Boolean> original, @Local RecipeHolder<?> recipeEntry, @Local RecipeDisplayEntry recipeDisplayEntry) {
        if (!CRAFTING_RECIPES_TO_REMOVE.contains(recipeEntry.id().identifier()) && recipeEntry.value().getType() == RecipeType.CRAFTING) {
            return original.call(instance, e);
        } else return false;
    }

    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Ljava/util/Map;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map;"))
    private static Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> gbw$injectOvenRecipeProperty(Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> original) {
        Map<ResourceKey<RecipePropertySet>, RecipeManager.IngredientExtractor> map = Maps.newHashMap();
        map.putAll(original);
        map.put(ArchitectsAssemblyMenus.KILN_INPUT, (recipe) -> {
            if (recipe.getType() == ArchitectsAssemblyRecipes.FIRING && recipe instanceof SingleItemRecipe singleItemRecipe) {
                return Optional.of(singleItemRecipe.input());
            } else return Optional.empty();
        });
        map.put(FarmsAndFriendsRecipes.OVEN_INPUT, recipe -> {
            if (recipe instanceof OvenRecipe ovenRecipe) {
                Item[] stacks = ovenRecipe.ingredients().stream()
                        .flatMap(i -> Arrays.stream(i.items().map(Holder::value).toArray(Item[]::new)))
                        .toArray(Item[]::new);
                return Optional.of(Ingredient.of(stacks));
            } else return Optional.empty();
        });
        return map;
    }
}
