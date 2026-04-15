package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import dev.creoii.greatbigworld.architectsassembly.recipe.SawmillingRecipe;
import dev.creoii.greatbigworld.architectsassembly.util.SawmillingRecipeManager;
import net.minecraft.client.multiplayer.ClientRecipeContainer;
import net.minecraft.world.item.crafting.SelectableRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ClientRecipeContainer.class)
public abstract class ClientRecipeContainerMixin implements SawmillingRecipeManager {
    @Unique
    private SelectableRecipe.SingleInputSet<SawmillingRecipe> sawmillingRecipes = SelectableRecipe.SingleInputSet.empty();

    @Override
    public SelectableRecipe.SingleInputSet<SawmillingRecipe> gbw$getSawmillingRecipes() {
        return sawmillingRecipes;
    }

    @Override
    public void gbw$setSawmillingRecipes(SelectableRecipe.SingleInputSet<SawmillingRecipe> sawmillingRecipes) {
        this.sawmillingRecipes = sawmillingRecipes;
    }
}
