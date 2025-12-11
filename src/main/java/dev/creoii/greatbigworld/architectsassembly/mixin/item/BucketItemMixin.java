package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {
    @Shadow @Final private Fluid content;
    @Shadow protected abstract void playEmptySound(@Nullable LivingEntity user, LevelAccessor world, BlockPos pos);

    @ModifyArg(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BucketItem;emptyContents(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;)Z"))
    private BlockPos gbw$fixFluidloggingFilling(BlockPos pos, @Local(argsOnly = true) Level world, @Local(argsOnly = true) Player user, @Local BlockState blockState, @Local(ordinal = 0) BlockPos blockPos, @Local(ordinal = 1) BlockPos blockPos2) {
        return blockState.getBlock() instanceof LiquidBlockContainer fluidFillable && fluidFillable.canPlaceLiquid(user, world, pos, blockState, content) ? blockPos : blockPos2;
    }

    @Inject(method = "emptyContents", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isClientSide()Z"), cancellable = true)
    private void gbw$fixFluidloggingFilling(LivingEntity user, Level world, BlockPos pos, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> cir, @Local FlowingFluid flowableFluid, @Local Block block, @Local BlockState blockState) {
        if (block instanceof LiquidBlockContainer fluidFillable) {
            fluidFillable.placeLiquid(world, pos, blockState, flowableFluid.getSource(false));
            playEmptySound(user, world, pos);
            cir.setReturnValue(true);
        }
    }
}
