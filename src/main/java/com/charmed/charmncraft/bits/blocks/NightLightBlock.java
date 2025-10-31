package com.charmed.charmncraft.bits.blocks;

import com.charmed.charmncraft.bits.util.VoxelShapeHelper;
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

import java.util.Map;

/**
 * Interactive decorative night light blocks (frogs and mushrooms).
 * Players can toggle the light on/off by right-clicking.
 * Optimized with cached shape rotations.
 */
public class NightLightBlock extends HorizontalFacingBlock {
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    // Cached rotated shapes for each direction - prevents runtime shape calculations
    private final Map<Direction, VoxelShape> cachedShapes;

    public NightLightBlock(Settings settings, VoxelShape shape) {
        super(settings);
        // Pre-calculate and cache all rotated shapes
        this.cachedShapes = VoxelShapeHelper.createHorizontalRotations(shape);

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
        return cachedShapes.get(state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return cachedShapes.get(state.get(FACING));
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
}
