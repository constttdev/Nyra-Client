package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Auto Weapon", description = "Automatically cherry picks a weapon from your inventory when damaging entities", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "autoweapon")
public class AutoWeaponModule extends ModuleImplementation {
}