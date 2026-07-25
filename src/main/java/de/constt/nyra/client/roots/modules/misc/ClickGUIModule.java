package de.constt.nyra.client.roots.modules.misc;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.screens.ModulesScreen;
import net.minecraft.client.Minecraft;

@ModuleInfoAnnotation(name = "Click GUI", description = "ClickGUI Settings", category = CategoryImplementation.Categories.MISC, internalModuleName = "clickgui")
public class ClickGUIModule extends ModuleImplementation {
    @Override
    public void onEnable() {
        super.onEnable();

        if (Minecraft.getInstance().gui.screen() instanceof ModulesScreen) {
            return;
        }

        Minecraft.getInstance().setScreenAndShow(new ModulesScreen());
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if (Minecraft.getInstance().gui.screen() instanceof ModulesScreen) {
            Minecraft.getInstance().setScreenAndShow(null);
        }
    }
}
