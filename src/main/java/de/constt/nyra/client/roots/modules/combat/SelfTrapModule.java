package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Self Trap", description = "Automatically tries to trap your-self", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "selftrap")
public class SelfTrapModule extends ModuleImplementation {
}