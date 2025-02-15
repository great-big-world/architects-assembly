package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.client.ArchitectsAssemblyClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow @Final private MinecraftClient client;

    @Unique
    private static int cycle(int index, int amount) {
        int cycled = index + amount;

        // -9 returns 27
        if (cycled < 0) {
            return PlayerInventory.MAIN_SIZE + cycled;
        }

        // 40 returns 4
        if (cycled > PlayerInventory.MAIN_SIZE) {
            return -PlayerInventory.MAIN_SIZE + cycled;
        }

        return cycled;
    }

    @Inject(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;setSelectedSlot(I)V"), cancellable = true)
    private void gbw$scrollHotbar(long window, double horizontal, double vertical, CallbackInfo ci, @Local int i) {
        if (ArchitectsAssemblyClient.shouldCycleHotbar()) {
            if (client.player != null && client.interactionManager != null && client.player.getInventory() != null) {
                double scrollDelta = Math.signum(i);
                for (int j = 0; j < 9; ++j) {
                    client.interactionManager.clickSlot(client.player.playerScreenHandler.syncId, cycle(j, scrollDelta > 0d ? -27 : 27), j, SlotActionType.SWAP, client.player);
                    client.interactionManager.clickSlot(client.player.playerScreenHandler.syncId, cycle(j, scrollDelta > 0d ? -18 : 18), j, SlotActionType.SWAP, client.player);
                    client.interactionManager.clickSlot(client.player.playerScreenHandler.syncId, cycle(j, scrollDelta > 0d ? -9 : 9), j, SlotActionType.SWAP, client.player);
                }
            }
            ci.cancel();
        }
    }
}
