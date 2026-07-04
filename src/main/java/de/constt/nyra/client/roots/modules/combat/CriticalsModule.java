package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Criticals", description = "Tries to make all hits automatically count as crits", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "criticals")
public class CriticalsModule extends ModuleImplementation {
}