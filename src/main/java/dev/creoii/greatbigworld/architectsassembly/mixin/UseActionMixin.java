package dev.creoii.greatbigworld.architectsassembly.mixin;

import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(UseAction.class)
public class UseActionMixin {
    @Invoker("<init>")
    private static UseAction init(String name, int id) {
        throw new AssertionError();
    }

    @Shadow @Final @Mutable private static UseAction[] field_8948;

    static {
        ArrayList<UseAction> values = new ArrayList<>(Arrays.asList(field_8948));
        int last = values.size();

        values.add(init("TOOL", last));

        field_8948 = values.toArray(new UseAction[0]);
    }
}
