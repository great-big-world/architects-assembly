package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract int getMaxDamage();
    @Shadow public abstract int getDamage();

    @ModifyExpressionValue(method = "appendTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/tooltip/TooltipType;isAdvanced()Z"))
    private boolean gbw$allowAdvanced(boolean original) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @WrapOperation(method = "appendTooltip", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 5))
    private <T> void gbw$allowAdvanced5(Consumer<T> instance, T t, Operation<Void> original, @Local(argsOnly = true) TooltipType type) {
        instance.accept((T) Text.translatable("item.durability", getMaxDamage() - getDamage(), getMaxDamage()).formatted(Formatting.GRAY));
    }

    @WrapWithCondition(method = "appendTooltip", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 6))
    private <T> boolean gbw$allowAdvanced6(Consumer<T> instance, T t, @Local(argsOnly = true) TooltipType type) {
        return type.isAdvanced();
    }

    @WrapWithCondition(method = "appendTooltip", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 7))
    private <T> boolean gbw$allowAdvanced7(Consumer<T> instance, T t, @Local(argsOnly = true) TooltipType type) {
        return type.isAdvanced();
    }
}
