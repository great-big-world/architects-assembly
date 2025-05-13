package dev.creoii.greatbigworld.architectsassembly.client;

import com.mojang.serialization.MapCodec;
import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import dev.creoii.greatbigworld.architectsassembly.util.SlabPlacer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record SlabPlacementProperty() implements BooleanProperty {
    public static final MapCodec<SlabPlacementProperty> CODEC = MapCodec.unit(new SlabPlacementProperty());

    public MapCodec<SlabPlacementProperty> getCodec() {
        return CODEC;
    }

    @Override
    public boolean test(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        if (entity instanceof SlabPlacer slabPlacer) {
            return !slabPlacer.gbw$getSlabPlacementState().equals(SlabItem.SlabPlacement.VERTICAL);
        }
        return false;
    }
}
