package com.robertx22.library_of_exile.dimension;

import com.robertx22.library_of_exile.dimension.structure.MapStructure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.HashMap;

// todo this breaks if there's multiple structures in 1 dimension, but might not be needed
public class MapDimensions {

    // this way I can keep track of what dimensions are maps
    private static HashMap<String, MapDimensionInfo> map = new HashMap<>();

    public static boolean isMap(ResourceLocation id) {
        return map.getOrDefault(id.toString(), null) != null;
    }

    public static MapContentType getContentType(Level world) {
        return getContentType(world.dimensionTypeId().location());
    }


    public static MapContentType getContentType(ResourceLocation id) {
        var info = getInfo(id);
        if (info == null) {
            return MapContentType.EMPTY;
        }
        return info.contentType;
    }

    public static boolean isMap(Level world) {
        return isMap(world.dimensionTypeId().location());
    }

    public static MapDimensionInfo getInfo(ResourceLocation id) {
        return map.get(id.toString());
    }

    public static MapDimensionInfo getInfo(MapStructure s) {
        return map.get(s.guid());
    }

    public static MapDimensionInfo getInfo(Level world) {
        return getInfo(world.dimensionTypeId().location());
    }

    public static void register(MapDimensionInfo info) {
        map.put(info.dimensionId.toString(), info);
    }
}
