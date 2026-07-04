package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Anti Anchor", description = "Try to block of any anchor near you", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "antianchor")
public class AntiAnchorModule extends ModuleImplementation {
}