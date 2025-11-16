package dev.creoii.greatbigworld.architectsassembly.client.screen;

import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyScreens;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblySoundEvents;
import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.display.CuttingRecipeDisplay;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

public class SawmillScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    private final Property selectedRecipe = Property.create();
    private final World world;
    private CuttingRecipeDisplay.Grouping<SawmillingRecipe> availableRecipes;
    private ItemStack inputStack = ItemStack.EMPTY;
    long lastTakeTime;
    final Slot inputSlot;
    final Slot outputSlot;
    Runnable contentsChangedListener = () -> {};
    public final Inventory input = new SimpleInventory(1) {
        @Override
        public void markDirty() {
            super.markDirty();
            SawmillScreenHandler.this.onContentChanged(this);
            SawmillScreenHandler.this.contentsChangedListener.run();
        }
    };
    final CraftingResultInventory output = new CraftingResultInventory();

    public SawmillScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public SawmillScreenHandler(int syncId, PlayerInventory playerInventory, final ScreenHandlerContext context) {
        super(ArchitectsAssemblyScreens.SAWMILL, syncId);
        int i;
        this.context = context;
        this.world = playerInventory.player.getEntityWorld();
        this.inputSlot = this.addSlot(new Slot(this.input, 0, 20, 33));
        this.outputSlot = this.addSlot(new Slot(this.output, 1, 143, 33){

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                stack.onCraftByPlayer(player, stack.getCount());
                SawmillScreenHandler.this.output.unlockLastRecipe(player, this.getInputStacks());
                ItemStack itemStack = SawmillScreenHandler.this.inputSlot.takeStack(1);
                if (!itemStack.isEmpty()) {
                    SawmillScreenHandler.this.populateResult(SawmillScreenHandler.this.selectedRecipe.get());
                }
                context.run((world, pos) -> {
                    long l = world.getTime();
                    if (SawmillScreenHandler.this.lastTakeTime != l) {
                        world.playSound(null, pos, ArchitectsAssemblySoundEvents.UI_SAWMILL_TAKE_RESULT, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        SawmillScreenHandler.this.lastTakeTime = l;
                    }
                });
                super.onTakeItem(player, stack);
            }

            private List<ItemStack> getInputStacks() {
                return List.of(SawmillScreenHandler.this.inputSlot.getStack());
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
        this.addProperty(this.selectedRecipe);
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public CuttingRecipeDisplay.Grouping<SawmillingRecipe> getAvailableRecipes() {
        return this.availableRecipes;
    }

    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    public boolean canCraft() {
        return this.inputSlot.hasStack() && !this.availableRecipes.isEmpty();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return SawmillScreenHandler.canUse(this.context, player, ArchitectsAssemblyBlocks.SAWMILL);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
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
    public void onContentChanged(Inventory inventory) {
        ItemStack itemStack = this.inputSlot.getStack();
        if (!itemStack.isOf(this.inputStack.getItem())) {
            this.inputStack = itemStack.copy();
            this.updateInput(inventory, itemStack);
        }
    }

    private void updateInput(Inventory input, ItemStack stack) {
        this.selectedRecipe.set(-1);
        this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.availableRecipes = ((SawmillingRecipeManager) this.world.getRecipeManager()).gbw$getSawmillingRecipes().filter(stack);
        } else this.availableRecipes = CuttingRecipeDisplay.Grouping.empty();
    }

    void populateResult(int selectedId) {
        Optional<RecipeEntry<SawmillingRecipe>> optional;
        if (!this.availableRecipes.isEmpty() && this.isInBounds(selectedId)) {
            CuttingRecipeDisplay.GroupEntry<SawmillingRecipe> groupEntry = this.availableRecipes.entries().get(selectedId);
            optional = groupEntry.recipe().recipe();
        } else {
            optional = Optional.empty();
        }

        optional.ifPresentOrElse((recipe) -> {
            this.output.setLastRecipe(recipe);
            this.outputSlot.setStackNoCallbacks((recipe.value()).craft(new SingleStackRecipeInput(this.input.getStack(0)), this.world.getRegistryManager()));
        }, () -> {
            this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
            this.output.setLastRecipe(null);
        });
        sendContentUpdates();
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return ArchitectsAssemblyScreens.SAWMILL;
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.output && super.canInsertIntoSlot(stack, slot);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            Item item = itemStack2.getItem();
            itemStack = itemStack2.copy();
            if (slot == 1) {
                item.onCraftByPlayer(itemStack2, player);
                if (!this.insertItem(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (slot == 0) {
                if (!this.insertItem(itemStack2, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (((SawmillingRecipeManager) this.world.getRecipeManager()).gbw$getSawmillingRecipes().contains(itemStack2)) {
                if (!this.insertItem(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 2 && slot < 29) {
                if (!this.insertItem(itemStack2, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 29 && slot < 38 && !this.insertItem(itemStack2, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            }

            slot2.markDirty();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTakeItem(player, itemStack2);
            if (slot == 1) {
                player.dropItem(itemStack2, false);
            }
            this.sendContentUpdates();
        }
        return itemStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.output.removeStack(1);
        this.context.run((world, pos) -> this.dropInventory(player, this.input));
    }

    private static SingleStackRecipeInput createRecipeInput(Inventory inventory) {
        return new SingleStackRecipeInput(inventory.getStack(0));
    }
}
