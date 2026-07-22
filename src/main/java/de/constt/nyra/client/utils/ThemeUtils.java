package de.constt.nyra.client.utils;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public final class ThemeUtils {

    private ThemeUtils() {}

    private static final Path THEMES_DIR = FabricLoader.getInstance()
            .getConfigDir()
            .resolve(VarUtils.getModID())
            .resolve("themes");

    private static final Path DEFAULT_THEME = THEMES_DIR.resolve("default.cfg");

    private static final int DEFAULT_BG = 0xE0121218;
    private static final int DEFAULT_SECONDARY = 0xFF252530;
    private static final int DEFAULT_ACCENT = 0xFFFF3344;
    private static final int DEFAULT_TEXT = 0xFFF2F2F2;

    private static final int[] DEFAULT_PALETTE = {
            0xFF0D0D12,
            0xFF1A080C,
            0xFF24151A,
            0xFF8A1C2B,
            0xFF4A1018,
            0xFF15151D
    };

    public static int accent = DEFAULT_ACCENT;
    public static int secondary = DEFAULT_SECONDARY;
    public static int bg = DEFAULT_BG;
    public static int text = DEFAULT_TEXT;

    public static int[] backgroundPalette = DEFAULT_PALETTE.clone();

    public static String animationStyle = "Marble Swirl";

    private static boolean loaded;
    private static String activeTheme = "default";


    public static int getBackgroundColor() {
        ensureLoaded();
        return bg;
    }

    public static int getSecondaryColor() {
        ensureLoaded();
        return secondary;
    }

    public static int getAccentColor() {
        ensureLoaded();
        return accent;
    }

    public static int getTextColor() {
        ensureLoaded();
        return text;
    }

    public static int[] getBackgroundPalette() {
        ensureLoaded();
        return backgroundPalette;
    }

    public static String getAnimationStyle() {
        ensureLoaded();
        return animationStyle;
    }


    public static void setBackgroundColor(int value) {
        bg = value;
        saveTheme();
    }

    public static void setSecondaryColor(int value) {
        secondary = value;
        saveTheme();
    }

    public static void setAccentColor(int value) {
        accent = value;
        saveTheme();
    }

    public static void setTextColor(int value) {
        text = value;
        saveTheme();
    }


    public static void setPaletteColor(int index, int value) {
        if (index >= 0 && index < backgroundPalette.length) {
            backgroundPalette[index] = value;
            saveTheme();
        }
    }

    public static void setBackgroundPalette(int[] palette) {
        if (palette != null && palette.length == backgroundPalette.length) {
            System.arraycopy(palette, 0, backgroundPalette, 0, palette.length);
            saveTheme();
        }
    }


    public static List<String> listThemes() {
        ensureLoaded();

        List<String> result = new ArrayList<>();
        result.add("default");

        if (!Files.exists(THEMES_DIR))
            return result;

        try (Stream<Path> files = Files.list(THEMES_DIR)) {
            files.filter(f -> f.toString().endsWith(".cfg"))
                    .map(f -> f.getFileName().toString().replace(".cfg", ""))
                    .filter(n -> !n.equals("default"))
                    .sorted()
                    .forEach(result::add);

        } catch (IOException ignored) {}

        return result;
    }


    public static boolean saveThemeAs(String name) {
        if (name == null || name.isBlank())
            return false;

        name = sanitize(name);

        if (name.equals("default"))
            return false;

        try {
            Files.createDirectories(THEMES_DIR);
            Files.writeString(
                    THEMES_DIR.resolve(name + ".cfg"),
                    serialize()
            );
            return true;

        } catch (IOException e) {
            return false;
        }
    }


    public static boolean loadNamedTheme(String name) {
        if (name == null || name.isBlank())
            return false;

        if (name.equals("default")) {
            resetToDefault();
            return true;
        }

        Path path = THEMES_DIR.resolve(sanitize(name) + ".cfg");

        if (!Files.exists(path))
            return false;

        try {
            apply(parse(path));
            activeTheme = name;
            saveTheme();
            return true;

        } catch (Exception e) {
            return false;
        }
    }


    public static boolean deleteTheme(String name) {
        if (name == null || name.equals("default"))
            return false;

        try {
            return Files.deleteIfExists(
                    THEMES_DIR.resolve(sanitize(name) + ".cfg")
            );

        } catch (IOException e) {
            return false;
        }
    }


    public static void reloadTheme() {
        loaded = false;
        ensureLoaded();
    }


    public static void resetToDefault() {
        bg = DEFAULT_BG;
        secondary = DEFAULT_SECONDARY;
        accent = DEFAULT_ACCENT;
        text = DEFAULT_TEXT;

        backgroundPalette = DEFAULT_PALETTE.clone();
        animationStyle = "Marble Swirl";

        activeTheme = "default";

        saveTheme();
    }


    private static void ensureLoaded() {
        if (loaded)
            return;

        loaded = true;

        try {
            if (Files.exists(DEFAULT_THEME)) {
                apply(parse(DEFAULT_THEME));
            } else {
                saveTheme();
            }

        } catch (Exception ignored) {}
    }


    public static void saveTheme() {
        try {
            Files.createDirectories(THEMES_DIR);
            Files.writeString(DEFAULT_THEME, serialize());

            if (!activeTheme.equals("default")) {
                Files.writeString(
                        THEMES_DIR.resolve(activeTheme + ".cfg"),
                        serialize()
                );
            }

        } catch (IOException ignored) {}
    }


    private static Map<String,String> parse(Path file) throws IOException {
        Map<String,String> values = new HashMap<>();

        for (String line : Files.readAllLines(file)) {
            if (!line.contains("="))
                continue;

            String[] split = line.split("=", 2);
            values.put(split[0], split[1]);
        }

        return values;
    }


    private static void apply(Map<String,String> values) {
        bg = color(values,"backgroundColor",bg);
        secondary = color(values,"secondaryColor",secondary);
        accent = color(values,"accentColor",accent);
        text = color(values,"textColor",text);

        for (int i = 0; i < backgroundPalette.length; i++) {
            backgroundPalette[i] =
                    color(values,"paletteColor"+i,backgroundPalette[i]);
        }

        animationStyle =
                values.getOrDefault("animationStyle", animationStyle);
    }


    private static int color(Map<String,String> map,String key,int fallback) {
        try {
            String value = map.get(key);

            if (value == null)
                return fallback;

            return (int)Long.parseLong(
                    value.replace("0x",""),
                    16
            );

        } catch (Exception e) {
            return fallback;
        }
    }


    private static String serialize() {
        StringBuilder out = new StringBuilder();

        out.append("backgroundColor=0x").append(hex(bg)).append('\n');
        out.append("secondaryColor=0x").append(hex(secondary)).append('\n');
        out.append("accentColor=0x").append(hex(accent)).append('\n');
        out.append("textColor=0x").append(hex(text)).append('\n');

        for (int i = 0; i < backgroundPalette.length; i++) {
            out.append("paletteColor")
                    .append(i)
                    .append("=0x")
                    .append(hex(backgroundPalette[i]))
                    .append('\n');
        }

        out.append("animationStyle=").append(animationStyle);

        return out.toString();
    }


    private static String hex(int value) {
        return String.format("%08X", value);
    }


    private static String sanitize(String name) {
        return name.trim().replaceAll("[^a-zA-Z0-9_-]", "_");
    }


    public static int getColorWithAlpha(int color,int alpha) {
        return (alpha << 24) | (color & 0xFFFFFF);
    }


    public static int blendColors(int a,int b,float t) {
        t = Math.max(0,Math.min(1,t));

        int alpha = (int)(((a>>>24)&255)*(1-t)+((b>>>24)&255)*t);
        int red = (int)(((a>>16)&255)*(1-t)+((b>>16)&255)*t);
        int green = (int)(((a>>8)&255)*(1-t)+((b>>8)&255)*t);
        int blue = (int)((a&255)*(1-t)+(b&255)*t);

        return (alpha<<24)|(red<<16)|(green<<8)|blue;
    }


    public static int darkenColor(int color,float amount) {
        return blendColors(color,0xFF000000,amount);
    }


    public static int lightenColor(int color,float amount) {
        return blendColors(color,0xFFFFFFFF,amount);
    }


    public static int getHoverColor(int color) {
        return lightenColor(color,0.15f);
    }


    public static int getPressedColor(int color) {
        return darkenColor(color,0.2f);
    }
}