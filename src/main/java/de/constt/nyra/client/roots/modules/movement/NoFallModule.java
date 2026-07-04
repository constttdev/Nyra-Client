package de.constt.nyra.client.roots.modules.movement;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "No Fall", description = "Automatically disables fall.", category = CategoryImplementation.Categories.MOVEMENT, internalModuleName = "nofall")
public class NoFallModule extends ModuleImplementation {
}