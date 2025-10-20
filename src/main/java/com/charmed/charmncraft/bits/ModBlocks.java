package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.blocks.SmallDecorativeBlock;
import com.charmed.charmncraft.bits.blocks.SmallLitDecorativeBlock;
import com.charmed.charmncraft.bits.blocks.WallMountedLightBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.util.shape.VoxelShape;
import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    // Color arrays for each block type
    private static final String[] FAIRY_LIGHTS_COLORS = {
        "default", "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
    };

    private static final String[] HANGING_LIGHTS_COLORS = {
        "default", "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
    };

    private static final String[] FROG_COLORS = {
        "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
    };

    private static final String[] MUSHROOM_COLORS = {
        "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
    };

    private static final String[] OCTOPUS_COLORS = {
        "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
    };

    // Store blocks for creative tab registration
    private static final List<Block> COLORED_BLOCKS = new ArrayList<>();

    static {
        // Non-interactive decorative blocks
        registerColoredBlocks("fairy_lights", FAIRY_LIGHTS_COLORS, "facing");
        registerColoredBlocks("hanging_lights", HANGING_LIGHTS_COLORS, "facing");
        
        // Interactive night light blocks (use lit property)
        registerColoredBlocks("frog", FROG_COLORS, "lit");
        registerColoredBlocks("mushroom", MUSHROOM_COLORS, "lit");
        registerColoredBlocks("octopus", OCTOPUS_COLORS, "lit");
        
        // Register all blocks to creative tab in a single callback
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register(entries -> {
                for (Block block : COLORED_BLOCKS) {
                    entries.add(block.asItem());
                }
            });
    }

    private static void registerColoredBlocks(String baseName, String[] colors, String propertyType) {
        for (String color : colors) {
            String blockName = baseName + "_" + color;
            Block block;
            VoxelShape shape = getShapeForBlockType(baseName);

            if ("facing".equals(propertyType)) {
                // Wall-mounted blocks (hanging lights, fairy lights)
                block = new WallMountedLightBlock(Block.Settings.create()
                    .strength(0.8f, 0.8f)
                    .sounds(net.minecraft.sound.BlockSoundGroup.WOOL), shape);
            } else if ("lit".equals(propertyType)) {
                // Blocks with lit property (frog, mushroom, octopus)
                block = new SmallLitDecorativeBlock(Block.Settings.create()
                    .strength(0.8f, 0.8f)
                    .sounds(net.minecraft.sound.BlockSoundGroup.WOOL), shape);
            } else {
                block = new Block(Block.Settings.create()
                    .strength(0.8f, 0.8f)
                    .sounds(net.minecraft.sound.BlockSoundGroup.WOOL));
            }

            registerBlock(blockName, block);
        }
    }

    private static VoxelShape getShapeForBlockType(String baseName) {
        switch (baseName) {
            case "hanging_lights":
                // Wall-mounted hanging lights - thin layer against the wall
                return VoxelShapes.cuboid(0.25, 0.375, 0.875, 0.75, 1.0, 1.0);

            case "fairy_lights":
                // Wall-mounted fairy lights - thin layer with wider coverage
                return VoxelShapes.cuboid(0.0, 0.0, 0.875, 1.0, 1.0, 1.0);

            case "frog":
                // Frog: body [5, 0, 5] to [11, 6, 11] = 6x6x6 cube
                return VoxelShapes.cuboid(5/16f, 0/16f, 5/16f, 11/16f, 6/16f, 11/16f);
            
            case "mushroom":
                // Mushroom: stem [5, 0, 5] to [11, 4, 11] + cap [4, 3, 4] to [12, 9, 12]
                VoxelShape stem = VoxelShapes.cuboid(5/16f, 0/16f, 5/16f, 11/16f, 4/16f, 11/16f);
                VoxelShape cap = VoxelShapes.cuboid(4/16f, 3/16f, 4/16f, 12/16f, 9/16f, 12/16f);
                return VoxelShapes.union(stem, cap);
            
            case "octopus":
                // Octopus: body [5, 0, 5] to [11, 6, 11] = 6x6x6 cube
                return VoxelShapes.cuboid(5/16f, 0/16f, 5/16f, 11/16f, 6/16f, 11/16f);
            
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
