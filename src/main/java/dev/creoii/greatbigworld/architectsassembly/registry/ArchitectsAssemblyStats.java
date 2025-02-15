package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

public class ArchitectsAssemblyStats {
    public static Identifier INTERACT_WITH_SAWMILL = Identifier.of(GreatBigWorld.NAMESPACE, "interact_with_sawmill");

    public static void register() {
        Registry.register(Registries.CUSTOM_STAT, INTERACT_WITH_SAWMILL, INTERACT_WITH_SAWMILL);
        Stats.CUSTOM.getOrCreateStat(INTERACT_WITH_SAWMILL, StatFormatter.DEFAULT);
    }
}
