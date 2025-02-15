package dev.creoii.greatbigworld.architectsassembly.client;

import com.mojang.serialization.MapCodec;
import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import dev.creoii.greatbigworld.architectsassembly.util.SlabPlacer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record SlabPlacementProperty() implements BooleanProperty {
    public static final MapCodec<SlabPlacementProperty> CODEC = MapCodec.unit(new SlabPlacementProperty());

    public boolean getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ModelTransformationMode modelTransformationMode) {
        if (user instanceof SlabPlacer slabPlacer && user.getStackInHand(user.getActiveHand()).isIn(ItemTags.SLABS)) {
            return !slabPlacer.gbw$getSlabPlacementState().equals(SlabItem.SlabPlacement.VERTICAL);
        }
        return false;
    }

    public MapCodec<SlabPlacementProperty> getCodec() {
        return CODEC;
    }
}
