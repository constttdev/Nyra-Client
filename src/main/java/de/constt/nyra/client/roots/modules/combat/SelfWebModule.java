package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Self Web", description = "Tries to automatically cob-web trap yourself", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "selfweb")
public class SelfWebModule extends ModuleImplementation {
}