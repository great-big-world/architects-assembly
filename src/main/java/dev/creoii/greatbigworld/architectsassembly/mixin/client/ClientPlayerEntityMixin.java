package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyUseActions;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocalPlayer.class)
public class ClientPlayerEntityMixin {
    @WrapOperation(method = "modifyInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"))
    private boolean gbw$dontSlowDownUsingTool(LocalPlayer instance, Operation<Boolean> original) {
        return original.call(instance) && instance.getUseItem().getUseAnimation() != ArchitectsAssemblyUseActions.TOOL;
    }

    @WrapOperation(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSlowDueToUsingItem()Z"))
    private boolean gbw$dontStopDoubleTapSprintUsingTool(LocalPlayer instance, Operation<Boolean> original) {
        return original.call(instance) && instance.getUseItem().getUseAnimation() != ArchitectsAssemblyUseActions.TOOL;
    }
}
