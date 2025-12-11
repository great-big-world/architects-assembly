package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.item.DyedItemFrameItem;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HangingEntityItem.class)
public class DecorationItemMixin {
    @Inject(method = "useOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private void gbw$dyePlacedItemFrame(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, @Local ItemStack itemStack, @Local HangingEntity abstractDecorationEntity) {
        if (itemStack.getItem() instanceof DyedItemFrameItem dyedItemFrameItem && abstractDecorationEntity instanceof ExtendedItemFrame itemFrame) {
            itemFrame.gbw$setColor(dyedItemFrameItem.getColor());
        }
    }
}
