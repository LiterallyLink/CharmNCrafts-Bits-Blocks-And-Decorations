package com.charmed.charmncraft.bits.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

/**
 * Azalea Flowers block that can be placed on multiple surfaces like carpet.
 * Can be placed on ground, walls, and ceiling.
 */
public class AzaleaFlowersBlock extends Block implements Waterloggable {
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty WEST = Properties.WEST;
    public static final BooleanProperty UP = Properties.UP;
    public static final BooleanProperty DOWN = Properties.DOWN;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Thin carpet-like shapes for each direction
    private static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
    private static final VoxelShape UP_SHAPE = Block.createCuboidShape(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);
    private static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public AzaleaFlowersBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false)
                .with(WATERLOGGED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(pos);
        BlockState blockState = ctx.getWorld().getBlockState(pos);

        if (blockState.isOf(this)) {
            // If block already exists at this position, add to it
            return blockState.with(getProperty(ctx.getSide()), true);
        }

        // New placement - place on the clicked face
        Direction side = ctx.getSide();
        BlockState state = this.getDefaultState()
            .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);

        return state.with(getProperty(side), true);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.empty();

        if (state.get(DOWN)) shape = VoxelShapes.union(shape, DOWN_SHAPE);
        if (state.get(UP)) shape = VoxelShapes.union(shape, UP_SHAPE);
        if (state.get(NORTH)) shape = VoxelShapes.union(shape, NORTH_SHAPE);
        if (state.get(SOUTH)) shape = VoxelShapes.union(shape, SOUTH_SHAPE);
        if (state.get(WEST)) shape = VoxelShapes.union(shape, WEST_SHAPE);
        if (state.get(EAST)) shape = VoxelShapes.union(shape, EAST_SHAPE);

        return shape.isEmpty() ? VoxelShapes.fullCube() : shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty(); // No collision like carpet
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (context.getStack().isOf(this.asItem())) {
            // Can replace if clicking on a face that doesn't have flowers yet
            return !state.get(getProperty(context.getSide()));
        }
        return false;
    }

    @Override
    public BlockState getStateForNeighborUpdate(
        BlockState state,
        Direction direction,
        BlockState neighborState,
        WorldAccess world,
        BlockPos pos,
        BlockPos neighborPos
    ) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        // Check if the block should drop
        BooleanProperty property = getProperty(direction);
        if (state.get(property) && !canPlaceOn(world, direction, pos)) {
            state = state.with(property, false);
        }

        // If no faces remain, drop the block
        return hasAnyFace(state) ? state : Blocks.AIR.getDefaultState();
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        // Check that at least one face can be placed
        for (Direction direction : Direction.values()) {
            if (state.get(getProperty(direction)) && canPlaceOn(world, direction, pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if flowers can be placed on this face
     */
    private boolean canPlaceOn(WorldView world, Direction face, BlockPos pos) {
        BlockPos attachPos = pos.offset(face.getOpposite());
        BlockState attachState = world.getBlockState(attachPos);
        return attachState.isSideSolidFullSquare(world, attachPos, face);
    }

    /**
     * Check if the state has any face with flowers
     */
    private boolean hasAnyFace(BlockState state) {
        return state.get(NORTH) || state.get(SOUTH) || state.get(EAST) ||
               state.get(WEST) || state.get(UP) || state.get(DOWN);
    }

    /**
     * Get the property for a given direction
     */
    private static BooleanProperty getProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            case UP -> UP;
            case DOWN -> DOWN;
        };
    }
}
