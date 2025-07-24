package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.model.BlockStateManagers;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockStateManagers.class)
public class BlockStateManagersMixin {
    @Inject(method = "createItemFrameStateManager", at = @At("RETURN"), cancellable = true)
    private static void gbw$redirectNormalFrames(CallbackInfoReturnable<StateManager<Block, BlockState>> cir) {
        StateManager.Builder<Block, BlockState> builder = new StateManager.Builder<>(Blocks.AIR);
        builder.add(Properties.MAP, ExtendedItemFrame.DYED);
        cir.setReturnValue(builder.build(Block::getDefaultState, BlockState::new));
    }
}
