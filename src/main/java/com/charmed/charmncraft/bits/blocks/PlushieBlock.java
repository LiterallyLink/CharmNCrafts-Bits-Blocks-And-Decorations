package com.charmed.charmncraft.bits.blocks;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class PlushieBlock extends HorizontalFacingBlock {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    // Default plushie hitbox: 8x8x8 centered cube (similar to your other decorative blocks)
    private static final VoxelShape DEFAULT_SHAPE = VoxelShapes.cuboid(
        4/16f, 0, 4/16f,      // min x, y, z
        12/16f, 8/16f, 12/16f  // max x, y, z
    );

    private final VoxelShape shape;

    public PlushieBlock(Settings settings, VoxelShape shape) {
        super(settings);
        this.shape = shape;
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
        );
    }

    public PlushieBlock(Settings settings) {
        this(settings, DEFAULT_SHAPE);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
            .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return rotateShape(shape, state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return rotateShape(shape, state.get(FACING));
    }

    /**
     * Rotates a VoxelShape based on the horizontal facing direction.
     * West is the default orientation (0 degrees) as models are defined facing west.
     */
    private static VoxelShape rotateShape(VoxelShape shape, Direction direction) {
        if (direction == Direction.WEST) {
            return shape;
        }

        VoxelShape[] buffer = {shape, VoxelShapes.empty()};

        int rotations = switch (direction) {
            case NORTH -> 1;  // 90 degrees clockwise from west
            case EAST -> 2;   // 180 degrees from west
            case SOUTH -> 3;  // 270 degrees clockwise from west
            default -> 0;
        };

        for (int i = 0; i < rotations; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
                // Rotate 90 degrees clockwise around Y axis (center at 0.5, 0.5)
                double newMinX = 1 - maxZ;
                double newMaxX = 1 - minZ;
                double newMinZ = minX;
                double newMaxZ = maxX;
                buffer[1] = VoxelShapes.union(buffer[1],
                    VoxelShapes.cuboid(newMinX, minY, newMinZ, newMaxX, maxY, newMaxZ));
            });
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }
}
