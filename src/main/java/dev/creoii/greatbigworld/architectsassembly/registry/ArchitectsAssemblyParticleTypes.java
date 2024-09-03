package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ArchitectsAssemblyParticleTypes {
    public static final SimpleParticleType SMALL_SOUL_FIRE_FLAME = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(ArchitectsAssembly.NAMESPACE, "small_soul_fire_flame"), SMALL_SOUL_FIRE_FLAME);
    }

    public static void registerClient() {
        ParticleFactoryRegistry.getInstance().register(SMALL_SOUL_FIRE_FLAME, FlameParticle.SmallFactory::new);
    }
}
