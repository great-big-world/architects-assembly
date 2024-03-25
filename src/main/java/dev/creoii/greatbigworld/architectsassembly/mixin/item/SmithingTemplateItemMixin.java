package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SmithingTemplateItem.class)
public class SmithingTemplateItemMixin {
    @Inject(method = "appendTooltip", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 1), cancellable = true)
    private void gbw$ignoreSmithingTemplateTooltipInfo(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        ci.cancel();
    }
}
