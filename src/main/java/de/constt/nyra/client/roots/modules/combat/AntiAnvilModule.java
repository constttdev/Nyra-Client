package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Anti Anvil", description = "Automatically try to block any falling anvils near you", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "antianvil")
public class AntiAnvilModule extends ModuleImplementation {
}