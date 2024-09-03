package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyParticleTypes;
import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractCandleBlock.class)
public class AbstractCandleBlockMixin {
    @Redirect(method = "spawnCandleParticles", at = @At(value = "FIELD", target = "Lnet/minecraft/particle/ParticleTypes;SMALL_FLAME:Lnet/minecraft/particle/SimpleParticleType;"))
    private static SimpleParticleType gbw$spawnSoulFlamesOnSoulSand(@Local(argsOnly = true) World world, @Local(argsOnly = true) Vec3d vec3d) {
        BlockPos pos = BlockPos.ofFloored(vec3d);
        if (world.getBlockState(pos.down()).isIn(BlockTags.SOUL_FIRE_BASE_BLOCKS)) {
            return ArchitectsAssemblyParticleTypes.SMALL_SOUL_FIRE_FLAME;
        } else return ParticleTypes.SMALL_FLAME;
    }
}
