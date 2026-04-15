package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.client.screen.KilnScreen;
import dev.creoii.greatbigworld.architectsassembly.menu.KilnMenu;
import dev.creoii.greatbigworld.architectsassembly.menu.SawmillMenu;
import dev.creoii.greatbigworld.architectsassembly.client.screen.SawmillScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipePropertySet;

public class ArchitectsAssemblyMenus {
    public static final MenuType<SawmillMenu> SAWMILL = new MenuType<>(SawmillMenu::new, FeatureFlags.VANILLA_SET);
    public static final MenuType<KilnMenu> KILN = new MenuType<>(KilnMenu::new, FeatureFlags.VANILLA_SET);
    public static final ResourceKey<RecipePropertySet> KILN_INPUT = ResourceKey.create(RecipePropertySet.TYPE_KEY, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "kiln_input"));

    public static void register() {
        Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmill"), SAWMILL);
        Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "kiln"), KILN);
    }

    public static void registerClient() {
        MenuScreens.register(SAWMILL, SawmillScreen::new);
        MenuScreens.register(KILN, KilnScreen::new);
    }
}
