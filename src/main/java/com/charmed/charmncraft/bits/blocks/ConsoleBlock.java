package com.charmed.charmncraft.bits.blocks;

import com.charmed.charmncraft.bits.util.VoxelShapeHelper;
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

import java.util.Map;

/**
 * A decorative console block with waterlogging support and custom hitboxes.
 * Uses cached rotated shapes for optimal performance.
 */
public class ConsoleBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Cached rotated shapes for each direction - prevents runtime shape calculations
    private final Map<Direction, VoxelShape> cachedShapes;

    /**
     * Creates a console block with a custom VoxelShape.
     * The shape is automatically rotated and cached for all 4 horizontal directions.
     *
     * @param settings Block settings
     * @param baseShape The north-facing shape (will be rotated for other directions)
     */
    public ConsoleBlock(Settings settings, VoxelShape baseShape) {
        super(settings);
        this.cachedShapes = VoxelShapeHelper.createHorizontalRotations(baseShape);
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false)
        );
    }

    /**
     * Creates a console block with a full cube hitbox.
     *
     * @param settings Block settings
     */
    public ConsoleBlock(Settings settings) {
        this(settings, VoxelShapes.fullCube());
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
        return cachedShapes.get(state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return cachedShapes.get(state.get(FACING));
    }
}
