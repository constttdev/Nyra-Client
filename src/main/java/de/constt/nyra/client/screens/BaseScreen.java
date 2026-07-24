package de.constt.nyra.client.screens;

import de.constt.nyra.client.utils.ColorUtils;
import de.constt.nyra.client.utils.ThemeUtils;
import foundry.imgui.api.ImGuiMC;
import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiStyleVar;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class BaseScreen extends Screen {

    protected static int bg;
    protected static int sidebar;
    protected static int accent;
    protected static int accentDim;
    protected static int accentMedium;
    protected static int text;
    protected static int textMuted;
    protected static int divider;
    protected static int scrollbar;

    public BaseScreen() {
        super(Component.literal("Base Screen"));
    }

    protected static float uiScale = 1.0f;

    private void updateUIScale() {
        float width = ImGui.getIO().getDisplaySizeX();
        float height = ImGui.getIO().getDisplaySizeY();

        float scaleX = width / 1920f;
        float scaleY = height / 1080f;

        uiScale = Math.min(scaleX, scaleY);
        uiScale = Math.clamp(uiScale, 0.7f, 1.5f);
    }

    protected float s(float value) {
        return value * uiScale;
    }

    private static void updateTheme() {
        int rawBg = ThemeUtils.getBackgroundColor();
        int rawSecondary = ThemeUtils.getSecondaryColor();
        int rawAccent = ThemeUtils.getAccentColor();
        int rawText = ThemeUtils.getTextColor();

        bg = rawBg;
        sidebar = ColorUtils.darken(rawBg, 0.2f);

        accent = rawAccent;
        accentDim = ColorUtils.withAlpha(rawAccent, 0x33);
        accentMedium = ColorUtils.withAlpha(rawAccent, 0x88);

        text = rawText;
        textMuted = ColorUtils.blend(rawText, rawSecondary, 0.6f);

        divider = ColorUtils.lighten(rawBg, 0.12f);
        scrollbar = ColorUtils.withAlpha(rawSecondary, 0xCC);
    }

    protected void reloadTheme() {
        ThemeUtils.reloadTheme();
    }

    private void pushTheme() {

        ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 12, 12);
        ImGui.pushStyleVar(ImGuiStyleVar.FramePadding, 10, 6);
        ImGui.pushStyleVar(ImGuiStyleVar.ItemSpacing, 10, 8);
        ImGui.pushStyleVar(ImGuiStyleVar.FrameRounding, 8);
        ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 10);
        ImGui.pushStyleVar(ImGuiStyleVar.ChildRounding, 8);
        ImGui.pushStyleVar(ImGuiStyleVar.PopupRounding, 8);
        ImGui.pushStyleVar(ImGuiStyleVar.ScrollbarRounding, 8);
        ImGui.pushStyleVar(ImGuiStyleVar.ScrollbarSize, 5);

        ImGui.pushStyleColor(ImGuiCol.WindowBg, ColorUtils.toImGuiColor(bg));
        ImGui.pushStyleColor(ImGuiCol.ChildBg, 0);
        ImGui.pushStyleColor(ImGuiCol.PopupBg, ColorUtils.toImGuiColor(sidebar));

        ImGui.pushStyleColor(ImGuiCol.Border, ColorUtils.toImGuiColor(divider));
        ImGui.pushStyleColor(ImGuiCol.Text, ColorUtils.toImGuiColor(text));

        ImGui.pushStyleColor(ImGuiCol.Button, ColorUtils.toImGuiColor(sidebar));
        ImGui.pushStyleColor(
                ImGuiCol.ButtonHovered,
                ColorUtils.toImGuiColor(ColorUtils.lighten(sidebar, 0.15f))
        );
        ImGui.pushStyleColor(
                ImGuiCol.ButtonActive,
                ColorUtils.toImGuiColor(accent)
        );

        ImGui.pushStyleColor(
                ImGuiCol.FrameBg,
                ColorUtils.toImGuiColor(sidebar)
        );
        ImGui.pushStyleColor(
                ImGuiCol.FrameBgHovered,
                ColorUtils.toImGuiColor(ColorUtils.lighten(sidebar, 0.15f))
        );
        ImGui.pushStyleColor(
                ImGuiCol.FrameBgActive,
                ColorUtils.toImGuiColor(accentDim)
        );
        ImGui.pushStyleColor(
                ImGuiCol.CheckMark,
                ColorUtils.toImGuiColor(accent)
        );

        ImGui.pushStyleColor(
                ImGuiCol.SliderGrab,
                ColorUtils.toImGuiColor(accent)
        );
        ImGui.pushStyleColor(
                ImGuiCol.SliderGrabActive,
                ColorUtils.toImGuiColor(ColorUtils.lighten(accent, 0.15f))
        );

        ImGui.pushStyleColor(
                ImGuiCol.Header,
                ColorUtils.toImGuiColor(accentDim)
        );
        ImGui.pushStyleColor(
                ImGuiCol.HeaderHovered,
                ColorUtils.toImGuiColor(accentMedium)
        );
        ImGui.pushStyleColor(
                ImGuiCol.HeaderActive,
                ColorUtils.toImGuiColor(accent)
        );

        ImGui.pushStyleColor(
                ImGuiCol.ScrollbarBg,
                ColorUtils.toImGuiColor(bg)
        );
        ImGui.pushStyleColor(
                ImGuiCol.ScrollbarGrab,
                ColorUtils.toImGuiColor(scrollbar)
        );
        ImGui.pushStyleColor(
                ImGuiCol.ScrollbarGrabHovered,
                ColorUtils.toImGuiColor(ColorUtils.getHoverColor(scrollbar))
        );

        ImGui.pushStyleColor(
                ImGuiCol.TitleBg,
                ColorUtils.toImGuiColor(sidebar)
        );

        ImGui.pushStyleColor(
                ImGuiCol.TitleBgActive,
                ColorUtils.toImGuiColor(accentDim)
        );

        ImGui.pushStyleColor(
                ImGuiCol.TitleBgCollapsed,
                ColorUtils.toImGuiColor(sidebar)
        );
    }

    private void popTheme() {
        ImGui.popStyleColor(23);
        ImGui.popStyleVar(9);
    }

    @Override
    public void extractRenderState(
            @NonNull GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float delta
    ) {

        try (ImGuiMC.ActiveContext ignored = ImGuiMC.withImGui()) {

            updateTheme();

            updateUIScale();

            pushTheme();

            try {
                render();
            } finally {
                popTheme();
            }
        }

        super.extractRenderState(graphics, mouseX, mouseY, delta);
    }

    public void render() {
    }
}