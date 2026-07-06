package de.constt.nyra.client.screens;

import de.constt.nyra.client.utils.ColorUtils;
import de.constt.nyra.client.utils.ThemeUtils;
import de.constt.nyra.client.utils.VarUtils;
import foundry.imgui.api.ImGuiMC;
import imgui.ImFont;
import imgui.ImFontAtlas;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiStyleVar;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class BaseScreen extends Screen {

    public BaseScreen() {
        super(Component.literal("Base Screen"));
    }

    private static boolean fontsLoaded = false;

    static ImFont regularFont;
    static ImFont subRegularFont;
    static ImFont boldFont;
    static ImFont titleFont;

    public static void initFonts(ImGuiIO io) {
        if (fontsLoaded) return;
        try {
            ImFontAtlas atlas = io.getFonts();
            try (var is = BaseScreen.class.getResourceAsStream(
                    "/assets/" + VarUtils.getModID() + "/fonts/Rubik-VariableFont_wght.ttf")) {

                if (is == null) throw new RuntimeException("Font not found");

                byte[] fontData = is.readAllBytes();

                regularFont = atlas.addFontFromMemoryTTF(fontData, 16f);
                subRegularFont = atlas.addFontFromMemoryTTF(fontData, 19f);
                boldFont = atlas.addFontFromMemoryTTF(fontData, 20f);
                titleFont = atlas.addFontFromMemoryTTF(fontData, 21f);

                atlas.build();
                fontsLoaded = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static int COL_BG;
    static int COL_SIDEBAR;
    static int COL_ACCENT;
    static int COL_ACCENT_DIM;
    static int COL_ACCENT_MED;
    static int COL_TEXT;
    static int COL_TEXT_MUTED;
    static int COL_TEXT_INACTIVE;
    static int COL_DIVIDER;
    static int COL_SCROLLBAR;
    static final int COL_TRANSPARENT = 0x00000000;

    private static float clamp(float v, float min, float max) {
        return Math.clamp(max, min, v);
    }

    private static void refreshThemeColors() {
        int rawBg = ThemeUtils.getBackgroundColor();
        int rawAccent = ThemeUtils.getAccentColor();
        int rawSecond = ThemeUtils.getSecondaryColor();
        int rawText = ThemeUtils.getTextColor();

        COL_BG = rawBg;
        COL_SIDEBAR = ColorUtils.darken(rawBg, 0.18f);

        COL_ACCENT = rawAccent;
        COL_ACCENT_DIM = ColorUtils.withAlpha(rawAccent, 0x33);
        COL_ACCENT_MED = ColorUtils.withAlpha(rawAccent, 0x88);

        COL_TEXT = rawText;
        COL_TEXT_MUTED = ColorUtils.blend(rawText, rawSecond, 0.60f);
        COL_TEXT_INACTIVE = ColorUtils.blend(rawText, rawBg, 0.75f);

        COL_DIVIDER = ColorUtils.lighten(rawBg, 0.12f);
        COL_SCROLLBAR = ColorUtils.withAlpha(rawSecond, 0xCC);

        ColorUtils.debugColor("TEXT_RAW", rawText);
        ColorUtils.debugColor("ACCENT_RAW", rawAccent);
        ColorUtils.debugColor("COL_TEXT_FINAL", COL_TEXT);
    }

    protected ImFont getBoldFont() { return boldFont; }
    protected ImFont getTitleFont() { return titleFont; }
    protected void reloadTheme() { ThemeUtils.reloadTheme(); }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        try (ImGuiMC.ActiveContext ctx = ImGuiMC.withImGui()) {
            if (ctx != null) {

                refreshThemeColors();

                ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 0f, 0f);
                ImGui.pushStyleVar(ImGuiStyleVar.FrameRounding, 5f);
                ImGui.pushStyleVar(ImGuiStyleVar.ScrollbarRounding, 6f);
                ImGui.pushStyleVar(ImGuiStyleVar.ScrollbarSize, 4f);
                ImGui.pushStyleVar(ImGuiStyleVar.ItemSpacing, 8f, 6f);

                ImGui.pushStyleColor(ImGuiCol.WindowBg, ColorUtils.toImGuiColor(COL_BG));
                ImGui.pushStyleColor(ImGuiCol.Border, ColorUtils.toImGuiColor(COL_DIVIDER));
                ImGui.pushStyleColor(ImGuiCol.Text, ColorUtils.toImGuiColor(COL_TEXT));

                ImGui.pushStyleColor(ImGuiCol.ScrollbarBg, 0x00000000);
                ImGui.pushStyleColor(ImGuiCol.ScrollbarGrab, ColorUtils.toImGuiColor(COL_SCROLLBAR));
                ImGui.pushStyleColor(ImGuiCol.ScrollbarGrabHovered, ColorUtils.toImGuiColor(ColorUtils.getHoverColor(COL_SCROLLBAR)));
                // ImGui.pushStyleColor(ImGuiCol.ScrollbarGrabActive, ColorUtils.toImGuiColor(ColorUtils.getPressedColor(COL_SCROLLBAR))); .getPressedColor() doesnt exsist

                ImGui.pushStyleColor(ImGuiCol.PopupBg, ColorUtils.toImGuiColor(COL_SIDEBAR));
                ImGui.pushStyleColor(ImGuiCol.ChildBg, 0x00000000);

                ImGui.popStyleColor();
                ImGui.popStyleColor();
                ImGui.popStyleColor();
                ImGui.popStyleColor();
                ImGui.popStyleColor();
                ImGui.popStyleColor();
                // ImGui.popStyleColor();
                ImGui.popStyleColor();
                ImGui.popStyleColor();
                ImGui.popStyleVar(5);

                // RENDERING
                ImGui.text("BASESCREEN TEXT");
                render();
            }
        }
        super.extractRenderState(graphics, mouseX, mouseY, a);
    }

    public void render() {}
}