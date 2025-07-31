package dev.creoii.greatbigworld.architectsassembly.client;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyParticleTypes;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyScreens;
import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import dev.creoii.greatbigworld.architectsassembly.variant.VariantItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.InputUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.biome.GrassColors;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;
import java.util.List;

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

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> tintIndex > 0 ? -1 : world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getDefaultColor(), ArchitectsAssemblyBlocks.POTTED_SHORT_GRASS);

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

        HudLayerRegistrationCallback.EVENT.register(layeredDrawerWrapper -> {
            layeredDrawerWrapper.addLayer(new IdentifiedLayer() {
                @Override
                public Identifier id() {
                    return Identifier.of(GreatBigWorld.NAMESPACE, "cycle_hotbar");
                }

                @Override
                public void render(DrawContext context, RenderTickCounter tickCounter) {
                    if (!context.client.options.hudHidden && CYCLE_HOTBAR.isPressed()) {
                        int x = (context.getScaledWindowWidth() / 2) - 91 - 4;
                        int y = context.getScaledWindowHeight() - 22 - 6;

                        context.getMatrices().push();
                        context.getMatrices().translate(0f, 0f, -90f);
                        context.drawGuiTexture(RenderLayer::getGuiTextured, CYCLE_HOTBAR_ARROW_TEXTURE, x, y, 9, 14);
                        context.getMatrices().pop();
                    }
                }
            });
        });

        // Fix Saddle, Elytra, Shears,
        ItemTooltipCallback.EVENT.register((stack, context, tooltipType, list) -> {
            EquippableComponent equippableComponent = stack.getOrDefault(DataComponentTypes.EQUIPPABLE, null);
            ToolComponent toolComponent = stack.getOrDefault(DataComponentTypes.TOOL, null);
            if (equippableComponent != null || toolComponent != null) {
                // remove ugly tooltip text, such as "When in hand...", but don't remove advanced item identifiers
                MutableInt attributesStartIndex = new MutableInt(-2);
                for (Text text : list) {
                    attributesStartIndex.increment();
                    if (text.getString().startsWith("When ")) {
                        break;
                    }
                }
                if (tooltipType.isAdvanced()) {
                    MutableInt identifierStartIndex = new MutableInt(-1);
                    for (int i = 0; i < list.size(); ++i) {
                        String line = list.get(i).getString();
                        if (!line.isBlank() && Identifier.tryParse(line) != null) {
                            identifierStartIndex.setValue(i);
                            break;
                        }
                    }
                    list.removeIf(text -> {
                        int i = list.indexOf(text);
                        int minI = attributesStartIndex.getValue();
                        int maxI = identifierStartIndex.getValue();
                        if (maxI != -1) {
                            return i >= minI && i < maxI;
                        } else return i >= minI;
                    });
                } else if (attributesStartIndex.getValue() > 0) {
                    list.removeIf(text -> list.indexOf(text) >= attributesStartIndex.getValue());
                }
            }

            if (shouldExpandTooltips() || tooltipType.isAdvanced()) {
                List<MutableText> itemTooltipInfos = new ArrayList<>();

                FoodComponent foodComponent = stack.getOrDefault(DataComponentTypes.FOOD, null);
                if (foodComponent != null) {
                    itemTooltipInfos.add(Text.translatable("item.tooltip.hunger", foodComponent.nutrition()).formatted(Formatting.GRAY));
                }
                if (toolComponent != null) {
                    itemTooltipInfos.add(Text.translatable("item.tooltip.mining_speed", (int) toolComponent.defaultMiningSpeed()).formatted(Formatting.GRAY));
                }
                WeaponComponent weaponComponent = stack.getOrDefault(DataComponentTypes.WEAPON, null);
                if (weaponComponent != null) {
                    itemTooltipInfos.add(Text.translatable("item.tooltip.damage", weaponComponent.itemDamagePerAttack()).formatted(Formatting.GRAY));
                }
                if (stack.isOf(Items.TURTLE_HELMET)) {
                    itemTooltipInfos.add(Text.translatable("item.tooltip.air", "10s").formatted(Formatting.GRAY));
                }
                AttributeModifiersComponent attributeModifiersComponent = stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, null);
                if (equippableComponent != null && attributeModifiersComponent != null) {
                    attributeModifiersComponent.modifiers().forEach(entry -> {
                        if (entry.attribute().matchesKey(EntityAttributes.ARMOR.getKey().get())) {
                            itemTooltipInfos.add(Text.translatable("item.tooltip.armor", entry.modifier().value()).formatted(Formatting.GRAY));
                        }
                    });
                }

                if (!itemTooltipInfos.isEmpty())
                    list.add(1, Texts.join(itemTooltipInfos, Text.translatable("item.tooltip.separator")));
            }

            if (stack.isIn(ItemTags.DECORATED_POT_SHERDS)) {
                Identifier id = Registries.ITEM.getId(stack.getItem());
                list.add(Text.translatable("variant.item.sherd." + id.getPath().replace("_pottery_sherd", "")).formatted(Formatting.GRAY));
            }

            if (stack.getItem() instanceof VariantItem variantItem && !ItemStack.areEqual(stack, Raid.createOminousBanner(context.getRegistryLookup().getOrThrow(RegistryKeys.BANNER_PATTERN)))) {
                for (Variant variant : Variant.VARIANTS.values()) {
                    if (variant.getItems().contains(variantItem) || variant.isStackInTags(stack))
                        variantItem.gbw$addVariant(variant);
                }

                if (!variantItem.gbw$getVariants().isEmpty())
                    list.add(1, VariantItem.getVariantTooltip(variantItem));
                else if (stack.getItem() instanceof SpawnEggItem spawnEggItem) {
                    list.add(1, MutableText.of(spawnEggItem.getEntityType(context.getRegistryLookup(), stack).getName().getContent()).formatted(Formatting.GRAY));
                }
            }
        });
    }

    public static boolean shouldExpandTooltips() {
        return shouldExpandTooltips;
    }

    public static boolean shouldCycleHotbar() {
        return shouldCycleHotbar;
    }
}
