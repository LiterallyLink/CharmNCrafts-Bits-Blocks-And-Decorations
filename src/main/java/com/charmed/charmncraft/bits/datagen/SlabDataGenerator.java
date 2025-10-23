package com.charmed.charmncraft.bits.datagen;

import com.charmed.charmncraft.bits.Charmncraftbits;
import com.charmed.charmncraft.bits.config.SlabVariantConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SlabDataGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger("SlabDataGenerator");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static boolean hasGenerated = false;

    public static void generateAll() {
        if (hasGenerated) {
            LOGGER.info("Slab data already generated, skipping...");
            return;
        }

        LOGGER.info("Starting slab data generation...");

        SlabVariantConfig config = SlabVariantConfig.load();

        // Determine if we're in dev environment
        boolean isDev = FabricLoader.getInstance().isDevelopmentEnvironment();

        Path basePath;
        if (isDev) {
            // In dev, write directly to src/main/resources
            basePath = FabricLoader.getInstance().getGameDir()
                    .resolve("src/main/resources");
            LOGGER.info("Development environment detected, writing to: {}", basePath);
        } else {
            // In production, write to config folder (won't be used at runtime, but good for reference)
            basePath = FabricLoader.getInstance().getConfigDir()
                    .resolve("charmncraft-bits-generated");
            LOGGER.warn("Production environment - generated files won't be loaded! Copy from: {}", basePath);
        }

        try {
            Files.createDirectories(basePath);

            // Generate lang file first to collect all entries
            Map<String, String> langEntries = new HashMap<>();

            for (SlabVariantConfig.SlabVariantEntry entry : config.getSlabs()) {
                generateRecipe(entry, basePath);
                generateBlockstate(entry, basePath);
                generateBlockModels(entry, basePath);
                generateItemModel(entry, basePath);
                generateLootTable(entry, basePath);

                // Add to lang entries
                String key = "block." + Charmncraftbits.MOD_ID + "." + entry.getSlabId();
                String displayName = entry.getDisplayName();
                langEntries.put(key, displayName);
            }

            // Generate language file
            generateLanguageFile(langEntries, basePath);

            hasGenerated = true;
            LOGGER.info("Slab data generation complete! Generated {} slabs", config.getSlabs().size());

        } catch (Exception e) {
            LOGGER.error("Failed to generate slab data", e);
        }
    }

    private static void generateRecipe(SlabVariantConfig.SlabVariantEntry entry, Path basePath) {
        try {
            Path recipePath = basePath.resolve("data")
                    .resolve(Charmncraftbits.MOD_ID)
                    .resolve("recipes")
                    .resolve(entry.getSlabId() + ".json");
            Files.createDirectories(recipePath.getParent());

            JsonObject recipe = new JsonObject();
            recipe.addProperty("type", "minecraft:crafting_shaped");

            JsonArray pattern = new JsonArray();
            pattern.add("###");
            recipe.add("pattern", pattern);

            JsonObject key = new JsonObject();
            JsonObject ingredient = new JsonObject();
            ingredient.addProperty("item", entry.getBaseBlock());
            key.add("#", ingredient);
            recipe.add("key", key);

            JsonObject result = new JsonObject();
            result.addProperty("item", Charmncraftbits.MOD_ID + ":" + entry.getSlabId());
            result.addProperty("count", 6);
            recipe.add("result", result);

            Files.writeString(recipePath, GSON.toJson(recipe));
            LOGGER.debug("Generated recipe: {}", entry.getSlabId());

        } catch (IOException e) {
            LOGGER.error("Failed to generate recipe for {}", entry.getSlabId(), e);
        }
    }

    private static void generateBlockstate(SlabVariantConfig.SlabVariantEntry entry, Path basePath) {
        try {
            Path blockstatePath = basePath.resolve("assets")
                    .resolve(Charmncraftbits.MOD_ID)
                    .resolve("blockstates")
                    .resolve(entry.getSlabId() + ".json");
            Files.createDirectories(blockstatePath.getParent());

            JsonObject blockstate = new JsonObject();
            JsonObject variants = new JsonObject();

            // type=bottom
            JsonObject bottom = new JsonObject();
            bottom.addProperty("model", Charmncraftbits.MOD_ID + ":block/" + entry.getSlabId());
            variants.add("type=bottom", bottom);

            // type=top
            JsonObject top = new JsonObject();
            top.addProperty("model", Charmncraftbits.MOD_ID + ":block/" + entry.getSlabId() + "_top");
            variants.add("type=top", top);

            // type=double
            JsonObject doubleBlock = new JsonObject();
            doubleBlock.addProperty("model", Charmncraftbits.MOD_ID + ":block/" + entry.getSlabId() + "_double");
            variants.add("type=double", doubleBlock);

            blockstate.add("variants", variants);

            Files.writeString(blockstatePath, GSON.toJson(blockstate));
            LOGGER.debug("Generated blockstate: {}", entry.getSlabId());

        } catch (IOException e) {
            LOGGER.error("Failed to generate blockstate for {}", entry.getSlabId(), e);
        }
    }

    private static void generateBlockModels(SlabVariantConfig.SlabVariantEntry entry, Path basePath) {
        try {
            Path modelPath = basePath.resolve("assets")
                    .resolve(Charmncraftbits.MOD_ID)
                    .resolve("models")
                    .resolve("block");
            Files.createDirectories(modelPath);

            // Extract namespace and path from base block
            String[] parts = entry.getBaseBlock().split(":");
            String namespace = parts[0];
            String blockName = parts[1];

            // Bottom slab model
            JsonObject bottomModel = new JsonObject();
            bottomModel.addProperty("parent", "minecraft:block/slab");
            JsonObject bottomTextures = new JsonObject();
            bottomTextures.addProperty("bottom", namespace + ":block/" + blockName);
            bottomTextures.addProperty("top", namespace + ":block/" + blockName);
            bottomTextures.addProperty("side", namespace + ":block/" + blockName);
            bottomModel.add("textures", bottomTextures);
            Files.writeString(modelPath.resolve(entry.getSlabId() + ".json"), GSON.toJson(bottomModel));

            // Top slab model
            JsonObject topModel = new JsonObject();
            topModel.addProperty("parent", "minecraft:block/slab_top");
            JsonObject topTextures = new JsonObject();
            topTextures.addProperty("bottom", namespace + ":block/" + blockName);
            topTextures.addProperty("top", namespace + ":block/" + blockName);
            topTextures.addProperty("side", namespace + ":block/" + blockName);
            topModel.add("textures", topTextures);
            Files.writeString(modelPath.resolve(entry.getSlabId() + "_top.json"), GSON.toJson(topModel));

            // Double slab model (full block)
            JsonObject doubleModel = new JsonObject();
            doubleModel.addProperty("parent", "minecraft:block/cube_all");
            JsonObject doubleTextures = new JsonObject();
            doubleTextures.addProperty("all", namespace + ":block/" + blockName);
            doubleModel.add("textures", doubleTextures);
            Files.writeString(modelPath.resolve(entry.getSlabId() + "_double.json"), GSON.toJson(doubleModel));

            LOGGER.debug("Generated block models: {}", entry.getSlabId());

        } catch (IOException e) {
            LOGGER.error("Failed to generate block models for {}", entry.getSlabId(), e);
        }
    }

    private static void generateItemModel(SlabVariantConfig.SlabVariantEntry entry, Path basePath) {
        try {
            Path modelPath = basePath.resolve("assets")
                    .resolve(Charmncraftbits.MOD_ID)
                    .resolve("models")
                    .resolve("item")
                    .resolve(entry.getSlabId() + ".json");
            Files.createDirectories(modelPath.getParent());

            JsonObject itemModel = new JsonObject();
            itemModel.addProperty("parent", Charmncraftbits.MOD_ID + ":block/" + entry.getSlabId());

            Files.writeString(modelPath, GSON.toJson(itemModel));
            LOGGER.debug("Generated item model: {}", entry.getSlabId());

        } catch (IOException e) {
            LOGGER.error("Failed to generate item model for {}", entry.getSlabId(), e);
        }
    }

    private static void generateLootTable(SlabVariantConfig.SlabVariantEntry entry, Path basePath) {
        try {
            Path lootTablePath = basePath.resolve("data")
                    .resolve(Charmncraftbits.MOD_ID)
                    .resolve("loot_tables")
                    .resolve("blocks")
                    .resolve(entry.getSlabId() + ".json");
            Files.createDirectories(lootTablePath.getParent());

            JsonObject lootTable = new JsonObject();
            lootTable.addProperty("type", "minecraft:block");

            JsonArray pools = new JsonArray();
            JsonObject pool = new JsonObject();
            pool.addProperty("rolls", 1);
            pool.addProperty("bonus_rolls", 0);

            JsonArray entries = new JsonArray();
            JsonObject poolEntry = new JsonObject();
            poolEntry.addProperty("type", "minecraft:item");
            poolEntry.addProperty("name", Charmncraftbits.MOD_ID + ":" + entry.getSlabId());

            JsonArray functions = new JsonArray();

            // Set count to 2 if double slab
            JsonObject setCountFunction = new JsonObject();
            setCountFunction.addProperty("function", "minecraft:set_count");
            setCountFunction.addProperty("count", 2);

            JsonArray conditions = new JsonArray();
            JsonObject blockStateCondition = new JsonObject();
            blockStateCondition.addProperty("condition", "minecraft:block_state_property");
            blockStateCondition.addProperty("block", Charmncraftbits.MOD_ID + ":" + entry.getSlabId());

            JsonObject properties = new JsonObject();
            properties.addProperty("type", "double");
            blockStateCondition.add("properties", properties);

            conditions.add(blockStateCondition);
            setCountFunction.add("conditions", conditions);
            functions.add(setCountFunction);

            // Explosion decay
            JsonObject explosionDecay = new JsonObject();
            explosionDecay.addProperty("function", "minecraft:explosion_decay");
            functions.add(explosionDecay);

            poolEntry.add("functions", functions);
            entries.add(poolEntry);

            pool.add("entries", entries);
            pools.add(pool);

            lootTable.add("pools", pools);

            Files.writeString(lootTablePath, GSON.toJson(lootTable));
            LOGGER.debug("Generated loot table: {}", entry.getSlabId());

        } catch (IOException e) {
            LOGGER.error("Failed to generate loot table for {}", entry.getSlabId(), e);
        }
    }

    private static void generateLanguageFile(Map<String, String> langEntries, Path basePath) {
        try {
            Path langPath = basePath.resolve("assets")
                    .resolve(Charmncraftbits.MOD_ID)
                    .resolve("lang")
                    .resolve("en_us.json");
            Files.createDirectories(langPath.getParent());

            JsonObject langFile = new JsonObject();

            // Load existing lang file if it exists
            if (Files.exists(langPath)) {
                try {
                    String existing = Files.readString(langPath);
                    JsonObject existingLang = GSON.fromJson(existing, JsonObject.class);
                    // Copy all existing entries
                    for (String key : existingLang.keySet()) {
                        langFile.addProperty(key, existingLang.get(key).getAsString());
                    }
                } catch (Exception e) {
                    LOGGER.warn("Could not load existing lang file, will overwrite", e);
                }
            }

            // Add/update slab entries
            for (Map.Entry<String, String> entry : langEntries.entrySet()) {
                langFile.addProperty(entry.getKey(), entry.getValue());
            }

            // Add creative tab if not present
            String tabKey = "itemGroup." + Charmncraftbits.MOD_ID + ".new_slab_variants";
            if (!langFile.has(tabKey)) {
                langFile.addProperty(tabKey, "New Slab Variants");
            }

            Files.writeString(langPath, GSON.toJson(langFile));
            LOGGER.info("Generated language file with {} entries", langEntries.size());

        } catch (IOException e) {
            LOGGER.error("Failed to generate language file", e);
        }
    }
}
