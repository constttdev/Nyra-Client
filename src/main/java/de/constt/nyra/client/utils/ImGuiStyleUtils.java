package de.constt.nyra.client.utils;

import imgui.ImGui;
import imgui.ImGuiStyle;

public class ImGuiStyleUtils {

    public static void applyFluentStyle(float scale) {
        ImGuiStyle style = ImGui.getStyle();

        style.setWindowPadding(14, 14);
        style.setFramePadding(10, 7);
        style.setItemSpacing(10, 8);
        style.setItemInnerSpacing(6, 6);
        style.setIndentSpacing(18.0f);
        style.setScrollbarSize(12.0f);
        style.setGrabMinSize(10.0f);

        style.setWindowBorderSize(0.0f);
        style.setChildBorderSize(0.0f);
        style.setPopupBorderSize(0.0f);
        style.setFrameBorderSize(0.0f);
        style.setTabBorderSize(0.0f);

        style.setWindowRounding(12.0f);
        style.setChildRounding(12.0f);
        style.setFrameRounding(10.0f);
        style.setPopupRounding(12.0f);
        style.setScrollbarRounding(12.0f);
        style.setGrabRounding(10.0f);
        style.setTabRounding(10.0f);

        style.scaleAllSizes(scale);
    }
}