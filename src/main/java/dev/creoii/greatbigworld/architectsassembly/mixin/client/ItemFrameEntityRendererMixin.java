package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameEntityRendererMixin<T extends ItemFrameEntity> {
    @Unique private static final ModelIdentifier DYED_NORMAL_FRAME = ModelIdentifier.ofVanilla("item_frame", "dyed=true,map=false");
    @Unique private static final ModelIdentifier DYED_GLOW_FRAME = ModelIdentifier.ofVanilla("glow_item_frame", "dyed=true,map=false");

    @Inject(method = "getModelId", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private void gbw$renderDyedItemFrame(T entity, ItemStack stack, CallbackInfoReturnable<ModelIdentifier> cir) {
        if (entity instanceof ExtendedItemFrame extendedItemFrame && extendedItemFrame.gbw$getColor() != null) {
            cir.setReturnValue(entity.getType() == EntityType.GLOW_ITEM_FRAME ? DYED_GLOW_FRAME : DYED_NORMAL_FRAME);
        }
    }

    @Redirect(method = "render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockModelRenderer;render(Lnet/minecraft/client/util/math/MatrixStack$Entry;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/block/BlockState;Lnet/minecraft/client/render/model/BakedModel;FFFII)V"))
    private void gbw$tintDyedItemFrame(BlockModelRenderer instance, MatrixStack.Entry entry, VertexConsumer vertexConsumer, BlockState state, BakedModel bakedModel, float red, float green, float blue, int light, int overlay, @Local(argsOnly = true) T itemFrameEntity, @Local ModelIdentifier modelIdentifier) {
        DyeColor color;
        if (itemFrameEntity instanceof ExtendedItemFrame extendedItemFrame && (color = extendedItemFrame.gbw$getColor()) != null) {
            instance.render(entry, vertexConsumer, state, bakedModel, ColorHelper.Argb.getRed(color.getEntityColor()) / 255f, ColorHelper.Argb.getGreen(color.getEntityColor()) / 255f, ColorHelper.Argb.getBlue(color.getEntityColor()) / 255f, light, overlay);
        } else {
            instance.render(entry, vertexConsumer, state, bakedModel, 1f, 1f, 1f, light, overlay);
        }
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/ModelIdentifier;ofVanilla(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/client/util/ModelIdentifier;"))
    private static ModelIdentifier gbw$redirectNormalFrame(String path, String variant) {
        if (!variant.contains("dyed"))
            return ModelIdentifier.ofVanilla(path, "dyed=false," + variant);
        return ModelIdentifier.ofVanilla(path, variant);
    }
}
