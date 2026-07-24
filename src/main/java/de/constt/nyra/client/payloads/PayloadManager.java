package de.constt.nyra.client.payloads;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class PayloadManager {
    public static void init() {
        //? if >=26.1 {
        /*PayloadTypeRegistry.serverboundPlay().register(JoinPayload.TYPE, JoinPayload.CODEC);
        *///?}
    }
}
