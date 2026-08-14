package de.constt.nyra.client.events;

import de.constt.nyra.client.libs.render.RenderingLibrary;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionEvents;

public class LevelExtractionEventsEvent {
    public static void register() {
        LevelExtractionEvents.END_EXTRACTION.register(RenderingLibrary::extractRenders);
    }
}