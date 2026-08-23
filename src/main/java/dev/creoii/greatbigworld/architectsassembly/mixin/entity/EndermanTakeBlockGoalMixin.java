package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import net.minecraft.world.entity.monster.EnderMan;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.EndermanTakeBlockGoal.class)
public class EndermanTakeBlockGoalMixin {
    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void gbw$disableEndermanPickupBlock(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
