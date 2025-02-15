package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.ToIntFunction;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin {
    @Shadow protected abstract BlockState asBlockState();

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/function/ToIntFunction;applyAsInt(Ljava/lang/Object;)I"))
    private <T> int gbw$modifyLuminanceForLavalogged(ToIntFunction<BlockState> instance, T t) {
        BlockState state = asBlockState();
        if (state.contains(Fluidloggable.FLUIDLOGGED) && state.get(Fluidloggable.FLUIDLOGGED) == FluidType.LAVA)
            return 15;
        return instance.applyAsInt(state);
    }
}
