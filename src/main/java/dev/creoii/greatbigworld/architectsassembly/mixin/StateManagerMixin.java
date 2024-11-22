package dev.creoii.greatbigworld.architectsassembly.mixin;

import dev.creoii.greatbigworld.architectsassembly.block.WallBlock;
import net.minecraft.registry.Registries;
import net.minecraft.state.State;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.function.Function;

@Mixin(StateManager.class)
public class StateManagerMixin<O, S extends State<O, S>> {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void gbw$debug(Function<O, S> defaultStateGetter, Object owner, StateManager.Factory<O, S> factory, Map<O, S> propertiesMap, CallbackInfo ci) {
        /*if (owner instanceof WallBlock wallBlock) {
            System.out.println(Registries.BLOCK.getId(wallBlock));
        } else if (owner instanceof net.minecraft.block.WallBlock wallBlock) {
            System.out.println(Registries.BLOCK.getId(wallBlock));
        }*/
    }
}
