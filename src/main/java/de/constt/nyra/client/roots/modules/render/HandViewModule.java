package de.constt.nyra.client.roots.modules.render;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.gui_assets.MiddleLineImplementation;
import de.constt.nyra.client.roots.implementations.settings.DoubleSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.ListSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.NumberSliderSettingImplementation;

@ModuleInfoAnnotation(
        name = "Hand View",
        description = "Changes how items in your hands / your hands render",
        category = CategoryImplementation.Categories.RENDER,
        internalModuleName = "handview"
)
public class HandViewModule extends ModuleImplementation {

    private final NumberSliderSettingImplementation swingSpeed;
    private final ListSettingImplementation animationPreset;

    private final DoubleSettingImplementation mhSizeX;
    private final DoubleSettingImplementation mhSizeY;
    private final DoubleSettingImplementation mhSizeZ;
    private final DoubleSettingImplementation mhPosX;
    private final DoubleSettingImplementation mhPosY;
    private final DoubleSettingImplementation mhPosZ;

    private final DoubleSettingImplementation ohSizeX;
    private final DoubleSettingImplementation ohSizeY;
    private final DoubleSettingImplementation ohSizeZ;
    private final DoubleSettingImplementation ohPosX;
    private final DoubleSettingImplementation ohPosY;
    private final DoubleSettingImplementation ohPosZ;

    private final DoubleSettingImplementation hSizeX;
    private final DoubleSettingImplementation hSizeY;
    private final DoubleSettingImplementation hSizeZ;
    private final DoubleSettingImplementation hPosX;
    private final DoubleSettingImplementation hPosY;
    private final DoubleSettingImplementation hPosZ;

    public HandViewModule() {
        swingSpeed = new NumberSliderSettingImplementation("Swing Speed", 0,-100, 100, 1);
        animationPreset = new ListSettingImplementation("Animation", new String[]{"Vanilla"}, "Vanilla");

        registerSetting(swingSpeed);
        registerSetting(animationPreset);

        registerGuiAsset(new MiddleLineImplementation("Main Hand", null, 2, 10));

        mhSizeX = new DoubleSettingImplementation("Size (X) - MH", 0.0, 0.1, 10.0);
        mhSizeY = new DoubleSettingImplementation("Size (Y) - MH", 0.0, 0.1, 10.0);
        mhSizeZ = new DoubleSettingImplementation("Size (Z) - MH", 0.0, 0.1, 10.0);

        mhPosX = new DoubleSettingImplementation("Pos (X) - MH", 0.0, -10.0, 10.0);
        mhPosY = new DoubleSettingImplementation("Pos (Y) - MH", 0.0, -10.0, 10.0);
        mhPosZ = new DoubleSettingImplementation("Pos (Z) - MH", 0.0, -10.0, 10.0);

        registerSetting(mhSizeX);
        registerSetting(mhSizeY);
        registerSetting(mhSizeZ);
        registerSetting(mhPosX);
        registerSetting(mhPosY);
        registerSetting(mhPosZ);

        registerGuiAsset(new MiddleLineImplementation("Off Hand", null, 2, 10));

        ohSizeX = new DoubleSettingImplementation("Size (X) - OH", 0.0, 0.1, 10.0);
        ohSizeY = new DoubleSettingImplementation("Size (Y) - OH", 0.0, 0.1, 10.0);
        ohSizeZ = new DoubleSettingImplementation("Size (Z) - OH", 0.0, 0.1, 10.0);

        ohPosX = new DoubleSettingImplementation("Pos (X) - OH", 0.0, -10.0, 10.0);
        ohPosY = new DoubleSettingImplementation("Pos (Y) - OH", 0.0, -10.0, 10.0);
        ohPosZ = new DoubleSettingImplementation("Pos (Z) - OH", 0.0, -10.0, 10.0);

        registerSetting(ohSizeX);
        registerSetting(ohSizeY);
        registerSetting(ohSizeZ);
        registerSetting(ohPosX);
        registerSetting(ohPosY);
        registerSetting(ohPosZ);

        registerGuiAsset(new MiddleLineImplementation("Hands", null, 2, 10));

        hSizeX = new DoubleSettingImplementation("Size (X) - H", 0.0, 0.1, 10.0);
        hSizeY = new DoubleSettingImplementation("Size (Y) - H", 0.0, 0.1, 10.0);
        hSizeZ = new DoubleSettingImplementation("Size (Z) - H", 0.0, 0.1, 10.0);

        hPosX = new DoubleSettingImplementation("Pos (X) - H", 0.0, -10.0, 10.0);
        hPosY = new DoubleSettingImplementation("Pos (Y) - H", 0.0, -10.0, 10.0);
        hPosZ = new DoubleSettingImplementation("Pos (Z) - H", 0.0, -10.0, 10.0);

        registerSetting(hSizeX);
        registerSetting(hSizeY);
        registerSetting(hSizeZ);
        registerSetting(hPosX);
        registerSetting(hPosY);
        registerSetting(hPosZ);
    }
}