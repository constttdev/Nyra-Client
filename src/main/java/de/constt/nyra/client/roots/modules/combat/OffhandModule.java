package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Offhand", description = "Tries to automatically equip a specific item to your offhand", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "offhand")
public class OffhandModule extends ModuleImplementation {
}