package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Eat", description = "Eats food in your inventory for you", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "autoeat")
public class AutoEatModule extends ModuleImplementation {
}