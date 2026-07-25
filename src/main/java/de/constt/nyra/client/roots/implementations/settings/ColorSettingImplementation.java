package de.constt.nyra.client.roots.implementations.settings;

import de.constt.nyra.client.roots.implementations.SettingImplementation;
import imgui.ImGui;
import imgui.flag.ImGuiColorEditFlags;

public final class ColorSettingImplementation extends SettingImplementation<Integer> {

    private final float[] imColor = new float[4];

    public ColorSettingImplementation(String name, int defaultArgb) {
        super(name, defaultArgb);
        unpackToFloat(defaultArgb, imColor);
    }

    @Override
    public void renderImGui() {
        if (ImGui.colorEdit4(
                getName(),
                imColor,
                ImGuiColorEditFlags.AlphaBar |
                        ImGuiColorEditFlags.AlphaPreviewHalf |
                        ImGuiColorEditFlags.DisplayHex
        )) {
            set(packFromFloat(imColor));
        }
    }

    @Override
    public void set(Integer value) {
        super.set(value);
        unpackToFloat(value, imColor);
    }

    public float[] getRGBA() {
        float[] out = new float[4];
        unpackToFloat(value, out);
        return out;
    }

    private static void unpackToFloat(int argb, float[] out) {
        out[0] = ((argb >> 16) & 0xFF) / 255f;
        out[1] = ((argb >> 8) & 0xFF) / 255f;
        out[2] = (argb & 0xFF) / 255f;
        out[3] = ((argb >> 24) & 0xFF) / 255f;
    }

    private static int packFromFloat(float[] c) {
        int a = Math.round(c[3] * 255) & 0xFF;
        int r = Math.round(c[0] * 255) & 0xFF;
        int g = Math.round(c[1] * 255) & 0xFF;
        int b = Math.round(c[2] * 255) & 0xFF;

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}