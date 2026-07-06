package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Anti AFK", description = "Protects you from being kicked for AFK", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "antiafk")
public class AntiAFKModule extends ModuleImplementation {
}