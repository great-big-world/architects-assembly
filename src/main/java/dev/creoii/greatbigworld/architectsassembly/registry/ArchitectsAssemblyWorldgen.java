package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public final class ArchitectsAssemblyWorldgen {
    public static final RegistryKey<ConfiguredFeature<?, ?>> MOSSIFY_PATCH_BONEMEAL = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(GreatBigWorld.NAMESPACE, "mossify_patch_bonemeal"));
}
