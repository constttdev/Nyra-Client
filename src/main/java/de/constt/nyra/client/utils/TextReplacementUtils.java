package de.constt.nyra.client.utils;

import net.minecraft.SharedConstants;

import java.util.HashMap;

public class TextReplacementUtils {
    private static final HashMap<String, String> textReplacements = new HashMap<>();

    public static void init() {
        //~ if <1.21.6 'name()' -> 'getName()'
        textReplacements.put("%mc_version%", SharedConstants.getCurrentVersion().name());
    }

    public static String replace(String input) {
        return textReplacements.getOrDefault(input, input);
    }
}
