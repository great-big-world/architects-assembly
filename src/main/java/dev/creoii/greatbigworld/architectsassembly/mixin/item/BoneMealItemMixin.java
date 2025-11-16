package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyWorldgen;
import dev.creoii.greatbigworld.architectsassembly.world.feature.MossifyVegetationPatchFeature;
import net.minecraft.block.Blocks;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoneMealItem.class)
public class BoneMealItemMixin {
    @Inject(method = "useOnFertilizable", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private static void gbw$fertilizeMossifiables(ItemStack stack, World world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (MossifyVegetationPatchFeature.MOSSY_CONVERSIONS.containsKey(world.getBlockState(pos).getBlock()) && isNextToMoss(world, pos)) {
            world.getRegistryManager().getOptional(RegistryKeys.CONFIGURED_FEATURE).flatMap(key -> {
                return key.getOptional(ArchitectsAssemblyWorldgen.MOSSIFY_PATCH_BONEMEAL);
            }).ifPresent(entry -> {
                ServerWorld serverWorld = (ServerWorld) world;
                entry.value().generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), world.getRandom(), pos.up());
            });
            if (!world.isClient())
                stack.decrement(1);
            cir.setReturnValue(true);
        }
    }

    @Unique
    private static boolean isNextToMoss(World world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (world.getBlockState(pos.offset(direction)).isOf(Blocks.MOSS_BLOCK))
                return true;
        }
        return false;
    }
}
