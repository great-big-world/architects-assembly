package dev.creoii.greatbigworld.architectsassembly.util;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.SelectableRecipe;

public interface SawmillingRecipeManager {
    SelectableRecipe.SingleInputSet<SawmillingRecipe> gbw$getSawmillingRecipes();

    void gbw$setSawmillingRecipes(SelectableRecipe.SingleInputSet<SawmillingRecipe> sawmillingRecipes);

    record SyncSawmillingRecipesS2C(SelectableRecipe.SingleInputSet<SawmillingRecipe> sawmillingRecipes) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<SyncSawmillingRecipesS2C> PACKET_ID = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sync_sawmilling_recipes"));
        public static final StreamCodec<RegistryFriendlyByteBuf, SyncSawmillingRecipesS2C> STREAM_CODEC = StreamCodec.composite(SelectableRecipe.SingleInputSet.noRecipeCodec(), SyncSawmillingRecipesS2C::sawmillingRecipes, SyncSawmillingRecipesS2C::new);

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return PACKET_ID;
        }
    }
}
