package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.client.resources.model.BlockStateDefinitions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockStateDefinitions.class)
public class BlockStateManagersMixin {
    @Inject(method = "createItemFrameFakeState", at = @At("RETURN"), cancellable = true)
    private static void gbw$redirectNormalFrames(CallbackInfoReturnable<StateDefinition<Block, BlockState>> cir) {
        StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder<>(Blocks.AIR);
        builder.add(BlockStateProperties.MAP, ExtendedItemFrame.DYED);
        cir.setReturnValue(builder.create(Block::defaultBlockState, BlockState::new));
    }
}
