package de.constt.nyra.client.roots.modules.misc;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.BooleanSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.NumberSettingImplementation;
import imgui.ImGui;

@ModuleInfoAnnotation(name = "Pearl Throw", description = "Automatically tries to throw a pearl for you", category = CategoryImplementation.Categories.MISC, internalModuleName = "pearlthrow")
public class PearlThrowModule extends ModuleImplementation {
    BooleanSettingImplementation autoswap = new BooleanSettingImplementation("Auto swap slot", false);
    NumberSettingImplementation slotSwapDelay = new NumberSettingImplementation("Slot Swap Delay", 0, 0, 100);

    public PearlThrowModule() {
        registerSetting(autoswap);
        registerSetting(slotSwapDelay);
    }

    @Override
    public void renderCustomSettings() {
        super.renderCustomSettings();

        ImGui.text("Enable the \"Disable on Toggle\" Setting to make the module work without any complications.");
    }
}
