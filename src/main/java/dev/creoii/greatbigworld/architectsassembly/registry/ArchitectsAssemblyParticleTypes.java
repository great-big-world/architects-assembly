package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public final class ArchitectsAssemblyParticleTypes {
    public static final SimpleParticleType SMALL_SOUL_FIRE_FLAME = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "small_soul_fire_flame"), SMALL_SOUL_FIRE_FLAME);
    }

    public static void registerClient() {
        ParticleFactoryRegistry.getInstance().register(SMALL_SOUL_FIRE_FLAME, FlameParticle.SmallFlameProvider::new);
    }
}
