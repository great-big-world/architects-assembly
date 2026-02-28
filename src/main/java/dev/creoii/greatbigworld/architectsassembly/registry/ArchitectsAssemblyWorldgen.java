package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.world.feature.MossifyVegetationPatchFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;

public final class ArchitectsAssemblyWorldgen {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSIFY_PATCH_BONEMEAL = ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossify_patch_bonemeal"));

    public static void register() {
        Registry.register(BuiltInRegistries.FEATURE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossify_vegetation_patch"), new MossifyVegetationPatchFeature(VegetationPatchConfiguration.CODEC));
    }
}
