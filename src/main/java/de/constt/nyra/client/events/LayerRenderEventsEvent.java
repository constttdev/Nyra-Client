package de.constt.nyra.client.events;

import de.constt.nyra.client.libs.render.CustomRenderingPipeline;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;

public final class LayerRenderEventsEvent {

    private LayerRenderEventsEvent() {
    }

    public static void register() {
        LevelRenderEvents.AFTER_TRANSLUCENT_TERRAIN.register(
                CustomRenderingPipeline::renderAndDraw
        );
    }
}