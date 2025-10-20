package com.charmed.charmncraft.bits;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.block.Block;

public class CharmncraftbitsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Register hanging lights as cutout render layer
		// This allows their transparent textures to render properly
		registerCutoutBlocks();
	}

	private static void registerCutoutBlocks() {
		String[] colors = {
			"default", "black", "blue", "brown", "cyan", "gray", "green",
			"light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow"
		};

		// Register hanging lights
		for (String color : colors) {
			String blockId = "hanging_lights_" + color;
			Block block = net.minecraft.registry.Registries.BLOCK.get(new net.minecraft.util.Identifier("charmncraft-bits", blockId));
			if (block != null) {
				BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
			} else {
				Charmncraftbits.LOGGER.warn("Hanging lights block not found: " + blockId);
			}
		}
	}
}