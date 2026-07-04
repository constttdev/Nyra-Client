package de.constt.nyra.client.utils;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.ImVec4;
import imgui.flag.ImGuiCol;

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

        ImVec4[] c = style.getColors();

        ImVec4 bg = new ImVec4(0.96f, 0.96f, 0.98f, 1.0f);
        ImVec4 panel = new ImVec4(0.90f, 0.90f, 0.94f, 1.0f);
        ImVec4 hover = new ImVec4(0.82f, 0.82f, 0.88f, 1.0f);
        ImVec4 accent = new ImVec4(0.20f, 0.55f, 0.95f, 1.0f);

        c[ImGuiCol.WindowBg] = bg;
        c[ImGuiCol.ChildBg] = panel;
        c[ImGuiCol.PopupBg] = bg;

        c[ImGuiCol.FrameBg] = panel;
        c[ImGuiCol.FrameBgHovered] = hover;
        c[ImGuiCol.FrameBgActive] = accent;

        c[ImGuiCol.TitleBg] = bg;
        c[ImGuiCol.TitleBgActive] = bg;

        c[ImGuiCol.Button] = panel;
        c[ImGuiCol.ButtonHovered] = hover;
        c[ImGuiCol.ButtonActive] = accent;

        c[ImGuiCol.Header] = panel;
        c[ImGuiCol.HeaderHovered] = hover;
        c[ImGuiCol.HeaderActive] = accent;

        c[ImGuiCol.Separator] = new ImVec4(0.75f, 0.75f, 0.75f, 1.0f);
        c[ImGuiCol.Border] = new ImVec4(0, 0, 0, 0);

        c[ImGuiCol.ScrollbarBg] = panel;
        c[ImGuiCol.ScrollbarGrab] = hover;
        c[ImGuiCol.ScrollbarGrabHovered] = accent;
        c[ImGuiCol.ScrollbarGrabActive] = accent;
    }
}