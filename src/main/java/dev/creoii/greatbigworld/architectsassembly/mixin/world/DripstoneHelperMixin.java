package dev.creoii.greatbigworld.architectsassembly.mixin.world;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;

@Mixin(DripstoneUtils.class)
public abstract class DripstoneHelperMixin {
    @Shadow
    protected static void buildBaseToTipColumn(Direction direction, int height, boolean merge, Consumer<BlockState> callback) {
    }

    @Inject(method = "growPointedDripstone", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/DripstoneUtils;buildBaseToTipColumn(Lnet/minecraft/core/Direction;IZLjava/util/function/Consumer;)V"), cancellable = true)
    private static void gbw$generateFluidloggedPointedDripstone(LevelAccessor world, BlockPos pos, Direction direction, int height, boolean merge, CallbackInfo ci, @Local BlockPos.MutableBlockPos mutable) {
        buildBaseToTipColumn(direction, height, merge, (state) -> {
            if (state.is(Blocks.POINTED_DRIPSTONE)) {
                state = state.setValue(PointedDripstoneBlock.WATERLOGGED, false).setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(world.getFluidState(mutable).getType()));
            }
            world.setBlock(mutable, state, Block.UPDATE_CLIENTS);
            mutable.move(direction);
        });
        ci.cancel();
    }

    @Inject(method = "isEmptyOrWater(Lnet/minecraft/world/level/block/state/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private static void gbw$allowGenerateInLava(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(Blocks.LAVA))
            cir.setReturnValue(true);
    }

    @Inject(method = "isNeitherEmptyNorWater", at = @At("HEAD"), cancellable = true)
    private static void gbw$donyDenyGenerateInLava(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.is(Blocks.LAVA))
            cir.setReturnValue(false);
    }
}
