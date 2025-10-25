package com.charmed.charmncraft.bits;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharmncraftbitsClient implements ClientModInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger("CharmncraftbitsClient");

	@Override
	public void onInitializeClient() {
		LOGGER.info("=== CLIENT INITIALIZATION STARTING ===");

		// Register render layers for blocks with transparency
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AZALEA_FLOWERS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_AZALEA_FLOWERS, RenderLayer.getCutout());

		LOGGER.info("=== CLIENT INITIALIZATION COMPLETE ===");
	}
}
