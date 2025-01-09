package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Items.class)
public class ItemsMixin {
    @Inject(method = "register(Lnet/minecraft/item/BlockItem;)Lnet/minecraft/item/Item;", at = @At("HEAD"))
    private static void gbw$cancelRegisterVanillaWalls(BlockItem item, CallbackInfoReturnable<Item> cir) {
        if (item.getBlock() instanceof WallBlock) {
            Identifier id = Identifier.of(GreatBigWorld.NAMESPACE, Registries.BLOCK.getId(item.getBlock()).getPath());
            Registry.register(Registries.ITEM, id, new BlockItem(Registries.BLOCK.get(id), new Item.Settings()));
        }
    }
}
