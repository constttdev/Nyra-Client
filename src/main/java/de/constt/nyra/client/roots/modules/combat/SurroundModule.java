package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Surround", description = "Tries to automatically place blocks around you to surround your-self", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "surround")
public class SurroundModule extends ModuleImplementation {
}