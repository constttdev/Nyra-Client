package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Streamer Mode", description = "Hides your name and other information that you don't want to show on stream", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "streamermode")
public class StreamerModeModule extends ModuleImplementation {
}