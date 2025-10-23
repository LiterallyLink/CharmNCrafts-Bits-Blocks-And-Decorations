package com.charmed.charmncraft.bits;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModSlabs {
    private static final List<SlabBlock> SLABS = new ArrayList<>();
    public static ItemGroup SLAB_GROUP;

    public static void initialize() {
        Charmncraftbits.LOGGER.info("Initializing slab generation system...");

        // Load configuration
        SlabVariantConfig config = SlabVariantConfig.load();

        // Create custom creative tab
        createCreativeTab();

        // Register slabs
        for (SlabVariantConfig.SlabVariant variant : config.getSlabs()) {
            registerSlab(variant);
        }

        // Generate all data files - DISABLED FOR TESTING
        // Files are now pre-generated and stored in resources
        // generateAllData(config);

        Charmncraftbits.LOGGER.info("Slab generation system initialized with {} slabs", SLABS.size());
    }

    private static void createCreativeTab() {
        SLAB_GROUP = FabricItemGroup.builder()
                .icon(() -> SLABS.isEmpty() ? ItemStack.EMPTY : new ItemStack(SLABS.get(0)))
                .displayName(Text.translatable("itemGroup.charmncraft-bits.more_slab_variants"))
                .entries((displayContext, entries) -> {
                    // Add all registered slabs to the creative tab
                    for (SlabBlock slab : SLABS) {
                        entries.add(slab);
                    }
                })
                .build();

        Registry.register(Registries.ITEM_GROUP,
            new Identifier(Charmncraftbits.MOD_ID, "more_slab_variants"),
            SLAB_GROUP);
    }

    private static void registerSlab(SlabVariantConfig.SlabVariant variant) {
        String slabName = variant.getName() + "_slab";
        Identifier id = new Identifier(Charmncraftbits.MOD_ID, slabName);

        // Get base block to reference for settings
        Block baseBlock = getBaseBlock(variant.getBaseBlock());
        if (baseBlock == null || baseBlock == Registries.BLOCK.get(new Identifier("minecraft:air"))) {
            Charmncraftbits.LOGGER.error("Could not find valid base block: " + variant.getBaseBlock());
            return;
        }

        // Create slab with settings that only copy material properties, not blockstate properties
        // Using create() instead of copy() to avoid copying axis and other blockstate properties
        Block.Settings settings = Block.Settings.create()
                .strength(baseBlock.getHardness(), baseBlock.getBlastResistance())
                .sounds(baseBlock.getSoundGroup(baseBlock.getDefaultState()))
                .requiresTool();

        // Copy luminance if the base block has it
        if (baseBlock.getDefaultState().getLuminance() > 0) {
            final int luminance = baseBlock.getDefaultState().getLuminance();
            settings.luminance(state -> luminance);
        }

        SlabBlock slab = new SlabBlock(settings);

        // Register block and item
        Registry.register(Registries.BLOCK, id, slab);
        BlockItem blockItem = new BlockItem(slab, new FabricItemSettings());
        Registry.register(Registries.ITEM, id, blockItem);

        SLABS.add(slab);
        Charmncraftbits.LOGGER.info("Registered slab: {}", slabName);
    }

    private static Block getBaseBlock(String blockId) {
        try {
            Identifier blockIdentifier = new Identifier(blockId);
            Block block = Registries.BLOCK.get(blockIdentifier);

            // Verify it's not air (which is the default when a block isn't found)
            if (block != null && !blockIdentifier.equals(Registries.BLOCK.getId(block))) {
                Charmncraftbits.LOGGER.warn("Block {} resolved to different ID: {}",
                    blockId, Registries.BLOCK.getId(block));
            }

            return block;
        } catch (Exception e) {
            Charmncraftbits.LOGGER.error("Failed to parse block ID: " + blockId, e);
            return null;
        }
    }

    private static void generateAllData(SlabVariantConfig config) {
        try {
            Path basePath = FabricLoader.getInstance().getGameDir().getParent()
                    .resolve("src/main/resources");

            Charmncraftbits.LOGGER.info("Generating data files to: {}", basePath.toAbsolutePath());

            // Verify base path exists or can be created
            if (!Files.exists(basePath)) {
                Charmncraftbits.LOGGER.warn("Base resource path does not exist: {}. Files will be created but may not load until rebuild.", basePath);
            }

            // Generate all data files for each slab
            int successCount = 0;
            for (SlabVariantConfig.SlabVariant variant : config.getSlabs()) {
                String slabName = variant.getName() + "_slab";
                String baseBlock = variant.getBaseBlock();
                String baseBlockPath = baseBlock.replace("minecraft:", "");

                try {
                    generateBlockstate(basePath, slabName);
                    generateModels(basePath, slabName, baseBlockPath);
                    generateRecipe(basePath, slabName, baseBlock);
                    generateLootTable(basePath, slabName);
                    successCount++;
                    Charmncraftbits.LOGGER.info("Generated data for: {}", slabName);
                } catch (Exception e) {
                    Charmncraftbits.LOGGER.error("Failed to generate data for {}", slabName, e);
                }
            }

            // Update lang file
            updateLangFile(basePath, config);

            Charmncraftbits.LOGGER.info("Generated data files for {}/{} slabs successfully", successCount, config.getSlabs().size());
            Charmncraftbits.LOGGER.warn("IMPORTANT: You must rebuild/restart for texture and model changes to take effect!");
        } catch (Exception e) {
            Charmncraftbits.LOGGER.error("Failed to generate data files", e);
        }
    }

    private static void generateBlockstate(Path basePath, String slabName) throws IOException {
        Path blockstatePath = basePath.resolve("assets/charmncraft-bits/blockstates/" + slabName + ".json");
        Files.createDirectories(blockstatePath.getParent());

        String blockstateJson = String.format("""
                {
                  "variants": {
                    "type=bottom": {
                      "model": "charmncraft-bits:block/%s"
                    },
                    "type=top": {
                      "model": "charmncraft-bits:block/%s_top"
                    },
                    "type=double": {
                      "model": "charmncraft-bits:block/%s_double"
                    }
                  }
                }""", slabName, slabName, slabName);

        Files.writeString(blockstatePath, blockstateJson, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static void generateModels(Path basePath, String slabName, String baseBlockPath) throws IOException {
        Path blockModelPath = basePath.resolve("assets/charmncraft-bits/models/block");
        Files.createDirectories(blockModelPath);

        // Determine texture paths based on block type
        String bottomTexture;
        String topTexture;
        String sideTexture;
        String doubleTexture;

        if (baseBlockPath.contains("_log")) {
            // Logs have different top and side textures
            bottomTexture = "minecraft:block/" + baseBlockPath + "_top";
            topTexture = "minecraft:block/" + baseBlockPath + "_top";
            sideTexture = "minecraft:block/" + baseBlockPath;

            // For double slab, use cube_column (like full log blocks)
            String doubleModel = String.format("""
                    {
                      "parent": "minecraft:block/cube_column",
                      "textures": {
                        "end": "%s",
                        "side": "%s"
                      }
                    }""", topTexture, sideTexture);
            Files.writeString(blockModelPath.resolve(slabName + "_double.json"), doubleModel, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } else {
            // Wood blocks and other blocks have same texture on all sides
            bottomTexture = "minecraft:block/" + baseBlockPath;
            topTexture = "minecraft:block/" + baseBlockPath;
            sideTexture = "minecraft:block/" + baseBlockPath;

            // Double slab uses cube_all
            String doubleModel = String.format("""
                    {
                      "parent": "minecraft:block/cube_all",
                      "textures": {
                        "all": "%s"
                      }
                    }""", topTexture);
            Files.writeString(blockModelPath.resolve(slabName + "_double.json"), doubleModel, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }

        // Bottom slab model
        String bottomModel = String.format("""
                {
                  "parent": "minecraft:block/slab",
                  "textures": {
                    "bottom": "%s",
                    "top": "%s",
                    "side": "%s"
                  }
                }""", bottomTexture, topTexture, sideTexture);
        Files.writeString(blockModelPath.resolve(slabName + ".json"), bottomModel, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        // Top slab model
        String topModel = String.format("""
                {
                  "parent": "minecraft:block/slab_top",
                  "textures": {
                    "bottom": "%s",
                    "top": "%s",
                    "side": "%s"
                  }
                }""", bottomTexture, topTexture, sideTexture);
        Files.writeString(blockModelPath.resolve(slabName + "_top.json"), topModel, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        // Item model
        Path itemModelPath = basePath.resolve("assets/charmncraft-bits/models/item");
        Files.createDirectories(itemModelPath);

        String itemModel = String.format("""
                {
                  "parent": "charmncraft-bits:block/%s"
                }""", slabName);
        Files.writeString(itemModelPath.resolve(slabName + ".json"), itemModel, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static void generateRecipe(Path basePath, String slabName, String baseBlock) throws IOException {
        Path recipePath = basePath.resolve("data/charmncraft-bits/recipes/" + slabName + ".json");
        Files.createDirectories(recipePath.getParent());

        String recipeJson = String.format("""
                {
                  "type": "minecraft:crafting_shaped",
                  "pattern": [
                    "###"
                  ],
                  "key": {
                    "#": {
                      "item": "%s"
                    }
                  },
                  "result": {
                    "item": "charmncraft-bits:%s",
                    "count": 6
                  }
                }""", baseBlock, slabName);

        Files.writeString(recipePath, recipeJson, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static void generateLootTable(Path basePath, String slabName) throws IOException {
        Path lootTablePath = basePath.resolve("data/charmncraft-bits/loot_tables/blocks/" + slabName + ".json");
        Files.createDirectories(lootTablePath.getParent());

        String lootTableJson = String.format("""
                {
                  "type": "minecraft:block",
                  "pools": [
                    {
                      "rolls": 1,
                      "bonus_rolls": 0,
                      "entries": [
                        {
                          "type": "minecraft:item",
                          "name": "charmncraft-bits:%s",
                          "functions": [
                            {
                              "function": "minecraft:set_count",
                              "conditions": [
                                {
                                  "condition": "minecraft:block_state_property",
                                  "block": "charmncraft-bits:%s",
                                  "properties": {
                                    "type": "double"
                                  }
                                }
                              ],
                              "count": 2,
                              "add": false
                            },
                            {
                              "function": "minecraft:explosion_decay"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }""", slabName, slabName);

        Files.writeString(lootTablePath, lootTableJson, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static void updateLangFile(Path basePath, SlabVariantConfig config) throws IOException {
        Path langPath = basePath.resolve("assets/charmncraft-bits/lang/en_us.json");

        // Read existing lang file
        String langContent = Files.readString(langPath, StandardCharsets.UTF_8);
        JsonObject langJson = JsonParser.parseString(langContent).getAsJsonObject();

        // Add/update entries
        boolean modified = false;
        for (SlabVariantConfig.SlabVariant variant : config.getSlabs()) {
            String slabName = variant.getName() + "_slab";
            String key = "block.charmncraft-bits." + slabName;

            if (!langJson.has(key)) {
                langJson.addProperty(key, variant.getDisplayName());
                modified = true;
                Charmncraftbits.LOGGER.info("Added lang entry: {} = {}", key, variant.getDisplayName());
            }
        }

        // Add creative tab entry if missing
        String tabKey = "itemGroup.charmncraft-bits.more_slab_variants";
        if (!langJson.has(tabKey)) {
            langJson.addProperty(tabKey, "More Slab Variants");
            modified = true;
        }

        if (modified) {
            // Write back with pretty printing
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            String prettyJson = gson.toJson(langJson);
            Files.writeString(langPath, prettyJson, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            Charmncraftbits.LOGGER.info("Updated lang file");
        } else {
            Charmncraftbits.LOGGER.info("Lang file already up to date");
        }
    }

    public static List<SlabBlock> getSlabs() {
        return SLABS;
    }
}
