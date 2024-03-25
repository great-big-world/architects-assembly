package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArmorStandEntity.class)
public abstract class ArmorStandEntityMixin {
    @Shadow public abstract void setShowArms(boolean showArms);

    @Inject(method = "interactAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ArmorStandEntity;isSlotDisabled(Lnet/minecraft/entity/EquipmentSlot;)Z", ordinal = 1))
    private void gbw$displayArmsOnEquip(PlayerEntity player, Vec3d hitPos, Hand hand, CallbackInfoReturnable<ActionResult> cir, @Local(ordinal = 0) EquipmentSlot equipmentSlot) {
        if (equipmentSlot.getType() == EquipmentSlot.Type.HAND)
            setShowArms(true);
    }
}
