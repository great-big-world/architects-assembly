package dev.creoii.greatbigworld.architectsassembly.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.item.ItemFrameItem;
import net.minecraft.util.DyeColor;

public class DyedItemFrameItem extends ItemFrameItem {
    private final DyeColor color;

    public DyedItemFrameItem(EntityType<? extends AbstractDecorationEntity> entityType, DyeColor color, Settings settings) {
        super(entityType, settings);
        this.color = color;
    }

    public DyeColor getColor() {
        return color;
    }
}
