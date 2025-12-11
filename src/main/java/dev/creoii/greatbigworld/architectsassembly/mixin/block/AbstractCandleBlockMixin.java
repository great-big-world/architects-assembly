package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractCandleBlock.class)
public class AbstractCandleBlockMixin {
    @Redirect(method = "addParticlesAndSound", at = @At(value = "FIELD", target = "Lnet/minecraft/core/particles/ParticleTypes;SMALL_FLAME:Lnet/minecraft/core/particles/SimpleParticleType;"))
    private static SimpleParticleType gbw$spawnSoulFlamesOnSoulSand(@Local(argsOnly = true) Level world, @Local(argsOnly = true) Vec3 vec3d) {
        BlockPos pos = BlockPos.containing(vec3d);
        if (world.getBlockState(pos.below()).is(BlockTags.SOUL_FIRE_BASE_BLOCKS)) {
            return ArchitectsAssemblyParticleTypes.SMALL_SOUL_FIRE_FLAME;
        } else return ParticleTypes.SMALL_FLAME;
    }
}
