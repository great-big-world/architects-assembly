package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.client.screen.SawmillScreen;
import dev.creoii.greatbigworld.architectsassembly.client.screen.SawmillScreenHandler;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ArchitectsAssemblyScreens {
    public static final MenuType<SawmillScreenHandler> SAWMILL = new MenuType<>(SawmillScreenHandler::new, FeatureFlags.VANILLA_SET);

    public static void register() {
        Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmill"), SAWMILL);
    }

    public static void registerClient() {
        MenuScreens.register(SAWMILL, SawmillScreen::new);
    }
}
