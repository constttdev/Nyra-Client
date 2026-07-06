package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Shear", description = "Uses your shears on sheep for you", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "autoshear")
public class AutoShearModule extends ModuleImplementation {
}