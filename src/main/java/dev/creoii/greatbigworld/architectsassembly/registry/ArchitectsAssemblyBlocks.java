package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.*;
import dev.creoii.greatbigworld.block.FacingBlock;
import dev.creoii.greatbigworld.util.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

@SuppressWarnings("deprecation")
public final class ArchitectsAssemblyBlocks {
    public static void register() {
        registerDecorativeBlocks();
        registerVerticalSlabs();
        registerMissingBlocks();
        registerMiscBlocks();
    }

    public static void registerClient() {
        registerDecorativeBlocksClient();
        registerMissingBlocksClient();
        registerMiscBlocksClient();
    }

    // region Decorative Blocks
    public static Block CRACKED_GLASS;
    public static Block CHISELED_GLASS;
    public static Block CHISELED_GLASS_PANE;
    public static Block CRACKED_BROWN_GLASS;
    public static Block CHISELED_BROWN_GLASS;
    public static Block CHISELED_BROWN_GLASS_PANE;
    public static Block CRACKED_RED_GLASS;
    public static Block CHISELED_RED_GLASS;
    public static Block CHISELED_RED_GLASS_PANE;
    public static Block CRACKED_ORANGE_GLASS;
    public static Block CHISELED_ORANGE_GLASS;
    public static Block CHISELED_ORANGE_GLASS_PANE;
    public static Block CRACKED_YELLOW_GLASS;
    public static Block CHISELED_YELLOW_GLASS;
    public static Block CHISELED_YELLOW_GLASS_PANE;
    public static Block CRACKED_LIME_GLASS;
    public static Block CHISELED_LIME_GLASS;
    public static Block CHISELED_LIME_GLASS_PANE;
    public static Block CRACKED_GREEN_GLASS;
    public static Block CHISELED_GREEN_GLASS;
    public static Block CHISELED_GREEN_GLASS_PANE;
    public static Block CRACKED_CYAN_GLASS;
    public static Block CHISELED_CYAN_GLASS;
    public static Block CHISELED_CYAN_GLASS_PANE;
    public static Block CRACKED_BLUE_GLASS;
    public static Block CHISELED_BLUE_GLASS;
    public static Block CHISELED_BLUE_GLASS_PANE;
    public static Block CRACKED_LIGHT_BLUE_GLASS;
    public static Block CHISELED_LIGHT_BLUE_GLASS;
    public static Block CHISELED_LIGHT_BLUE_GLASS_PANE;
    public static Block CRACKED_PINK_GLASS;
    public static Block CHISELED_PINK_GLASS;
    public static Block CHISELED_PINK_GLASS_PANE;
    public static Block CRACKED_MAGENTA_GLASS;
    public static Block CHISELED_MAGENTA_GLASS;
    public static Block CHISELED_MAGENTA_GLASS_PANE;
    public static Block CRACKED_PURPLE_GLASS;
    public static Block CHISELED_PURPLE_GLASS;
    public static Block CHISELED_PURPLE_GLASS_PANE;
    public static Block CRACKED_BLACK_GLASS;
    public static Block CHISELED_BLACK_GLASS;
    public static Block CHISELED_BLACK_GLASS_PANE;
    public static Block CRACKED_GRAY_GLASS;
    public static Block CHISELED_GRAY_GLASS;
    public static Block CHISELED_GRAY_GLASS_PANE;
    public static Block CRACKED_LIGHT_GRAY_GLASS;
    public static Block CHISELED_LIGHT_GRAY_GLASS;
    public static Block CHISELED_LIGHT_GRAY_GLASS_PANE;
    public static Block CRACKED_WHITE_GLASS;
    public static Block CHISELED_WHITE_GLASS;
    public static Block CHISELED_WHITE_GLASS_PANE;
    public static Block LAPIS_STAIRS;
    public static Block LAPIS_SLAB;
    public static Block VERTICAL_LAPIS_SLAB;
    public static Block LAPIS_WALL;
    public static Block POLISHED_LAPIS_BLOCK;
    public static Block POLISHED_LAPIS_STAIRS;
    public static Block POLISHED_LAPIS_SLAB;
    public static Block VERTICAL_POLISHED_LAPIS_SLAB;
    public static Block POLISHED_LAPIS_WALL;
    public static Block POLISHED_LAPIS_BRICKS;
    public static Block POLISHED_LAPIS_BRICK_STAIRS;
    public static Block POLISHED_LAPIS_BRICK_SLAB;
    public static Block VERTICAL_POLISHED_LAPIS_BRICK_SLAB;
    public static Block POLISHED_LAPIS_BRICK_WALL;
    public static Block CRACKED_BRICKS;
    public static Block MOSSY_BRICKS;
    public static Block MOSSY_BRICK_STAIRS;
    public static Block MOSSY_BRICK_SLAB;
    public static Block VERTICAL_MOSSY_BRICK_SLAB;
    public static Block MOSSY_BRICK_WALL;
    public static Block GLASS;
    public static Block BROWN_GLASS;
    public static Block RED_GLASS;
    public static Block ORANGE_GLASS;
    public static Block YELLOW_GLASS;
    public static Block LIME_GLASS;
    public static Block GREEN_GLASS;
    public static Block CYAN_GLASS;
    public static Block BLUE_GLASS;
    public static Block LIGHT_BLUE_GLASS;
    public static Block PINK_GLASS;
    public static Block MAGENTA_GLASS;
    public static Block PURPLE_GLASS;
    public static Block BLACK_GLASS;
    public static Block GRAY_GLASS;
    public static Block LIGHT_GRAY_GLASS;
    public static Block WHITE_GLASS;
    public static Block CHISELED_OAK_PLANKS;
    public static Block CHISELED_SPRUCE_PLANKS;
    public static Block CHISELED_BIRCH_PLANKS;
    public static Block CHISELED_JUNGLE_PLANKS;
    public static Block CHISELED_ACACIA_PLANKS;
    public static Block CHISELED_DARK_OAK_PLANKS;
    public static Block CHISELED_MANGROVE_PLANKS;
    public static Block CHISELED_CHERRY_PLANKS;
    public static Block CHISELED_PALE_OAK_PLANKS;
    public static Block CHISELED_BAMBOO_PLANKS;
    public static Block CHISELED_OAK_LOG;
    public static Block CHISELED_OAK_WOOD;
    public static Block STRIPPED_CHISELED_OAK_LOG;
    public static Block STRIPPED_CHISELED_OAK_WOOD;
    public static Block CHISELED_SPRUCE_LOG;
    public static Block CHISELED_SPRUCE_WOOD;
    public static Block CHISELED_BIRCH_LOG;
    public static Block CHISELED_BIRCH_WOOD;
    public static Block CHISELED_JUNGLE_LOG;
    public static Block CHISELED_JUNGLE_WOOD;
    public static Block CHISELED_ACACIA_LOG;
    public static Block CHISELED_ACACIA_WOOD;
    public static Block CHISELED_DARK_OAK_LOG;
    public static Block CHISELED_DARK_OAK_WOOD;
    public static Block CHISELED_MANGROVE_LOG;
    public static Block CHISELED_MANGROVE_WOOD;
    public static Block CHISELED_CHERRY_LOG;
    public static Block CHISELED_CHERRY_WOOD;
    public static Block POLISHED_OAK_PLANKS;
    public static Block POLISHED_OAK_STAIRS;
    public static Block POLISHED_OAK_SLAB;
    public static Block VERTICAL_POLISHED_OAK_SLAB;
    public static Block POLISHED_SPRUCE_PLANKS;
    public static Block POLISHED_SPRUCE_STAIRS;
    public static Block POLISHED_SPRUCE_SLAB;
    public static Block VERTICAL_POLISHED_SPRUCE_SLAB;
    public static Block POLISHED_BIRCH_PLANKS;
    public static Block POLISHED_BIRCH_STAIRS;
    public static Block POLISHED_BIRCH_SLAB;
    public static Block VERTICAL_POLISHED_BIRCH_SLAB;
    public static Block POLISHED_JUNGLE_PLANKS;
    public static Block POLISHED_JUNGLE_STAIRS;
    public static Block POLISHED_JUNGLE_SLAB;
    public static Block VERTICAL_POLISHED_JUNGLE_SLAB;
    public static Block POLISHED_ACACIA_PLANKS;
    public static Block POLISHED_ACACIA_STAIRS;
    public static Block POLISHED_ACACIA_SLAB;
    public static Block VERTICAL_POLISHED_ACACIA_SLAB;
    public static Block POLISHED_DARK_OAK_PLANKS;
    public static Block POLISHED_DARK_OAK_STAIRS;
    public static Block POLISHED_DARK_OAK_SLAB;
    public static Block VERTICAL_POLISHED_DARK_OAK_SLAB;
    public static Block POLISHED_MANGROVE_PLANKS;
    public static Block POLISHED_MANGROVE_STAIRS;
    public static Block POLISHED_MANGROVE_SLAB;
    public static Block VERTICAL_POLISHED_MANGROVE_SLAB;
    public static Block POLISHED_CHERRY_PLANKS;
    public static Block POLISHED_CHERRY_STAIRS;
    public static Block POLISHED_CHERRY_SLAB;
    public static Block VERTICAL_POLISHED_CHERRY_SLAB;
    public static Block POLISHED_PALE_OAK_PLANKS;
    public static Block POLISHED_PALE_OAK_STAIRS;
    public static Block POLISHED_PALE_OAK_SLAB;
    public static Block VERTICAL_POLISHED_PALE_OAK_SLAB;
    public static Block POLISHED_BAMBOO_PLANKS;
    public static Block POLISHED_BAMBOO_STAIRS;
    public static Block POLISHED_BAMBOO_SLAB;
    public static Block VERTICAL_POLISHED_BAMBOO_SLAB;
    public static Block POLISHED_CRIMSON_PLANKS;
    public static Block POLISHED_CRIMSON_STAIRS;
    public static Block POLISHED_CRIMSON_SLAB;
    public static Block VERTICAL_POLISHED_CRIMSON_SLAB;
    public static Block POLISHED_WARPED_PLANKS;
    public static Block POLISHED_WARPED_STAIRS;
    public static Block POLISHED_WARPED_SLAB;
    public static Block VERTICAL_POLISHED_WARPED_SLAB;
    public static Block CUT_COPPER_WALL;
    public static Block EXPOSED_CUT_COPPER_WALL;
    public static Block WEATHERED_CUT_COPPER_WALL;
    public static Block OXIDIZED_CUT_COPPER_WALL;
    public static Block WAXED_CUT_COPPER_WALL;
    public static Block WAXED_EXPOSED_CUT_COPPER_WALL;
    public static Block WAXED_WEATHERED_CUT_COPPER_WALL;
    public static Block WAXED_OXIDIZED_CUT_COPPER_WALL;
    public static Block POLISHED_CALCITE;
    public static Block POLISHED_CALCITE_STAIRS;
    public static Block POLISHED_CALCITE_SLAB;
    public static Block VERTICAL_POLISHED_CALCITE_SLAB;
    public static Block POLISHED_CALCITE_WALL;
    public static Block PACKED_MUD_STAIRS;
    public static Block PACKED_MUD_SLAB;
    public static Block VERTICAL_PACKED_MUD_SLAB;
    public static Block PACKED_MUD_WALL;
    public static Block NETHERRACK_STAIRS;
    public static Block NETHERRACK_SLAB;
    public static Block VERTICAL_NETHERRACK_SLAB;
    public static Block NETHERRACK_WALL;
    public static Block END_STONE_STAIRS;
    public static Block END_STONE_SLAB;
    public static Block VERTICAL_END_STONE_SLAB;
    public static Block END_STONE_WALL;
    public static Block DEEPSLATE_STAIRS;
    public static Block DEEPSLATE_SLAB;
    public static Block VERTICAL_DEEPSLATE_SLAB;
    public static Block DEEPSLATE_WALL;
    public static Block MOSSY_MUD_BRICKS;
    public static Block MOSSY_MUD_BRICK_STAIRS;
    public static Block MOSSY_MUD_BRICK_SLAB;
    public static Block VERTICAL_MOSSY_MUD_BRICK_SLAB;
    public static Block MOSSY_MUD_BRICK_WALL;

