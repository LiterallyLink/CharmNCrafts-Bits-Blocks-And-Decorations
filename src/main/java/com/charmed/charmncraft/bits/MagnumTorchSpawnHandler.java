package com.charmed.charmncraft.bits;

import com.charmed.charmncraft.bits.blocks.MagnumTorchBlock;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.Chunk;

/**
 * Handles mob spawn prevention for Magnum Torches.
 * Each torch type prevents different categories of mobs from spawning
 * within a 32-block radius (16 blocks vertical).
 *
 * Optimized to use chunk-based scanning and reduced vertical range.
 */
public class MagnumTorchSpawnHandler {

    // Reduced vertical range - torches don't need full 32 block vertical range
    private static final int VERTICAL_RANGE = 16;

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
     * Check if a mob spawn should be prevented due to nearby magnum torches.
     * Uses an optimized chunk-based search algorithm to minimize block checks.
     */
    private static boolean shouldPreventSpawn(MobEntity entity, ServerWorld world) {
        BlockPos spawnPos = entity.getBlockPos();
        int horizontalRange = MagnumTorchBlock.SPAWN_PREVENTION_RANGE;

        // Calculate chunk range to check
        ChunkPos spawnChunk = new ChunkPos(spawnPos);
        int chunkRadius = (horizontalRange >> 4) + 1; // Convert blocks to chunks (divide by 16)

        // Calculate Y range (reduced from full 32 to 16)
        int minY = Math.max(world.getBottomY(), spawnPos.getY() - VERTICAL_RANGE);
        int maxY = Math.min(world.getTopY() - 1, spawnPos.getY() + VERTICAL_RANGE);

        // Iterate through chunks in range (much fewer iterations than individual blocks)
        for (int chunkX = spawnChunk.x - chunkRadius; chunkX <= spawnChunk.x + chunkRadius; chunkX++) {
            for (int chunkZ = spawnChunk.z - chunkRadius; chunkZ <= spawnChunk.z + chunkRadius; chunkZ++) {

                // Skip chunks that aren't loaded (no torches can be there)
                if (!world.isChunkLoaded(chunkX, chunkZ)) {
                    continue;
                }

                Chunk chunk = world.getChunk(chunkX, chunkZ);

                // Calculate the actual block positions to check within this chunk
                int minX = Math.max(chunkX << 4, spawnPos.getX() - horizontalRange);
                int maxX = Math.min((chunkX << 4) + 15, spawnPos.getX() + horizontalRange);
                int minZ = Math.max(chunkZ << 4, spawnPos.getZ() - horizontalRange);
                int maxZ = Math.min((chunkZ << 4) + 15, spawnPos.getZ() + horizontalRange);

                // Check blocks in this chunk section
                for (int x = minX; x <= maxX; x++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        // Pre-calculate squared distance for early exit (avoid sqrt)
                        int dx = x - spawnPos.getX();
                        int dz = z - spawnPos.getZ();
                        int distanceSquared = dx * dx + dz * dz;

                        // Skip if outside circular range (more accurate than cube)
                        if (distanceSquared > horizontalRange * horizontalRange) {
                            continue;
                        }

                        for (int y = minY; y <= maxY; y++) {
                            BlockPos pos = new BlockPos(x, y, z);
                            BlockState state = chunk.getBlockState(pos);

                            // Check if this is a magnum torch
                            if (state.getBlock() instanceof MagnumTorchBlock magnumTorch) {
                                // Check if this torch type prevents this entity
                                if (magnumTorch.preventsSpawn(entity.getType())) {
                                    return true; // Prevent spawn (early exit)
                                }
                            }
                        }
                    }
                }
            }
        }

        return false; // Allow spawn
    }
}
