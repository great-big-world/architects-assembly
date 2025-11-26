package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyItems;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import dev.creoii.greatbigworld.util.network.SyncWorldEventS2C;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntityMixin extends AbstractDecorationEntity implements ExtendedItemFrame {
    @Unique private static final int NO_COLOR = -1;
    @Unique private static final TrackedData<Integer> COLOR = DataTracker.registerData(ItemFrameEntity.class, TrackedDataHandlerRegistry.INTEGER);

    protected ItemFrameEntityMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void gbw$trackData(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(COLOR, NO_COLOR);
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void gbw$writeDataToNbt(WriteView view, CallbackInfo ci) {
        DyeColor color = gbw$getColor();
        if (color == null)
            view.putInt("Color", NO_COLOR);
        else
            view.putInt("Color", color.getIndex());
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void gbw$readColorFromNbt(ReadView view, CallbackInfo ci) {
        int color = view.getInt("Color", 0);
        if (color >= 0 && color <= 15)
            gbw$setColor(DyeColor.byIndex(color));
        else gbw$setColor(null);
    }

    @ModifyReturnValue(method = "interact", at = @At(value = "RETURN", ordinal = 0))
    private ActionResult gbw$handleWaxedItemFrames(ActionResult original, @Local(argsOnly = true) PlayerEntity player, @Local(argsOnly = true) Hand hand, @Local ItemStack itemStack) {
        if (itemStack.isIn(ItemTags.AXES) && player.isSneaking()) {
            return unwax((ItemFrameEntity) (Object) this, player, hand, itemStack);
        }
        return ActionResult.FAIL;
    }

    @ModifyConstant(method = "interact", constant = @Constant(intValue = 1))
    private int gbw$handleBackwardsRotation(int constant, @Local(argsOnly = true) PlayerEntity player) {
        return player.isSneaking() ? -constant : constant;
    }

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;setHeldItemStack(Lnet/minecraft/item/ItemStack;)V"), cancellable = true)
    private void gbw$handleItemInteractionEmpty(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, @Local ItemStack itemStack) {
        if (player.isSneaking()) {
            if (itemStack.isOf(Items.HONEYCOMB)) {
                cir.setReturnValue(wax((ItemFrameEntity) (Object) this, player, itemStack));
            } else if (itemStack.getItem() instanceof DyeItem dyeItem) {
                getEntityWorld().playSound(player, getBlockPos(), SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1f, 1f);
                gbw$setColor(dyeItem.getColor());
                itemStack.decrementUnlessCreative(1, player);
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"), cancellable = true)
    private void gbw$handleItemInteractionNonEmpty(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (player.isSneaking()) {
            if (itemStack.isOf(Items.HONEYCOMB)) {
                cir.setReturnValue(wax((ItemFrameEntity) (Object) this, player, itemStack));
            } else if (itemStack.getItem() instanceof DyeItem dyeItem) {
                getEntityWorld().playSound(player, getBlockPos(), SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1f, 1f);
                gbw$setColor(dyeItem.getColor());
                itemStack.decrementUnlessCreative(1, player);
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }

    @Inject(method = "getAsItemStack", at = @At("HEAD"), cancellable = true)
    private void gbw$fixItemFrameDropAndPickStack(CallbackInfoReturnable<ItemStack> cir) {
        DyeColor color = gbw$getColor();
        if (color == null)
            return;
        switch (color) {
            case GRAY -> cir.setReturnValue(ArchitectsAssemblyItems.GRAY_ITEM_FRAME.getDefaultStack());
            case RED -> cir.setReturnValue(ArchitectsAssemblyItems.RED_ITEM_FRAME.getDefaultStack());
            case BLUE -> cir.setReturnValue(ArchitectsAssemblyItems.BLUE_ITEM_FRAME.getDefaultStack());
            case CYAN -> cir.setReturnValue(ArchitectsAssemblyItems.CYAN_ITEM_FRAME.getDefaultStack());
            case LIME -> cir.setReturnValue(ArchitectsAssemblyItems.LIME_ITEM_FRAME.getDefaultStack());
            case PINK -> cir.setReturnValue(ArchitectsAssemblyItems.PINK_ITEM_FRAME.getDefaultStack());
            case BLACK -> cir.setReturnValue(ArchitectsAssemblyItems.BLACK_ITEM_FRAME.getDefaultStack());
            case BROWN -> cir.setReturnValue(ArchitectsAssemblyItems.BROWN_ITEM_FRAME.getDefaultStack());
            case GREEN -> cir.setReturnValue(ArchitectsAssemblyItems.GREEN_ITEM_FRAME.getDefaultStack());
            case WHITE -> cir.setReturnValue(ArchitectsAssemblyItems.WHITE_ITEM_FRAME.getDefaultStack());
            case ORANGE -> cir.setReturnValue(ArchitectsAssemblyItems.ORANGE_ITEM_FRAME.getDefaultStack());
            case PURPLE -> cir.setReturnValue(ArchitectsAssemblyItems.PURPLE_ITEM_FRAME.getDefaultStack());
            case YELLOW -> cir.setReturnValue(ArchitectsAssemblyItems.YELLOW_ITEM_FRAME.getDefaultStack());
            case MAGENTA -> cir.setReturnValue(ArchitectsAssemblyItems.MAGENTA_ITEM_FRAME.getDefaultStack());
            case LIGHT_BLUE -> cir.setReturnValue(ArchitectsAssemblyItems.LIGHT_BLUE_ITEM_FRAME.getDefaultStack());
            case LIGHT_GRAY -> cir.setReturnValue(ArchitectsAssemblyItems.LIGHT_GRAY_ITEM_FRAME.getDefaultStack());
        }
    }

    @Unique
    private static ActionResult wax(ItemFrameEntity itemFrame, PlayerEntity player, ItemStack itemStack) {
        PlayerLookup.tracking(itemFrame).forEach(serverPlayerEntity -> {
            ServerPlayNetworking.send(serverPlayerEntity, new SyncWorldEventS2C(WorldEvents.BLOCK_WAXED, itemFrame.getBlockPos(), 0));
        });
        itemFrame.fixed = true;
        itemStack.decrementUnlessCreative(1, player);
        return ActionResult.SUCCESS;
    }

    @Unique
    private static ActionResult unwax(ItemFrameEntity itemFrame, PlayerEntity player, Hand hand, ItemStack itemStack) {
        PlayerLookup.tracking(itemFrame).forEach(serverPlayerEntity -> {
            ServerPlayNetworking.send(serverPlayerEntity, new SyncWorldEventS2C(WorldEvents.WAX_REMOVED, itemFrame.getBlockPos(), 0));
        });
        itemFrame.fixed = false;
        itemFrame.getEntityWorld().playSound(player, itemFrame.getBlockPos(), SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1f, 1f);
        if (!player.getAbilities().creativeMode) {
            itemStack.damage(1, player, hand.getEquipmentSlot());
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    public DyeColor gbw$getColor() {
        int color = dataTracker.get(COLOR);
        if (color == NO_COLOR)
            return null;
        return DyeColor.byIndex(color);
    }

    public void gbw$setColor(@Nullable DyeColor color) {
        if (color == null)
            dataTracker.set(COLOR, NO_COLOR);
        else {
            dataTracker.set(COLOR, color.getIndex());
        }
    }
}
