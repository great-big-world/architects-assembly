package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.state.ConsolidatedStateManagers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.State;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Shadow protected abstract void appendProperties(StateManager.Builder<Block, BlockState> builder);
    @Shadow protected abstract void setDefaultState(BlockState state);

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/DefaultedRegistry;createEntry(Ljava/lang/Object;)Lnet/minecraft/registry/entry/RegistryEntry$Reference;"))
    private RegistryEntry.Reference<Block> gbw$cancelRegistryEntryForWalls(DefaultedRegistry<Block> instance, Object object) {
        Block block = (Block) (Object) this;
        if (block instanceof WallBlock) {
            return null;
        }
        return instance.createEntry(block);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;appendProperties(Lnet/minecraft/state/StateManager$Builder;)V"))
    private void gbw$cancelAppendPropertiesForWalls(Block instance, StateManager.Builder<Block, BlockState> builder) {
        if (!(instance instanceof WallBlock)) {
            appendProperties(builder);
        }
    }

    @SuppressWarnings("unchecked")
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/state/StateManager$Builder;build(Ljava/util/function/Function;Lnet/minecraft/state/StateManager$Factory;)Lnet/minecraft/state/StateManager;"))
    private <O, S extends State<O, S>> StateManager<O, S> gbw$cancelBuildStateManagerForWalls(StateManager.Builder<O, S> instance, Function<O, S> defaultStateGetter, StateManager.Factory<O, S> factory) {
        Block block = (Block) (Object) this;
        if (block instanceof WallBlock) {
            return null;
        } else if (block instanceof dev.creoii.greatbigworld.architectsassembly.block.WallBlock wallBlock) {
            if (wallBlock.isTemplate()) {
                return instance.build(defaultStateGetter, factory);
            } else return (StateManager<O, S>) ConsolidatedStateManagers.WALL_MANAGER.getStateManager();
        } else return instance.build(defaultStateGetter, factory);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;setDefaultState(Lnet/minecraft/block/BlockState;)V"))
    private void gbw$cancelSetDefaultStateForWalls(Block instance, BlockState state) {
        if (!(instance instanceof WallBlock)) {
            setDefaultState(state);
        }
    }
}
