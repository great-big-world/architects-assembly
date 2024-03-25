package dev.creoii.greatbigworld.architectsassembly.variant;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Iterator;
import java.util.Set;

public interface VariantItem {
    Set<Variant> gbw$getVariants();

    default void gbw$addVariant(Variant variant) {
        gbw$getVariants().add(variant);
    }

    static Text getVariantTooltip(VariantItem variantItem) {
        MutableText text = Text.empty();
        Iterator<Variant> iterator = variantItem.gbw$getVariants().iterator();
        while (iterator.hasNext()) {
            Variant variant = iterator.next();
            text.append(Text.translatable(variant.getTranslationKey()).formatted(Formatting.GRAY));
            if (iterator.hasNext())
                text.append(Text.literal(", ").formatted(Formatting.GRAY));
        }
        return text;
    }
}
