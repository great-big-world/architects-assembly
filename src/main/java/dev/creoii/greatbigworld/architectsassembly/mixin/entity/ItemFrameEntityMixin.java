package dev.creoii.greatbigworld.architectsassembly.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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
    @Shadow private boolean fixed;
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

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void gbw$writeDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        DyeColor color = gbw$getColor();
        if (color == null)
            nbt.putInt("Color", NO_COLOR);
        else
            nbt.putInt("Color", color.getId());

        nbt.putBoolean("Waxed", gbw$isWaxed());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void gbw$readColorFromNbt(NbtCompound nbt, CallbackInfo ci) {
        int color = nbt.getInt("Color");
        if (color >= 0 && color <= 15)
            gbw$setColor(DyeColor.byId(color));
        gbw$setWaxed(nbt.getBoolean("Waxed"));
    }

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;getWorld()Lnet/minecraft/world/World;", ordinal = 0), cancellable = true)
    private void gbw$overwriteInteraction(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir, @Local ItemStack itemStack, @Local(ordinal = 0) boolean bl, @Local(ordinal = 1) boolean bl2) {
        if (!fixed && !bl && !itemStack.isEmpty() && !isRemoved() && player.shouldCancelInteraction()) {
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
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }
                cir.setReturnValue(ActionResult.success(getWorld().isClient));
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
                    }
                }

                setHeldItemStack(itemStack);
                emitGameEvent(GameEvent.BLOCK_CHANGE, player);
                if (!player.isCreative()) {
                    itemStack.decrement(1);
                }
                return;
            }
        } else {
            if (gbw$isWaxed()) {
                if (itemStack.isIn(ItemTags.AXES) && player.shouldCancelInteraction()) {
                    cir.setReturnValue(unwax((ItemFrameEntity) (Object) this, player, itemStack));
                } else {
                    cir.setReturnValue(ActionResult.PASS);
                }
                return;
            } else if (!gbw$isWaxed()) {
                if (player.shouldCancelInteraction()) {
                    if (itemStack.isOf(Items.HONEYCOMB)) {
                        cir.setReturnValue(wax((ItemFrameEntity) (Object) this, player, itemStack));
                    } else if (itemStack.getItem() instanceof DyeItem dyeItem && gbw$getColor() != dyeItem.getColor()) {
                        getWorld().playSoundFromEntity(player, (ItemFrameEntity) (Object) this, SoundEvents.ITEM_DYE_USE, SoundCategory.PLAYERS, 1f, 1f);
                        gbw$setColor(dyeItem.getColor());
                        emitGameEvent(GameEvent.BLOCK_CHANGE, player);
                        if (!player.isCreative()) {
                            itemStack.decrement(1);
                        }
                        cir.setReturnValue(ActionResult.success(getWorld().isClient));
                    }
                } else {
                    playSound(getRotateItemSound(), 1f, 1f);
                    if (player.shouldCancelInteraction()) {
                        setRotation(getRotation() - 1);
                    } else setRotation(getRotation() + 1);
                    emitGameEvent(GameEvent.BLOCK_CHANGE, player);
                    cir.setReturnValue(ActionResult.success(getWorld().isClient));
                }
                return;
            }
        }
        cir.setReturnValue(!bl && !bl2 ? ActionResult.PASS : ActionResult.success(getWorld().isClient));
    }

    @Unique
    private static ActionResult wax(ItemFrameEntity itemFrame, PlayerEntity player, ItemStack itemStack) {
        ((ExtendedItemFrame) itemFrame).gbw$setWaxed(true);
        itemFrame.getWorld().syncWorldEvent(player, WorldEvents.BLOCK_WAXED, itemFrame.getBlockPos(), 0);
        itemFrame.emitGameEvent(GameEvent.BLOCK_CHANGE, player);
        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return ActionResult.success(itemFrame.getWorld().isClient);
    }

    @Unique
    private static ActionResult unwax(ItemFrameEntity itemFrame, PlayerEntity player, ItemStack itemStack) {
        ((ExtendedItemFrame) itemFrame).gbw$setWaxed(false);
        itemFrame.getWorld().syncWorldEvent(player, WorldEvents.WAX_REMOVED, itemFrame.getBlockPos(), 0);
        itemFrame.emitGameEvent(GameEvent.BLOCK_CHANGE, player);
        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return ActionResult.success(itemFrame.getWorld().isClient);
    }

    @Nullable
    public DyeColor gbw$getColor() {
        int color = dataTracker.get(COLOR);
        if (color == NO_COLOR)
            return null;
        return DyeColor.byId(color);
    }

    public void gbw$setColor(@Nullable DyeColor color) {
        if (color == null)
            dataTracker.set(COLOR, NO_COLOR);
        else {
            dataTracker.set(COLOR, color.getId());
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
