package dev.creoii.greatbigworld.architectsassembly.util;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ArchitectsAssemblyTags {
    public static final TagKey<Block> VERTICAL_SLABS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(GreatBigWorld.NAMESPACE, "vertical_slabs"));
    public static final TagKey<Item> FREE_PLACEABLE_BLOCKS = TagKey.of(RegistryKeys.ITEM, Identifier.of(GreatBigWorld.NAMESPACE, "free_placeable_blocks"));
    public static final TagKey<EntityType<?>> LIGHTWEIGHT_ENTITIES = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(GreatBigWorld.NAMESPACE, "lightweight_entities"));
}
