package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(WallBlock.class)
public class WallBlockMixin {
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/WallBlock;setDefaultState(Lnet/minecraft/block/BlockState;)V"))
    private void gbw$cancelSetDefaultState(WallBlock instance, BlockState state) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/WallBlock;getShapeMap(FFFFFF)Ljava/util/Map;"))
    private Map<BlockState, VoxelShape> gbw$cancelCreateShapeMaps(WallBlock instance, float f, float g, float h, float i, float j, float k) {
        return null;
    }
}
