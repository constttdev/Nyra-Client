package de.constt.nyra.client.screens;

import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.SettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;
import imgui.ImGui;
import imgui.flag.ImGuiStyleVar;

import java.util.HashSet;
import java.util.Set;

public class ModulesScreen extends BaseScreen {

    private final Set<ModuleImplementation> expandedModules = new HashSet<>();

    @Override
    public void render() {
        float screenWidth = ImGui.getIO().getDisplaySizeX();
        float screenHeight = ImGui.getIO().getDisplaySizeY();

        int categories = CategoryImplementation.Categories.values().length;

        float spacing = 20;
        float width = Math.max(220, (screenWidth - spacing * (categories + 1)) / categories);
        float height = screenHeight - 120;

        int index = 0;

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

        if (ImGui.button(label, ImGui.getContentRegionAvailX(), 32)) {
            module.toggle();
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
}