package com.charmed.charmncraft.bits;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SlabVariantConfig {
    private List<SlabVariant> slabs = new ArrayList<>();

    public static class SlabVariant {
        private String name;
        private String base_block;
        private String display_name;

        public String getName() {
            return name;
        }

        public String getBaseBlock() {
            return base_block;
        }

        public String getDisplayName() {
            return display_name;
        }
    }

    public List<SlabVariant> getSlabs() {
        return slabs;
    }

    public static SlabVariantConfig load() {
        try {
            InputStream stream = SlabVariantConfig.class.getResourceAsStream("/slab_variants.json");
            if (stream == null) {
                Charmncraftbits.LOGGER.error("Could not find slab_variants.json");
                return new SlabVariantConfig();
            }

            Gson gson = new GsonBuilder().create();
            InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            SlabVariantConfig config = gson.fromJson(reader, SlabVariantConfig.class);

            Charmncraftbits.LOGGER.info("Loaded {} slab variants from config", config.getSlabs().size());
            return config;
        } catch (Exception e) {
            Charmncraftbits.LOGGER.error("Failed to load slab_variants.json", e);
            return new SlabVariantConfig();
        }
    }
}
