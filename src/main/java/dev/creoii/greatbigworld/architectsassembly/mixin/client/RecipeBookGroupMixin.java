package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

@Mixin(RecipeBookGroup.class)
public class RecipeBookGroupMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static RecipeBookGroup create(String internalName, int internalId, ItemStack... entries) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable private static RecipeBookGroup[] field_1805;

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/client/recipebook/RecipeBookGroup;field_1805:[Lnet/minecraft/client/recipebook/RecipeBookGroup;", shift = At.Shift.AFTER))
    private static void addCustomRecipeBookGroup(CallbackInfo ci) {
        ArrayList<RecipeBookGroup> values = new ArrayList<>(Arrays.asList(field_1805));
        int last = values.size();

        RecipeBookGroup sawmill = create("GBW_SAWMILL", last, new ItemStack(Items.OAK_PLANKS));
        values.add(sawmill);

        field_1805 = values.toArray(new RecipeBookGroup[0]);
    }
}
