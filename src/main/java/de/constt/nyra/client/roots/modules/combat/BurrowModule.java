package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Burrow", description = "Tries to place a block on your feet to block damage", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "burrow")
public class BurrowModule extends ModuleImplementation {
}