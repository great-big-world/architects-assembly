package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyUseActions;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @WrapOperation(method = "renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isUsingItem()Z", ordinal = 1))
    private boolean gbw$renderToolUseAction(AbstractClientPlayerEntity instance, Operation<Boolean> original) {
        return original.call(instance) && instance.getActiveItem().getUseAction() != ArchitectsAssemblyUseActions.TOOL;
    }
}
