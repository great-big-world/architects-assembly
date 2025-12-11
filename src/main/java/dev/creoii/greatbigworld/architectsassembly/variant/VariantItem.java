package dev.creoii.greatbigworld.architectsassembly.variant;

import java.util.Iterator;
import java.util.Set;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public interface VariantItem {
    Set<Variant> gbw$getVariants();

    default void gbw$addVariant(Variant variant) {
        gbw$getVariants().add(variant);
    }

    static Component getVariantTooltip(VariantItem variantItem) {
        MutableComponent text = Component.empty();
        Iterator<Variant> iterator = variantItem.gbw$getVariants().iterator();
        while (iterator.hasNext()) {
            Variant variant = iterator.next();
            text.append(Component.translatable(variant.getTranslationKey()).withStyle(ChatFormatting.GRAY));
            if (iterator.hasNext())
                text.append(Component.literal(", ").withStyle(ChatFormatting.GRAY));
        }
        return text;
    }
}
