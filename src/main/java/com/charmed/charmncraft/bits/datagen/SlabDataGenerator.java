package com.charmed.charmncraft.bits.datagen;

import com.charmed.charmncraft.bits.Charmncraftbits;
import com.charmed.charmncraft.bits.config.SlabVariantConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SlabDataGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger("SlabDataGenerator");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void generateAll() {
        LOGGER.info("Starting slab data generation...");

        SlabVariantConfig config = SlabVariantConfig.load();
        Path gameDir = FabricLoader.getInstance().getGameDir();
        Path resourcesPath = gameDir.resolve("config").resolve("charmncraft-bits-generated");

        try {
            Files.createDirectories(resourcesPath);

            for (SlabVariantConfig.SlabVariantEntry entry : config.getSlabs()) {
                generateRecipe(entry, resourcesPath);
                generateBlockstate(entry, resourcesPath);
                generateBlockModel(entry, resourcesPath);
                generateItemModel(entry, resourcesPath);
                generateLootTable(entry, resourcesPath);
            }

            LOGGER.info("Slab data generation complete! Files saved to: {}", resourcesPath);
            LOGGER.info("Copy the contents of this folder to src/main/resources/data/ and src/main/resources/assets/");

        } catch (Exception e) {
            LOGGER.error("Failed to generate slab data", e);
        }
    }

    private static void generateRecipe(SlabVariantConfig.SlabVariantEntry entry, Path basePath) {
        try {
            Path recipePath = basePath.resolve("data").resolve(Charmncraftbits.MOD_ID)
                    .resolve("recipes").resolve(entry.getSlabId() + ".json");
            Files.createDirectories(recipePath.getParent());

            JsonObject recipe = new JsonObject();
            recipe.addProperty("type", "minecraft:crafting_shaped");

            JsonObject pattern = new JsonObject();
            com.google.gson.JsonArray patternArray = new com.google.gson.JsonArray();
            patternArray.add("###");
            pattern.add("pattern", patternArray);

            JsonObject key = new JsonObject();
            JsonObject ingredient = new JsonObject();
            ingredient.addProperty("item", entry.getBaseBlock());
            key.add("#", ingredient);

            recipe.add("pattern", patternArray);
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
            Path blockstatePath = basePath.resolve("assets").resolve(Charmncraftbits.MOD_ID)
                    .resolve("blockstates").resolve(entry.getSlabId() + ".json");
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
            doubleBlock.addProperty("model", Charmncraftbits.MOD_ID + ":block/" + entry.getName() + "_double");
            variants.add("type=double", doubleBlock);

            blockstate.add("variants", variants);

            Files.writeString(blockstatePath, GSON.toJson(blockstate));
            LOGGER.debug("Generated blockstate: {}", entry.getSlabId());

        } catch (IOException e) {
            LOGGER.error("Failed to generate blockstate for {}", entry.getSlabId(), e);
        }
    }

    private static void generateBlockModel(SlabVariantConfig.SlabVariantEntry entry, Path basePath) {
        try {
            Path modelPath = basePath.resolve("assets").resolve(Charmncraftbits.MOD_ID)
                    .resolve("models").resolve("block");
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
            Files.writeString(modelPath.resolve(entry.getName() + "_double.json"), GSON.toJson(doubleModel));

            LOGGER.debug("Generated block models: {}", entry.getSlabId());

        } catch (IOException e) {
            LOGGER.error("Failed to generate block models for {}", entry.getSlabId(), e);
        }
    }

    private static void generateItemModel(SlabVariantConfig.SlabVariantEntry entry, Path basePath) {
        try {
            Path modelPath = basePath.resolve("assets").resolve(Charmncraftbits.MOD_ID)
                    .resolve("models").resolve("item").resolve(entry.getSlabId() + ".json");
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
            Path lootTablePath = basePath.resolve("data").resolve(Charmncraftbits.MOD_ID)
                    .resolve("loot_tables").resolve("blocks").resolve(entry.getSlabId() + ".json");
            Files.createDirectories(lootTablePath.getParent());

            JsonObject lootTable = new JsonObject();
            lootTable.addProperty("type", "minecraft:block");

            com.google.gson.JsonArray pools = new com.google.gson.JsonArray();
            JsonObject pool = new JsonObject();

            pool.addProperty("rolls", 1);
            pool.addProperty("bonus_rolls", 0);

            com.google.gson.JsonArray entries = new com.google.gson.JsonArray();
            JsonObject poolEntry = new JsonObject();
            poolEntry.addProperty("type", "minecraft:item");
            poolEntry.addProperty("name", Charmncraftbits.MOD_ID + ":" + entry.getSlabId());

            com.google.gson.JsonArray functions = new com.google.gson.JsonArray();
            JsonObject function = new JsonObject();
            function.addProperty("function", "minecraft:set_count");

            JsonObject conditions = new JsonObject();
            conditions.addProperty("block_state", Charmncraftbits.MOD_ID + ":" + entry.getSlabId());
            conditions.addProperty("property", "type");
            conditions.addProperty("value", "double");

            function.addProperty("count", 2);
            function.add("conditions", new com.google.gson.JsonArray());
            function.getAsJsonArray("conditions").add(createCondition());

            functions.add(function);

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

    private static JsonObject createCondition() {
        JsonObject condition = new JsonObject();
        condition.addProperty("condition", "minecraft:block_state_property");
        condition.addProperty("block", Charmncraftbits.MOD_ID + ":oak_log_slab");

        JsonObject properties = new JsonObject();
        properties.addProperty("type", "double");
        condition.add("properties", properties);

        return condition;
    }
}
