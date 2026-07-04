package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Auto Totem", description = "Automatically tries to equip Totems of Undying to save you from death", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "autototem")
public class AutoTotemModule extends ModuleImplementation {
}