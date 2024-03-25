package dev.creoii.greatbigworld.architectsassembly.mixin.world;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(DripstoneHelper.class)
public abstract class DripstoneHelperMixin {
    @Shadow
    protected static void getDripstoneThickness(Direction direction, int height, boolean merge, Consumer<BlockState> callback) {
    }

    @Inject(method = "generatePointedDripstone", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/util/DripstoneHelper;getDripstoneThickness(Lnet/minecraft/util/math/Direction;IZLjava/util/function/Consumer;)V"), cancellable = true)
    private static void gbw$generateFluidloggedPointedDripstone(WorldAccess world, BlockPos pos, Direction direction, int height, boolean merge, CallbackInfo ci, @Local BlockPos.Mutable mutable) {
        getDripstoneThickness(direction, height, merge, (state) -> {
            if (state.isOf(Blocks.POINTED_DRIPSTONE)) {
                state = state.with(PointedDripstoneBlock.WATERLOGGED, false).with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(world.getFluidState(mutable).getFluid()));
            }
            world.setBlockState(mutable, state, Block.NOTIFY_LISTENERS);
            mutable.move(direction);
        });
        ci.cancel();
    }

    @Inject(method = "canGenerate(Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private static void gbw$allowGenerateInLava(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(Blocks.LAVA))
            cir.setReturnValue(true);
    }

    @Inject(method = "cannotGenerate", at = @At("HEAD"), cancellable = true)
    private static void gbw$donyDenyGenerateInLava(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(Blocks.LAVA))
            cir.setReturnValue(false);
    }
}
