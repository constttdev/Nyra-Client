package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Safe Anchor", description = "Tries to automatically protect you from the damage of an self-placed anchor", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "safeanchor")
public class SafeAnchorModule extends ModuleImplementation {
}