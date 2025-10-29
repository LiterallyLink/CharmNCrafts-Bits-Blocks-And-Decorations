package com.charmed.charmncraft.bits.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class NightLightBlock extends HorizontalFacingBlock {
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    // Cache rotated shapes for better performance
    private final VoxelShape northShape;
    private final VoxelShape eastShape;
    private final VoxelShape southShape;
    private final VoxelShape westShape;

    public NightLightBlock(Settings settings, VoxelShape shape) {
        super(settings);
        // Pre-calculate and cache all rotated shapes
        this.northShape = shape;
        this.eastShape = rotateShape(shape, Direction.EAST);
        this.southShape = rotateShape(shape, Direction.SOUTH);
        this.westShape = rotateShape(shape, Direction.WEST);

        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(LIT, true) // Default to lit (glowing)
                .with(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
            .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
            .with(LIT, true);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShapeForDirection(state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShapeForDirection(state.get(FACING));
    }

    /**
     * Returns the cached shape for the given direction.
     * This is much faster than recalculating rotation every time.
     */
    private VoxelShape getShapeForDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> northShape;
            case EAST -> eastShape;
            case SOUTH -> southShape;
            case WEST -> westShape;
            default -> northShape;
        };
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            // Toggle the lit state when right-clicked
            BlockState newState = state.cycle(LIT);
            world.setBlockState(pos, newState, Block.NOTIFY_ALL);
        }
        return ActionResult.success(world.isClient);
    }

    /**
     * Rotates a VoxelShape based on the horizontal facing direction.
     * North is the default orientation (0 degrees).
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
