package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ArchitectsAssemblyPaintings {
    public static final PaintingVariant RED_CEILING = new PaintingVariant(48, 32);

    public static void register() {
        Registry.register(Registries.PAINTING_VARIANT, new Identifier(GreatBigWorld.NAMESPACE, "red_ceiling"), RED_CEILING);
    }
}
