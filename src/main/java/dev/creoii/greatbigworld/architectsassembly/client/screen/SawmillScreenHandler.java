package dev.creoii.greatbigworld.architectsassembly.client.screen;

import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyScreens;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblySoundEvents;
import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SelectableRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import java.util.List;
import java.util.Optional;

public class SawmillScreenHandler extends AbstractContainerMenu {
    private final ContainerLevelAccess context;
    private final DataSlot selectedRecipe = DataSlot.standalone();
    private final Level world;
    private SelectableRecipe.SingleInputSet<SawmillingRecipe> availableRecipes;
    private ItemStack inputStack = ItemStack.EMPTY;
    long lastTakeTime;
    final Slot inputSlot;
    final Slot outputSlot;
    Runnable contentsChangedListener = () -> {};
    public final Container input = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            SawmillScreenHandler.this.slotsChanged(this);
            SawmillScreenHandler.this.contentsChangedListener.run();
        }
    };
    final ResultContainer output = new ResultContainer();

    public SawmillScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, ContainerLevelAccess.NULL);
    }

    public SawmillScreenHandler(int syncId, Inventory playerInventory, final ContainerLevelAccess context) {
        super(ArchitectsAssemblyScreens.SAWMILL, syncId);
        int i;
        this.context = context;
        this.world = playerInventory.player.level();
        this.inputSlot = this.addSlot(new Slot(this.input, 0, 20, 33));
        this.outputSlot = this.addSlot(new Slot(this.output, 1, 143, 33){

            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                stack.onCraftedBy(player, stack.getCount());
                SawmillScreenHandler.this.output.awardUsedRecipes(player, this.getInputStacks());
                ItemStack itemStack = SawmillScreenHandler.this.inputSlot.remove(1);
                if (!itemStack.isEmpty()) {
                    SawmillScreenHandler.this.populateResult(SawmillScreenHandler.this.selectedRecipe.get());
                }
                context.execute((world, pos) -> {
                    long l = world.getGameTime();
                    if (SawmillScreenHandler.this.lastTakeTime != l) {
                        world.playSound(null, pos, ArchitectsAssemblySoundEvents.UI_SAWMILL_TAKE_RESULT, SoundSource.BLOCKS, 1.0f, 1.0f);
                        SawmillScreenHandler.this.lastTakeTime = l;
                    }
                });
                super.onTake(player, stack);
            }

            private List<ItemStack> getInputStacks() {
                return List.of(SawmillScreenHandler.this.inputSlot.getItem());
            }
        });
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        this.addDataSlot(this.selectedRecipe);
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public SelectableRecipe.SingleInputSet<SawmillingRecipe> getAvailableRecipes() {
        return this.availableRecipes;
    }

    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    public boolean canCraft() {
        return this.inputSlot.hasItem() && !this.availableRecipes.isEmpty();
    }

    @Override
    public boolean stillValid(Player player) {
        return SawmillScreenHandler.stillValid(this.context, player, ArchitectsAssemblyBlocks.SAWMILL);
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.selectedRecipe.get() == id) {
            return false;
        } else {
            if (this.isInBounds(id)) {
                this.selectedRecipe.set(id);
                this.populateResult(id);
            }

            return true;
        }
    }

    private boolean isInBounds(int id) {
        return id >= 0 && id < this.availableRecipes.size();
    }

    @Override
    public void slotsChanged(Container inventory) {
        ItemStack itemStack = this.inputSlot.getItem();
        if (!itemStack.is(this.inputStack.getItem())) {
            this.inputStack = itemStack.copy();
            this.updateInput(inventory, itemStack);
        }
    }

    private void updateInput(Container input, ItemStack stack) {
        this.selectedRecipe.set(-1);
        this.outputSlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.availableRecipes = ((SawmillingRecipeManager) this.world.recipeAccess()).gbw$getSawmillingRecipes().selectByInput(stack);
        } else this.availableRecipes = SelectableRecipe.SingleInputSet.empty();
    }

    void populateResult(int selectedId) {
        Optional<RecipeHolder<SawmillingRecipe>> optional;
        if (!this.availableRecipes.isEmpty() && this.isInBounds(selectedId)) {
            SelectableRecipe.SingleInputEntry<SawmillingRecipe> groupEntry = this.availableRecipes.entries().get(selectedId);
            optional = groupEntry.recipe().recipe();
        } else {
            optional = Optional.empty();
        }

        optional.ifPresentOrElse((recipe) -> {
            this.output.setRecipeUsed(recipe);
            this.outputSlot.set((recipe.value()).assemble(new SingleRecipeInput(this.input.getItem(0)), this.world.registryAccess()));
        }, () -> {
            this.outputSlot.set(ItemStack.EMPTY);
            this.output.setRecipeUsed(null);
        });
        broadcastChanges();
    }

    @Override
    public MenuType<?> getType() {
        return ArchitectsAssemblyScreens.SAWMILL;
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.output && super.canTakeItemForPickAll(stack, slot);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasItem()) {
            ItemStack itemStack2 = slot2.getItem();
            Item item = itemStack2.getItem();
            itemStack = itemStack2.copy();
            if (slot == 1) {
                item.onCraftedBy(itemStack2, player);
                if (!this.moveItemStackTo(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickCraft(itemStack2, itemStack);
            } else if (slot == 0) {
                if (!this.moveItemStackTo(itemStack2, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (((SawmillingRecipeManager) this.world.recipeAccess()).gbw$getSawmillingRecipes().acceptsInput(itemStack2)) {
                if (!this.moveItemStackTo(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 2 && slot < 29) {
                if (!this.moveItemStackTo(itemStack2, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 29 && slot < 38 && !this.moveItemStackTo(itemStack2, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setByPlayer(ItemStack.EMPTY);
            }

            slot2.setChanged();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTake(player, itemStack2);
            if (slot == 1) {
                player.drop(itemStack2, false);
            }
            this.broadcastChanges();
        }
        return itemStack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.output.removeItemNoUpdate(1);
        this.context.execute((world, pos) -> this.clearContainer(player, this.input));
    }

    private static SingleRecipeInput createRecipeInput(Container inventory) {
        return new SingleRecipeInput(inventory.getItem(0));
    }
}
