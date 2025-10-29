package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.blocks.MagnumTorchBlock;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

/**
 * Handles mob spawn prevention for Magnum Torches.
 * Each torch type prevents different categories of mobs from spawning
 * within a 32-block radius.
 */
public class MagnumTorchSpawnHandler {

    public static void register() {
        // Hook into entity loading to check for natural spawns
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            // Only check mob entities that are naturally spawning
            if (entity instanceof MobEntity mobEntity) {
                // Check if spawn should be prevented
                if (shouldPreventSpawn(mobEntity, world)) {
                    // Remove the entity before it fully spawns
                    entity.discard();
                }
            }
        });
    }

    /**
     * Check if a mob spawn should be prevented due to nearby magnum torches
     */
    private static boolean shouldPreventSpawn(MobEntity entity, ServerWorld world) {
        BlockPos spawnPos = entity.getBlockPos();
        int range = MagnumTorchBlock.SPAWN_PREVENTION_RANGE;

        // Check all blocks in a cube around the spawn position
        for (BlockPos pos : BlockPos.iterate(
                spawnPos.add(-range, -range, -range),
                spawnPos.add(range, range, range))) {

            BlockState state = world.getBlockState(pos);

            // Check if this is a magnum torch
            if (state.getBlock() instanceof MagnumTorchBlock magnumTorch) {
                // Check if this torch type prevents this entity
                if (magnumTorch.preventsSpawn(entity.getType())) {
                    return true; // Prevent spawn
                }
            }
        }

        return false; // Allow spawn
    }
}
