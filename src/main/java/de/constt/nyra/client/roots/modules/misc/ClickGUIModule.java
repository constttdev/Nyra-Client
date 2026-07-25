package de.constt.nyra.client.roots.modules.misc;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.ColorSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.ListSettingImplementation;
import de.constt.nyra.client.screens.ModulesScreen;
import de.constt.nyra.client.utils.ThemeUtils;
import imgui.ImGui;
import imgui.type.ImString;
import net.minecraft.client.Minecraft;

@ModuleInfoAnnotation(
        name = "Click GUI",
        description = "ClickGUI Settings",
        category = CategoryImplementation.Categories.MISC,
        internalModuleName = "clickgui"
)
public class ClickGUIModule extends ModuleImplementation {

    private final ImString newThemeName = new ImString(64);

    private final ListSettingImplementation themeSetting =
            new ListSettingImplementation(
                    "Theme",
                    ThemeUtils.listThemes().toArray(new String[0]),
                    "default"
            );

    private final ColorSettingImplementation backgroundSetting =
            new ColorSettingImplementation(
                    "Background",
                    ThemeUtils.getBackgroundColor()
            );

    private final ColorSettingImplementation secondarySetting =
            new ColorSettingImplementation(
                    "Secondary",
                    ThemeUtils.getSecondaryColor()
            );

    private final ColorSettingImplementation accentSetting =
            new ColorSettingImplementation(
                    "Accent",
                    ThemeUtils.getAccentColor()
            );

    private final ColorSettingImplementation textSetting =
            new ColorSettingImplementation(
                    "Text",
                    ThemeUtils.getTextColor()
            );

    public ClickGUIModule() {
        registerSetting(themeSetting);
        registerSetting(backgroundSetting);
        registerSetting(secondarySetting);
        registerSetting(accentSetting);
        registerSetting(textSetting);

        backgroundSetting.addChangeListener(setting ->
                ThemeUtils.setBackgroundColor(backgroundSetting.get())
        );

        secondarySetting.addChangeListener(setting ->
                ThemeUtils.setSecondaryColor(secondarySetting.get())
        );

        accentSetting.addChangeListener(setting ->
                ThemeUtils.setAccentColor(accentSetting.get())
        );

        textSetting.addChangeListener(setting ->
                ThemeUtils.setTextColor(textSetting.get())
        );

        themeSetting.addChangeListener(setting -> {
            ThemeUtils.loadNamedTheme(themeSetting.get());

            backgroundSetting.set(ThemeUtils.getBackgroundColor());
            secondarySetting.set(ThemeUtils.getSecondaryColor());
            accentSetting.set(ThemeUtils.getAccentColor());
            textSetting.set(ThemeUtils.getTextColor());
        });
    }

    @Override
    public void onEnable() {
        super.onEnable();

        if (Minecraft.getInstance().gui.screen() instanceof ModulesScreen)
            return;

        Minecraft.getInstance().setScreenAndShow(new ModulesScreen());
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if (Minecraft.getInstance().gui.screen() instanceof ModulesScreen)
            Minecraft.getInstance().setScreenAndShow(null);
    }

    @Override
    public void renderCustomSettings() {

        themeSetting.setOptions(
                ThemeUtils.listThemes().toArray(new String[0])
        );

        ImGui.separator();

        ImGui.text("Create Theme");

        ImGui.inputText(
                "Name",
                newThemeName
        );

        if (ImGui.button("Create")) {
            String name = newThemeName.get().trim();

            if (!name.isEmpty()) {
                ThemeUtils.saveThemeAs(name);
                themeSetting.setOptions(
                        ThemeUtils.listThemes().toArray(new String[0])
                );
                newThemeName.set("");
            }
        }

        ImGui.spacing();

        ImGui.text("Delete Theme");

        for (String theme : ThemeUtils.listThemes()) {

            if (theme.equalsIgnoreCase("default"))
                continue;

            if (ImGui.button("Delete##" + theme)) {
                ThemeUtils.deleteTheme(theme);

                themeSetting.setOptions(
                        ThemeUtils.listThemes().toArray(new String[0])
                );
            }
        }
    }
}