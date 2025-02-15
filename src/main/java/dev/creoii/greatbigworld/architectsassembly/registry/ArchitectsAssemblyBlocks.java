package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.*;
import dev.creoii.greatbigworld.architectsassembly.block.RedstoneLampBlock;
import dev.creoii.greatbigworld.architectsassembly.block.TorchBlock;
import dev.creoii.greatbigworld.util.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

@SuppressWarnings("deprecation")
public final class ArchitectsAssemblyBlocks {
    public static void register() {
        registerDecorativeBlocks();
        registerVerticalSlabs();
        registerMissingBlocks();
        registerImprovedBlocks();
        registerMiscBlocks();
    }

    public static void registerClient() {
        registerDecorativeBlocksClient();
        registerMissingBlocksClient();
        registerImprovedBlocksClient();
        registerMiscBlocksClient();
    }

    // region Decorative Blocks
    public static Block SHATTERED_GLASS;
    public static Block CHISELED_GLASS;
    public static Block CHISELED_GLASS_PANE;
    public static Block BROWN_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_BROWN_STAINED_GLASS;
    public static Block CHISELED_BROWN_STAINED_GLASS_PANE;
    public static Block RED_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_RED_STAINED_GLASS;
    public static Block CHISELED_RED_STAINED_GLASS_PANE;
    public static Block ORANGE_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_ORANGE_STAINED_GLASS;
    public static Block CHISELED_ORANGE_STAINED_GLASS_PANE;
    public static Block YELLOW_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_YELLOW_STAINED_GLASS;
    public static Block CHISELED_YELLOW_STAINED_GLASS_PANE;
    public static Block LIME_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_LIME_STAINED_GLASS;
    public static Block CHISELED_LIME_STAINED_GLASS_PANE;
    public static Block GREEN_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_GREEN_STAINED_GLASS;
    public static Block CHISELED_GREEN_STAINED_GLASS_PANE;
    public static Block CYAN_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_CYAN_STAINED_GLASS;
    public static Block CHISELED_CYAN_STAINED_GLASS_PANE;
    public static Block BLUE_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_BLUE_STAINED_GLASS;
    public static Block CHISELED_BLUE_STAINED_GLASS_PANE;
    public static Block LIGHT_BLUE_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_LIGHT_BLUE_STAINED_GLASS;
    public static Block CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE;
    public static Block PINK_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_PINK_STAINED_GLASS;
    public static Block CHISELED_PINK_STAINED_GLASS_PANE;
    public static Block MAGENTA_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_MAGENTA_STAINED_GLASS;
    public static Block CHISELED_MAGENTA_STAINED_GLASS_PANE;
    public static Block PURPLE_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_PURPLE_STAINED_GLASS;
    public static Block CHISELED_PURPLE_STAINED_GLASS_PANE;
    public static Block BLACK_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_BLACK_STAINED_GLASS;
    public static Block CHISELED_BLACK_STAINED_GLASS_PANE;
    public static Block GRAY_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_GRAY_STAINED_GLASS;
    public static Block CHISELED_GRAY_STAINED_GLASS_PANE;
    public static Block LIGHT_GRAY_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_LIGHT_GRAY_STAINED_GLASS;
    public static Block CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE;
    public static Block WHITE_STAINED_SHATTERED_GLASS;
    public static Block CHISELED_WHITE_STAINED_GLASS;
    public static Block CHISELED_WHITE_STAINED_GLASS_PANE;
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
        SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.GLASS).breakInstantly());
        CHISELED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_glass"), settings -> new GlassBlock(settings, SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GLASS));
        CHISELED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.GLASS_PANE));
        BROWN_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "brown_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS).breakInstantly());
        CHISELED_BROWN_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_brown_stained_glass"), settings -> new GlassBlock(settings, SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS));
        CHISELED_BROWN_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_brown_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS_PANE));
        RED_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "red_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS).breakInstantly());
        CHISELED_RED_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_red_stained_glass"), settings -> new GlassBlock(settings, RED_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS));
        CHISELED_RED_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_red_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS_PANE));
        ORANGE_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "orange_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS).breakInstantly());
        CHISELED_ORANGE_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_orange_stained_glass"), settings -> new GlassBlock(settings, ORANGE_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS));
        CHISELED_ORANGE_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_orange_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS_PANE));
        YELLOW_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "yellow_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS).breakInstantly());
        CHISELED_YELLOW_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_yellow_stained_glass"), settings -> new GlassBlock(settings, YELLOW_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS));
        CHISELED_YELLOW_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_yellow_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS_PANE));
        LIME_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "lime_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS).breakInstantly());
        CHISELED_LIME_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_lime_stained_glass"), settings -> new GlassBlock(settings, LIME_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS));
        CHISELED_LIME_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_lime_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS_PANE));
        GREEN_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "green_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS).breakInstantly());
        CHISELED_GREEN_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_green_stained_glass"), settings -> new GlassBlock(settings, GREEN_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS));
        CHISELED_GREEN_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_green_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS_PANE));
        CYAN_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "cyan_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS).breakInstantly());
        CHISELED_CYAN_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_cyan_stained_glass"), settings -> new GlassBlock(settings, CYAN_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS));
        CHISELED_CYAN_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_cyan_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS_PANE));
        BLUE_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "blue_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS).breakInstantly());
        CHISELED_BLUE_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_blue_stained_glass"), settings -> new GlassBlock(settings, BLUE_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS));
        CHISELED_BLUE_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_blue_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS_PANE));
        LIGHT_BLUE_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "light_blue_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS).breakInstantly());
        CHISELED_LIGHT_BLUE_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_blue_stained_glass"), settings -> new GlassBlock(settings, LIGHT_BLUE_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS));
        CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_blue_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE));
        PINK_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "pink_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS).breakInstantly());
        CHISELED_PINK_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_pink_stained_glass"), settings -> new GlassBlock(settings, PINK_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS));
        CHISELED_PINK_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_pink_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS_PANE));
        MAGENTA_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "magenta_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS).breakInstantly());
        CHISELED_MAGENTA_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_magenta_stained_glass"), settings -> new GlassBlock(settings, MAGENTA_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS));
        CHISELED_MAGENTA_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_magenta_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS_PANE));
        PURPLE_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "purple_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS).breakInstantly());
        CHISELED_PURPLE_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_purple_stained_glass"), settings -> new GlassBlock(settings, PURPLE_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS));
        CHISELED_PURPLE_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_purple_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS_PANE));
        BLACK_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "black_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS).breakInstantly());
        CHISELED_BLACK_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_black_stained_glass"), settings -> new GlassBlock(settings, BLACK_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS));
        CHISELED_BLACK_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_black_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE));
        GRAY_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "gray_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS).breakInstantly());
        CHISELED_GRAY_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_gray_stained_glass"), settings -> new GlassBlock(settings, GRAY_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS));
        CHISELED_GRAY_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_gray_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS_PANE));
        LIGHT_GRAY_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "light_gray_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS).breakInstantly());
        CHISELED_LIGHT_GRAY_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_gray_stained_glass"), settings -> new GlassBlock(settings, LIGHT_GRAY_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS));
        CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_gray_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE));
        WHITE_STAINED_SHATTERED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "white_stained_shattered_glass"), ShatteredGlassBlock::new, AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS).breakInstantly());
        CHISELED_WHITE_STAINED_GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_white_stained_glass"), settings -> new GlassBlock(settings, WHITE_STAINED_SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS));
        CHISELED_WHITE_STAINED_GLASS_PANE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_white_stained_glass_pane"), PaneBlock::new, AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS_PANE));
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
        GLASS = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "glass"), settings -> new GlassBlock(settings, SHATTERED_GLASS.getDefaultState()), AbstractBlock.Settings.copy(Blocks.GLASS));
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
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                CHISELED_GLASS,
                CHISELED_GLASS_PANE,
                GLASS,
                SHATTERED_GLASS
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                CHISELED_BROWN_STAINED_GLASS,
                CHISELED_BROWN_STAINED_GLASS_PANE,
                CHISELED_RED_STAINED_GLASS,
                CHISELED_RED_STAINED_GLASS_PANE,
                CHISELED_ORANGE_STAINED_GLASS,
                CHISELED_ORANGE_STAINED_GLASS_PANE,
                CHISELED_YELLOW_STAINED_GLASS,
                CHISELED_YELLOW_STAINED_GLASS_PANE,
                CHISELED_LIME_STAINED_GLASS,
                CHISELED_LIME_STAINED_GLASS_PANE,
                CHISELED_GREEN_STAINED_GLASS,
                CHISELED_GREEN_STAINED_GLASS_PANE,
                CHISELED_CYAN_STAINED_GLASS,
                CHISELED_CYAN_STAINED_GLASS_PANE,
                CHISELED_BLUE_STAINED_GLASS,
                CHISELED_BLUE_STAINED_GLASS_PANE,
                CHISELED_LIGHT_BLUE_STAINED_GLASS,
                CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE,
                CHISELED_PINK_STAINED_GLASS,
                CHISELED_PINK_STAINED_GLASS_PANE,
                CHISELED_MAGENTA_STAINED_GLASS,
                CHISELED_MAGENTA_STAINED_GLASS_PANE,
                CHISELED_PURPLE_STAINED_GLASS,
                CHISELED_PURPLE_STAINED_GLASS_PANE,
                CHISELED_BLACK_STAINED_GLASS,
                CHISELED_BLACK_STAINED_GLASS_PANE,
                CHISELED_GRAY_STAINED_GLASS,
                CHISELED_GRAY_STAINED_GLASS_PANE,
                CHISELED_LIGHT_GRAY_STAINED_GLASS,
                CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE,
                CHISELED_WHITE_STAINED_GLASS,
                CHISELED_WHITE_STAINED_GLASS_PANE
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
    }

    @Environment(EnvType.CLIENT)
    private static void registerMissingBlocksClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), POTTED_SHORT_GRASS);
    }
    // endregion

    // region Improved Blocks
    public static Block TORCH;
    public static Block SOUL_TORCH;

    private static void registerImprovedBlocks() {
        TORCH = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "torch"), settings -> new TorchBlock(ParticleTypes.FLAME, settings), AbstractBlock.Settings.copy(Blocks.TORCH));
        SOUL_TORCH = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "soul_torch"), settings -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, settings), AbstractBlock.Settings.copy(Blocks.SOUL_TORCH));
    }

    @Environment(EnvType.CLIENT)
    private static void registerImprovedBlocksClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                TORCH,
                SOUL_TORCH
        );
    }
    // endregion

    // region Misc Blocks
    public static Block SAWMILL;
    public static Block COPPER_PRESSURE_PLATE;

    private static void registerMiscBlocks() {
        SAWMILL = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "sawmill"), SawmillBlock::new, AbstractBlock.Settings.copy(Blocks.STONECUTTER).strength(2f).sounds(BlockSoundGroup.WOOD));
        COPPER_PRESSURE_PLATE = RegistryHelper.registerBlock(Identifier.of(GreatBigWorld.NAMESPACE, "copper_pressure_plate"), settings -> new CopperPressurePlateBlock(BlockSetType.COPPER, settings), AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK));
    }

    @Environment(EnvType.CLIENT)
    private static void registerMiscBlocksClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                SAWMILL
        );
    }
    // endregion
}
