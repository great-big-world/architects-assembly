package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrameRenderer.class)
public abstract class ItemFrameEntityRendererMixin<T extends ItemFrame> {
    @Shadow protected abstract int getLightCoords(boolean glow, int glowLight, int regularLight);
    @Final @Shadow private BlockRenderDispatcher blockRenderer;

    @SuppressWarnings("deprecation")
    @Inject(method = "submit(Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/BlockStateDefinitions;getItemFrameFakeState(ZZ)Lnet/minecraft/world/level/block/state/BlockState;"), cancellable = true)
    private void gbw$tintDyedItemFrame(ItemFrameRenderState itemFrameEntityRenderState, PoseStack matrixStack, SubmitNodeCollector orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        if (itemFrameEntityRenderState.mapId == null) {
            BlockState blockState = ExtendedItemFrame.getStateForItemFrame(itemFrameEntityRenderState.isGlowFrame, false, false);
            BlockStateModel blockStateModel = blockRenderer.getBlockModel(blockState);

            if (itemFrameEntityRenderState instanceof ExtendedItemFrame extendedItemFrame) {
                DyeColor color = extendedItemFrame.gbw$getColor();
                if (color != null) {
                    blockState = blockState.setValue(ExtendedItemFrame.DYED, true);
                    blockStateModel = blockRenderer.getBlockModel(blockState);
                    orderedRenderCommandQueue.submitBlockModel(matrixStack, RenderTypes.entitySolidZOffsetForward(TextureAtlas.LOCATION_BLOCKS), blockStateModel, ARGB.red(color.getTextureDiffuseColor()) / 255f, ARGB.green(color.getTextureDiffuseColor()) / 255f, ARGB.blue(color.getTextureDiffuseColor()) / 255f, itemFrameEntityRenderState.lightCoords, OverlayTexture.NO_OVERLAY, itemFrameEntityRenderState.outlineColor);
                } else orderedRenderCommandQueue.submitBlockModel(matrixStack, RenderTypes.entitySolidZOffsetForward(TextureAtlas.LOCATION_BLOCKS), blockStateModel, 1f, 1f, 1f, itemFrameEntityRenderState.lightCoords, OverlayTexture.NO_OVERLAY, itemFrameEntityRenderState.outlineColor);
            } else orderedRenderCommandQueue.submitBlockModel(matrixStack, RenderTypes.entitySolidZOffsetForward(TextureAtlas.LOCATION_BLOCKS), blockStateModel, 1f, 1f, 1f, itemFrameEntityRenderState.lightCoords, OverlayTexture.NO_OVERLAY, itemFrameEntityRenderState.outlineColor);

            matrixStack.popPose();

            if (itemFrameEntityRenderState.isInvisible)
                matrixStack.translate(0f, 0f, .5f);
            else matrixStack.translate(0f, 0f, .4375f);

            if (!itemFrameEntityRenderState.item.isEmpty()) {
                matrixStack.mulPose(Axis.ZP.rotationDegrees((float)itemFrameEntityRenderState.rotation * 360f / 8f));
                int j = getLightCoords(itemFrameEntityRenderState.isGlowFrame, 15728880, itemFrameEntityRenderState.lightCoords);
                matrixStack.scale(.5f, .5f, .5f);
                itemFrameEntityRenderState.item.submit(matrixStack, orderedRenderCommandQueue, j, OverlayTexture.NO_OVERLAY, itemFrameEntityRenderState.outlineColor);
            }

            matrixStack.popPose();
            ci.cancel();
        }
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/decoration/ItemFrame;Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;F)V", at = @At("TAIL"))
    private void gbw$fixItemFrameRenderState(T itemFrameEntity, ItemFrameRenderState itemFrameEntityRenderState, float f, CallbackInfo ci) {
        if (itemFrameEntity instanceof ExtendedItemFrame extendedItemFrame && itemFrameEntityRenderState instanceof ExtendedItemFrame extendedItemFrame1) {
            extendedItemFrame1.gbw$setColor(extendedItemFrame.gbw$getColor());
        }
    }
}
