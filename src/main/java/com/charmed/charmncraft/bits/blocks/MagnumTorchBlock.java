package com.charmed.charmncraft.bits.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class MagnumTorchBlock extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Magnum torch hitbox: 4x16x4 centered column (matches texture size)
    // Based on the original model: from [6, 0, 6] to [10, 16, 10]
    private static final VoxelShape SHAPE = VoxelShapes.cuboid(
        6/16f, 0, 6/16f,      // min x, y, z
        10/16f, 1.0f, 10/16f  // max x, y, z (1.0 = 16/16)
    );

    // Spawn prevention range (in blocks)
    public static final int SPAWN_PREVENTION_RANGE = 32;

    private final TorchType torchType;

    public enum TorchType {
        DIAMOND,   // Prevents hostile mob spawning
        EMERALD,   // Prevents passive mob spawning
        AMETHYST   // Prevents cave and water mob spawning
    }

    public MagnumTorchBlock(Settings settings, TorchType torchType) {
        super(settings);
        this.torchType = torchType;
        this.setDefaultState(
            this.stateManager.getDefaultState()
                .with(WATERLOGGED, false)
        );
    }

    public TorchType getTorchType() {
        return torchType;
    }

    /**
     * Check if this torch type prevents the given entity from spawning
     */
    public boolean preventsSpawn(EntityType<?> entityType) {
        return switch (torchType) {
            case DIAMOND -> isHostileMob(entityType);
            case EMERALD -> isPassiveMob(entityType);
            case AMETHYST -> isCaveOrWaterMob(entityType);
        };
    }

    private boolean isHostileMob(EntityType<?> entityType) {
        // All monsters: zombies, skeletons, creepers, spiders, phantoms, etc.
        return entityType.getSpawnGroup() == SpawnGroup.MONSTER;
    }

    private boolean isPassiveMob(EntityType<?> entityType) {
        // Passive mobs: wandering traders, animals
        // Note: Animals only spawn during world gen or breeding, both unaffected
        return entityType == EntityType.WANDERING_TRADER
            || entityType == EntityType.TRADER_LLAMA
            || entityType.getSpawnGroup() == SpawnGroup.CREATURE;
    }

    private boolean isCaveOrWaterMob(EntityType<?> entityType) {
        // Cave and water mobs: bats, squids, axolotls, fish
        return entityType == EntityType.BAT
            || entityType == EntityType.SQUID
            || entityType == EntityType.GLOW_SQUID
            || entityType == EntityType.AXOLOTL
            || entityType == EntityType.COD
            || entityType == EntityType.SALMON
            || entityType == EntityType.TROPICAL_FISH
            || entityType == EntityType.PUFFERFISH
            || entityType.getSpawnGroup() == SpawnGroup.WATER_AMBIENT
            || entityType.getSpawnGroup() == SpawnGroup.WATER_CREATURE
            || entityType.getSpawnGroup() == SpawnGroup.UNDERGROUND_WATER_CREATURE
            || entityType.getSpawnGroup() == SpawnGroup.AMBIENT;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState()
            .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
