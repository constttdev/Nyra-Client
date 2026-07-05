package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Reconnect", description = "Reconnects to a server for you", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "autoreconnect")
public class AutoReconnectModule extends ModuleImplementation {
}