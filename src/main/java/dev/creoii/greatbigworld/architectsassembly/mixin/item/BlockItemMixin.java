package dev.creoii.greatbigworld.architectsassembly.mixin.item;

import dev.creoii.greatbigworld.architectsassembly.util.ArchitectsAssemblyTags;
import dev.creoii.greatbigworld.architectsassembly.util.FreePlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin extends Item {
    @Shadow public abstract Block getBlock();

    public BlockItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    private void gbw$allowPlaceInCreative(ItemPlacementContext context, CallbackInfoReturnable<BlockState> cir) {
        BlockState state = getBlock().getPlacementState(context);
        if (context.getPlayer() != null && state != null && context.getPlayer().isCreative() && context.getStack().isIn(ArchitectsAssemblyTags.FREE_PLACEABLE_BLOCKS)) {
            if (context.getPlayer() instanceof FreePlacer freePlacer && freePlacer.gbw$hasFreePlacement())
                cir.setReturnValue(state);
        }
    }

    @Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void gbw$dontNotifyInCreative(ItemPlacementContext context, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (context.getPlayer() != null && state != null && context.getPlayer().isCreative() && context.getStack().isIn(ArchitectsAssemblyTags.FREE_PLACEABLE_BLOCKS)) {
            if (context.getPlayer() instanceof FreePlacer freePlacer && freePlacer.gbw$hasFreePlacement())
                cir.setReturnValue(context.getWorld().setBlockState(context.getBlockPos(), state, Block.NOTIFY_LISTENERS | Block.FORCE_STATE));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isCreative() && user.shouldCancelInteraction() && user instanceof FreePlacer freePlacer && user.getStackInHand(hand).isIn(ArchitectsAssemblyTags.FREE_PLACEABLE_BLOCKS)) {
            freePlacer.gbw$setFreePlacement(!freePlacer.gbw$hasFreePlacement());

            if (!world.isClient) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
                if (freePlacer.gbw$hasFreePlacement())
                    serverPlayer.sendMessageToClient(Text.translatable("gui.placement.switch_free_placement_on"), true);
                else
                    serverPlayer.sendMessageToClient(Text.translatable("gui.placement.switch_free_placement_off"), true);
            }
        }
        return super.use(world, user, hand);
    }
}
