package dev.creoii.greatbigworld.architectsassembly.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.PotDecorations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Consumer;

@Mixin(PotDecorations.class)
public abstract class PotDecorationsMixin {
    @Shadow
    public abstract List<Item> ordered();

    @Inject(method = "addToTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/PotDecorations;addSideDetailsToTooltip(Ljava/util/function/Consumer;Ljava/util/Optional;)V", ordinal = 0), cancellable = true)
    private void gbw$cancelDefaultPotTooltip(Item.TooltipContext tooltipContext, Consumer<Component> consumer, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter, CallbackInfo ci) {
        ordered().forEach(item -> {
            ItemStack stack1 = item.getDefaultInstance();
            if (stack1.is(ItemTags.DECORATED_POT_SHERDS)) {
                Identifier id = BuiltInRegistries.ITEM.getKey(stack1.getItem());
                consumer.accept(Component.translatable("variant.item.sherd." + id.getPath().replace("_pottery_sherd", "")).withStyle(ChatFormatting.GRAY));
            }
        });

        ci.cancel();
    }
}
