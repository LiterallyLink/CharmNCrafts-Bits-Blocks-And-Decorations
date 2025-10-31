package com.charmed.charmncraft.bits.util;

import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.EnumMap;
import java.util.Map;

/**
 * Utility class for optimized VoxelShape operations.
 * Provides caching and rotation utilities to prevent runtime shape calculations.
 */
public final class VoxelShapeHelper {

    private VoxelShapeHelper() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Pre-calculates and caches rotated shapes for all 4 horizontal directions.
     * This should be called once during block initialization for optimal performance.
     *
     * @param northShape The base shape facing north
     * @return A map of cached shapes for each horizontal direction
     */
    public static Map<Direction, VoxelShape> createHorizontalRotations(VoxelShape northShape) {
        Map<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);
        shapes.put(Direction.NORTH, northShape);
        shapes.put(Direction.EAST, rotateShapeClockwise(northShape, 1));
        shapes.put(Direction.SOUTH, rotateShapeClockwise(northShape, 2));
        shapes.put(Direction.WEST, rotateShapeClockwise(northShape, 3));
        return shapes;
    }

    /**
     * Pre-calculates and caches rotated shapes for all 4 horizontal directions.
     * Uses WEST as the base orientation (for blocks whose models face west by default).
     *
     * @param westShape The base shape facing west
     * @return A map of cached shapes for each horizontal direction
     */
    public static Map<Direction, VoxelShape> createHorizontalRotationsFromWest(VoxelShape westShape) {
        Map<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);
        shapes.put(Direction.WEST, westShape);
        shapes.put(Direction.NORTH, rotateShapeClockwise(westShape, 1));
        shapes.put(Direction.EAST, rotateShapeClockwise(westShape, 2));
        shapes.put(Direction.SOUTH, rotateShapeClockwise(westShape, 3));
        return shapes;
    }

    /**
     * Rotates a VoxelShape 90 degrees clockwise around the Y axis.
     * Uses an efficient algorithm that processes the shape once per rotation.
     *
     * @param shape The shape to rotate
     * @param times Number of 90-degree clockwise rotations (1-3)
     * @return The rotated shape
     */
    public static VoxelShape rotateShapeClockwise(VoxelShape shape, int times) {
        if (times <= 0 || times > 3) {
            throw new IllegalArgumentException("Rotation times must be between 1 and 3");
        }

        VoxelShape[] buffer = {shape, VoxelShapes.empty()};

        for (int i = 0; i < times; i++) {
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

    /**
     * Creates a cuboid VoxelShape from pixel coordinates (0-16).
     * More intuitive than working with 0-1 double values.
     *
     * @param x1 Start X in pixels (0-16)
     * @param y1 Start Y in pixels (0-16)
     * @param z1 Start Z in pixels (0-16)
     * @param x2 End X in pixels (0-16)
     * @param y2 End Y in pixels (0-16)
     * @param z2 End Z in pixels (0-16)
     * @return The VoxelShape
     */
    public static VoxelShape createCuboidShape(double x1, double y1, double z1, double x2, double y2, double z2) {
        return VoxelShapes.cuboid(x1 / 16.0, y1 / 16.0, z1 / 16.0, x2 / 16.0, y2 / 16.0, z2 / 16.0);
    }
}
