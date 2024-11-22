package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public abstract class BlocksMixin {
    @Shadow public static Block register(String id, Block block) {
        throw new IllegalArgumentException();
    }

    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;", at = @At("HEAD"), cancellable = true)
    private static void gbw$cancelRegisterVanillaWalls(String id, Block block, CallbackInfoReturnable<Block> cir) {
        if (block instanceof WallBlock) {
            cir.setReturnValue(Registry.register(Registries.BLOCK, id, new dev.creoii.greatbigworld.architectsassembly.block.WallBlock(block.getSettings())));
        }
    }
}
