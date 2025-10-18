package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.blocks.InteractiveNightLightBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
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
        registerColoredBlocks("fairy_lights", FAIRY_LIGHTS_COLORS, false);
        registerColoredBlocks("hanging_lights", HANGING_LIGHTS_COLORS, false);
        
        // Interactive night light blocks
        registerColoredBlocks("frog", FROG_COLORS, true);
        registerColoredBlocks("mushroom", MUSHROOM_COLORS, true);
        registerColoredBlocks("octopus", OCTOPUS_COLORS, true);
        
        // Register all blocks to creative tab in a single callback
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
            .register(entries -> {
                for (Block block : COLORED_BLOCKS) {
                    entries.add(block.asItem());
                }
            });
    }

    private static void registerColoredBlocks(String baseName, String[] colors, boolean interactive) {
        for (String color : colors) {
            String blockName = baseName + "_" + color;
            Block block;
            
            if (interactive) {
                // Use interactive block class with state properties
                block = new InteractiveNightLightBlock(Block.Settings.create()
                    .strength(0.8f, 0.8f)
                    .sounds(net.minecraft.sound.BlockSoundGroup.WOOL));
            } else {
                // Use basic decorative block
                block = new Block(Block.Settings.create()
                    .strength(0.8f, 0.8f)
                    .sounds(net.minecraft.sound.BlockSoundGroup.WOOL));
            }
            
            registerBlock(blockName, block);
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
