package de.constt.nyra.client.screens;

import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.SettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.utils.ConfigManagerUtils;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;
import imgui.ImGui;
import imgui.flag.ImGuiStyleVar;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

import java.util.HashSet;
import java.util.Set;

public class ModulesScreen extends BaseScreen {

    private final Set<ModuleImplementation> expandedModules = new HashSet<>();
    private ModuleImplementation listeningModule = null;

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

            ImGui.setNextWindowPos(
                    spacing + index * (width + spacing),
                    60
            );

            ImGui.setNextWindowSize(
                    width,
                    height
            );

            ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 12, 12);

            if (ImGui.begin(
                    category.name(),
                            imgui.flag.ImGuiWindowFlags.NoResize |
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

            ImGui.popStyleVar();

            index++;
        }
    }


    private void drawModule(ModuleImplementation module) {

        boolean expanded = expandedModules.contains(module);

        String label = module.getTranslatableText();

        if (module.isEnabled()) {
            label += " [ON]";
        }

        if (ImGui.button(label, ImGui.getContentRegionAvailX() - 80, 32)) {
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

        if (ImGui.isItemClicked(1)) {
            if (expanded) {
                expandedModules.remove(module);
            } else {
                expandedModules.add(module);
            }
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
}