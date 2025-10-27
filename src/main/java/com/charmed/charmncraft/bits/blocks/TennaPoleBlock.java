package com.charmed.charmncraft.bits.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

/**
 * Tenna Pole block - a unique decorative block from Deltarune.
 * Not a plushie! Uses metal/gold sounds and custom hitbox.
 */
public class TennaPoleBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Tenna pole hitbox - custom shape based on the model
    private static final VoxelShape SHAPE = VoxelShapes.union(
        VoxelShapes.cuboid(7/16f, 0, 7/16f, 9/16f, 9/16f, 9/16f),  // Stick base
        VoxelShapes.cuboid(6/16f, 9/16f, 6/16f, 10/16f, 10/16f, 10/16f),  // Connection
        VoxelShapes.cuboid(5/16f, 10/16f, 6/16f, 11/16f, 14/16f, 10/16f),  // Main antenna body
        VoxelShapes.cuboid(5/16f, 14/16f, 6/16f, 11/16f, 18/16f, 10/16f)   // Antenna top
    );

    public TennaPoleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState()
            .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
            .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return rotateShape(SHAPE, state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return rotateShape(SHAPE, state.get(FACING));
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
