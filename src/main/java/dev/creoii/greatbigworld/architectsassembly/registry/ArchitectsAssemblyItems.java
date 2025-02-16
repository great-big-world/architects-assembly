package dev.creoii.greatbigworld.architectsassembly.registry;

import com.google.common.collect.ImmutableMap;
import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.item.DyedItemFrameItem;
import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import dev.creoii.greatbigworld.util.RegistryHelper;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ArchitectsAssemblyItems {
    public static void register() {
        registerItems();
        registerVerticalSlabs();
        registerDecorativeBlocks();
        registerMissingBlocks();
        registerImprovedBlocks();
        registerMiscBlocks();
    }

    // region Items
    public static Item BROWN_ITEM_FRAME;
    public static Item RED_ITEM_FRAME;
    public static Item ORANGE_ITEM_FRAME;
    public static Item YELLOW_ITEM_FRAME;
    public static Item LIME_ITEM_FRAME;
    public static Item GREEN_ITEM_FRAME;
    public static Item CYAN_ITEM_FRAME;
    public static Item BLUE_ITEM_FRAME;
    public static Item LIGHT_BLUE_ITEM_FRAME;
    public static Item PINK_ITEM_FRAME;
    public static Item MAGENTA_ITEM_FRAME;
    public static Item PURPLE_ITEM_FRAME;
    public static Item BLACK_ITEM_FRAME;
    public static Item GRAY_ITEM_FRAME;
    public static Item LIGHT_GRAY_ITEM_FRAME;
    public static Item WHITE_ITEM_FRAME;
    public static Item BROWN_GLOW_ITEM_FRAME;
    public static Item RED_GLOW_ITEM_FRAME;
    public static Item ORANGE_GLOW_ITEM_FRAME;
    public static Item YELLOW_GLOW_ITEM_FRAME;
    public static Item LIME_GLOW_ITEM_FRAME;
    public static Item GREEN_GLOW_ITEM_FRAME;
    public static Item CYAN_GLOW_ITEM_FRAME;
    public static Item BLUE_GLOW_ITEM_FRAME;
    public static Item LIGHT_BLUE_GLOW_ITEM_FRAME;
    public static Item PINK_GLOW_ITEM_FRAME;
    public static Item MAGENTA_GLOW_ITEM_FRAME;
    public static Item PURPLE_GLOW_ITEM_FRAME;
    public static Item BLACK_GLOW_ITEM_FRAME;
    public static Item GRAY_GLOW_ITEM_FRAME;
    public static Item LIGHT_GRAY_GLOW_ITEM_FRAME;
    public static Item WHITE_GLOW_ITEM_FRAME;
    public static Item GLASS_SHARD;
    public static Item BROWN_GLASS_SHARD;
    public static Item RED_GLASS_SHARD;
    public static Item ORANGE_GLASS_SHARD;
    public static Item YELLOW_GLASS_SHARD;
    public static Item LIME_GLASS_SHARD;
    public static Item GREEN_GLASS_SHARD;
    public static Item CYAN_GLASS_SHARD;
    public static Item BLUE_GLASS_SHARD;
    public static Item LIGHT_BLUE_GLASS_SHARD;
    public static Item PINK_GLASS_SHARD;
    public static Item MAGENTA_GLASS_SHARD;
    public static Item PURPLE_GLASS_SHARD;
    public static Item BLACK_GLASS_SHARD;
    public static Item GRAY_GLASS_SHARD;
    public static Item LIGHT_GRAY_GLASS_SHARD;
    public static Item WHITE_GLASS_SHARD;

    private static void registerItems() {
        BROWN_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "brown_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.BROWN, settings));
        RED_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "red_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.RED, settings));
        ORANGE_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "orange_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.ORANGE, settings));
        YELLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "yellow_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.YELLOW, settings));
        LIME_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "lime_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.LIME, settings));
        GREEN_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "green_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.GREEN, settings));
        CYAN_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cyan_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.CYAN, settings));
        BLUE_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "blue_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.BLUE, settings));
        LIGHT_BLUE_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "light_blue_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.LIGHT_BLUE, settings));
        PINK_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "pink_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.PINK, settings));
        MAGENTA_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "magenta_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.MAGENTA, settings));
        PURPLE_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "purple_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.PURPLE, settings));
        BLACK_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "black_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.BLACK, settings));
        GRAY_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "gray_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.GRAY, settings));
        LIGHT_GRAY_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "light_gray_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.LIGHT_GRAY, settings));
        WHITE_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "white_item_frame"), settings -> new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.WHITE, settings));

        BROWN_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "brown_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.BROWN, settings));
        RED_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "red_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.RED, settings));
        ORANGE_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "orange_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.ORANGE, settings));
        YELLOW_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "yellow_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.YELLOW, settings));
        LIME_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "lime_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.LIME, settings));
        GREEN_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "green_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.GREEN, settings));
        CYAN_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cyan_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.CYAN, settings));
        BLUE_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "blue_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.BLUE, settings));
        LIGHT_BLUE_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "light_blue_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.LIGHT_BLUE, settings));
        PINK_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "pink_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.PINK, settings));
        MAGENTA_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "magenta_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.MAGENTA, settings));
        PURPLE_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "purple_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.PURPLE, settings));
        BLACK_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "black_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.BLACK, settings));
        GRAY_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "gray_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.GRAY, settings));
        LIGHT_GRAY_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "light_gray_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.LIGHT_GRAY, settings));
        WHITE_GLOW_ITEM_FRAME = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "white_glow_item_frame"), settings -> new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.WHITE, settings));

        GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "glass_shard"));
        BROWN_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "brown_glass_shard"));
        RED_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "red_glass_shard"));
        ORANGE_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "orange_glass_shard"));
        YELLOW_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "yellow_glass_shard"));
        LIME_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "lime_glass_shard"));
        GREEN_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "green_glass_shard"));
        CYAN_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cyan_glass_shard"));
        BLUE_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "blue_glass_shard"));
        LIGHT_BLUE_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "light_blue_glass_shard"));
        PINK_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "pink_glass_shard"));
        MAGENTA_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "magenta_glass_shard"));
        PURPLE_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "purple_glass_shard"));
        BLACK_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "black_glass_shard"));
        GRAY_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "gray_glass_shard"));
        LIGHT_GRAY_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "light_gray_glass_shard"));
        WHITE_GLASS_SHARD = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "white_glass_shard"));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.ITEM_FRAME, WHITE_ITEM_FRAME, LIGHT_GRAY_ITEM_FRAME, GRAY_ITEM_FRAME, BLACK_ITEM_FRAME, BROWN_ITEM_FRAME, RED_ITEM_FRAME, ORANGE_ITEM_FRAME, YELLOW_ITEM_FRAME, LIME_ITEM_FRAME, GREEN_ITEM_FRAME, CYAN_ITEM_FRAME, BLUE_ITEM_FRAME, LIGHT_BLUE_ITEM_FRAME, PINK_ITEM_FRAME, MAGENTA_ITEM_FRAME, PURPLE_ITEM_FRAME);
            entries.addAfter(Items.GLOW_ITEM_FRAME, WHITE_GLOW_ITEM_FRAME, LIGHT_GRAY_GLOW_ITEM_FRAME, GRAY_GLOW_ITEM_FRAME, BLACK_GLOW_ITEM_FRAME, BROWN_GLOW_ITEM_FRAME, RED_GLOW_ITEM_FRAME, ORANGE_GLOW_ITEM_FRAME, YELLOW_GLOW_ITEM_FRAME, LIME_GLOW_ITEM_FRAME, GREEN_GLOW_ITEM_FRAME, CYAN_GLOW_ITEM_FRAME, BLUE_GLOW_ITEM_FRAME, PINK_GLOW_ITEM_FRAME, MAGENTA_GLOW_ITEM_FRAME, PURPLE_GLOW_ITEM_FRAME);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.PINK_DYE, GLASS_SHARD, BROWN_GLASS_SHARD, RED_GLASS_SHARD, ORANGE_GLASS_SHARD, YELLOW_GLASS_SHARD, LIME_GLASS_SHARD, GREEN_GLASS_SHARD, CYAN_GLASS_SHARD, BLUE_GLASS_SHARD, LIGHT_BLUE_GLASS_SHARD, PINK_GLASS_SHARD, MAGENTA_GLASS_SHARD, PURPLE_GLASS_SHARD, BLACK_GLASS_SHARD, GRAY_GLASS_SHARD, LIGHT_GRAY_GLASS_SHARD, WHITE_GLASS_SHARD);
        });
    }
    // endregion

    // region Decorative Blocks
    public static Item CHISELED_GLASS;
    public static Item CHISELED_GLASS_PANE;
    public static Item CRACKED_GLASS;
    public static Item CHISELED_BROWN_GLASS;
    public static Item CHISELED_BROWN_GLASS_PANE;
    public static Item CRACKED_BROWN_GLASS;
    public static Item CHISELED_RED_GLASS;
    public static Item CHISELED_RED_GLASS_PANE;
    public static Item CRACKED_RED_GLASS;
    public static Item CHISELED_ORANGE_GLASS;
    public static Item CHISELED_ORANGE_GLASS_PANE;
    public static Item CRACKED_ORANGE_GLASS;
    public static Item CHISELED_YELLOW_GLASS;
    public static Item CHISELED_YELLOW_GLASS_PANE;
    public static Item CRACKED_YELLOW_GLASS;
    public static Item CHISELED_LIME_GLASS;
    public static Item CHISELED_LIME_GLASS_PANE;
    public static Item CRACKED_LIME_GLASS;
    public static Item CHISELED_GREEN_GLASS;
    public static Item CHISELED_GREEN_GLASS_PANE;
    public static Item CRACKED_GREEN_GLASS;
    public static Item CHISELED_CYAN_GLASS;
    public static Item CHISELED_CYAN_GLASS_PANE;
    public static Item CRACKED_CYAN_GLASS;
    public static Item CHISELED_BLUE_GLASS;
    public static Item CHISELED_BLUE_GLASS_PANE;
    public static Item CRACKED_BLUE_GLASS;
    public static Item CHISELED_LIGHT_BLUE_GLASS;
    public static Item CHISELED_LIGHT_BLUE_GLASS_PANE;
    public static Item CRACKED_LIGHT_BLUE_GLASS;
    public static Item CHISELED_PINK_GLASS;
    public static Item CHISELED_PINK_GLASS_PANE;
    public static Item CRACKED_PINK_GLASS;
    public static Item CHISELED_MAGENTA_GLASS;
    public static Item CHISELED_MAGENTA_GLASS_PANE;
    public static Item CRACKED_MAGENTA_GLASS;
    public static Item CHISELED_PURPLE_GLASS;
    public static Item CHISELED_PURPLE_GLASS_PANE;
    public static Item CRACKED_PURPLE_GLASS;
    public static Item CHISELED_BLACK_GLASS;
    public static Item CHISELED_BLACK_GLASS_PANE;
    public static Item CRACKED_BLACK_GLASS;
    public static Item CHISELED_GRAY_GLASS;
    public static Item CHISELED_GRAY_GLASS_PANE;
    public static Item CRACKED_GRAY_GLASS;
    public static Item CHISELED_LIGHT_GRAY_GLASS;
    public static Item CHISELED_LIGHT_GRAY_GLASS_PANE;
    public static Item CRACKED_LIGHT_GRAY_GLASS;
    public static Item CHISELED_WHITE_GLASS;
    public static Item CHISELED_WHITE_GLASS_PANE;
    public static Item CRACKED_WHITE_GLASS;
    public static Item LAPIS_STAIRS;
    public static Item LAPIS_SLAB;
    public static Item LAPIS_WALL;
    public static Item POLISHED_LAPIS_BLOCK;
    public static Item POLISHED_LAPIS_STAIRS;
    public static Item POLISHED_LAPIS_SLAB;
    public static Item POLISHED_LAPIS_WALL;
    public static Item POLISHED_LAPIS_BRICKS;
    public static Item POLISHED_LAPIS_BRICK_STAIRS;
    public static Item POLISHED_LAPIS_BRICK_SLAB;
    public static Item POLISHED_LAPIS_BRICK_WALL;
    public static Item CRACKED_BRICKS;
    public static Item MOSSY_BRICKS;
    public static Item MOSSY_BRICK_STAIRS;
    public static Item MOSSY_BRICK_SLAB;
    public static Item MOSSY_BRICK_WALL;
    public static Item GLASS;
    public static Item BROWN_GLASS;
    public static Item RED_GLASS;
    public static Item ORANGE_GLASS;
    public static Item YELLOW_GLASS;
    public static Item LIME_GLASS;
    public static Item GREEN_GLASS;
    public static Item CYAN_GLASS;
    public static Item BLUE_GLASS;
    public static Item LIGHT_BLUE_GLASS;
    public static Item PINK_GLASS;
    public static Item MAGENTA_GLASS;
    public static Item PURPLE_GLASS;
    public static Item BLACK_GLASS;
    public static Item GRAY_GLASS;
    public static Item LIGHT_GRAY_GLASS;
    public static Item WHITE_GLASS;
    public static Item CHISELED_OAK_PLANKS;
    public static Item CHISELED_OAK_LOG;
    public static Item CHISELED_OAK_WOOD;
    public static Item STRIPPED_CHISELED_OAK_LOG;
    public static Item STRIPPED_CHISELED_OAK_WOOD;
    public static Item CUT_COPPER_WALL;
    public static Item WAXED_CUT_COPPER_WALL;
    public static Item EXPOSED_CUT_COPPER_WALL;
    public static Item WAXED_EXPOSED_CUT_COPPER_WALL;
    public static Item WEATHERED_CUT_COPPER_WALL;
    public static Item WAXED_WEATHERED_CUT_COPPER_WALL;
    public static Item OXIDIZED_CUT_COPPER_WALL;
    public static Item WAXED_OXIDIZED_CUT_COPPER_WALL;

    private static void registerDecorativeBlocks() {
        CHISELED_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_glass"), ArchitectsAssemblyBlocks.CHISELED_GLASS);
        CHISELED_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_GLASS_PANE);
        CRACKED_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_glass"), ArchitectsAssemblyBlocks.CRACKED_GLASS);
        CRACKED_BROWN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_brown_glass"), ArchitectsAssemblyBlocks.CRACKED_BROWN_GLASS);
        CHISELED_BROWN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_brown_glass"), ArchitectsAssemblyBlocks.CHISELED_BROWN_GLASS);
        CHISELED_BROWN_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_brown_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_BROWN_GLASS_PANE);
        CRACKED_RED_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_red_glass"), ArchitectsAssemblyBlocks.CRACKED_RED_GLASS);
        CHISELED_RED_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_red_glass"), ArchitectsAssemblyBlocks.CHISELED_RED_GLASS);
        CHISELED_RED_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_red_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_RED_GLASS_PANE);
        CRACKED_ORANGE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_orange_glass"), ArchitectsAssemblyBlocks.CRACKED_ORANGE_GLASS);
        CHISELED_ORANGE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_orange_glass"), ArchitectsAssemblyBlocks.CHISELED_ORANGE_GLASS);
        CHISELED_ORANGE_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_orange_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_ORANGE_GLASS_PANE);
        CRACKED_YELLOW_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_yellow_glass"), ArchitectsAssemblyBlocks.CRACKED_YELLOW_GLASS);
        CHISELED_YELLOW_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_yellow_glass"), ArchitectsAssemblyBlocks.CHISELED_YELLOW_GLASS);
        CHISELED_YELLOW_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_yellow_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_YELLOW_GLASS_PANE);
        CRACKED_LIME_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_lime_glass"), ArchitectsAssemblyBlocks.CRACKED_LIME_GLASS);
        CHISELED_LIME_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_lime_glass"), ArchitectsAssemblyBlocks.CHISELED_LIME_GLASS);
        CHISELED_LIME_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_lime_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_LIME_GLASS_PANE);
        CRACKED_GREEN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_green_glass"), ArchitectsAssemblyBlocks.CRACKED_GREEN_GLASS);
        CHISELED_GREEN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_green_glass"), ArchitectsAssemblyBlocks.CHISELED_GREEN_GLASS);
        CHISELED_GREEN_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_green_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_GREEN_GLASS_PANE);
        CRACKED_CYAN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_cyan_glass"), ArchitectsAssemblyBlocks.CRACKED_CYAN_GLASS);
        CHISELED_CYAN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_cyan_glass"), ArchitectsAssemblyBlocks.CHISELED_CYAN_GLASS);
        CHISELED_CYAN_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_cyan_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_CYAN_GLASS_PANE);
        CRACKED_BLUE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_blue_glass"), ArchitectsAssemblyBlocks.CRACKED_BLUE_GLASS);
        CHISELED_BLUE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_blue_glass"), ArchitectsAssemblyBlocks.CHISELED_BLUE_GLASS);
        CHISELED_BLUE_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_blue_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_BLUE_GLASS_PANE);
        CRACKED_LIGHT_BLUE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_light_blue_glass"), ArchitectsAssemblyBlocks.CRACKED_LIGHT_BLUE_GLASS);
        CHISELED_LIGHT_BLUE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_blue_glass"), ArchitectsAssemblyBlocks.CHISELED_LIGHT_BLUE_GLASS);
        CHISELED_LIGHT_BLUE_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_blue_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_LIGHT_BLUE_GLASS_PANE);
        CRACKED_PINK_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_pink_glass"), ArchitectsAssemblyBlocks.CRACKED_PINK_GLASS);
        CHISELED_PINK_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_pink_glass"), ArchitectsAssemblyBlocks.CHISELED_PINK_GLASS);
        CHISELED_PINK_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_pink_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_PINK_GLASS_PANE);
        CRACKED_MAGENTA_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_magenta_glass"), ArchitectsAssemblyBlocks.CRACKED_MAGENTA_GLASS);
        CHISELED_MAGENTA_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_magenta_glass"), ArchitectsAssemblyBlocks.CHISELED_MAGENTA_GLASS);
        CHISELED_MAGENTA_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_magenta_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_MAGENTA_GLASS_PANE);
        CRACKED_PURPLE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_purple_glass"), ArchitectsAssemblyBlocks.CRACKED_PURPLE_GLASS);
        CHISELED_PURPLE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_purple_glass"), ArchitectsAssemblyBlocks.CHISELED_PURPLE_GLASS);
        CHISELED_PURPLE_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_purple_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_PURPLE_GLASS_PANE);
        CRACKED_BLACK_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_black_glass"), ArchitectsAssemblyBlocks.CRACKED_BLACK_GLASS);
        CHISELED_BLACK_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_black_glass"), ArchitectsAssemblyBlocks.CHISELED_BLACK_GLASS);
        CHISELED_BLACK_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_black_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_BLACK_GLASS_PANE);
        CRACKED_GRAY_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_gray_glass"), ArchitectsAssemblyBlocks.CRACKED_GRAY_GLASS);
        CHISELED_GRAY_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_gray_glass"), ArchitectsAssemblyBlocks.CHISELED_GRAY_GLASS);
        CHISELED_GRAY_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_gray_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_GRAY_GLASS_PANE);
        CRACKED_LIGHT_GRAY_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_light_gray_glass"), ArchitectsAssemblyBlocks.CRACKED_LIGHT_GRAY_GLASS);
        CHISELED_LIGHT_GRAY_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_gray_glass"), ArchitectsAssemblyBlocks.CHISELED_LIGHT_GRAY_GLASS);
        CHISELED_LIGHT_GRAY_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_light_gray_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_LIGHT_GRAY_GLASS_PANE);
        CRACKED_WHITE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_white_glass"), ArchitectsAssemblyBlocks.CRACKED_WHITE_GLASS);
        CHISELED_WHITE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_white_glass"), ArchitectsAssemblyBlocks.CHISELED_WHITE_GLASS);
        CHISELED_WHITE_GLASS_PANE = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_white_glass_pane"), ArchitectsAssemblyBlocks.CHISELED_WHITE_GLASS_PANE);
        LAPIS_STAIRS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "lapis_stairs"), ArchitectsAssemblyBlocks.LAPIS_STAIRS);
        LAPIS_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "lapis_slab"), settings -> new SlabItem(ArchitectsAssemblyBlocks.LAPIS_SLAB, ArchitectsAssemblyBlocks.VERTICAL_LAPIS_SLAB, settings.useBlockPrefixedTranslationKey()));
        LAPIS_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "lapis_wall"), ArchitectsAssemblyBlocks.LAPIS_WALL);
        POLISHED_LAPIS_BLOCK = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_block"), ArchitectsAssemblyBlocks.POLISHED_LAPIS_BLOCK);
        POLISHED_LAPIS_STAIRS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_stairs"), ArchitectsAssemblyBlocks.POLISHED_LAPIS_STAIRS);
        POLISHED_LAPIS_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_slab"), settings -> new SlabItem(ArchitectsAssemblyBlocks.POLISHED_LAPIS_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_LAPIS_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_LAPIS_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_wall"), ArchitectsAssemblyBlocks.POLISHED_LAPIS_WALL);
        POLISHED_LAPIS_BRICKS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_bricks"), ArchitectsAssemblyBlocks.POLISHED_LAPIS_BRICKS);
        POLISHED_LAPIS_BRICK_STAIRS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_brick_stairs"), ArchitectsAssemblyBlocks.POLISHED_LAPIS_BRICK_STAIRS);
        POLISHED_LAPIS_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_brick_slab"), settings -> new SlabItem(ArchitectsAssemblyBlocks.POLISHED_LAPIS_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_LAPIS_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_LAPIS_BRICK_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_lapis_brick_wall"), ArchitectsAssemblyBlocks.POLISHED_LAPIS_BRICK_WALL);
        CRACKED_BRICKS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_bricks"), ArchitectsAssemblyBlocks.CRACKED_BRICKS);
        MOSSY_BRICKS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_bricks"), ArchitectsAssemblyBlocks.MOSSY_BRICKS);
        MOSSY_BRICK_STAIRS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_brick_stairs"), ArchitectsAssemblyBlocks.MOSSY_BRICK_STAIRS);
        MOSSY_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_brick_slab"), settings -> new SlabItem(ArchitectsAssemblyBlocks.MOSSY_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        MOSSY_BRICK_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_brick_wall"), ArchitectsAssemblyBlocks.MOSSY_BRICK_WALL);
        GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "glass"), ArchitectsAssemblyBlocks.GLASS);
        BROWN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "brown_glass"), ArchitectsAssemblyBlocks.BROWN_GLASS);
        RED_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "red_glass"), ArchitectsAssemblyBlocks.RED_GLASS);
        ORANGE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "orange_glass"), ArchitectsAssemblyBlocks.ORANGE_GLASS);
        YELLOW_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "yellow_glass"), ArchitectsAssemblyBlocks.YELLOW_GLASS);
        LIME_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "lime_glass"), ArchitectsAssemblyBlocks.LIME_GLASS);
        GREEN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "green_glass"), ArchitectsAssemblyBlocks.GREEN_GLASS);
        CYAN_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cyan_glass"), ArchitectsAssemblyBlocks.CYAN_GLASS);
        BLUE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "blue_glass"), ArchitectsAssemblyBlocks.BLUE_GLASS);
        LIGHT_BLUE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "light_blue_glass"), ArchitectsAssemblyBlocks.LIGHT_BLUE_GLASS);
        PINK_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "pink_glass"), ArchitectsAssemblyBlocks.PINK_GLASS);
        MAGENTA_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "magenta_glass"), ArchitectsAssemblyBlocks.MAGENTA_GLASS);
        PURPLE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "purple_glass"), ArchitectsAssemblyBlocks.PURPLE_GLASS);
        BLACK_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "black_glass"), ArchitectsAssemblyBlocks.BLACK_GLASS);
        GRAY_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "gray_glass"), ArchitectsAssemblyBlocks.GRAY_GLASS);
        LIGHT_GRAY_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "light_gray_glass"), ArchitectsAssemblyBlocks.LIGHT_GRAY_GLASS);
        WHITE_GLASS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "white_glass"), ArchitectsAssemblyBlocks.WHITE_GLASS);
        CHISELED_OAK_PLANKS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_oak_planks"), ArchitectsAssemblyBlocks.CHISELED_OAK_PLANKS);
        CHISELED_OAK_LOG = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_oak_log"), ArchitectsAssemblyBlocks.CHISELED_OAK_LOG);
        CHISELED_OAK_WOOD = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "chiseled_oak_wood"), ArchitectsAssemblyBlocks.CHISELED_OAK_WOOD);
        STRIPPED_CHISELED_OAK_LOG = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "stripped_chiseled_oak_log"), ArchitectsAssemblyBlocks.STRIPPED_CHISELED_OAK_LOG);
        STRIPPED_CHISELED_OAK_WOOD = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "stripped_chiseled_oak_wood"), ArchitectsAssemblyBlocks.STRIPPED_CHISELED_OAK_WOOD);
        CUT_COPPER_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cut_copper_wall"), ArchitectsAssemblyBlocks.CUT_COPPER_WALL);
        WAXED_CUT_COPPER_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_cut_copper_wall"), ArchitectsAssemblyBlocks.WAXED_CUT_COPPER_WALL);
        EXPOSED_CUT_COPPER_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "exposed_cut_copper_wall"), ArchitectsAssemblyBlocks.EXPOSED_CUT_COPPER_WALL);
        WAXED_EXPOSED_CUT_COPPER_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_exposed_cut_copper_wall"), ArchitectsAssemblyBlocks.WAXED_EXPOSED_CUT_COPPER_WALL);
        WEATHERED_CUT_COPPER_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "weathered_cut_copper_wall"), ArchitectsAssemblyBlocks.WEATHERED_CUT_COPPER_WALL);
        WAXED_WEATHERED_CUT_COPPER_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_weathered_cut_copper_wall"), ArchitectsAssemblyBlocks.WAXED_WEATHERED_CUT_COPPER_WALL);
        OXIDIZED_CUT_COPPER_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "oxidized_cut_copper_wall"), ArchitectsAssemblyBlocks.OXIDIZED_CUT_COPPER_WALL);
        WAXED_OXIDIZED_CUT_COPPER_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_oxidized_cut_copper_wall"), ArchitectsAssemblyBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.getDisplayStacks().replaceAll(stack -> {
                if (stack.isOf(Items.GLASS)) {
                    return GLASS.getDefaultStack();
                } else if (stack.isOf(Items.BROWN_STAINED_GLASS)) {
                    return BROWN_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.RED_STAINED_GLASS)) {
                    return RED_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.ORANGE_STAINED_GLASS)) {
                    return ORANGE_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.YELLOW_STAINED_GLASS)) {
                    return YELLOW_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.LIME_STAINED_GLASS)) {
                    return LIME_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.GREEN_STAINED_GLASS)) {
                    return GREEN_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.CYAN_STAINED_GLASS)) {
                    return CYAN_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.BLUE_STAINED_GLASS)) {
                    return BLUE_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.LIGHT_BLUE_STAINED_GLASS)) {
                    return LIGHT_BLUE_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.PINK_STAINED_GLASS)) {
                    return PINK_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.MAGENTA_STAINED_GLASS)) {
                    return MAGENTA_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.PURPLE_STAINED_GLASS)) {
                    return PURPLE_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.BLACK_STAINED_GLASS)) {
                    return BLACK_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.GRAY_STAINED_GLASS)) {
                    return GRAY_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.LIGHT_GRAY_STAINED_GLASS)) {
                    return LIGHT_GRAY_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.WHITE_STAINED_GLASS)) {
                    return WHITE_GLASS.getDefaultStack();
                }
                return stack;
            });
            entries.getSearchTabStacks().replaceAll(stack -> {
                if (stack.isOf(Items.GLASS)) {
                    return GLASS.getDefaultStack();
                } else if (stack.isOf(Items.BROWN_STAINED_GLASS)) {
                    return BROWN_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.RED_STAINED_GLASS)) {
                    return RED_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.ORANGE_STAINED_GLASS)) {
                    return ORANGE_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.YELLOW_STAINED_GLASS)) {
                    return YELLOW_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.LIME_STAINED_GLASS)) {
                    return LIME_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.GREEN_STAINED_GLASS)) {
                    return GREEN_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.CYAN_STAINED_GLASS)) {
                    return CYAN_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.BLUE_STAINED_GLASS)) {
                    return BLUE_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.LIGHT_BLUE_STAINED_GLASS)) {
                    return LIGHT_BLUE_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.PINK_STAINED_GLASS)) {
                    return PINK_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.MAGENTA_STAINED_GLASS)) {
                    return MAGENTA_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.PURPLE_STAINED_GLASS)) {
                    return PURPLE_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.BLACK_STAINED_GLASS)) {
                    return BLACK_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.GRAY_STAINED_GLASS)) {
                    return GRAY_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.LIGHT_GRAY_STAINED_GLASS)) {
                    return LIGHT_GRAY_GLASS.getDefaultStack();
                } else if (stack.isOf(Items.WHITE_STAINED_GLASS)) {
                    return WHITE_GLASS.getDefaultStack();
                }
                return stack;
            });
            entries.addAfter(ArchitectsAssemblyItems.GLASS, CHISELED_GLASS);
            entries.addAfter(Items.GLASS_PANE, CHISELED_GLASS_PANE, CRACKED_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.BROWN_GLASS, CHISELED_BROWN_GLASS);
            entries.addAfter(Items.BROWN_STAINED_GLASS_PANE, CHISELED_BROWN_GLASS_PANE, CRACKED_BROWN_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.RED_GLASS, CHISELED_RED_GLASS);
            entries.addAfter(Items.RED_STAINED_GLASS_PANE, CHISELED_RED_GLASS_PANE, CRACKED_RED_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.ORANGE_GLASS, CHISELED_ORANGE_GLASS);
            entries.addAfter(Items.ORANGE_STAINED_GLASS_PANE, CHISELED_ORANGE_GLASS_PANE, CRACKED_ORANGE_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.YELLOW_GLASS, CHISELED_YELLOW_GLASS);
            entries.addAfter(Items.YELLOW_STAINED_GLASS_PANE, CHISELED_YELLOW_GLASS_PANE, CRACKED_YELLOW_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.LIME_GLASS, CHISELED_LIME_GLASS);
            entries.addAfter(Items.LIME_STAINED_GLASS_PANE, CHISELED_LIME_GLASS_PANE, CRACKED_LIME_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.GREEN_GLASS, CHISELED_GREEN_GLASS);
            entries.addAfter(Items.GREEN_STAINED_GLASS_PANE, CHISELED_GREEN_GLASS_PANE, CRACKED_GREEN_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.CYAN_GLASS, CHISELED_CYAN_GLASS);
            entries.addAfter(Items.CYAN_STAINED_GLASS_PANE, CHISELED_CYAN_GLASS_PANE, CRACKED_CYAN_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.BLUE_GLASS, CHISELED_BLUE_GLASS);
            entries.addAfter(Items.BLUE_STAINED_GLASS_PANE, CHISELED_BLUE_GLASS_PANE, CRACKED_BLUE_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.LIGHT_BLUE_GLASS, CHISELED_LIGHT_BLUE_GLASS);
            entries.addAfter(Items.LIGHT_BLUE_STAINED_GLASS_PANE, CHISELED_LIGHT_BLUE_GLASS_PANE, CRACKED_LIGHT_BLUE_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.PINK_GLASS, CHISELED_PINK_GLASS);
            entries.addAfter(Items.PINK_STAINED_GLASS_PANE, CHISELED_PINK_GLASS_PANE, CRACKED_PINK_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.MAGENTA_GLASS, CHISELED_MAGENTA_GLASS);
            entries.addAfter(Items.MAGENTA_STAINED_GLASS_PANE, CHISELED_MAGENTA_GLASS_PANE, CRACKED_MAGENTA_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.PURPLE_GLASS, CHISELED_PURPLE_GLASS);
            entries.addAfter(Items.PURPLE_STAINED_GLASS_PANE, CHISELED_PURPLE_GLASS_PANE, CRACKED_PURPLE_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.BLACK_GLASS, CHISELED_BLACK_GLASS);
            entries.addAfter(Items.BLACK_STAINED_GLASS_PANE, CHISELED_BLACK_GLASS_PANE, CRACKED_BLACK_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.GRAY_GLASS, CHISELED_GRAY_GLASS);
            entries.addAfter(Items.GRAY_STAINED_GLASS_PANE, CHISELED_GRAY_GLASS_PANE, CRACKED_GRAY_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.LIGHT_GRAY_GLASS, CHISELED_LIGHT_GRAY_GLASS);
            entries.addAfter(Items.LIGHT_GRAY_STAINED_GLASS_PANE, CHISELED_LIGHT_GRAY_GLASS_PANE, CRACKED_LIGHT_GRAY_GLASS);
            entries.addAfter(ArchitectsAssemblyItems.WHITE_GLASS, CHISELED_WHITE_GLASS);
            entries.addAfter(Items.WHITE_STAINED_GLASS_PANE, CHISELED_WHITE_GLASS_PANE, CRACKED_WHITE_GLASS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.TINTED_GLASS, CRACKED_GLASS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.LAPIS_BLOCK, LAPIS_STAIRS, LAPIS_SLAB, LAPIS_WALL, POLISHED_LAPIS_BLOCK, POLISHED_LAPIS_STAIRS, POLISHED_LAPIS_SLAB, POLISHED_LAPIS_WALL, POLISHED_LAPIS_BRICKS, POLISHED_LAPIS_BRICK_STAIRS, POLISHED_LAPIS_BRICK_SLAB, POLISHED_LAPIS_BRICK_WALL);
            entries.addAfter(Items.BRICKS, CRACKED_BRICKS);
            entries.addAfter(Items.BRICK_WALL, MOSSY_BRICKS, MOSSY_BRICK_STAIRS, MOSSY_BRICK_SLAB, MOSSY_BRICK_WALL);
            entries.addAfter(CUT_COPPER_SLAB, CUT_COPPER_WALL);
            entries.addAfter(EXPOSED_CUT_COPPER_SLAB, EXPOSED_CUT_COPPER_WALL);
            entries.addAfter(WEATHERED_CUT_COPPER_SLAB, WEATHERED_CUT_COPPER_WALL);
            entries.addAfter(OXIDIZED_CUT_COPPER_SLAB, OXIDIZED_CUT_COPPER_WALL);
            entries.addAfter(WAXED_CUT_COPPER_SLAB, WAXED_CUT_COPPER_WALL);
            entries.addAfter(WAXED_EXPOSED_CUT_COPPER_SLAB, WAXED_EXPOSED_CUT_COPPER_WALL);
            entries.addAfter(WAXED_WEATHERED_CUT_COPPER_SLAB, WAXED_WEATHERED_CUT_COPPER_WALL);
            entries.addAfter(WAXED_OXIDIZED_CUT_COPPER_SLAB, WAXED_OXIDIZED_CUT_COPPER_WALL);
        });
    }
    // endregion

    // region Vertical Slabs
    public static Item OAK_SLAB;
    public static Item SPRUCE_SLAB;
    public static Item BIRCH_SLAB;
    public static Item JUNGLE_SLAB;
    public static Item DARK_OAK_SLAB;
    public static Item ACACIA_SLAB;
    public static Item MANGROVE_SLAB;
    public static Item CHERRY_SLAB;
    public static Item PALE_OAK_SLAB;
    public static Item BAMBOO_SLAB;
    public static Item BAMBOO_MOSAIC_SLAB;
    public static Item CRIMSON_SLAB;
    public static Item WARPED_SLAB;
    public static Item STONE_SLAB;
    public static Item SMOOTH_STONE_SLAB;
    public static Item SANDSTONE_SLAB;
    public static Item CUT_SANDSTONE_SLAB;
    public static Item PETRIFIED_OAK_SLAB;
    public static Item COBBLESTONE_SLAB;
    public static Item BRICK_SLAB;
    public static Item STONE_BRICK_SLAB;
    public static Item MUD_BRICK_SLAB;
    public static Item NETHER_BRICK_SLAB;
    public static Item QUARTZ_SLAB;
    public static Item RED_SANDSTONE_SLAB;
    public static Item CUT_RED_SANDSTONE_SLAB;
    public static Item PURPUR_SLAB;
    public static Item PRISMARINE_SLAB;
    public static Item PRISMARINE_BRICK_SLAB;
    public static Item DARK_PRISMARINE_SLAB;
    public static Item GRANITE_SLAB;
    public static Item POLISHED_GRANITE_SLAB;
    public static Item ANDESITE_SLAB;
    public static Item POLISHED_ANDESITE_SLAB;
    public static Item DIORITE_SLAB;
    public static Item POLISHED_DIORITE_SLAB;
    public static Item SMOOTH_SANDSTONE_SLAB;
    public static Item SMOOTH_QUARTZ_SLAB;
    public static Item SMOOTH_RED_SANDSTONE_SLAB;
    public static Item MOSSY_STONE_BRICK_SLAB;
    public static Item END_STONE_BRICK_SLAB;
    public static Item RED_NETHER_BRICK_SLAB;
    public static Item BLACKSTONE_SLAB;
    public static Item POLISHED_BLACKSTONE_SLAB;
    public static Item POLISHED_BLACKSTONE_BRICK_SLAB;
    public static Item OXIDIZED_CUT_COPPER_SLAB;
    public static Item WEATHERED_CUT_COPPER_SLAB;
    public static Item EXPOSED_CUT_COPPER_SLAB;
    public static Item CUT_COPPER_SLAB;
    public static Item WAXED_OXIDIZED_CUT_COPPER_SLAB;
    public static Item WAXED_WEATHERED_CUT_COPPER_SLAB;
    public static Item WAXED_EXPOSED_CUT_COPPER_SLAB;
    public static Item WAXED_CUT_COPPER_SLAB;
    public static Item COBBLED_DEEPSLATE_SLAB;
    public static Item POLISHED_DEEPSLATE_SLAB;
    public static Item DEEPSLATE_TILE_SLAB;
    public static Item DEEPSLATE_BRICK_SLAB;
    public static Item MOSSY_COBBLESTONE_SLAB;
    public static Item TUFF_SLAB;
    public static Item TUFF_BRICK_SLAB;
    public static Item POLISHED_TUFF_SLAB;
    public static Item RESIN_BRICK_SLAB;

    private static void registerVerticalSlabs() {
        OAK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "oak_slab"), settings -> new SlabItem(Blocks.OAK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_OAK_SLAB, settings.useBlockPrefixedTranslationKey()));
        SPRUCE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "spruce_slab"), settings -> new SlabItem(Blocks.SPRUCE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SPRUCE_SLAB, settings.useBlockPrefixedTranslationKey()));
        BIRCH_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "birch_slab"), settings -> new SlabItem(Blocks.BIRCH_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BIRCH_SLAB, settings.useBlockPrefixedTranslationKey()));
        JUNGLE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "jungle_slab"), settings -> new SlabItem(Blocks.JUNGLE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_JUNGLE_SLAB, settings.useBlockPrefixedTranslationKey()));
        DARK_OAK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "dark_oak_slab"), settings -> new SlabItem(Blocks.DARK_OAK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DARK_OAK_SLAB, settings.useBlockPrefixedTranslationKey()));
        ACACIA_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "acacia_slab"), settings -> new SlabItem(Blocks.ACACIA_SLAB, ArchitectsAssemblyBlocks.VERTICAL_ACACIA_SLAB, settings.useBlockPrefixedTranslationKey()));
        MANGROVE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "mangrove_slab"), settings -> new SlabItem(Blocks.MANGROVE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MANGROVE_SLAB, settings.useBlockPrefixedTranslationKey()));
        CHERRY_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cherry_slab"), settings -> new SlabItem(Blocks.CHERRY_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CHERRY_SLAB, settings.useBlockPrefixedTranslationKey()));
        PALE_OAK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "pale_oak_slab"), settings -> new SlabItem(Blocks.PALE_OAK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PALE_OAK_SLAB, settings.useBlockPrefixedTranslationKey()));
        BAMBOO_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "bamboo_slab"), settings -> new SlabItem(Blocks.BAMBOO_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BAMBOO_SLAB, settings.useBlockPrefixedTranslationKey()));
        BAMBOO_MOSAIC_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "bamboo_mosaic_slab"), settings -> new SlabItem(Blocks.BAMBOO_MOSAIC_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB, settings.useBlockPrefixedTranslationKey()));
        CRIMSON_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "crimson_slab"), settings -> new SlabItem(Blocks.CRIMSON_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CRIMSON_SLAB, settings.useBlockPrefixedTranslationKey()));
        WARPED_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "warped_slab"), settings -> new SlabItem(Blocks.WARPED_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WARPED_SLAB, settings.useBlockPrefixedTranslationKey()));
        STONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "stone_slab"), settings -> new SlabItem(Blocks.STONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_STONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        SMOOTH_STONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "smooth_stone_slab"), settings -> new SlabItem(Blocks.SMOOTH_STONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SMOOTH_STONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        SANDSTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "sandstone_slab"), settings -> new SlabItem(Blocks.SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SANDSTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        CUT_SANDSTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cut_sandstone_slab"), settings -> new SlabItem(Blocks.PRISMARINE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PRISMARINE_SLAB, settings.useBlockPrefixedTranslationKey()));
        PETRIFIED_OAK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "petrified_oak_slab"), settings -> new SlabItem(Blocks.CUT_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CUT_SANDSTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        COBBLESTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cobblestone_slab"), settings -> new SlabItem(Blocks.COBBLESTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_COBBLESTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "brick_slab"), settings -> new SlabItem(Blocks.BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        STONE_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "stone_brick_slab"), settings -> new SlabItem(Blocks.STONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_STONE_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        MUD_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "mud_brick_slab"), settings -> new SlabItem(Blocks.MUD_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MUD_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        NETHER_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "nether_brick_slab"), settings -> new SlabItem(Blocks.NETHER_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_NETHER_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        QUARTZ_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "quartz_slab"), settings -> new SlabItem(Blocks.QUARTZ_SLAB, ArchitectsAssemblyBlocks.VERTICAL_QUARTZ_SLAB, settings.useBlockPrefixedTranslationKey()));
        RED_SANDSTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "red_sandstone_slab"), settings -> new SlabItem(Blocks.RED_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_RED_SANDSTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        CUT_RED_SANDSTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cut_red_sandstone_slab"), settings -> new SlabItem(Blocks.CUT_RED_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        PURPUR_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "purpur_slab"), settings -> new SlabItem(Blocks.PURPUR_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PURPUR_SLAB, settings.useBlockPrefixedTranslationKey()));
        PRISMARINE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "prismarine_slab"), settings -> new SlabItem(Blocks.PRISMARINE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PRISMARINE_SLAB, settings.useBlockPrefixedTranslationKey()));
        PRISMARINE_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "prismarine_brick_slab"), settings -> new SlabItem(Blocks.PRISMARINE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PRISMARINE_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        DARK_PRISMARINE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "dark_prismarine_slab"), settings -> new SlabItem(Blocks.DARK_PRISMARINE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DARK_PRISMARINE_SLAB, settings.useBlockPrefixedTranslationKey()));
        GRANITE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "granite_slab"), settings -> new SlabItem(Blocks.GRANITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_GRANITE_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_GRANITE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_granite_slab"), settings -> new SlabItem(Blocks.POLISHED_GRANITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_GRANITE_SLAB, settings.useBlockPrefixedTranslationKey()));
        ANDESITE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "andesite_slab"), settings -> new SlabItem(Blocks.ANDESITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_ANDESITE_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_ANDESITE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_andesite_slab"), settings -> new SlabItem(Blocks.POLISHED_ANDESITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_ANDESITE_SLAB, settings.useBlockPrefixedTranslationKey()));
        DIORITE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "diorite_slab"), settings -> new SlabItem(Blocks.DIORITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DIORITE_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_DIORITE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_diorite_slab"), settings -> new SlabItem(Blocks.POLISHED_DIORITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_DIORITE_SLAB, settings.useBlockPrefixedTranslationKey()));
        SMOOTH_SANDSTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "smooth_sandstone_slab"), settings -> new SlabItem(Blocks.SMOOTH_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        SMOOTH_QUARTZ_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "smooth_quartz_slab"), settings -> new SlabItem(Blocks.SMOOTH_QUARTZ_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB, settings.useBlockPrefixedTranslationKey()));
        SMOOTH_RED_SANDSTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "smooth_red_sandstone_slab"), settings -> new SlabItem(Blocks.SMOOTH_RED_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        MOSSY_STONE_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_stone_brick_slab"), settings -> new SlabItem(Blocks.MOSSY_STONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        END_STONE_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "end_stone_brick_slab"), settings -> new SlabItem(Blocks.END_STONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_END_STONE_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        RED_NETHER_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "red_nether_brick_slab"), settings -> new SlabItem(Blocks.RED_NETHER_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_RED_NETHER_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        BLACKSTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "blackstone_slab"), settings -> new SlabItem(Blocks.BLACKSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BLACKSTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_BLACKSTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_blackstone_slab"), settings -> new SlabItem(Blocks.POLISHED_BLACKSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_BLACKSTONE_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_blackstone_brick_slab"), settings -> new SlabItem(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        OXIDIZED_CUT_COPPER_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "oxidized_cut_copper_slab"), settings -> new SlabItem(Blocks.OXIDIZED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB, settings.useBlockPrefixedTranslationKey()));
        WEATHERED_CUT_COPPER_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "weathered_cut_copper_slab"), settings -> new SlabItem(Blocks.WEATHERED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB, settings.useBlockPrefixedTranslationKey()));
        EXPOSED_CUT_COPPER_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "exposed_cut_copper_slab"), settings -> new SlabItem(Blocks.EXPOSED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB, settings.useBlockPrefixedTranslationKey()));
        CUT_COPPER_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cut_copper_slab"), settings -> new SlabItem(Blocks.CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CUT_COPPER_SLAB, settings.useBlockPrefixedTranslationKey()));
        WAXED_OXIDIZED_CUT_COPPER_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_oxidized_cut_copper_slab"), settings -> new SlabItem(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB, settings.useBlockPrefixedTranslationKey()));
        WAXED_WEATHERED_CUT_COPPER_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_weathered_cut_copper_slab"), settings -> new SlabItem(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB, settings.useBlockPrefixedTranslationKey()));
        WAXED_EXPOSED_CUT_COPPER_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_exposed_cut_copper_slab"), settings -> new SlabItem(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB, settings.useBlockPrefixedTranslationKey()));
        WAXED_CUT_COPPER_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "waxed_cut_copper_slab"), settings -> new SlabItem(Blocks.WAXED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB, settings.useBlockPrefixedTranslationKey()));
        COBBLED_DEEPSLATE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "cobbled_deepslate_slab"), settings -> new SlabItem(Blocks.COBBLED_DEEPSLATE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_DEEPSLATE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_deepslate_slab"), settings -> new SlabItem(Blocks.POLISHED_DEEPSLATE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB, settings.useBlockPrefixedTranslationKey()));
        DEEPSLATE_TILE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "deepslate_tile_slab"), settings -> new SlabItem(Blocks.DEEPSLATE_TILE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DEEPSLATE_TILE_SLAB, settings.useBlockPrefixedTranslationKey()));
        DEEPSLATE_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "deepslate_brick_slab"), settings -> new SlabItem(Blocks.DEEPSLATE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        MOSSY_COBBLESTONE_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "mossy_cobblestone_slab"), settings -> new SlabItem(Blocks.MOSSY_COBBLESTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB, settings.useBlockPrefixedTranslationKey()));
        TUFF_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "tuff_slab"), settings -> new SlabItem(Blocks.TUFF_SLAB, ArchitectsAssemblyBlocks.VERTICAL_TUFF_SLAB, settings.useBlockPrefixedTranslationKey()));
        TUFF_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "tuff_brick_slab"), settings -> new SlabItem(Blocks.TUFF_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_TUFF_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        POLISHED_TUFF_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_tuff_slab"), settings -> new SlabItem(Blocks.POLISHED_TUFF_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_TUFF_SLAB, settings.useBlockPrefixedTranslationKey()));
        RESIN_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "resin_brick_slab"), settings -> new SlabItem(Blocks.RESIN_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_RESIN_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            replaceVanillaSlabs(entries.getDisplayStacks());
            replaceVanillaSlabs(entries.getSearchTabStacks());
        });
    }

    private static final Map<Item, Supplier<Item>> SLABS = new ImmutableMap.Builder<Item, Supplier<Item>>()
            .put(Items.OAK_SLAB, () -> OAK_SLAB)
            .put(Items.SPRUCE_SLAB, () -> SPRUCE_SLAB)
            .put(Items.BIRCH_SLAB, () -> BIRCH_SLAB)
            .put(Items.JUNGLE_SLAB, () -> JUNGLE_SLAB)
            .put(Items.ACACIA_SLAB, () -> ACACIA_SLAB)
            .put(Items.DARK_OAK_SLAB, () -> DARK_OAK_SLAB)
            .put(Items.MANGROVE_SLAB, () -> MANGROVE_SLAB)
            .put(Items.CHERRY_SLAB, () -> CHERRY_SLAB)
            .put(Items.PALE_OAK_SLAB, () -> PALE_OAK_SLAB)
            .put(Items.BAMBOO_SLAB, () -> BAMBOO_SLAB)
            .put(Items.BAMBOO_MOSAIC_SLAB, () -> BAMBOO_MOSAIC_SLAB)
            .put(Items.CRIMSON_SLAB, () -> CRIMSON_SLAB)
            .put(Items.WARPED_SLAB, () -> WARPED_SLAB)
            .put(Items.STONE_SLAB, () -> STONE_SLAB)
            .put(Items.SMOOTH_STONE_SLAB, () -> SMOOTH_STONE_SLAB)
            .put(Items.SANDSTONE_SLAB, () -> SANDSTONE_SLAB)
            .put(Items.CUT_SANDSTONE_SLAB, () -> CUT_SANDSTONE_SLAB)
            .put(Items.RED_SANDSTONE_SLAB, () -> RED_SANDSTONE_SLAB)
            .put(Items.CUT_RED_SANDSTONE_SLAB, () -> CUT_RED_SANDSTONE_SLAB)
            .put(Items.PETRIFIED_OAK_SLAB, () -> PETRIFIED_OAK_SLAB)
            .put(Items.STONE_BRICK_SLAB, () -> STONE_BRICK_SLAB)
            .put(Items.BRICK_SLAB, () -> BRICK_SLAB)
            .put(Items.MUD_BRICK_SLAB, () -> MUD_BRICK_SLAB)
            .put(Items.NETHER_BRICK_SLAB, () -> NETHER_BRICK_SLAB)
            .put(Items.RED_NETHER_BRICK_SLAB, () -> RED_NETHER_BRICK_SLAB)
            .put(Items.END_STONE_BRICK_SLAB, () -> END_STONE_BRICK_SLAB)
            .put(Items.QUARTZ_SLAB, () -> QUARTZ_SLAB)
            .put(Items.PURPUR_SLAB, () -> PURPUR_SLAB)
            .put(Items.PRISMARINE_SLAB, () -> PRISMARINE_SLAB)
            .put(Items.PRISMARINE_BRICK_SLAB, () -> PRISMARINE_BRICK_SLAB)
            .put(Items.DARK_PRISMARINE_SLAB, () -> DARK_PRISMARINE_SLAB)
            .put(Items.GRANITE_SLAB, () -> GRANITE_SLAB)
            .put(Items.POLISHED_GRANITE_SLAB, () -> POLISHED_GRANITE_SLAB)
            .put(Items.DIORITE_SLAB, () -> DIORITE_SLAB)
            .put(Items.POLISHED_DIORITE_SLAB, () -> POLISHED_DIORITE_SLAB)
            .put(Items.ANDESITE_SLAB, () -> ANDESITE_SLAB)
            .put(Items.POLISHED_ANDESITE_SLAB, () -> POLISHED_ANDESITE_SLAB)
            .put(Items.SMOOTH_SANDSTONE_SLAB, () -> SMOOTH_SANDSTONE_SLAB)
            .put(Items.SMOOTH_RED_SANDSTONE_SLAB, () -> SMOOTH_RED_SANDSTONE_SLAB)
            .put(Items.SMOOTH_QUARTZ_SLAB, () -> SMOOTH_QUARTZ_SLAB)
            .put(Items.MOSSY_STONE_BRICK_SLAB, () -> MOSSY_STONE_BRICK_SLAB)
            .put(Items.MOSSY_COBBLESTONE_SLAB, () -> MOSSY_COBBLESTONE_SLAB)
            .put(Items.COBBLESTONE_SLAB, () -> COBBLESTONE_SLAB)
            .put(Items.BLACKSTONE_SLAB, () -> BLACKSTONE_SLAB)
            .put(Items.POLISHED_BLACKSTONE_SLAB, () -> POLISHED_BLACKSTONE_SLAB)
            .put(Items.POLISHED_BLACKSTONE_BRICK_SLAB, () -> POLISHED_BLACKSTONE_BRICK_SLAB)
            .put(Items.COBBLED_DEEPSLATE_SLAB, () -> COBBLED_DEEPSLATE_SLAB)
            .put(Items.POLISHED_DEEPSLATE_SLAB, () -> POLISHED_DEEPSLATE_SLAB)
            .put(Items.DEEPSLATE_BRICK_SLAB, () -> DEEPSLATE_BRICK_SLAB)
            .put(Items.DEEPSLATE_TILE_SLAB, () -> DEEPSLATE_TILE_SLAB)
            .put(Items.CUT_COPPER_SLAB, () -> CUT_COPPER_SLAB)
            .put(Items.EXPOSED_CUT_COPPER_SLAB, () -> EXPOSED_CUT_COPPER_SLAB)
            .put(Items.WEATHERED_CUT_COPPER_SLAB, () -> WEATHERED_CUT_COPPER_SLAB)
            .put(Items.OXIDIZED_CUT_COPPER_SLAB, () -> OXIDIZED_CUT_COPPER_SLAB)
            .put(Items.WAXED_CUT_COPPER_SLAB, () -> WAXED_CUT_COPPER_SLAB)
            .put(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, () -> WAXED_EXPOSED_CUT_COPPER_SLAB)
            .put(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, () -> WAXED_WEATHERED_CUT_COPPER_SLAB)
            .put(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, () -> WAXED_OXIDIZED_CUT_COPPER_SLAB)
            .put(Items.TUFF_SLAB, () -> TUFF_SLAB)
            .put(Items.TUFF_BRICK_SLAB, () -> TUFF_BRICK_SLAB)
            .put(Items.POLISHED_TUFF_SLAB, () -> POLISHED_TUFF_SLAB)
            .put(Items.RESIN_BRICK_SLAB, () -> RESIN_BRICK_SLAB)
            .build();

    private static void replaceVanillaSlabs(List<ItemStack> stacks) {
        stacks.replaceAll(stack -> {
            Item item = stack.getItem();
            if (SLABS.containsKey(item))
                return SLABS.get(item).get().getDefaultStack();
            return stack;
        });
    }
    // endregion

    // region Missing Blocks
    public static Item QUARTZ_BRICK_STAIRS;
    public static Item QUARTZ_BRICK_SLAB;
    public static Item QUARTZ_BRICK_WALL;
    public static Item PRISMARINE_BRICK_WALL;
    public static Item DARK_PRISMARINE_WALL;
    public static Item SMOOTH_SANDSTONE_WALL;
    public static Item SMOOTH_RED_SANDSTONE_WALL;
    public static Item POLISHED_GRANITE_WALL;
    public static Item POLISHED_ANDESITE_WALL;
    public static Item POLISHED_DIORITE_WALL;
    public static Item PURPUR_WALL;
    public static Item CRACKED_MUD_BRICKS;
    public static Item CRACKED_QUARTZ_BRICKS;
    public static Item CRACKED_RED_NETHER_BRICKS;
    public static Item CRACKED_END_STONE_BRICKS;

    private static void registerMissingBlocks() {
        QUARTZ_BRICK_STAIRS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "quartz_brick_stairs"), ArchitectsAssemblyBlocks.QUARTZ_BRICK_STAIRS);
        QUARTZ_BRICK_SLAB = RegistryHelper.registerItem(Identifier.of(GreatBigWorld.NAMESPACE, "quartz_brick_slab"), settings -> new SlabItem(ArchitectsAssemblyBlocks.QUARTZ_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_QUARTZ_BRICK_SLAB, settings.useBlockPrefixedTranslationKey()));
        QUARTZ_BRICK_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "quartz_brick_wall"), ArchitectsAssemblyBlocks.QUARTZ_BRICK_WALL);
        PRISMARINE_BRICK_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "prismarine_brick_wall"), ArchitectsAssemblyBlocks.PRISMARINE_BRICK_WALL);
        DARK_PRISMARINE_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "dark_prismarine_wall"), ArchitectsAssemblyBlocks.DARK_PRISMARINE_WALL);
        SMOOTH_SANDSTONE_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "smooth_sandstone_wall"), ArchitectsAssemblyBlocks.SMOOTH_SANDSTONE_WALL);
        SMOOTH_RED_SANDSTONE_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "smooth_red_sandstone_wall"), ArchitectsAssemblyBlocks.SMOOTH_RED_SANDSTONE_WALL);
        POLISHED_GRANITE_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_granite_wall"), ArchitectsAssemblyBlocks.POLISHED_GRANITE_WALL);
        POLISHED_ANDESITE_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_andesite_wall"), ArchitectsAssemblyBlocks.POLISHED_ANDESITE_WALL);
        POLISHED_DIORITE_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "polished_diorite_wall"), ArchitectsAssemblyBlocks.POLISHED_DIORITE_WALL);
        PURPUR_WALL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "purpur_wall"), ArchitectsAssemblyBlocks.PURPUR_WALL);
        CRACKED_MUD_BRICKS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_mud_bricks"), ArchitectsAssemblyBlocks.CRACKED_MUD_BRICKS);
        CRACKED_QUARTZ_BRICKS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_quartz_bricks"), ArchitectsAssemblyBlocks.CRACKED_QUARTZ_BRICKS);
        CRACKED_RED_NETHER_BRICKS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_red_nether_bricks"), ArchitectsAssemblyBlocks.CRACKED_RED_NETHER_BRICKS);
        CRACKED_END_STONE_BRICKS = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "cracked_end_stone_bricks"), ArchitectsAssemblyBlocks.CRACKED_END_STONE_BRICKS);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.QUARTZ_BRICKS, QUARTZ_BRICK_STAIRS, QUARTZ_BRICK_SLAB, QUARTZ_BRICK_WALL);
            entries.addAfter(ArchitectsAssemblyItems.PRISMARINE_BRICK_SLAB, PRISMARINE_BRICK_WALL);
            entries.addAfter(ArchitectsAssemblyItems.DARK_PRISMARINE_SLAB, DARK_PRISMARINE_WALL);
            entries.addAfter(ArchitectsAssemblyItems.SMOOTH_SANDSTONE_SLAB, SMOOTH_SANDSTONE_WALL);
            entries.addAfter(ArchitectsAssemblyItems.SMOOTH_RED_SANDSTONE_SLAB, SMOOTH_RED_SANDSTONE_WALL);
            entries.addAfter(ArchitectsAssemblyItems.POLISHED_GRANITE_SLAB, POLISHED_GRANITE_WALL);
            entries.addAfter(ArchitectsAssemblyItems.POLISHED_ANDESITE_SLAB, POLISHED_ANDESITE_WALL);
            entries.addAfter(ArchitectsAssemblyItems.POLISHED_DIORITE_SLAB, POLISHED_DIORITE_WALL);
            entries.addAfter(ArchitectsAssemblyItems.PURPUR_SLAB, PURPUR_WALL);
            entries.addAfter(Items.MUD_BRICKS, CRACKED_MUD_BRICKS);
            entries.addAfter(Items.QUARTZ_BRICKS, CRACKED_QUARTZ_BRICKS);
            entries.addAfter(Items.RED_NETHER_BRICKS, CRACKED_RED_NETHER_BRICKS);
            entries.addAfter(Items.END_STONE_BRICKS, CRACKED_END_STONE_BRICKS);
        });
    }
    // endregion

    // region Improved Blocks
    public static Item TORCH;
    public static Item SOUL_TORCH;

    private static void registerImprovedBlocks() {
        TORCH = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "torch"), ArchitectsAssemblyBlocks.TORCH);
        SOUL_TORCH = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "soul_torch"), ArchitectsAssemblyBlocks.SOUL_TORCH);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            replaceImprovedBlocks(entries.getDisplayStacks());
            replaceImprovedBlocks(entries.getSearchTabStacks());
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            replaceImprovedBlocks(entries.getDisplayStacks());
            replaceImprovedBlocks(entries.getSearchTabStacks());
        });
    }

    private static void replaceImprovedBlocks(List<ItemStack> stacks) {
        stacks.replaceAll(stack -> {
            if (stack.isOf(Items.TORCH))
                return TORCH.getDefaultStack();
            if (stack.isOf(Items.SOUL_TORCH))
                return SOUL_TORCH.getDefaultStack();
            return stack;
        });
    }
    // endregion

    // region Misc Blocks
    public static Item SAWMILL;

    private static void registerMiscBlocks() {
        SAWMILL = RegistryHelper.registerBlockItem(Identifier.of(GreatBigWorld.NAMESPACE, "sawmill"), ArchitectsAssemblyBlocks.SAWMILL);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.STONECUTTER, SAWMILL);
        });
    }
    // endregion
}
