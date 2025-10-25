package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.blocks.PlushieBlock;
import com.charmed.charmncraft.bits.blocks.NightLightBlock;
import com.charmed.charmncraft.bits.blocks.ConsoleBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.shape.VoxelShape;
import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    // Hitbox dimension constants (in 1/16th block units)
    private static final float FROG_MIN = 5/16f;
    private static final float FROG_MAX = 11/16f;
    private static final float FROG_HEIGHT = 6/16f;

    private static final float MUSHROOM_STEM_MIN = 5/16f;
    private static final float MUSHROOM_STEM_MAX = 11/16f;
    private static final float MUSHROOM_STEM_HEIGHT = 4/16f;
    private static final float MUSHROOM_CAP_MIN = 4/16f;
    private static final float MUSHROOM_CAP_MAX = 12/16f;
    private static final float MUSHROOM_CAP_START = 3/16f;
    private static final float MUSHROOM_CAP_END = 9/16f;

    private static final float OCTOPUS_MIN = 5/16f;
    private static final float OCTOPUS_MAX = 11/16f;
    private static final float OCTOPUS_HEIGHT = 6/16f;

    // Shared color array for all decorative blocks
    private static final String[] COLORS = {
        "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "lime", "magenta", "orange",
        "pink", "purple", "red", "white", "yellow"
    };

    // Store blocks for creative tab registration
    private static final List<Block> COLORED_BLOCKS = new ArrayList<>();
    private static final List<Block> PLUSHIE_BLOCKS = new ArrayList<>();
    private static final List<Block> DELTARUNE_BLOCKS = new ArrayList<>();
    private static final List<Block> STACKED_BLOCKS = new ArrayList<>();
    private static final List<Block> CONSOLE_BLOCKS = new ArrayList<>();

    // List of all plushie types to register
    private static final String[] PLUSHIES = {
        "allay_plushie", "axolotl_plushie", "bat_plushie", "bee_plushie",
        "black_rabbit_plushie", "blaze_plushie", "brown_rabbit_plushie", "camel_plushie",
        "cat_plushie", "cave_spider_plushie", "chicken_plushie", "cod_plushie",
        "cold_frog_plushie", "cow_plushie", "creeper_plushie", "dolphin_plushie",
        "dragon_plushie", "drowned_plushie", "elder_guardian_plushie", "enderman_plushie",
        "endermite_plushie", "evoker_plushie", "frog_plushie", "ghast_plushie",
        "glow_squid_plushie", "goat_plushie", "guardian_plushie", "hoglin_plushie",
        "horse_plushie", "husk_plushie", "illusioner_plushie", "iron_golem_plushie",
        "killer_bunny_plushie", "llama_plushie", "magma_cube_plushie", "mooshroom_plushie",
        "nubert", "ocelot_plushie", "panda_plushie", "parrot_plushie", "phantom_plushie",
        "pig_plushie", "piglin_brute_plushie", "piglin_plushie", "pillager_plushie",
        "polar_bear_plushie", "pufferfish_plushie", "ravager_plushie", "red_fox_plushie",
        "salmon_plushie", "salt_rabbit_plushie", "sheep_plushie", "shulker_plushie",
        "silverfish_plushie", "skeleton_horse_plushie", "skeleton_plushie", "slime_plushie",
        "sniffer_plushie", "snow_golem_plushie", "spider_plushie", "squid_plushie",
        "stray_plushie", "strider_plushie", "tadpole_plushie", "tenna_statue", "tenna_pole", "toast_rabbit_plushie",
        "turtle_plushie", "vex_plushie", "villager_plushie", "vindicator_plushie",
        "wandering_trader_plushie", "warden_plushie", "warm_frog_plushie", "white_fox_plushie",
        "white_rabbit_plushie", "white_splotched_rabbit_plushie", "witch_plushie", "wither_plushie",
        "wither_skeleton_plushie", "wolf_plushie", "yellow_rabbit_plushie", "zoglin_plushie",
        "zombie_plushie", "zombie_villager_plushie", "zombiefied_piglin_plushie"
    };

    // List of all stacked block types to register
    private static final String[] STACKED_BLOCK_TYPES = {
        "stacked_acacia_logs", "stacked_acacia_planks", "stacked_bamboo_blocks",
        "stacked_bamboo_planks", "stacked_birch_logs", "stacked_birch_planks",
        "stacked_bricks", "stacked_cherry_logs", "stacked_cherry_planks",
        "stacked_coal_blocks", "stacked_cobblestone_blocks", "stacked_crimson_planks",
        "stacked_crimson_stems", "stacked_dark_oak_logs", "stacked_dark_oak_planks",
        "stacked_diamond_blocks", "stacked_emerald_blocks", "stacked_gold_blocks",
        "stacked_iron_blocks", "stacked_jungle_logs", "stacked_jungle_planks",
        "stacked_lapis_blocks", "stacked_mangrove_logs", "stacked_mangrove_planks",
        "stacked_melons", "stacked_netherite_blocks", "stacked_netherrack_blocks",
        "stacked_oak_logs", "stacked_oak_planks", "stacked_organic_compost",
        "stacked_pale_oak_logs", "stacked_pale_oak_planks", "stacked_pumpkins",
        "stacked_quartz_blocks", "stacked_raw_copper_blocks", "stacked_raw_gold_blocks",
        "stacked_raw_iron_blocks", "stacked_redstone_blocks", "stacked_resin_blocks",
        "stacked_resin_bricks", "stacked_spruce_logs", "stacked_spruce_planks",
        "stacked_stone_blocks", "stacked_stripped_acacia_logs", "stacked_stripped_bamboo_blocks",
        "stacked_stripped_birch_logs", "stacked_stripped_cherry_logs", "stacked_stripped_crimson_stems",
        "stacked_stripped_dark_oak_logs", "stacked_stripped_jungle_logs", "stacked_stripped_mangrove_logs",
        "stacked_stripped_oak_logs", "stacked_stripped_pale_oak_logs", "stacked_stripped_spruce_logs",
        "stacked_stripped_warped_stems", "stacked_warped_planks", "stacked_warped_stems"
    };

    // Nubert hitbox - custom shape based on the model
    private static final VoxelShape NUBERT_SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(2/16f, 0, 2/16f, 14/16f, 1/16f, 14/16f),  // Base
        VoxelShapes.cuboid(3/16f, 1/16f, 3/16f, 13/16f, 8/16f, 13/16f),  // Body
        VoxelShapes.cuboid(4/16f, 8/16f, 4/16f, 12/16f, 9/16f, 12/16f)   // Top
    );

    // Tenna pole hitbox - custom shape based on the model
    private static final VoxelShape TENNA_POLE_SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(7/16f, 0, 7/16f, 9/16f, 9/16f, 9/16f),  // Stick base
        VoxelShapes.cuboid(6/16f, 9/16f, 6/16f, 10/16f, 10/16f, 10/16f),  // Connection
        VoxelShapes.cuboid(5/16f, 10/16f, 6/16f, 11/16f, 14/16f, 10/16f),  // Main antenna body
        VoxelShapes.cuboid(5/16f, 14/16f, 6/16f, 11/16f, 18/16f, 10/16f)   // Antenna top
    );

    // Tenna statue hitbox - custom shape based on the model
    private static final VoxelShape TENNA_STATUE_SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(3/16f, 0, 3/16f, 13/16f, 2/16f, 13/16f),  // Base platform
        VoxelShapes.cuboid(7/16f, 0, 5/16f, 10/16f, 3/16f, 11/16f),   // Feet
        VoxelShapes.cuboid(7/16f, 3/16f, 6/16f, 10/16f, 13/16f, 10/16f),  // Lower body/stick
        VoxelShapes.cuboid(0/16f, 14/16f, 1/16f, 8/16f, 18/16f, 6/16f),   // Left arm extended
        VoxelShapes.cuboid(6/16f, 13/16f, 4/16f, 11/16f, 21/16f, 12/16f), // Main body
        VoxelShapes.cuboid(6/16f, 21/16f, 4/16f, 11/16f, 29/16f, 16/16f), // Upper body and head
        VoxelShapes.cuboid(6/16f, 26/16f, 13/16f, 10/16f, 32/16f, 16/16f) // Antenna extending back
    );

    static {
        // Register all night light blocks with lit property
        registerColoredBlocks("frog", COLORS);
        registerColoredBlocks("mushroom", COLORS);
        registerColoredBlocks("octopus", COLORS);

        // Register all plushie blocks
        registerPlushies();

        // Register all stacked blocks
        registerStackedBlocks();

        // Register console blocks (starting with NES as test)
        registerConsoles();

        // Register Night Lights to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.NIGHT_LIGHTS_KEY)
            .register(entries -> {
                for (Block block : COLORED_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Plushies to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.PLUSHIES_KEY)
            .register(entries -> {
                for (Block block : PLUSHIE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Deltarune Doodads to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.DELTARUNE_DOODADS_KEY)
            .register(entries -> {
                for (Block block : DELTARUNE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Stacked Blocks to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.STACKED_BLOCKS_KEY)
            .register(entries -> {
                for (Block block : STACKED_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register Consoles to custom creative tab
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CONSOLES_KEY)
            .register(entries -> {
                for (Block block : CONSOLE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });
    }

    private static void registerColoredBlocks(String baseName, String[] colors) {
        for (String color : colors) {
            String blockName = baseName + "_" + color;
            VoxelShape shape = getShapeForBlockType(baseName);

            // Create night light block with glowstone-level lighting (15) when lit
            Block block = new NightLightBlock(
                Block.Settings.create()
                    .strength(0.8f, 0.8f)
                    .sounds(BlockSoundGroup.WOOL)
                    .luminance(state -> state.get(NightLightBlock.LIT) ? 7 : 0),
                shape
            );

            registerBlock(blockName, block);
        }
    }

    private static VoxelShape getShapeForBlockType(String baseName) {
        switch (baseName) {
            case "frog":
                // Frog: 6x6x6 centered cube for body
                return VoxelShapes.cuboid(FROG_MIN, 0, FROG_MIN, FROG_MAX, FROG_HEIGHT, FROG_MAX);

            case "mushroom":
                // Mushroom: vertical stem + wider cap
                VoxelShape stem = VoxelShapes.cuboid(
                    MUSHROOM_STEM_MIN, 0, MUSHROOM_STEM_MIN,
                    MUSHROOM_STEM_MAX, MUSHROOM_STEM_HEIGHT, MUSHROOM_STEM_MAX
                );
                VoxelShape cap = VoxelShapes.cuboid(
                    MUSHROOM_CAP_MIN, MUSHROOM_CAP_START, MUSHROOM_CAP_MIN,
                    MUSHROOM_CAP_MAX, MUSHROOM_CAP_END, MUSHROOM_CAP_MAX
                );
                return VoxelShapes.union(stem, cap);

            case "octopus":
                // Octopus: 6x6x6 centered cube for body
                return VoxelShapes.cuboid(OCTOPUS_MIN, 0, OCTOPUS_MIN, OCTOPUS_MAX, OCTOPUS_HEIGHT, OCTOPUS_MAX);

            default:
                return VoxelShapes.fullCube();
        }
    }

    private static void registerPlushies() {
        for (String plushieName : PLUSHIES) {
            // Use custom shapes for special blocks, default for others
            VoxelShape shape = null;
            if (plushieName.equals("nubert")) {
                shape = NUBERT_SHAPE;
            } else if (plushieName.equals("tenna_pole")) {
                shape = TENNA_POLE_SHAPE;
            } else if (plushieName.equals("tenna_statue")) {
                shape = TENNA_STATUE_SHAPE;
            }

            // Create plushie block with wool sounds and waterlogging
            Block plushieBlock;
            if (shape != null) {
                plushieBlock = new PlushieBlock(
                    Block.Settings.create()
                        .strength(0.7f, 0.7f)
                        .sounds(BlockSoundGroup.WOOL)
                        .nonOpaque(), // Prevent face culling on blocks below
                    shape
                );
            } else {
                plushieBlock = new PlushieBlock(
                    Block.Settings.create()
                        .strength(0.7f, 0.7f)
                        .sounds(BlockSoundGroup.WOOL)
                        .nonOpaque() // Prevent face culling on blocks below
                );
            }

            registerPlushieBlock(plushieName, plushieBlock);
        }
    }

    private static void registerStackedBlocks() {
        for (String blockName : STACKED_BLOCK_TYPES) {
            // Create simple block with stone properties
            Block stackedBlock = new Block(
                Block.Settings.create()
                    .strength(2.0f, 6.0f)
                    .sounds(BlockSoundGroup.WOOD)
                    .requiresTool()
            );

            registerStackedBlock(blockName, stackedBlock);
        }
    }

    private static void registerBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to our list for later creative tab registration
        COLORED_BLOCKS.add(block);
    }

    private static void registerPlushieBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to appropriate list for creative tab registration
        if (name.equals("nubert") || name.equals("tenna_pole") || name.equals("tenna_statue")) {
            DELTARUNE_BLOCKS.add(block);
        } else {
            PLUSHIE_BLOCKS.add(block);
        }
    }

    private static void registerStackedBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to stacked blocks list for creative tab registration
        STACKED_BLOCKS.add(block);
    }

    private static void registerConsoles() {
        // NES console hitbox (14x4x11 pixels)
        registerConsoleBlock("nes", VoxelShapes.cuboid(1/16f, 0, 2/16f, 15/16f, 4/16f, 13/16f));

        // Dreamcast (13x4x12 pixels)
        registerConsoleBlock("dreamcast", VoxelShapes.cuboid(1.5f/16f, 0, 2/16f, 14.5f/16f, 4/16f, 14/16f));

        // DS (9x1x6 pixels)
        registerConsoleBlock("ds", VoxelShapes.cuboid(5/16f, 0, 2.8f/16f, 14/16f, 1/16f, 8.8f/16f));

        // Gameboys (2 gameboys - compound shape)
        VoxelShape gameboysShape = VoxelShapes.union(
            VoxelShapes.cuboid(9/16f, 0, 1.8f/16f, 15/16f, 2/16f, 11.8f/16f),      // Right gameboy
            VoxelShapes.cuboid(1/16f, 0, 1.8f/16f, 8/16f, 2/16f, 12.8f/16f)        // Left gameboy
        );
        registerConsoleBlock("gameboys", gameboysShape);

        // GameCube (11x8x11 pixels)
        registerConsoleBlock("gamecube", VoxelShapes.cuboid(2.5f/16f, 0, 2.5f/16f, 13.5f/16f, 8/16f, 13.5f/16f));

        // Switch Dock (13x9x1 pixels)
        registerConsoleBlock("dock", VoxelShapes.cuboid(1.5f/16f, 0, 6/16f, 14.5f/16f, 9/16f, 7/16f));

        // N64 (4 parts: left controller + console body + right controller + top)
        VoxelShape n64Shape = VoxelShapes.union(
            VoxelShapes.cuboid(0, 0, 1.49f/16f, 3.01f/16f, 2.01f/16f, 5.51f/16f),       // Left controller (clamped from -0.01)
            VoxelShapes.cuboid(1.5f/16f, 0, 3/16f, 14.5f/16f, 3/16f, 14/16f),           // Main console body
            VoxelShapes.cuboid(12.99f/16f, 0, 1.49f/16f, 1.0f, 2.01f/16f, 5.51f/16f),   // Right controller (clamped from 16.01)
            VoxelShapes.cuboid(4.5f/16f, 3/16f, 9/16f, 11.5f/16f, 6/16f, 11/16f)        // Top part
        );
        registerConsoleBlock("n_64", n64Shape);

        // PS1 (13x2x9 pixels)
        registerConsoleBlock("ps_1", VoxelShapes.cuboid(1.5f/16f, 0, 3.5f/16f, 14.5f/16f, 2/16f, 12.5f/16f));

        // PS2 (15x2x10 pixels)
        registerConsoleBlock("ps_2", VoxelShapes.cuboid(0.5f/16f, 2/16f, 3/16f, 15.5f/16f, 4/16f, 13/16f));

        // PS4 (15x1x12 pixels)
        registerConsoleBlock("ps_4", VoxelShapes.cuboid(0.5f/16f, 3/16f, 2/16f, 15.5f/16f, 4/16f, 14/16f));

        // PS5 (4 parts - tall vertical console - extends above one block!)
        VoxelShape ps5Shape = VoxelShapes.union(
            VoxelShapes.cuboid(6.4848f/16f, 1.4467f/16f, 1.2322f/16f, 9.8384f/16f, 24.0835f/16f, 13.8082f/16f),   // Center
            VoxelShapes.cuboid(5.6464f/16f, 1.2371f/16f, 0.3938f/16f, 6.4848f/16f, 25.5507f/16f, 14.6466f/16f),   // Left side (tallest)
            VoxelShapes.cuboid(9.8384f/16f, 1.2371f/16f, 0.3938f/16f, 10.6768f/16f, 25.5507f/16f, 14.6466f/16f),  // Right side (tallest)
            VoxelShapes.cuboid(4.808f/16f, 0.3987f/16f, 4.5858f/16f, 11.5152f/16f, 2.0755f/16f, 11.293f/16f)      // Base
        );
        registerConsoleBlock("ps_5", ps5Shape);

        // PSP (10x2x6 pixels)
        registerConsoleBlock("psp", VoxelShapes.cuboid(3/16f, 0, 5/16f, 13/16f, 2/16f, 11/16f));

        // Sega Genesis (12x2x10 pixels)
        registerConsoleBlock("sega_genesis", VoxelShapes.cuboid(2/16f, 0, 3/16f, 14/16f, 2/16f, 13/16f));

        // SNES (10x3x12 pixels)
        registerConsoleBlock("snes", VoxelShapes.cuboid(3.5f/16f, 0, 2/16f, 13.5f/16f, 3/16f, 14/16f));

        // Switch In Dock (6 parts: dock base + switch tablet + various components)
        VoxelShape switchInDockShape = VoxelShapes.union(
            VoxelShapes.cuboid(0, 2/16f, 7/16f, 1/16f, 10/16f, 8/16f),               // Left side (clamped from -2)
            VoxelShapes.cuboid(1/16f, 0, 6/16f, 14/16f, 9/16f, 7/16f),               // Dock base front
            VoxelShapes.cuboid(14/16f, 2/16f, 7/16f, 1.0f, 10/16f, 8/16f),           // Right side (clamped from 17)
            VoxelShapes.cuboid(1/16f, 2/16f, 7/16f, 14/16f, 10/16f, 8/16f),          // Center tablet
            VoxelShapes.cuboid(1/16f, 0, 8/16f, 14/16f, 9/16f, 10/16f),              // Dock base back
            VoxelShapes.cuboid(1/16f, 0, 7/16f, 14/16f, 2/16f, 8/16f)                // Bottom connector
        );
        registerConsoleBlock("switch_in_dock", switchInDockShape);

        // Switch (3 parts: left joy-con + center tablet + right joy-con)
        VoxelShape switchShape = VoxelShapes.union(
            VoxelShapes.cuboid(0, 0, 6/16f, 2/16f, 1/16f, 14/16f),           // Left joy-con (clamped from -1)
            VoxelShapes.cuboid(2/16f, 0, 6/16f, 14/16f, 1/16f, 14/16f),      // Center tablet
            VoxelShapes.cuboid(14/16f, 0, 6/16f, 1.0f, 1/16f, 14/16f)        // Right joy-con (clamped from 17)
        );
        registerConsoleBlock("switcsh", switchShape);

        // TV (15x11x8 pixels)
        registerConsoleBlock("tv", VoxelShapes.cuboid(0.5f/16f, 0, 4/16f, 15.5f/16f, 11/16f, 12/16f));

        // Wii (base + vertical stand - compound shape)
        VoxelShape wiiShape = VoxelShapes.union(
            VoxelShapes.cuboid(6.5f/16f, 0, 0.75f/16f, 11.5f/16f, 3/16f, 13.75f/16f),      // Base
            VoxelShapes.cuboid(7/16f, 2.4f/16f, 1.7f/16f, 11/16f, 13.4f/16f, 12.7f/16f)   // Vertical stand
        );
        registerConsoleBlock("wii", wiiShape);

        // Original Xbox (16x6x15 pixels, clamped)
        registerConsoleBlock("xbox", VoxelShapes.cuboid(0, 0, 0.5f/16f, 1.0f, 6/16f, 15.5f/16f));

        // Xbox One (2 parts: top + base)
        VoxelShape xboxOneShape = VoxelShapes.union(
            VoxelShapes.cuboid(0, 1/16f, 2/16f, 1.0f, 4/16f, 15/16f),        // Top (clamped from 17)
            VoxelShapes.cuboid(0, 0, 2/16f, 1.0f, 1/16f, 14/16f)             // Base (clamped from 16)
        );
        registerConsoleBlock("xbox_1", xboxOneShape);

        // Xbox Series S (4x19x11 pixels - tall vertical tower)
        registerConsoleBlock("xbox_series_s", VoxelShapes.cuboid(1/16f, 0, 2.5f/16f, 5/16f, 19/16f, 13.5f/16f));

        // Xbox Series X (2 parts - tall vertical tower with top vent - extends above one block!)
        VoxelShape xboxSeriesXShape = VoxelShapes.union(
            VoxelShapes.cuboid(2.5f/16f, 0, 2.5f/16f, 13.5f/16f, 21/16f, 13.5f/16f),         // Main tower
            VoxelShapes.cuboid(2.6f/16f, 20.05f/16f, 2.6f/16f, 13.4f/16f, 20.85f/16f, 13.4f/16f)  // Top vent
        );
        registerConsoleBlock("xbox_series_x", xboxSeriesXShape);
    }

    private static void registerConsoleBlock(String name, VoxelShape shape) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);

        // Create console block with custom hitbox
        Block block = new ConsoleBlock(
            Block.Settings.create()
                .strength(1.5f, 6.0f)
                .sounds(BlockSoundGroup.METAL)
                .nonOpaque(),
            shape
        );

        // Register block
        Registry.register(Registries.BLOCK, id, block);

        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        // Add to console blocks list for creative tab registration
        CONSOLE_BLOCKS.add(block);
    }

    public static void initialize() {
        // This method is called to trigger the static block initializer
    }
}
