package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Auto Armor", description = "Automatically tries to equip armor from your inventory", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "autoarmor")
public class AutoArmorModule extends ModuleImplementation {
}