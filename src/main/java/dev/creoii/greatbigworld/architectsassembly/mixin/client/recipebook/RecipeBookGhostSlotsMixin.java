package dev.creoii.greatbigworld.architectsassembly.mixin.client.recipebook;

import dev.creoii.greatbigworld.architectsassembly.util.ItemRenderHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.recipebook.RecipeBookGhostSlots;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipeBookGhostSlots.class)
public class RecipeBookGhostSlotsMixin {
    /*
    Ghost Slots only show for recipes that aren't blacked out in the first place

    TODO:
    Fix Ghost Slots for unknown recipes
    Only blackout Ghost Slots for unknown recipes

    @Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItemWithoutEntity(Lnet/minecraft/item/ItemStack;II)V"))
    private void gbw$blackoutGhostItems(DrawContext instance, ItemStack stack, int x, int y) {
        ItemRenderHelper.drawItemSilhouette(instance, null, instance.client.world, stack, x, y, 0, 0);
    }
    */
}
