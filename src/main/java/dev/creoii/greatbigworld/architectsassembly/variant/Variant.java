package dev.creoii.greatbigworld.architectsassembly.variant;

import com.google.gson.*;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import java.lang.reflect.Type;
import java.util.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class Variant {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Variant.class, new Serializer()).create();
    public static final Map<String, Variant> VARIANTS = new HashMap<>();
    private final Set<Item> items;
    private final Set<TagKey<Item>> itemTags;
    private String translationKey;

    public Variant() {
        items = new HashSet<>();
        itemTags = new HashSet<>();
    }

    public Variant build(Identifier id) {
        this.translationKey = "variant.item." + id.getPath();
        return this;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Set<TagKey<Item>> getItemTags() {
        return itemTags;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public boolean isStackInTags(ItemStack stack) {
        for (TagKey<Item> tagKey : itemTags) {
            if (stack.is(tagKey))
                return true;
        }
        return false;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItemTag(TagKey<Item> itemTag) {
        itemTags.add(itemTag);
    }

    public void copyTo(Variant other) {
        itemTags.forEach(other::addItemTag);
        items.forEach(other::addItem);
    }

    public static class Serializer implements JsonDeserializer<Variant>, JsonSerializer<Variant> {
        @Override
        public Variant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {
                JsonObject object = json.getAsJsonObject();
                Variant variant = new Variant();

                JsonArray values = GsonHelper.getAsJsonArray(object, "values");
                values.forEach(value -> {
                    if (value.isJsonPrimitive()) {
                        String pValue = value.getAsString();
                        if (pValue.startsWith("#")) {
                            TagKey<Item> tagKey = TagKey.create(Registries.ITEM, Identifier.tryParse(pValue.substring(1)));
                            variant.addItemTag(tagKey);
                        } else {
                            Identifier id = Identifier.tryParse(pValue);
                            if (BuiltInRegistries.ITEM.containsKey(id)) {
                                variant.addItem(BuiltInRegistries.ITEM.getValue(id));
                            } else ArchitectsAssembly.LOGGER.warn("Found unknown item id '{}' in a variant.", id);
                        }
                    }
                });
                return variant;
            }
            throw new JsonParseException("Variant definition is not a JsonObject.");
        }

        @Override
        public JsonElement serialize(Variant src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            JsonArray array = new JsonArray();

            src.getItemTags().forEach(tagKey -> array.add("#" + tagKey.location()));
            src.getItems().forEach(item -> array.add(BuiltInRegistries.ITEM.getKey(item).toString()));

            obj.add("values", array);
            return obj;
        }
    }
}