package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import dev.creoii.greatbigworld.client.ScreenShakeManager;
import dev.creoii.greatbigworld.util.network.ScreenShakeS2C;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public abstract class LightningEntityMixin extends Entity {
    public LightningEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LightningEntity;emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;)V"))
    private void gbw$lightningScreenShake(CallbackInfo ci) {
        PlayerLookup.tracking((ServerWorld) getEntityWorld(), getBlockPos()).forEach(serverPlayer -> {
            float t = Math.max(0f, 2.5f - ((float) serverPlayer.getEntityPos().distanceTo(getEntityPos()) / 64f));
            ServerPlayNetworking.send(serverPlayer, new ScreenShakeS2C(t, 200, ScreenShakeManager.Easing.OUT));
        });
    }
}
