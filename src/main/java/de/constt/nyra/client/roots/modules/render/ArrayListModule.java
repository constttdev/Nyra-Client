package de.constt.nyra.client.roots.modules.render;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.BooleanSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.ColorSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.NumberSettingImplementation;


@ModuleInfoAnnotation(name = "Array List", description = "Enables the Array List module", category = CategoryImplementation.Categories.RENDER, internalModuleName = "arraylist")
public class ArrayListModule extends ModuleImplementation {
    private final BooleanSettingImplementation showBind;
    private final BooleanSettingImplementation showSettings;
    private final ColorSettingImplementation textColor;
    private final NumberSettingImplementation xOffset;

    public ArrayListModule() {
        showBind = new BooleanSettingImplementation("Show Bind", true);
        showSettings = new BooleanSettingImplementation("Show Settings", false);
        textColor = new ColorSettingImplementation("Text Color", 0xFFFFFFFF);
        xOffset = new NumberSettingImplementation("Module Text Offset", 10, 1, 50);

        registerSetting(showBind);
        registerSetting(showSettings);
        registerSetting(textColor);
        registerSetting(xOffset);
    }
}