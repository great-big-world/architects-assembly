package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.client.ArchitectsAssemblyClient;
import dev.creoii.greatbigworld.architectsassembly.variant.Variant;
import dev.creoii.greatbigworld.architectsassembly.variant.VariantItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
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
    private void gbw$appendBlockVariantTooltips(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, @Local List<Text> list) {
        ItemStack stack = (ItemStack) (Object) this;
        if (stack.isIn(ItemTags.DECORATED_POT_SHERDS)) {
            String path = Registries.ITEM.getId(stack.getItem()).getPath();

            String variant = path.substring(0, path.indexOf('_'));
            list.add(Text.literal(StringUtils.capitalize(variant)).formatted(Formatting.GRAY));
        }

        if (stack.getItem() instanceof VariantItem variantItem && !ItemStack.areEqual(stack, Raid.getOminousBanner())) {
            for (Variant variant : Variant.VARIANTS.values()) {
                if (variant.getItems().contains(variantItem) || variant.isStackInTags(stack))
                    variantItem.gbw$addVariant(variant);
            }

            if (!variantItem.gbw$getVariants().isEmpty())
                list.add(VariantItem.getVariantTooltip(variantItem));
        }
    }

    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/item/TooltipContext;isAdvanced()Z", ordinal = 2))
    private void gbw$appendItemInformationTooltips(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, @Local List<Text> list) {
        ItemStack stack = (ItemStack) (Object) this;
        if (!context.isAdvanced() && stack.isDamaged()) {
            list.add(Text.translatable("item.durability", stack.getMaxDamage() - stack.getDamage(), stack.getMaxDamage()).formatted(Formatting.GRAY));
        }

        if (!context.isAdvanced() && ArchitectsAssemblyClient.shouldExpandTooltips()) {
            if (stack.isFood()) {
                FoodComponent foodComponent = stack.getItem().getFoodComponent();
                if (foodComponent != null)
                    list.add(Text.translatable("item.hunger", foodComponent.getHunger()).formatted(Formatting.GRAY));
            }

            if (stack.getItem() instanceof ArmorItem armorItem) {
                appendValue(list, armorItem.getProtection(), "item.armor");
                if (stack.isOf(Items.TURTLE_HELMET)) {
                    list.add(Text.translatable("item.air", "10s").formatted(Formatting.GRAY));
                }
            } else if (stack.getItem() instanceof MiningToolItem miningToolItem) {
                appendValue(list, miningToolItem.miningSpeed, "item.mining_speed");
                appendValue(list, miningToolItem.getAttackDamage(), "item.damage");
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
    }

    @Unique
    private static void appendValue(List<Text> lines, float value, String translationKey) {
        if (value == 0f)
            return;

        if (value == (int) value)
            lines.add(Text.translatable(translationKey, (int) value).formatted(Formatting.GRAY));
        else
            lines.add(Text.translatable(translationKey, value).formatted(Formatting.GRAY));
    }
}
