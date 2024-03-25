package dev.creoii.greatbigworld.architectsassembly.registry;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Lifecycle;
import dev.creoii.creoapi.api.item.CreoItemSettings;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import dev.creoii.greatbigworld.architectsassembly.item.DyedItemFrameItem;
import dev.creoii.greatbigworld.architectsassembly.item.SlabItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public final class ArchitectsAssemblyItems {
    public static void register() {
        registerItems();
        registerDecorativeBlocks();
        registerVerticalSlabs();
        registerMissingBlocks();
        registerImprovedBlocks();
        registerMiscBlocks();
    }

    // region Items
    public static final Item BROWN_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.BROWN, new CreoItemSettings());
    public static final Item RED_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.RED, new CreoItemSettings());
    public static final Item ORANGE_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.ORANGE, new CreoItemSettings());
    public static final Item YELLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.YELLOW, new CreoItemSettings());
    public static final Item LIME_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.LIME, new CreoItemSettings());
    public static final Item GREEN_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.GREEN, new CreoItemSettings());
    public static final Item CYAN_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.CYAN, new CreoItemSettings());
    public static final Item BLUE_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.BLUE, new CreoItemSettings());
    public static final Item LIGHT_BLUE_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.LIGHT_BLUE, new CreoItemSettings());
    public static final Item PINK_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.PINK, new CreoItemSettings());
    public static final Item MAGENTA_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.MAGENTA, new CreoItemSettings());
    public static final Item PURPLE_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.PURPLE, new CreoItemSettings());
    public static final Item BLACK_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.BLACK, new CreoItemSettings());
    public static final Item GRAY_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.GRAY, new CreoItemSettings());
    public static final Item LIGHT_GRAY_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.LIGHT_GRAY, new CreoItemSettings());
    public static final Item WHITE_ITEM_FRAME = new DyedItemFrameItem(EntityType.ITEM_FRAME, DyeColor.WHITE, new CreoItemSettings());
    public static final Item BROWN_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.BROWN, new CreoItemSettings());
    public static final Item RED_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.RED, new CreoItemSettings());
    public static final Item ORANGE_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.ORANGE, new CreoItemSettings());
    public static final Item YELLOW_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.YELLOW, new CreoItemSettings());
    public static final Item LIME_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.LIME, new CreoItemSettings());
    public static final Item GREEN_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.GREEN, new CreoItemSettings());
    public static final Item CYAN_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.CYAN, new CreoItemSettings());
    public static final Item BLUE_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.BLUE, new CreoItemSettings());
    public static final Item LIGHT_BLUE_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.LIGHT_BLUE, new CreoItemSettings());
    public static final Item PINK_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.PINK, new CreoItemSettings());
    public static final Item MAGENTA_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.MAGENTA, new CreoItemSettings());
    public static final Item PURPLE_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.PURPLE, new CreoItemSettings());
    public static final Item BLACK_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.BLACK, new CreoItemSettings());
    public static final Item GRAY_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.GRAY, new CreoItemSettings());
    public static final Item LIGHT_GRAY_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.LIGHT_GRAY, new CreoItemSettings());
    public static final Item WHITE_GLOW_ITEM_FRAME = new DyedItemFrameItem(EntityType.GLOW_ITEM_FRAME, DyeColor.WHITE, new CreoItemSettings());
    public static final Item GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item BROWN_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item RED_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item ORANGE_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item YELLOW_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item LIME_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item GREEN_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item CYAN_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item BLUE_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item LIGHT_BLUE_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item PINK_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item MAGENTA_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item PURPLE_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item BLACK_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item GRAY_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item LIGHT_GRAY_GLASS_SHARD = new Item(new CreoItemSettings());
    public static final Item WHITE_GLASS_SHARD = new Item(new CreoItemSettings());

    private static void registerItems() {
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "brown_item_frame"), BROWN_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "red_item_frame"), RED_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "orange_item_frame"), ORANGE_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "yellow_item_frame"), YELLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "lime_item_frame"), LIME_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "green_item_frame"), GREEN_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cyan_item_frame"), CYAN_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "blue_item_frame"), BLUE_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "light_blue_item_frame"), LIGHT_BLUE_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "pink_item_frame"), PINK_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "magenta_item_frame"), MAGENTA_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "purple_item_frame"), PURPLE_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "black_item_frame"), BLACK_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "gray_item_frame"), GRAY_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "light_gray_item_frame"), LIGHT_GRAY_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "white_item_frame"), WHITE_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "brown_glow_item_frame"), BROWN_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "red_glow_item_frame"), RED_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "orange_glow_item_frame"), ORANGE_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "yellow_glow_item_frame"), YELLOW_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "lime_glow_item_frame"), LIME_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "green_glow_item_frame"), GREEN_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cyan_glow_item_frame"), CYAN_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "blue_glow_item_frame"), BLUE_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "light_blue_glow_item_frame"), LIGHT_BLUE_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "pink_glow_item_frame"), PINK_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "magenta_glow_item_frame"), MAGENTA_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "purple_glow_item_frame"), PURPLE_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "black_glow_item_frame"), BLACK_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "gray_glow_item_frame"), GRAY_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "light_gray_glow_item_frame"), LIGHT_GRAY_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "white_glow_item_frame"), WHITE_GLOW_ITEM_FRAME);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "glass_shard"), GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "brown_glass_shard"), BROWN_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "red_glass_shard"), RED_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "orange_glass_shard"), ORANGE_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "yellow_glass_shard"), YELLOW_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "lime_glass_shard"), LIME_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "green_glass_shard"), GREEN_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cyan_glass_shard"), CYAN_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "blue_glass_shard"), BLUE_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "light_blue_glass_shard"), LIGHT_BLUE_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "pink_glass_shard"), PINK_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "magenta_glass_shard"), MAGENTA_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "purple_glass_shard"), PURPLE_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "black_glass_shard"), BLACK_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "gray_glass_shard"), GRAY_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "light_gray_glass_shard"), LIGHT_GRAY_GLASS_SHARD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "white_glass_shard"), WHITE_GLASS_SHARD);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.ITEM_FRAME, BROWN_ITEM_FRAME, RED_ITEM_FRAME, ORANGE_ITEM_FRAME, YELLOW_ITEM_FRAME, LIME_ITEM_FRAME, GREEN_ITEM_FRAME, CYAN_ITEM_FRAME, BLUE_ITEM_FRAME, LIGHT_BLUE_ITEM_FRAME, PINK_ITEM_FRAME, MAGENTA_ITEM_FRAME, PURPLE_ITEM_FRAME, BLACK_ITEM_FRAME, GRAY_ITEM_FRAME, LIGHT_GRAY_ITEM_FRAME, WHITE_ITEM_FRAME);
            entries.addAfter(Items.GLOW_ITEM_FRAME, BROWN_GLOW_ITEM_FRAME, RED_GLOW_ITEM_FRAME, ORANGE_GLOW_ITEM_FRAME, YELLOW_GLOW_ITEM_FRAME, LIME_GLOW_ITEM_FRAME, GREEN_GLOW_ITEM_FRAME, CYAN_GLOW_ITEM_FRAME, BLUE_GLOW_ITEM_FRAME, LIGHT_BLUE_GLOW_ITEM_FRAME, PINK_GLOW_ITEM_FRAME, MAGENTA_GLOW_ITEM_FRAME, PURPLE_GLOW_ITEM_FRAME, BLACK_GLOW_ITEM_FRAME, GRAY_GLOW_ITEM_FRAME, LIGHT_GRAY_GLOW_ITEM_FRAME, WHITE_GLOW_ITEM_FRAME);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.PINK_DYE, GLASS_SHARD, BROWN_GLASS_SHARD, RED_GLASS_SHARD, ORANGE_GLASS_SHARD, YELLOW_GLASS_SHARD, LIME_GLASS_SHARD, GREEN_GLASS_SHARD, CYAN_GLASS_SHARD, BLUE_GLASS_SHARD, LIGHT_BLUE_GLASS_SHARD, PINK_GLASS_SHARD, MAGENTA_GLASS_SHARD, PURPLE_GLASS_SHARD, BLACK_GLASS_SHARD, GRAY_GLASS_SHARD, LIGHT_GRAY_GLASS_SHARD, WHITE_GLASS_SHARD);
        });
    }
    // endregion

    // region Decorative Blocks
    public static final Item CHISELED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_BROWN_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_BROWN_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_BROWN_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_BROWN_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_RED_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_RED_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_RED_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_RED_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_ORANGE_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_ORANGE_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_ORANGE_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_ORANGE_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_YELLOW_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_YELLOW_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_YELLOW_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_YELLOW_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_LIME_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_LIME_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_LIME_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_LIME_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_GREEN_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_GREEN_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_GREEN_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_GREEN_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_CYAN_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_CYAN_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_CYAN_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_CYAN_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_BLUE_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_BLUE_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_BLUE_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_BLUE_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_LIGHT_BLUE_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_LIGHT_BLUE_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_PINK_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_PINK_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_PINK_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_PINK_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_MAGENTA_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_MAGENTA_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_MAGENTA_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_MAGENTA_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_PURPLE_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_PURPLE_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_PURPLE_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_PURPLE_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_BLACK_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_BLACK_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_BLACK_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_BLACK_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_GRAY_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_GRAY_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_GRAY_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_GRAY_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_LIGHT_GRAY_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_LIGHT_GRAY_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item CHISELED_WHITE_STAINED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_WHITE_STAINED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_WHITE_STAINED_GLASS_PANE = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_WHITE_STAINED_GLASS_PANE, new CreoItemSettings());
    public static final Item POLISHED_LAPIS_BLOCK = new BlockItem(ArchitectsAssemblyBlocks.POLISHED_LAPIS_BLOCK, new CreoItemSettings());
    public static final Item POLISHED_LAPIS_STAIRS = new BlockItem(ArchitectsAssemblyBlocks.POLISHED_LAPIS_STAIRS, new CreoItemSettings());
    public static final Item POLISHED_LAPIS_SLAB = new SlabItem(ArchitectsAssemblyBlocks.POLISHED_LAPIS_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_LAPIS_SLAB, new CreoItemSettings());
    public static final Item CRACKED_BRICKS = new BlockItem(ArchitectsAssemblyBlocks.CRACKED_BRICKS, new CreoItemSettings());
    public static final Item MOSSY_BRICKS = new BlockItem(ArchitectsAssemblyBlocks.MOSSY_BRICKS, new CreoItemSettings());
    public static final Item MOSSY_BRICK_STAIRS = new BlockItem(ArchitectsAssemblyBlocks.MOSSY_BRICK_STAIRS, new CreoItemSettings());
    public static final Item MOSSY_BRICK_SLAB = new SlabItem(ArchitectsAssemblyBlocks.MOSSY_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_BRICK_SLAB, new CreoItemSettings());
    public static final Item MOSSY_BRICK_WALL = new BlockItem(ArchitectsAssemblyBlocks.MOSSY_BRICK_WALL, new CreoItemSettings());
    public static final Item GLASS = new BlockItem(ArchitectsAssemblyBlocks.GLASS, new CreoItemSettings());
    public static final Item SHATTERED_GLASS = new BlockItem(ArchitectsAssemblyBlocks.SHATTERED_GLASS, new CreoItemSettings());
    public static final Item CHISELED_OAK_PLANKS = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_OAK_PLANKS, new CreoItemSettings());
    public static final Item CHISELED_OAK_LOG = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_OAK_LOG, new CreoItemSettings());
    public static final Item CHISELED_OAK_WOOD = new BlockItem(ArchitectsAssemblyBlocks.CHISELED_OAK_WOOD, new CreoItemSettings());
    public static final Item STRIPPED_CHISELED_OAK_LOG = new BlockItem(ArchitectsAssemblyBlocks.STRIPPED_CHISELED_OAK_LOG, new CreoItemSettings());
    public static final Item STRIPPED_CHISELED_OAK_WOOD = new BlockItem(ArchitectsAssemblyBlocks.STRIPPED_CHISELED_OAK_WOOD, new CreoItemSettings());
    public static final Item CUT_COPPER_WALL = new BlockItem(ArchitectsAssemblyBlocks.CUT_COPPER_WALL, new CreoItemSettings());
    public static final Item WAXED_CUT_COPPER_WALL = new BlockItem(ArchitectsAssemblyBlocks.WAXED_CUT_COPPER_WALL, new CreoItemSettings());
    public static final Item EXPOSED_CUT_COPPER_WALL = new BlockItem(ArchitectsAssemblyBlocks.EXPOSED_CUT_COPPER_WALL, new CreoItemSettings());
    public static final Item WAXED_EXPOSED_CUT_COPPER_WALL = new BlockItem(ArchitectsAssemblyBlocks.WAXED_EXPOSED_CUT_COPPER_WALL, new CreoItemSettings());
    public static final Item WEATHERED_CUT_COPPER_WALL = new BlockItem(ArchitectsAssemblyBlocks.WEATHERED_CUT_COPPER_WALL, new CreoItemSettings());
    public static final Item WAXED_WEATHERED_CUT_COPPER_WALL = new BlockItem(ArchitectsAssemblyBlocks.WAXED_WEATHERED_CUT_COPPER_WALL, new CreoItemSettings());
    public static final Item OXIDIZED_CUT_COPPER_WALL = new BlockItem(ArchitectsAssemblyBlocks.OXIDIZED_CUT_COPPER_WALL, new CreoItemSettings());
    public static final Item WAXED_OXIDIZED_CUT_COPPER_WALL = new BlockItem(ArchitectsAssemblyBlocks.WAXED_OXIDIZED_CUT_COPPER_WALL, new CreoItemSettings());

    private static void registerDecorativeBlocks() {
        final SimpleDefaultedRegistry<Item> items = (SimpleDefaultedRegistry<Item>) Registries.ITEM;

        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_glass"), CHISELED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_glass_pane"), CHISELED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_brown_stained_glass"), CHISELED_BROWN_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_brown_stained_glass_pane"), CHISELED_BROWN_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_red_stained_glass"), CHISELED_RED_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_red_stained_glass_pane"), CHISELED_RED_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_orange_stained_glass"), CHISELED_ORANGE_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_orange_stained_glass_pane"), CHISELED_ORANGE_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_yellow_stained_glass"), CHISELED_YELLOW_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_yellow_stained_glass_pane"), CHISELED_YELLOW_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_lime_stained_glass"), CHISELED_LIME_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_lime_stained_glass_pane"), CHISELED_LIME_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_green_stained_glass"), CHISELED_GREEN_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_green_stained_glass_pane"), CHISELED_GREEN_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_cyan_stained_glass"), CHISELED_CYAN_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_cyan_stained_glass_pane"), CHISELED_CYAN_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_blue_stained_glass"), CHISELED_BLUE_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_blue_stained_glass_pane"), CHISELED_BLUE_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_light_blue_stained_glass"), CHISELED_LIGHT_BLUE_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_light_blue_stained_glass_pane"), CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_pink_stained_glass"), CHISELED_PINK_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_pink_stained_glass_pane"), CHISELED_PINK_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_magenta_stained_glass"), CHISELED_MAGENTA_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_magenta_stained_glass_pane"), CHISELED_MAGENTA_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_purple_stained_glass"), CHISELED_PURPLE_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_purple_stained_glass_pane"), CHISELED_PURPLE_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_black_stained_glass"), CHISELED_BLACK_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_black_stained_glass_pane"), CHISELED_BLACK_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_gray_stained_glass"), CHISELED_GRAY_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_gray_stained_glass_pane"), CHISELED_GRAY_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_light_gray_stained_glass"), CHISELED_LIGHT_GRAY_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_light_gray_stained_glass_pane"), CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_white_stained_glass"), CHISELED_WHITE_STAINED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_white_stained_glass_pane"), CHISELED_WHITE_STAINED_GLASS_PANE);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "polished_lapis_block"), POLISHED_LAPIS_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "polished_lapis_stairs"), POLISHED_LAPIS_STAIRS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "polished_lapis_slab"), POLISHED_LAPIS_SLAB);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cracked_bricks"), CRACKED_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "mossy_bricks"), MOSSY_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "mossy_brick_stairs"), MOSSY_BRICK_STAIRS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "mossy_brick_slab"), MOSSY_BRICK_SLAB);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "mossy_brick_wall"), MOSSY_BRICK_WALL);
        items.set(items.getRawId(Items.GLASS), RegistryKey.of(RegistryKeys.ITEM, new Identifier("glass")), GLASS, Lifecycle.stable());
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "shattered_glass"), SHATTERED_GLASS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_oak_planks"), CHISELED_OAK_PLANKS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_oak_log"), CHISELED_OAK_LOG);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "chiseled_oak_wood"), CHISELED_OAK_WOOD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "stripped_chiseled_oak_log"), STRIPPED_CHISELED_OAK_LOG);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "stripped_chiseled_oak_wood"), STRIPPED_CHISELED_OAK_WOOD);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cut_copper_wall"), CUT_COPPER_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "waxed_cut_copper_wall"), WAXED_CUT_COPPER_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "exposed_cut_copper_wall"), EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "waxed_exposed_cut_copper_wall"), WAXED_EXPOSED_CUT_COPPER_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "weathered_cut_copper_wall"), WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "waxed_weathered_cut_copper_wall"), WAXED_WEATHERED_CUT_COPPER_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "oxidized_cut_copper_wall"), OXIDIZED_CUT_COPPER_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "waxed_oxidized_cut_copper_wall"), WAXED_OXIDIZED_CUT_COPPER_WALL);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(entries -> {
            entries.addAfter(Items.GLASS, CHISELED_GLASS, SHATTERED_GLASS);
            entries.addAfter(Items.GLASS_PANE, CHISELED_GLASS_PANE);
            entries.addAfter(Items.BROWN_STAINED_GLASS, CHISELED_BROWN_STAINED_GLASS);
            entries.addAfter(Items.BROWN_STAINED_GLASS_PANE, CHISELED_BROWN_STAINED_GLASS_PANE);
            entries.addAfter(Items.RED_STAINED_GLASS, CHISELED_RED_STAINED_GLASS);
            entries.addAfter(Items.RED_STAINED_GLASS_PANE, CHISELED_RED_STAINED_GLASS_PANE);
            entries.addAfter(Items.ORANGE_STAINED_GLASS, CHISELED_ORANGE_STAINED_GLASS);
            entries.addAfter(Items.ORANGE_STAINED_GLASS_PANE, CHISELED_ORANGE_STAINED_GLASS_PANE);
            entries.addAfter(Items.YELLOW_STAINED_GLASS, CHISELED_YELLOW_STAINED_GLASS);
            entries.addAfter(Items.YELLOW_STAINED_GLASS_PANE, CHISELED_YELLOW_STAINED_GLASS_PANE);
            entries.addAfter(Items.LIME_STAINED_GLASS, CHISELED_LIME_STAINED_GLASS);
            entries.addAfter(Items.LIME_STAINED_GLASS_PANE, CHISELED_LIME_STAINED_GLASS_PANE);
            entries.addAfter(Items.GREEN_STAINED_GLASS, CHISELED_GREEN_STAINED_GLASS);
            entries.addAfter(Items.GREEN_STAINED_GLASS_PANE, CHISELED_GREEN_STAINED_GLASS_PANE);
            entries.addAfter(Items.CYAN_STAINED_GLASS, CHISELED_CYAN_STAINED_GLASS);
            entries.addAfter(Items.CYAN_STAINED_GLASS_PANE, CHISELED_CYAN_STAINED_GLASS_PANE);
            entries.addAfter(Items.BLUE_STAINED_GLASS, CHISELED_BLUE_STAINED_GLASS);
            entries.addAfter(Items.BLUE_STAINED_GLASS_PANE, CHISELED_BLUE_STAINED_GLASS_PANE);
            entries.addAfter(Items.LIGHT_BLUE_STAINED_GLASS, CHISELED_LIGHT_BLUE_STAINED_GLASS);
            entries.addAfter(Items.LIGHT_BLUE_STAINED_GLASS_PANE, CHISELED_LIGHT_BLUE_STAINED_GLASS_PANE);
            entries.addAfter(Items.PINK_STAINED_GLASS, CHISELED_PINK_STAINED_GLASS);
            entries.addAfter(Items.PINK_STAINED_GLASS_PANE, CHISELED_PINK_STAINED_GLASS_PANE);
            entries.addAfter(Items.MAGENTA_STAINED_GLASS, CHISELED_MAGENTA_STAINED_GLASS);
            entries.addAfter(Items.MAGENTA_STAINED_GLASS_PANE, CHISELED_MAGENTA_STAINED_GLASS_PANE);
            entries.addAfter(Items.PURPLE_STAINED_GLASS, CHISELED_PURPLE_STAINED_GLASS);
            entries.addAfter(Items.PURPLE_STAINED_GLASS_PANE, CHISELED_PURPLE_STAINED_GLASS_PANE);
            entries.addAfter(Items.BLACK_STAINED_GLASS, CHISELED_BLACK_STAINED_GLASS);
            entries.addAfter(Items.BLACK_STAINED_GLASS_PANE, CHISELED_BLACK_STAINED_GLASS_PANE);
            entries.addAfter(Items.GRAY_STAINED_GLASS, CHISELED_GRAY_STAINED_GLASS);
            entries.addAfter(Items.GRAY_STAINED_GLASS_PANE, CHISELED_GRAY_STAINED_GLASS_PANE);
            entries.addAfter(Items.LIGHT_GRAY_STAINED_GLASS, CHISELED_LIGHT_GRAY_STAINED_GLASS);
            entries.addAfter(Items.LIGHT_GRAY_STAINED_GLASS_PANE, CHISELED_LIGHT_GRAY_STAINED_GLASS_PANE);
            entries.addAfter(Items.WHITE_STAINED_GLASS, CHISELED_WHITE_STAINED_GLASS);
            entries.addAfter(Items.WHITE_STAINED_GLASS_PANE, CHISELED_WHITE_STAINED_GLASS_PANE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(WHITE_GLOW_ITEM_FRAME, SHATTERED_GLASS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.LAPIS_BLOCK, POLISHED_LAPIS_BLOCK, POLISHED_LAPIS_STAIRS, POLISHED_LAPIS_SLAB);
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
    public static final Item OAK_SLAB = new SlabItem(Blocks.OAK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_OAK_SLAB, new CreoItemSettings());
    public static final Item SPRUCE_SLAB = new SlabItem(Blocks.SPRUCE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SPRUCE_SLAB, new CreoItemSettings());
    public static final Item BIRCH_SLAB = new SlabItem(Blocks.BIRCH_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BIRCH_SLAB, new CreoItemSettings());
    public static final Item JUNGLE_SLAB = new SlabItem(Blocks.JUNGLE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_JUNGLE_SLAB, new CreoItemSettings());
    public static final Item DARK_OAK_SLAB = new SlabItem(Blocks.DARK_OAK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DARK_OAK_SLAB, new CreoItemSettings());
    public static final Item ACACIA_SLAB = new SlabItem(Blocks.ACACIA_SLAB, ArchitectsAssemblyBlocks.VERTICAL_ACACIA_SLAB, new CreoItemSettings());
    public static final Item MANGROVE_SLAB = new SlabItem(Blocks.MANGROVE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MANGROVE_SLAB, new CreoItemSettings());
    public static final Item CHERRY_SLAB = new SlabItem(Blocks.CHERRY_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CHERRY_SLAB, new CreoItemSettings());
    public static final Item BAMBOO_SLAB = new SlabItem(Blocks.BAMBOO_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BAMBOO_SLAB, new CreoItemSettings());
    public static final Item BAMBOO_MOSAIC_SLAB = new SlabItem(Blocks.BAMBOO_MOSAIC_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BAMBOO_MOSAIC_SLAB, new CreoItemSettings());
    public static final Item CRIMSON_SLAB = new SlabItem(Blocks.CRIMSON_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CRIMSON_SLAB, new CreoItemSettings());
    public static final Item WARPED_SLAB = new SlabItem(Blocks.WARPED_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WARPED_SLAB, new CreoItemSettings());
    public static final Item STONE_SLAB = new SlabItem(Blocks.STONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_STONE_SLAB, new CreoItemSettings());
    public static final Item SMOOTH_STONE_SLAB = new SlabItem(Blocks.SMOOTH_STONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SMOOTH_STONE_SLAB, new CreoItemSettings());
    public static final Item SANDSTONE_SLAB = new SlabItem(Blocks.SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SANDSTONE_SLAB, new CreoItemSettings());
    public static final Item CUT_SANDSTONE_SLAB = new SlabItem(Blocks.CUT_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CUT_SANDSTONE_SLAB, new CreoItemSettings());
    public static final Item PETRIFIED_OAK_SLAB = new SlabItem(Blocks.PETRIFIED_OAK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PETRIFIED_OAK_SLAB, new CreoItemSettings());
    public static final Item COBBLESTONE_SLAB = new SlabItem(Blocks.COBBLESTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_COBBLESTONE_SLAB, new CreoItemSettings());
    public static final Item BRICK_SLAB = new SlabItem(Blocks.BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BRICK_SLAB, new CreoItemSettings());
    public static final Item STONE_BRICK_SLAB = new SlabItem(Blocks.STONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_STONE_BRICK_SLAB, new CreoItemSettings());
    public static final Item MUD_BRICK_SLAB = new SlabItem(Blocks.MUD_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MUD_BRICK_SLAB, new CreoItemSettings());
    public static final Item NETHER_BRICK_SLAB = new SlabItem(Blocks.NETHER_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_NETHER_BRICK_SLAB, new CreoItemSettings());
    public static final Item QUARTZ_SLAB = new SlabItem(Blocks.QUARTZ_SLAB, ArchitectsAssemblyBlocks.VERTICAL_QUARTZ_SLAB, new CreoItemSettings());
    public static final Item RED_SANDSTONE_SLAB = new SlabItem(Blocks.RED_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_RED_SANDSTONE_SLAB, new CreoItemSettings());
    public static final Item CUT_RED_SANDSTONE_SLAB = new SlabItem(Blocks.CUT_RED_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CUT_RED_SANDSTONE_SLAB, new CreoItemSettings());
    public static final Item PURPUR_SLAB = new SlabItem(Blocks.PURPUR_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PURPUR_SLAB, new CreoItemSettings());
    public static final Item PRISMARINE_SLAB = new SlabItem(Blocks.PRISMARINE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PRISMARINE_SLAB, new CreoItemSettings());
    public static final Item PRISMARINE_BRICK_SLAB = new SlabItem(Blocks.PRISMARINE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_PRISMARINE_BRICK_SLAB, new CreoItemSettings());
    public static final Item DARK_PRISMARINE_SLAB = new SlabItem(Blocks.DARK_PRISMARINE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DARK_PRISMARINE_SLAB, new CreoItemSettings());
    public static final Item GRANITE_SLAB = new SlabItem(Blocks.GRANITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_GRANITE_SLAB, new CreoItemSettings());
    public static final Item POLISHED_GRANITE_SLAB = new SlabItem(Blocks.POLISHED_GRANITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_GRANITE_SLAB, new CreoItemSettings());
    public static final Item ANDESITE_SLAB = new SlabItem(Blocks.ANDESITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_ANDESITE_SLAB, new CreoItemSettings());
    public static final Item POLISHED_ANDESITE_SLAB = new SlabItem(Blocks.POLISHED_ANDESITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_ANDESITE_SLAB, new CreoItemSettings());
    public static final Item DIORITE_SLAB = new SlabItem(Blocks.DIORITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DIORITE_SLAB, new CreoItemSettings());
    public static final Item POLISHED_DIORITE_SLAB = new SlabItem(Blocks.POLISHED_DIORITE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_DIORITE_SLAB, new CreoItemSettings());
    public static final Item SMOOTH_SANDSTONE_SLAB = new SlabItem(Blocks.SMOOTH_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SMOOTH_SANDSTONE_SLAB, new CreoItemSettings());
    public static final Item SMOOTH_QUARTZ_SLAB = new SlabItem(Blocks.SMOOTH_QUARTZ_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SMOOTH_QUARTZ_SLAB, new CreoItemSettings());
    public static final Item SMOOTH_RED_SANDSTONE_SLAB = new SlabItem(Blocks.SMOOTH_RED_SANDSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_SMOOTH_RED_SANDSTONE_SLAB, new CreoItemSettings());
    public static final Item MOSSY_STONE_BRICK_SLAB = new SlabItem(Blocks.MOSSY_STONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_STONE_BRICK_SLAB, new CreoItemSettings());
    public static final Item END_STONE_BRICK_SLAB = new SlabItem(Blocks.END_STONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_END_STONE_BRICK_SLAB, new CreoItemSettings());
    public static final Item RED_NETHER_BRICK_SLAB = new SlabItem(Blocks.RED_NETHER_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_RED_NETHER_BRICK_SLAB, new CreoItemSettings());
    public static final Item BLACKSTONE_SLAB = new SlabItem(Blocks.BLACKSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_BLACKSTONE_SLAB, new CreoItemSettings());
    public static final Item POLISHED_BLACKSTONE_SLAB = new SlabItem(Blocks.POLISHED_BLACKSTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_BLACKSTONE_SLAB, new CreoItemSettings());
    public static final Item POLISHED_BLACKSTONE_BRICK_SLAB = new SlabItem(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_BLACKSTONE_BRICK_SLAB, new CreoItemSettings());
    public static final Item OXIDIZED_CUT_COPPER_SLAB = new SlabItem(Blocks.OXIDIZED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_OXIDIZED_CUT_COPPER_SLAB, new CreoItemSettings());
    public static final Item WEATHERED_CUT_COPPER_SLAB = new SlabItem(Blocks.WEATHERED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WEATHERED_CUT_COPPER_SLAB, new CreoItemSettings());
    public static final Item EXPOSED_CUT_COPPER_SLAB = new SlabItem(Blocks.EXPOSED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_EXPOSED_CUT_COPPER_SLAB, new CreoItemSettings());
    public static final Item CUT_COPPER_SLAB = new SlabItem(Blocks.CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_CUT_COPPER_SLAB, new CreoItemSettings());
    public static final Item WAXED_OXIDIZED_CUT_COPPER_SLAB = new SlabItem(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WAXED_OXIDIZED_CUT_COPPER_SLAB, new CreoItemSettings());
    public static final Item WAXED_WEATHERED_CUT_COPPER_SLAB = new SlabItem(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WAXED_WEATHERED_CUT_COPPER_SLAB, new CreoItemSettings());
    public static final Item WAXED_EXPOSED_CUT_COPPER_SLAB = new SlabItem(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WAXED_EXPOSED_CUT_COPPER_SLAB, new CreoItemSettings());
    public static final Item WAXED_CUT_COPPER_SLAB = new SlabItem(Blocks.WAXED_CUT_COPPER_SLAB, ArchitectsAssemblyBlocks.VERTICAL_WAXED_CUT_COPPER_SLAB, new CreoItemSettings());
    public static final Item COBBLED_DEEPSLATE_SLAB = new SlabItem(Blocks.COBBLED_DEEPSLATE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_COBBLED_DEEPSLATE_SLAB, new CreoItemSettings());
    public static final Item POLISHED_DEEPSLATE_SLAB = new SlabItem(Blocks.POLISHED_DEEPSLATE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_POLISHED_DEEPSLATE_SLAB, CreoItemSettings.copyOf(Items.POLISHED_DEEPSLATE_SLAB));
    public static final Item DEEPSLATE_TILE_SLAB = new SlabItem(Blocks.DEEPSLATE_TILE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DEEPSLATE_TILE_SLAB, new CreoItemSettings());
    public static final Item DEEPSLATE_BRICK_SLAB = new SlabItem(Blocks.DEEPSLATE_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_DEEPSLATE_BRICK_SLAB, new CreoItemSettings());
    public static final Item MOSSY_COBBLESTONE_SLAB = new SlabItem(Blocks.MOSSY_COBBLESTONE_SLAB, ArchitectsAssemblyBlocks.VERTICAL_MOSSY_COBBLESTONE_SLAB, new CreoItemSettings());

    private static void registerVerticalSlabs() {
        final SimpleDefaultedRegistry<Item> items = (SimpleDefaultedRegistry<Item>) Registries.ITEM;

        items.set(items.getRawId(Items.OAK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("oak_slab")), OAK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.SPRUCE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("spruce_slab")), SPRUCE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.BIRCH_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("birch_slab")), BIRCH_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.JUNGLE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("jungle_slab")), JUNGLE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.DARK_OAK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("dark_oak_slab")), DARK_OAK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.ACACIA_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("acacia_slab")), ACACIA_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.MANGROVE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("mangrove_slab")), MANGROVE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.CHERRY_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("cherry_slab")), CHERRY_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.BAMBOO_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("bamboo_slab")), BAMBOO_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.BAMBOO_MOSAIC_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("bamboo_mosaic_slab")), BAMBOO_MOSAIC_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.CRIMSON_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("crimson_slab")), CRIMSON_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.WARPED_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("warped_slab")), WARPED_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.STONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("stone_slab")), STONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.SMOOTH_STONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("smooth_stone_slab")), SMOOTH_STONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.SANDSTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("sandstone_slab")), SANDSTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.CUT_SANDSTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("cut_sandstone_slab")), CUT_SANDSTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.PETRIFIED_OAK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("petrified_oak_slab")), PETRIFIED_OAK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.COBBLESTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("cobblestone_slab")), COBBLESTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("brick_slab")), BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.STONE_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("stone_brick_slab")), STONE_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.MUD_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("mud_brick_slab")), MUD_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.NETHER_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("nether_brick_slab")), NETHER_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.QUARTZ_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("quartz_slab")), QUARTZ_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.RED_SANDSTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("red_sandstone_slab")), RED_SANDSTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.CUT_RED_SANDSTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("cut_red_sandstone_slab")), CUT_RED_SANDSTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.PURPUR_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("purpur_slab")), PURPUR_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.PRISMARINE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("prismarine_slab")), PRISMARINE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.PRISMARINE_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("prismarine_brick_slab")), PRISMARINE_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.DARK_PRISMARINE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("dark_prismarine_slab")), DARK_PRISMARINE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.GRANITE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("granite_slab")), GRANITE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.POLISHED_GRANITE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("polished_granite_slab")), POLISHED_GRANITE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.ANDESITE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("andesite_slab")), ANDESITE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.POLISHED_ANDESITE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("polished_andesite_slab")), POLISHED_ANDESITE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.DIORITE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("diorite_slab")), DIORITE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.POLISHED_DIORITE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("polished_diorite_slab")), POLISHED_DIORITE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.SMOOTH_SANDSTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("smooth_sandstone_slab")), SMOOTH_SANDSTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.SMOOTH_QUARTZ_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("smooth_quartz_slab")), SMOOTH_QUARTZ_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.SMOOTH_RED_SANDSTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("smooth_red_sandstone_slab")), SMOOTH_RED_SANDSTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.MOSSY_STONE_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("mossy_stone_brick_slab")), MOSSY_STONE_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.END_STONE_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("end_stone_brick_slab")), END_STONE_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.RED_NETHER_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("red_nether_brick_slab")), RED_NETHER_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.BLACKSTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("blackstone_slab")), BLACKSTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.POLISHED_BLACKSTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("polished_blackstone_slab")), POLISHED_BLACKSTONE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.POLISHED_BLACKSTONE_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("polished_blackstone_brick_slab")), POLISHED_BLACKSTONE_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.OXIDIZED_CUT_COPPER_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("oxidized_cut_copper_slab")), OXIDIZED_CUT_COPPER_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.WEATHERED_CUT_COPPER_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("weathered_cut_copper_slab")), WEATHERED_CUT_COPPER_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.EXPOSED_CUT_COPPER_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("exposed_cut_copper_slab")), EXPOSED_CUT_COPPER_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.CUT_COPPER_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("cut_copper_slab")), CUT_COPPER_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("waxed_oxidized_cut_copper_slab")), WAXED_OXIDIZED_CUT_COPPER_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.WAXED_WEATHERED_CUT_COPPER_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("waxed_weathered_cut_copper_slab")), WAXED_WEATHERED_CUT_COPPER_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.WAXED_EXPOSED_CUT_COPPER_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("waxed_exposed_cut_copper_slab")), WAXED_EXPOSED_CUT_COPPER_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.WAXED_CUT_COPPER_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("waxed_cut_copper_slab")), WAXED_CUT_COPPER_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.COBBLED_DEEPSLATE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("cobbled_deepslate_slab")), COBBLED_DEEPSLATE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.POLISHED_DEEPSLATE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("polished_deepslate_slab")), POLISHED_DEEPSLATE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.DEEPSLATE_TILE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("deepslate_tile_slab")), DEEPSLATE_TILE_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.DEEPSLATE_BRICK_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("deepslate_brick_slab")), DEEPSLATE_BRICK_SLAB, Lifecycle.stable());
        items.set(items.getRawId(Items.MOSSY_COBBLESTONE_SLAB), RegistryKey.of(RegistryKeys.ITEM, new Identifier("mossy_cobblestone_slab")), MOSSY_COBBLESTONE_SLAB, Lifecycle.stable());

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            replaceVanillaSlabs(entries.getDisplayStacks());
            replaceVanillaSlabs(entries.getSearchTabStacks());
        });
    }

    private static final Map<Item, Item> SLABS = new ImmutableMap.Builder<Item, Item>()
            .put(Items.OAK_SLAB, OAK_SLAB)
            .put(Items.SPRUCE_SLAB, SPRUCE_SLAB)
            .put(Items.BIRCH_SLAB, BIRCH_SLAB)
            .put(Items.JUNGLE_SLAB, JUNGLE_SLAB)
            .put(Items.ACACIA_SLAB, ACACIA_SLAB)
            .put(Items.DARK_OAK_SLAB, DARK_OAK_SLAB)
            .put(Items.MANGROVE_SLAB, MANGROVE_SLAB)
            .put(Items.CHERRY_SLAB, CHERRY_SLAB)
            .put(Items.BAMBOO_SLAB, BAMBOO_SLAB)
            .put(Items.BAMBOO_MOSAIC_SLAB, BAMBOO_MOSAIC_SLAB)
            .put(Items.CRIMSON_SLAB, CRIMSON_SLAB)
            .put(Items.WARPED_SLAB, WARPED_SLAB)
            .put(Items.STONE_SLAB, STONE_SLAB)
            .put(Items.SMOOTH_STONE_SLAB, SMOOTH_STONE_SLAB)
            .put(Items.SANDSTONE_SLAB, SANDSTONE_SLAB)
            .put(Items.CUT_SANDSTONE_SLAB, CUT_SANDSTONE_SLAB)
            .put(Items.RED_SANDSTONE_SLAB, RED_SANDSTONE_SLAB)
            .put(Items.CUT_RED_SANDSTONE_SLAB, CUT_RED_SANDSTONE_SLAB)
            .put(Items.PETRIFIED_OAK_SLAB, PETRIFIED_OAK_SLAB)
            .put(Items.STONE_BRICK_SLAB, STONE_BRICK_SLAB)
            .put(Items.BRICK_SLAB, BRICK_SLAB)
            .put(Items.MUD_BRICK_SLAB, MUD_BRICK_SLAB)
            .put(Items.NETHER_BRICK_SLAB, NETHER_BRICK_SLAB)
            .put(Items.RED_NETHER_BRICK_SLAB, RED_NETHER_BRICK_SLAB)
            .put(Items.END_STONE_BRICK_SLAB, END_STONE_BRICK_SLAB)
            .put(Items.QUARTZ_SLAB, QUARTZ_SLAB)
            .put(Items.PURPUR_SLAB, PURPUR_SLAB)
            .put(Items.PRISMARINE_SLAB, PRISMARINE_SLAB)
            .put(Items.PRISMARINE_BRICK_SLAB, PRISMARINE_BRICK_SLAB)
            .put(Items.DARK_PRISMARINE_SLAB, DARK_PRISMARINE_SLAB)
            .put(Items.GRANITE_SLAB, GRANITE_SLAB)
            .put(Items.POLISHED_GRANITE_SLAB, POLISHED_GRANITE_SLAB)
            .put(Items.DIORITE_SLAB, DIORITE_SLAB)
            .put(Items.POLISHED_DIORITE_SLAB, POLISHED_DIORITE_SLAB)
            .put(Items.ANDESITE_SLAB, ANDESITE_SLAB)
            .put(Items.POLISHED_ANDESITE_SLAB, POLISHED_ANDESITE_SLAB)
            .put(Items.SMOOTH_SANDSTONE_SLAB, SMOOTH_SANDSTONE_SLAB)
            .put(Items.SMOOTH_RED_SANDSTONE_SLAB, SMOOTH_RED_SANDSTONE_SLAB)
            .put(Items.SMOOTH_QUARTZ_SLAB, SMOOTH_QUARTZ_SLAB)
            .put(Items.MOSSY_STONE_BRICK_SLAB, MOSSY_STONE_BRICK_SLAB)
            .put(Items.MOSSY_COBBLESTONE_SLAB, MOSSY_COBBLESTONE_SLAB)
            .put(Items.COBBLESTONE_SLAB, COBBLESTONE_SLAB)
            .put(Items.BLACKSTONE_SLAB, BLACKSTONE_SLAB)
            .put(Items.POLISHED_BLACKSTONE_SLAB, POLISHED_BLACKSTONE_SLAB)
            .put(Items.POLISHED_BLACKSTONE_BRICK_SLAB, POLISHED_BLACKSTONE_BRICK_SLAB)
            .put(Items.COBBLED_DEEPSLATE_SLAB, COBBLED_DEEPSLATE_SLAB)
            .put(Items.POLISHED_DEEPSLATE_SLAB, POLISHED_DEEPSLATE_SLAB)
            .put(Items.DEEPSLATE_BRICK_SLAB, DEEPSLATE_BRICK_SLAB)
            .put(Items.DEEPSLATE_TILE_SLAB, DEEPSLATE_TILE_SLAB)
            .put(Items.CUT_COPPER_SLAB, CUT_COPPER_SLAB)
            .put(Items.EXPOSED_CUT_COPPER_SLAB, EXPOSED_CUT_COPPER_SLAB)
            .put(Items.WEATHERED_CUT_COPPER_SLAB, WEATHERED_CUT_COPPER_SLAB)
            .put(Items.OXIDIZED_CUT_COPPER_SLAB, OXIDIZED_CUT_COPPER_SLAB)
            .put(Items.WAXED_CUT_COPPER_SLAB, WAXED_CUT_COPPER_SLAB)
            .put(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, WAXED_EXPOSED_CUT_COPPER_SLAB)
            .put(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, WAXED_WEATHERED_CUT_COPPER_SLAB)
            .put(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, WAXED_OXIDIZED_CUT_COPPER_SLAB)
            .build();

    private static void replaceVanillaSlabs(List<ItemStack> stacks) {
        stacks.replaceAll(stack -> {
            Item item = stack.getItem();
            if (SLABS.containsKey(item))
                return SLABS.get(item).getDefaultStack();
            return stack;
        });
    }
    // endregion

    // region Missing Blocks
    public static final Item QUARTZ_BRICK_STAIRS = new BlockItem(ArchitectsAssemblyBlocks.QUARTZ_BRICK_STAIRS, new CreoItemSettings());
    public static final Item QUARTZ_BRICK_SLAB = new SlabItem(ArchitectsAssemblyBlocks.QUARTZ_BRICK_SLAB, ArchitectsAssemblyBlocks.VERTICAL_QUARTZ_BRICK_SLAB, new CreoItemSettings());
    public static final Item QUARTZ_BRICK_WALL = new BlockItem(ArchitectsAssemblyBlocks.QUARTZ_BRICK_WALL, new CreoItemSettings());
    public static final Item PRISMARINE_BRICK_WALL = new BlockItem(ArchitectsAssemblyBlocks.PRISMARINE_BRICK_WALL, new CreoItemSettings());
    public static final Item DARK_PRISMARINE_WALL = new BlockItem(ArchitectsAssemblyBlocks.DARK_PRISMARINE_WALL, new CreoItemSettings());
    public static final Item SMOOTH_SANDSTONE_WALL = new BlockItem(ArchitectsAssemblyBlocks.SMOOTH_SANDSTONE_WALL, new CreoItemSettings());
    public static final Item SMOOTH_RED_SANDSTONE_WALL = new BlockItem(ArchitectsAssemblyBlocks.SMOOTH_RED_SANDSTONE_WALL, new CreoItemSettings());
    public static final Item POLISHED_GRANITE_WALL = new BlockItem(ArchitectsAssemblyBlocks.POLISHED_GRANITE_WALL, new CreoItemSettings());
    public static final Item POLISHED_ANDESITE_WALL = new BlockItem(ArchitectsAssemblyBlocks.POLISHED_ANDESITE_WALL, new CreoItemSettings());
    public static final Item POLISHED_DIORITE_WALL = new BlockItem(ArchitectsAssemblyBlocks.POLISHED_DIORITE_WALL, new CreoItemSettings());
    public static final Item PURPUR_WALL = new BlockItem(ArchitectsAssemblyBlocks.PURPUR_WALL, new CreoItemSettings());
    public static final Item CRACKED_MUD_BRICKS = new BlockItem(ArchitectsAssemblyBlocks.CRACKED_MUD_BRICKS, new CreoItemSettings());
    public static final Item CRACKED_QUARTZ_BRICKS = new BlockItem(ArchitectsAssemblyBlocks.CRACKED_QUARTZ_BRICKS, new CreoItemSettings());
    public static final Item CRACKED_RED_NETHER_BRICKS = new BlockItem(ArchitectsAssemblyBlocks.CRACKED_RED_NETHER_BRICKS, new CreoItemSettings());
    public static final Item CRACKED_END_STONE_BRICKS = new BlockItem(ArchitectsAssemblyBlocks.CRACKED_END_STONE_BRICKS, new CreoItemSettings());

    private static void registerMissingBlocks() {
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "quartz_brick_stairs"), QUARTZ_BRICK_STAIRS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "quartz_brick_slab"), QUARTZ_BRICK_SLAB);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "quartz_brick_wall"), QUARTZ_BRICK_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "prismarine_brick_wall"), PRISMARINE_BRICK_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "dark_prismarine_wall"), DARK_PRISMARINE_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "smooth_sandstone_wall"), SMOOTH_SANDSTONE_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "smooth_red_sandstone_wall"), SMOOTH_RED_SANDSTONE_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "polished_granite_wall"), POLISHED_GRANITE_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "polished_andesite_wall"), POLISHED_ANDESITE_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "polished_diorite_wall"), POLISHED_DIORITE_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "purpur_wall"), PURPUR_WALL);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cracked_mud_bricks"), CRACKED_MUD_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cracked_quartz_bricks"), CRACKED_QUARTZ_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cracked_red_nether_bricks"), CRACKED_RED_NETHER_BRICKS);
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "cracked_end_stone_bricks"), CRACKED_END_STONE_BRICKS);

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
    public static final Item TORCH = new BlockItem(ArchitectsAssemblyBlocks.TORCH, new CreoItemSettings());
    public static final Item SOUL_TORCH = new BlockItem(ArchitectsAssemblyBlocks.SOUL_TORCH, new CreoItemSettings());
    public static final Item REDSTONE_LAMP = new BlockItem(ArchitectsAssemblyBlocks.REDSTONE_LAMP, new CreoItemSettings());

    private static void registerImprovedBlocks() {
        final SimpleDefaultedRegistry<Item> items = (SimpleDefaultedRegistry<Item>) Registries.ITEM;

        items.set(items.getRawId(Items.TORCH), RegistryKey.of(RegistryKeys.ITEM, new Identifier("torch")), TORCH, Lifecycle.stable());
        items.set(items.getRawId(Items.SOUL_TORCH), RegistryKey.of(RegistryKeys.ITEM, new Identifier("soul_torch")), SOUL_TORCH, Lifecycle.stable());
        items.set(items.getRawId(Items.REDSTONE_LAMP), RegistryKey.of(RegistryKeys.ITEM, new Identifier("redstone_lamp")), REDSTONE_LAMP, Lifecycle.stable());

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
            if (stack.isOf(Items.REDSTONE_LAMP))
                return REDSTONE_LAMP.getDefaultStack();
            return stack;
        });
    }
    // endregion

    // region Misc Blocks
    public static final Item SAWMILL = new BlockItem(ArchitectsAssemblyBlocks.SAWMILL, new CreoItemSettings());

    private static void registerMiscBlocks() {
        Registry.register(Registries.ITEM, new Identifier(ArchitectsAssembly.NAMESPACE, "sawmill"), SAWMILL);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.STONECUTTER, SAWMILL);
        });
    }
    // endregion
}
