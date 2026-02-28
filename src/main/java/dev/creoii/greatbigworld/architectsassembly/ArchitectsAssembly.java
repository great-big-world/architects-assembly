package dev.creoii.greatbigworld.architectsassembly;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.VerticalSlabBlock;
import dev.creoii.greatbigworld.architectsassembly.registry.*;
import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
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
