package dev.creoii.greatbigworld.architectsassembly.mixin;

import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import dev.creoii.greatbigworld.architectsassembly.util.state.ConsolidatedStateManagers;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class BootstrapMixin {
    @Inject(method = "initialize", at = @At(value = "INVOKE", target = "Ljava/time/Instant;now()Ljava/time/Instant;"))
    private static void gbw$earlyLoad(CallbackInfo ci) {
        ArchitectsAssembly.CONSOLIDATED_STATE_MANAGERS = new ConsolidatedStateManagers();
    }
}
