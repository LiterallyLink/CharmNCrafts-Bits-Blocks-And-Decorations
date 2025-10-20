package com.charmed.charmncraft.bits.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WallMountedLightBlock extends Block {
    private final VoxelShape shape;

    public WallMountedLightBlock(Settings settings, VoxelShape shape) {
        super(settings);
        this.shape = shape;
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction clickedFace = ctx.getSide();

        // Only allow placement on walls (not floor or ceiling)
        if (clickedFace == Direction.UP || clickedFace == Direction.DOWN) {
            return null;
        }

        // Face should point outward from the wall (opposite of clicked face)
        Direction facing = clickedFace.getOpposite();

        // Verify the block can be placed here
        BlockPos pos = ctx.getBlockPos();
        if (!canPlaceAt(this.getDefaultState().with(Properties.HORIZONTAL_FACING, facing), ctx.getWorld(), pos)) {
            return null;
        }

        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, facing);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction facing = state.get(Properties.HORIZONTAL_FACING);
        Direction attachmentDirection = facing.getOpposite();
        BlockPos attachmentPos = pos.offset(attachmentDirection);
        BlockState attachmentState = world.getBlockState(attachmentPos);

        // Check if the block behind has a solid face for attachment
        return attachmentState.isSideSolidFullSquare(world, attachmentPos, attachmentDirection.getOpposite());
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
        // If the block we're attached to is removed, break this block
        Direction attachmentDirection = state.get(Properties.HORIZONTAL_FACING).getOpposite();
        if (direction == attachmentDirection && !state.canPlaceAt(world, pos)) {
            return net.minecraft.block.Blocks.AIR.getDefaultState();
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }
}
