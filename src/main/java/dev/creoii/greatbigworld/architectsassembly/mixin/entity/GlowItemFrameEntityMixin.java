package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.entity.decoration.GlowItemFrameEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GlowItemFrameEntity.class)
public abstract class GlowItemFrameEntityMixin implements ExtendedItemFrame {
}
