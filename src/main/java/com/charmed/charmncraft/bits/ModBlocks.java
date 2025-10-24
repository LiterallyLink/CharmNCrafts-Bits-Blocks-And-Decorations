package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.blocks.PlushieBlock;
import com.charmed.charmncraft.bits.blocks.SmallLitDecorativeBlock;
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
        "nubert_plushie", "ocelot_plushie", "panda_plushie", "parrot_plushie", "phantom_plushie",
        "pig_plushie", "piglin_brute_plushie", "piglin_plushie", "pillager_plushie",
        "polar_bear_plushie", "pufferfish_plushie", "ravager_plushie", "red_fox_plushie",
        "salmon_plushie", "salt_rabbit_plushie", "sheep_plushie", "shulker_plushie",
        "silverfish_plushie", "skeleton_horse_plushie", "skeleton_plushie", "slime_plushie",
        "sniffer_plushie", "snow_golem_plushie", "spider_plushie", "squid_plushie",
        "stray_plushie", "strider_plushie", "tadpole_plushie", "tenna_stick", "toast_rabbit_plushie",
        "turtle_plushie", "vex_plushie", "villager_plushie", "vindicator_plushie",
        "wandering_trader_plushie", "warden_plushie", "warm_frog_plushie", "white_fox_plushie",
        "white_rabbit_plushie", "white_splotched_rabbit_plushie", "witch_plushie", "wither_plushie",
        "wither_skeleton_plushie", "wolf_plushie", "yellow_rabbit_plushie", "zoglin_plushie",
        "zombie_plushie", "zombie_villager_plushie", "zombiefied_piglin_plushie"
    };

    // Nubert plushie hitbox - custom shape based on the model
    private static final VoxelShape NUBERT_SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(2/16f, 0, 2/16f, 14/16f, 1/16f, 14/16f),  // Base
        VoxelShapes.cuboid(3/16f, 1/16f, 3/16f, 13/16f, 8/16f, 13/16f),  // Body
        VoxelShapes.cuboid(4/16f, 8/16f, 4/16f, 12/16f, 9/16f, 12/16f)   // Top
    );

    // Tenna stick hitbox - custom shape based on the model
    private static final VoxelShape TENNA_STICK_SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(7/16f, 0, 7/16f, 9/16f, 9/16f, 9/16f),  // Stick base
        VoxelShapes.cuboid(6/16f, 9/16f, 6/16f, 10/16f, 10/16f, 10/16f),  // Connection
        VoxelShapes.cuboid(5/16f, 10/16f, 6/16f, 11/16f, 14/16f, 10/16f),  // Main antenna body
        VoxelShapes.cuboid(5/16f, 14/16f, 6/16f, 11/16f, 18/16f, 10/16f)   // Antenna top
    );

    static {
        // Register all night light blocks with lit property
        registerColoredBlocks("frog", COLORS);
        registerColoredBlocks("mushroom", COLORS);
        registerColoredBlocks("octopus", COLORS);

        // Register all plushie blocks
        registerPlushies();

        // Register all blocks to creative tab in a single callback
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register(entries -> {
                for (Block block : COLORED_BLOCKS) {
                    entries.add(block.asItem());
                }
            });

        // Register plushies to their own creative tab section
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register(entries -> {
                for (Block block : PLUSHIE_BLOCKS) {
                    entries.add(block.asItem());
                }
            });
    }

    private static void registerColoredBlocks(String baseName, String[] colors) {
        for (String color : colors) {
            String blockName = baseName + "_" + color;
            VoxelShape shape = getShapeForBlockType(baseName);

            // Create night light block with glowstone-level lighting (15) when lit
            Block block = new SmallLitDecorativeBlock(
                Block.Settings.create()
                    .strength(0.8f, 0.8f)
                    .sounds(BlockSoundGroup.WOOL)
                    .luminance(state -> state.get(SmallLitDecorativeBlock.LIT) ? 7 : 0),
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
            if (plushieName.equals("nubert_plushie")) {
                shape = NUBERT_SHAPE;
            } else if (plushieName.equals("tenna_stick")) {
                shape = TENNA_STICK_SHAPE;
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

        // Add to plushie list for creative tab registration
        PLUSHIE_BLOCKS.add(block);
    }

    public static void initialize() {
        // This method is called to trigger the static block initializer
    }
}
