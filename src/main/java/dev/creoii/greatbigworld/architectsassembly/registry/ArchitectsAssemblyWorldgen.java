package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public final class ArchitectsAssemblyWorldgen {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSIFY_PATCH_BONEMEAL = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossify_patch_bonemeal"));
}
