package com.charmed.charmncraft.bits;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharmncraftbitsClient implements ClientModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger("CharmncraftbitsClient");
	private static boolean logged = false;

	@Override
	public void onInitializeClient() {
		LOGGER.info("CharmncraftbitsClient initialized");
		
		// Log texture availability on first world load
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (!logged && client.world != null) {
				logged = true;
				LOGGER.info("==== CHECKING TEXTURE AVAILABILITY ====");
				
				// Try to get the texture manager
				try {
					var textureManager = client.getTextureManager();
					LOGGER.info("TextureManager available: {}", textureManager != null);
					
					// Log some texture IDs
					LOGGER.info("oak_log texture: minecraft:block/oak_log");
					LOGGER.info("oak_wood texture: minecraft:block/oak_wood");
					LOGGER.info("spruce_wood texture: minecraft:block/spruce_wood");
					LOGGER.info("stripped_oak_wood texture: minecraft:block/stripped_oak_wood");
					LOGGER.info("stripped_spruce_wood texture: minecraft:block/stripped_spruce_wood");
					
				} catch (Exception e) {
					LOGGER.error("Error checking textures", e);
				}
				
				LOGGER.info("==== SLAB BLOCKS REGISTERED ====");
				var slabBlocks = ModSlabs.getSlabBlocks();
				for (var entry : slabBlocks.entrySet()) {
					LOGGER.info("Slab: {} -> Block: {}", entry.getKey(), entry.getValue());
				}
			}
		});
	}
}
