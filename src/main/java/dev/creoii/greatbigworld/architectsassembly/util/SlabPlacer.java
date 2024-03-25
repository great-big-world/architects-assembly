package dev.creoii.greatbigworld.architectsassembly.util;

import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;

public interface SlabPlacer {
    SlabItem.SlabPlacement gbw$getSlabPlacementState();

    void gbw$setSlabPlacementState(SlabItem.SlabPlacement slabPlacementState);
}
