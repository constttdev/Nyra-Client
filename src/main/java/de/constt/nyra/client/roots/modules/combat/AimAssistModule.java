package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Aim Assist", description = "Automatically sets your aim to specific entities", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "aimassist")
public class AimAssistModule extends ModuleImplementation {
}