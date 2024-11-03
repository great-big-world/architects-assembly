package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import dev.creoii.greatbigworld.architectsassembly.client.screen.SawmillScreen;
import dev.creoii.greatbigworld.architectsassembly.client.screen.SawmillScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ArchitectsAssemblyScreens {
    public static final ScreenHandlerType<SawmillScreenHandler> SAWMILL = new ScreenHandlerType<>(SawmillScreenHandler::new, FeatureFlags.VANILLA_FEATURES);

    public static void register() {
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(GreatBigWorld.NAMESPACE, "sawmill"), SAWMILL);
    }

    public static void registerClient() {
        HandledScreens.register(SAWMILL, SawmillScreen::new);
    }
}
