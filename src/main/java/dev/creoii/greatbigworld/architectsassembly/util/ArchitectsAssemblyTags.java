package dev.creoii.greatbigworld.architectsassembly.util;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ArchitectsAssemblyTags {
    public static final TagKey<Block> VERTICAL_SLABS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(GreatBigWorld.NAMESPACE, "vertical_slabs"));
    public static final TagKey<EntityType<?>> LIGHTWEIGHT_ENTITIES = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(GreatBigWorld.NAMESPACE, "lightweight_entities"));
}
