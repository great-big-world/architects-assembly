package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.client.ArchitectsAssemblyClient;
import dev.creoii.greatbigworld.architectsassembly.item.DyedItemFrameItem;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.item.DecorationItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DecorationItem.class)
public class DecorationItemMixin {
    @Shadow @Final private static Text RANDOM_TEXT;

    @Inject(method = "appendTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/painting/PaintingEntity;readVariantFromNbt(Lnet/minecraft/nbt/NbtCompound;)Ljava/util/Optional;"), cancellable = true)
    private void gbw$improvePaintingTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci, @Local(ordinal = 1) NbtCompound nbtCompound2) {
        ci.cancel();
        PaintingEntity.readVariantFromNbt(nbtCompound2).ifPresentOrElse(variant -> {
            variant.getKey().ifPresent(key -> {
                MutableText mutableText = Text.empty();
                mutableText.append(Text.translatable(key.getValue().toTranslationKey("painting", "title")));
                mutableText.append(" - ");
                mutableText.append(Text.translatable(key.getValue().toTranslationKey("painting", "author")));
                tooltip.add(mutableText.formatted(Formatting.GRAY));
            });
            if (ArchitectsAssemblyClient.shouldExpandTooltips())
                tooltip.add(Text.translatable("painting.dimensions", MathHelper.ceilDiv(variant.value().getWidth(), 16), MathHelper.ceilDiv(variant.value().getHeight(), 16)).formatted(Formatting.GRAY));
        }, () -> tooltip.add(RANDOM_TEXT));
    }

    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private void gbw_dyePlacedItemFrame(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, @Local ItemStack itemStack, @Local AbstractDecorationEntity abstractDecorationEntity) {
        if (itemStack.getItem() instanceof DyedItemFrameItem dyedItemFrameItem && abstractDecorationEntity instanceof ExtendedItemFrame itemFrame) {
            itemFrame.gbw$setColor(dyedItemFrameItem.getColor());
        }
    }
}
