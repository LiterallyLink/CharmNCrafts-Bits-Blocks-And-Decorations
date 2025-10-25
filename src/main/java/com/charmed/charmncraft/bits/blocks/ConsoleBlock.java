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

public class ConsoleBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Default console hitbox: full block for now (can be customized per console later)
    private static final VoxelShape DEFAULT_SHAPE = VoxelShapes.fullCube();

    private final VoxelShape shape;

    public ConsoleBlock(Settings settings, VoxelShape shape) {
        super(settings);
        this.shape = shape;
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false)
        );
    }

    public ConsoleBlock(Settings settings) {
        this(settings, DEFAULT_SHAPE);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState()
            .with(FACING, ctx.getHorizontalPlayerFacing())
            .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
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
     * North is the default orientation as defined in the blockstate models.
     */
    private static VoxelShape rotateShape(VoxelShape shape, Direction direction) {
        if (direction == Direction.NORTH) {
            return shape;
        }

        VoxelShape[] buffer = {shape, VoxelShapes.empty()};

        int rotations = switch (direction) {
            case EAST -> 1;   // 90 degrees clockwise from north
            case SOUTH -> 2;  // 180 degrees from north
            case WEST -> 3;   // 270 degrees clockwise from north
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
