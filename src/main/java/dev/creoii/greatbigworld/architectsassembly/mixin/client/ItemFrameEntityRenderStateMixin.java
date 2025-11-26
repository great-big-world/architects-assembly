package dev.creoii.greatbigworld.architectsassembly.mixin.client;

import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.client.render.entity.state.ItemFrameEntityRenderState;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemFrameEntityRenderState.class)
public class ItemFrameEntityRenderStateMixin implements ExtendedItemFrame {
    @Unique private DyeColor color;

    @Override
    public @Nullable DyeColor gbw$getColor() {
        return color;
    }

    @Override
    public void gbw$setColor(@Nullable DyeColor color) {
        this.color = color;
    }
}
