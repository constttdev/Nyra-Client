package de.constt.nyra.client.screens;

import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.SettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.utils.ColorUtils;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;
import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiCond;

public class ModulesScreen extends BaseScreen {

    private ModuleImplementation settingsModule;

    @Override
    public void render() {
        super.render();

        ImGui.pushStyleColor(ImGuiCol.Text, COL_TEXT);
        ColorUtils.debugColor("IMGUI_TEXT", COL_TEXT);
        ImGui.text("TEST TEXT");
        ImGui.popStyleColor();
        ImGui.textColored(0xFFFF0000, "FORCED RED TEST");

        int offset = 0;

        for (CategoryImplementation.Categories category : CategoryImplementation.Categories.values()) {

            ImGui.setNextWindowPos(40 + offset, 40, ImGuiCond.FirstUseEver);
            ImGui.setNextWindowSize(220, 350, ImGuiCond.FirstUseEver);

            if (ImGui.begin(category.name())) {
                for (ModuleImplementation module : ModuleManager.getModules()) {
                    if (ModuleManager.getCategory(module.getClass()) != category) {
                        continue;
                    }

                    boolean enabled = module.isEnabled();

                    if (enabled) {
                        ImGui.pushStyleColor(ImGuiCol.Button, 0.18f, 0.65f, 0.28f, 1.0f);
                        ImGui.pushStyleColor(ImGuiCol.ButtonHovered, 0.22f, 0.75f, 0.34f, 1.0f);
                        ImGui.pushStyleColor(ImGuiCol.ButtonActive, 0.14f, 0.55f, 0.24f, 1.0f);
                    }

                    if (ImGui.button(module.getTranslatableText())) {
                        module.toggle();
                    }

                    if (enabled) {
                        ImGui.popStyleColor(3);
                    }

                    if (ImGui.isItemClicked(1)) {
                        settingsModule = module;
                    }
                }
            }

            ImGui.end();

            offset += 240;
        }

        if (settingsModule != null) {
            ImGui.setNextWindowSize(300, 400, ImGuiCond.FirstUseEver);

            if (ImGui.begin(settingsModule.getTranslatableText() + " Settings")) {
                renderSettings(settingsModule);
            }

            ImGui.end();
        }
    }

    private void renderSettings(ModuleImplementation module) {
        ImGui.beginChild("Settings");

        ImGui.text(ModuleAnnotationUtils.getDescription(module.getClass()));

        ImGui.separator();

        for (SettingImplementation<?> setting : module.getSettings()) {
            setting.renderImGui();
        }

        ImGui.separator();

        module.renderCustomSettings();

        ImGui.endChild();
    }
}