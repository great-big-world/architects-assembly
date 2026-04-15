package dev.creoii.greatbigworld.architectsassembly.menu;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyMenus;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyRecipes;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyRecipeBookTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;

public class KilnMenu extends AbstractFurnaceMenu {
    public KilnMenu(int i, Inventory inventory) {
        super(ArchitectsAssemblyMenus.KILN, ArchitectsAssemblyRecipes.FIRING, ArchitectsAssemblyMenus.KILN_INPUT, ArchitectsAssemblyRecipeBookTypes.KILN, i, inventory);
    }

    public KilnMenu(int i, Inventory inventory, Container container, ContainerData containerData) {
        super(ArchitectsAssemblyMenus.KILN, ArchitectsAssemblyRecipes.FIRING, ArchitectsAssemblyMenus.KILN_INPUT, ArchitectsAssemblyRecipeBookTypes.KILN, i, inventory, container, containerData);
    }
}
