package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Trigger Bot", description = "Automatically tries to attack an entity when your hovered over it", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "triggerbot")
public class TriggerbotModule extends ModuleImplementation {
}