package dev.creoii.greatbigworld.architectsassembly.client;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyParticleTypes;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.GrassColors;

public class ArchitectsAssemblyClient implements ClientModInitializer {
    public static final KeyBinding EXPAND_TOOLTIPS = new KeyBinding("key." + GreatBigWorld.NAMESPACE + ".expand_tooltips", InputUtil.GLFW_KEY_LEFT_CONTROL, KeyBinding.INVENTORY_CATEGORY);
    public static final KeyBinding CYCLE_HOTBAR = new KeyBinding("key." + GreatBigWorld.NAMESPACE + ".cycle_hotbar", InputUtil.GLFW_KEY_LEFT_CONTROL, KeyBinding.INVENTORY_CATEGORY);
    private static final Identifier CYCLE_HOTBAR_ARROW_TEXTURE = Identifier.of(GreatBigWorld.NAMESPACE, "hud/cycle_hotbar_arrow");
    private static boolean shouldExpandTooltips = false;
    private static boolean shouldCycleHotbar = false;

    @Override
    public void onInitializeClient() {
        ArchitectsAssemblyBlocks.registerClient();
        ArchitectsAssemblyParticleTypes.registerClient();
        ArchitectsAssemblyScreens.registerClient();

        KeyBindingHelper.registerKeyBinding(EXPAND_TOOLTIPS);
        KeyBindingHelper.registerKeyBinding(CYCLE_HOTBAR);

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            MinecraftClient.getInstance().getLanguageManager().reload(client.getResourceManager());
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.player.isSpectator())
                return;

            EXPAND_TOOLTIPS.setPressed(client.currentScreen != null && InputUtil.isKeyPressed(client.getWindow().getHandle(), EXPAND_TOOLTIPS.boundKey.getCode()));
            CYCLE_HOTBAR.setPressed(client.currentScreen == null && InputUtil.isKeyPressed(client.getWindow().getHandle(), CYCLE_HOTBAR.boundKey.getCode()));

            shouldExpandTooltips = EXPAND_TOOLTIPS.isPressed();
            shouldCycleHotbar = CYCLE_HOTBAR.isPressed();
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (!drawContext.client.options.hudHidden && CYCLE_HOTBAR.isPressed()) {
                int x = (drawContext.getScaledWindowWidth() / 2) - 91 - 4;
                int y = drawContext.getScaledWindowHeight() - 22 - 6;

                drawContext.getMatrices().push();
                drawContext.getMatrices().translate(0f, 0f, -90f);
                drawContext.drawGuiTexture(RenderLayer::getGuiTextured, CYCLE_HOTBAR_ARROW_TEXTURE, x, y, 9, 14);
                drawContext.getMatrices().pop();
            }
        });

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> tintIndex > 0 ? -1 : world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getDefaultColor(), ArchitectsAssemblyBlocks.POTTED_SHORT_GRASS);
    }

    public static boolean shouldExpandTooltips() {
        return shouldExpandTooltips;
    }

    public static boolean shouldCycleHotbar() {
        return shouldCycleHotbar;
    }
}
