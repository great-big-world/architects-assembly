package dev.creoii.greatbigworld.architectsassembly.client;

import com.mojang.blaze3d.platform.InputConstants;
import dev.creoii.greatbigworld.architectsassembly.variant.VariantItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
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

import java.util.ArrayList;
import java.util.List;

public final class ArchitectsAssemblyClientEvents {
    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            Minecraft.getInstance().getLanguageManager().onResourceManagerReload(client.getResourceManager());
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.player.isSpectator())
                return;

            ArchitectsAssemblyClient.CYCLE_HOTBAR.setDown(client.screen == null && InputConstants.isKeyDown(client.getWindow(), ArchitectsAssemblyClient.CYCLE_HOTBAR.key.getValue()));

            ArchitectsAssemblyClient.shouldCycleHotbar = ArchitectsAssemblyClient.CYCLE_HOTBAR.isDown();
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
                if (!variantItem.gbw$getVariants().isEmpty()) {
                    list.add(1, VariantItem.getVariantTooltip(variantItem));
                } else if (stack.getItem() instanceof SpawnEggItem spawnEggItem) {
                    list.add(1, MutableComponent.create(spawnEggItem.getType(stack).getDescription().getContents()).withStyle(ChatFormatting.GRAY));
                }
            }
        });
    }
}
