package de.constt.nyra.client.managers;

import de.constt.nyra.client.events.*;

public class EventManager {
    public static void registerEvents() {
        ClientSendMessageEvent.register();
        ClientPlayerConnectionEventsEvent.register();
        ClientTickEventsEvent.register();
        InputEventHandler.register();

        // RENDERING PIPELINE
        LevelExtractionEventsEvent.register();
        LayerRenderEventsEvent.register();
    }
}
