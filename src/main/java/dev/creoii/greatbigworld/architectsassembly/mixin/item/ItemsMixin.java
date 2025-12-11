package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import dev.creoii.greatbigworld.architectsassembly.item.PickaxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @Shadow
    public static Item registerItem(String id, Function<Item.Properties, Item> factory, Item.Properties settings) {
        return null;
    }

    @Shadow
    public static Item registerItem(String id, Function<Item.Properties, Item> factory) {
        return null;
    }

    /*@Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;", ordinal = 15))
    private static Item gbw$woodenPickaxe(String id, Item.Settings settings) {
        return register("wooden_pickaxe", settings1 -> new PickaxeItem(ToolMaterial.WOOD, 1f, -2.8f, settings1));
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;", ordinal = 17))
    private static Item gbw$stonePickaxe(String id, Item.Settings settings) {
        return register("stone_pickaxe", settings1 -> new PickaxeItem(ToolMaterial.STONE, 1f, -2.8f, settings1));
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;", ordinal = 19))
    private static Item gbw$goldenPickaxe(String id, Item.Settings settings) {
        return register("golden_pickaxe", settings1 -> new PickaxeItem(ToolMaterial.GOLD, 1f, -2.8f, settings1));
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;", ordinal = 21))
    private static Item gbw$ironPickaxe(String id, Item.Settings settings) {
        return register("iron_pickaxe", settings1 -> new PickaxeItem(ToolMaterial.IRON, 1f, -2.8f, settings1));
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;", ordinal = 23))
    private static Item gbw$diamondPickaxe(String id, Item.Settings settings) {
        return register("diamond_pickaxe", settings1 -> new PickaxeItem(ToolMaterial.DIAMOND, 1f, -2.8f, settings1));
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Items;register(Ljava/lang/String;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;", ordinal = 25))
    private static Item gbw$netheritePickaxe(String id, Item.Settings settings) {
        return register("netherite_pickaxe", settings1 -> new PickaxeItem(ToolMaterial.NETHERITE, 1f, -2.8f, settings1), new Item.Settings().fireproof());
    }*/
}
