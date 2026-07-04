package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Hole Filler", description = "Automatically fills all holes near you", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "holefiller")
public class HoleFillerModule extends ModuleImplementation {
}