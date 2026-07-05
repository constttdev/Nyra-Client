package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Mend", description = "Sets the item in your offhand to one that needs mending automatically", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "automend")
public class AutoMendModule extends ModuleImplementation {
}