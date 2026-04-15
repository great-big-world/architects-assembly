package dev.creoii.greatbigworld.architectsassembly.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyRecipeBookTypes;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedRecipeBookSettings;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.stats.RecipeBookSettings;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.UnaryOperator;

@Mixin(RecipeBookSettings.class)
public class RecipeBookSettingsMixin implements ExtendedRecipeBookSettings {
    @Mutable @Shadow @Final public static StreamCodec<FriendlyByteBuf, RecipeBookSettings> STREAM_CODEC;
    @Mutable @Shadow @Final public static MapCodec<RecipeBookSettings> MAP_CODEC;
    @Unique private RecipeBookSettings.TypeSettings kiln;

    @Override
    public RecipeBookSettings.TypeSettings great_big_world$kiln() {
        return kiln;
    }

    @Override
    public void great_big_world$setKiln(RecipeBookSettings.TypeSettings kiln) {
        this.kiln = kiln;
    }

    @Inject(method = "<init>()V", at = @At("TAIL"))
    private void gbw$initBaseKilnSettings(CallbackInfo ci) {
        kiln = RecipeBookSettings.TypeSettings.DEFAULT;
    }

    @Inject(method = "getSettings", at = @At("HEAD"), cancellable = true)
    private void gbw$injectKilnGetSettings(RecipeBookType recipeBookType, CallbackInfoReturnable<RecipeBookSettings.TypeSettings> cir) {
        if (recipeBookType == ArchitectsAssemblyRecipeBookTypes.KILN)
            cir.setReturnValue(kiln);
    }

    @Inject(method = "updateSettings", at = @At("HEAD"), cancellable = true)
    private void gbw$injectKilnUpdateSettings(RecipeBookType recipeBookType, UnaryOperator<RecipeBookSettings.TypeSettings> unaryOperator, CallbackInfo ci) {
        if (recipeBookType == ArchitectsAssemblyRecipeBookTypes.KILN) {
            kiln = unaryOperator.apply(kiln);
            ci.cancel();
        }
    }

    @Inject(method = "replaceFrom", at = @At("HEAD"))
    private void gbw$injectKilnReplaceFrom(RecipeBookSettings recipeBookSettings, CallbackInfo ci) {
        kiln = ((ExtendedRecipeBookSettings) (Object) recipeBookSettings).great_big_world$kiln();
    }

    @ModifyReturnValue(method = "copy", at = @At("RETURN"))
    private RecipeBookSettings gbw$fixCopyForKiln(RecipeBookSettings original) {
        if ((Object) original instanceof ExtendedRecipeBookSettings extendedRecipeBookSettings) {
            extendedRecipeBookSettings.great_big_world$setKiln(kiln);
        }
        return original;
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void gbw$fixCodecs(CallbackInfo ci) {
        STREAM_CODEC = StreamCodec.composite(RecipeBookSettings.TypeSettings.STREAM_CODEC, (recipeBookSettings) -> recipeBookSettings.crafting, RecipeBookSettings.TypeSettings.STREAM_CODEC, (recipeBookSettings) -> recipeBookSettings.furnace, RecipeBookSettings.TypeSettings.STREAM_CODEC, (recipeBookSettings) -> recipeBookSettings.blastFurnace, RecipeBookSettings.TypeSettings.STREAM_CODEC, (recipeBookSettings) -> recipeBookSettings.smoker, RecipeBookSettings.TypeSettings.STREAM_CODEC, (recipeBookSettings) -> ((ExtendedRecipeBookSettings) (Object) recipeBookSettings).great_big_world$kiln(), (typeSettings, typeSettings2, typeSettings3, typeSettings4, typeSettings5) -> {
            RecipeBookSettings recipeBookSettings = new RecipeBookSettings(typeSettings, typeSettings2, typeSettings3, typeSettings4);
            ((ExtendedRecipeBookSettings) (Object) recipeBookSettings).great_big_world$setKiln(typeSettings5);
            return recipeBookSettings;
        });
        MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> {
            return instance.group(
                    RecipeBookSettings.TypeSettings.CRAFTING_MAP_CODEC.forGetter((recipeBookSettings) -> recipeBookSettings.crafting),
                    RecipeBookSettings.TypeSettings.FURNACE_MAP_CODEC.forGetter((recipeBookSettings) -> recipeBookSettings.furnace),
                    RecipeBookSettings.TypeSettings.BLAST_FURNACE_MAP_CODEC.forGetter((recipeBookSettings) -> recipeBookSettings.blastFurnace),
                    RecipeBookSettings.TypeSettings.SMOKER_MAP_CODEC.forGetter((recipeBookSettings) -> recipeBookSettings.smoker),
                    ExtendedRecipeBookSettings.KILN_MAP_CODEC.forGetter((recipeBookSettings) -> ((ExtendedRecipeBookSettings) (Object) recipeBookSettings).great_big_world$kiln())
            ).apply(instance, (typeSettings, typeSettings2, typeSettings3, typeSettings4, typeSettings5) -> {
                RecipeBookSettings recipeBookSettings = new RecipeBookSettings(typeSettings, typeSettings2, typeSettings3, typeSettings4);
                ((ExtendedRecipeBookSettings) (Object) recipeBookSettings).great_big_world$setKiln(typeSettings5);
                return recipeBookSettings;
            });
        });
    }
}
