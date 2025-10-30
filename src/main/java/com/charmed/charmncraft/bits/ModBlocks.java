package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.blocks.AzaleaFlowersBlock;
import com.charmed.charmncraft.bits.blocks.ConsoleBlock;
import com.charmed.charmncraft.bits.blocks.MagnumTorchBlock;
import com.charmed.charmncraft.bits.blocks.NightLightBlock;
import com.charmed.charmncraft.bits.blocks.NubertBlock;
import com.charmed.charmncraft.bits.blocks.PlushieBlock;
import com.charmed.charmncraft.bits.blocks.PottedAzaleaFlowersBlock;
import com.charmed.charmncraft.bits.blocks.TennaPoleBlock;
import com.charmed.charmncraft.bits.blocks.TennaStatueBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.block.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * ModBlocks - Central registration hub for all custom blocks in Charm n Craft Bits
 *
 * This class organizes and registers blocks into the following categories:
 * - Stacked Blocks: Decorative material blocks
 * - Crates: Food and produce storage crates
 * - Bags: Ingredient and powder bags
 * - Twigs: Nature decorations (azalea flowers)
 * - Consoles: Gaming console decorations
 * - Deltarune: Deltarune-themed blocks
 * - Magnum Torches: Anti-spawn torches with special abilities
 * - Plushies: Character plushies
 * - Night Lights: Interactive decorative lights
 */
public class ModBlocks {
    // Use the correct MOD_ID from main class
    private static final String MOD_ID = Charmncraftbits.MOD_ID;

    // ====== MASTER BLOCK LISTS ======
    // These lists organize blocks by category for creative tabs and easier management
    public static final List<Block> STACKED_BLOCKS = new ArrayList<>();      // Decorative stacked material blocks
    public static final List<Block> CRATE_BLOCKS = new ArrayList<>();        // Food and produce storage crates
    public static final List<Block> BAG_BLOCKS = new ArrayList<>();          // Ingredient and powder bags
    public static final List<Block> MAGNUM_TORCH_BLOCKS = new ArrayList<>(); // Anti-spawn torches
    public static final List<Block> CONSOLE_BLOCKS = new ArrayList<>();      // Gaming console decorations
    public static final List<Block> TWIGS_BLOCKS = new ArrayList<>();        // Nature decorations (azalea)
    public static final List<Block> DELTARUNE_BLOCKS = new ArrayList<>();    // Deltarune-themed blocks
    public static final List<Block> PLUSHIE_BLOCKS = new ArrayList<>();      // Character plushies
    public static final List<Block> NIGHT_LIGHT_BLOCKS = new ArrayList<>();  // Decorative lights

    // ====== TWIGS INDIVIDUAL BLOCKS ======
    // Public references for blocks that need special client-side handling (render layers, etc.)
    public static Block AZALEA_FLOWERS;
    public static Block POTTED_AZALEA_FLOWERS;

    // ====== BLOCK TYPE REGISTRIES ======
    // Organized by category for better maintainability
    private static final String[] STACKED_BLOCK_TYPES = {
            // Wood Logs (Overworld)
            "stacked_oak_logs", "stacked_spruce_logs", "stacked_birch_logs", "stacked_jungle_logs",
            "stacked_acacia_logs", "stacked_dark_oak_logs", "stacked_mangrove_logs", "stacked_cherry_logs",
            "stacked_bamboo_blocks", "stacked_pale_oak_logs",

            // Stripped Wood Logs (Overworld)
            "stacked_stripped_oak_logs", "stacked_stripped_spruce_logs", "stacked_stripped_birch_logs",
            "stacked_stripped_jungle_logs", "stacked_stripped_acacia_logs", "stacked_stripped_dark_oak_logs",
            "stacked_stripped_mangrove_logs", "stacked_stripped_cherry_logs", "stacked_stripped_bamboo_blocks",
            "stacked_stripped_pale_oak_logs",

            // Nether Stems
            "stacked_crimson_stems", "stacked_warped_stems",
            "stacked_stripped_crimson_stems", "stacked_stripped_warped_stems",

            // Wood Planks
            "stacked_oak_planks", "stacked_spruce_planks", "stacked_birch_planks", "stacked_jungle_planks",
            "stacked_acacia_planks", "stacked_dark_oak_planks", "stacked_mangrove_planks", "stacked_cherry_planks",
            "stacked_bamboo_planks", "stacked_crimson_planks", "stacked_warped_planks",

            // Stone & Building Blocks
            "stacked_stone_blocks", "stacked_cobblestone_blocks", "stacked_netherrack_blocks",
            "stacked_bricks", "stacked_quartz_blocks", "stacked_resin_blocks", "stacked_resin_bricks",

            // Mineral Blocks
            "stacked_coal_blocks", "stacked_iron_blocks", "stacked_gold_blocks", "stacked_lapis_blocks",
            "stacked_redstone_blocks", "stacked_diamond_blocks", "stacked_emerald_blocks", "stacked_netherite_blocks",

            // Raw Ore Blocks
            "stacked_raw_iron_blocks", "stacked_raw_gold_blocks", "stacked_raw_copper_blocks",

            // Organic/Food Blocks
            "stacked_melons", "stacked_pumpkins", "stacked_organic_compost"
    };

