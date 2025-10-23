package com.charmed.charmncraft.bits.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SlabVariantConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger("SlabVariantConfig");
    private static final Gson GSON = new Gson();

    private final List<SlabVariantEntry> slabs = new ArrayList<>();

    public static class SlabVariantEntry {
        private final String name;
        private final String baseBlock;
        private final String displayName;

        public SlabVariantEntry(String name, String baseBlock, String displayName) {
            this.name = name;
            this.baseBlock = baseBlock;
            this.displayName = displayName != null ? displayName : formatDisplayName(name);
        }

        public String getName() {
            return name;
        }

        public String getBaseBlock() {
            return baseBlock;
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getSlabId() {
            return name + "_slab";
        }

        private static String formatDisplayName(String name) {
            // Convert oak_log to Oak Log Slab
            String[] words = name.split("_");
            StringBuilder result = new StringBuilder();

            for (String word : words) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
            }

            return result.append(" Slab").toString();
        }
    }

    public static SlabVariantConfig load() {
        SlabVariantConfig config = new SlabVariantConfig();

        try {
            // Try to load from the mod's resources
            InputStream stream = SlabVariantConfig.class.getResourceAsStream("/slab_variants.json");

            if (stream == null) {
                LOGGER.error("Could not find slab_variants.json in resources!");
                return config;
            }

            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            JsonObject json = GSON.fromJson(reader, JsonObject.class);

            if (json.has("slabs")) {
                JsonArray slabsArray = json.getAsJsonArray("slabs");

                for (JsonElement element : slabsArray) {
                    JsonObject slabObj = element.getAsJsonObject();
                    String name = slabObj.get("name").getAsString();
                    String baseBlock = slabObj.get("base_block").getAsString();
                    String displayName = slabObj.has("display_name") ? slabObj.get("display_name").getAsString() : null;

                    config.slabs.add(new SlabVariantEntry(name, baseBlock, displayName));
                    LOGGER.info("Loaded slab variant: {} from base block: {}", name, baseBlock);
                }
            }

            reader.close();
            stream.close();

        } catch (Exception e) {
            LOGGER.error("Failed to load slab_variants.json", e);
        }

        return config;
    }

    public List<SlabVariantEntry> getSlabs() {
        return slabs;
    }
}
