package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import dev.creoii.greatbigworld.architectsassembly.util.SlabPlacer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements SlabPlacer {
    @Unique private static final TrackedData<String> SLAB_PLACEMENT_TYPE = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.STRING);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public SlabItem.SlabPlacement gbw$getSlabPlacementState() {
        return SlabItem.SlabPlacement.valueOf(dataTracker.get(SLAB_PLACEMENT_TYPE));
    }

    @Override
    public void gbw$setSlabPlacementState(SlabItem.SlabPlacement slabPlacementState) {
        dataTracker.set(SLAB_PLACEMENT_TYPE, slabPlacementState.name());
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void gbw$initCustomPlacerDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(SLAB_PLACEMENT_TYPE, "VERTICAL");
    }
}
