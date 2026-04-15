package dev.creoii.greatbigworld.architectsassembly.block.entity;

import dev.creoii.greatbigworld.architectsassembly.menu.KilnMenu;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlockEntities;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    private static final Component DEFAULT_NAME = Component.translatable("container.kiln");

    public KilnBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ArchitectsAssemblyBlockEntities.KILN, blockPos, blockState, ArchitectsAssemblyRecipes.FIRING);
    }

    @Override
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }

    protected int getBurnDuration(FuelValues fuelValues, ItemStack itemStack) {
        return super.getBurnDuration(fuelValues, itemStack) / 2;
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new KilnMenu(i, inventory, this, dataAccess);
    }
}
