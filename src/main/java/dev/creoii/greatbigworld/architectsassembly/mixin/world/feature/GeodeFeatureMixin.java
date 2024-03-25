package dev.creoii.greatbigworld.architectsassembly.mixin.world.feature;

import com.mojang.serialization.Codec;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GeodeFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;

@Mixin(GeodeFeature.class)
public abstract class GeodeFeatureMixin extends Feature<GeodeFeatureConfig> {
    public GeodeFeatureMixin(Codec<GeodeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/GeodeFeature;setBlockStateIf(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Ljava/util/function/Predicate;)V", ordinal = 0))
    private void gbw$fluidLogGeodes(GeodeFeature instance, StructureWorldAccess structureWorldAccess, BlockPos blockPos, BlockState blockState, Predicate<BlockState> predicate) {
        if (blockState.contains(Fluidloggable.FLUIDLOGGED)) {
            blockState = blockState.with(Properties.WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(blockState.getFluidState().getFluid()));
        }
        setBlockStateIf(structureWorldAccess, blockPos, blockState, predicate);
    }
}
