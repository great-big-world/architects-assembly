package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyItems;
import dev.creoii.greatbigworld.architectsassembly.util.ExtendedItemFrame;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
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
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntityMixin extends AbstractDecorationEntity implements ExtendedItemFrame {
    @Shadow public abstract int getRotation();
    @Shadow public abstract void setRotation(int value);
    @Shadow public abstract SoundEvent getRotateItemSound();
    @Shadow public abstract void setHeldItemStack(ItemStack stack);
    @Unique private static final int NO_COLOR = -1;
    @Unique private static final TrackedData<Integer> COLOR = DataTracker.registerData(ItemFrameEntity.class, TrackedDataHandlerRegistry.INTEGER);
    @Unique private static final TrackedData<Boolean> WAXED = DataTracker.registerData(ItemFrameEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    protected ItemFrameEntityMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void gbw$trackData(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(COLOR, NO_COLOR);
        builder.add(WAXED, false);
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void gbw$writeDataToNbt(WriteView view, CallbackInfo ci) {
        DyeColor color = gbw$getColor();
        if (color == null)
            view.putInt("Color", NO_COLOR);
        else
            view.putInt("Color", color.getIndex());

        view.putBoolean("Waxed", gbw$isWaxed());
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void gbw$readColorFromNbt(ReadView view, CallbackInfo ci) {
        int color = view.getInt("Color", 0);
        if (color >= 0 && color <= 15)
            gbw$setColor(DyeColor.byIndex(color));
        else gbw$setColor(null);
        gbw$setWaxed(view.getBoolean("Waxed", false));
    }

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/FilledMapItem;getMapState(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;)Lnet/minecraft/item/map/MapState;", ordinal = 0), cancellable = true)
    private void gbw$overwriteInteraction(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, @Local ItemStack itemStack, @Local(ordinal = 0) boolean bl, @Local(ordinal = 1) boolean bl2) {
        if (!bl && !itemStack.isEmpty() && !isRemoved() && player.shouldCancelInteraction()) {
            if (gbw$isWaxed() && itemStack.isIn(ItemTags.AXES) && player.shouldCancelInteraction()) {
                cir.setReturnValue(unwax((ItemFrameEntity) (Object) this, player, itemStack));
                return;
            } else if (!gbw$isWaxed() && itemStack.isOf(Items.HONEYCOMB)) {
                cir.setReturnValue(wax((ItemFrameEntity) (Object) this, player, itemStack));
                return;
            } else if (!gbw$isWaxed() && itemStack.getItem() instanceof DyeItem dyeItem) {
                getWorld().playSoundFromEntity(player, (ItemFrameEntity) (Object) this, SoundEvents.ITEM_DYE_USE, SoundCategory.PLAYERS, 1f, 1f);
                gbw$setColor(dyeItem.getColor());
                emitGameEvent(GameEvent.BLOCK_CHANGE, player);
                itemStack.decrementUnlessCreative(1, player);

                if (!getWorld().isClient)
                    cir.setReturnValue(ActionResult.SUCCESS_SERVER);
                else
                    cir.setReturnValue(ActionResult.SUCCESS);
                return;
            }
            cir.setReturnValue(ActionResult.PASS);
            return;
        }

        if (!bl) {
            if (bl2 && !isRemoved() && !getWorld().isClient) {
                if (itemStack.isOf(Items.FILLED_MAP)) {
                    MapState mapState = FilledMapItem.getMapState(itemStack, getWorld());
                    if (mapState != null && mapState.decorationCountNotLessThan(256)) {
                        cir.setReturnValue(ActionResult.FAIL);
                        return;
                    }
                }

                setHeldItemStack(itemStack);
                emitGameEvent(GameEvent.BLOCK_CHANGE, player);
                itemStack.decrementUnlessCreative(1, player);
                if (!getWorld().isClient)
                    cir.setReturnValue(ActionResult.SUCCESS_SERVER);
                else cir.setReturnValue(ActionResult.SUCCESS);
                return;
            }
        } else {
            if (gbw$isWaxed()) {
                if (itemStack.isIn(ItemTags.AXES) && player.shouldCancelInteraction()) {
                    cir.setReturnValue(unwax((ItemFrameEntity) (Object) this, player, itemStack));
                } else
                    cir.setReturnValue(ActionResult.PASS);
                return;
            } else if (!gbw$isWaxed()) {
                if (player.shouldCancelInteraction()) {
                    if (itemStack.isOf(Items.HONEYCOMB)) {
                        cir.setReturnValue(wax((ItemFrameEntity) (Object) this, player, itemStack));
                        return;
                    } else if (itemStack.getItem() instanceof DyeItem dyeItem && gbw$getColor() != dyeItem.getColor()) {
                        getWorld().playSoundFromEntity(player, (ItemFrameEntity) (Object) this, SoundEvents.ITEM_DYE_USE, SoundCategory.PLAYERS, 1f, 1f);
                        gbw$setColor(dyeItem.getColor());
                        emitGameEvent(GameEvent.BLOCK_CHANGE, player);
                        itemStack.decrementUnlessCreative(1, player);

                        if (!getWorld().isClient)
                            cir.setReturnValue(ActionResult.SUCCESS_SERVER);
                        else cir.setReturnValue(ActionResult.SUCCESS);
                        return;
                    }
                } else {
                    playSound(getRotateItemSound(), 1f, 1f);
                    if (player.shouldCancelInteraction()) {
                        setRotation(getRotation() - 1);
                    } else setRotation(getRotation() + 1);
                    emitGameEvent(GameEvent.BLOCK_CHANGE, player);

                    if (!getWorld().isClient)
                        cir.setReturnValue(ActionResult.SUCCESS_SERVER);
                    else cir.setReturnValue(ActionResult.SUCCESS);
                    return;
                }
                return;
            }
        }
        if (!bl && !bl2) {
            cir.setReturnValue(ActionResult.PASS);
        } else {
            if (!getWorld().isClient) {
                cir.setReturnValue(ActionResult.SUCCESS_SERVER);
            } else cir.setReturnValue(ActionResult.SUCCESS);
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
        ((ExtendedItemFrame) itemFrame).gbw$setWaxed(true);
        itemFrame.getWorld().syncWorldEvent(player, WorldEvents.BLOCK_WAXED, itemFrame.getBlockPos(), 0);
        itemFrame.emitGameEvent(GameEvent.BLOCK_CHANGE, player);
        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        if (!itemFrame.getWorld().isClient) {
            return ActionResult.SUCCESS_SERVER;
        } else return ActionResult.SUCCESS;
    }

    @Unique
    private static ActionResult unwax(ItemFrameEntity itemFrame, PlayerEntity player, ItemStack itemStack) {
        ((ExtendedItemFrame) itemFrame).gbw$setWaxed(false);
        itemFrame.getWorld().syncWorldEvent(player, WorldEvents.WAX_REMOVED, itemFrame.getBlockPos(), 0);
        itemFrame.emitGameEvent(GameEvent.BLOCK_CHANGE, player);
        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        if (!itemFrame.getWorld().isClient) {
            return ActionResult.SUCCESS_SERVER;
        } else return ActionResult.SUCCESS;
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

    @Override
    public boolean gbw$isWaxed() {
        return dataTracker.get(WAXED);
    }

    @Override
    public void gbw$setWaxed(boolean waxed) {
        dataTracker.set(WAXED, waxed);
    }
}
