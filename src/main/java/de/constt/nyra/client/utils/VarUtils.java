package de.constt.nyra.client.utils;

import net.fabricmc.loader.api.FabricLoader;

public class VarUtils {
    public static String getModID() {
        String modId = FabricLoader.getInstance()
                .getModContainer("nyra")
                .map(mod -> mod.getMetadata().getId())
                .orElse(null);

        return modId;
    }
}
