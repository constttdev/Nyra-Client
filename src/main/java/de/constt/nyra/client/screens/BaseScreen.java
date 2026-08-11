package de.constt.nyra.client.screens;

import de.constt.nyra.client.utils.ColorUtils;
import de.constt.nyra.client.utils.ThemeUtils;
import foundry.imgui.api.ImGuiMC;
import imgui.ImFont;
import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiStyleVar;
//~if <26.1 GuiGraphicsExtractor -> GuiGraphics
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
//?if >1.21.11
import net.minecraft.resources.Identifier;
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

        // Slightly darker secondary surface for panels/sidebar.
        sidebar = ColorUtils.darken(rawBg, 0.22f);

        accent = rawAccent;
        accentDim = ColorUtils.withAlpha(rawAccent, 0x2A);
        accentMedium = ColorUtils.withAlpha(rawAccent, 0x66);

        text = rawText;
        textMuted = ColorUtils.blend(rawText, rawSecondary, 0.6f);

        // Keep borders subtle but visible.
        divider = ColorUtils.lighten(rawBg, 0.10f);

        scrollbar = ColorUtils.withAlpha(rawSecondary, 0xCC);
    }

    protected void reloadTheme() {
        ThemeUtils.reloadTheme();
    }

    private void pushTheme() {

        /*
         * Meteor/Future-style:
         * - Compact spacing
         * - Almost completely square components
         * - Thin borders
         * - Less "modern app" and more Minecraft client UI
         */
        ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 10, 10);
        ImGui.pushStyleVar(ImGuiStyleVar.FramePadding, 8, 5);
        ImGui.pushStyleVar(ImGuiStyleVar.ItemSpacing, 8, 6);

        // Keep everything mostly square.
        ImGui.pushStyleVar(ImGuiStyleVar.FrameRounding, 2);
        ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 2);
        ImGui.pushStyleVar(ImGuiStyleVar.ChildRounding, 2);
        ImGui.pushStyleVar(ImGuiStyleVar.PopupRounding, 2);
        ImGui.pushStyleVar(ImGuiStyleVar.ScrollbarRounding, 1);

        // Thin, compact scrollbar.
        ImGui.pushStyleVar(ImGuiStyleVar.ScrollbarSize, 4);

        ImGui.pushStyleColor(
                ImGuiCol.WindowBg,
                ColorUtils.toImGuiColor(bg)
        );

        ImGui.pushStyleColor(
                ImGuiCol.ChildBg,
                0
        );

        ImGui.pushStyleColor(
                ImGuiCol.PopupBg,
                ColorUtils.toImGuiColor(sidebar)
        );

        // Subtle outlines around UI elements.
        ImGui.pushStyleColor(
                ImGuiCol.Border,
                ColorUtils.toImGuiColor(divider)
        );

        ImGui.pushStyleColor(
                ImGuiCol.Text,
                ColorUtils.toImGuiColor(text)
        );

        /*
         * Buttons
         */
        ImGui.pushStyleColor(
                ImGuiCol.Button,
                ColorUtils.toImGuiColor(sidebar)
        );

        ImGui.pushStyleColor(
                ImGuiCol.ButtonHovered,
                ColorUtils.toImGuiColor(
                        ColorUtils.lighten(sidebar, 0.12f)
                )
        );

        ImGui.pushStyleColor(
                ImGuiCol.ButtonActive,
                ColorUtils.toImGuiColor(accent)
        );

        /*
         * Input fields / frames
         */
        ImGui.pushStyleColor(
                ImGuiCol.FrameBg,
                ColorUtils.toImGuiColor(sidebar)
        );

        ImGui.pushStyleColor(
                ImGuiCol.FrameBgHovered,
                ColorUtils.toImGuiColor(
                        ColorUtils.lighten(sidebar, 0.12f)
                )
        );

        ImGui.pushStyleColor(
                ImGuiCol.FrameBgActive,
                ColorUtils.toImGuiColor(accentDim)
        );

        ImGui.pushStyleColor(
                ImGuiCol.CheckMark,
                ColorUtils.toImGuiColor(accent)
        );

        /*
         * Sliders
         */
        ImGui.pushStyleColor(
                ImGuiCol.SliderGrab,
                ColorUtils.toImGuiColor(accent)
        );

        ImGui.pushStyleColor(
                ImGuiCol.SliderGrabActive,
                ColorUtils.toImGuiColor(
                        ColorUtils.lighten(accent, 0.15f)
                )
        );

        /*
         * Headers / selectable elements
         */
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

        /*
         * Scrollbar
         */
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
                ColorUtils.toImGuiColor(
                        ColorUtils.getHoverColor(scrollbar)
                )
        );

        /*
         * Window title bar
         */
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
    //~if <26.1 extractRenderState -> render
    public void extractRenderState(
            //~if <26.1 GuiGraphicsExtractor -> GuiGraphics
            //~ if <=1.21.11 '@NonNull GuiGraphicsExtractor graphics,' -> 'GuiGraphics graphics,'
            @NonNull GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float delta
    ) {

        try (ImGuiMC.ActiveContext ignored = ImGuiMC.withImGui()) {

            ImFont font = ImGuiMC.getFont(
                    Identifier.parse("nyra:mcfont"),
                    false,
                    false
            );

            ImGui.pushFont(font, 18);
            ImGui.popFont();

            try (var ctx = ImGuiMC.withImGui()) {
                ctx.io().setFontDefault(font);
            }

            updateTheme();
            updateUIScale();

            pushTheme();

            try {
                render();
            } finally {
                popTheme();
            }
        }

        //~if <26.1 extractRenderState -> render
        super.extractRenderState(graphics, mouseX, mouseY, delta);
    }

    public void render() {
    }
}
