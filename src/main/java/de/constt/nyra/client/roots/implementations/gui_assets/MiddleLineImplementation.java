package de.constt.nyra.client.roots.implementations.gui_assets;

import de.constt.nyra.client.roots.implementations.GuiAssetImplementation;
import imgui.ImGui;

public class MiddleLineImplementation extends GuiAssetImplementation {
    private final Integer spacing;

    protected MiddleLineImplementation(String name, Object defaultValue, Integer spacing) {
        super(name, defaultValue);
        this.spacing = spacing;
    }

    @Override
    public void renderImGui() {
        if (spacing != null && spacing > 0) {
            ImGui.dummy(0, spacing);
        }

        ImGui.separator();

        if (spacing != null && spacing > 0) {
            ImGui.dummy(0, spacing);
        }
    }
}