package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import com.mojang.serialization.MapCodec;
import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.client.SlabPlacementProperty;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConditionalItemModelProperties.class)
public class BooleanPropertiesMixin {
    @Shadow @Final public static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends ConditionalItemModelProperty>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void gbw$registerCustomBooleanProperties(CallbackInfo ci) {
        ID_MAPPER.put(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "slab_placement"), SlabPlacementProperty.CODEC);
    }
}
