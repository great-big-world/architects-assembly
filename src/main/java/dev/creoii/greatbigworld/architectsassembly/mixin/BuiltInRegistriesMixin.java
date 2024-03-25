package dev.creoii.greatbigworld.architectsassembly.mixin;

import net.minecraft.registry.BuiltinRegistries;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BuiltinRegistries.class)
public class BuiltInRegistriesMixin {
    /*@Shadow @Final private static RegistryBuilder REGISTRY_BUILDER;

    static {
        REGISTRY_BUILDER.addRegistry(Variants.VARIANTS, Variants::bootstrap);
    }*/
}
