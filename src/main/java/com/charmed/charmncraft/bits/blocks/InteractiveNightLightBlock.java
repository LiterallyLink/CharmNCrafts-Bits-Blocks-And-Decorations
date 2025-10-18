package com.charmed.charmncraft.bits.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Interactive night light block (frog, mushroom, octopus).
 * 
 * When right-clicked, toggles the light on/off.
 * The light property controls whether the block emits light.
 */
public class InteractiveNightLightBlock extends Block {
    
    public static final BooleanProperty LIT = BooleanProperty.of("lit");
    
    public InteractiveNightLightBlock(Block.Settings settings) {
        super(settings);
        // Set default state with lit=true (light on by default)
        this.setDefaultState(this.stateManager.getDefaultState()
            .with(LIT, true));
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
    
    /**
     * Handle player interaction (right-click).
     * Toggles the light on/off.
     */
    public ActionResult onUse(BlockState state, World world, BlockPos pos, 
                              PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        
        // Toggle lit state
        boolean isLit = state.get(LIT);
        BlockState newState = state.with(LIT, !isLit);
        
        world.setBlockState(pos, newState, Block.NOTIFY_ALL);
        
        return ActionResult.CONSUME;
    }
}
