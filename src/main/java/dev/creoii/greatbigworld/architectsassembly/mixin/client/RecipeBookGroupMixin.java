package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import net.minecraft.client.recipebook.RecipeBookType;
import net.minecraft.recipe.book.RecipeBookCategory;
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

@Mixin(RecipeBookType.class)
public class RecipeBookGroupMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static RecipeBookType create(String internalName, int internalId, RecipeBookCategory... categories) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable private static RecipeBookType[] field_25767;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/client/recipebook/RecipeBookType;field_54842:[Lnet/minecraft/client/recipebook/RecipeBookType;", shift = At.Shift.AFTER))
    private static void addCustomRecipeBookGroup(CallbackInfo ci) {
        ArrayList<RecipeBookType> values = new ArrayList<>(Arrays.asList(field_25767));
        int last = values.size();

        RecipeBookType sawmill = create("GBW_SAWMILL", last, new RecipeBookCategory());
        values.add(sawmill);

        field_25767 = values.toArray(new RecipeBookType[0]);
    }
}
