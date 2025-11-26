package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.client.render.entity.state.ItemFrameEntityRenderState;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrameEntityRenderer.class)
public abstract class ItemFrameEntityRendererMixin<T extends ItemFrameEntity> {
    @Shadow protected abstract int getLight(boolean glow, int glowLight, int regularLight);
    @Final @Shadow private BlockRenderManager blockRenderManager;

    @SuppressWarnings("deprecation")
    @Inject(method = "render(Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;submitBlockStateModel(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/render/model/BlockStateModel;FFFIII)V"), cancellable = true)
    private void gbw$tintDyedItemFrame(ItemFrameEntityRenderState itemFrameEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        if (itemFrameEntityRenderState.mapId == null) {
            BlockState blockState = ExtendedItemFrame.getStateForItemFrame(itemFrameEntityRenderState.glow, false, false);
            BlockStateModel blockStateModel = blockRenderManager.getModel(blockState);

            if (itemFrameEntityRenderState instanceof ExtendedItemFrame extendedItemFrame) {
                DyeColor color = extendedItemFrame.gbw$getColor();
                if (color != null) {
                    blockState = blockState.with(ExtendedItemFrame.DYED, true);
                    blockStateModel = blockRenderManager.getModel(blockState);
                    orderedRenderCommandQueue.submitBlockStateModel(matrixStack, RenderLayer.getEntitySolidZOffsetForward(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE), blockStateModel, ColorHelper.getRed(color.getEntityColor()) / 255f, ColorHelper.getGreen(color.getEntityColor()) / 255f, ColorHelper.getBlue(color.getEntityColor()) / 255f, itemFrameEntityRenderState.light, OverlayTexture.DEFAULT_UV, itemFrameEntityRenderState.outlineColor);
                } else orderedRenderCommandQueue.submitBlockStateModel(matrixStack, RenderLayer.getEntitySolidZOffsetForward(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE), blockStateModel, 1f, 1f, 1f, itemFrameEntityRenderState.light, OverlayTexture.DEFAULT_UV, itemFrameEntityRenderState.outlineColor);
            } else orderedRenderCommandQueue.submitBlockStateModel(matrixStack, RenderLayer.getEntitySolidZOffsetForward(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE), blockStateModel, 1f, 1f, 1f, itemFrameEntityRenderState.light, OverlayTexture.DEFAULT_UV, itemFrameEntityRenderState.outlineColor);

            matrixStack.pop();

            if (itemFrameEntityRenderState.invisible)
                matrixStack.translate(0f, 0f, .5f);
            else matrixStack.translate(0f, 0f, .4375f);

            if (!itemFrameEntityRenderState.itemRenderState.isEmpty()) {
                matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float)itemFrameEntityRenderState.rotation * 360f / 8f));
                int j = getLight(itemFrameEntityRenderState.glow, 15728880, itemFrameEntityRenderState.light);
                matrixStack.scale(.5f, .5f, .5f);
                itemFrameEntityRenderState.itemRenderState.render(matrixStack, orderedRenderCommandQueue, j, OverlayTexture.DEFAULT_UV, itemFrameEntityRenderState.outlineColor);
            }

            matrixStack.pop();
            ci.cancel();
        }
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/decoration/ItemFrameEntity;Lnet/minecraft/client/render/entity/state/ItemFrameEntityRenderState;F)V", at = @At("TAIL"))
    private void gbw$fixItemFrameRenderState(T itemFrameEntity, ItemFrameEntityRenderState itemFrameEntityRenderState, float f, CallbackInfo ci) {
        if (itemFrameEntity instanceof ExtendedItemFrame extendedItemFrame && itemFrameEntityRenderState instanceof ExtendedItemFrame extendedItemFrame1) {
            extendedItemFrame1.gbw$setColor(extendedItemFrame.gbw$getColor());
        }
    }
}
