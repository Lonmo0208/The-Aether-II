package com.aetherteam.aetherii.world;

import com.aetherteam.aetherii.AetherII;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class DynamicWorldLights {
    private static final Object2IntArrayMap<BlockPos> DYNAMIC_LIGHT_SOURCES = new Object2IntArrayMap<>(); //todo globalpos

    public static void entityTick(Entity entity) {
        Level level = entity.level();
        BlockPos pos = entity.blockPosition();
        if (hasDynamicLightSource(entity)) {
            int lightLevel = 12; //todo replace 12 with a value determined by a dynamic light item; maybe an item attachment?
            int trackedLightLevel = DYNAMIC_LIGHT_SOURCES.getInt(pos);
            if (trackedLightLevel <= 0) {
                addLight(level, pos, lightLevel);
            } else if (DYNAMIC_LIGHT_SOURCES.getInt(pos) < lightLevel) {
                addLight(level, pos, calculateLightEmission(lightLevel, trackedLightLevel));
            }
        }
    }

    public static void levelTick(Level level) {
        if (level.dimension() == Level.OVERWORLD) {
            for (BlockPos pos : DYNAMIC_LIGHT_SOURCES.keySet()) { //todo
                List<Entity> entities = level.getEntities(null, AABB.encapsulatingFullBlocks(pos, pos));
                if (entities.isEmpty()) {
                    removeLight(level, pos);
                }
            }
        }
    }

    protected static void addLight(Level level, BlockPos pos, int light) {
        DYNAMIC_LIGHT_SOURCES.put(pos, light);
        updateLighting(level, pos);
    }

    protected static void removeLight(Level level, BlockPos pos) {
        DYNAMIC_LIGHT_SOURCES.removeInt(pos);
        updateLighting(level, pos);
    }

    protected static void updateLighting(Level level, BlockPos pos) {
        LevelChunk chunk = level.getChunkAt(pos);
        int j = pos.getX() & 15;
        int l = pos.getZ() & 15;
        chunk.getSkyLightSources().update(chunk, j, pos.getY(), l);
        chunk.getLevel().getChunkSource().getLightEngine().checkBlock(pos);

//        for (Direction direction : Direction.Plane.HORIZONTAL) {
//            for (int i = 0; i < 4; i++) {
//                BlockPos nearbyPos = pos.relative(direction, i);
//                chunk = level.getChunkAt(nearbyPos);
//                j = nearbyPos.getX() & 15;
//                l = nearbyPos.getZ() & 15;
//                chunk.getSkyLightSources().update(chunk, j, nearbyPos.getY(), l);
//                chunk.getLevel().getChunkSource().getLightEngine().checkBlock(nearbyPos);
//            }
//        }
    }

    protected static boolean hasDynamicLightSource(Entity entity) {
        return entity instanceof Player; //todo check for dynamic light items and stuff;
    }

    public static int getDynamicLight(BlockPos pos) {
        return DYNAMIC_LIGHT_SOURCES.getInt(pos);
    }

    public static int calculateLightEmission(int original, int other) {
        return Math.min(original + other, 15);
    }
}
