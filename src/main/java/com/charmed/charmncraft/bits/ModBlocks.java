package com.charmed.charmncraft.bits;

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

public class ModBlocks {

    // ====== MASTER BLOCK LISTS ======
    public static final List<Block> STACKED_BLOCKS = new ArrayList<>();
    public static final List<Block> CRATE_BLOCKS = new ArrayList<>();
    public static final List<Block> COLORED_BLOCKS = new ArrayList<>();
    public static final List<Block> MAGNUM_TORCH_BLOCKS = new ArrayList<>();
    public static final List<Block> CONSOLE_BLOCKS = new ArrayList<>();
    public static final List<Block> TWIGS_BLOCKS = new ArrayList<>();
    public static final List<Block> DELTARUNE_BLOCKS = new ArrayList<>();

    // ====== BLOCK TYPE REGISTRIES ======
    private static final String[] STACKED_BLOCK_TYPES = {
            "stacked_acacia_logs", "stacked_acacia_planks", "stacked_bamboo_blocks", "stacked_bamboo_planks",
            "stacked_birch_logs", "stacked_birch_planks", "stacked_bricks", "stacked_cherry_logs",
            "stacked_cherry_planks", "stacked_coal_blocks", "stacked_cobblestone_blocks", "stacked_crimson_planks",
            "stacked_crimson_stems", "stacked_dark_oak_logs", "stacked_dark_oak_planks", "stacked_diamond_blocks",
            "stacked_emerald_blocks", "stacked_gold_blocks", "stacked_iron_blocks", "stacked_jungle_logs",
            "stacked_jungle_planks", "stacked_lapis_blocks", "stacked_mangrove_logs", "stacked_mangrove_planks",
            "stacked_melons", "stacked_netherite_blocks", "stacked_netherrack_blocks", "stacked_oak_logs",
            "stacked_oak_planks", "stacked_organic_compost", "stacked_pumpkins", "stacked_quartz_blocks",
            "stacked_raw_copper_blocks", "stacked_raw_gold_blocks", "stacked_raw_iron_blocks", "stacked_redstone_blocks",
            "stacked_spruce_logs", "stacked_spruce_planks", "stacked_stone_blocks", "stacked_stripped_acacia_logs",
            "stacked_stripped_bamboo_blocks", "stacked_stripped_birch_logs", "stacked_stripped_cherry_logs",
            "stacked_stripped_crimson_stems", "stacked_stripped_dark_oak_logs", "stacked_stripped_jungle_logs",
            "stacked_stripped_mangrove_logs", "stacked_stripped_oak_logs", "stacked_stripped_spruce_logs",
            "stacked_stripped_warped_stems", "stacked_warped_planks", "stacked_warped_stems",
            "stacked_resin_blocks", "stacked_resin_bricks", "stacked_pale_oak_logs", "stacked_stripped_pale_oak_logs"
    };

    private static final String[] CRATE_TYPES = {
            "oak_crate", "birch_crate", "spruce_crate", "jungle_crate",
            "acacia_crate", "dark_oak_crate", "mangrove_crate", "cherry_crate",
            "bamboo_crate"
    };

    private static final Set<String> AMW_PLUSHIE_NAMES = Set.of(
            "abbie_plushie", "maddie_plushie", "willow_plushie"
    );

