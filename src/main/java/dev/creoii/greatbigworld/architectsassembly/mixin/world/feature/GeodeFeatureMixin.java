package dev.creoii.greatbigworld.architectsassembly.mixin.world.feature;

import com.mojang.serialization.Codec;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.GeodeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;

@Mixin(GeodeFeature.class)
public abstract class GeodeFeatureMixin extends Feature<GeodeConfiguration> {
    public GeodeFeatureMixin(Codec<GeodeConfiguration> configCodec) {
        super(configCodec);
    }

    @Redirect(method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/GeodeFeature;safeSetBlock(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Ljava/util/function/Predicate;)V", ordinal = 0))
    private void gbw$fluidLogGeodes(GeodeFeature instance, WorldGenLevel structureWorldAccess, BlockPos blockPos, BlockState blockState, Predicate<BlockState> predicate) {
        if (blockState.hasProperty(Fluidloggable.FLUIDLOGGED)) {
            blockState = blockState.setValue(BlockStateProperties.WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(blockState.getFluidState().getType()));
        }
        safeSetBlock(structureWorldAccess, blockPos, blockState, predicate);
    }
}
