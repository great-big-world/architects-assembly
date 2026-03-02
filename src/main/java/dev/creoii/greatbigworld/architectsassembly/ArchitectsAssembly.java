package dev.creoii.greatbigworld.architectsassembly;

import dev.creoii.delaytoolusage.UsageRegistry;
import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.VerticalSlabBlock;
import dev.creoii.greatbigworld.architectsassembly.registry.*;
import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import dev.creoii.greatbigworld.architectsassembly.variant.VariantItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
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
        ArchitectsAssemblyWorldgen.register();
        ArchitectsAssemblyEvents.register();

        BuiltInRegistries.ITEM.forEach(item -> {
            if (item instanceof VariantItem variantItem) {
                for (Variant variant : Variant.VARIANTS.values()) {
                    if (variant.getItems().contains(variantItem) || variant.isStackInTags(item.getDefaultInstance()))
                        variantItem.gbw$addVariant(variant);
                }
            }
        });

        UsageRegistry.registerPickaxe(Blocks.BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_BRICKS);
        UsageRegistry.registerPickaxe(Blocks.MUD_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_MUD_BRICKS);
        UsageRegistry.registerPickaxe(Blocks.QUARTZ_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_QUARTZ_BRICKS);
        UsageRegistry.registerPickaxe(Blocks.RED_NETHER_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_RED_NETHER_BRICKS);
        UsageRegistry.registerPickaxe(Blocks.END_STONE_BRICKS, () -> ArchitectsAssemblyBlocks.CRACKED_END_STONE_BRICKS);
        UsageRegistry.registerPickaxe(Blocks.GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GLASS);
        UsageRegistry.registerPickaxe(Blocks.BROWN_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BROWN_GLASS);
        UsageRegistry.registerPickaxe(Blocks.RED_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_RED_GLASS);
        UsageRegistry.registerPickaxe(Blocks.ORANGE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_ORANGE_GLASS);
        UsageRegistry.registerPickaxe(Blocks.YELLOW_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_YELLOW_GLASS);
        UsageRegistry.registerPickaxe(Blocks.LIME_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIME_GLASS);
        UsageRegistry.registerPickaxe(Blocks.GREEN_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GREEN_GLASS);
        UsageRegistry.registerPickaxe(Blocks.CYAN_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_CYAN_GLASS);
        UsageRegistry.registerPickaxe(Blocks.BLUE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLUE_GLASS);
        UsageRegistry.registerPickaxe(Blocks.LIGHT_BLUE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_BLUE_GLASS);
        UsageRegistry.registerPickaxe(Blocks.PINK_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PINK_GLASS);
        UsageRegistry.registerPickaxe(Blocks.MAGENTA_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_MAGENTA_GLASS);
        UsageRegistry.registerPickaxe(Blocks.PURPLE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PURPLE_GLASS);
        UsageRegistry.registerPickaxe(Blocks.BLACK_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLACK_GLASS);
        UsageRegistry.registerPickaxe(Blocks.GRAY_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GRAY_GLASS);
        UsageRegistry.registerPickaxe(Blocks.LIGHT_GRAY_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_GRAY_GLASS);
        UsageRegistry.registerPickaxe(Blocks.WHITE_STAINED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_WHITE_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.BROWN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BROWN_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.RED_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_RED_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.ORANGE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_ORANGE_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.YELLOW_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_YELLOW_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.LIME_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIME_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.GREEN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GREEN_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.CYAN_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_CYAN_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.BLUE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLUE_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.LIGHT_BLUE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_BLUE_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.PINK_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PINK_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.MAGENTA_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_MAGENTA_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.PURPLE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_PURPLE_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.BLACK_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_BLACK_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.GRAY_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_GRAY_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.LIGHT_GRAY_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_LIGHT_GRAY_GLASS);
        UsageRegistry.registerPickaxe(ArchitectsAssemblyBlocks.WHITE_GLASS, () -> ArchitectsAssemblyBlocks.CRACKED_WHITE_GLASS);

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
