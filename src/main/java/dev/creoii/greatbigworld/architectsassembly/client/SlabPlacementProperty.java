package dev.creoii.greatbigworld.architectsassembly.client;

import com.mojang.serialization.MapCodec;
import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import dev.creoii.greatbigworld.architectsassembly.util.SlabPlacer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record SlabPlacementProperty() implements ConditionalItemModelProperty {
    public static final MapCodec<SlabPlacementProperty> CODEC = MapCodec.unit(new SlabPlacementProperty());

    public MapCodec<SlabPlacementProperty> type() {
        return CODEC;
    }

    @Override
    public boolean get(ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        if (entity instanceof SlabPlacer slabPlacer) {
            return !slabPlacer.gbw$getSlabPlacementState().equals(SlabItem.SlabPlacement.VERTICAL);
        }
        return false;
    }
}
