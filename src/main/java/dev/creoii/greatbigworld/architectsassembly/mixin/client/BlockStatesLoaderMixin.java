package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import net.minecraft.client.render.model.BlockStatesLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockStatesLoader.class)
public class BlockStatesLoaderMixin {
    @Mutable @Shadow @Final public static ModelIdentifier MAP_GLOW_ITEM_FRAME_MODEL_ID;
    @Mutable @Shadow @Final public static ModelIdentifier GLOW_ITEM_FRAME_MODEL_ID;
    @Mutable @Shadow @Final public static ModelIdentifier MAP_ITEM_FRAME_MODEL_ID;
    @Mutable @Shadow @Final public static ModelIdentifier ITEM_FRAME_MODEL_ID;
    @Shadow @Final private static Identifier ITEM_FRAME_ID;
    @Shadow @Final private static Identifier GLOW_ITEM_FRAME_ID;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void gbw$redirectNormalFrames(CallbackInfo ci) {
        MAP_GLOW_ITEM_FRAME_MODEL_ID = new ModelIdentifier(GLOW_ITEM_FRAME_ID, "dyed=false,map=true");
        GLOW_ITEM_FRAME_MODEL_ID = new ModelIdentifier(GLOW_ITEM_FRAME_ID, "dyed=false,map=false");
        MAP_ITEM_FRAME_MODEL_ID = new ModelIdentifier(ITEM_FRAME_ID, "dyed=false,map=true");
        ITEM_FRAME_MODEL_ID = new ModelIdentifier(ITEM_FRAME_ID, "dyed=false,map=false");
    }
}
