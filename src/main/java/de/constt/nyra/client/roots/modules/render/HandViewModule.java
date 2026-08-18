package de.constt.nyra.client.roots.modules.render;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.NumberSliderSettingImplementation;

@ModuleInfoAnnotation(name = "Hand View", description = "Changes how items in your hands / your hands render", category = CategoryImplementation.Categories.RENDER, internalModuleName = "handview")
public class HandViewModule extends ModuleImplementation {
    private final NumberSliderSettingImplementation swingSpeed;
    // TODO: Register module, add settings, add functionaility (for sure its mixins to change the rendering)
    // TODO: Add more settings impl's?

    public HandViewModule() {
        swingSpeed = new NumberSliderSettingImplementation("Swing Speed", 1, -10, 100, 1);

    }
    //X swing speed
    // animation presets
    // - main hand
    // size: x, y, z
    // position: x, y, z
    // - off hand
    // size: x, y, z
    // position: x, y, z
    // - hands
    // size: x, y, z
    // position: x, y, z
}
