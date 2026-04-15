package dev.creoii.greatbigworld.architectsassembly.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jspecify.annotations.NonNull;

public record SawmillRecipeDisplay(SlotDisplay input, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {
    public static final MapCodec<SawmillRecipeDisplay> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(SlotDisplay.CODEC.fieldOf("input").forGetter(SawmillRecipeDisplay::input), SlotDisplay.CODEC.fieldOf("result").forGetter(SawmillRecipeDisplay::result), SlotDisplay.CODEC.fieldOf("crafting_station").forGetter(SawmillRecipeDisplay::craftingStation)).apply(instance, SawmillRecipeDisplay::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, SawmillRecipeDisplay> STREAM_CODEC;
    public static final RecipeDisplay.Type<SawmillRecipeDisplay> TYPE;

    public RecipeDisplay.@NonNull Type<SawmillRecipeDisplay> type() {
        return TYPE;
    }

    static {
        STREAM_CODEC = StreamCodec.composite(SlotDisplay.STREAM_CODEC, SawmillRecipeDisplay::input, SlotDisplay.STREAM_CODEC, SawmillRecipeDisplay::result, SlotDisplay.STREAM_CODEC, SawmillRecipeDisplay::craftingStation, SawmillRecipeDisplay::new);
        TYPE = new RecipeDisplay.Type<>(MAP_CODEC, STREAM_CODEC);
    }
}
