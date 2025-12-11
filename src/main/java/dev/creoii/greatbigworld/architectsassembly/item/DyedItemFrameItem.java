package dev.creoii.greatbigworld.architectsassembly.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemFrameItem;

public class DyedItemFrameItem extends ItemFrameItem {
    private final DyeColor color;

    public DyedItemFrameItem(EntityType<? extends HangingEntity> entityType, DyeColor color, Properties settings) {
        super(entityType, settings);
        this.color = color;
    }

    public DyeColor getColor() {
        return color;
    }
}
