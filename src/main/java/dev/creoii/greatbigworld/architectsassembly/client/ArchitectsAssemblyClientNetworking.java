package dev.creoii.greatbigworld.architectsassembly.client;

import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public final class ArchitectsAssemblyClientNetworking {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(SawmillingRecipeManager.SyncSawmillingRecipesS2C.PACKET_ID, (syncSawmillingRecipesS2C, context) -> {
            context.client().execute(() -> {
                if (context.client().level.recipeAccess() instanceof SawmillingRecipeManager sawmillingRecipeManager) {
                    sawmillingRecipeManager.gbw$setSawmillingRecipes(syncSawmillingRecipesS2C.sawmillingRecipes());
                }
            });
        });
    }
}
