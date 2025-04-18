package com.robertx22.library_of_exile.dimension;

import com.robertx22.library_of_exile.main.ExileLog;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Random;

public class MapGenerationUTIL {
    public static Random createRandom(Level level, ChunkPos start) {
        long worldSeed = level.getServer().getWorldData().worldGenOptions().seed();
        int chunkX = start.x;
        int chunkZ = start.z;
        long newSeed = (worldSeed + (long) (chunkX * chunkX * 4987142) + (long) (chunkX * 5947611) + (long) (chunkZ * chunkZ) * 4392871L + (long) (chunkZ * 389711) ^ worldSeed);
        return new Random(newSeed);
    }

    public static Random createRandom(ChunkPos start) {
        return createRandom(ServerLifecycleHooks.getCurrentServer().overworld(), start);
    }

    public static boolean spawnStructure(ServerLevelAccessor level, ChunkPos cpos, StructureTemplateManager man, int y, ResourceLocation room, boolean errorIfMissing) {

        try {

            var opt = man.get(room);
            if (opt.isPresent()) {
                var template = opt.get();
                StructurePlaceSettings settings = new StructurePlaceSettings().setMirror(Mirror.NONE).setIgnoreEntities(false);
                settings.setBoundingBox(settings.getBoundingBox());
                BlockPos position = cpos.getBlockAt(0, y, 0);

                if (template == null) {
                    if (errorIfMissing) {
                        ExileLog.get().warn("FATAL ERROR: Structure does not exist (" + room.toString() + ")");
                    }
                    return false;
                }
                if (template.getSize().getX() > 16 || template.getSize().getZ() > 16) {
                    ExileLog.get().warn("FATAL ERROR: Structure is bigger than possible (" + room + ") " + template.getSize().toString());
                    return false;
                }

                settings.setRotation(Rotation.NONE);

                template.placeInWorld(level, position, position, settings, level.getRandom(), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
            } else {
                if (errorIfMissing) {

                    ExileLog.get().warn("FATAL ERROR: Structure does not exist (" + room.toString() + ")");
                }
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
