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
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
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

        Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossify_vegetation_patch"), new MossifyVegetationPatchFeature(VegetationPatchConfiguration.CODEC));

        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.igniteOdds.forEach((block, integer) -> {
            Block verticalSlab = VerticalSlabBlock.fromSlab(block);
            if (verticalSlab != null) {
                fireBlock.igniteOdds.put(verticalSlab, integer);
            }
        });
        fireBlock.burnOdds.forEach((block, integer) -> {
            Block verticalSlab = VerticalSlabBlock.fromSlab(block);
            if (verticalSlab != null) {
                fireBlock.burnOdds.put(verticalSlab, integer);
            }
        });

        DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
            modifyContext.modify(Items.IRON_AXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_PICKAXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_SHOVEL, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_HOE, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_SWORD, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_HELMET, builder -> builder.set(DataComponents.MAX_DAMAGE, 265));
            modifyContext.modify(Items.IRON_CHESTPLATE, builder -> builder.set(DataComponents.MAX_DAMAGE, 340));
            modifyContext.modify(Items.IRON_LEGGINGS, builder -> builder.set(DataComponents.MAX_DAMAGE, 325));
            modifyContext.modify(Items.IRON_BOOTS, builder -> builder.set(DataComponents.MAX_DAMAGE, 295));

            modifyContext.modify(Items.COPPER_AXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_PICKAXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_SHOVEL, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_HOE, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_SWORD, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_HELMET, builder -> builder.set(DataComponents.MAX_DAMAGE, 130)); // drop
            modifyContext.modify(Items.COPPER_CHESTPLATE, builder -> builder.set(DataComponents.MAX_DAMAGE, 170)); // drop
            modifyContext.modify(Items.COPPER_LEGGINGS, builder -> builder.set(DataComponents.MAX_DAMAGE, 160)); // drop
            modifyContext.modify(Items.COPPER_BOOTS, builder -> builder.set(DataComponents.MAX_DAMAGE, 145)); // drop
        });

        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "variant");
            }

            @Override
            public void onResourceManagerReload(ResourceManager manager) {
                Variant.VARIANTS.clear();
                Map<Identifier, List<Resource>> resourceMap = manager.listResourceStacks("variants", path -> path.getPath().endsWith(".json"));
                for (Map.Entry<Identifier, List<Resource>> entry : resourceMap.entrySet()) {
                    Identifier identifier = entry.getKey();
                    for (Resource resource : entry.getValue()) {
                        try (InputStream stream = resource.open()) {
                            String result = IOUtils.toString(stream, StandardCharsets.UTF_8);
                            Identifier identifier1 = Identifier.fromNamespaceAndPath(identifier.getNamespace(), identifier.getPath().replace("variants/", "").replace(".json", ""));
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
