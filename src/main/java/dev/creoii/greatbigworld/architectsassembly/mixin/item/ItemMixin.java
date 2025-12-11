package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import dev.creoii.greatbigworld.architectsassembly.variant.VariantItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.world.item.Item;

@Mixin(Item.class)
public class ItemMixin implements VariantItem {
    @Unique
    private final Set<Variant> gbw$variants = new HashSet<>();

    @Override
    public Set<Variant> gbw$getVariants() {
        return gbw$variants;
    }
}
