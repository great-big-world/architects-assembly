package dev.creoii.greatbigworld.architectsassembly.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.stats.ServerRecipeBook;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerList.class)
public class PlayerListMixin {
    @Shadow @Final private List<ServerPlayer> players;

    @WrapOperation(method = "reloadResources", at = @At(value = "INVOKE", target = "Lnet/minecraft/stats/ServerRecipeBook;sendInitialRecipeBook(Lnet/minecraft/server/level/ServerPlayer;)V", ordinal = 0))
    private void gbw$cancelFirstRecipeBookSync(ServerRecipeBook instance, ServerPlayer serverPlayer, Operation<Void> original) {
        // cancel to do this *after* syncing custom sawmilling recipes
    }

    @Inject(method = "reloadResources", at = @At("TAIL"))
    private void gbw$reloadSawmillingRecipes(CallbackInfo ci, @Local RecipeManager recipeManager) {
        SawmillingRecipeManager.SyncSawmillingRecipesS2C packet = null;
        if (recipeManager instanceof SawmillingRecipeManager sawmillingRecipeManager) {
            packet = new SawmillingRecipeManager.SyncSawmillingRecipesS2C(sawmillingRecipeManager.gbw$getSawmillingRecipes());
        }

        for (ServerPlayer serverPlayer : players) {
            if (packet != null) {
                ServerPlayNetworking.send(serverPlayer, packet);
            }

            serverPlayer.getRecipeBook().sendInitialRecipeBook(serverPlayer);
        }
    }
}
