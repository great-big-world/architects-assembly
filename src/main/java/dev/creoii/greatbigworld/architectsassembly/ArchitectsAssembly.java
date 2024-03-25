package dev.creoii.greatbigworld.architectsassembly;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import dev.creoii.creoapi.api.event.misc.RecipeEvents;
import dev.creoii.greatbigworld.architectsassembly.block.VerticalSlabBlock;
import dev.creoii.greatbigworld.architectsassembly.registry.*;
import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import dev.creoii.greatbigworld.architectsassembly.world.feature.MossifyVegetationPatchFeature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.VegetationPatchFeatureConfig;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArchitectsAssembly implements ModInitializer {
    public static final String NAMESPACE = "great_big_world";
    public static final Logger LOGGER = LogManager.getLogger(ArchitectsAssembly.class);
    public static final Identifier SYNC_INVENTORY_PACKET_ID = new Identifier(ArchitectsAssembly.NAMESPACE, "sync_inventory");
    private final Map<RecipeType<?>, List<Identifier>> RECIPES_TO_REMOVE = new ImmutableMap.Builder<RecipeType<?>, List<Identifier>>()
            .put(RecipeType.CRAFTING, new ImmutableList.Builder<Identifier>()
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
                    .build())
            .build();

    @Override
    @SuppressWarnings("deprecation")
    public void onInitialize() {
        ArchitectsAssemblyBlocks.register();
        ArchitectsAssemblyItems.register();
        ArchitectsAssemblySoundEvents.register();
        ArchitectsAssemblyScreens.register();
        ArchitectsAssemblyRecipes.register();
        ArchitectsAssemblyStats.register();

        Registry.register(Registries.FEATURE, new Identifier(NAMESPACE, "mossify_vegetation_patch"), new MossifyVegetationPatchFeature(VegetationPatchFeatureConfig.CODEC));

        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.burnChances.forEach((block, integer) -> {
            Block verticalSlab = VerticalSlabBlock.fromSlab(block);
            if (verticalSlab != null) {
                fireBlock.burnChances.put(verticalSlab, integer);
            }
        });
        fireBlock.spreadChances.forEach((block, integer) -> {
            Block verticalSlab = VerticalSlabBlock.fromSlab(block);
            if (verticalSlab != null) {
                fireBlock.spreadChances.put(verticalSlab, integer);
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(SYNC_INVENTORY_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                for (int i = 0; i < PlayerInventory.MAIN_SIZE; ++i) {
                    int index = buf.readInt();
                    ItemStack stack = buf.readItemStack();
                    player.getInventory().main.set(index, stack);
                }
            });
        });

        RecipeEvents.LOAD_RECIPE.register((builder, recipeEntry) -> {
            List<Identifier> toRemove = RECIPES_TO_REMOVE.get(recipeEntry.value().getType());
            return toRemove == null || !toRemove.contains(recipeEntry.id());
        });

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return new Identifier(ArchitectsAssembly.NAMESPACE, "variant");
            }

            @Override
            public void reload(ResourceManager manager) {
                Variant.VARIANTS.clear();
                Map<Identifier, Resource> resourceMap = manager.findResources("variants", path -> path.getPath().endsWith(".json"));
                for (Map.Entry<Identifier, Resource> entry : resourceMap.entrySet()) {
                    Identifier identifier = entry.getKey();
                    Optional<Resource> optional = manager.getResource(identifier);
                    if (optional.isPresent()) {
                        try (InputStream stream = optional.get().getInputStream()) {
                            String result = IOUtils.toString(stream, StandardCharsets.UTF_8);
                            Identifier identifier1 = new Identifier(identifier.getNamespace(), identifier.getPath().replace("variants/", "").replace(".json", ""));
                            Variant variant = Variant.GSON.fromJson(result, Variant.class).build(identifier1);

                            if (variant.getItems().isEmpty() && variant.getItemTags().isEmpty()) {
                                ArchitectsAssembly.LOGGER.warn("Found empty variant definition: '" + identifier + "'");
                                continue;
                            }

                            if (Variant.VARIANTS.containsKey(identifier.getPath())) {
                                variant.copyTo(Variant.VARIANTS.get(identifier.getPath()));
                            } else Variant.VARIANTS.put(identifier.getPath(), variant);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
