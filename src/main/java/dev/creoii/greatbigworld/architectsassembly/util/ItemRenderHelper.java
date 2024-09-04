package dev.creoii.greatbigworld.architectsassembly.util;

import net.minecraft.block.Block;
import net.minecraft.block.StainedGlassPaneBlock;
import net.minecraft.block.TranslucentBlock;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MatrixUtil;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class ItemRenderHelper {
    private static final ModelIdentifier TRIDENT = ModelIdentifier.ofVanilla("trident", "inventory");
    private static final ModelIdentifier SPYGLASS = ModelIdentifier.ofVanilla("spyglass", "inventory");

    public static void drawItemSilhouette(DrawContext context, @Nullable LivingEntity entity, @Nullable World world, ItemStack stack, int x, int y, int seed, int z) {
        if (stack.isEmpty()) {
            return;
        }
        BakedModel bakedModel = context.client.getItemRenderer().getModel(stack, world, entity, seed);
        context.getMatrices().push();
        context.getMatrices().translate(x + 8, y + 8, 150 + (bakedModel.hasDepth() ? z : 0));
        try {
            boolean bl = !bakedModel.isSideLit();
            context.getMatrices().scale(16f, -16f, 16f);
            if (bl) {
                DiffuseLighting.disableGuiDepthLighting();
            }
            renderItemSilhouette(context.client.getItemRenderer(), stack, ModelTransformationMode.GUI, false, context.getMatrices(), context.getVertexConsumers(), 0xf000f0, OverlayTexture.DEFAULT_UV, bakedModel);
            context.draw();
            if (bl) {
                DiffuseLighting.enableGuiDepthLighting();
            }
        } catch (Throwable throwable) {
            CrashReport crashReport = CrashReport.create(throwable, "Rendering item");
            CrashReportSection crashReportSection = crashReport.addElement("Item being rendered");
            crashReportSection.add("Item Type", () -> String.valueOf(stack.getItem()));
            crashReportSection.add("Item Components", () -> String.valueOf(stack.getComponents()));
            crashReportSection.add("Item Foil", () -> String.valueOf(stack.hasGlint()));
            throw new CrashException(crashReport);
        }
        context.getMatrices().pop();
    }

    public static void renderItemSilhouette(ItemRenderer itemRenderer, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model) {
        if (stack.isEmpty()) {
            return;
        }
        matrices.push();
        boolean bl = renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED;
        if (bl) {
            if (stack.isOf(Items.TRIDENT)) {
                model = itemRenderer.getModels().getModelManager().getModel(TRIDENT);
            } else if (stack.isOf(Items.SPYGLASS)) {
                model = itemRenderer.getModels().getModelManager().getModel(SPYGLASS);
            }
        }
        model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
        matrices.translate(-.5f, -.5f, -.5f);
        if (model.isBuiltin() || stack.isOf(Items.TRIDENT) && !bl) {
            itemRenderer.builtinModelItemRenderer.render(stack, renderMode, matrices, vertexConsumers, light, overlay);
        } else {
            VertexConsumer vertexConsumer;
            Block block;
            boolean bl22 = renderMode == ModelTransformationMode.GUI || renderMode.isFirstPerson() || !(stack.getItem() instanceof BlockItem) || !((block = ((BlockItem) stack.getItem()).getBlock()) instanceof TranslucentBlock) && !(block instanceof StainedGlassPaneBlock);
            RenderLayer renderLayer = RenderLayers.getItemLayer(stack, bl22);
            if (usesDynamicDisplay(stack) && stack.hasGlint()) {
                MatrixStack.Entry entry = matrices.peek().copy();
                if (renderMode == ModelTransformationMode.GUI) {
                    MatrixUtil.scale(entry.getPositionMatrix(), .5f);
                } else if (renderMode.isFirstPerson()) {
                    MatrixUtil.scale(entry.getPositionMatrix(), .75f);
                }
                vertexConsumer = bl22 ? ItemRenderer.getDirectDynamicDisplayGlintConsumer(vertexConsumers, renderLayer, entry) : ItemRenderer.getDynamicDisplayGlintConsumer(vertexConsumers, renderLayer, entry);
            } else {
                vertexConsumer = bl22 ? ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, renderLayer, true, stack.hasGlint()) : ItemRenderer.getItemGlintConsumer(vertexConsumers, renderLayer, true, stack.hasGlint());
            }
            renderBakedItemModelSilhouette(model, overlay, matrices, vertexConsumer, light);
        }
        matrices.pop();
    }

    private static void renderBakedItemModelSilhouette(BakedModel model, int overlay, MatrixStack matrices, VertexConsumer vertices, int light) {
        Random random = Random.create();
        for (Direction direction : Direction.values()) {
            random.setSeed(42L);
            renderBakedItemQuadsSilhouette(matrices, vertices, model.getQuads(null, direction, random), light, overlay);
        }
        random.setSeed(42L);
        renderBakedItemQuadsSilhouette(matrices, vertices, model.getQuads(null, null, random), light, overlay);
    }

    private static void renderBakedItemQuadsSilhouette(MatrixStack matrices, VertexConsumer vertices, List<BakedQuad> quads, int light, int overlay) {
        MatrixStack.Entry entry = matrices.peek();
        for (BakedQuad bakedQuad : quads) {
            vertices.quad(entry, bakedQuad, 0f, 0f, 0f, 255f, light, overlay);
        }
    }

    private static boolean usesDynamicDisplay(ItemStack stack) {
        return stack.isIn(ItemTags.COMPASSES) || stack.isOf(Items.CLOCK);
    }
}
