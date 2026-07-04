package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Hit Crystal", description = "Tries to automatically hit crystal entities on damage", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "hitcrystal")
public class HitCrystalModule extends ModuleImplementation {
}