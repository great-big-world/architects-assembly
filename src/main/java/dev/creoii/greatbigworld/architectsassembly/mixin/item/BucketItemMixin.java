package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {
    @Shadow @Final private Fluid fluid;
    @Shadow protected abstract void playEmptyingSound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos);

    @ModifyArg(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BucketItem;placeFluid(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/hit/BlockHitResult;)Z"))
    private BlockPos gbw$fixFluidloggingFilling(BlockPos pos, @Local World world, @Local PlayerEntity user, @Local BlockState blockState, @Local(ordinal = 0) BlockPos blockPos, @Local(ordinal = 1) BlockPos blockPos2) {
        return blockState.getBlock() instanceof FluidFillable fluidFillable && fluidFillable.canFillWithFluid(user, world, pos, blockState, fluid) ? blockPos : blockPos2;
    }

    @Inject(method = "placeFluid", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isClient:Z", opcode = Opcodes.GETFIELD), cancellable = true)
    private void gbw$fixFluidloggingFilling(PlayerEntity player, World world, BlockPos pos, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> cir, @Local FlowableFluid flowableFluid, @Local Block block, @Local BlockState blockState) {
        if (block instanceof FluidFillable fluidFillable) {
            fluidFillable.tryFillWithFluid(world, pos, blockState, flowableFluid.getStill(false));
            playEmptyingSound(player, world, pos);
            cir.setReturnValue(true);
        }
    }
}
