package com.charmed.charmncraft.bits.blocks;

import com.charmed.charmncraft.bits.util.VoxelShapeHelper;
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

import java.util.Map;

/**
 * Tenna Pole block - a unique decorative block from Deltarune.
 * Not a plushie! Uses metal/gold sounds and custom hitbox.
 * Optimized with cached shape rotations.
 */
public class TennaPoleBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Cached rotated shapes for each direction - prevents runtime shape calculations
    private static final Map<Direction, VoxelShape> CACHED_SHAPES;

    static {
        // Tenna pole base shape facing west - custom shape based on the model
        VoxelShape baseShape = VoxelShapes.union(
            VoxelShapes.cuboid(7/16f, 0, 7/16f, 9/16f, 9/16f, 9/16f),  // Stick base
            VoxelShapes.cuboid(6/16f, 9/16f, 6/16f, 10/16f, 10/16f, 10/16f),  // Connection
            VoxelShapes.cuboid(5/16f, 10/16f, 6/16f, 11/16f, 14/16f, 10/16f),  // Main antenna body
            VoxelShapes.cuboid(5/16f, 14/16f, 6/16f, 11/16f, 18/16f, 10/16f)   // Antenna top
        );
        CACHED_SHAPES = VoxelShapeHelper.createHorizontalRotationsFromWest(baseShape);
    }

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
        return CACHED_SHAPES.get(state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CACHED_SHAPES.get(state.get(FACING));
    }
}
