package de.constt.nyra.client.events;

/*
import de.constt.nyra.client.libs.render.RenderingLibrary;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.minecraft.client.renderer.RenderType;


 */
public class WorldRenderEventsEvent {

    public static void register() {
        /*
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            var matrices = context.matrices();

            matrices.pushPose();

            var camera = context.camera().position();

            matrices.translate(
                    -camera.x,
                    -camera.y,
                    -camera.z
            );

            var buffer = context.consumers()
                    .getBuffer(RenderType.lines().getClass());

            RenderingLibrary.drawBox(
                    matrices,
                    buffer,
                    0,
                    0,
                    0,
                    1,
                    1,
                    1,
                    0xFFFF0000
            );

            matrices.popPose();
        });

         */
    }
}