package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Brew", description = "Brews potions for you", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "autobrew")
public class AutoBrewModule extends ModuleImplementation {
}