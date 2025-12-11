package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyWorldgen;
import dev.creoii.greatbigworld.architectsassembly.world.feature.MossifyVegetationPatchFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
    @Inject(method = "growCrop", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private static void gbw$fertilizeMossifiables(ItemStack stack, Level world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (MossifyVegetationPatchFeature.MOSSY_CONVERSIONS.containsKey(world.getBlockState(pos).getBlock()) && isNextToMoss(world, pos)) {
            world.registryAccess().lookup(Registries.CONFIGURED_FEATURE).flatMap(key -> {
                return key.get(ArchitectsAssemblyWorldgen.MOSSIFY_PATCH_BONEMEAL);
            }).ifPresent(entry -> {
                ServerLevel serverWorld = (ServerLevel) world;
                entry.value().place(serverWorld, serverWorld.getChunkSource().getGenerator(), world.getRandom(), pos.above());
            });
            if (!world.isClientSide())
                stack.shrink(1);
            cir.setReturnValue(true);
        }
    }

    @Unique
    private static boolean isNextToMoss(Level world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (world.getBlockState(pos.relative(direction)).is(Blocks.MOSS_BLOCK))
                return true;
        }
        return false;
    }
}
