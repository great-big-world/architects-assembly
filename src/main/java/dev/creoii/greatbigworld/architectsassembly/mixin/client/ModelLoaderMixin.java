package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ModelLoader.class)
public class ModelLoaderMixin {
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/state/StateManager$Builder;add([Lnet/minecraft/state/property/Property;)Lnet/minecraft/state/StateManager$Builder;"))
    private static StateManager.Builder<Block, BlockState> gbw$injectDyedProperty(StateManager.Builder<Block, BlockState> instance, Property<?>[] properties) {
        return instance.add(BooleanProperty.of("map"), BooleanProperty.of("dyed"));
    }
}
