package dev.creoii.greatbigworld.architectsassembly.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Unique
    private final List<Identifier> CRAFTING_RECIPES_TO_REMOVE = new ImmutableList.Builder<Identifier>()
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

    @Redirect(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;put(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap$Builder;"))
    private <K, V> ImmutableMap.Builder<K, V> gbw$removeRecipes(ImmutableMap.Builder<K, V> instance, K key, V value) {
        RecipeEntry<?> recipeEntry = (RecipeEntry<?>) value;
        if (CRAFTING_RECIPES_TO_REMOVE.contains(recipeEntry.id())) {
            return instance;
        }
        return instance.put(key, value);
    }
}
