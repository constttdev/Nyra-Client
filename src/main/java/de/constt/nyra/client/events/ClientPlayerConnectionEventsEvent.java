package de.constt.nyra.client.events;

import de.constt.nyra.client.payloads.JoinPayload;
import de.constt.nyra.client.roots.implementations.DomainsImplementation;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.multiplayer.ServerData;

import static de.constt.nyra.client.NyraMod.LOGGER;

public class ClientPlayerConnectionEventsEvent {
    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((listener, sender, client) -> {
            ServerData server = client.getCurrentServer();
            if (server == null || !DomainsImplementation.contains(server.ip)) {
                return;
            }

            try {
                sender.sendPacket(new JoinPayload());
            } catch (RuntimeException error) {
                // Analytics must never be able to break an otherwise successful connection.
                LOGGER.warn("Failed to send join notification to {}: {}", server.ip, error.getMessage());
            }
        });
    }
}
