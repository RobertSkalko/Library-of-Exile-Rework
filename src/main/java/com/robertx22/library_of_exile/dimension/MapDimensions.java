package com.robertx22.library_of_exile.dimension;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

public class MapDimensions {

    // this way I can keep track of what dimensions are maps
    public static HashMap<String, Boolean> map = new HashMap<>();

    public static boolean isMap(ResourceLocation id) {
        return map.getOrDefault(id.toString(), false);
    }
}
