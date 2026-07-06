package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Mace", description = "Uses your mace for you when falling on someone", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "automace")
public class AutoMaceModule extends ModuleImplementation {
}