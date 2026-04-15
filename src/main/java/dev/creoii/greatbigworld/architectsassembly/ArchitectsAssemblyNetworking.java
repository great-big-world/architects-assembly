package dev.creoii.greatbigworld.architectsassembly;

import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public final class ArchitectsAssemblyNetworking {
    public static void register() {
        PayloadTypeRegistry.playS2C().register(SawmillingRecipeManager.SyncSawmillingRecipesS2C.PACKET_ID, SawmillingRecipeManager.SyncSawmillingRecipesS2C.STREAM_CODEC);
    }
}
