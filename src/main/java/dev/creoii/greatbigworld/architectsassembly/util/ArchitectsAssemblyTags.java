package dev.creoii.greatbigworld.architectsassembly.util;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public final class ArchitectsAssemblyTags {
    public static final TagKey<Block> VERTICAL_SLABS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_slabs"));
    public static final TagKey<EntityType<?>> LIGHTWEIGHT_ENTITIES = TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "lightweight_entities"));
}
