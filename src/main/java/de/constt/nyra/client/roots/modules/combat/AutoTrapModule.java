package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Auto Trap", description = "Automatically tries to trap players near you", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "autotrap")
public class AutoTrapModule extends ModuleImplementation {
}