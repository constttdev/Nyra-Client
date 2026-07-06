package de.constt.nyra.client.utils;

public final class ColorUtils {

    public static int argb(int a, int r, int g, int b) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static int withAlpha(int color, int alpha) {
        return (alpha << 24) | (color & 0x00FFFFFF);
    }

    public static int blend(int c1, int c2, float t) {
        t = Math.max(0f, Math.min(1f, t));

        int a = (int)(((c1 >>> 24) & 0xFF) * (1 - t) + ((c2 >>> 24) & 0xFF) * t);
        int r = (int)(((c1 >>> 16) & 0xFF) * (1 - t) + ((c2 >>> 16) & 0xFF) * t);
        int g = (int)(((c1 >>> 8) & 0xFF) * (1 - t) + ((c2 >>> 8) & 0xFF) * t);
        int b = (int)((c1 & 0xFF) * (1 - t) + (c2 & 0xFF) * t);

        return argb(a, r, g, b);
    }

    public static int darken(int color, float t) {
        return blend(color, 0xFF000000, t);
    }

    public static int lighten(int color, float t) {
        return blend(color, 0xFFFFFFFF, t);
    }

    public static int rgb(int r, int g, int b) {
        return argb(255, r, g, b);
    }

    public static int getHoverColor(int color) {
        return lighten(color, 0.15f);
    }

    public static void debugColor(String name, int color) {
        int a = (color >>> 24) & 0xFF;
        int r = (color >>> 16) & 0xFF;
        int g = (color >>> 8) & 0xFF;
        int b = color & 0xFF;

        System.out.println(name + " = " + String.format("0x%08X", color)
                + " | A:" + a + " R:" + r + " G:" + g + " B:" + b);
    }

    public static int imGuiColor(int argb) {
        int a = (argb >>> 24) & 0xFF;
        int r = (argb >>> 16) & 0xFF;
        int g = (argb >>> 8) & 0xFF;
        int b = argb & 0xFF;
        return (a << 24) | (b << 16) | (g << 8) | r;
    }

    public static int toImGuiColor(int argb) {
        int a = (argb >>> 24) & 0xFF;
        int r = (argb >>> 16) & 0xFF;
        int g = (argb >>> 8) & 0xFF;
        int b = argb & 0xFF;
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}