package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ArchitectsAssemblyStats {
    public static Identifier INTERACT_WITH_SAWMILL = Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "interact_with_sawmill");

    public static void register() {
        Registry.register(BuiltInRegistries.CUSTOM_STAT, INTERACT_WITH_SAWMILL, INTERACT_WITH_SAWMILL);
        Stats.CUSTOM.get(INTERACT_WITH_SAWMILL, StatFormatter.DEFAULT);
    }
}