    private static void registerDecorativeBlocks() {
        CRACKED_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_glass"), settings -> new CrackedGlassBlock(settings, Blocks.GLASS::defaultBlockState, () -> ArchitectsAssemblyItems.GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).instabreak());
        CHISELED_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_glass"), settings -> new GlassBlock(settings, CRACKED_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));
        CHISELED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE));
        CRACKED_BROWN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_brown_glass"), settings -> new CrackedGlassBlock(settings, () -> BROWN_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.BROWN_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_STAINED_GLASS).instabreak());
        CHISELED_BROWN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_brown_glass"), settings -> new GlassBlock(settings, CRACKED_BROWN_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_STAINED_GLASS));
        CHISELED_BROWN_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_brown_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_STAINED_GLASS_PANE));
        CRACKED_RED_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_red_glass"), settings -> new CrackedGlassBlock(settings, () -> RED_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.RED_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_STAINED_GLASS).instabreak());
        CHISELED_RED_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_red_glass"), settings -> new GlassBlock(settings, CRACKED_RED_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_STAINED_GLASS));
        CHISELED_RED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_red_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_STAINED_GLASS_PANE));
        CRACKED_ORANGE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_orange_glass"), settings -> new CrackedGlassBlock(settings, () -> ORANGE_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.ORANGE_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_STAINED_GLASS).instabreak());
        CHISELED_ORANGE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_orange_glass"), settings -> new GlassBlock(settings, CRACKED_ORANGE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_STAINED_GLASS));
        CHISELED_ORANGE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_orange_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_STAINED_GLASS_PANE));
        CRACKED_YELLOW_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_yellow_glass"), settings -> new CrackedGlassBlock(settings, () -> YELLOW_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.YELLOW_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS).instabreak());
        CHISELED_YELLOW_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_yellow_glass"), settings -> new GlassBlock(settings, CRACKED_YELLOW_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS));
        CHISELED_YELLOW_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_yellow_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS_PANE));
        CRACKED_LIME_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_lime_glass"), settings -> new CrackedGlassBlock(settings, () -> LIME_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.LIME_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_STAINED_GLASS).instabreak());
        CHISELED_LIME_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_lime_glass"), settings -> new GlassBlock(settings, CRACKED_LIME_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_STAINED_GLASS));
        CHISELED_LIME_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_lime_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_STAINED_GLASS_PANE));
        CRACKED_GREEN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_green_glass"), settings -> new CrackedGlassBlock(settings, () -> GREEN_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.GREEN_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_STAINED_GLASS).instabreak());
        CHISELED_GREEN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_green_glass"), settings -> new GlassBlock(settings, CRACKED_GREEN_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_STAINED_GLASS));
        CHISELED_GREEN_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_green_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_STAINED_GLASS_PANE));
        CRACKED_CYAN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_cyan_glass"), settings -> new CrackedGlassBlock(settings, () -> CYAN_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.CYAN_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_STAINED_GLASS).instabreak());
        CHISELED_CYAN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_cyan_glass"), settings -> new GlassBlock(settings, CRACKED_CYAN_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_STAINED_GLASS));
        CHISELED_CYAN_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_cyan_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_STAINED_GLASS_PANE));
        CRACKED_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_blue_glass"), settings -> new CrackedGlassBlock(settings, () -> BLUE_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.BLUE_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_STAINED_GLASS).instabreak());
        CHISELED_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_blue_glass"), settings -> new GlassBlock(settings, CRACKED_BLUE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_STAINED_GLASS));
        CHISELED_BLUE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_blue_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_STAINED_GLASS_PANE));
        CRACKED_LIGHT_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_light_blue_glass"), settings -> new CrackedGlassBlock(settings, () -> LIGHT_BLUE_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.LIGHT_BLUE_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_STAINED_GLASS).instabreak());
        CHISELED_LIGHT_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_light_blue_glass"), settings -> new GlassBlock(settings, CRACKED_LIGHT_BLUE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_STAINED_GLASS));
        CHISELED_LIGHT_BLUE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_light_blue_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE));
        CRACKED_PINK_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_pink_glass"), settings -> new CrackedGlassBlock(settings, () -> PINK_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.PINK_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_STAINED_GLASS).instabreak());
        CHISELED_PINK_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_pink_glass"), settings -> new GlassBlock(settings, CRACKED_PINK_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_STAINED_GLASS));
        CHISELED_PINK_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_pink_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_STAINED_GLASS_PANE));
        CRACKED_MAGENTA_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_magenta_glass"), settings -> new CrackedGlassBlock(settings, () -> MAGENTA_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.MAGENTA_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_STAINED_GLASS).instabreak());
        CHISELED_MAGENTA_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_magenta_glass"), settings -> new GlassBlock(settings, CRACKED_MAGENTA_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_STAINED_GLASS));
        CHISELED_MAGENTA_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_magenta_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_STAINED_GLASS_PANE));
        CRACKED_PURPLE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_purple_glass"), settings -> new CrackedGlassBlock(settings, () -> PURPLE_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.PURPLE_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_STAINED_GLASS).instabreak());
        CHISELED_PURPLE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_purple_glass"), settings -> new GlassBlock(settings, CRACKED_PURPLE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_STAINED_GLASS));
        CHISELED_PURPLE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_purple_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_STAINED_GLASS_PANE));
        CRACKED_BLACK_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_black_glass"), settings -> new CrackedGlassBlock(settings, () -> BLACK_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.BLACK_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_STAINED_GLASS).instabreak());
        CHISELED_BLACK_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_black_glass"), settings -> new GlassBlock(settings, CRACKED_BLACK_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_STAINED_GLASS));
        CHISELED_BLACK_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_black_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_STAINED_GLASS_PANE));
        CRACKED_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_gray_glass"), settings -> new CrackedGlassBlock(settings, () -> GRAY_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.GRAY_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_STAINED_GLASS).instabreak());
        CHISELED_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_gray_glass"), settings -> new GlassBlock(settings, CRACKED_GRAY_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_STAINED_GLASS));
        CHISELED_GRAY_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_gray_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_STAINED_GLASS_PANE));
        CRACKED_LIGHT_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_light_gray_glass"), settings -> new CrackedGlassBlock(settings, () -> LIGHT_GRAY_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.LIGHT_GRAY_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_STAINED_GLASS).instabreak());
        CHISELED_LIGHT_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_light_gray_glass"), settings -> new GlassBlock(settings, CRACKED_LIGHT_GRAY_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_STAINED_GLASS));
        CHISELED_LIGHT_GRAY_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_light_gray_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE));
        CRACKED_WHITE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_white_glass"), settings -> new CrackedGlassBlock(settings, () -> WHITE_GLASS.defaultBlockState(), () -> ArchitectsAssemblyItems.WHITE_GLASS_SHARD), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS).instabreak());
        CHISELED_WHITE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_white_glass"), settings -> new GlassBlock(settings, CRACKED_WHITE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS));
        CHISELED_WHITE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_white_glass_pane"), IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS_PANE));
        LAPIS_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "lapis_stairs"), settings -> new StairBlock(Blocks.LAPIS_BLOCK.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK));
        LAPIS_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "lapis_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK));
        VERTICAL_LAPIS_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_lapis_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK));
        LAPIS_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "lapis_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.LAPIS_BLOCK).forceSolidOn());
        POLISHED_LAPIS_BLOCK = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_lapis_block"), BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_BLOCK));
        POLISHED_LAPIS_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_lapis_stairs"), settings -> new StairBlock(POLISHED_LAPIS_BLOCK.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(POLISHED_LAPIS_BLOCK));
        POLISHED_LAPIS_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_lapis_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(POLISHED_LAPIS_BLOCK));
        VERTICAL_POLISHED_LAPIS_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_lapis_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(POLISHED_LAPIS_BLOCK));
        POLISHED_LAPIS_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_lapis_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(POLISHED_LAPIS_BLOCK).forceSolidOn());
        POLISHED_LAPIS_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_lapis_bricks"), BlockBehaviour.Properties.ofFullCopy(POLISHED_LAPIS_BLOCK));
        POLISHED_LAPIS_BRICK_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_lapis_brick_stairs"), settings -> new StairBlock(POLISHED_LAPIS_BRICKS.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(POLISHED_LAPIS_BRICKS));
        POLISHED_LAPIS_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_lapis_brick_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(POLISHED_LAPIS_BRICKS));
        VERTICAL_POLISHED_LAPIS_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_lapis_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(POLISHED_LAPIS_BRICKS));
        POLISHED_LAPIS_BRICK_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_lapis_brick_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(POLISHED_LAPIS_BRICKS).forceSolidOn());
        CRACKED_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS));
        MOSSY_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossy_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS));
        MOSSY_BRICK_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossy_brick_stairs"), settings -> new StairBlock(MOSSY_BRICKS.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(MOSSY_BRICKS));
        MOSSY_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossy_brick_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(MOSSY_BRICKS));
        VERTICAL_MOSSY_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_mossy_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(MOSSY_BRICKS));
        MOSSY_BRICK_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossy_brick_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(MOSSY_BRICKS).forceSolidOn());
        GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "glass"), settings -> new GlassBlock(settings, CRACKED_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS));
        BROWN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "brown_glass"), settings -> new GlassBlock(settings, CRACKED_BROWN_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_STAINED_GLASS));
        RED_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "red_glass"), settings -> new GlassBlock(settings, CRACKED_RED_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_STAINED_GLASS));
        ORANGE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "orange_glass"), settings -> new GlassBlock(settings, CRACKED_ORANGE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_STAINED_GLASS));
        YELLOW_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "yellow_glass"), settings -> new GlassBlock(settings, CRACKED_YELLOW_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_STAINED_GLASS));
        LIME_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "lime_glass"), settings -> new GlassBlock(settings, CRACKED_LIME_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_STAINED_GLASS));
        GREEN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "green_glass"), settings -> new GlassBlock(settings, CRACKED_GREEN_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_STAINED_GLASS));
        CYAN_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cyan_glass"), settings -> new GlassBlock(settings, CRACKED_CYAN_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_STAINED_GLASS));
        BLUE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "blue_glass"), settings -> new GlassBlock(settings, CRACKED_BLUE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_STAINED_GLASS));
        LIGHT_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "light_blue_glass"), settings -> new GlassBlock(settings, CRACKED_LIGHT_BLUE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_STAINED_GLASS));
        PINK_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "pink_glass"), settings -> new GlassBlock(settings, CRACKED_PINK_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_STAINED_GLASS));
        MAGENTA_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "magenta_glass"), settings -> new GlassBlock(settings, CRACKED_MAGENTA_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_STAINED_GLASS));
        PURPLE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "purple_glass"), settings -> new GlassBlock(settings, CRACKED_PURPLE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_STAINED_GLASS));
        BLACK_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "black_glass"), settings -> new GlassBlock(settings, CRACKED_BLACK_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_STAINED_GLASS));
        GRAY_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "gray_glass"), settings -> new GlassBlock(settings, CRACKED_GRAY_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_STAINED_GLASS));
        LIGHT_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "light_gray_glass"), settings -> new GlassBlock(settings, CRACKED_LIGHT_GRAY_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_STAINED_GLASS));
        WHITE_GLASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "white_glass"), settings -> new GlassBlock(settings, CRACKED_WHITE_GLASS.defaultBlockState()), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS));
        CHISELED_OAK_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_oak_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
        CHISELED_SPRUCE_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_spruce_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
        CHISELED_BIRCH_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_birch_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));
        CHISELED_JUNGLE_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_jungle_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));
        CHISELED_ACACIA_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_acacia_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
        CHISELED_DARK_OAK_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_dark_oak_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS));
        CHISELED_MANGROVE_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_mangrove_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
        CHISELED_CHERRY_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_cherry_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS));
        CHISELED_PALE_OAK_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_pale_oak_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS));
        CHISELED_BAMBOO_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_bamboo_planks"), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS));
        CHISELED_OAK_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_oak_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG));
        CHISELED_OAK_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_oak_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD));
        STRIPPED_CHISELED_OAK_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "stripped_chiseled_oak_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG));
        STRIPPED_CHISELED_OAK_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "stripped_chiseled_oak_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD));
        CHISELED_SPRUCE_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_spruce_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG));
        CHISELED_SPRUCE_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_spruce_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD));
        CHISELED_BIRCH_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_birch_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_LOG));
        CHISELED_BIRCH_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_birch_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_WOOD));
        CHISELED_JUNGLE_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_jungle_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LOG));
        CHISELED_JUNGLE_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_jungle_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_WOOD));
        CHISELED_ACACIA_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_acacia_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_LOG));
        CHISELED_ACACIA_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_acacia_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_WOOD));
        CHISELED_DARK_OAK_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_dark_oak_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_LOG));
        CHISELED_DARK_OAK_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_dark_oak_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_WOOD));
        CHISELED_MANGROVE_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_mangrove_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_LOG));
        CHISELED_MANGROVE_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_mangrove_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WOOD));
        CHISELED_CHERRY_LOG = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_cherry_log"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LOG));
        CHISELED_CHERRY_WOOD = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_cherry_wood"), RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WOOD));
        POLISHED_OAK_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_oak_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
        POLISHED_OAK_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_oak_stairs"), properties -> new StairBlock(POLISHED_OAK_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
        POLISHED_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_oak_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));
        VERTICAL_POLISHED_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_oak_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));
        POLISHED_SPRUCE_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_spruce_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
        POLISHED_SPRUCE_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_spruce_stairs"), properties -> new StairBlock(POLISHED_SPRUCE_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
        POLISHED_SPRUCE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_spruce_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB));
        VERTICAL_POLISHED_SPRUCE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_spruce_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB));
        POLISHED_BIRCH_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_birch_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));
        POLISHED_BIRCH_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_birch_stairs"), properties -> new StairBlock(POLISHED_BIRCH_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_STAIRS));
        POLISHED_BIRCH_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_birch_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB));
        VERTICAL_POLISHED_BIRCH_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_birch_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB));
        POLISHED_JUNGLE_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_jungle_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));
        POLISHED_JUNGLE_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_jungle_stairs"), properties -> new StairBlock(POLISHED_JUNGLE_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_STAIRS));
        POLISHED_JUNGLE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_jungle_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB));
        VERTICAL_POLISHED_JUNGLE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_jungle_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB));
        POLISHED_ACACIA_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_acacia_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
        POLISHED_ACACIA_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_acacia_stairs"), properties -> new StairBlock(POLISHED_ACACIA_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_STAIRS));
        POLISHED_ACACIA_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_acacia_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB));
        VERTICAL_POLISHED_ACACIA_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_acacia_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB));
        POLISHED_DARK_OAK_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_dark_oak_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS));
        POLISHED_DARK_OAK_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_dark_oak_stairs"), properties -> new StairBlock(POLISHED_DARK_OAK_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS));
        POLISHED_DARK_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_dark_oak_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB));
        VERTICAL_POLISHED_DARK_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_dark_oak_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB));
        POLISHED_MANGROVE_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_mangrove_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
        POLISHED_MANGROVE_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_mangrove_stairs"), properties -> new StairBlock(POLISHED_MANGROVE_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS));
        POLISHED_MANGROVE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_mangrove_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB));
        VERTICAL_POLISHED_MANGROVE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_mangrove_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB));
        POLISHED_CHERRY_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_cherry_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS));
        POLISHED_CHERRY_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_cherry_stairs"), properties -> new StairBlock(POLISHED_CHERRY_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_STAIRS));
        POLISHED_CHERRY_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_cherry_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB));
        VERTICAL_POLISHED_CHERRY_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_cherry_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB));
        POLISHED_BAMBOO_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_bamboo_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS));
        POLISHED_BAMBOO_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_bamboo_stairs"), properties -> new StairBlock(POLISHED_BAMBOO_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_STAIRS));
        POLISHED_BAMBOO_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_bamboo_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB));
        VERTICAL_POLISHED_BAMBOO_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_bamboo_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB));
        POLISHED_PALE_OAK_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_pale_oak_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS));
        POLISHED_PALE_OAK_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_pale_oak_stairs"), properties -> new StairBlock(POLISHED_PALE_OAK_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_STAIRS));
        POLISHED_PALE_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_pale_oak_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_SLAB));
        VERTICAL_POLISHED_PALE_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_pale_oak_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_SLAB));
        POLISHED_CRIMSON_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_crimson_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS));
        POLISHED_CRIMSON_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_crimson_stairs"), properties -> new StairBlock(POLISHED_CRIMSON_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_STAIRS));
        POLISHED_CRIMSON_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_crimson_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB));
        VERTICAL_POLISHED_CRIMSON_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_crimson_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB));
        POLISHED_WARPED_PLANKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_warped_planks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS));
        POLISHED_WARPED_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_warped_stairs"), properties -> new StairBlock(POLISHED_WARPED_PLANKS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STAIRS));
        POLISHED_WARPED_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_warped_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB));
        VERTICAL_POLISHED_WARPED_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_warped_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB));
        CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cut_copper_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.CUT_COPPER).forceSolidOn());
        EXPOSED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "exposed_cut_copper_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.EXPOSED_CUT_COPPER).forceSolidOn());
        WEATHERED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "weathered_cut_copper_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.WEATHERED_CUT_COPPER).forceSolidOn());
        OXIDIZED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "oxidized_cut_copper_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.OXIDIZED_CUT_COPPER).forceSolidOn());
        WAXED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "waxed_cut_copper_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.WAXED_CUT_COPPER).forceSolidOn());
        WAXED_EXPOSED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "waxed_exposed_cut_copper_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.WAXED_EXPOSED_CUT_COPPER).forceSolidOn());
        WAXED_WEATHERED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "waxed_weathered_cut_copper_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.WAXED_WEATHERED_CUT_COPPER).forceSolidOn());
        WAXED_OXIDIZED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "waxed_oxidized_cut_copper_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.WAXED_OXIDIZED_CUT_COPPER).forceSolidOn());
        POLISHED_CALCITE = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_calcite"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
        POLISHED_CALCITE_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_calcite_stairs"), settings -> new StairBlock(POLISHED_CALCITE.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(POLISHED_CALCITE));
        POLISHED_CALCITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_calcite_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(POLISHED_CALCITE));
        VERTICAL_POLISHED_CALCITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_calcite_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(POLISHED_CALCITE_SLAB));
        POLISHED_CALCITE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_calcite_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(POLISHED_CALCITE).forceSolidOn());

        PACKED_MUD_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "packed_mud_stairs"), settings -> new StairBlock(Blocks.PACKED_MUD.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD));
        PACKED_MUD_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "packed_mud_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD));
        VERTICAL_PACKED_MUD_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_packed_mud_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD));
        PACKED_MUD_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "packed_mud_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.PACKED_MUD).forceSolidOn());
        NETHERRACK_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "netherrack_stairs"), settings -> new StairBlock(Blocks.NETHERRACK.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
        NETHERRACK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "netherrack_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
        VERTICAL_NETHERRACK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_netherrack_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
        NETHERRACK_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "netherrack_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.NETHERRACK).forceSolidOn());
        END_STONE_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "end_stone_stairs"), settings -> new StairBlock(Blocks.END_STONE.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE));
        END_STONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "end_stone_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE));
        VERTICAL_END_STONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_end_stone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE));
        END_STONE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "end_stone_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.END_STONE).forceSolidOn());
        DEEPSLATE_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "deepslate_stairs"), settings -> new StairBlock(Blocks.DEEPSLATE.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE));
        DEEPSLATE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "deepslate_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE));
        VERTICAL_DEEPSLATE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_deepslate_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE));
        DEEPSLATE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "deepslate_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE).forceSolidOn());
        MOSSY_MUD_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossy_mud_bricks"), Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS));
        MOSSY_MUD_BRICK_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossy_mud_brick_stairs"), settings -> new StairBlock(MOSSY_MUD_BRICKS.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS));
        MOSSY_MUD_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossy_mud_brick_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS));
        VERTICAL_MOSSY_MUD_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_mossy_mud_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS));
        MOSSY_MUD_BRICK_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "mossy_mud_brick_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(MOSSY_MUD_BRICKS).forceSolidOn());

        StrippableBlockRegistry.register(CHISELED_OAK_LOG, STRIPPED_CHISELED_OAK_LOG);
        StrippableBlockRegistry.register(CHISELED_OAK_WOOD, STRIPPED_CHISELED_OAK_WOOD);

        OxidizableBlocksRegistry.registerWaxableBlockPair(CUT_COPPER_WALL, WAXED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_CUT_COPPER_WALL, WAXED_EXPOSED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_CUT_COPPER_WALL, WAXED_WEATHERED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_CUT_COPPER_WALL, WAXED_OXIDIZED_CUT_COPPER_WALL);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(CUT_COPPER_WALL, EXPOSED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_CUT_COPPER_WALL, WEATHERED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_CUT_COPPER_WALL, OXIDIZED_CUT_COPPER_WALL);

        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_OAK_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_OAK_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_OAK_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_OAK_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_OAK_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_OAK_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_OAK_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_CHISELED_OAK_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_CHISELED_OAK_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_SPRUCE_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_SPRUCE_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_SPRUCE_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_SPRUCE_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_BIRCH_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_BIRCH_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_BIRCH_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_BIRCH_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_JUNGLE_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_JUNGLE_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_JUNGLE_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_JUNGLE_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_DARK_OAK_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_DARK_OAK_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_DARK_OAK_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_DARK_OAK_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_ACACIA_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_ACACIA_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_ACACIA_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_ACACIA_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_MANGROVE_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_MANGROVE_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_MANGROVE_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_MANGROVE_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_CHERRY_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_CHERRY_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_CHERRY_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_CHERRY_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_PALE_OAK_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_PALE_OAK_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHISELED_BAMBOO_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_BAMBOO_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_SPRUCE_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_SPRUCE_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_SPRUCE_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_BIRCH_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_BIRCH_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_BIRCH_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_JUNGLE_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_JUNGLE_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_JUNGLE_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_ACACIA_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_ACACIA_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_ACACIA_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_DARK_OAK_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_DARK_OAK_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_DARK_OAK_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_MANGROVE_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_MANGROVE_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_MANGROVE_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_CHERRY_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_CHERRY_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_CHERRY_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_BAMBOO_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_BAMBOO_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_BAMBOO_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_PALE_OAK_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(POLISHED_PALE_OAK_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(VERTICAL_POLISHED_PALE_OAK_SLAB, 5, 20);
    }

    @Environment(EnvType.CLIENT)
    private static void registerDecorativeBlocksClient() {
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.TRANSLUCENT,
                CHISELED_GLASS,
                CHISELED_GLASS_PANE,
                GLASS,
                CRACKED_GLASS,
                BROWN_GLASS, CHISELED_BROWN_GLASS,
                CHISELED_BROWN_GLASS_PANE,
                CRACKED_BROWN_GLASS,
                RED_GLASS, CHISELED_RED_GLASS,
                CHISELED_RED_GLASS_PANE,
                CRACKED_RED_GLASS,
                ORANGE_GLASS, CHISELED_ORANGE_GLASS,
                CHISELED_ORANGE_GLASS_PANE,
                CRACKED_ORANGE_GLASS,
                YELLOW_GLASS, CHISELED_YELLOW_GLASS,
                CHISELED_YELLOW_GLASS_PANE,
                CRACKED_YELLOW_GLASS,
                LIME_GLASS, CHISELED_LIME_GLASS,
                CHISELED_LIME_GLASS_PANE,
                CRACKED_LIME_GLASS,
                GREEN_GLASS, CHISELED_GREEN_GLASS,
                CHISELED_GREEN_GLASS_PANE,
                CRACKED_GREEN_GLASS,
                CYAN_GLASS, CHISELED_CYAN_GLASS,
                CHISELED_CYAN_GLASS_PANE,
                CRACKED_CYAN_GLASS,
                BLUE_GLASS, CHISELED_BLUE_GLASS,
                CHISELED_BLUE_GLASS_PANE,
                CRACKED_BLUE_GLASS,
                LIGHT_BLUE_GLASS, CHISELED_LIGHT_BLUE_GLASS,
                CHISELED_LIGHT_BLUE_GLASS_PANE,
                CRACKED_LIGHT_BLUE_GLASS,
                PINK_GLASS, CHISELED_PINK_GLASS,
                CHISELED_PINK_GLASS_PANE,
                CRACKED_PINK_GLASS,
                MAGENTA_GLASS, CHISELED_MAGENTA_GLASS,
                CHISELED_MAGENTA_GLASS_PANE,
                CRACKED_MAGENTA_GLASS,
                PURPLE_GLASS, CHISELED_PURPLE_GLASS,
                CHISELED_PURPLE_GLASS_PANE,
                CRACKED_PURPLE_GLASS,
                BLACK_GLASS, CHISELED_BLACK_GLASS,
                CHISELED_BLACK_GLASS_PANE,
                CRACKED_BLACK_GLASS,
                GRAY_GLASS, CHISELED_GRAY_GLASS,
                CHISELED_GRAY_GLASS_PANE,
                CRACKED_GRAY_GLASS,
                LIGHT_GRAY_GLASS, CHISELED_LIGHT_GRAY_GLASS,
                CHISELED_LIGHT_GRAY_GLASS_PANE,
                CRACKED_LIGHT_GRAY_GLASS,
                WHITE_GLASS, CHISELED_WHITE_GLASS,
                CHISELED_WHITE_GLASS_PANE,
                CRACKED_WHITE_GLASS
        );
    }
    // endregion

    // region Vertical Slabs
    public static Block VERTICAL_OAK_SLAB;
    public static Block VERTICAL_SPRUCE_SLAB;
    public static Block VERTICAL_BIRCH_SLAB;
    public static Block VERTICAL_JUNGLE_SLAB;
    public static Block VERTICAL_DARK_OAK_SLAB;
    public static Block VERTICAL_ACACIA_SLAB;
    public static Block VERTICAL_MANGROVE_SLAB;
    public static Block VERTICAL_CHERRY_SLAB;
    public static Block VERTICAL_PALE_OAK_SLAB;
    public static Block VERTICAL_BAMBOO_SLAB;
    public static Block VERTICAL_BAMBOO_MOSAIC_SLAB;
    public static Block VERTICAL_CRIMSON_SLAB;
    public static Block VERTICAL_WARPED_SLAB;
    public static Block VERTICAL_STONE_SLAB;
    public static Block VERTICAL_SMOOTH_STONE_SLAB;
    public static Block VERTICAL_SANDSTONE_SLAB;
    public static Block VERTICAL_CUT_SANDSTONE_SLAB;
    public static Block VERTICAL_PETRIFIED_OAK_SLAB;
    public static Block VERTICAL_COBBLESTONE_SLAB;
    public static Block VERTICAL_BRICK_SLAB;
    public static Block VERTICAL_STONE_BRICK_SLAB;
    public static Block VERTICAL_MUD_BRICK_SLAB;
    public static Block VERTICAL_NETHER_BRICK_SLAB;
    public static Block VERTICAL_QUARTZ_SLAB;
    public static Block VERTICAL_RED_SANDSTONE_SLAB;
    public static Block VERTICAL_CUT_RED_SANDSTONE_SLAB;
    public static Block VERTICAL_PURPUR_SLAB;
    public static Block VERTICAL_PRISMARINE_SLAB;
    public static Block VERTICAL_PRISMARINE_BRICK_SLAB;
    public static Block VERTICAL_DARK_PRISMARINE_SLAB;
    public static Block VERTICAL_GRANITE_SLAB;
    public static Block VERTICAL_POLISHED_GRANITE_SLAB;
    public static Block VERTICAL_ANDESITE_SLAB;
    public static Block VERTICAL_POLISHED_ANDESITE_SLAB;
    public static Block VERTICAL_DIORITE_SLAB;
    public static Block VERTICAL_POLISHED_DIORITE_SLAB;
    public static Block VERTICAL_SMOOTH_SANDSTONE_SLAB;
    public static Block VERTICAL_SMOOTH_QUARTZ_SLAB;
    public static Block VERTICAL_SMOOTH_RED_SANDSTONE_SLAB;
    public static Block VERTICAL_MOSSY_STONE_BRICK_SLAB;
    public static Block VERTICAL_END_STONE_BRICK_SLAB;
    public static Block VERTICAL_RED_NETHER_BRICK_SLAB;
    public static Block VERTICAL_BLACKSTONE_SLAB;
    public static Block VERTICAL_POLISHED_BLACKSTONE_SLAB;
    public static Block VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB;
    public static Block VERTICAL_OXIDIZED_CUT_COPPER_SLAB;
    public static Block VERTICAL_WEATHERED_CUT_COPPER_SLAB;
    public static Block VERTICAL_EXPOSED_CUT_COPPER_SLAB;
    public static Block VERTICAL_CUT_COPPER_SLAB;
    public static Block VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB;
    public static Block VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB;
    public static Block VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB;
    public static Block VERTICAL_WAXED_CUT_COPPER_SLAB;
    public static Block VERTICAL_COBBLED_DEEPSLATE_SLAB;
    public static Block VERTICAL_POLISHED_DEEPSLATE_SLAB;
    public static Block VERTICAL_DEEPSLATE_TILE_SLAB;
    public static Block VERTICAL_DEEPSLATE_BRICK_SLAB;
    public static Block VERTICAL_MOSSY_COBBLESTONE_SLAB;
    public static Block VERTICAL_TUFF_SLAB;
    public static Block VERTICAL_TUFF_BRICK_SLAB;
    public static Block VERTICAL_POLISHED_TUFF_SLAB;
    public static Block VERTICAL_RESIN_BRICK_SLAB;

    private static void registerVerticalSlabs() {
        VERTICAL_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_oak_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB));
        VERTICAL_SPRUCE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_spruce_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB));
        VERTICAL_BIRCH_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_birch_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_SLAB));
        VERTICAL_JUNGLE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_jungle_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SLAB));
        VERTICAL_DARK_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_dark_oak_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SLAB));
        VERTICAL_ACACIA_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_acacia_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SLAB));
        VERTICAL_MANGROVE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_mangrove_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB));
        VERTICAL_CHERRY_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_cherry_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SLAB));
        VERTICAL_PALE_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_pale_oak_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_SLAB));
        VERTICAL_BAMBOO_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_bamboo_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SLAB));
        VERTICAL_BAMBOO_MOSAIC_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_bamboo_mosaic_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_MOSAIC_SLAB));
        VERTICAL_CRIMSON_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_crimson_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_SLAB));
        VERTICAL_WARPED_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_warped_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SLAB));
        VERTICAL_STONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_stone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
        VERTICAL_SMOOTH_STONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_smooth_stone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE_SLAB));
        VERTICAL_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_sandstone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE_SLAB));
        VERTICAL_CUT_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_cut_sandstone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_SANDSTONE_SLAB));
        VERTICAL_PETRIFIED_OAK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_petrified_oak_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PETRIFIED_OAK_SLAB));
        VERTICAL_COBBLESTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_cobblestone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE_SLAB));
        VERTICAL_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
        VERTICAL_STONE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_stone_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB));
        VERTICAL_MUD_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_mud_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICK_SLAB));
        VERTICAL_NETHER_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_nether_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICK_SLAB));
        VERTICAL_QUARTZ_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_quartz_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_SLAB));
        VERTICAL_RED_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_red_sandstone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE_SLAB));
        VERTICAL_CUT_RED_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_cut_red_sandstone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_RED_SANDSTONE_SLAB));
        VERTICAL_PURPUR_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_purpur_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_SLAB));
        VERTICAL_PRISMARINE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_prismarine_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_SLAB));
        VERTICAL_PRISMARINE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_prismarine_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICK_SLAB));
        VERTICAL_DARK_PRISMARINE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_dark_prismarine_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE_SLAB));
        VERTICAL_GRANITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_granite_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE_SLAB));
        VERTICAL_POLISHED_GRANITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_granite_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE_SLAB));
        VERTICAL_ANDESITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_andesite_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE_SLAB));
        VERTICAL_POLISHED_ANDESITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_andesite_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE_SLAB));
        VERTICAL_DIORITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_diorite_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE_SLAB));
        VERTICAL_POLISHED_DIORITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_diorite_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE_SLAB));
        VERTICAL_SMOOTH_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_smooth_sandstone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE_SLAB));
        VERTICAL_SMOOTH_QUARTZ_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_smooth_quartz_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ_SLAB));
        VERTICAL_SMOOTH_RED_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_smooth_red_sandstone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE_SLAB));
        VERTICAL_MOSSY_STONE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_mossy_stone_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICK_SLAB));
        VERTICAL_END_STONE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_end_stone_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICK_SLAB));
        VERTICAL_RED_NETHER_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_red_nether_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICK_SLAB));
        VERTICAL_BLACKSTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_blackstone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE_SLAB));
        VERTICAL_POLISHED_BLACKSTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_blackstone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_SLAB));
        VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_blackstone_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB));
        VERTICAL_OXIDIZED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_oxidized_cut_copper_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OXIDIZED_CUT_COPPER_SLAB));
        VERTICAL_WEATHERED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_weathered_cut_copper_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WEATHERED_CUT_COPPER_SLAB));
        VERTICAL_EXPOSED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_exposed_cut_copper_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.EXPOSED_CUT_COPPER_SLAB));
        VERTICAL_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_cut_copper_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CUT_COPPER_SLAB));
        VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_waxed_oxidized_cut_copper_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB));
        VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_waxed_weathered_cut_copper_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB));
        VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_waxed_exposed_cut_copper_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB));
        VERTICAL_WAXED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_waxed_cut_copper_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_CUT_COPPER_SLAB));
        VERTICAL_COBBLED_DEEPSLATE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_cobbled_deepslate_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE_SLAB));
        VERTICAL_POLISHED_DEEPSLATE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_deepslate_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE_SLAB));
        VERTICAL_DEEPSLATE_TILE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_deepslate_tile_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_TILE_SLAB));
        VERTICAL_DEEPSLATE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_deepslate_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICK_SLAB));
        VERTICAL_MOSSY_COBBLESTONE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_mossy_cobblestone_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE_SLAB));
        VERTICAL_TUFF_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_tuff_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF_SLAB));
        VERTICAL_TUFF_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_tuff_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF_BRICK_SLAB));
        VERTICAL_POLISHED_TUFF_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_polished_tuff_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_TUFF_SLAB));
        VERTICAL_RESIN_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_resin_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RESIN_BRICK_SLAB));

        OxidizableBlocksRegistry.registerOxidizableBlockPair(VERTICAL_CUT_COPPER_SLAB, VERTICAL_EXPOSED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(VERTICAL_EXPOSED_CUT_COPPER_SLAB, VERTICAL_WEATHERED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(VERTICAL_WEATHERED_CUT_COPPER_SLAB, VERTICAL_OXIDIZED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(VERTICAL_CUT_COPPER_SLAB, VERTICAL_WAXED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(VERTICAL_EXPOSED_CUT_COPPER_SLAB, VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(VERTICAL_WEATHERED_CUT_COPPER_SLAB, VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(VERTICAL_OXIDIZED_CUT_COPPER_SLAB, VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB);
    }
    // endregion

    // region Missing Blocks
    public static Block QUARTZ_BRICK_STAIRS;
    public static Block QUARTZ_BRICK_SLAB;
    public static Block VERTICAL_QUARTZ_BRICK_SLAB;
    public static Block QUARTZ_BRICK_WALL;
    public static Block PRISMARINE_BRICK_WALL;
    public static Block DARK_PRISMARINE_WALL;
    public static Block SMOOTH_SANDSTONE_WALL;
    public static Block SMOOTH_RED_SANDSTONE_WALL;
    public static Block POLISHED_GRANITE_WALL;
    public static Block POLISHED_ANDESITE_WALL;
    public static Block POLISHED_DIORITE_WALL;
    public static Block PURPUR_WALL;
    public static Block POTTED_SHORT_GRASS;
    public static Block CRACKED_MUD_BRICKS;
    public static Block CRACKED_QUARTZ_BRICKS;
    public static Block CRACKED_RED_NETHER_BRICKS;
    public static Block CRACKED_END_STONE_BRICKS;
    public static Block CHISELED_PURPUR;
    public static Block POLISHED_BLACKSTONE_PILLAR;
    public static Block CRACKED_TUFF_BRICKS;
    public static Block CALCITE_STAIRS;
    public static Block CALCITE_SLAB;
    public static Block VERTICAL_CALCITE_SLAB;
    public static Block CALCITE_WALL;

    private static void registerMissingBlocks() {
        QUARTZ_BRICK_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "quartz_brick_stairs"), settings -> new StairBlock(Blocks.QUARTZ_BRICKS.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS));
        QUARTZ_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "quartz_brick_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS));
        VERTICAL_QUARTZ_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_quartz_brick_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(QUARTZ_BRICK_SLAB));
        QUARTZ_BRICK_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "quartz_brick_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.QUARTZ_BRICKS).forceSolidOn());
        PRISMARINE_BRICK_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "prismarine_brick_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.PRISMARINE_BRICKS).forceSolidOn());
        DARK_PRISMARINE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "dark_prismarine_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.DARK_PRISMARINE).forceSolidOn());
        SMOOTH_SANDSTONE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "smooth_sandstone_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.SMOOTH_SANDSTONE).forceSolidOn());
        SMOOTH_RED_SANDSTONE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "smooth_red_sandstone_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.SMOOTH_RED_SANDSTONE).forceSolidOn());
        POLISHED_GRANITE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_granite_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.POLISHED_GRANITE).forceSolidOn());
        POLISHED_ANDESITE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_andesite_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.POLISHED_ANDESITE).forceSolidOn());
        POLISHED_DIORITE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_diorite_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.POLISHED_DIORITE).forceSolidOn());
        PURPUR_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "purpur_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.PURPUR_BLOCK).forceSolidOn());
        POTTED_SHORT_GRASS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "potted_short_grass"), settings -> new FlowerPotBlock(Blocks.SHORT_GRASS, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
        CRACKED_MUD_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_mud_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS));
        CRACKED_QUARTZ_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_quartz_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS));
        CRACKED_RED_NETHER_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_red_nether_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS));
        CRACKED_END_STONE_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_end_stone_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS));
        CHISELED_PURPUR = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "chiseled_purpur"), BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK));
        POLISHED_BLACKSTONE_PILLAR = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "polished_blackstone_pillar"), FacingBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE));
        CRACKED_TUFF_BRICKS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "cracked_tuff_bricks"), BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF_BRICKS));
        CALCITE_STAIRS = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "calcite_stairs"), settings -> new StairBlock(Blocks.CALCITE.defaultBlockState(), settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
        CALCITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "calcite_slab"), SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
        VERTICAL_CALCITE_SLAB = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "vertical_calcite_slab"), VerticalSlabBlock::new, BlockBehaviour.Properties.ofFullCopy(CALCITE_SLAB));
        CALCITE_WALL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "calcite_wall"), WallBlock::new, BlockBehaviour.Properties.ofLegacyCopy(Blocks.CALCITE).forceSolidOn());
    }

    @Environment(EnvType.CLIENT)
    private static void registerMissingBlocksClient() {
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, POTTED_SHORT_GRASS);
    }
    // endregion

    // region Misc Blocks
    public static Block SAWMILL;
    public static Block KILN;

    private static void registerMiscBlocks() {
        SAWMILL = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "sawmill"), SawmillBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONECUTTER).strength(2f).sound(SoundType.WOOD));
        KILN = RegistryHelper.registerBlock(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "kiln"), KilnBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLAST_FURNACE));
    }

    @Environment(EnvType.CLIENT)
    private static void registerMiscBlocksClient() {
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
                SAWMILL
        );
    }
    // endregion
}
