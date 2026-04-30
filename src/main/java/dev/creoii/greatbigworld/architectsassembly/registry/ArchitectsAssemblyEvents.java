package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;

public final class ArchitectsAssemblyEvents {
    public static void register() {
        DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
            modifyContext.modify(Items.IRON_AXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_PICKAXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_SHOVEL, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_HOE, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_SWORD, builder -> builder.set(DataComponents.MAX_DAMAGE, 451));
            modifyContext.modify(Items.IRON_HELMET, builder -> builder.set(DataComponents.MAX_DAMAGE, 265));
            modifyContext.modify(Items.IRON_CHESTPLATE, builder -> builder.set(DataComponents.MAX_DAMAGE, 340));
            modifyContext.modify(Items.IRON_LEGGINGS, builder -> builder.set(DataComponents.MAX_DAMAGE, 325));
            modifyContext.modify(Items.IRON_BOOTS, builder -> builder.set(DataComponents.MAX_DAMAGE, 295));

            modifyContext.modify(Items.COPPER_AXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_PICKAXE, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_SHOVEL, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_HOE, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_SWORD, builder -> builder.set(DataComponents.MAX_DAMAGE, 221));
            modifyContext.modify(Items.COPPER_HELMET, builder -> builder.set(DataComponents.MAX_DAMAGE, 130)); // drop
            modifyContext.modify(Items.COPPER_CHESTPLATE, builder -> builder.set(DataComponents.MAX_DAMAGE, 170)); // drop
            modifyContext.modify(Items.COPPER_LEGGINGS, builder -> builder.set(DataComponents.MAX_DAMAGE, 160)); // drop
            modifyContext.modify(Items.COPPER_BOOTS, builder -> builder.set(DataComponents.MAX_DAMAGE, 145)); // drop
        });

        ServerPlayerEvents.JOIN.register(player -> {
            ServerPlayNetworking.send(player, new SawmillingRecipeManager.SyncSawmillingRecipesS2C(((SawmillingRecipeManager) player.level().getServer().getRecipeManager()).gbw$getSawmillingRecipes()));
        });
    }
}
