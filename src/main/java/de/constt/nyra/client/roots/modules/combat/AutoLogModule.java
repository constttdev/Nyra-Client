package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Auto Log", description = "Automatically tries to disconnect you", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "autolog")
public class AutoLogModule extends ModuleImplementation {
}