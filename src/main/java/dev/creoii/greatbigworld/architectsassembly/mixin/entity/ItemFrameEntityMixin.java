package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyItems;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import dev.creoii.greatbigworld.util.network.SyncWorldEventS2C;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrame.class)
public abstract class ItemFrameEntityMixin extends HangingEntity implements ExtendedItemFrame {
    @Unique private static final int NO_COLOR = -1;
    @Unique private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(ItemFrame.class, EntityDataSerializers.INT);

    protected ItemFrameEntityMixin(EntityType<? extends HangingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void gbw$trackData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(COLOR, NO_COLOR);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void gbw$writeDataToNbt(ValueOutput view, CallbackInfo ci) {
        DyeColor color = gbw$getColor();
        if (color == null)
            view.putInt("Color", NO_COLOR);
        else
            view.putInt("Color", color.getId());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void gbw$readColorFromNbt(ValueInput view, CallbackInfo ci) {
        int color = view.getIntOr("Color", 0);
        if (color >= 0 && color <= 15)
            gbw$setColor(DyeColor.byId(color));
        else gbw$setColor(null);
    }

    @ModifyReturnValue(method = "interact", at = @At(value = "RETURN", ordinal = 0))
    private InteractionResult gbw$handleWaxedItemFrames(InteractionResult original, @Local(argsOnly = true) Player player, @Local(argsOnly = true) InteractionHand hand, @Local ItemStack itemStack) {
        if (itemStack.is(ItemTags.AXES) && player.isShiftKeyDown()) {
            return unwax((ItemFrame) (Object) this, player, hand, itemStack);
        }
        return InteractionResult.FAIL;
    }

    @ModifyConstant(method = "interact", constant = @Constant(intValue = 1))
    private int gbw$handleBackwardsRotation(int constant, @Local(argsOnly = true) Player player) {
        return player.isShiftKeyDown() ? -constant : constant;
    }

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/ItemFrame;setItem(Lnet/minecraft/world/item/ItemStack;)V"), cancellable = true)
    private void gbw$handleItemInteractionEmpty(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir, @Local ItemStack itemStack) {
        if (player.isShiftKeyDown()) {
            if (itemStack.is(Items.HONEYCOMB)) {
                cir.setReturnValue(wax((ItemFrame) (Object) this, player, itemStack));
            } else if (itemStack.getItem() instanceof DyeItem dyeItem) {
                level().playSound(player, blockPosition(), SoundEvents.DYE_USE, SoundSource.BLOCKS, 1f, 1f);
                gbw$setColor(dyeItem.getDyeColor());
                itemStack.consume(1, player);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/ItemFrame;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"), cancellable = true)
    private void gbw$handleItemInteractionNonEmpty(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            if (itemStack.is(Items.HONEYCOMB)) {
                cir.setReturnValue(wax((ItemFrame) (Object) this, player, itemStack));
            } else if (itemStack.getItem() instanceof DyeItem dyeItem) {
                level().playSound(player, blockPosition(), SoundEvents.DYE_USE, SoundSource.BLOCKS, 1f, 1f);
                gbw$setColor(dyeItem.getDyeColor());
                itemStack.consume(1, player);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

    @Inject(method = "getFrameItemStack", at = @At("HEAD"), cancellable = true)
    private void gbw$fixItemFrameDropAndPickStack(CallbackInfoReturnable<ItemStack> cir) {
        DyeColor color = gbw$getColor();
        if (color == null)
            return;
        switch (color) {
            case GRAY -> cir.setReturnValue(ArchitectsAssemblyItems.GRAY_ITEM_FRAME.getDefaultInstance());
            case RED -> cir.setReturnValue(ArchitectsAssemblyItems.RED_ITEM_FRAME.getDefaultInstance());
            case BLUE -> cir.setReturnValue(ArchitectsAssemblyItems.BLUE_ITEM_FRAME.getDefaultInstance());
            case CYAN -> cir.setReturnValue(ArchitectsAssemblyItems.CYAN_ITEM_FRAME.getDefaultInstance());
            case LIME -> cir.setReturnValue(ArchitectsAssemblyItems.LIME_ITEM_FRAME.getDefaultInstance());
            case PINK -> cir.setReturnValue(ArchitectsAssemblyItems.PINK_ITEM_FRAME.getDefaultInstance());
            case BLACK -> cir.setReturnValue(ArchitectsAssemblyItems.BLACK_ITEM_FRAME.getDefaultInstance());
            case BROWN -> cir.setReturnValue(ArchitectsAssemblyItems.BROWN_ITEM_FRAME.getDefaultInstance());
            case GREEN -> cir.setReturnValue(ArchitectsAssemblyItems.GREEN_ITEM_FRAME.getDefaultInstance());
            case WHITE -> cir.setReturnValue(ArchitectsAssemblyItems.WHITE_ITEM_FRAME.getDefaultInstance());
            case ORANGE -> cir.setReturnValue(ArchitectsAssemblyItems.ORANGE_ITEM_FRAME.getDefaultInstance());
            case PURPLE -> cir.setReturnValue(ArchitectsAssemblyItems.PURPLE_ITEM_FRAME.getDefaultInstance());
            case YELLOW -> cir.setReturnValue(ArchitectsAssemblyItems.YELLOW_ITEM_FRAME.getDefaultInstance());
            case MAGENTA -> cir.setReturnValue(ArchitectsAssemblyItems.MAGENTA_ITEM_FRAME.getDefaultInstance());
            case LIGHT_BLUE -> cir.setReturnValue(ArchitectsAssemblyItems.LIGHT_BLUE_ITEM_FRAME.getDefaultInstance());
            case LIGHT_GRAY -> cir.setReturnValue(ArchitectsAssemblyItems.LIGHT_GRAY_ITEM_FRAME.getDefaultInstance());
        }
    }

    @Unique
    private static InteractionResult wax(ItemFrame itemFrame, Player player, ItemStack itemStack) {
        PlayerLookup.tracking(itemFrame).forEach(serverPlayerEntity -> {
            ServerPlayNetworking.send(serverPlayerEntity, new SyncWorldEventS2C(LevelEvent.PARTICLES_AND_SOUND_WAX_ON, itemFrame.blockPosition(), 0));
        });
        itemFrame.fixed = true;
        itemStack.consume(1, player);
        return InteractionResult.SUCCESS;
    }

    @Unique
    private static InteractionResult unwax(ItemFrame itemFrame, Player player, InteractionHand hand, ItemStack itemStack) {
        PlayerLookup.tracking(itemFrame).forEach(serverPlayerEntity -> {
            ServerPlayNetworking.send(serverPlayerEntity, new SyncWorldEventS2C(LevelEvent.PARTICLES_WAX_OFF, itemFrame.blockPosition(), 0));
        });
        itemFrame.fixed = false;
        itemFrame.level().playSound(player, itemFrame.blockPosition(), SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1f, 1f);
        if (!player.getAbilities().instabuild) {
            itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    public DyeColor gbw$getColor() {
        int color = entityData.get(COLOR);
        if (color == NO_COLOR)
            return null;
        return DyeColor.byId(color);
    }

    public void gbw$setColor(@Nullable DyeColor color) {
        if (color == null)
            entityData.set(COLOR, NO_COLOR);
        else {
            entityData.set(COLOR, color.getId());
        }
    }
}
