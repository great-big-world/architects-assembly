package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract int getMaxDamage();
    @Shadow public abstract int getDamageValue();
    @Shadow public abstract boolean isDamaged();

    @Inject(method = "addDetailsToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;addToTooltip(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V", ordinal = 23, shift = At.Shift.AFTER))
    private void gbw$allowAdvanced5(Item.TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, @Nullable Player player, TooltipFlag tooltipFlag, Consumer<Component> consumer, CallbackInfo ci) {
        if (!tooltipFlag.isAdvanced() && isDamaged() && tooltipDisplay.shows(DataComponents.DAMAGE))
            consumer.accept(Component.translatable("item.durability", getMaxDamage() - getDamageValue(), getMaxDamage()).withStyle(ChatFormatting.GRAY));
    }
}
