package de.constt.nyra.client.screens;

import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.SettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;
import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiStyleVar;

public class ModulesScreen extends BaseScreen {

    private ModuleImplementation settingsModule;

    @Override
    public void render() {

        ImGui.text("Nyra Client");

        ImGui.spacing();

        int index = 0;

        for (CategoryImplementation.Categories category : CategoryImplementation.Categories.values()) {

            ImGui.setNextWindowPos(
                    30 + index * 250,
                    60,
                    ImGuiCond.FirstUseEver
            );

            ImGui.setNextWindowSize(
                    230,
                    420,
                    ImGuiCond.FirstUseEver
            );

            ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 14, 14);

            if (ImGui.begin(category.name())) {

                ImGui.beginChild(
                        category.name() + "_modules",
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

        drawSettings();
    }


    private void drawModule(ModuleImplementation module) {

        if (ImGui.button(
                module.getTranslatableText(),
                200,
                32
        )) {
            module.toggle();
        }

        if (ImGui.isItemClicked(1)) {
            settingsModule = module;
        }

        ImGui.spacing();
    }


    private void drawSettings() {

        if (settingsModule == null)
            return;

        ImGui.setNextWindowSize(
                350,
                450,
                ImGuiCond.FirstUseEver
        );

        ImGui.pushStyleVar(
                ImGuiStyleVar.WindowPadding,
                16,
                16
        );

        if (ImGui.begin(
                settingsModule.getTranslatableText() + " Settings"
        )) {

            ImGui.text(settingsModule.getTranslatableText());

            ImGui.separator();

            ImGui.beginChild(
                    "settings",
                    0,
                    0,
                    false
            );


            ImGui.text(
                    ModuleAnnotationUtils.getDescription(
                            settingsModule.getClass()
                    )
            );

            ImGui.spacing();

            ImGui.separator();

            for (SettingImplementation<?> setting : settingsModule.getSettings()) {
                setting.renderImGui();
                ImGui.spacing();
            }

            settingsModule.renderCustomSettings();

            ImGui.endChild();
        }

        ImGui.end();

        ImGui.popStyleVar();
    }
}