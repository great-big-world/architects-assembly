package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.item.DyedItemFrameItem;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.item.DecorationItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecorationItem.class)
public class DecorationItemMixin {
    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private void gbw$dyePlacedItemFrame(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, @Local ItemStack itemStack, @Local AbstractDecorationEntity abstractDecorationEntity) {
        if (itemStack.getItem() instanceof DyedItemFrameItem dyedItemFrameItem && abstractDecorationEntity instanceof ExtendedItemFrame itemFrame) {
            itemFrame.gbw$setColor(dyedItemFrameItem.getColor());
        }
    }
}
