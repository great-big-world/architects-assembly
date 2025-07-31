package dev.creoii.greatbigworld.architectsassembly;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.VerticalSlabBlock;
import dev.creoii.greatbigworld.architectsassembly.registry.*;
import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import dev.creoii.greatbigworld.architectsassembly.world.feature.MossifyVegetationPatchFeature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ArchitectsAssembly implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(ArchitectsAssembly.class);

    @Override
    @SuppressWarnings("deprecation")
    public void onInitialize() {
        ArchitectsAssemblyBlocks.register();
        ArchitectsAssemblyItems.register();
        ArchitectsAssemblyParticleTypes.register();
        ArchitectsAssemblySoundEvents.register();
        ArchitectsAssemblyScreens.register();
        ArchitectsAssemblyRecipes.register();
        ArchitectsAssemblyStats.register();

        Registry.register(Registries.FEATURE, Identifier.of(GreatBigWorld.NAMESPACE, "mossify_vegetation_patch"), new MossifyVegetationPatchFeature(VegetationPatchFeatureConfig.CODEC));

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

        DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
            modifyContext.modify(Items.IRON_AXE, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_PICKAXE, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_SHOVEL, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_HOE, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_SWORD, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_HELMET, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 265));
            modifyContext.modify(Items.IRON_CHESTPLATE, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 340));
            modifyContext.modify(Items.IRON_LEGGINGS, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 325));
            modifyContext.modify(Items.IRON_BOOTS, builder -> builder.add(DataComponentTypes.MAX_DAMAGE, 295));
        });

        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return Identifier.of(GreatBigWorld.NAMESPACE, "variant");
            }

            @Override
            public void reload(ResourceManager manager) {
                Variant.VARIANTS.clear();
                Map<Identifier, List<Resource>> resourceMap = manager.findAllResources("variants", path -> path.getPath().endsWith(".json"));
                for (Map.Entry<Identifier, List<Resource>> entry : resourceMap.entrySet()) {
                    Identifier identifier = entry.getKey();
                    for (Resource resource : entry.getValue()) {
                        try (InputStream stream = resource.getInputStream()) {
                            String result = IOUtils.toString(stream, StandardCharsets.UTF_8);
                            Identifier identifier1 = Identifier.of(identifier.getNamespace(), identifier.getPath().replace("variants/", "").replace(".json", ""));
                            Variant variant = Variant.GSON.fromJson(result, Variant.class).build(identifier1);

                            if (variant.getItems().isEmpty() && variant.getItemTags().isEmpty()) {
                                ArchitectsAssembly.LOGGER.warn("Found empty variant definition: '{}'", identifier);
                                continue;
                            }

                            if (Variant.VARIANTS.containsKey(identifier1.getPath())) {
                                variant.copyTo(Variant.VARIANTS.get(identifier1.getPath()));
                            } else Variant.VARIANTS.put(identifier1.getPath(), variant);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }
}
