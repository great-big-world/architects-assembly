package dev.creoii.greatbigworld.architectsassembly.mixin.entity.player;

import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import dev.creoii.greatbigworld.architectsassembly.util.FreePlacer;
import dev.creoii.greatbigworld.architectsassembly.util.SlabPlacer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements SlabPlacer, FreePlacer {
    @Unique private SlabItem.SlabPlacement gbw$slabPlacementType = SlabItem.SlabPlacement.NORMAL;
    @Unique private boolean gbw$freePlacing = false;

    @Override
    public SlabItem.SlabPlacement gbw$getSlabPlacementState() {
        return gbw$slabPlacementType;
    }

    @Override
    public void gbw$setSlabPlacementState(SlabItem.SlabPlacement slabPlacementState) {
        this.gbw$slabPlacementType = slabPlacementState;
    }

    @Override
    public boolean gbw$hasFreePlacement() {
        return gbw$freePlacing;
    }

    @Override
    public void gbw$setFreePlacement(boolean freePlacement) {
        gbw$freePlacing = freePlacement;
    }
}
