package dev.creoii.greatbigworld.architectsassembly.client;

import com.mojang.blaze3d.platform.InputConstants;
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
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.GrassColor;

import java.util.ArrayList;
import java.util.List;

public class ArchitectsAssemblyClient implements ClientModInitializer {
    public static final KeyMapping CYCLE_HOTBAR = new KeyMapping("key." + GreatBigWorld.NAMESPACE + ".cycle_hotbar", InputConstants.KEY_LCONTROL, KeyMapping.Category.INVENTORY);
    private static final Identifier CYCLE_HOTBAR_ARROW_TEXTURE = Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "hud/cycle_hotbar_arrow");
    private static boolean shouldCycleHotbar = false;

    @Override
    public void onInitializeClient() {
        ArchitectsAssemblyBlocks.registerClient();
        ArchitectsAssemblyParticleTypes.registerClient();
        ArchitectsAssemblyScreens.registerClient();

        KeyBindingHelper.registerKeyBinding(CYCLE_HOTBAR);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> tintIndex > 0 ? -1 : world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.getDefaultColor(), ArchitectsAssemblyBlocks.POTTED_SHORT_GRASS);

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            Minecraft.getInstance().getLanguageManager().onResourceManagerReload(client.getResourceManager());
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.player.isSpectator())
                return;

            CYCLE_HOTBAR.setDown(client.screen == null && InputConstants.isKeyDown(client.getWindow(), CYCLE_HOTBAR.key.getValue()));

            shouldCycleHotbar = CYCLE_HOTBAR.isDown();
        });

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

        ItemTooltipCallback.EVENT.register((stack, context, tooltipType, list) -> {
            List<MutableComponent> itemTooltipInfos = new ArrayList<>();

            FoodProperties foodComponent = stack.getOrDefault(DataComponents.FOOD, null);
            if (foodComponent != null) {
                itemTooltipInfos.add(Component.translatable("item.tooltip.hunger", foodComponent.nutrition()).withStyle(ChatFormatting.GRAY));
            }

            Tool toolComponent = stack.getOrDefault(DataComponents.TOOL, null);
            if (toolComponent != null) {
                itemTooltipInfos.add(Component.translatable("item.tooltip.mining_speed", (int) toolComponent.defaultMiningSpeed()).withStyle(ChatFormatting.GRAY));
            }

            Weapon weaponComponent = stack.getOrDefault(DataComponents.WEAPON, null);
            if (weaponComponent != null) {
                itemTooltipInfos.add(Component.translatable("item.tooltip.damage", weaponComponent.itemDamagePerAttack()).withStyle(ChatFormatting.GRAY));
            }

            if (stack.is(Items.TURTLE_HELMET)) {
                itemTooltipInfos.add(Component.translatable("item.tooltip.air", "10s").withStyle(ChatFormatting.GRAY));
            }

            Equippable equippableComponent = stack.getOrDefault(DataComponents.EQUIPPABLE, null);
            ItemAttributeModifiers attributeModifiersComponent = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, null);
            if (equippableComponent != null && attributeModifiersComponent != null) {
                attributeModifiersComponent.modifiers().forEach(entry -> {
                    if (entry.attribute().is(Attributes.ARMOR.unwrapKey().get())) {
                        itemTooltipInfos.add(Component.translatable("item.tooltip.armor", entry.modifier().amount()).withStyle(ChatFormatting.GRAY));
                    }
                });
            }

            if (!itemTooltipInfos.isEmpty())
                list.add(1, ComponentUtils.formatList(itemTooltipInfos, Component.translatable("item.tooltip.separator")));

            if (stack.is(ItemTags.DECORATED_POT_SHERDS)) {
                Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
                list.add(Component.translatable("variant.item.sherd." + id.getPath().replace("_pottery_sherd", "")).withStyle(ChatFormatting.GRAY));
            }

            if (stack.getItem() instanceof VariantItem variantItem && !ItemStack.matches(stack, Raid.getOminousBannerInstance(context.registries().lookupOrThrow(Registries.BANNER_PATTERN)))) {
                for (Variant variant : Variant.VARIANTS.values()) {
                    if (variant.getItems().contains(variantItem) || variant.isStackInTags(stack))
                        variantItem.gbw$addVariant(variant);
                }

                if (!variantItem.gbw$getVariants().isEmpty()) {
                    list.add(1, VariantItem.getVariantTooltip(variantItem));
                } else if (stack.getItem() instanceof SpawnEggItem spawnEggItem) {
                    list.add(1, MutableComponent.create(spawnEggItem.getType(stack).getDescription().getContents()).withStyle(ChatFormatting.GRAY));
                }
            }
        });
    }

    public static boolean shouldCycleHotbar() {
        return shouldCycleHotbar;
    }
}
