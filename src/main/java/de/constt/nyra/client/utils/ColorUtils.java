package de.constt.nyra.client.utils;

public final class ColorUtils {

    private ColorUtils() {}

    public static int argb(int a, int r, int g, int b) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }


    public static int rgb(int r, int g, int b) {
        return argb(255, r, g, b);
    }


    public static int withAlpha(int color, int alpha) {
        return (alpha << 24) | (color & 0x00FFFFFF);
    }


    public static int blend(int first, int second, float amount) {
        amount = clamp(amount);

        int a = lerp(
                (first >>> 24) & 0xFF,
                (second >>> 24) & 0xFF,
                amount
        );

        int r = lerp(
                (first >>> 16) & 0xFF,
                (second >>> 16) & 0xFF,
                amount
        );

        int g = lerp(
                (first >>> 8) & 0xFF,
                (second >>> 8) & 0xFF,
                amount
        );

        int b = lerp(
                first & 0xFF,
                second & 0xFF,
                amount
        );

        return argb(a, r, g, b);
    }


    public static int darken(int color, float amount) {
        return blend(color, 0xFF000000, amount);
    }


    public static int lighten(int color, float amount) {
        return blend(color, 0xFFFFFFFF, amount);
    }


    public static int getHoverColor(int color) {
        return lighten(color, 0.15f);
    }


    public static int getPressedColor(int color) {
        return darken(color, 0.2f);
    }


    public static int toImGuiColor(int argb) {
        int a = (argb >>> 24) & 0xFF;
        int r = (argb >>> 16) & 0xFF;
        int g = (argb >>> 8) & 0xFF;
        int b = argb & 0xFF;

        return (a << 24)
                | (b << 16)
                | (g << 8)
                | r;
    }


    private static int lerp(int a, int b, float t) {
        return (int)(a + (b - a) * t);
    }


    private static float clamp(float value) {
        return Math.max(0f, Math.min(1f, value));
    }
}