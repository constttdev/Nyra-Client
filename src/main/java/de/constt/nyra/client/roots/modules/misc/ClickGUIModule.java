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
        //~ if <=1.21.10 setScreenAndShow -> setScreen
        Minecraft.getInstance().setScreenAndShow(new ModulesScreen());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        //~ if <=1.21.10 setScreenAndShow -> setScreen
        Minecraft.getInstance().setScreenAndShow(null);
    }
}
