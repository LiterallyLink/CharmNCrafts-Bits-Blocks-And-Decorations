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
 * Nubert block - a unique decorative block from Deltarune.
 * Not a plushie! Uses slime sounds and custom hitbox.
 * Optimized with cached shape rotations.
 */
public class NubertBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Cached rotated shapes for each direction - prevents runtime shape calculations
    private static final Map<Direction, VoxelShape> CACHED_SHAPES;

    static {
        // Nubert base shape facing west - custom shape based on the model
        VoxelShape baseShape = VoxelShapes.union(
            VoxelShapes.cuboid(2/16f, 0, 2/16f, 14/16f, 1/16f, 14/16f),  // Base
            VoxelShapes.cuboid(3/16f, 1/16f, 3/16f, 13/16f, 8/16f, 13/16f),  // Body
            VoxelShapes.cuboid(4/16f, 8/16f, 4/16f, 12/16f, 9/16f, 12/16f)   // Top
        );
        CACHED_SHAPES = VoxelShapeHelper.createHorizontalRotationsFromWest(baseShape);
    }

    public NubertBlock(Settings settings) {
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
