package dev.creoii.greatbigworld.architectsassembly.client.screen;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.menu.KilnMenu;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblySearchRecipeBookCategories;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

@Environment(EnvType.CLIENT)
public class KilnScreen extends AbstractFurnaceScreen<KilnMenu> {
    private static final Identifier LIT_PROGRESS_SPRITE = Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "container/kiln/lit_progress");
    private static final Identifier BURN_PROGRESS_SPRITE = Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "container/kiln/burn_progress");
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "textures/gui/container/kiln.png");
    private static final Component FILTER_NAME = Component.translatable("gui.recipebook.toggleRecipes.fireable");
    private static final List<RecipeBookComponent.TabInfo> TABS;

    public KilnScreen(KilnMenu kilnMenu, Inventory inventory, Component component) {
        super(kilnMenu, inventory, component, FILTER_NAME, TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE, TABS);
    }

    static {
        TABS = List.of(new RecipeBookComponent.TabInfo(ArchitectsAssemblySearchRecipeBookCategories.KILN));
    }
}
