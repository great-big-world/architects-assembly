package dev.creoii.greatbigworld.architectsassembly.client;

import com.mojang.blaze3d.platform.InputConstants;
import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyParticleTypes;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyMenus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.GrassColor;

public class ArchitectsAssemblyClient implements ClientModInitializer {
    public static final KeyMapping CYCLE_HOTBAR = new KeyMapping("key." + GreatBigWorld.NAMESPACE + ".cycle_hotbar", InputConstants.KEY_LCONTROL, KeyMapping.Category.INVENTORY);
    protected static final Identifier CYCLE_HOTBAR_ARROW_TEXTURE = Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "hud/cycle_hotbar_arrow");
    protected static boolean shouldCycleHotbar = false;

    @Override
    public void onInitializeClient() {
        ArchitectsAssemblyBlocks.registerClient();
        ArchitectsAssemblyParticleTypes.registerClient();
        ArchitectsAssemblyMenus.registerClient();
        ArchitectsAssemblyClientEvents.register();
        ArchitectsAssemblyClientNetworking.register();

        KeyBindingHelper.registerKeyBinding(CYCLE_HOTBAR);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> tintIndex > 0 ? -1 : world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.getDefaultColor(), ArchitectsAssemblyBlocks.POTTED_SHORT_GRASS);

        HudElementRegistry.attachElementAfter(VanillaHudElements.HOTBAR, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cycle_hotbar"), (context, tickCounter) -> {
            if (!context.minecraft.options.hideGui && CYCLE_HOTBAR.isDown()) {
                int x = (context.guiWidth() / 2) - 91 - 4;
                int y = context.guiHeight() - 22 - 6;

                context.pose().pushMatrix();
                context.pose().translate(0f, 0f);
                context.blitSprite(RenderPipelines.GUI_TEXTURED, CYCLE_HOTBAR_ARROW_TEXTURE, x, y, 9, 14);
                context.pose().popMatrix();
            }
        });
    }

    public static boolean shouldCycleHotbar() {
        return shouldCycleHotbar;
    }
}
