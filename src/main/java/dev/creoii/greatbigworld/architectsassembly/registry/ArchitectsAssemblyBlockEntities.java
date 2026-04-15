package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.entity.KilnBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ArchitectsAssemblyBlockEntities {
    public static BlockEntityType<KilnBlockEntity> KILN;

    public static void register() {
        KILN = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "kiln"), FabricBlockEntityTypeBuilder.create(KilnBlockEntity::new, ArchitectsAssemblyBlocks.KILN).build());
    }
}
