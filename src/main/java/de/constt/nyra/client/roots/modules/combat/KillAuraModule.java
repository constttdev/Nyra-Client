package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Kill Aura", description = "Automatically damages entities near you", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "killaura")
public class KillAuraModule extends ModuleImplementation {
}