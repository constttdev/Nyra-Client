package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Bed Aura", description = "Automatically tries to place beds near other players", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "bedaura")
public class BedAuraModule extends ModuleImplementation {
}