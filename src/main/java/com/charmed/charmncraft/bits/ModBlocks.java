package com.charmed.charmncraft.bits;

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

    static {
        // Register all night light blocks with lit property
        registerColoredBlocks("frog", COLORS);
        registerColoredBlocks("mushroom", COLORS);
        registerColoredBlocks("octopus", COLORS);

        // Register all blocks to creative tab in a single callback
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register(entries -> {
                for (Block block : COLORED_BLOCKS) {
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

    private static void registerBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);
        
        // Register block
        Registry.register(Registries.BLOCK, id, block);
        
        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        
        // Add to our list for later creative tab registration
        COLORED_BLOCKS.add(block);
    }

    public static void initialize() {
        // This method is called to trigger the static block initializer
    }
}
