package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import dev.creoii.greatbigworld.client.ScreenShakeManager;
import dev.creoii.greatbigworld.util.network.ScreenShakeS2C;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public abstract class LightningEntityMixin extends Entity {
    public LightningEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LightningBolt;gameEvent(Lnet/minecraft/core/Holder;)V"))
    private void gbw$lightningScreenShake(CallbackInfo ci) {
        PlayerLookup.tracking((ServerLevel) level(), blockPosition()).forEach(serverPlayer -> {
            float t = Math.max(0f, 2.5f - ((float) serverPlayer.position().distanceTo(position()) / 64f));
            ServerPlayNetworking.send(serverPlayer, new ScreenShakeS2C(t, 200, ScreenShakeManager.Easing.OUT));
        });
    }
}
