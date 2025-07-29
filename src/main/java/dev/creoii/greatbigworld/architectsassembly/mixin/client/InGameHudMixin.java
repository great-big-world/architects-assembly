package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import dev.creoii.greatbigworld.architectsassembly.variant.VariantItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow private ItemStack currentStack;
    @Shadow @Final private MinecraftClient client;

    @Redirect(method = "renderHeldItemTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithBackground(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIII)I"))
    private int gbw$renderHeldItemVariants(DrawContext instance, TextRenderer textRenderer, Text text, int x, int y, int width, int color) {
        if (client.player != null && client.player.getArmor() > 0) {
            y -= 10;
        }
        if (currentStack.getItem() instanceof VariantItem variantItem && !variantItem.gbw$getVariants().isEmpty()) {
            instance.drawCenteredTextWithShadow(textRenderer, VariantItem.getVariantTooltip(variantItem), x + (textRenderer.getWidth(text.getString()) / 2), y + 10, color);
        } else if (currentStack.getItem() instanceof SpawnEggItem spawnEggItem && instance.client.world != null) {
            MutableText mutableText = MutableText.of(spawnEggItem.getEntityType(instance.client.world.getRegistryManager(), currentStack).getName().getContent()).formatted(Formatting.GRAY);
            instance.drawCenteredTextWithShadow(textRenderer, mutableText, x + (textRenderer.getWidth(text.getString()) / 2), y + 10, color);
        } else if (currentStack.isIn(ItemTags.DECORATED_POT_SHERDS)) {
            Identifier id = Registries.ITEM.getId(currentStack.getItem());
            instance.drawCenteredTextWithShadow(textRenderer, Text.translatable("variant.item.sherd." + id.getPath().replace("_pottery_sherd", "")).formatted(Formatting.GRAY), x + (textRenderer.getWidth(text.getString()) / 2), y + 10, color);
        }

        return instance.drawTextWithBackground(textRenderer, text, x, y, width, color);
    }
}