    // ====== REGISTER HELPERS ======
    private static Block registerBasicBlock(String name, Block.Settings settings) {
        Block block = new Block(settings);
        Identifier id = Identifier.of("charmncraftbits", name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        return block;
    }

    private static void registerColoredBlock(String name, Block.Settings settings) {
        Block block = new Block(settings);
        Identifier id = Identifier.of("charmncraftbits", name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        COLORED_BLOCKS.add(block);
    }

    // ====== STACKED BLOCKS ======
    private static void registerStackedBlocks() {
        for (String blockName : STACKED_BLOCK_TYPES) {
            Block stackedBlock = new Block(
                    Block.Settings.create()
                            .strength(2.0f, 6.0f)
                            .sounds(BlockSoundGroup.WOOD)
                            .requiresTool()
            );
            registerStackedBlock(blockName, stackedBlock);
        }
    }

    private static void registerStackedBlock(String name, Block block) {
        Identifier id = Identifier.of("charmncraftbits", name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        STACKED_BLOCKS.add(block);
    }

    // ====== CRATES ======
    private static void registerCrates() {
        for (String crateName : CRATE_TYPES) {
            Block crateBlock = new Block(
                    Block.Settings.create()
                            .strength(2.0f, 3.0f)
                            .sounds(BlockSoundGroup.WOOD)
            );
            registerCrateBlock(crateName, crateBlock);
        }
    }

    private static void registerCrateBlock(String name, Block block) {
        Identifier id = Identifier.of("charmncraftbits", name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        CRATE_BLOCKS.add(block);
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
    private static void registerConsoleBlocks() {
        Map<String, Float> consoleHardness = Map.of(
                "nintendo_console", 1.5f,
                "playstation_console", 2.0f,
                "xbox_console", 2.5f
        );

        for (Map.Entry<String, Float> entry : consoleHardness.entrySet()) {
            String name = entry.getKey();
            float hardness = entry.getValue();

            Block consoleBlock = new Block(FabricBlockSettings.create()
                    .strength(hardness, hardness * 2)
                    .sounds(BlockSoundGroup.METAL));

            registerConsoleBlock(name, consoleBlock);
        }
    }

    private static void registerConsoleBlock(String name, Block block) {
        Identifier id = Identifier.of("charmncraftbits", name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        CONSOLE_BLOCKS.add(block);
    }

    // ====== DELTARUNE BLOCKS ======
    private static void registerDeltaruneBlocks() {
        String[] deltaruneProps = {
                "dark_fountain_crystal", "save_point", "card_castle_banner"
        };

        for (String name : deltaruneProps) {
            Block block = new Block(FabricBlockSettings.create()
                    .luminance(8)
                    .strength(1.5f)
                    .sounds(BlockSoundGroup.GLASS)
                    .nonOpaque());
            Identifier id = Identifier.of("charmncraftbits", name);
            Registry.register(Registries.BLOCK, id, block);
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
            DELTARUNE_BLOCKS.add(block);
        }
    }

    // ====== MAGNUM TORCHS ======
    private static void registerMagnumTorches() {
        String[] torchNames = {
                "magnum_torch_oak", "magnum_torch_birch", "magnum_torch_spruce"
        };

        for (String torch : torchNames) {
            Block torchBlock = new Block(FabricBlockSettings.create()
                    .luminance(15)
                    .strength(1.0f)
                    .sounds(BlockSoundGroup.WOOD)
                    .nonOpaque());
            Identifier id = Identifier.of("charmncraftbits", torch);
            Registry.register(Registries.BLOCK, id, torchBlock);
            Registry.register(Registries.ITEM, id, new BlockItem(torchBlock, new Item.Settings()));
            MAGNUM_TORCH_BLOCKS.add(torchBlock);
        }
    }

    // ====== COLORED BLOCK VARIANTS ======
    private static void registerColoredVariants() {
        String[] colorNames = {
                "red_bricks", "blue_bricks", "green_bricks", "yellow_bricks",
                "purple_bricks", "black_bricks", "white_bricks"
        };

        for (String color : colorNames) {
            registerColoredBlock(color, FabricBlockSettings.create()
                    .strength(1.5f, 3.0f)
                    .sounds(BlockSoundGroup.STONE));
        }
    }


    private static final VoxelShape WILLOW_PLUSHIE_SHAPE = VoxelShapes.cuboid(
            2.0f / 16f, 0f / 16f, 4.0f / 16f,
            14.5f / 16f, 10.5f / 16f, 12.5f / 16f
    );

    // ====== PLUSHIE REGISTRATION ======
    private static void registerAMWPlushies() {
        for (String name : AMW_PLUSHIE_NAMES) {
            VoxelShape shape = getAMWPlushieShape(name);
            Block plushieBlock = new Block(FabricBlockSettings.create()
                    .nonOpaque()
                    .strength(0.5f)
                    .sounds(BlockSoundGroup.WOOL)) {

                @Override
                public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                    return shape;
                }
            };

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
        Identifier id = Identifier.of("charmncraftbits", name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
    }

    // ====== TWIGS DECOR BLOCKS ======
    private static void registerTwigsBlocks() {
        String[] twigBlockNames = {
                "twig_table", "twig_chair", "twig_stool"
        };

        for (String twig : twigBlockNames) {
            Block block = new Block(FabricBlockSettings.create()
                    .strength(1.0f, 2.0f)
                    .sounds(BlockSoundGroup.WOOD));
            Identifier id = Identifier.of("charmncraftbits", twig);
            Registry.register(Registries.BLOCK, id, block);
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
            TWIGS_BLOCKS.add(block);
        }
    }

    // ====== MASTER INITIALIZER ======
    public static void initialize() {
        // Order matters only for logging / clarity, not dependency
        registerStackedBlocks();
        registerCrates();
        registerAMWPlushies();
        registerTwigsBlocks();
        registerConsoleBlocks();
        registerDeltaruneBlocks();
        registerMagnumTorches();
        registerColoredVariants();
        registerExtendedConsoles();
        registerAzaleaDecor();
        registerExtraMagnumTorches();

        System.out.println("[Charm n Craft Bits] All custom blocks registered successfully.");
    }

    // ====== DEBUG / UTILITY HELPERS ======

    public static void printRegisteredBlockCounts() {
        System.out.println("==== CharmnCraftBits Block Summary ====");
        System.out.println("Stacked: " + STACKED_BLOCKS.size());
        System.out.println("Crates: " + CRATE_BLOCKS.size());
        System.out.println("Plushies: " + AMW_PLUSHIE_NAMES.size());
        System.out.println("Twigs: " + TWIGS_BLOCKS.size());
        System.out.println("Console: " + CONSOLE_BLOCKS.size());
        System.out.println("Deltarune: " + DELTARUNE_BLOCKS.size());
        System.out.println("Magnum Torches: " + MAGNUM_TORCH_BLOCKS.size());
        System.out.println("Colored Variants: " + COLORED_BLOCKS.size());
        System.out.println("=======================================");
    }

    // ====== BLOCK RETRIEVAL HELPERS ======

    public static Block getBlockByName(String name) {
        Identifier id = Identifier.of("charmncraftbits", name);
        return Registries.BLOCK.get(id);
    }

    public static boolean isRegistered(String name) {
        Identifier id = Identifier.of("charmncraftbits", name);
        return Registries.BLOCK.containsId(id);
    }

    public static List<Block> getAllBlocks() {
        List<Block> all = new ArrayList<>();
        all.addAll(STACKED_BLOCKS);
        all.addAll(CRATE_BLOCKS);
        all.addAll(TWIGS_BLOCKS);
        all.addAll(CONSOLE_BLOCKS);
        all.addAll(DELTARUNE_BLOCKS);
        all.addAll(MAGNUM_TORCH_BLOCKS);
        all.addAll(COLORED_BLOCKS);
        return all;
    }

    // ====== EXTENDED CONSOLE SHAPES ======
    private static void registerExtendedConsoles() {
        // Dreamcast
        registerConsoleBlock("dreamcast", new Block(FabricBlockSettings.create()
                .strength(2.0f)
                .sounds(BlockSoundGroup.METAL)));

        // Nintendo DS
        registerConsoleBlock("ds", new Block(FabricBlockSettings.create()
                .strength(1.5f)
                .sounds(BlockSoundGroup.METAL)));

        // GameBoys (combined shape)
        registerConsoleBlock("gameboys", new Block(FabricBlockSettings.create()
                .strength(1.5f)
                .sounds(BlockSoundGroup.METAL)));

        // GameCube
        registerConsoleBlock("gamecube", new Block(FabricBlockSettings.create()
                .strength(2.0f)
                .sounds(BlockSoundGroup.METAL)));

        // Dock
        registerConsoleBlock("dock", new Block(FabricBlockSettings.create()
                .strength(1.5f)
                .sounds(BlockSoundGroup.METAL)));

        // Nintendo 64
        registerConsoleBlock("n_64", new Block(FabricBlockSettings.create()
                .strength(1.8f)
                .sounds(BlockSoundGroup.METAL)));

        // PS1–PS5
        registerConsoleBlock("ps_1", new Block(FabricBlockSettings.create()
                .strength(1.5f)
                .sounds(BlockSoundGroup.METAL)));

        registerConsoleBlock("ps_2", new Block(FabricBlockSettings.create()
                .strength(1.7f)
                .sounds(BlockSoundGroup.METAL)));

        registerConsoleBlock("ps_4", new Block(FabricBlockSettings.create()
                .strength(2.0f)
                .sounds(BlockSoundGroup.METAL)));

        registerConsoleBlock("ps_5", new Block(FabricBlockSettings.create()
                .strength(2.2f)
                .sounds(BlockSoundGroup.METAL)));

        // PSP
        registerConsoleBlock("psp", new Block(FabricBlockSettings.create()
                .strength(1.3f)
                .sounds(BlockSoundGroup.METAL)));

        // Sega Genesis
        registerConsoleBlock("sega_genesis", new Block(FabricBlockSettings.create()
                .strength(1.5f)
                .sounds(BlockSoundGroup.METAL)));

        // SNES
        registerConsoleBlock("snes", new Block(FabricBlockSettings.create()
                .strength(1.8f)
                .sounds(BlockSoundGroup.METAL)));

        // Switch in Dock
        registerConsoleBlock("switch_in_dock", new Block(FabricBlockSettings.create()
                .strength(1.8f)
                .sounds(BlockSoundGroup.METAL)));

        // Switch
        registerConsoleBlock("switch", new Block(FabricBlockSettings.create()
                .strength(1.6f)
                .sounds(BlockSoundGroup.METAL)));

        // TV
        registerConsoleBlock("tv", new Block(FabricBlockSettings.create()
                .strength(1.8f)
                .sounds(BlockSoundGroup.METAL)));

        // Wii
        registerConsoleBlock("wii", new Block(FabricBlockSettings.create()
                .strength(1.8f)
                .sounds(BlockSoundGroup.METAL)));

        // Xbox
        registerConsoleBlock("xbox", new Block(FabricBlockSettings.create()
                .strength(1.8f)
                .sounds(BlockSoundGroup.METAL)));

        // Xbox One
        registerConsoleBlock("xbox_1", new Block(FabricBlockSettings.create()
                .strength(2.0f)
                .sounds(BlockSoundGroup.METAL)));

        // Xbox Series S
        registerConsoleBlock("xbox_series_s", new Block(FabricBlockSettings.create()
                .strength(2.0f)
                .sounds(BlockSoundGroup.METAL)));

        // Xbox Series X
        registerConsoleBlock("xbox_series_x", new Block(FabricBlockSettings.create()
                .strength(2.2f)
                .sounds(BlockSoundGroup.METAL)));
    }

    // ====== AZALEA DECOR ======
    private static void registerAzaleaDecor() {
        Block azaleaFlowers = new Block(FabricBlockSettings.create()
                .nonOpaque()
                .strength(0.5f)
                .sounds(BlockSoundGroup.GRASS));
        Identifier azaleaId = Identifier.of("charmncraftbits", "azalea_flowers");
        Registry.register(Registries.BLOCK, azaleaId, azaleaFlowers);
        Registry.register(Registries.ITEM, azaleaId, new BlockItem(azaleaFlowers, new Item.Settings()));
        TWIGS_BLOCKS.add(azaleaFlowers);

        Block pottedAzalea = new Block(FabricBlockSettings.create()
                .nonOpaque()
                .strength(0.3f)
                .sounds(BlockSoundGroup.GRASS));
        Identifier pottedId = Identifier.of("charmncraftbits", "potted_azalea_flowers");
        Registry.register(Registries.BLOCK, pottedId, pottedAzalea);
        Registry.register(Registries.ITEM, pottedId, new BlockItem(pottedAzalea, new Item.Settings()));
        TWIGS_BLOCKS.add(pottedAzalea);
    }

    // ====== MAGNUM TORCH VARIANTS ======
    private static void registerExtraMagnumTorches() {
        registerMagnumTorchBlock("diamond_magnum_torch", 14);
        registerMagnumTorchBlock("emerald_magnum_torch", 15);
    }

    private static void registerMagnumTorchBlock(String name, int luminance) {
        Block block = new Block(FabricBlockSettings.create()
                .luminance(luminance)
                .strength(1.0f)
                .sounds(BlockSoundGroup.WOOD)
                .nonOpaque());
        Identifier id = Identifier.of("charmncraftbits", name);
        Registry.register(Registries.BLOCK, id, block);
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        MAGNUM_TORCH_BLOCKS.add(block);
    }

    // ====== FINAL INITIALIZER EXTENSION ======
    // Note: initializeExtended() is now called from initialize() to avoid duplication
}