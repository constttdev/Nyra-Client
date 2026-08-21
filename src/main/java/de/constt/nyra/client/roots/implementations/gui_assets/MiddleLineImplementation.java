package de.constt.nyra.client.roots.implementations.gui_assets;

import de.constt.nyra.client.roots.implementations.GuiAssetImplementation;
import imgui.ImGui;
import imgui.ImVec2;

public class MiddleLineImplementation extends GuiAssetImplementation<Object> {

    private final int spacing;
    private final int length;

    public MiddleLineImplementation(
            String name,
            Object defaultValue,
            Integer spacing,
            Integer length
    ) {
        super(name, defaultValue);
        this.spacing = spacing != null ? Math.max(0, spacing) : 0;
        this.length = length != null ? Math.max(0, length) : 0;
    }

    @Override
    public void renderImGui() {
        ImVec2 cursor = ImGui.getCursorScreenPos();

        float textWidth = ImGui.calcTextSize(getName()).x;
        float textHeight = ImGui.getTextLineHeight();

        float dashWidth = ImGui.calcTextSize("-").x;
        float lineWidth = dashWidth * length;

        float spaceWidth = ImGui.calcTextSize(" ").x;
        float spacingWidth = spaceWidth * spacing;

        float totalWidth =
                lineWidth +
                        spacingWidth +
                        textWidth +
                        spacingWidth +
                        lineWidth;

        float availableWidth = ImGui.getContentRegionAvailX();

        float startX = cursor.x + Math.max(
                0,
                (availableWidth - totalWidth) * 0.5f
        );

        float centerY = cursor.y + textHeight * 0.5f;

        int lineColor = ImGui.getColorU32(
                0.45f,
                0.45f,
                0.45f,
                1.0f
        );

        ImGui.getWindowDrawList().addLine(
                startX,
                centerY,
                startX + lineWidth,
                centerY,
                lineColor
        );

        float textX =
                startX +
                        lineWidth +
                        spacingWidth;

        ImGui.getWindowDrawList().addText(
                textX,
                cursor.y,
                ImGui.getColorU32(
                        1.0f,
                        1.0f,
                        1.0f,
                        1.0f
                ),
                getName()
        );

        float rightLineX =
                textX +
                        textWidth +
                        spacingWidth;

        ImGui.getWindowDrawList().addLine(
                rightLineX,
                centerY,
                rightLineX + lineWidth,
                centerY,
                lineColor
        );

        ImGui.dummy(0, textHeight);
    }
}