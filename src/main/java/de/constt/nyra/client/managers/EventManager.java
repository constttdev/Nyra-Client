package de.constt.nyra.client.managers;

import de.constt.nyra.client.events.ClientPlayerConnectionEventsEvent;
import de.constt.nyra.client.events.ClientSendMessageEvent;
import de.constt.nyra.client.events.ClientTickEventsEvent;
import de.constt.nyra.client.events.InputEventHandler;

public class EventManager {
    public static void registerEvents() {
        ClientSendMessageEvent.register();
        ClientPlayerConnectionEventsEvent.register();
        ClientTickEventsEvent.register();
        InputEventHandler.register();
    }
}
