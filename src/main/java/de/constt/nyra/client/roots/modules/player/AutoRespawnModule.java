package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Respawn", description = "Presses the respawn button for you", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "autorespawn")
public class AutoRespawnModule extends ModuleImplementation {
}