package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(CreativeInventoryScreen.class)
public class CreativeInventoryScreenMixin {
    @Redirect(method = "getTooltipFromItem", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V"))
    private <E> void gbw$fixTooltipOrder(List<Text> instance, int i, E e) {
        instance.add(Math.min(i + 1, instance.size()), (Text) e);
    }
}
