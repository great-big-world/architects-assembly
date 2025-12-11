package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.block.enums.FluidType;
import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.ToIntFunction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class AbstractBlockStateMixin {
    @Shadow protected abstract BlockState asState();

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/function/ToIntFunction;applyAsInt(Ljava/lang/Object;)I"))
    private <T> int gbw$modifyLuminanceForLavalogged(ToIntFunction<BlockState> instance, T t) {
        BlockState state = asState();
        if (state.hasProperty(Fluidloggable.FLUIDLOGGED) && state.getValue(Fluidloggable.FLUIDLOGGED) == FluidType.LAVA)
            return 15;
        return instance.applyAsInt(state);
    }
}
