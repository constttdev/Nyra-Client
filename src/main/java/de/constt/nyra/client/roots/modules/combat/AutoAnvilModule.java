package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Auto Anvil", description = "Automatically try to place anvils above other players heads near you", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "autoanvil")
public class AutoAnvilModule extends ModuleImplementation {
}