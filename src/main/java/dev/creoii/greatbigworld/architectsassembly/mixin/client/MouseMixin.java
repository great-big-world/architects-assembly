package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.client.ArchitectsAssemblyClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;scrollInHotbar(D)V"), cancellable = true)
    private void gbw$scrollHotbar(long window, double horizontal, double vertical, CallbackInfo ci, @Local(ordinal = 2) int k) {
        if (ArchitectsAssemblyClient.shouldCycleHotbar()) {
            for (int i = 0; i < 9; ++i) {
                if (client.player != null && client.interactionManager != null && client.player.getInventory() != null) {
                    client.interactionManager.clickSlot(client.player.playerScreenHandler.syncId, Math.signum(k) < 0d ? i + 9 : 27, i, SlotActionType.SWAP, client.player);
                    client.interactionManager.clickSlot(client.player.playerScreenHandler.syncId, i + 18, i, SlotActionType.SWAP, client.player);
                    client.interactionManager.clickSlot(client.player.playerScreenHandler.syncId, Math.signum(k) < 0d ? i + 27 : 9, i, SlotActionType.SWAP, client.player);
                }
            }
            ci.cancel();
        }
    }
}
