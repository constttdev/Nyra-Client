package de.constt.nyra.client.panels;

import imgui.ImGui;
import imgui.flag.ImGuiStyleVar;

import java.util.Map;

public abstract class ClickGUIPanel {

    private final String name;

    public ClickGUIPanel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void render(Map<String, float[]> windowPositions, float screenWidth, float screenHeight) {

        float[] pos = windowPositions.computeIfAbsent(
                name,
                k -> new float[]{100, 100}
        );

        ImGui.setNextWindowPos(
                pos[0],
                pos[1],
                imgui.flag.ImGuiCond.FirstUseEver
        );

        ImGui.setNextWindowSize(
                220,
                400,
                imgui.flag.ImGuiCond.FirstUseEver
        );

        ImGui.pushStyleVar(
                ImGuiStyleVar.WindowPadding,
                12,
                12
        );

        if (ImGui.begin(
                name,
                        imgui.flag.ImGuiWindowFlags.NoCollapse
        )) {

            ImGui.beginChild(
                    name + "_content",
                    0,
                    0,
                    false
            );

            renderContent();

            ImGui.endChild();

            float x = ImGui.getWindowPosX();
            float y = ImGui.getWindowPosY();

            float width = ImGui.getWindowWidth();
            float height = ImGui.getWindowHeight();

            pos[0] = x;
            pos[1] = y;

            if (
                    x + width < 0 ||
                            y + height < 0 ||
                            x > screenWidth ||
                            y > screenHeight
            ) {
                pos[0] = (screenWidth - width) / 2;
                pos[1] = (screenHeight - height) / 2;
            }
        }

        ImGui.end();

        ImGui.popStyleVar();
    }

    protected abstract void renderContent();
}