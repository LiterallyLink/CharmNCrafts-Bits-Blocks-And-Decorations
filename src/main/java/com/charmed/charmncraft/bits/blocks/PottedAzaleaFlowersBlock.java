package com.charmed.charmncraft.bits.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

/**
 * Potted Azalea Flowers block - decorative flower pot variant of azalea flowers.
 * Provides a contained, potted version suitable for indoor decoration.
 *
 * Uses standard Minecraft flower pot mechanics and rendering.
 */
public class PottedAzaleaFlowersBlock extends FlowerPotBlock {
    /**
     * Standard flower pot shape (6x6x6 pixels, centered at bottom).
     * Matches vanilla Minecraft flower pot dimensions.
     */
    private static final VoxelShape SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 11.0);

    /**
     * Creates a new potted azalea flowers block.
     *
     * @param content The flower content (azalea flowers block)
     * @param settings Block settings including material and sound
     */
    public PottedAzaleaFlowersBlock(Block content, Settings settings) {
        super(content, settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
