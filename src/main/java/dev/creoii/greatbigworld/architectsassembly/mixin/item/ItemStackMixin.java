package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.creoapi.api.item.CreoDataComponentTypes;
import dev.creoii.creoapi.api.item.CreoFoodComponent;
import dev.creoii.greatbigworld.architectsassembly.client.ArchitectsAssemblyClient;
import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import dev.creoii.greatbigworld.architectsassembly.variant.VariantItem;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.item.TooltipType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.village.raid.Raid;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0, shift = At.Shift.AFTER))
    private void gbw$appendBlockVariantTooltips(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir, @Local List<Text> list) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.isIn(ItemTags.DECORATED_POT_SHERDS)) {
            String path = Registries.ITEM.getId(stack.getItem()).getPath();

            String variant = path.substring(0, path.indexOf('_'));
            list.add(Text.literal(StringUtils.capitalize(variant)).formatted(Formatting.GRAY));
        }

        if (stack.getItem() instanceof VariantItem variantItem && player != null && !ItemStack.areEqual(stack, Raid.getOminousBanner(player.getRegistryManager().createRegistryLookup().getOrThrow(RegistryKeys.BANNER_PATTERN)))) {
            for (Variant variant : Variant.VARIANTS.values()) {
                if (variant.getItems().contains(variantItem) || variant.isStackInTags(stack))
                    variantItem.gbw$addVariant(variant);
            }

            if (!variantItem.gbw$getVariants().isEmpty())
                list.add(VariantItem.getVariantTooltip(variantItem));
        }
    }

    /*@Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/item/TooltipType;isAdvanced()Z", ordinal = 1))
    private void gbw$appendItemInformationTooltips(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir, @Local List<Text> list) {
        ItemStack stack = (ItemStack) (Object) this;
        if (!type.isAdvanced() && stack.isDamaged()) {
            list.add(Text.translatable("item.durability", stack.getMaxDamage() - stack.getDamage(), stack.getMaxDamage()).formatted(Formatting.GRAY));
        }

        if (!type.isAdvanced() && ArchitectsAssemblyClient.shouldExpandTooltips()) {
            FoodComponent component;
            CreoFoodComponent creoFoodComponent;
            if ((component = stack.get(DataComponentTypes.FOOD)) != null) {
                list.add(Text.translatable("item.hunger", component.nutrition()).formatted(Formatting.GRAY));
            } else if ((creoFoodComponent = stack.get(CreoDataComponentTypes.FOOD)) != null) {
                list.add(Text.translatable("item.hunger", creoFoodComponent.nutrition()).formatted(Formatting.GRAY));
            }

            ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
            if (stack.getItem() instanceof ArmorItem armorItem) {
                appendValue(list, armorItem.getProtection(), "item.armor");
                if (stack.isOf(Items.TURTLE_HELMET)) {
                    list.add(Text.translatable("item.air", "10s").formatted(Formatting.GRAY));
                }
            } else if (toolComponent != null) {
                appendValue(list, toolComponent.defaultMiningSpeed(), "item.mining_speed");
                appendValue(list, toolComponent.getAttackDamage(), "item.damage");
            } else if (stack.getItem() instanceof SwordItem swordItem) {
                appendValue(list, swordItem.getAttackDamage(), "item.damage");
            } else if (stack.getItem() instanceof TridentItem tridentItem) {
                appendValue(list, (float) tridentItem.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_DAMAGE).stream().findFirst().get().getValue(), "item.damage");
            } else if (stack.getItem() instanceof HorseArmorItem horseArmorItem) {
                appendValue(list, horseArmorItem.getBonus(), "item.armor");
            }
        }
    }

    @ModifyExpressionValue(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isSectionVisible(ILnet/minecraft/item/ItemStack$TooltipSection;)Z", ordinal = 4))
    private boolean gbw$ignoreTooltipModifiers(boolean original) {
        return false;
    }*/

    @Unique
    private static void appendValue(List<Text> lines, float value, String translationKey) {
        if (value == 0f)
            return;

        if (value == (int) value)
            lines.add(Text.translatable(translationKey, (int) value).formatted(Formatting.GRAY));
        else lines.add(Text.translatable(translationKey, value).formatted(Formatting.GRAY));
    }
}
