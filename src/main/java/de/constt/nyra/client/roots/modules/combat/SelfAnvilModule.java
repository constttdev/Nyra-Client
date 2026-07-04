package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Self Anvil", description = "Tries to automatically place anvils above your head", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "selfanvil")
public class SelfAnvilModule extends ModuleImplementation {
}