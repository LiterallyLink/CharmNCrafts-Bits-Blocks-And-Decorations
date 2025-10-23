package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.config.SlabVariantConfig;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModSlabs {
    private static final Logger LOGGER = LoggerFactory.getLogger("ModSlabs");
    private static final Map<String, SlabBlock> SLAB_BLOCKS = new HashMap<>();
    private static final List<SlabBlock> SLAB_LIST = new ArrayList<>();

    // Custom Creative Tab
    public static final RegistryKey<ItemGroup> NEW_SLAB_VARIANTS_GROUP = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "new_slab_variants")
    );

    public static final ItemGroup NEW_SLAB_VARIANTS = FabricItemGroup.builder()
            .icon(() -> {
                // Use the first slab as the icon, or oak_log_slab if available
                if (SLAB_BLOCKS.containsKey("oak_log_slab")) {
                    return new ItemStack(SLAB_BLOCKS.get("oak_log_slab"));
                } else if (!SLAB_LIST.isEmpty()) {
                    return new ItemStack(SLAB_LIST.get(0));
                }
                return new ItemStack(Blocks.STONE_SLAB);
            })
            .displayName(Text.translatable("itemGroup.charmncraft-bits.new_slab_variants"))
            .build();

    public static void registerSlabs() {
        LOGGER.info("Loading slab variants from configuration...");

        // Load configuration
        SlabVariantConfig config = SlabVariantConfig.load();

        // Register the custom creative tab first
        Registry.register(Registries.ITEM_GROUP, NEW_SLAB_VARIANTS_GROUP, NEW_SLAB_VARIANTS);

        // Register each slab from the configuration
        for (SlabVariantConfig.SlabVariantEntry entry : config.getSlabs()) {
            registerSlab(entry);
        }

        // Add all slabs to the custom creative tab
        ItemGroupEvents.modifyEntriesEvent(NEW_SLAB_VARIANTS_GROUP).register(content -> {
            for (SlabBlock slab : SLAB_LIST) {
                content.add(slab);
            }
        });

        LOGGER.info("Registered {} slab variants", SLAB_BLOCKS.size());
    }

    private static void registerSlab(SlabVariantConfig.SlabVariantEntry entry) {
        try {
            // Get the base block to copy properties from
            Block baseBlock = getBaseBlock(entry.getBaseBlock());

            if (baseBlock == null) {
                LOGGER.error("Base block not found: {}", entry.getBaseBlock());
                return;
            }

            // Create the slab block with properties copied from base block
            SlabBlock slabBlock = new SlabBlock(AbstractBlock.Settings.copy(baseBlock));

            // Register the block
            Identifier slabId = new Identifier(Charmncraftbits.MOD_ID, entry.getSlabId());
            Registry.register(Registries.BLOCK, slabId, slabBlock);

            // Register the item
            BlockItem slabItem = new BlockItem(slabBlock, new FabricItemSettings());
            Registry.register(Registries.ITEM, slabId, slabItem);

            // Store in maps
            SLAB_BLOCKS.put(entry.getSlabId(), slabBlock);
            SLAB_LIST.add(slabBlock);

            LOGGER.info("Registered slab: {}", entry.getSlabId());

        } catch (Exception e) {
            LOGGER.error("Failed to register slab: {}", entry.getName(), e);
        }
    }

    private static Block getBaseBlock(String blockId) {
        try {
            String[] parts = blockId.split(":");
            String namespace = parts[0];
            String path = parts[1];

            Identifier id = new Identifier(namespace, path);
            return Registries.BLOCK.get(id);

        } catch (Exception e) {
            LOGGER.error("Failed to parse block ID: {}", blockId, e);
            return null;
        }
    }

    public static Map<String, SlabBlock> getSlabBlocks() {
        return SLAB_BLOCKS;
    }

    public static SlabVariantConfig.SlabVariantEntry getEntryForSlab(String slabId) {
        SlabVariantConfig config = SlabVariantConfig.load();
        for (SlabVariantConfig.SlabVariantEntry entry : config.getSlabs()) {
            if (entry.getSlabId().equals(slabId)) {
                return entry;
            }
        }
        return null;
    }
}
