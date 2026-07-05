package de.constt.nyra.client.roots.modules.misc;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "RAM Usage", description = "Shows you your RAM usage on screen", category = CategoryImplementation.Categories.MISC, internalModuleName = "ramusage")
public class RAMUsageModule extends ModuleImplementation {
}