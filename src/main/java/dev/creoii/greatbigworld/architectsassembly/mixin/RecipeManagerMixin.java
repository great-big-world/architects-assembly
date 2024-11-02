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
    private final List<Identifier> RECIPES_TO_REMOVE = new ImmutableList.Builder<Identifier>()
            .add(new Identifier("chiseled_deepslate"))
            .add(new Identifier("chiseled_nether_bricks"))
            .add(new Identifier("chiseled_polished_blackstone"))
            .add(new Identifier("chiseled_quartz_block"))
            .add(new Identifier("chiseled_red_sandstone"))
            .add(new Identifier("chiseled_sandstone"))
            .add(new Identifier("chiseled_stone_bricks"))
            .add(new Identifier("purpur_pillar"))
            .add(new Identifier("quartz_pillar"))
            .add(new Identifier("deepslate_tiles"))
            .add(new Identifier("bamboo_mosaic"))
            .build();

    @Redirect(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;put(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap$Builder;"))
    private <K, V> ImmutableMap.Builder<K, V> gbw$removeRecipes(ImmutableMap.Builder<K, V> instance, K key, V value) {
        RecipeEntry<?> recipeEntry = (RecipeEntry<?>) value;
        if (RECIPES_TO_REMOVE.contains(recipeEntry.id())) {
            return instance;
        }
        return instance.put(key, value);
    }
}
