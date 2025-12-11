package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyItems;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.world.entity.decoration.GlowItemFrame;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlowItemFrame.class)
public abstract class GlowItemFrameEntityMixin implements ExtendedItemFrame {
    @Inject(method = "getFrameItemStack", at = @At("HEAD"), cancellable = true)
    private void gbw$fixItemFrameDropAndPickStack(CallbackInfoReturnable<ItemStack> cir) {
        DyeColor color = gbw$getColor();
        if (color == null)
            return;
        switch (color) {
            case GRAY -> cir.setReturnValue(ArchitectsAssemblyItems.GRAY_GLOW_ITEM_FRAME.getDefaultInstance());
            case RED -> cir.setReturnValue(ArchitectsAssemblyItems.RED_GLOW_ITEM_FRAME.getDefaultInstance());
            case BLUE -> cir.setReturnValue(ArchitectsAssemblyItems.BLUE_GLOW_ITEM_FRAME.getDefaultInstance());
            case CYAN -> cir.setReturnValue(ArchitectsAssemblyItems.CYAN_GLOW_ITEM_FRAME.getDefaultInstance());
            case LIME -> cir.setReturnValue(ArchitectsAssemblyItems.LIME_GLOW_ITEM_FRAME.getDefaultInstance());
            case PINK -> cir.setReturnValue(ArchitectsAssemblyItems.PINK_GLOW_ITEM_FRAME.getDefaultInstance());
            case BLACK -> cir.setReturnValue(ArchitectsAssemblyItems.BLACK_GLOW_ITEM_FRAME.getDefaultInstance());
            case BROWN -> cir.setReturnValue(ArchitectsAssemblyItems.BROWN_GLOW_ITEM_FRAME.getDefaultInstance());
            case GREEN -> cir.setReturnValue(ArchitectsAssemblyItems.GREEN_GLOW_ITEM_FRAME.getDefaultInstance());
            case WHITE -> cir.setReturnValue(ArchitectsAssemblyItems.WHITE_GLOW_ITEM_FRAME.getDefaultInstance());
            case ORANGE -> cir.setReturnValue(ArchitectsAssemblyItems.ORANGE_GLOW_ITEM_FRAME.getDefaultInstance());
            case PURPLE -> cir.setReturnValue(ArchitectsAssemblyItems.PURPLE_GLOW_ITEM_FRAME.getDefaultInstance());
            case YELLOW -> cir.setReturnValue(ArchitectsAssemblyItems.YELLOW_GLOW_ITEM_FRAME.getDefaultInstance());
            case MAGENTA -> cir.setReturnValue(ArchitectsAssemblyItems.MAGENTA_GLOW_ITEM_FRAME.getDefaultInstance());
            case LIGHT_BLUE -> cir.setReturnValue(ArchitectsAssemblyItems.LIGHT_BLUE_GLOW_ITEM_FRAME.getDefaultInstance());
            case LIGHT_GRAY -> cir.setReturnValue(ArchitectsAssemblyItems.LIGHT_GRAY_GLOW_ITEM_FRAME.getDefaultInstance());
        }
    }
}
