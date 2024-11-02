package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import dev.creoii.greatbigworld.architectsassembly.block.*;
import dev.creoii.greatbigworld.architectsassembly.block.RedstoneLampBlock;
import dev.creoii.greatbigworld.architectsassembly.block.TorchBlock;
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
    public static final Block SHATTERED_GLASS = new ShatteredGlassBlock(AbstractBlock.Settings.copy(Blocks.GLASS).breakInstantly());
    public static final Block CHISELED_GLASS = new GlassBlock(AbstractBlock.Settings.copy(Blocks.GLASS), SHATTERED_GLASS.getDefaultState());
    public static final Block CHISELED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.GLASS_PANE));
    public static final Block CHISELED_BROWN_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS));
    public static final Block CHISELED_BROWN_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.BROWN_STAINED_GLASS_PANE));
    public static final Block CHISELED_RED_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS));
    public static final Block CHISELED_RED_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.RED_STAINED_GLASS_PANE));
    public static final Block CHISELED_ORANGE_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS));
    public static final Block CHISELED_ORANGE_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.ORANGE_STAINED_GLASS_PANE));
    public static final Block CHISELED_YELLOW_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS));
    public static final Block CHISELED_YELLOW_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.YELLOW_STAINED_GLASS_PANE));
    public static final Block CHISELED_LIME_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS));
    public static final Block CHISELED_LIME_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.LIME_STAINED_GLASS_PANE));
    public static final Block CHISELED_GREEN_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS));
    public static final Block CHISELED_GREEN_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.GREEN_STAINED_GLASS_PANE));
    public static final Block CHISELED_CYAN_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS));
    public static final Block CHISELED_CYAN_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.CYAN_STAINED_GLASS_PANE));
    public static final Block CHISELED_BLUE_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS));
    public static final Block CHISELED_BLUE_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.BLUE_STAINED_GLASS_PANE));
    public static final Block CHISELED_LIGHT_BLUE_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS));
    public static final Block CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE));
    public static final Block CHISELED_PINK_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS));
    public static final Block CHISELED_PINK_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.PINK_STAINED_GLASS_PANE));
    public static final Block CHISELED_MAGENTA_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS));
    public static final Block CHISELED_MAGENTA_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.MAGENTA_STAINED_GLASS_PANE));
    public static final Block CHISELED_PURPLE_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS));
    public static final Block CHISELED_PURPLE_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.PURPLE_STAINED_GLASS_PANE));
    public static final Block CHISELED_BLACK_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS));
    public static final Block CHISELED_BLACK_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.BLACK_STAINED_GLASS_PANE));
    public static final Block CHISELED_GRAY_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS));
    public static final Block CHISELED_GRAY_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.GRAY_STAINED_GLASS_PANE));
    public static final Block CHISELED_LIGHT_GRAY_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS));
    public static final Block CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE));
    public static final Block CHISELED_WHITE_STAINED_GLASS = new TransparentBlock(AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS));
    public static final Block CHISELED_WHITE_STAINED_GLASS_PANE = new PaneBlock(AbstractBlock.Settings.copy(Blocks.WHITE_STAINED_GLASS_PANE));
    public static final Block LAPIS_STAIRS = new StairsBlock(Blocks.LAPIS_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.LAPIS_BLOCK));
    public static final Block LAPIS_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.LAPIS_BLOCK));
    public static final Block VERTICAL_LAPIS_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(LAPIS_SLAB));
    public static final Block LAPIS_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.LAPIS_BLOCK));
    public static final Block POLISHED_LAPIS_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.LAPIS_BLOCK));
    public static final Block POLISHED_LAPIS_STAIRS = new StairsBlock(POLISHED_LAPIS_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_LAPIS_BLOCK));
    public static final Block POLISHED_LAPIS_SLAB = new SlabBlock(AbstractBlock.Settings.copy(POLISHED_LAPIS_BLOCK));
    public static final Block VERTICAL_POLISHED_LAPIS_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(POLISHED_LAPIS_SLAB));
    public static final Block POLISHED_LAPIS_WALL = new WallBlock(AbstractBlock.Settings.copy(POLISHED_LAPIS_BLOCK));
    public static final Block CRACKED_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.BRICKS));
    public static final Block MOSSY_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.BRICKS));
    public static final Block MOSSY_BRICK_STAIRS = new StairsBlock(MOSSY_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BRICK_STAIRS));
    public static final Block MOSSY_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BRICK_SLAB));
    public static final Block VERTICAL_MOSSY_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(MOSSY_BRICK_SLAB));
    public static final Block MOSSY_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.BRICK_WALL));
    public static final Block GLASS = new GlassBlock(AbstractBlock.Settings.copy(Blocks.GLASS), SHATTERED_GLASS.getDefaultState());
    public static final Block CHISELED_OAK_PLANKS = new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS));
    public static final Block CHISELED_OAK_LOG = new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG));
    public static final Block CHISELED_OAK_WOOD = new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD));
    public static final Block STRIPPED_CHISELED_OAK_LOG = new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_CHISELED_OAK_WOOD = new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD));
    public static final Block CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.CUT_COPPER).solid());
    public static final Block EXPOSED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.EXPOSED_CUT_COPPER).solid());
    public static final Block WEATHERED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.WEATHERED_CUT_COPPER).solid());
    public static final Block OXIDIZED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.OXIDIZED_CUT_COPPER).solid());
    public static final Block WAXED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.WAXED_CUT_COPPER).solid());
    public static final Block WAXED_EXPOSED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.WAXED_EXPOSED_CUT_COPPER).solid());
    public static final Block WAXED_WEATHERED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.WAXED_WEATHERED_CUT_COPPER).solid());
    public static final Block WAXED_OXIDIZED_CUT_COPPER_WALL = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.WAXED_OXIDIZED_CUT_COPPER).solid());

    private static void registerDecorativeBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_glass"), CHISELED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_glass_pane"), CHISELED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_brown_stained_glass"), CHISELED_BROWN_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_brown_stained_glass_pane"), CHISELED_BROWN_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_red_stained_glass"), CHISELED_RED_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_red_stained_glass_pane"), CHISELED_RED_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_orange_stained_glass"), CHISELED_ORANGE_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_orange_stained_glass_pane"), CHISELED_ORANGE_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_yellow_stained_glass"), CHISELED_YELLOW_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_yellow_stained_glass_pane"), CHISELED_YELLOW_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_lime_stained_glass"), CHISELED_LIME_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_lime_stained_glass_pane"), CHISELED_LIME_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_green_stained_glass"), CHISELED_GREEN_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_green_stained_glass_pane"), CHISELED_GREEN_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_cyan_stained_glass"), CHISELED_CYAN_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_cyan_stained_glass_pane"), CHISELED_CYAN_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_blue_stained_glass"), CHISELED_BLUE_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_blue_stained_glass_pane"), CHISELED_BLUE_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_light_blue_stained_glass"), CHISELED_LIGHT_BLUE_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_light_blue_stained_glass_pane"), CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_pink_stained_glass"), CHISELED_PINK_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_pink_stained_glass_pane"), CHISELED_PINK_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_magenta_stained_glass"), CHISELED_MAGENTA_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_magenta_stained_glass_pane"), CHISELED_MAGENTA_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_purple_stained_glass"), CHISELED_PURPLE_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_purple_stained_glass_pane"), CHISELED_PURPLE_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_black_stained_glass"), CHISELED_BLACK_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_black_stained_glass_pane"), CHISELED_BLACK_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_gray_stained_glass"), CHISELED_GRAY_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_gray_stained_glass_pane"), CHISELED_GRAY_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_light_gray_stained_glass"), CHISELED_LIGHT_GRAY_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_light_gray_stained_glass_pane"), CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_white_stained_glass"), CHISELED_WHITE_STAINED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_white_stained_glass_pane"), CHISELED_WHITE_STAINED_GLASS_PANE);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "lapis_stairs"), LAPIS_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "lapis_slab"), LAPIS_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_lapis_slab"), VERTICAL_LAPIS_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "lapis_wall"), LAPIS_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "polished_lapis_block"), POLISHED_LAPIS_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "polished_lapis_stairs"), POLISHED_LAPIS_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "polished_lapis_slab"), POLISHED_LAPIS_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_polished_lapis_slab"), VERTICAL_POLISHED_LAPIS_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "polished_lapis_wall"), POLISHED_LAPIS_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "cracked_bricks"), CRACKED_BRICKS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "mossy_bricks"), MOSSY_BRICKS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "mossy_brick_stairs"), MOSSY_BRICK_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "mossy_brick_slab"), MOSSY_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_mossy_brick_slab"), VERTICAL_MOSSY_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "mossy_brick_wall"), MOSSY_BRICK_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "glass"), GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "shattered_glass"), SHATTERED_GLASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_oak_planks"), CHISELED_OAK_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_oak_log"), CHISELED_OAK_LOG);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "chiseled_oak_wood"), CHISELED_OAK_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "stripped_chiseled_oak_log"), STRIPPED_CHISELED_OAK_LOG);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "stripped_chiseled_oak_wood"), STRIPPED_CHISELED_OAK_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "cut_copper_wall"), CUT_COPPER_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "exposed_cut_copper_wall"), EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "weathered_cut_copper_wall"), WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "oxidized_cut_copper_wall"), OXIDIZED_CUT_COPPER_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "waxed_cut_copper_wall"), WAXED_CUT_COPPER_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "waxed_exposed_cut_copper_wall"), WAXED_EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "waxed_weathered_cut_copper_wall"), WAXED_WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "waxed_oxidized_cut_copper_wall"), WAXED_OXIDIZED_CUT_COPPER_WALL);

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
    public static final Block VERTICAL_OAK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB));
    public static final Block VERTICAL_SPRUCE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.SPRUCE_SLAB));
    public static final Block VERTICAL_BIRCH_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.BIRCH_SLAB));
    public static final Block VERTICAL_JUNGLE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_SLAB));
    public static final Block VERTICAL_DARK_OAK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.DARK_OAK_SLAB));
    public static final Block VERTICAL_ACACIA_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_SLAB));
    public static final Block VERTICAL_MANGROVE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_SLAB));
    public static final Block VERTICAL_CHERRY_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_SLAB));
    public static final Block VERTICAL_BAMBOO_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_SLAB));
    public static final Block VERTICAL_BAMBOO_MOSAIC_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.BAMBOO_MOSAIC_SLAB));
    public static final Block VERTICAL_CRIMSON_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.CRIMSON_SLAB));
    public static final Block VERTICAL_WARPED_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.WARPED_SLAB));
    public static final Block VERTICAL_STONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_SLAB));
    public static final Block VERTICAL_SMOOTH_STONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE_SLAB));
    public static final Block VERTICAL_SANDSTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE_SLAB));
    public static final Block VERTICAL_CUT_SANDSTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.CUT_SANDSTONE_SLAB));
    public static final Block VERTICAL_PETRIFIED_OAK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.PETRIFIED_OAK_SLAB));
    public static final Block VERTICAL_COBBLESTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.COBBLESTONE_SLAB));
    public static final Block VERTICAL_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.BRICK_SLAB));
    public static final Block VERTICAL_STONE_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.STONE_BRICK_SLAB));
    public static final Block VERTICAL_MUD_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.MUD_BRICK_SLAB));
    public static final Block VERTICAL_NETHER_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_SLAB));
    public static final Block VERTICAL_QUARTZ_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_SLAB));
    public static final Block VERTICAL_RED_SANDSTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE_SLAB));
    public static final Block VERTICAL_CUT_RED_SANDSTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.CUT_RED_SANDSTONE_SLAB));
    public static final Block VERTICAL_PURPUR_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.PURPUR_SLAB));
    public static final Block VERTICAL_PRISMARINE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE_SLAB));
    public static final Block VERTICAL_PRISMARINE_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE_BRICK_SLAB));
    public static final Block VERTICAL_DARK_PRISMARINE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.DARK_PRISMARINE_SLAB));
    public static final Block VERTICAL_GRANITE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE_SLAB));
    public static final Block VERTICAL_POLISHED_GRANITE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE_SLAB));
    public static final Block VERTICAL_ANDESITE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE_SLAB));
    public static final Block VERTICAL_POLISHED_ANDESITE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE_SLAB));
    public static final Block VERTICAL_DIORITE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE_SLAB));
    public static final Block VERTICAL_POLISHED_DIORITE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE_SLAB));
    public static final Block VERTICAL_SMOOTH_SANDSTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_SANDSTONE_SLAB));
    public static final Block VERTICAL_SMOOTH_QUARTZ_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_QUARTZ_SLAB));
    public static final Block VERTICAL_SMOOTH_RED_SANDSTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_RED_SANDSTONE_SLAB));
    public static final Block VERTICAL_MOSSY_STONE_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.MOSSY_STONE_BRICK_SLAB));
    public static final Block VERTICAL_END_STONE_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICK_SLAB));
    public static final Block VERTICAL_RED_NETHER_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICK_SLAB));
    public static final Block VERTICAL_BLACKSTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE_SLAB));
    public static final Block VERTICAL_POLISHED_BLACKSTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_SLAB));
    public static final Block VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB));
    public static final Block VERTICAL_OXIDIZED_CUT_COPPER_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.OXIDIZED_CUT_COPPER_SLAB));
    public static final Block VERTICAL_WEATHERED_CUT_COPPER_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.WEATHERED_CUT_COPPER_SLAB));
    public static final Block VERTICAL_EXPOSED_CUT_COPPER_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.EXPOSED_CUT_COPPER_SLAB));
    public static final Block VERTICAL_CUT_COPPER_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.CUT_COPPER_SLAB));
    public static final Block VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB));
    public static final Block VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB));
    public static final Block VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB));
    public static final Block VERTICAL_WAXED_CUT_COPPER_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.WAXED_CUT_COPPER_SLAB));
    public static final Block VERTICAL_COBBLED_DEEPSLATE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.COBBLED_DEEPSLATE_SLAB));
    public static final Block VERTICAL_POLISHED_DEEPSLATE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DEEPSLATE_SLAB));
    public static final Block VERTICAL_DEEPSLATE_TILE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_TILE_SLAB));
    public static final Block VERTICAL_DEEPSLATE_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICK_SLAB));
    public static final Block VERTICAL_MOSSY_COBBLESTONE_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.MOSSY_COBBLESTONE_SLAB));

    private static void registerVerticalSlabs() {
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_oak_slab"), VERTICAL_OAK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_spruce_slab"), VERTICAL_SPRUCE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_birch_slab"), VERTICAL_BIRCH_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_jungle_slab"), VERTICAL_JUNGLE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_dark_oak_slab"), VERTICAL_DARK_OAK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_acacia_slab"), VERTICAL_ACACIA_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_mangrove_slab"), VERTICAL_MANGROVE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_cherry_slab"), VERTICAL_CHERRY_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_bamboo_slab"), VERTICAL_BAMBOO_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_bamboo_mosaic_slab"), VERTICAL_BAMBOO_MOSAIC_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_crimson_slab"), VERTICAL_CRIMSON_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_warped_slab"), VERTICAL_WARPED_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_stone_slab"), VERTICAL_STONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_smooth_stone_slab"), VERTICAL_SMOOTH_STONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_sandstone_slab"), VERTICAL_SANDSTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_cut_sandstone_slab"), VERTICAL_CUT_SANDSTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_petrified_oak_slab"), VERTICAL_PETRIFIED_OAK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_cobblestone_slab"), VERTICAL_COBBLESTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_brick_slab"), VERTICAL_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_stone_brick_slab"), VERTICAL_STONE_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_mud_brick_slab"), VERTICAL_MUD_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_nether_brick_slab"), VERTICAL_NETHER_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_quartz_slab"), VERTICAL_QUARTZ_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_red_sandstone_slab"), VERTICAL_RED_SANDSTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_cut_red_sandstone_slab"), VERTICAL_CUT_RED_SANDSTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_purpur_slab"), VERTICAL_PURPUR_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_prismarine_slab"), VERTICAL_PRISMARINE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_prismarine_brick_slab"), VERTICAL_PRISMARINE_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_dark_prismarine_slab"), VERTICAL_DARK_PRISMARINE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_granite_slab"), VERTICAL_GRANITE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_polished_granite_slab"), VERTICAL_POLISHED_GRANITE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_andesite_slab"), VERTICAL_ANDESITE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_polished_andesite_slab"), VERTICAL_POLISHED_ANDESITE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_diorite_slab"), VERTICAL_DIORITE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_polished_diorite_slab"), VERTICAL_POLISHED_DIORITE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_smooth_sandstone_slab"), VERTICAL_SMOOTH_SANDSTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_smooth_quartz_slab"), VERTICAL_SMOOTH_QUARTZ_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_smooth_red_sandstone_slab"), VERTICAL_SMOOTH_RED_SANDSTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_mossy_stone_brick_slab"), VERTICAL_MOSSY_STONE_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_end_stone_brick_slab"), VERTICAL_END_STONE_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_red_nether_brick_slab"), VERTICAL_RED_NETHER_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_blackstone_slab"), VERTICAL_BLACKSTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_polished_blackstone_slab"), VERTICAL_POLISHED_BLACKSTONE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_polished_blackstone_brick_slab"), VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_oxidized_cut_copper_slab"), VERTICAL_OXIDIZED_CUT_COPPER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_weathered_cut_copper_slab"), VERTICAL_WEATHERED_CUT_COPPER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_exposed_cut_copper_slab"), VERTICAL_EXPOSED_CUT_COPPER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_cut_copper_slab"), VERTICAL_CUT_COPPER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_waxed_oxidized_cut_copper_slab"), VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_waxed_weathered_cut_copper_slab"), VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_waxed_exposed_cut_copper_slab"), VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_waxed_cut_copper_slab"), VERTICAL_WAXED_CUT_COPPER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_cobbled_deepslate_slab"), VERTICAL_COBBLED_DEEPSLATE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_polished_deepslate_slab"), VERTICAL_POLISHED_DEEPSLATE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_deepslate_tile_slab"), VERTICAL_DEEPSLATE_TILE_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_deepslate_brick_slab"), VERTICAL_DEEPSLATE_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_mossy_cobblestone_slab"), VERTICAL_MOSSY_COBBLESTONE_SLAB);

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
    public static final Block QUARTZ_BRICK_STAIRS = new StairsBlock(Blocks.QUARTZ_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS));
    public static final Block QUARTZ_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS));
    public static final Block VERTICAL_QUARTZ_BRICK_SLAB = new VerticalSlabBlock(AbstractBlock.Settings.copy(QUARTZ_BRICK_SLAB));
    public static final Block QUARTZ_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS).solid());
    public static final Block PRISMARINE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE_BRICKS).solid());
    public static final Block DARK_PRISMARINE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DARK_PRISMARINE).solid());
    public static final Block SMOOTH_SANDSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_SANDSTONE).solid());
    public static final Block SMOOTH_RED_SANDSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_RED_SANDSTONE).solid());
    public static final Block POLISHED_GRANITE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE).solid());
    public static final Block POLISHED_ANDESITE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE).solid());
    public static final Block POLISHED_DIORITE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE).solid());
    public static final Block PURPUR_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.PURPUR_BLOCK).solid());
    public static final Block POTTED_SHORT_GRASS = new FlowerPotBlock(Blocks.SHORT_GRASS, AbstractBlock.Settings.copy(Blocks.PURPUR_BLOCK).solid());
    public static final Block CRACKED_MUD_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.MUD_BRICKS).solid());
    public static final Block CRACKED_QUARTZ_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS).solid());
    public static final Block CRACKED_RED_NETHER_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS).solid());
    public static final Block CRACKED_END_STONE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.END_STONE_BRICKS).solid());

    private static void registerMissingBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "quartz_brick_stairs"), QUARTZ_BRICK_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "quartz_brick_slab"), QUARTZ_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "vertical_quartz_brick_slab"), VERTICAL_QUARTZ_BRICK_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "quartz_brick_wall"), QUARTZ_BRICK_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "prismarine_brick_wall"), PRISMARINE_BRICK_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "dark_prismarine_wall"), DARK_PRISMARINE_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "smooth_sandstone_wall"), SMOOTH_SANDSTONE_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "smooth_red_sandstone_wall"), SMOOTH_RED_SANDSTONE_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "polished_granite_wall"), POLISHED_GRANITE_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "polished_andesite_wall"), POLISHED_ANDESITE_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "polished_diorite_wall"), POLISHED_DIORITE_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "purpur_wall"), PURPUR_WALL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "potted_short_grass"), POTTED_SHORT_GRASS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "cracked_mud_bricks"), CRACKED_MUD_BRICKS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "cracked_quartz_bricks"), CRACKED_QUARTZ_BRICKS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "cracked_red_nether_bricks"), CRACKED_RED_NETHER_BRICKS);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "cracked_end_stone_bricks"), CRACKED_END_STONE_BRICKS);
    }

    @Environment(EnvType.CLIENT)
    private static void registerMissingBlocksClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), POTTED_SHORT_GRASS);
    }
    // endregion

    // region Improved Blocks
    public static final Block TORCH = new TorchBlock(ParticleTypes.FLAME, AbstractBlock.Settings.copy(Blocks.TORCH));
    public static final Block SOUL_TORCH = new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, AbstractBlock.Settings.copy(Blocks.SOUL_TORCH));
    public static final Block REDSTONE_LAMP = new RedstoneLampBlock(AbstractBlock.Settings.create().luminance(state -> state.get(RedstoneLampBlock.LIGHT)).strength(.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::always));

    private static void registerImprovedBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "torch"), TORCH);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "soul_torch"), SOUL_TORCH);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "redstone_lamp"), REDSTONE_LAMP);
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
    public static final Block SAWMILL = new SawmillBlock();
    public static final Block COPPER_PRESSURE_PLATE = new CopperPressurePlateBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK));

    private static void registerMiscBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "sawmill"), SAWMILL);
        Registry.register(Registries.BLOCK, new Identifier(GreatBigWorld.NAMESPACE, "copper_pressure_plate"), COPPER_PRESSURE_PLATE);
    }

    @Environment(EnvType.CLIENT)
    private static void registerMiscBlocksClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                SAWMILL
        );
    }
    // endregion
}
