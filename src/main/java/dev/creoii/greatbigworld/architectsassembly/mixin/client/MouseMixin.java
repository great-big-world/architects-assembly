package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.client.ArchitectsAssemblyClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseMixin {
    @Shadow @Final private Minecraft minecraft;

    @Unique
    private static int cycle(int index, int amount) {
        int cycled = index + amount;

        // -9 returns 27
        if (cycled < 0) {
            return Inventory.INVENTORY_SIZE + cycled;
        }

        // 40 returns 4
        if (cycled > Inventory.INVENTORY_SIZE) {
            return -Inventory.INVENTORY_SIZE + cycled;
        }

        return cycled;
    }

    @Inject(method = "onScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Inventory;setSelectedSlot(I)V"), cancellable = true)
    private void gbw$scrollHotbar(long window, double horizontal, double vertical, CallbackInfo ci, @Local int i) {
        if (ArchitectsAssemblyClient.shouldCycleHotbar()) {
            if (minecraft.player != null && minecraft.gameMode != null && minecraft.player.getInventory() != null) {
                double scrollDelta = Math.signum(i);
                for (int j = 0; j < 9; ++j) {
                    minecraft.gameMode.handleInventoryMouseClick(minecraft.player.inventoryMenu.containerId, cycle(j, scrollDelta > 0d ? -27 : 27), j, ClickType.SWAP, minecraft.player);
                    minecraft.gameMode.handleInventoryMouseClick(minecraft.player.inventoryMenu.containerId, cycle(j, scrollDelta > 0d ? -18 : 18), j, ClickType.SWAP, minecraft.player);
                    minecraft.gameMode.handleInventoryMouseClick(minecraft.player.inventoryMenu.containerId, cycle(j, scrollDelta > 0d ? -9 : 9), j, ClickType.SWAP, minecraft.player);
                }
            }
            ci.cancel();
        }
    }
}
