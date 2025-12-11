package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeBookCategory;

@Mixin(SearchRecipeBookCategory.class)
public class RecipeBookTypeMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static SearchRecipeBookCategory create(String internalName, int internalId, RecipeBookCategory... categories) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable private static SearchRecipeBookCategory[] $VALUES;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/client/gui/screens/recipebook/SearchRecipeBookCategory;$VALUES:[Lnet/minecraft/client/gui/screens/recipebook/SearchRecipeBookCategory;", shift = At.Shift.AFTER))
    private static void addCustomRecipeBookGroup(CallbackInfo ci) {
        ArrayList<SearchRecipeBookCategory> values = new ArrayList<>(Arrays.asList($VALUES));
        int last = values.size();

        SearchRecipeBookCategory sawmill = create("GBW_SAWMILL", last, new RecipeBookCategory());
        values.add(sawmill);

        $VALUES = values.toArray(new SearchRecipeBookCategory[0]);
    }
}
