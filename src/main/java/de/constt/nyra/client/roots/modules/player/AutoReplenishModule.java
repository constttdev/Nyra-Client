package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Replenish", description = "Places your crops for you upon breaking them", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "autoreplenish")
public class AutoReplenishModule extends ModuleImplementation {
}