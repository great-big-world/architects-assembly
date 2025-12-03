package dev.creoii.greatbigworld.architectsassembly.mixin.world;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.client.ScreenShakeManager;
import dev.creoii.greatbigworld.util.network.ScreenShakeS2C;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.ExplosionImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExplosionImpl.class)
public abstract class ExplosionImplMixin {
    @Shadow
    public abstract Vec3d getPosition();

    @Inject(method = "damageEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
    private void gbw$explosionScreenShake(CallbackInfo ci, @Local Entity entity) {
        if (entity instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new ScreenShakeS2C((float) Math.exp(-.08f * serverPlayer.getEntityPos().distanceTo(getPosition())), 80, ScreenShakeManager.Easing.OUT));
        }
    }
}
