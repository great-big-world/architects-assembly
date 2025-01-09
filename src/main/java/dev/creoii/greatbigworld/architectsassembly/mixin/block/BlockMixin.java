package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import dev.creoii.greatbigworld.architectsassembly.block.TemplateWallBlock;
import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.State;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(Block.class)
public abstract class BlockMixin {
    /**
     * Allow instantiating new WallBlock objects without registering them
     */
    /*@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/DefaultedRegistry;createEntry(Ljava/lang/Object;)Lnet/minecraft/registry/entry/RegistryEntry$Reference;"))
    private RegistryEntry.Reference<Block> gbw$cancelRegistryEntryForWalls(DefaultedRegistry<Block> instance, Object object) {
        Block block = (Block) (Object) this;
        if (block instanceof WallBlock) {
            return null;
        }
        return instance.createEntry(block);
    }*/

    @SuppressWarnings("unchecked")
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/state/StateManager$Builder;build(Ljava/util/function/Function;Lnet/minecraft/state/StateManager$Factory;)Lnet/minecraft/state/StateManager;"))
    private <O, S extends State<O, S>> StateManager<O, S> gbw$cancelBuildStateManagerForWalls(StateManager.Builder<O, S> instance, Function<O, S> defaultStateGetter, StateManager.Factory<O, S> factory) {
        Block block = (Block) (Object) this;
        if (block instanceof dev.creoii.greatbigworld.architectsassembly.block.WallBlock && !(block instanceof TemplateWallBlock)) {
            return (StateManager<O, S>) ArchitectsAssembly.CONSOLIDATED_STATE_MANAGERS.getWallManager().getStateManager();
        } else return instance.build(defaultStateGetter, factory);
    }
}
