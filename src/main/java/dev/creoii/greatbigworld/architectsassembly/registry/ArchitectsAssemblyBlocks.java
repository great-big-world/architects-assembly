package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.*;
import dev.creoii.greatbigworld.block.FacingBlock;
import dev.creoii.greatbigworld.util.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

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
    public static Block CHISELED_OAK_LOG;
    public static Block CHISELED_OAK_WOOD;
    public static Block STRIPPED_CHISELED_OAK_LOG;
    public static Block STRIPPED_CHISELED_OAK_WOOD;
    public static Block CUT_COPPER_WALL;
    public static Block EXPOSED_CUT_COPPER_WALL;
    public static Block WEATHERED_CUT_COPPER_WALL;
    public static Block OXIDIZED_CUT_COPPER_WALL;
    public static Block WAXED_CUT_COPPER_WALL;
    public static Block WAXED_EXPOSED_CUT_COPPER_WALL;
    public static Block WAXED_WEATHERED_CUT_COPPER_WALL;
    public static Block WAXED_OXIDIZED_CUT_COPPER_WALL;

    private static void registerDecorativeBlocks() {
        CRACKED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_glass"), settings -> new CrackedGlassBlock(settings, Blocks.GLASS::getDefaultState, () -> ArchitectsAssemblyItems.GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.GLASS).breakInstantly());
        CHISELED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_glass"), settings -> new GlassBlock(settings, CRACKED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GLASS));
        CHISELED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.GLASS_PANE));
        CRACKED_BROWN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_brown_glass"), settings -> new CrackedGlassBlock(settings, () -> BROWN_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.BROWN_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS).breakInstantly());
        CHISELED_BROWN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_brown_glass"), settings -> new GlassBlock(settings, CRACKED_BROWN_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS));
        CHISELED_BROWN_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_brown_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS_PANE));
        CRACKED_RED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_red_glass"), settings -> new CrackedGlassBlock(settings, () -> RED_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.RED_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS).breakInstantly());
        CHISELED_RED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_red_glass"), settings -> new GlassBlock(settings, CRACKED_RED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS));
        CHISELED_RED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_red_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS_PANE));
        CRACKED_ORANGE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_orange_glass"), settings -> new CrackedGlassBlock(settings, () -> ORANGE_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.ORANGE_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS).breakInstantly());
        CHISELED_ORANGE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_orange_glass"), settings -> new GlassBlock(settings, CRACKED_ORANGE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS));
        CHISELED_ORANGE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_orange_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS_PANE));
        CRACKED_YELLOW_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_yellow_glass"), settings -> new CrackedGlassBlock(settings, () -> YELLOW_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.YELLOW_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS).breakInstantly());
        CHISELED_YELLOW_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_yellow_glass"), settings -> new GlassBlock(settings, CRACKED_YELLOW_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS));
        CHISELED_YELLOW_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_yellow_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS_PANE));
        CRACKED_LIME_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_lime_glass"), settings -> new CrackedGlassBlock(settings, () -> LIME_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.LIME_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS).breakInstantly());
        CHISELED_LIME_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_lime_glass"), settings -> new GlassBlock(settings, CRACKED_LIME_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS));
        CHISELED_LIME_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_lime_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS_PANE));
        CRACKED_GREEN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_green_glass"), settings -> new CrackedGlassBlock(settings, () -> GREEN_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.GREEN_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS).breakInstantly());
        CHISELED_GREEN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_green_glass"), settings -> new GlassBlock(settings, CRACKED_GREEN_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS));
        CHISELED_GREEN_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_green_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS_PANE));
        CRACKED_CYAN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_cyan_glass"), settings -> new CrackedGlassBlock(settings, () -> CYAN_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.CYAN_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS).breakInstantly());
        CHISELED_CYAN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_cyan_glass"), settings -> new GlassBlock(settings, CRACKED_CYAN_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS));
        CHISELED_CYAN_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_cyan_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS_PANE));
        CRACKED_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_blue_glass"), settings -> new CrackedGlassBlock(settings, () -> BLUE_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.BLUE_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS).breakInstantly());
        CHISELED_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_blue_glass"), settings -> new GlassBlock(settings, CRACKED_BLUE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS));
        CHISELED_BLUE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_blue_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS_PANE));
        CRACKED_LIGHT_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_light_blue_glass"), settings -> new CrackedGlassBlock(settings, () -> LIGHT_BLUE_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.LIGHT_BLUE_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS).breakInstantly());
        CHISELED_LIGHT_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_blue_glass"), settings -> new GlassBlock(settings, CRACKED_LIGHT_BLUE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS));
        CHISELED_LIGHT_BLUE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_blue_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE));
        CRACKED_PINK_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_pink_glass"), settings -> new CrackedGlassBlock(settings, () -> PINK_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.PINK_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS).breakInstantly());
        CHISELED_PINK_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_pink_glass"), settings -> new GlassBlock(settings, CRACKED_PINK_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS));
        CHISELED_PINK_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_pink_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS_PANE));
        CRACKED_MAGENTA_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_magenta_glass"), settings -> new CrackedGlassBlock(settings, () -> MAGENTA_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.MAGENTA_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS).breakInstantly());
        CHISELED_MAGENTA_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_magenta_glass"), settings -> new GlassBlock(settings, CRACKED_MAGENTA_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS));
        CHISELED_MAGENTA_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_magenta_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS_PANE));
        CRACKED_PURPLE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_purple_glass"), settings -> new CrackedGlassBlock(settings, () -> PURPLE_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.PURPLE_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS).breakInstantly());
        CHISELED_PURPLE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_purple_glass"), settings -> new GlassBlock(settings, CRACKED_PURPLE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS));
        CHISELED_PURPLE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_purple_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS_PANE));
        CRACKED_BLACK_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_black_glass"), settings -> new CrackedGlassBlock(settings, () -> BLACK_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.BLACK_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS).breakInstantly());
        CHISELED_BLACK_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_black_glass"), settings -> new GlassBlock(settings, CRACKED_BLACK_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS));
        CHISELED_BLACK_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_black_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE));
        CRACKED_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_gray_glass"), settings -> new CrackedGlassBlock(settings, () -> GRAY_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.GRAY_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS).breakInstantly());
        CHISELED_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_gray_glass"), settings -> new GlassBlock(settings, CRACKED_GRAY_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS));
        CHISELED_GRAY_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_gray_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS_PANE));
        CRACKED_LIGHT_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_light_gray_glass"), settings -> new CrackedGlassBlock(settings, () -> LIGHT_GRAY_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.LIGHT_GRAY_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS).breakInstantly());
        CHISELED_LIGHT_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_gray_glass"), settings -> new GlassBlock(settings, CRACKED_LIGHT_GRAY_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS));
        CHISELED_LIGHT_GRAY_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_gray_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE));
        CRACKED_WHITE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_white_glass"), settings -> new CrackedGlassBlock(settings, () -> WHITE_GLASS.getDefaultState(), () -> ArchitectsAssemblyItems.WHITE_GLASS_SHARD), AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS).breakInstantly());
        CHISELED_WHITE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_white_glass"), settings -> new GlassBlock(settings, CRACKED_WHITE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS));
        CHISELED_WHITE_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_white_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS_PANE));
        LAPIS_STAIRS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "lapis_stairs"), settings -> new StairsBlock(Blocks.LAPIS_BLOCK.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.LAPIS_BLOCK));
        LAPIS_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "lapis_slab"), SlabBlock::new, AbstractBlock.Settings.copy(Blocks.LAPIS_BLOCK));
        VERTICAL_LAPIS_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_lapis_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.LAPIS_BLOCK));
        LAPIS_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "lapis_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.LAPIS_BLOCK).solid());
        POLISHED_LAPIS_BLOCK = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_block"), AbstractBlock.Settings.copy(Blocks.LAPIS_BLOCK));
        POLISHED_LAPIS_STAIRS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_stairs"), settings -> new StairsBlock(POLISHED_LAPIS_BLOCK.getDefaultState(), settings), AbstractBlock.Settings.copy(POLISHED_LAPIS_BLOCK));
        POLISHED_LAPIS_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_slab"), SlabBlock::new, AbstractBlock.Settings.copy(POLISHED_LAPIS_BLOCK));
        VERTICAL_POLISHED_LAPIS_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_lapis_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(POLISHED_LAPIS_BLOCK));
        POLISHED_LAPIS_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(POLISHED_LAPIS_BLOCK).solid());
        POLISHED_LAPIS_BRICKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_bricks"), AbstractBlock.Settings.copy(POLISHED_LAPIS_BLOCK));
        POLISHED_LAPIS_BRICK_STAIRS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_brick_stairs"), settings -> new StairsBlock(POLISHED_LAPIS_BRICKS.getDefaultState(), settings), AbstractBlock.Settings.copy(POLISHED_LAPIS_BRICKS));
        POLISHED_LAPIS_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_brick_slab"), SlabBlock::new, AbstractBlock.Settings.copy(POLISHED_LAPIS_BRICKS));
        VERTICAL_POLISHED_LAPIS_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_lapis_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(POLISHED_LAPIS_BRICKS));
        POLISHED_LAPIS_BRICK_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_brick_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(POLISHED_LAPIS_BRICKS).solid());
        CRACKED_BRICKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_bricks"), AbstractBlock.Settings.copy(Blocks.BRICKS));
        MOSSY_BRICKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_bricks"), AbstractBlock.Settings.copy(Blocks.BRICKS));
        MOSSY_BRICK_STAIRS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_brick_stairs"), settings -> new StairsBlock(MOSSY_BRICKS.getDefaultState(), settings), AbstractBlock.Settings.copy(MOSSY_BRICKS));
        MOSSY_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_brick_slab"), SlabBlock::new, AbstractBlock.Settings.copy(MOSSY_BRICKS));
        VERTICAL_MOSSY_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_mossy_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(MOSSY_BRICKS));
        MOSSY_BRICK_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_brick_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(MOSSY_BRICKS).solid());
        GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "glass"), settings -> new GlassBlock(settings, CRACKED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GLASS));
        BROWN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "brown_glass"), settings -> new GlassBlock(settings, CRACKED_BROWN_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS));
        RED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "red_glass"), settings -> new GlassBlock(settings, CRACKED_RED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS));
        ORANGE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "orange_glass"), settings -> new GlassBlock(settings, CRACKED_ORANGE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS));
        YELLOW_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "yellow_glass"), settings -> new GlassBlock(settings, CRACKED_YELLOW_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS));
        LIME_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "lime_glass"), settings -> new GlassBlock(settings, CRACKED_LIME_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS));
        GREEN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "green_glass"), settings -> new GlassBlock(settings, CRACKED_GREEN_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS));
        CYAN_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cyan_glass"), settings -> new GlassBlock(settings, CRACKED_CYAN_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS));
        BLUE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "blue_glass"), settings -> new GlassBlock(settings, CRACKED_BLUE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS));
        LIGHT_BLUE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "light_blue_glass"), settings -> new GlassBlock(settings, CRACKED_LIGHT_BLUE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS));
        PINK_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "pink_glass"), settings -> new GlassBlock(settings, CRACKED_PINK_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS));
        MAGENTA_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "magenta_glass"), settings -> new GlassBlock(settings, CRACKED_MAGENTA_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS));
        PURPLE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "purple_glass"), settings -> new GlassBlock(settings, CRACKED_PURPLE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS));
        BLACK_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "black_glass"), settings -> new GlassBlock(settings, CRACKED_BLACK_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS));
        GRAY_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "gray_glass"), settings -> new GlassBlock(settings, CRACKED_GRAY_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS));
        LIGHT_GRAY_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "light_gray_glass"), settings -> new GlassBlock(settings, CRACKED_LIGHT_GRAY_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS));
        WHITE_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "white_glass"), settings -> new GlassBlock(settings, CRACKED_WHITE_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS));
        CHISELED_OAK_PLANKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_oak_planks"), AbstractBlock.Settings.copy(Blocks.OAK_PLANKS));
        CHISELED_OAK_LOG = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_oak_log"), PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LOG));
        CHISELED_OAK_WOOD = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_oak_wood"), PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_WOOD));
        STRIPPED_CHISELED_OAK_LOG = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "stripped_chiseled_oak_log"), PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG));
        STRIPPED_CHISELED_OAK_WOOD = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "stripped_chiseled_oak_wood"), PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD));
        CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cut_copper_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.CUT_COPPER).solid());
        EXPOSED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "exposed_cut_copper_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.EXPOSED_CUT_COPPER).solid());
        WEATHERED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "weathered_cut_copper_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.WEATHERED_CUT_COPPER).solid());
        OXIDIZED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "oxidized_cut_copper_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.OXIDIZED_CUT_COPPER).solid());
        WAXED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_cut_copper_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.WAXED_CUT_COPPER).solid());
        WAXED_EXPOSED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_exposed_cut_copper_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.WAXED_EXPOSED_CUT_COPPER).solid());
        WAXED_WEATHERED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_weathered_cut_copper_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.WAXED_WEATHERED_CUT_COPPER).solid());
        WAXED_OXIDIZED_CUT_COPPER_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_oxidized_cut_copper_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.WAXED_OXIDIZED_CUT_COPPER).solid());

        StrippableBlockRegistry.register(CHISELED_OAK_LOG, STRIPPED_CHISELED_OAK_LOG);
        StrippableBlockRegistry.register(CHISELED_OAK_WOOD, STRIPPED_CHISELED_OAK_WOOD);

        OxidizableBlocksRegistry.registerWaxableBlockPair(CUT_COPPER_WALL, WAXED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_CUT_COPPER_WALL, WAXED_EXPOSED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_CUT_COPPER_WALL, WAXED_WEATHERED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_CUT_COPPER_WALL, WAXED_OXIDIZED_CUT_COPPER_WALL);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(CUT_COPPER_WALL, EXPOSED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_CUT_COPPER_WALL, WEATHERED_CUT_COPPER_WALL);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_CUT_COPPER_WALL, OXIDIZED_CUT_COPPER_WALL);
    }

    @Environment(EnvType.CLIENT)
    private static void registerDecorativeBlocksClient() {
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT_MIPPED,
                CHISELED_GLASS,
                CHISELED_GLASS_PANE,
                GLASS,
                CRACKED_GLASS
        );
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.TRANSLUCENT,
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
        VERTICAL_OAK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_oak_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_SLAB));
        VERTICAL_SPRUCE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_spruce_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.SPRUCE_SLAB));
        VERTICAL_BIRCH_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_birch_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.BIRCH_SLAB));
        VERTICAL_JUNGLE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_jungle_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.JUNGLE_SLAB));
        VERTICAL_DARK_OAK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_dark_oak_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.DARK_OAK_SLAB));
        VERTICAL_ACACIA_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_acacia_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.ACACIA_SLAB));
        VERTICAL_MANGROVE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_mangrove_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.MANGROVE_SLAB));
        VERTICAL_CHERRY_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_cherry_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.CHERRY_SLAB));
        VERTICAL_PALE_OAK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_pale_oak_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.PALE_OAK_SLAB));
        VERTICAL_BAMBOO_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_bamboo_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.BAMBOO_SLAB));
        VERTICAL_BAMBOO_MOSAIC_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_bamboo_mosaic_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.BAMBOO_MOSAIC_SLAB));
        VERTICAL_CRIMSON_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_crimson_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.CRIMSON_SLAB));
        VERTICAL_WARPED_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_warped_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.WARPED_SLAB));
        VERTICAL_STONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_stone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.STONE_SLAB));
        VERTICAL_SMOOTH_STONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_smooth_stone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE_SLAB));
        VERTICAL_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_sandstone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.SANDSTONE_SLAB));
        VERTICAL_CUT_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_cut_sandstone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.CUT_SANDSTONE_SLAB));
        VERTICAL_PETRIFIED_OAK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_petrified_oak_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.PETRIFIED_OAK_SLAB));
        VERTICAL_COBBLESTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_cobblestone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLESTONE_SLAB));
        VERTICAL_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.BRICK_SLAB));
        VERTICAL_STONE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_stone_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB));
        VERTICAL_MUD_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_mud_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.MUD_BRICK_SLAB));
        VERTICAL_NETHER_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_nether_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_SLAB));
        VERTICAL_QUARTZ_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_quartz_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_SLAB));
        VERTICAL_RED_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_red_sandstone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE_SLAB));
        VERTICAL_CUT_RED_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_cut_red_sandstone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.CUT_RED_SANDSTONE_SLAB));
        VERTICAL_PURPUR_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_purpur_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.PURPUR_SLAB));
        VERTICAL_PRISMARINE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_prismarine_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.PRISMARINE_SLAB));
        VERTICAL_PRISMARINE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_prismarine_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.PRISMARINE_BRICK_SLAB));
        VERTICAL_DARK_PRISMARINE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_dark_prismarine_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.DARK_PRISMARINE_SLAB));
        VERTICAL_GRANITE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_granite_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.GRANITE_SLAB));
        VERTICAL_POLISHED_GRANITE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_granite_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE_SLAB));
        VERTICAL_ANDESITE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_andesite_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.ANDESITE_SLAB));
        VERTICAL_POLISHED_ANDESITE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_andesite_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE_SLAB));
        VERTICAL_DIORITE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_diorite_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.DIORITE_SLAB));
        VERTICAL_POLISHED_DIORITE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_diorite_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE_SLAB));
        VERTICAL_SMOOTH_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_smooth_sandstone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.SMOOTH_SANDSTONE_SLAB));
        VERTICAL_SMOOTH_QUARTZ_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_smooth_quartz_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.SMOOTH_QUARTZ_SLAB));
        VERTICAL_SMOOTH_RED_SANDSTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_smooth_red_sandstone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.SMOOTH_RED_SANDSTONE_SLAB));
        VERTICAL_MOSSY_STONE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_mossy_stone_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.MOSSY_STONE_BRICK_SLAB));
        VERTICAL_END_STONE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_end_stone_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_SLAB));
        VERTICAL_RED_NETHER_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_red_nether_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICK_SLAB));
        VERTICAL_BLACKSTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_blackstone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.BLACKSTONE_SLAB));
        VERTICAL_POLISHED_BLACKSTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_blackstone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_SLAB));
        VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_blackstone_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB));
        VERTICAL_OXIDIZED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_oxidized_cut_copper_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.OXIDIZED_CUT_COPPER_SLAB));
        VERTICAL_WEATHERED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_weathered_cut_copper_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.WEATHERED_CUT_COPPER_SLAB));
        VERTICAL_EXPOSED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_exposed_cut_copper_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.EXPOSED_CUT_COPPER_SLAB));
        VERTICAL_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_cut_copper_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.CUT_COPPER_SLAB));
        VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_waxed_oxidized_cut_copper_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB));
        VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_waxed_weathered_cut_copper_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB));
        VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_waxed_exposed_cut_copper_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB));
        VERTICAL_WAXED_CUT_COPPER_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_waxed_cut_copper_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.WAXED_CUT_COPPER_SLAB));
        VERTICAL_COBBLED_DEEPSLATE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_cobbled_deepslate_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE_SLAB));
        VERTICAL_POLISHED_DEEPSLATE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_deepslate_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE_SLAB));
        VERTICAL_DEEPSLATE_TILE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_deepslate_tile_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_TILE_SLAB));
        VERTICAL_DEEPSLATE_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_deepslate_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICK_SLAB));
        VERTICAL_MOSSY_COBBLESTONE_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_mossy_cobblestone_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.MOSSY_COBBLESTONE_SLAB));
        VERTICAL_TUFF_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_tuff_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.TUFF_SLAB));
        VERTICAL_TUFF_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_tuff_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.TUFF_BRICK_SLAB));
        VERTICAL_POLISHED_TUFF_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_polished_tuff_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_TUFF_SLAB));
        VERTICAL_RESIN_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_resin_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(Blocks.RESIN_BRICK_SLAB));

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

    private static void registerMissingBlocks() {
        QUARTZ_BRICK_STAIRS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "quartz_brick_stairs"), settings -> new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), settings), AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS));
        QUARTZ_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "quartz_brick_slab"), SlabBlock::new, AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS));
        VERTICAL_QUARTZ_BRICK_SLAB = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "vertical_quartz_brick_slab"), VerticalSlabBlock::new, AbstractBlock.Settings.copy(QUARTZ_BRICK_SLAB));
        QUARTZ_BRICK_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "quartz_brick_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.QUARTZ_BRICKS).solid());
        PRISMARINE_BRICK_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "prismarine_brick_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.PRISMARINE_BRICKS).solid());
        DARK_PRISMARINE_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "dark_prismarine_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.DARK_PRISMARINE).solid());
        SMOOTH_SANDSTONE_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "smooth_sandstone_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.SMOOTH_SANDSTONE).solid());
        SMOOTH_RED_SANDSTONE_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "smooth_red_sandstone_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.SMOOTH_RED_SANDSTONE).solid());
        POLISHED_GRANITE_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_granite_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.POLISHED_GRANITE).solid());
        POLISHED_ANDESITE_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_andesite_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.POLISHED_ANDESITE).solid());
        POLISHED_DIORITE_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_diorite_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.POLISHED_DIORITE).solid());
        PURPUR_WALL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "purpur_wall"), WallBlock::new, AbstractBlock.Settings.copyShallow(Blocks.PURPUR_BLOCK).solid());
        POTTED_SHORT_GRASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "potted_short_grass"), settings -> new FlowerPotBlock(Blocks.SHORT_GRASS, settings), AbstractBlock.Settings.copy(Blocks.FLOWER_POT));
        CRACKED_MUD_BRICKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_mud_bricks"), AbstractBlock.Settings.copy(Blocks.MUD_BRICKS));
        CRACKED_QUARTZ_BRICKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_quartz_bricks"), AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS));
        CRACKED_RED_NETHER_BRICKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_red_nether_bricks"), AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS));
        CRACKED_END_STONE_BRICKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_end_stone_bricks"), AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS));
        CHISELED_PURPUR = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_purpur"), AbstractBlock.Settings.copy(Blocks.PURPUR_BLOCK));
        POLISHED_BLACKSTONE_PILLAR = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "polished_blackstone_pillar"), FacingBlock::new, AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE));
        CRACKED_TUFF_BRICKS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_tuff_bricks"), AbstractBlock.Settings.copy(Blocks.TUFF_BRICKS));
    }

    @Environment(EnvType.CLIENT)
    private static void registerMissingBlocksClient() {
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, POTTED_SHORT_GRASS);
    }
    // endregion

    // region Misc Blocks
    public static Block SAWMILL;
    public static Block COPPER_PRESSURE_PLATE;
    public static Block EXPOSED_COPPER_PRESSURE_PLATE;
    public static Block WEATHERED_COPPER_PRESSURE_PLATE;
    public static Block OXIDIZED_COPPER_PRESSURE_PLATE;
    public static Block WAXED_COPPER_PRESSURE_PLATE;
    public static Block WAXED_EXPOSED_COPPER_PRESSURE_PLATE;
    public static Block WAXED_WEATHERED_COPPER_PRESSURE_PLATE;
    public static Block WAXED_OXIDIZED_COPPER_PRESSURE_PLATE;

    private static void registerMiscBlocks() {
        SAWMILL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "sawmill"), SawmillBlock::new, AbstractBlock.Settings.copy(Blocks.STONECUTTER).strength(2f).sounds(BlockSoundGroup.WOOD));
        COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "copper_pressure_plate"), settings -> new CopperPressurePlateBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.ORANGE));
        EXPOSED_COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "exposed_copper_pressure_plate"), settings -> new CopperPressurePlateBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
        WEATHERED_COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "weathered_copper_pressure_plate"), settings -> new CopperPressurePlateBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.DARK_AQUA));
        OXIDIZED_COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "oxidized_copper_pressure_plate"), settings -> new CopperPressurePlateBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.TEAL));
        WAXED_COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_copper_pressure_plate"), settings -> new CopperPressurePlateBlock(Oxidizable.OxidationLevel.UNAFFECTED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.ORANGE));
        WAXED_EXPOSED_COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_exposed_copper_pressure_plate"), settings -> new CopperPressurePlateBlock(Oxidizable.OxidationLevel.EXPOSED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.TERRACOTTA_LIGHT_GRAY));
        WAXED_WEATHERED_COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_weathered_copper_pressure_plate"), settings -> new CopperPressurePlateBlock(Oxidizable.OxidationLevel.WEATHERED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.DARK_AQUA));
        WAXED_OXIDIZED_COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_oxidized_copper_pressure_plate"), settings -> new CopperPressurePlateBlock(Oxidizable.OxidationLevel.OXIDIZED, settings), AbstractBlock.Settings.copy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE).sounds(BlockSoundGroup.COPPER).mapColor(MapColor.TEAL));

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_PRESSURE_PLATE, EXPOSED_COPPER_PRESSURE_PLATE);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_PRESSURE_PLATE, WEATHERED_COPPER_PRESSURE_PLATE);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_PRESSURE_PLATE, OXIDIZED_COPPER_PRESSURE_PLATE);
        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_PRESSURE_PLATE, WAXED_COPPER_PRESSURE_PLATE);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_PRESSURE_PLATE, WAXED_EXPOSED_COPPER_PRESSURE_PLATE);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_PRESSURE_PLATE, WAXED_WEATHERED_COPPER_PRESSURE_PLATE);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_PRESSURE_PLATE, WAXED_OXIDIZED_COPPER_PRESSURE_PLATE);
    }

    @Environment(EnvType.CLIENT)
    private static void registerMiscBlocksClient() {
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
                SAWMILL
        );
    }
    // endregion
}
