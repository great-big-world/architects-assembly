package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BlockStatesLoader;
import net.minecraft.state.StateManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockStatesLoader.class)
public class BlockStatesLoaderMixin {
    @Inject(method = "loadBlockStates", at = @At("HEAD"), cancellable = true)
    private void gbw$cancelTemplates(Identifier id, StateManager<Block, BlockState> stateManager, CallbackInfo ci) {
        if (id.toString().equals("great_big_world:template_wall"))
            ci.cancel();
    }
}
