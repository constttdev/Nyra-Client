package de.constt.nyra.client.roots.modules.movement;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "No Push", description = "Disables collisions for you", category = CategoryImplementation.Categories.MOVEMENT, internalModuleName = "nopush")
public class NoPushModule extends ModuleImplementation {
}