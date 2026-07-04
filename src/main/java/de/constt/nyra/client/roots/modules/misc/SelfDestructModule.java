package de.constt.nyra.client.roots.modules.misc;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;

@ModuleInfoAnnotation(name = "Self Destruct", description = "Tries to unload and remove the client from your system", category = CategoryImplementation.Categories.MISC, internalModuleName = "selfdestruct")
public class SelfDestructModule extends ModuleImplementation {
}