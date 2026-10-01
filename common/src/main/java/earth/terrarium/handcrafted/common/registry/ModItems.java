package earth.terrarium.handcrafted.common.registry;

import com.teamresourceful.resourcefullib.common.item.tabs.ResourcefulCreativeModeTab;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import com.teamresourceful.resourcefullib.common.registry.builtin.ResourcefulItemRegistry;
import earth.terrarium.handcrafted.Handcrafted;
import earth.terrarium.handcrafted.common.constants.ConstantComponents;
import earth.terrarium.handcrafted.common.items.CustomPaintingItem;
import earth.terrarium.handcrafted.common.items.HammerItem;
import earth.terrarium.handcrafted.common.items.TooltipBlockItem;
import earth.terrarium.handcrafted.common.items.TooltipItem;
import earth.terrarium.handcrafted.common.tags.ModPaintingVariantTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;


@SuppressWarnings("unused")
public class ModItems {
    public static final ResourcefulItemRegistry ITEMS = ResourcefulRegistries.createForItems(Handcrafted.MOD_ID);
    public static final ResourcefulRegistry<CreativeModeTab> TABS = ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, Handcrafted.MOD_ID);
    public static final RegistryEntry<CreativeModeTab> TAB = TABS.register("main", () -> new ResourcefulCreativeModeTab(Identifier.fromNamespaceAndPath(Handcrafted.MOD_ID, "main"))
        .setItemIcon(() -> ModItems.OAK_BENCH.get())
        .addRegistry(ITEMS)
        .build());

    public static final ResourcefulItemRegistry CUSHIONS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry SHEETS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry BENCHES = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry WOODEN_BENCHES = ResourcefulRegistries.createForItems(BENCHES);
    public static final ResourcefulItemRegistry METAL_BENCHES = ResourcefulRegistries.createForItems(BENCHES);
    public static final ResourcefulItemRegistry COUCHES = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry CHAIRS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry DINING_BENCHES = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry SIDE_TABLES = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry DESKS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry NIGHTSTANDS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry TABLES = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry FANCY_BEDS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry COUNTERS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry CUPBOARDS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry DRAWERS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry SHELVES = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry TRIMS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry PILLAR_TRIMS = ResourcefulRegistries.createForItems(TRIMS);
    public static final ResourcefulItemRegistry CORNER_TRIMS = ResourcefulRegistries.createForItems(TRIMS);
    public static final ResourcefulItemRegistry POTS = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry TROPHIES = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry CROCKERY = ResourcefulRegistries.createForItems(ITEMS);
    public static final ResourcefulItemRegistry CUPS = ResourcefulRegistries.createForItems(CROCKERY);
    public static final ResourcefulItemRegistry PLATES = ResourcefulRegistries.createForItems(CROCKERY);
    public static final ResourcefulItemRegistry BOWLS = ResourcefulRegistries.createForItems(CROCKERY);
    public static final ResourcefulItemRegistry CROCKERY_COMBOS = ResourcefulRegistries.createForItems(CROCKERY);

    public static final RegistryEntry<Item> HAMMER = ITEMS.register("hammer", HammerItem::new, () -> new Item.Properties().stacksTo(1));
    public static final RegistryEntry<Item> FANCY_PAINTING = ITEMS.register("fancy_painting", p -> new CustomPaintingItem(p, ModPaintingVariantTags.PAINTINGS), Item.Properties::new);

    public static final RegistryEntry<Item> OVEN = ITEMS.register("oven", p -> new TooltipBlockItem(ModBlocks.OVEN.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> KITCHEN_HOOD = ITEMS.register("kitchen_hood", p -> new TooltipBlockItem(ModBlocks.KITCHEN_HOOD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> KITCHEN_HOOD_PIPE = ITEMS.register("kitchen_hood_pipe", p -> new TooltipBlockItem(ModBlocks.KITCHEN_HOOD_PIPE.get(), ConstantComponents.HAMMER_USE_SHAPE, p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BERRY_JAM_JAR = ITEMS.register("berry_jam_jar", p -> new TooltipBlockItem(ModBlocks.BERRY_JAM_JAR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> STACKABLE_BOOK = ITEMS.register("stackable_book", p -> new TooltipBlockItem(ModBlocks.STACKABLE_BOOK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> BLACK_CUSHION = CUSHIONS.register("black_cushion", p -> new TooltipBlockItem(ModBlocks.BLACK_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_CUSHION = CUSHIONS.register("blue_cushion", p -> new TooltipBlockItem(ModBlocks.BLUE_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BROWN_CUSHION = CUSHIONS.register("brown_cushion", p -> new TooltipBlockItem(ModBlocks.BROWN_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CYAN_CUSHION = CUSHIONS.register("cyan_cushion", p -> new TooltipBlockItem(ModBlocks.CYAN_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GRAY_CUSHION = CUSHIONS.register("gray_cushion", p -> new TooltipBlockItem(ModBlocks.GRAY_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GREEN_CUSHION = CUSHIONS.register("green_cushion", p -> new TooltipBlockItem(ModBlocks.GREEN_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> LIGHT_BLUE_CUSHION = CUSHIONS.register("light_blue_cushion", p -> new TooltipBlockItem(ModBlocks.LIGHT_BLUE_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> LIGHT_GRAY_CUSHION = CUSHIONS.register("light_gray_cushion", p -> new TooltipBlockItem(ModBlocks.LIGHT_GRAY_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> LIME_CUSHION = CUSHIONS.register("lime_cushion", p -> new TooltipBlockItem(ModBlocks.LIME_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MAGENTA_CUSHION = CUSHIONS.register("magenta_cushion", p -> new TooltipBlockItem(ModBlocks.MAGENTA_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> ORANGE_CUSHION = CUSHIONS.register("orange_cushion", p -> new TooltipBlockItem(ModBlocks.ORANGE_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> PINK_CUSHION = CUSHIONS.register("pink_cushion", p -> new TooltipBlockItem(ModBlocks.PINK_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> PURPLE_CUSHION = CUSHIONS.register("purple_cushion", p -> new TooltipBlockItem(ModBlocks.PURPLE_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> RED_CUSHION = CUSHIONS.register("red_cushion", p -> new TooltipBlockItem(ModBlocks.RED_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WHITE_CUSHION = CUSHIONS.register("white_cushion", p -> new TooltipBlockItem(ModBlocks.WHITE_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> YELLOW_CUSHION = CUSHIONS.register("yellow_cushion", p -> new TooltipBlockItem(ModBlocks.YELLOW_CUSHION.get(), p.useItemDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> BLACK_SHEET = SHEETS.register("black_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_SHEET = SHEETS.register("blue_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> BROWN_SHEET = SHEETS.register("brown_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> CYAN_SHEET = SHEETS.register("cyan_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> GRAY_SHEET = SHEETS.register("gray_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> GREEN_SHEET = SHEETS.register("green_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> LIGHT_BLUE_SHEET = SHEETS.register("light_blue_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> LIGHT_GRAY_SHEET = SHEETS.register("light_gray_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> LIME_SHEET = SHEETS.register("lime_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> MAGENTA_SHEET = SHEETS.register("magenta_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> ORANGE_SHEET = SHEETS.register("orange_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> PINK_SHEET = SHEETS.register("pink_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> PURPLE_SHEET = SHEETS.register("purple_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> RED_SHEET = SHEETS.register("red_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> WHITE_SHEET = SHEETS.register("white_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);
    public static final RegistryEntry<Item> YELLOW_SHEET = SHEETS.register("yellow_sheet", p -> new TooltipItem(ConstantComponents.PLACE_ON_FURNITURE, p), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_BENCH = WOODEN_BENCHES.register("acacia_bench", p -> new TooltipBlockItem(ModBlocks.ACACIA_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_BENCH = WOODEN_BENCHES.register("bamboo_bench", p -> new TooltipBlockItem(ModBlocks.BAMBOO_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_BENCH = WOODEN_BENCHES.register("birch_bench", p -> new TooltipBlockItem(ModBlocks.BIRCH_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_BENCH = WOODEN_BENCHES.register("cherry_bench", p -> new TooltipBlockItem(ModBlocks.CHERRY_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_BENCH = WOODEN_BENCHES.register("crimson_bench", p -> new TooltipBlockItem(ModBlocks.CRIMSON_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_BENCH = WOODEN_BENCHES.register("dark_oak_bench", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_BENCH = WOODEN_BENCHES.register("jungle_bench", p -> new TooltipBlockItem(ModBlocks.JUNGLE_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_BENCH = WOODEN_BENCHES.register("mangrove_bench", p -> new TooltipBlockItem(ModBlocks.MANGROVE_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_BENCH = WOODEN_BENCHES.register("oak_bench", p -> new TooltipBlockItem(ModBlocks.OAK_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_BENCH = WOODEN_BENCHES.register("spruce_bench", p -> new TooltipBlockItem(ModBlocks.SPRUCE_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_BENCH = WOODEN_BENCHES.register("warped_bench", p -> new TooltipBlockItem(ModBlocks.WARPED_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> BENCH = METAL_BENCHES.register("bench", p -> new TooltipBlockItem(ModBlocks.BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> FROZEN_BENCH = METAL_BENCHES.register("frozen_bench", p -> new TooltipBlockItem(ModBlocks.FROZEN_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_COUCH = COUCHES.register("acacia_couch", p -> new TooltipBlockItem(ModBlocks.ACACIA_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_COUCH = COUCHES.register("bamboo_couch", p -> new TooltipBlockItem(ModBlocks.BAMBOO_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_COUCH = COUCHES.register("birch_couch", p -> new TooltipBlockItem(ModBlocks.BIRCH_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_COUCH = COUCHES.register("cherry_couch", p -> new TooltipBlockItem(ModBlocks.CHERRY_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_COUCH = COUCHES.register("crimson_couch", p -> new TooltipBlockItem(ModBlocks.CRIMSON_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_COUCH = COUCHES.register("dark_oak_couch", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_COUCH = COUCHES.register("jungle_couch", p -> new TooltipBlockItem(ModBlocks.JUNGLE_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_COUCH = COUCHES.register("mangrove_couch", p -> new TooltipBlockItem(ModBlocks.MANGROVE_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_COUCH = COUCHES.register("oak_couch", p -> new TooltipBlockItem(ModBlocks.OAK_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_COUCH = COUCHES.register("spruce_couch", p -> new TooltipBlockItem(ModBlocks.SPRUCE_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_COUCH = COUCHES.register("warped_couch", p -> new TooltipBlockItem(ModBlocks.WARPED_COUCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_CHAIR = CHAIRS.register("acacia_chair", p -> new TooltipBlockItem(ModBlocks.ACACIA_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_CHAIR = CHAIRS.register("bamboo_chair", p -> new TooltipBlockItem(ModBlocks.BAMBOO_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_CHAIR = CHAIRS.register("birch_chair", p -> new TooltipBlockItem(ModBlocks.BIRCH_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_CHAIR = CHAIRS.register("cherry_chair", p -> new TooltipBlockItem(ModBlocks.CHERRY_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_CHAIR = CHAIRS.register("crimson_chair", p -> new TooltipBlockItem(ModBlocks.CRIMSON_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_CHAIR = CHAIRS.register("dark_oak_chair", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_CHAIR = CHAIRS.register("jungle_chair", p -> new TooltipBlockItem(ModBlocks.JUNGLE_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_CHAIR = CHAIRS.register("mangrove_chair", p -> new TooltipBlockItem(ModBlocks.MANGROVE_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_CHAIR = CHAIRS.register("oak_chair", p -> new TooltipBlockItem(ModBlocks.OAK_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_CHAIR = CHAIRS.register("spruce_chair", p -> new TooltipBlockItem(ModBlocks.SPRUCE_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_CHAIR = CHAIRS.register("warped_chair", p -> new TooltipBlockItem(ModBlocks.WARPED_CHAIR.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_DINING_BENCH = DINING_BENCHES.register("acacia_dining_bench", p -> new TooltipBlockItem(ModBlocks.ACACIA_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_DINING_BENCH = DINING_BENCHES.register("bamboo_dining_bench", p -> new TooltipBlockItem(ModBlocks.BAMBOO_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_DINING_BENCH = DINING_BENCHES.register("birch_dining_bench", p -> new TooltipBlockItem(ModBlocks.BIRCH_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_DINING_BENCH = DINING_BENCHES.register("cherry_dining_bench", p -> new TooltipBlockItem(ModBlocks.CHERRY_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_DINING_BENCH = DINING_BENCHES.register("crimson_dining_bench", p -> new TooltipBlockItem(ModBlocks.CRIMSON_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_DINING_BENCH = DINING_BENCHES.register("dark_oak_dining_bench", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_DINING_BENCH = DINING_BENCHES.register("jungle_dining_bench", p -> new TooltipBlockItem(ModBlocks.JUNGLE_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_DINING_BENCH = DINING_BENCHES.register("mangrove_dining_bench", p -> new TooltipBlockItem(ModBlocks.MANGROVE_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_DINING_BENCH = DINING_BENCHES.register("oak_dining_bench", p -> new TooltipBlockItem(ModBlocks.OAK_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_DINING_BENCH = DINING_BENCHES.register("spruce_dining_bench", p -> new TooltipBlockItem(ModBlocks.SPRUCE_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_DINING_BENCH = DINING_BENCHES.register("warped_dining_bench", p -> new TooltipBlockItem(ModBlocks.WARPED_DINING_BENCH.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_SIDE_TABLE = SIDE_TABLES.register("acacia_side_table", p -> new TooltipBlockItem(ModBlocks.ACACIA_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_SIDE_TABLE = SIDE_TABLES.register("birch_side_table", p -> new TooltipBlockItem(ModBlocks.BIRCH_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_SIDE_TABLE = SIDE_TABLES.register("dark_oak_side_table", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_SIDE_TABLE = SIDE_TABLES.register("jungle_side_table", p -> new TooltipBlockItem(ModBlocks.JUNGLE_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_SIDE_TABLE = SIDE_TABLES.register("mangrove_side_table", p -> new TooltipBlockItem(ModBlocks.MANGROVE_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_SIDE_TABLE = SIDE_TABLES.register("oak_side_table", p -> new TooltipBlockItem(ModBlocks.OAK_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_SIDE_TABLE = SIDE_TABLES.register("spruce_side_table", p -> new TooltipBlockItem(ModBlocks.SPRUCE_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_SIDE_TABLE = SIDE_TABLES.register("crimson_side_table", p -> new TooltipBlockItem(ModBlocks.CRIMSON_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_SIDE_TABLE = SIDE_TABLES.register("warped_side_table", p -> new TooltipBlockItem(ModBlocks.WARPED_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_SIDE_TABLE = SIDE_TABLES.register("cherry_side_table", p -> new TooltipBlockItem(ModBlocks.CHERRY_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_SIDE_TABLE = SIDE_TABLES.register("bamboo_side_table", p -> new TooltipBlockItem(ModBlocks.BAMBOO_SIDE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_DESK = DESKS.register("acacia_desk", p -> new TooltipBlockItem(ModBlocks.ACACIA_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_DESK = DESKS.register("bamboo_desk", p -> new TooltipBlockItem(ModBlocks.BAMBOO_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_DESK = DESKS.register("birch_desk", p -> new TooltipBlockItem(ModBlocks.BIRCH_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_DESK = DESKS.register("cherry_desk", p -> new TooltipBlockItem(ModBlocks.CHERRY_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_DESK = DESKS.register("crimson_desk", p -> new TooltipBlockItem(ModBlocks.CRIMSON_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_DESK = DESKS.register("dark_oak_desk", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_DESK = DESKS.register("jungle_desk", p -> new TooltipBlockItem(ModBlocks.JUNGLE_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_DESK = DESKS.register("mangrove_desk", p -> new TooltipBlockItem(ModBlocks.MANGROVE_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_DESK = DESKS.register("oak_desk", p -> new TooltipBlockItem(ModBlocks.OAK_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_DESK = DESKS.register("spruce_desk", p -> new TooltipBlockItem(ModBlocks.SPRUCE_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_DESK = DESKS.register("warped_desk", p -> new TooltipBlockItem(ModBlocks.WARPED_DESK.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_NIGHTSTAND = NIGHTSTANDS.register("acacia_nightstand", p -> new TooltipBlockItem(ModBlocks.ACACIA_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_NIGHTSTAND = NIGHTSTANDS.register("bamboo_nightstand", p -> new TooltipBlockItem(ModBlocks.BAMBOO_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_NIGHTSTAND = NIGHTSTANDS.register("birch_nightstand", p -> new TooltipBlockItem(ModBlocks.BIRCH_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_NIGHTSTAND = NIGHTSTANDS.register("cherry_nightstand", p -> new TooltipBlockItem(ModBlocks.CHERRY_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_NIGHTSTAND = NIGHTSTANDS.register("crimson_nightstand", p -> new TooltipBlockItem(ModBlocks.CRIMSON_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_NIGHTSTAND = NIGHTSTANDS.register("dark_oak_nightstand", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_NIGHTSTAND = NIGHTSTANDS.register("jungle_nightstand", p -> new TooltipBlockItem(ModBlocks.JUNGLE_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_NIGHTSTAND = NIGHTSTANDS.register("mangrove_nightstand", p -> new TooltipBlockItem(ModBlocks.MANGROVE_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_NIGHTSTAND = NIGHTSTANDS.register("oak_nightstand", p -> new TooltipBlockItem(ModBlocks.OAK_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_NIGHTSTAND = NIGHTSTANDS.register("spruce_nightstand", p -> new TooltipBlockItem(ModBlocks.SPRUCE_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_NIGHTSTAND = NIGHTSTANDS.register("warped_nightstand", p -> new TooltipBlockItem(ModBlocks.WARPED_NIGHTSTAND.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_TABLE = TABLES.register("acacia_table", p -> new TooltipBlockItem(ModBlocks.ACACIA_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_TABLE = TABLES.register("bamboo_table", p -> new TooltipBlockItem(ModBlocks.BAMBOO_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_TABLE = TABLES.register("birch_table", p -> new TooltipBlockItem(ModBlocks.BIRCH_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_TABLE = TABLES.register("cherry_table", p -> new TooltipBlockItem(ModBlocks.CHERRY_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_TABLE = TABLES.register("crimson_table", p -> new TooltipBlockItem(ModBlocks.CRIMSON_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_TABLE = TABLES.register("dark_oak_table", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_TABLE = TABLES.register("jungle_table", p -> new TooltipBlockItem(ModBlocks.JUNGLE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_TABLE = TABLES.register("mangrove_table", p -> new TooltipBlockItem(ModBlocks.MANGROVE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_TABLE = TABLES.register("oak_table", p -> new TooltipBlockItem(ModBlocks.OAK_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_TABLE = TABLES.register("spruce_table", p -> new TooltipBlockItem(ModBlocks.SPRUCE_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_TABLE = TABLES.register("warped_table", p -> new TooltipBlockItem(ModBlocks.WARPED_TABLE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_FANCY_BED = FANCY_BEDS.register("acacia_fancy_bed", p -> new TooltipBlockItem(ModBlocks.ACACIA_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_FANCY_BED = FANCY_BEDS.register("bamboo_fancy_bed", p -> new TooltipBlockItem(ModBlocks.BAMBOO_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_FANCY_BED = FANCY_BEDS.register("birch_fancy_bed", p -> new TooltipBlockItem(ModBlocks.BIRCH_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_FANCY_BED = FANCY_BEDS.register("cherry_fancy_bed", p -> new TooltipBlockItem(ModBlocks.CHERRY_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_FANCY_BED = FANCY_BEDS.register("crimson_fancy_bed", p -> new TooltipBlockItem(ModBlocks.CRIMSON_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_FANCY_BED = FANCY_BEDS.register("dark_oak_fancy_bed", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_FANCY_BED = FANCY_BEDS.register("jungle_fancy_bed", p -> new TooltipBlockItem(ModBlocks.JUNGLE_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_FANCY_BED = FANCY_BEDS.register("mangrove_fancy_bed", p -> new TooltipBlockItem(ModBlocks.MANGROVE_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_FANCY_BED = FANCY_BEDS.register("oak_fancy_bed", p -> new TooltipBlockItem(ModBlocks.OAK_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_FANCY_BED = FANCY_BEDS.register("spruce_fancy_bed", p -> new TooltipBlockItem(ModBlocks.SPRUCE_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_FANCY_BED = FANCY_BEDS.register("warped_fancy_bed", p -> new TooltipBlockItem(ModBlocks.WARPED_FANCY_BED.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_COUNTER = COUNTERS.register("acacia_counter", p -> new TooltipBlockItem(ModBlocks.ACACIA_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_COUNTER = COUNTERS.register("bamboo_counter", p -> new TooltipBlockItem(ModBlocks.BAMBOO_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_COUNTER = COUNTERS.register("birch_counter", p -> new TooltipBlockItem(ModBlocks.BIRCH_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_COUNTER = COUNTERS.register("cherry_counter", p -> new TooltipBlockItem(ModBlocks.CHERRY_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_COUNTER = COUNTERS.register("crimson_counter", p -> new TooltipBlockItem(ModBlocks.CRIMSON_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_COUNTER = COUNTERS.register("dark_oak_counter", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_COUNTER = COUNTERS.register("jungle_counter", p -> new TooltipBlockItem(ModBlocks.JUNGLE_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_COUNTER = COUNTERS.register("mangrove_counter", p -> new TooltipBlockItem(ModBlocks.MANGROVE_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_COUNTER = COUNTERS.register("oak_counter", p -> new TooltipBlockItem(ModBlocks.OAK_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_COUNTER = COUNTERS.register("spruce_counter", p -> new TooltipBlockItem(ModBlocks.SPRUCE_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_COUNTER = COUNTERS.register("warped_counter", p -> new TooltipBlockItem(ModBlocks.WARPED_COUNTER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_CUPBOARD = CUPBOARDS.register("acacia_cupboard", p -> new TooltipBlockItem(ModBlocks.ACACIA_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_CUPBOARD = CUPBOARDS.register("bamboo_cupboard", p -> new TooltipBlockItem(ModBlocks.BAMBOO_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_CUPBOARD = CUPBOARDS.register("birch_cupboard", p -> new TooltipBlockItem(ModBlocks.BIRCH_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_CUPBOARD = CUPBOARDS.register("cherry_cupboard", p -> new TooltipBlockItem(ModBlocks.CHERRY_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_CUPBOARD = CUPBOARDS.register("crimson_cupboard", p -> new TooltipBlockItem(ModBlocks.CRIMSON_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_CUPBOARD = CUPBOARDS.register("dark_oak_cupboard", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_CUPBOARD = CUPBOARDS.register("jungle_cupboard", p -> new TooltipBlockItem(ModBlocks.JUNGLE_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_CUPBOARD = CUPBOARDS.register("mangrove_cupboard", p -> new TooltipBlockItem(ModBlocks.MANGROVE_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_CUPBOARD = CUPBOARDS.register("oak_cupboard", p -> new TooltipBlockItem(ModBlocks.OAK_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_CUPBOARD = CUPBOARDS.register("spruce_cupboard", p -> new TooltipBlockItem(ModBlocks.SPRUCE_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_CUPBOARD = CUPBOARDS.register("warped_cupboard", p -> new TooltipBlockItem(ModBlocks.WARPED_CUPBOARD.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_DRAWER = DRAWERS.register("acacia_drawer", p -> new TooltipBlockItem(ModBlocks.ACACIA_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_DRAWER = DRAWERS.register("bamboo_drawer", p -> new TooltipBlockItem(ModBlocks.BAMBOO_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_DRAWER = DRAWERS.register("birch_drawer", p -> new TooltipBlockItem(ModBlocks.BIRCH_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_DRAWER = DRAWERS.register("cherry_drawer", p -> new TooltipBlockItem(ModBlocks.CHERRY_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_DRAWER = DRAWERS.register("crimson_drawer", p -> new TooltipBlockItem(ModBlocks.CRIMSON_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_DRAWER = DRAWERS.register("dark_oak_drawer", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_DRAWER = DRAWERS.register("jungle_drawer", p -> new TooltipBlockItem(ModBlocks.JUNGLE_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_DRAWER = DRAWERS.register("mangrove_drawer", p -> new TooltipBlockItem(ModBlocks.MANGROVE_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_DRAWER = DRAWERS.register("oak_drawer", p -> new TooltipBlockItem(ModBlocks.OAK_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_DRAWER = DRAWERS.register("spruce_drawer", p -> new TooltipBlockItem(ModBlocks.SPRUCE_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_DRAWER = DRAWERS.register("warped_drawer", p -> new TooltipBlockItem(ModBlocks.WARPED_DRAWER.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_SHELF = SHELVES.register("acacia_shelf", p -> new TooltipBlockItem(ModBlocks.ACACIA_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_SHELF = SHELVES.register("bamboo_shelf", p -> new TooltipBlockItem(ModBlocks.BAMBOO_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_SHELF = SHELVES.register("birch_shelf", p -> new TooltipBlockItem(ModBlocks.BIRCH_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_SHELF = SHELVES.register("cherry_shelf", p -> new TooltipBlockItem(ModBlocks.CHERRY_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_SHELF = SHELVES.register("crimson_shelf", p -> new TooltipBlockItem(ModBlocks.CRIMSON_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_SHELF = SHELVES.register("dark_oak_shelf", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_SHELF = SHELVES.register("jungle_shelf", p -> new TooltipBlockItem(ModBlocks.JUNGLE_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_SHELF = SHELVES.register("mangrove_shelf", p -> new TooltipBlockItem(ModBlocks.MANGROVE_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_SHELF = SHELVES.register("oak_shelf", p -> new TooltipBlockItem(ModBlocks.OAK_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_SHELF = SHELVES.register("spruce_shelf", p -> new TooltipBlockItem(ModBlocks.SPRUCE_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_SHELF = SHELVES.register("warped_shelf", p -> new TooltipBlockItem(ModBlocks.WARPED_SHELF.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> ACACIA_PILLAR_TRIM = PILLAR_TRIMS.register("acacia_pillar_trim", p -> new TooltipBlockItem(ModBlocks.ACACIA_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> ACACIA_CORNER_TRIM = CORNER_TRIMS.register("acacia_corner_trim", p -> new TooltipBlockItem(ModBlocks.ACACIA_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_PILLAR_TRIM = PILLAR_TRIMS.register("bamboo_pillar_trim", p -> new TooltipBlockItem(ModBlocks.BAMBOO_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BAMBOO_CORNER_TRIM = CORNER_TRIMS.register("bamboo_corner_trim", p -> new TooltipBlockItem(ModBlocks.BAMBOO_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_PILLAR_TRIM = PILLAR_TRIMS.register("birch_pillar_trim", p -> new TooltipBlockItem(ModBlocks.BIRCH_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BIRCH_CORNER_TRIM = CORNER_TRIMS.register("birch_corner_trim", p -> new TooltipBlockItem(ModBlocks.BIRCH_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_PILLAR_TRIM = PILLAR_TRIMS.register("cherry_pillar_trim", p -> new TooltipBlockItem(ModBlocks.CHERRY_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CHERRY_CORNER_TRIM = CORNER_TRIMS.register("cherry_corner_trim", p -> new TooltipBlockItem(ModBlocks.CHERRY_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_PILLAR_TRIM = PILLAR_TRIMS.register("crimson_pillar_trim", p -> new TooltipBlockItem(ModBlocks.CRIMSON_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CRIMSON_CORNER_TRIM = CORNER_TRIMS.register("crimson_corner_trim", p -> new TooltipBlockItem(ModBlocks.CRIMSON_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_PILLAR_TRIM = PILLAR_TRIMS.register("dark_oak_pillar_trim", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DARK_OAK_CORNER_TRIM = CORNER_TRIMS.register("dark_oak_corner_trim", p -> new TooltipBlockItem(ModBlocks.DARK_OAK_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_PILLAR_TRIM = PILLAR_TRIMS.register("jungle_pillar_trim", p -> new TooltipBlockItem(ModBlocks.JUNGLE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> JUNGLE_CORNER_TRIM = CORNER_TRIMS.register("jungle_corner_trim", p -> new TooltipBlockItem(ModBlocks.JUNGLE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_PILLAR_TRIM = PILLAR_TRIMS.register("mangrove_pillar_trim", p -> new TooltipBlockItem(ModBlocks.MANGROVE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> MANGROVE_CORNER_TRIM = CORNER_TRIMS.register("mangrove_corner_trim", p -> new TooltipBlockItem(ModBlocks.MANGROVE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_PILLAR_TRIM = PILLAR_TRIMS.register("oak_pillar_trim", p -> new TooltipBlockItem(ModBlocks.OAK_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> OAK_CORNER_TRIM = CORNER_TRIMS.register("oak_corner_trim", p -> new TooltipBlockItem(ModBlocks.OAK_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_PILLAR_TRIM = PILLAR_TRIMS.register("spruce_pillar_trim", p -> new TooltipBlockItem(ModBlocks.SPRUCE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPRUCE_CORNER_TRIM = CORNER_TRIMS.register("spruce_corner_trim", p -> new TooltipBlockItem(ModBlocks.SPRUCE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_PILLAR_TRIM = PILLAR_TRIMS.register("warped_pillar_trim", p -> new TooltipBlockItem(ModBlocks.WARPED_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WARPED_CORNER_TRIM = CORNER_TRIMS.register("warped_corner_trim", p -> new TooltipBlockItem(ModBlocks.WARPED_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> ANDESITE_PILLAR_TRIM = PILLAR_TRIMS.register("andesite_pillar_trim", p -> new TooltipBlockItem(ModBlocks.ANDESITE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> ANDESITE_CORNER_TRIM = CORNER_TRIMS.register("andesite_corner_trim", p -> new TooltipBlockItem(ModBlocks.ANDESITE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLACKSTONE_PILLAR_TRIM = PILLAR_TRIMS.register("blackstone_pillar_trim", p -> new TooltipBlockItem(ModBlocks.BLACKSTONE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLACKSTONE_CORNER_TRIM = CORNER_TRIMS.register("blackstone_corner_trim", p -> new TooltipBlockItem(ModBlocks.BLACKSTONE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BRICKS_PILLAR_TRIM = PILLAR_TRIMS.register("bricks_pillar_trim", p -> new TooltipBlockItem(ModBlocks.BRICKS_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BRICKS_CORNER_TRIM = CORNER_TRIMS.register("bricks_corner_trim", p -> new TooltipBlockItem(ModBlocks.BRICKS_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CALCITE_PILLAR_TRIM = PILLAR_TRIMS.register("calcite_pillar_trim", p -> new TooltipBlockItem(ModBlocks.CALCITE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> CALCITE_CORNER_TRIM = CORNER_TRIMS.register("calcite_corner_trim", p -> new TooltipBlockItem(ModBlocks.CALCITE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DEEPSLATE_PILLAR_TRIM = PILLAR_TRIMS.register("deepslate_pillar_trim", p -> new TooltipBlockItem(ModBlocks.DEEPSLATE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DEEPSLATE_CORNER_TRIM = CORNER_TRIMS.register("deepslate_corner_trim", p -> new TooltipBlockItem(ModBlocks.DEEPSLATE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DIORITE_PILLAR_TRIM = PILLAR_TRIMS.register("diorite_pillar_trim", p -> new TooltipBlockItem(ModBlocks.DIORITE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DIORITE_CORNER_TRIM = CORNER_TRIMS.register("diorite_corner_trim", p -> new TooltipBlockItem(ModBlocks.DIORITE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DRIPSTONE_PILLAR_TRIM = PILLAR_TRIMS.register("dripstone_pillar_trim", p -> new TooltipBlockItem(ModBlocks.DRIPSTONE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> DRIPSTONE_CORNER_TRIM = CORNER_TRIMS.register("dripstone_corner_trim", p -> new TooltipBlockItem(ModBlocks.DRIPSTONE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GRANITE_PILLAR_TRIM = PILLAR_TRIMS.register("granite_pillar_trim", p -> new TooltipBlockItem(ModBlocks.GRANITE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GRANITE_CORNER_TRIM = CORNER_TRIMS.register("granite_corner_trim", p -> new TooltipBlockItem(ModBlocks.GRANITE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> QUARTZ_PILLAR_TRIM = PILLAR_TRIMS.register("quartz_pillar_trim", p -> new TooltipBlockItem(ModBlocks.QUARTZ_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> QUARTZ_CORNER_TRIM = CORNER_TRIMS.register("quartz_corner_trim", p -> new TooltipBlockItem(ModBlocks.QUARTZ_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> STONE_PILLAR_TRIM = PILLAR_TRIMS.register("stone_pillar_trim", p -> new TooltipBlockItem(ModBlocks.STONE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> STONE_CORNER_TRIM = CORNER_TRIMS.register("stone_corner_trim", p -> new TooltipBlockItem(ModBlocks.STONE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SANDSTONE_PILLAR_TRIM = PILLAR_TRIMS.register("sandstone_pillar_trim", p -> new TooltipBlockItem(ModBlocks.SANDSTONE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SANDSTONE_CORNER_TRIM = CORNER_TRIMS.register("sandstone_corner_trim", p -> new TooltipBlockItem(ModBlocks.SANDSTONE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> RED_SANDSTONE_PILLAR_TRIM = PILLAR_TRIMS.register("red_sandstone_pillar_trim", p -> new TooltipBlockItem(ModBlocks.RED_SANDSTONE_PILLAR_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> RED_SANDSTONE_CORNER_TRIM = CORNER_TRIMS.register("red_sandstone_corner_trim", p -> new TooltipBlockItem(ModBlocks.RED_SANDSTONE_CORNER_TRIM.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> TERRACOTTA_THIN_POT = POTS.register("terracotta_thin_pot", p -> new TooltipBlockItem(ModBlocks.TERRACOTTA_THIN_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> TERRACOTTA_MEDIUM_POT = POTS.register("terracotta_medium_pot", p -> new TooltipBlockItem(ModBlocks.TERRACOTTA_MEDIUM_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> TERRACOTTA_WIDE_POT = POTS.register("terracotta_wide_pot", p -> new TooltipBlockItem(ModBlocks.TERRACOTTA_WIDE_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> TERRACOTTA_THICK_POT = POTS.register("terracotta_thick_pot", p -> new TooltipBlockItem(ModBlocks.TERRACOTTA_THICK_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WHITE_GLAZED_THIN_POT = POTS.register("white_glazed_thin_pot", p -> new TooltipBlockItem(ModBlocks.WHITE_GLAZED_THIN_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WHITE_GLAZED_MEDIUM_POT = POTS.register("white_glazed_medium_pot", p -> new TooltipBlockItem(ModBlocks.WHITE_GLAZED_MEDIUM_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WHITE_GLAZED_WIDE_POT = POTS.register("white_glazed_wide_pot", p -> new TooltipBlockItem(ModBlocks.WHITE_GLAZED_WIDE_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WHITE_GLAZED_THICK_POT = POTS.register("white_glazed_thick_pot", p -> new TooltipBlockItem(ModBlocks.WHITE_GLAZED_THICK_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_GLAZED_THIN_POT = POTS.register("blue_glazed_thin_pot", p -> new TooltipBlockItem(ModBlocks.BLUE_GLAZED_THIN_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_GLAZED_MEDIUM_POT = POTS.register("blue_glazed_medium_pot", p -> new TooltipBlockItem(ModBlocks.BLUE_GLAZED_MEDIUM_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_GLAZED_WIDE_POT = POTS.register("blue_glazed_wide_pot", p -> new TooltipBlockItem(ModBlocks.BLUE_GLAZED_WIDE_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_GLAZED_THICK_POT = POTS.register("blue_glazed_thick_pot", p -> new TooltipBlockItem(ModBlocks.BLUE_GLAZED_THICK_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GOLDEN_THIN_POT = POTS.register("golden_thin_pot", p -> new TooltipBlockItem(ModBlocks.GOLDEN_THIN_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GOLDEN_MEDIUM_POT = POTS.register("golden_medium_pot", p -> new TooltipBlockItem(ModBlocks.GOLDEN_MEDIUM_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GOLDEN_WIDE_POT = POTS.register("golden_wide_pot", p -> new TooltipBlockItem(ModBlocks.GOLDEN_WIDE_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GOLDEN_THICK_POT = POTS.register("golden_thick_pot", p -> new TooltipBlockItem(ModBlocks.GOLDEN_THICK_POT.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> BEAR_TROPHY = TROPHIES.register("bear_trophy", p -> new TooltipBlockItem(ModBlocks.BEAR_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLAZE_TROPHY = TROPHIES.register("blaze_trophy", p -> new TooltipBlockItem(ModBlocks.BLAZE_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> FOX_TROPHY = TROPHIES.register("fox_trophy", p -> new TooltipBlockItem(ModBlocks.FOX_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> GOAT_TROPHY = TROPHIES.register("goat_trophy", p -> new TooltipBlockItem(ModBlocks.GOAT_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> PUFFERFISH_TROPHY = TROPHIES.register("pufferfish_trophy", p -> new TooltipBlockItem(ModBlocks.PUFFERFISH_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SALMON_TROPHY = TROPHIES.register("salmon_trophy", p -> new TooltipBlockItem(ModBlocks.SALMON_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SILVERFISH_TROPHY = TROPHIES.register("silverfish_trophy", p -> new TooltipBlockItem(ModBlocks.SILVERFISH_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SKELETON_HORSE_TROPHY = TROPHIES.register("skeleton_horse_trophy", p -> new TooltipBlockItem(ModBlocks.SKELETON_HORSE_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SPIDER_TROPHY = TROPHIES.register("spider_trophy", p -> new TooltipBlockItem(ModBlocks.SPIDER_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> TROPICAL_FISH_TROPHY = TROPHIES.register("tropical_fish_trophy", p -> new TooltipBlockItem(ModBlocks.TROPICAL_FISH_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WITHER_SKELETON_TROPHY = TROPHIES.register("wither_skeleton_trophy", p -> new TooltipBlockItem(ModBlocks.WITHER_SKELETON_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WOLF_TROPHY = TROPHIES.register("wolf_trophy", p -> new TooltipBlockItem(ModBlocks.WOLF_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> PHANTOM_TROPHY = TROPHIES.register("phantom_trophy", p -> new TooltipBlockItem(ModBlocks.PHANTOM_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> CREEPER_TROPHY = TROPHIES.register("creeper_trophy", p -> new TooltipBlockItem(ModBlocks.CREEPER_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> SKELETON_TROPHY = TROPHIES.register("skeleton_trophy", p -> new TooltipBlockItem(ModBlocks.SKELETON_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> EVOKER_TROPHY = TROPHIES.register("evoker_trophy", p -> new TooltipBlockItem(ModBlocks.EVOKER_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> PILLAGER_TROPHY = TROPHIES.register("pillager_trophy", p -> new TooltipBlockItem(ModBlocks.PILLAGER_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> VINDICATOR_TROPHY = TROPHIES.register("vindicator_trophy", p -> new TooltipBlockItem(ModBlocks.VINDICATOR_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WITCH_TROPHY = TROPHIES.register("witch_trophy", p -> new TooltipBlockItem(ModBlocks.WITCH_TROPHY.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> WHITE_CUP = CUPS.register("white_cup", p -> new TooltipBlockItem(ModBlocks.WHITE_CUP.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> YELLOW_CUP = CUPS.register("yellow_cup", p -> new TooltipBlockItem(ModBlocks.YELLOW_CUP.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_CUP = CUPS.register("blue_cup", p -> new TooltipBlockItem(ModBlocks.BLUE_CUP.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WOOD_CUP = CUPS.register("wood_cup", p -> new TooltipBlockItem(ModBlocks.WOOD_CUP.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> TERRACOTTA_CUP = CUPS.register("terracotta_cup", p -> new TooltipBlockItem(ModBlocks.TERRACOTTA_CUP.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> WHITE_PLATE = PLATES.register("white_plate", p -> new TooltipBlockItem(ModBlocks.WHITE_PLATE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> YELLOW_PLATE = PLATES.register("yellow_plate", p -> new TooltipBlockItem(ModBlocks.YELLOW_PLATE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_PLATE = PLATES.register("blue_plate", p -> new TooltipBlockItem(ModBlocks.BLUE_PLATE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WOOD_PLATE = PLATES.register("wood_plate", p -> new TooltipBlockItem(ModBlocks.WOOD_PLATE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> TERRACOTTA_PLATE = PLATES.register("terracotta_plate", p -> new TooltipBlockItem(ModBlocks.TERRACOTTA_PLATE.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> WHITE_BOWL = BOWLS.register("white_bowl", p -> new TooltipBlockItem(ModBlocks.WHITE_BOWL.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> YELLOW_BOWL = BOWLS.register("yellow_bowl", p -> new TooltipBlockItem(ModBlocks.YELLOW_BOWL.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_BOWL = BOWLS.register("blue_bowl", p -> new TooltipBlockItem(ModBlocks.BLUE_BOWL.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WOOD_BOWL = BOWLS.register("wood_bowl", p -> new TooltipBlockItem(ModBlocks.WOOD_BOWL.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> TERRACOTTA_BOWL = BOWLS.register("terracotta_bowl", p -> new TooltipBlockItem(ModBlocks.TERRACOTTA_BOWL.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);

    public static final RegistryEntry<Item> WHITE_CROCKERY_COMBO = CROCKERY_COMBOS.register("white_crockery_combo", p -> new TooltipBlockItem(ModBlocks.WHITE_CROCKERY_COMBO.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> YELLOW_CROCKERY_COMBO = CROCKERY_COMBOS.register("yellow_crockery_combo", p -> new TooltipBlockItem(ModBlocks.YELLOW_CROCKERY_COMBO.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> BLUE_CROCKERY_COMBO = CROCKERY_COMBOS.register("blue_crockery_combo", p -> new TooltipBlockItem(ModBlocks.BLUE_CROCKERY_COMBO.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> WOOD_CROCKERY_COMBO = CROCKERY_COMBOS.register("wood_crockery_combo", p -> new TooltipBlockItem(ModBlocks.WOOD_CROCKERY_COMBO.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
    public static final RegistryEntry<Item> TERRACOTTA_CROCKERY_COMBO = CROCKERY_COMBOS.register("terracotta_crockery_combo", p -> new TooltipBlockItem(ModBlocks.TERRACOTTA_CROCKERY_COMBO.get(), p.useBlockDescriptionPrefix()), Item.Properties::new);
}
