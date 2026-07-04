package de.constt.nyra.client.roots.modules.combat;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;

@ModuleInfoAnnotation(name = "Bow Spam", description = "Automatically tries to spam the current used bow", category = CategoryImplementation.Categories.COMBAT, internalModuleName = "bowspam")
public class BowSpamModule extends ModuleImplementation {
}