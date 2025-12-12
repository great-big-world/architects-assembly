package dev.creoii.greatbigworld.architectsassembly.mixin.world;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.client.ScreenShakeManager;
import dev.creoii.greatbigworld.util.network.ScreenShakeS2C;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerExplosion.class)
public abstract class ExplosionImplMixin {
    /*@Shadow
    public abstract Vec3 center();

    @Inject(method = "hurtEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;push(Lnet/minecraft/world/phys/Vec3;)V"))
    private void gbw$explosionScreenShake(CallbackInfo ci, @Local Entity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new ScreenShakeS2C((float) Math.exp(-.08f * serverPlayer.position().distanceTo(center())), 80, ScreenShakeManager.Easing.OUT));
        }
    }*/
}
