package dev.creoii.greatbigworld.architectsassembly.client;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.item.DyedItemFrameItem;
import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyItems;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyParticleTypes;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyScreens;
import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import dev.creoii.greatbigworld.architectsassembly.variant.VariantItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.biome.GrassColors;

import java.util.function.BiConsumer;

public class ArchitectsAssemblyClient implements ClientModInitializer {
    public static final MinecraftClient CLIENT = MinecraftClient.getInstance();
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

        Identifier id = Identifier.of(GreatBigWorld.NAMESPACE, "placement");
        ModelPredicateProviderRegistry.register(id, (stack, world, entity, seed) -> {
            if (stack.isIn(ItemTags.SLABS)) {
                return SlabItem.getPlacement(entity);
            }
            return 0f;
        });

        /*LanguageEvents.LOAD_TRANSLATION.register((langCode, consumer, translationKey, translated) -> {
            if (langCode == null || !langCode.equals("en_us"))
                return true;

            if ((translationKey.startsWith("item.") && ((translationKey.contains("_pottery_sherd") || translationKey.contains("_pottery_shard"))) || translationKey.contains("_spawn_egg"))) {
                consumer.accept(translationKey, translated.substring(translated.indexOf(" ") + 1));
                return false;
            }

            Item item = Registries.ITEM.get(toId(translationKey));
            return renameItemForVariants(item, consumer, translationKey, translated);
        });*/

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            MinecraftClient.getInstance().getLanguageManager().reload(client.getResourceManager());
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            EXPAND_TOOLTIPS.setPressed(client.currentScreen != null && InputUtil.isKeyPressed(client.getWindow().getHandle(), EXPAND_TOOLTIPS.boundKey.getCode()));
            CYCLE_HOTBAR.setPressed(client.currentScreen == null && InputUtil.isKeyPressed(client.getWindow().getHandle(), CYCLE_HOTBAR.boundKey.getCode()));

            shouldExpandTooltips = EXPAND_TOOLTIPS.isPressed();
            shouldCycleHotbar = CYCLE_HOTBAR.isPressed();
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (!CLIENT.options.hudHidden && CYCLE_HOTBAR.isPressed()) {
                int x = (drawContext.getScaledWindowWidth() / 2) - 91 - 4;
                int y = drawContext.getScaledWindowHeight() - 22 - 6;

                drawContext.getMatrices().push();
                drawContext.getMatrices().translate(0f, 0f, -90f);
                drawContext.drawGuiTexture(CYCLE_HOTBAR_ARROW_TEXTURE, x, y, 9, 14);
                drawContext.getMatrices().pop();
            }
        });

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex < 1 ? -1 : ColorHelper.Argb.fullAlpha(((DyedItemFrameItem) stack.getItem()).getColor().getMapColor().color),
                ArchitectsAssemblyItems.BROWN_ITEM_FRAME, ArchitectsAssemblyItems.BROWN_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.RED_ITEM_FRAME, ArchitectsAssemblyItems.RED_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.ORANGE_ITEM_FRAME, ArchitectsAssemblyItems.ORANGE_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.YELLOW_ITEM_FRAME, ArchitectsAssemblyItems.YELLOW_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.LIME_ITEM_FRAME, ArchitectsAssemblyItems.LIME_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.GREEN_ITEM_FRAME, ArchitectsAssemblyItems.GREEN_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.CYAN_ITEM_FRAME, ArchitectsAssemblyItems.CYAN_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.BLUE_ITEM_FRAME, ArchitectsAssemblyItems.BLUE_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.LIGHT_BLUE_ITEM_FRAME, ArchitectsAssemblyItems.LIGHT_BLUE_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.PINK_ITEM_FRAME, ArchitectsAssemblyItems.PINK_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.MAGENTA_ITEM_FRAME, ArchitectsAssemblyItems.MAGENTA_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.PURPLE_ITEM_FRAME, ArchitectsAssemblyItems.PURPLE_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.BLACK_ITEM_FRAME, ArchitectsAssemblyItems.BLACK_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.GRAY_ITEM_FRAME, ArchitectsAssemblyItems.GRAY_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.LIGHT_GRAY_ITEM_FRAME, ArchitectsAssemblyItems.LIGHT_GRAY_GLOW_ITEM_FRAME,
                ArchitectsAssemblyItems.WHITE_ITEM_FRAME, ArchitectsAssemblyItems.WHITE_GLOW_ITEM_FRAME
        );
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> tintIndex > 0 ? -1 : world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getDefaultColor(), ArchitectsAssemblyBlocks.POTTED_SHORT_GRASS);
    }

    public static boolean shouldExpandTooltips() {
        return shouldExpandTooltips;
    }

    public static boolean shouldCycleHotbar() {
        return shouldCycleHotbar;
    }

    private static Identifier toId(String translationKey) {
        translationKey = translationKey.toLowerCase();

        int dot1 = translationKey.indexOf('.') + 1;
        int dot2 = translationKey.indexOf('.', dot1);
        int dot3 = translationKey.indexOf('.', dot2 + 1);

        if (dot2 < 0)
            return Identifier.of("air");

        String path;
        if (dot3 <= 0) path = translationKey.substring(dot2 + 1);
        else path = translationKey.substring(dot2 + 1, dot3);

        return Identifier.of(translationKey.substring(dot1, dot2), path);
    }

    private static boolean renameItemForVariants(Item item, BiConsumer<String, String> consumer, String translationKey, String translated) {
        if (item == Items.AIR)
            return true;
        if (item instanceof VariantItem variantItem) {
            for (Variant variant : Variant.VARIANTS.values()) {
                if (variant.getItems().contains(item) || variant.isStackInTags(item.getDefaultStack())) {
                    variantItem.gbw$addVariant(variant);
                }
            }

            if (!variantItem.gbw$getVariants().isEmpty()) {
                String variantKey = translationKey.endsWith(".variant") ? translationKey : translationKey + ".variant";
                String translated1 = Text.translatable(variantKey).getString();
                if (!translated1.equals(translated)) {
                    consumer.accept(translationKey, translated1);
                    return false;
                }
            }
        }
        return true;
    }
}
