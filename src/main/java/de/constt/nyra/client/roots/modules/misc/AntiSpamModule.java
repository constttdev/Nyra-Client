package de.constt.nyra.client.roots.modules.misc;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;

@ModuleInfoAnnotation(name = "Anti Spam", description = "Protects you from spam", category = CategoryImplementation.Categories.MISC, internalModuleName = "antispam")
public class AntiSpamModule extends ModuleImplementation {
}