    private static final Set<String> AMW_PLUSHIE_NAMES = Set.of(
            "abbie_plushie", "maddie_plushie", "willow_plushie"
    );

    // ====== REGISTER HELPERS ======
    private static Block registerBasicBlock(String name, Block.Settings settings) {
        Block block = new Block(settings);
        Identifier id = Identifier.of(MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        return block;
    }

    // ====== STACKED BLOCKS ======
    // Reusable settings for all stacked blocks to improve performance
    private static final Block.Settings STACKED_BLOCK_SETTINGS = Block.Settings.create()
            .strength(2.0f, 6.0f)
            .sounds(BlockSoundGroup.WOOD)
            .requiresTool();

    /**
     * Registers all stacked block variants.
     * All blocks share the same properties for consistency and balance.
     */
    private static void registerStackedBlocks() {
        for (String blockName : STACKED_BLOCK_TYPES) {
            Block stackedBlock = new Block(STACKED_BLOCK_SETTINGS);
            registerStackedBlock(blockName, stackedBlock);
        }
    }

    /**
     * Helper method to register a single stacked block and add it to the collection.
     * @param name The block identifier name
     * @param block The block instance to register
     */
    private static void registerStackedBlock(String name, Block block) {
        Identifier id = Identifier.of(MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        STACKED_BLOCKS.add(block);
    }

    // ====== PLUSHIE CUSTOM HITBOXES ======
    // Auto-generated plushie voxel shapes (example shown for ABBIE)
    // These are static final so they're initialized once and reused.
    private static final VoxelShape ABBIE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
            2.5f / 16f, 0f / 16f, 5.375f / 16f,  // min x, y, z
            15.25f / 16f, 11f / 16f, 13.5f / 16f // max x, y, z
    );

    private static final VoxelShape MADDIE_PLUSHIE_SHAPE = VoxelShapes.cuboid(
            3.0f / 16f, 0f / 16f, 6.0f / 16f,
            14.0f / 16f, 11.0f / 16f, 13.0f / 16f
    );

    // ====== CONSOLE BLOCKS ======
    // Note: Basic console registration removed - all consoles now use extended registration with proper models
    private static void registerConsoleBlocks() {
        // This method is kept for backwards compatibility but does nothing
        // All console registration happens in registerExtendedConsoles()
    }

    private static void registerConsoleBlock(String name, Block block) {
        Identifier id = Identifier.of(MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        CONSOLE_BLOCKS.add(block);
    }

    // ====== MAGNUM TORCHES ======
    // Magnum torches are powerful decorative light sources
    // Only three variants: Amethyst, Diamond, and Emerald

    private static final VoxelShape WILLOW_PLUSHIE_SHAPE = VoxelShapes.cuboid(
            2.0f / 16f, 0f / 16f, 4.0f / 16f,
            14.5f / 16f, 10.5f / 16f, 12.5f / 16f
    );

    // ====== PLUSHIE REGISTRATION ======
    private static void registerAMWPlushies() {
        for (String name : AMW_PLUSHIE_NAMES) {
            VoxelShape shape = getAMWPlushieShape(name);
            Block plushieBlock = new PlushieBlock(
                    FabricBlockSettings.create()
                            .nonOpaque()
                            .strength(0.5f)
                            .sounds(BlockSoundGroup.WOOL),
                    shape);

            registerPlushieBlock(name, plushieBlock);
        }
    }

    private static VoxelShape getAMWPlushieShape(String name) {
        return switch (name) {
            case "abbie_plushie" -> ABBIE_PLUSHIE_SHAPE;
            case "maddie_plushie" -> MADDIE_PLUSHIE_SHAPE;
            case "willow_plushie" -> WILLOW_PLUSHIE_SHAPE;
            default -> VoxelShapes.fullCube();
        };
    }

    private static void registerPlushieBlock(String name, Block block) {
        Identifier id = Identifier.of(MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        PLUSHIE_BLOCKS.add(block);
    }

    // ====== TWIGS DECOR BLOCKS ======
    // Note: Twig furniture items (twig_table, twig_chair, twig_stool) have been removed
    // Only azalea decor items remain in the twigs category

    // ====== MASTER INITIALIZER ======
    public static void initialize() {
        // Order matters only for logging / clarity, not dependency
        registerStackedBlocks();
        registerAMWPlushies();
        registerAllPlushies();
        registerNightLights();
        registerConsoleBlocks();
        registerDeltaruneBlocks();
        registerExtendedConsoles();
        registerAzaleaDecor();
        registerMagnumTorches();
        registerCrateBlocks();
        registerBagBlocks();

        System.out.println("[Charm n Craft Bits] All custom blocks registered successfully.");
    }

    // ====== DEBUG / UTILITY HELPERS ======

    public static void printRegisteredBlockCounts() {
        System.out.println("==== CharmnCraftBits Block Summary ====");
        System.out.println("Stacked Blocks: " + STACKED_BLOCKS.size());
        System.out.println("Crates: " + CRATE_BLOCKS.size());
        System.out.println("Bags: " + BAG_BLOCKS.size());
        System.out.println("Plushies: " + PLUSHIE_BLOCKS.size());
        System.out.println("Twigs: " + TWIGS_BLOCKS.size());
        System.out.println("Consoles: " + CONSOLE_BLOCKS.size());
        System.out.println("Deltarune: " + DELTARUNE_BLOCKS.size());
        System.out.println("Magnum Torches: " + MAGNUM_TORCH_BLOCKS.size());
        System.out.println("Night Lights: " + NIGHT_LIGHT_BLOCKS.size());
        System.out.println("Total: " + (STACKED_BLOCKS.size() + CRATE_BLOCKS.size() + BAG_BLOCKS.size() +
                                       PLUSHIE_BLOCKS.size() + TWIGS_BLOCKS.size() + CONSOLE_BLOCKS.size() +
                                       DELTARUNE_BLOCKS.size() + MAGNUM_TORCH_BLOCKS.size() +
                                       NIGHT_LIGHT_BLOCKS.size()));
        System.out.println("=======================================");
    }

    // ====== BLOCK RETRIEVAL HELPERS ======

    public static Block getBlockByName(String name) {
        Identifier id = Identifier.of(MOD_ID, name);
        return Registries.BLOCK.get(id);
    }

    public static boolean isRegistered(String name) {
        Identifier id = Identifier.of(MOD_ID, name);
        return Registries.BLOCK.containsId(id);
    }

    public static List<Block> getAllBlocks() {
        List<Block> all = new ArrayList<>();
        all.addAll(STACKED_BLOCKS);
        all.addAll(CRATE_BLOCKS);
        all.addAll(BAG_BLOCKS);
        all.addAll(TWIGS_BLOCKS);
        all.addAll(CONSOLE_BLOCKS);
        all.addAll(DELTARUNE_BLOCKS);
        all.addAll(MAGNUM_TORCH_BLOCKS);
        all.addAll(PLUSHIE_BLOCKS);
        all.addAll(NIGHT_LIGHT_BLOCKS);
        return all;
    }

    // ====== CONSOLE VOXEL SHAPES ======
    // Custom hitboxes extracted from console block models
    private static final VoxelShape PS1_SHAPE = VoxelShapes.cuboid(
        1.5f/16f, 0f/16f, 3.5f/16f,
        14.5f/16f, 2.5f/16f, 12.5f/16f
    );

    private static final VoxelShape PS2_SHAPE = VoxelShapes.cuboid(
        0.5f/16f, 0f/16f, 3f/16f,
        15.5f/16f, 4f/16f, 13f/16f
    );

    private static final VoxelShape PS4_SHAPE = VoxelShapes.cuboid(
        0.5f/16f, 0f/16f, 1f/16f,
        15.5f/16f, 4f/16f, 14f/16f
    );

    private static final VoxelShape PS5_SHAPE = VoxelShapes.cuboid(
        4.808f/16f, 0.3987f/16f, 0.3938f/16f,
        11.5152f/16f, 25.5507f/16f, 14.6466f/16f
    );

    private static final VoxelShape PSP_SHAPE = VoxelShapes.cuboid(
        1f/16f, 0f/16f, 5f/16f,
        15f/16f, 2f/16f, 11f/16f
    );

    private static final VoxelShape N64_SHAPE = VoxelShapes.cuboid(
        0f/16f, 0f/16f, 1.49f/16f,
        16f/16f, 6f/16f, 14f/16f
    );

    private static final VoxelShape NES_SHAPE = VoxelShapes.cuboid(
        1f/16f, 0f/16f, 2f/16f,
        15f/16f, 4f/16f, 13f/16f
    );

    private static final VoxelShape SNES_SHAPE = VoxelShapes.cuboid(
        3.5f/16f, 0f/16f, 1f/16f,
        13.5f/16f, 4f/16f, 14f/16f
    );

    private static final VoxelShape GAMECUBE_SHAPE = VoxelShapes.cuboid(
        2.5f/16f, 0f/16f, 2.5f/16f,
        13.5f/16f, 8f/16f, 13.5f/16f
    );

    private static final VoxelShape WII_SHAPE = VoxelShapes.cuboid(
        6.5f/16f, 0f/16f, 0.75f/16f,
        11.5f/16f, 13.4f/16f, 13.75f/16f
    );

    private static final VoxelShape OG_XBOX_SHAPE = VoxelShapes.cuboid(
        0f/16f, 0f/16f, 0.5f/16f,
        16f/16f, 6f/16f, 15.5f/16f
    );

    private static final VoxelShape XBOX_1_SHAPE = VoxelShapes.cuboid(
        0f/16f, 0f/16f, 2f/16f,
        16f/16f, 4f/16f, 15f/16f
    );

    private static final VoxelShape XBOX_SERIES_S_SHAPE = VoxelShapes.cuboid(
        1f/16f, 0f/16f, 2.5f/16f,
        5f/16f, 19f/16f, 13.5f/16f
    );

    private static final VoxelShape XBOX_SERIES_X_SHAPE = VoxelShapes.cuboid(
        2.5f/16f, 0f/16f, 2.5f/16f,
        13.5f/16f, 21f/16f, 13.5f/16f
    );

    private static final VoxelShape DREAMCAST_SHAPE = VoxelShapes.cuboid(
        1.5f/16f, 0f/16f, 2f/16f,
        14.5f/16f, 4f/16f, 14f/16f
    );

    private static final VoxelShape SEGA_GEN_SHAPE = VoxelShapes.cuboid(
        2f/16f, 0f/16f, 3f/16f,
        14f/16f, 4f/16f, 13f/16f
    );

    private static final VoxelShape DS_SHAPE = VoxelShapes.cuboid(
        4.99f/16f, 0f/16f, 2.8f/16f,
        14.01f/16f, 5.51f/16f, 9.51f/16f
    );

    private static final VoxelShape GAMEBOYS_SHAPE = VoxelShapes.cuboid(
        1f/16f, 0f/16f, 1.8f/16f,
        15f/16f, 2f/16f, 12.8f/16f
    );

    private static final VoxelShape SWITCH_DOCK_SHAPE = VoxelShapes.cuboid(
        1.5f/16f, 0f/16f, 6f/16f,
        14.5f/16f, 9f/16f, 10f/16f
    );

    private static final VoxelShape SWITCH_IN_DOCK_SHAPE = VoxelShapes.cuboid(
        0f/16f, 0f/16f, 6f/16f,
        16f/16f, 10f/16f, 10f/16f
    );

    private static final VoxelShape TV_SHAPE = VoxelShapes.cuboid(
        0.5f/16f, 0f/16f, 4f/16f,
        15.5f/16f, 11f/16f, 12f/16f
    );

    // ====== CONSOLE REGISTRATION DATA ======
    // Static final maps to avoid recreating them on every registration call
    private static final Map<String, Float> CONSOLE_STRENGTH = Map.ofEntries(
            Map.entry("dreamcast", 2.0f),
            Map.entry("ds", 1.5f),
            Map.entry("gameboys", 1.5f),
            Map.entry("gamecube", 2.0f),
            Map.entry("dock", 1.5f),
            Map.entry("n_64", 1.8f),
            Map.entry("nes", 1.7f),
            Map.entry("ps_1", 1.5f),
            Map.entry("ps_2", 1.7f),
            Map.entry("ps_4", 2.0f),
            Map.entry("ps_5", 2.2f),
            Map.entry("psp", 1.3f),
            Map.entry("sega_genesis", 1.5f),
            Map.entry("snes", 1.8f),
            Map.entry("switch_in_dock", 1.8f),
            Map.entry("tv", 1.8f),
            Map.entry("wii", 1.8f),
            Map.entry("xbox", 1.8f),
            Map.entry("xbox_1", 2.0f),
            Map.entry("xbox_series_s", 2.0f),
            Map.entry("xbox_series_x", 2.2f)
    );

    private static final Map<String, VoxelShape> CONSOLE_SHAPES = Map.ofEntries(
            Map.entry("dreamcast", DREAMCAST_SHAPE),
            Map.entry("ds", DS_SHAPE),
            Map.entry("gameboys", GAMEBOYS_SHAPE),
            Map.entry("gamecube", GAMECUBE_SHAPE),
            Map.entry("dock", SWITCH_DOCK_SHAPE),
            Map.entry("n_64", N64_SHAPE),
            Map.entry("nes", NES_SHAPE),
            Map.entry("ps_1", PS1_SHAPE),
            Map.entry("ps_2", PS2_SHAPE),
            Map.entry("ps_4", PS4_SHAPE),
            Map.entry("ps_5", PS5_SHAPE),
            Map.entry("psp", PSP_SHAPE),
            Map.entry("sega_genesis", SEGA_GEN_SHAPE),
            Map.entry("snes", SNES_SHAPE),
            Map.entry("switch_in_dock", SWITCH_IN_DOCK_SHAPE),
            Map.entry("tv", TV_SHAPE),
            Map.entry("wii", WII_SHAPE),
            Map.entry("xbox", OG_XBOX_SHAPE),
            Map.entry("xbox_1", XBOX_1_SHAPE),
            Map.entry("xbox_series_s", XBOX_SERIES_S_SHAPE),
            Map.entry("xbox_series_x", XBOX_SERIES_X_SHAPE)
    );

    // ====== EXTENDED CONSOLE SHAPES ======
    private static void registerExtendedConsoles() {
        // Register all consoles with their respective strength and shape values
        for (Map.Entry<String, Float> entry : CONSOLE_STRENGTH.entrySet()) {
            String name = entry.getKey();
            float strength = entry.getValue();
            VoxelShape shape = CONSOLE_SHAPES.get(name);

            Block consoleBlock = new ConsoleBlock(
                    FabricBlockSettings.create()
                            .nonOpaque()
                            .strength(strength)
                            .sounds(BlockSoundGroup.METAL),
                    shape);

            registerConsoleBlock(name, consoleBlock);
        }
    }

    // ====== AZALEA DECOR ======
    private static void registerAzaleaDecor() {
        // Azalea flowers - multi-surface placement block
        AZALEA_FLOWERS = new AzaleaFlowersBlock(FabricBlockSettings.create()
                .nonOpaque()
                .strength(0.5f)
                .noCollision()
                .sounds(BlockSoundGroup.GRASS));
        registerTwigsBlock("azalea_flowers", AZALEA_FLOWERS);

        // Potted azalea flowers - decorative flower pot variant
        POTTED_AZALEA_FLOWERS = new PottedAzaleaFlowersBlock(
                AZALEA_FLOWERS,
                FabricBlockSettings.create()
                        .nonOpaque()
                        .strength(0.3f)
                        .sounds(BlockSoundGroup.GRASS));
        registerTwigsBlock("potted_azalea_flowers", POTTED_AZALEA_FLOWERS);
    }

    // Helper method for registering twigs blocks
    private static void registerTwigsBlock(String name, Block block) {
        Identifier id = Identifier.of(MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        TWIGS_BLOCKS.add(block);
    }

    // ====== MAGNUM TORCH REGISTRATION ======
    // Magnum torches prevent mob spawning in a 32-block radius
    // - Diamond: Prevents hostile mobs (zombies, skeletons, creepers, spiders, phantoms, etc.)
    // - Emerald: Prevents passive mobs (wandering traders, trader llamas, animals)
    // - Amethyst: Prevents cave/water mobs (bats, squids, axolotls, fish)
    // All emit light level 14 (same as regular torches)
    private static void registerMagnumTorches() {
        registerMagnumTorchBlock("amethyst_magnum_torch", MagnumTorchBlock.TorchType.AMETHYST);
        registerMagnumTorchBlock("diamond_magnum_torch", MagnumTorchBlock.TorchType.DIAMOND);
        registerMagnumTorchBlock("emerald_magnum_torch", MagnumTorchBlock.TorchType.EMERALD);
    }

    private static void registerMagnumTorchBlock(String name, MagnumTorchBlock.TorchType torchType) {
        Block block = new MagnumTorchBlock(FabricBlockSettings.create()
                .luminance(14)  // Same as regular torch
                .strength(1.0f)
                .sounds(BlockSoundGroup.WOOD)
                .nonOpaque(), torchType);
        Identifier id = Identifier.of(MOD_ID, name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        MAGNUM_TORCH_BLOCKS.add(block);
    }

    // ====== PLUSHIES REGISTRATION ======
    private static void registerAllPlushies() {
        // Get all plushie blockstates from resources
        // NOTE: abbie_plushie, maddie_plushie, and willow_plushie are registered separately in registerAMWPlushies()
        String[] plushieNames = {
            "aggressive_eye_of_cthulhu_plushie", "aggressive_retinazer_plushie",
            "aggressive_spazmatism_plushie", "allay_plushie", "angry_baldi_plushie",
            "angry_camper_baldi_plushie", "angry_farmer_baldi_plushie", "animdude_plushie",
            "audino_plushie", "axolotl_plushie", "azazel_plushie", "badeline_plushie",
            "baldi_plushie", "bandage_girl_plushie", "bat_plushie", "bee_plushie", "bendy_plushie",
            "black_crewmate_plushie", "black_junimo_plushie", "black_slime_plushie",
            "blob_plushie", "blue_crewmate_plushie", "blue_junimo_plushie", "blue_slime_plushie",
            "bmo_plushie", "breadbug_plushie", "brown_crewmate_plushie", "brown_junimo_plushie",
            "brown_slime_plushie", "cal_kestis_plushie", "camper_baldi_plushie", "carrot_plushie",
            "caterpillar_plushie", "catnap_plushie", "celeste_plushie", "chicken_plushie",
            "chomper_plushie", "clem_plushie", "clicker_plushie", "cold_frog_plushie",
            "copper_golem_plushie", "cow_plushie", "creeper_plushie", "crewmate_plushie",
            "cyan_crewmate_plushie", "cyan_junimo_plushie", "cyan_slime_plushie",
            "dark_knight_plushie", "death_plushie", "demon_plushie", "diamond_steve_plushie",
            "doggo_plushie", "dusty_plushie", "eden_plushie", "egg_plushie",
            "emerald_steve_plushie", "enderman_plushie", "ender_dragon_plushie",
            "ender_drake_plushie", "eve_plushie", "eye_of_cthulhu_plushie", "farmer_baldi_plushie",
            "finn_plushie", "flowey_plushie", "forgotten_plushie", "fox_plushie",
            "frog_plushie", "ghostface_plushie", "giant_plushie", "glaggle_plushie",
            "glare_plushie", "gloria_plushie", "gold_steve_plushie", "grandma_plushie",
            "gray_crewmate_plushie", "gray_junimo_plushie", "gray_slime_plushie",
            "green_crewmate_plushie", "green_junimo_plushie", "green_slime_plushie",
            "grumbo_jumbo_plushie", "happy_baldi_plushie", "happy_camper_baldi_plushie",
            "happy_farmer_baldi_plushie", "hello_charlotte_plushie", "hopps_plushie",
            "iron_golem_plushie", "isaac_plushie", "isabelle_plushie", "jacob_plushie",
            "jake_plushie", "jellie_plushie", "junimo_plushie", "kerfur_plushie",
            "killer_bunny_plushie", "king_of_hearts_plushie", "kissy_missy_plushie",
            "knight_of_hearts_plushie", "lazarus_plushie", "life_plushie", "light_blue_crewmate_plushie",
            "light_blue_junimo_plushie", "light_blue_slime_plushie", "light_gray_crewmate_plushie",
            "light_gray_junimo_plushie", "light_gray_slime_plushie", "lilith_plushie",
            "lime_crewmate_plushie", "lime_junimo_plushie", "lime_slime_plushie",
            "lost_plushie", "luigi_plushie", "madeline_plushie",
            "magenta_crewmate_plushie", "magenta_junimo_plushie", "magenta_slime_plushie",
            "magma_cube_plushie", "magnemite_plushie", "magnetite_plushie", "mario_plushie",
            "meatboy_plushie", "meowscles_plushie", "michael_plushie", "moobloom_plushie",
            "moon_presence_plushie", "mushroom_cow_plushie", "noelle_plushie", "nubert_plushie",
            "ochaco_plushie", "omurice_plushie", "orange_crewmate_plushie", "orange_junimo_plushie",
            "orange_slime_plushie", "pale_king_plushie", "papyrus_plushie", "peashooter_plushie",
            "peepy_plushie", "penguin_plushie", "pig_plushie", "pink_crewmate_plushie",
            "pink_junimo_plushie", "pink_slime_plushie", "player_plushie", "poke_ball_plushie",
            "principal_plushie", "purple_crewmate_plushie", "purple_junimo_plushie",
            "purple_slime_plushie", "queen_bee_plushie", "ralsei_plushie", "red_crewmate_plushie",
            "red_junimo_plushie", "red_slime_plushie", "retinazer_plushie", "roomba_plushie",
            "rudolph_plushie", "sad_ghost_plushie", "samson_plushie", "sans_plushie",
            "scp_999_plushie", "scp_131_plushie", "sheep_plushie", "sibling_plushie",
            "skeletron_plushie", "slime_plushie", "smugdoka_plushie", "sniffer_plushie",
            "snowman_plushie", "spazmatism_plushie", "spider_plushie", "spiffo_plushie",
            "sprig_plushie", "steve_plushie", "stray_plushie", "sunflower_plushie",
            "sus_jerma_plushie", "susie_plushie", "tadpole_plushie", "tarnished_plushie",
            "terrarian_plushie", "tinted_plushie", "toad_plushie", "tom_nook_plushie",
            "toriel_plushie", "towelie_plushie", "trader_llama_plushie", "tuff_golem_plushie",
            "undyne_plushie", "warden_plushie", "white_crewmate_plushie", "white_junimo_plushie",
            "white_slime_plushie", "wooly_cow_plushie", "wumpus_plushie",
            "yellow_crewmate_plushie", "yellow_junimo_plushie", "yellow_slime_plushie",
            "zombie_plushie"
        };

        for (String name : plushieNames) {
            Block plushieBlock = new PlushieBlock(FabricBlockSettings.create()
                    .nonOpaque()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL));

            Identifier id = Identifier.of(MOD_ID, name);
            Registry.register(Registries.BLOCK, id, plushieBlock);
            Registry.register(Registries.ITEM, id, new BlockItem(plushieBlock, new Item.Settings()));
            PLUSHIE_BLOCKS.add(plushieBlock);
        }
    }

    // ====== NIGHT LIGHTS REGISTRATION ======
    private static void registerNightLights() {
        String[] nightLightNames = {
            "frog_black", "frog_blue", "frog_brown", "frog_cyan", "frog_gray", "frog_green",
            "frog_light_blue", "frog_light_gray", "frog_lime", "frog_magenta", "frog_orange",
            "frog_pink", "frog_purple", "frog_red", "frog_white", "frog_yellow",
            "mushroom_black", "mushroom_blue", "mushroom_brown", "mushroom_cyan", "mushroom_gray",
            "mushroom_green", "mushroom_light_blue", "mushroom_light_gray", "mushroom_lime",
            "mushroom_magenta", "mushroom_orange", "mushroom_pink", "mushroom_purple",
            "mushroom_red", "mushroom_white", "mushroom_yellow"
        };

        VoxelShape nightLightShape = VoxelShapes.cuboid(4/16f, 0, 4/16f, 12/16f, 8/16f, 12/16f);

        for (String name : nightLightNames) {
            Block nightLightBlock = new NightLightBlock(FabricBlockSettings.create()
                    .nonOpaque()
                    .luminance(state -> state.get(NightLightBlock.LIT) ? 15 : 0)
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL), nightLightShape);

            Identifier id = Identifier.of(MOD_ID, name);
            Registry.register(Registries.BLOCK, id, nightLightBlock);
            Registry.register(Registries.ITEM, id, new BlockItem(nightLightBlock, new Item.Settings()));
            NIGHT_LIGHT_BLOCKS.add(nightLightBlock);
        }
    }

    // ====== CRATE BLOCKS REGISTRATION ======
    private static void registerCrateBlocks() {
        String[] crateNames = {
            "apple_crate", "bass_crate", "beetroot_crate", "berry_crate",
            "black_berry_crate", "blueberry_crate", "brown_mushroom_crate",
            "carrot_crate", "catfish_crate", "cod_crate", "diamond_apple_crate",
            "duck_egg_crate", "egg_crate", "end_fish_crate", "glowberry_crate",
            "golden_apple_crate", "golden_carrot_crate", "green_berry_crate",
            "kiwi_egg_crate", "kiwifruit_crate", "orange_berry_crate",
            "peanut_crate", "potato_crate", "purple_berry_crate",
            "red_mushroom_crate", "salmon_crate", "yellow_berry_crate"
        };

        for (String name : crateNames) {
            Block crateBlock = new Block(FabricBlockSettings.create()
                    .strength(2.0f)
                    .sounds(BlockSoundGroup.WOOD));

            Identifier id = Identifier.of(MOD_ID, name);
            Registry.register(Registries.BLOCK, id, crateBlock);
            Registry.register(Registries.ITEM, id, new BlockItem(crateBlock, new Item.Settings()));
            CRATE_BLOCKS.add(crateBlock);
        }
    }

    // ====== BAG BLOCKS REGISTRATION ======
    private static void registerBagBlocks() {
        String[] bagNames = {
            "cinder_flour_bag", "cocoabeans_bag", "cookie_bag", "ender_dust_bag",
            "ground_cinnamon_bag", "gunpowder_bag", "powdered_obsidian_bag",
            "salt_bag", "sugar_bag", "wheat_flour_bag"
        };

        for (String name : bagNames) {
            Block bagBlock = new Block(FabricBlockSettings.create()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL));

            Identifier id = Identifier.of(MOD_ID, name);
            Registry.register(Registries.BLOCK, id, bagBlock);
            Registry.register(Registries.ITEM, id, new BlockItem(bagBlock, new Item.Settings()));
            BAG_BLOCKS.add(bagBlock);
        }
    }

    // ====== DELTARUNE BLOCKS ======
    private static void registerDeltaruneBlocks() {
        // Nubert block
        Block nubertBlock = new NubertBlock(FabricBlockSettings.create()
                .nonOpaque()
                .strength(0.5f)
                .sounds(BlockSoundGroup.SLIME));
        Identifier nubertId = Identifier.of(MOD_ID, "nubert");
        Registry.register(Registries.BLOCK, nubertId, nubertBlock);
        Registry.register(Registries.ITEM, nubertId, new BlockItem(nubertBlock, new Item.Settings()));
        DELTARUNE_BLOCKS.add(nubertBlock);

        // Tenna Statue block
        Block tennaStatueBlock = new TennaStatueBlock(FabricBlockSettings.create()
                .nonOpaque()
                .strength(1.0f)
                .sounds(BlockSoundGroup.METAL));
        Identifier tennaStatueId = Identifier.of(MOD_ID, "tenna_statue");
        Registry.register(Registries.BLOCK, tennaStatueId, tennaStatueBlock);
        Registry.register(Registries.ITEM, tennaStatueId, new BlockItem(tennaStatueBlock, new Item.Settings()));
        DELTARUNE_BLOCKS.add(tennaStatueBlock);

        // Tenna Pole block
        Block tennaPoleBlock = new TennaPoleBlock(FabricBlockSettings.create()
                .nonOpaque()
                .strength(1.0f)
                .sounds(BlockSoundGroup.METAL));
        Identifier tennaPoleId = Identifier.of(MOD_ID, "tenna_pole");
        Registry.register(Registries.BLOCK, tennaPoleId, tennaPoleBlock);
        Registry.register(Registries.ITEM, tennaPoleId, new BlockItem(tennaPoleBlock, new Item.Settings()));
        DELTARUNE_BLOCKS.add(tennaPoleBlock);
    }
}