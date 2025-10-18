package com.charmed.charmncraft.bits;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    // Color arrays for each block type
    private static final String[] FAIRY_LIGHTS_COLORS = {
        "default", "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "light_green", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
    };

    private static final String[] HANGING_LIGHTS_COLORS = {
        "default", "black", "blue", "brown", "cyan", "gray", "green",
        "light_blue", "light_gray", "light_green", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
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

    static {
        registerColoredBlocks("fairy_lights", FAIRY_LIGHTS_COLORS);
        registerColoredBlocks("hanging_lights", HANGING_LIGHTS_COLORS);
        registerColoredBlocks("frog", FROG_COLORS);
        registerColoredBlocks("mushroom", MUSHROOM_COLORS);
        registerColoredBlocks("octopus", OCTOPUS_COLORS);
    }

    private static void registerColoredBlocks(String baseName, String[] colors) {
        for (String color : colors) {
            String blockName = baseName + "_" + color;
            Block block = new Block(Block.Settings.create()
                .strength(0.8f, 0.8f)
                .sounds(net.minecraft.sound.BlockSoundGroup.WOOL));
            
            registerBlock(blockName, block);
        }
    }

    private static void registerBlock(String name, Block block) {
        Identifier id = Identifier.of(Charmncraftbits.MOD_ID, name);
        
        // Register block
        Registry.register(Registries.BLOCK, id, block);
        
        // Register block item
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        
        // Add to Building Blocks creative tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register(entries -> entries.add(block.asItem()));
    }

    public static void initialize() {
        // This method is called to trigger the static block initializer
    }
}
