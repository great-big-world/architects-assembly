package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import dev.creoii.greatbigworld.architectsassembly.util.SlabPlacer;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements SlabPlacer {
    @Unique private static final EntityDataAccessor<String> SLAB_PLACEMENT_TYPE = SynchedEntityData.defineId(Player.class, EntityDataSerializers.STRING);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public SlabItem.SlabPlacement gbw$getSlabPlacementState() {
        return SlabItem.SlabPlacement.valueOf(entityData.get(SLAB_PLACEMENT_TYPE));
    }

    @Override
    public void gbw$setSlabPlacementState(SlabItem.SlabPlacement slabPlacementState) {
        entityData.set(SLAB_PLACEMENT_TYPE, slabPlacementState.name());
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void gbw$initCustomPlacerDataTracker(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(SLAB_PLACEMENT_TYPE, "VERTICAL");
    }
}
