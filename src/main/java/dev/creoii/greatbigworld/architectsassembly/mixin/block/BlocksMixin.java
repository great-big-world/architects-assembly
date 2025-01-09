package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public abstract class BlocksMixin {
    @Shadow public static Block register(String id, Block block) {
        throw new IllegalArgumentException();
    }

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void gbw$initStateManagers(CallbackInfo ci) {
        ArchitectsAssembly.CONSOLIDATED_STATE_MANAGERS.getWallManager().init();
    }

    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;", at = @At("HEAD"))
    private static void gbw$cancelRegisterVanillaWalls(String id, Block block, CallbackInfoReturnable<Block> cir) {
        if (block instanceof WallBlock) {
            Registry.register(Registries.BLOCK, Identifier.of(GreatBigWorld.NAMESPACE, id), new dev.creoii.greatbigworld.architectsassembly.block.WallBlock(block.getSettings()));
        }
    }
}
