package de.constt.nyra.client.screens;

import de.constt.nyra.client.panels.ClickGUIPanel;
import de.constt.nyra.client.panels.FriendsPanel;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.SettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.misc.ClickGUIModule;
import de.constt.nyra.client.utils.ConfigManagerUtils;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;
import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiStyleVar;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClickGUIScreen extends BaseScreen {

    private final Set<ModuleImplementation> expandedModules = new HashSet<>();
    private ModuleImplementation listeningModule = null;

    private final Map<String, float[]> windowPositions = new HashMap<>();

    private final ClickGUIPanel[] panels = {
            new FriendsPanel()
    };

    private void renderWindowPosition(String name, float defaultX, float defaultY, float width, float height) {

        float[] pos = windowPositions.computeIfAbsent(
                name,
                k -> new float[]{defaultX, defaultY}
        );

        ImGui.setNextWindowPos(
                pos[0],
                pos[1],
                ImGuiCond.FirstUseEver
        );

        ImGui.setNextWindowSize(
                width,
                height,
                ImGuiCond.FirstUseEver
        );
    }

    private void updateWindowPosition(String name, float screenWidth, float screenHeight) {

        float[] pos = windowPositions.computeIfAbsent(
                name,
                k -> new float[]{0, 0}
        );

        float x = ImGui.getWindowPosX();
        float y = ImGui.getWindowPosY();

        float width = ImGui.getWindowWidth();
        float height = ImGui.getWindowHeight();

        pos[0] = x;
        pos[1] = y;

        boolean fullyOutside =
                x + width < 0 ||
                        y + height < 0 ||
                        x > screenWidth ||
                        y > screenHeight;

        if (fullyOutside) {
            pos[0] = (screenWidth - width) / 2;
            pos[1] = (screenHeight - height) / 2;
        }
    }

    @Override
    public void render() {
        float screenWidth = ImGui.getIO().getDisplaySizeX();
        float screenHeight = ImGui.getIO().getDisplaySizeY();

        int categories = CategoryImplementation.Categories.values().length;

        float spacing = 20;
        float width = Math.max(220, (screenWidth - spacing * (categories + 1)) / categories);
        float height = screenHeight - 120;

        int index = 0;

        if (listeningModule != null) {
            //~ if <1.21.9 '.getWindow().handle()' -> '.getWindow().getWindow()'
            long window = Minecraft.getInstance().getWindow().handle();

            for (int key = GLFW.GLFW_KEY_SPACE; key <= GLFW.GLFW_KEY_LAST; key++) {
                if (GLFW.glfwGetKey(window, key) == GLFW.GLFW_PRESS) {

                    if (key == GLFW.GLFW_KEY_ESCAPE) {
                        listeningModule.keyBindingCode = GLFW.GLFW_KEY_UNKNOWN;
                        ConfigManagerUtils.removeKeybind(listeningModule);
                    } else {
                        listeningModule.keyBindingCode = key;
                        ConfigManagerUtils.addKeybind(listeningModule, key);
                    }

                    listeningModule = null;
                    break;
                }
            }
        }

        for (CategoryImplementation.Categories category : CategoryImplementation.Categories.values()) {

            renderWindowPosition(
                    category.name(),
                    spacing + index * (width + spacing),
                    60,
                    width,
                    height
            );

            ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 12, 12);

            if (ImGui.begin(
                    category.name(),
                            imgui.flag.ImGuiWindowFlags.NoCollapse
            )) {

                ImGui.beginChild(
                        category.name() + "_content",
                        0,
                        0,
                        false
                );

                for (ModuleImplementation module : ModuleManager.getModules()) {

                    if (ModuleManager.getCategory(module.getClass()) != category)
                        continue;

                    drawModule(module);
                }

                ImGui.endChild();
            }

            ImGui.end();

            updateWindowPosition(
                    category.name(),
                    screenWidth,
                    screenHeight
            );

            ImGui.popStyleVar();

            index++;
        }

        for (ClickGUIPanel panel : panels) {

            renderWindowPosition(
                    panel.getName(),
                    spacing + index * (width + spacing),
                    60,
                    width,
                    height
            );

            panel.render(
                    windowPositions,
                    screenWidth,
                    screenHeight
            );

            updateWindowPosition(
                    panel.getName(),
                    screenWidth,
                    screenHeight
            );

            index++;
        }
    }


    private void drawModule(ModuleImplementation module) {

        boolean expanded = expandedModules.contains(module);

        String label = module.getTranslatableText();

        if (module.isEnabled()) {
            label += " [ON]";
        }

        boolean moduleClicked = ImGui.button(
                label,
                ImGui.getContentRegionAvailX() - 80,
                32
        );

        if (ImGui.isItemClicked(1)) {
            if (expanded) {
                expandedModules.remove(module);
            } else {
                expandedModules.add(module);
            }
        } else if (moduleClicked) {
            module.toggle();
        }

        ImGui.sameLine();

        boolean isListening = listeningModule == module;

        if (isListening) {
            ImGui.pushStyleColor(
                    imgui.flag.ImGuiCol.Button,
                    0.8f,
                    0.2f,
                    0.2f,
                    1.0f
            );
        }

        String bindText = isListening
                ? "Press..."
                : getBindLabel(module);

        if (ImGui.button(bindText + "##bind_" + module.getTranslatableText(), 70, 32)) {
            listeningModule = isListening ? null : module;
        }

        if (isListening) {
            ImGui.popStyleColor();
        }

        if (expanded) {
            ImGui.pushStyleVar(
                    ImGuiStyleVar.ChildRounding,
                    6
            );

            ImGui.beginChild(
                    module.getTranslatableText() + "_settings",
                    0,
                    calculateSettingsHeight(module),
                    true
            );

            ImGui.text(
                    ModuleAnnotationUtils.getDescription(
                            module.getClass()
                    )
            );

            ImGui.spacing();

            for (SettingImplementation<?> setting : module.getSettings()) {
                setting.renderImGui();
                ImGui.spacing();
            }

            module.renderCustomSettings();

            ImGui.endChild();

            ImGui.popStyleVar();
        }

        ImGui.spacing();
    }


    private float calculateSettingsHeight(ModuleImplementation module) {

        float height = 45;

        height += module.getSettings().size() * 45;

        return Math.min(height, 300);
    }

    private static String getBindLabel(ModuleImplementation module) {
        int key = module.keyBindingCode;
        if (key == GLFW.GLFW_KEY_UNKNOWN || key == 0) return "[NONE]";

        String name = GLFW.glfwGetKeyName(key, 0);
        if (name != null && !name.isBlank()) return "[" + name.toUpperCase() + "]";

        return switch (key) {
            case GLFW.GLFW_KEY_SPACE        -> "[SPACE]";
            case GLFW.GLFW_KEY_LEFT_SHIFT   -> "[LSHIFT]";
            case GLFW.GLFW_KEY_RIGHT_SHIFT  -> "[RSHIFT]";
            case GLFW.GLFW_KEY_LEFT_CONTROL -> "[LCTRL]";
            case GLFW.GLFW_KEY_RIGHT_CONTROL-> "[RCTRL]";
            case GLFW.GLFW_KEY_LEFT_ALT     -> "[LALT]";
            case GLFW.GLFW_KEY_RIGHT_ALT    -> "[RALT]";
            case GLFW.GLFW_KEY_ESCAPE       -> "[ESC]";
            case GLFW.GLFW_KEY_TAB          -> "[TAB]";
            case GLFW.GLFW_KEY_CAPS_LOCK    -> "[CAPS]";
            case GLFW.GLFW_KEY_F1           -> "[F1]";
            case GLFW.GLFW_KEY_F2           -> "[F2]";
            case GLFW.GLFW_KEY_F3           -> "[F3]";
            case GLFW.GLFW_KEY_F4           -> "[F4]";
            case GLFW.GLFW_KEY_F5           -> "[F5]";
            case GLFW.GLFW_KEY_F6           -> "[F6]";
            case GLFW.GLFW_KEY_F7           -> "[F7]";
            case GLFW.GLFW_KEY_F8           -> "[F8]";
            case GLFW.GLFW_KEY_F9           -> "[F9]";
            case GLFW.GLFW_KEY_F10          -> "[F10]";
            case GLFW.GLFW_KEY_F11          -> "[F11]";
            case GLFW.GLFW_KEY_F12          -> "[F12]";
            default -> "[KEY:" + key + "]";
        };
    }

    @Override
    public void removed() {
        super.removed();

        ClickGUIModule module = ModuleManager.getModule(ClickGUIModule.class);

        if (module != null && module.isEnabled()) {
            module.toggle();
        }
    }
}