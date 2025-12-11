package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStand.class)
public abstract class ArmorStandEntityMixin {
    @Shadow public abstract void setShowArms(boolean showArms);

    @Inject(method = "interactAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/ArmorStand;isDisabled(Lnet/minecraft/world/entity/EquipmentSlot;)Z", ordinal = 1))
    private void gbw$displayArmsOnEquip(Player player, Vec3 hitPos, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir, @Local(ordinal = 0) EquipmentSlot equipmentSlot) {
        if (equipmentSlot.getType() == EquipmentSlot.Type.HAND)
            setShowArms(true);
    }
}
