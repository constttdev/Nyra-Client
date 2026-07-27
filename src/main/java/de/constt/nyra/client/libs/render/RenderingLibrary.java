package de.constt.nyra.client.libs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public final class RenderingLibrary {

    private RenderingLibrary() {
    }

    public static void drawBox(
            PoseStack matrices,
            VertexConsumer consumer,
            double x,
            double y,
            double z,
            double width,
            double height,
            double depth,
            int color
    ) {
        float a = ((color >> 24) & 255) / 255f;
        float r = ((color >> 16) & 255) / 255f;
        float g = ((color >> 8) & 255) / 255f;
        float b = (color & 255) / 255f;

        var pose = matrices.last().pose();

        drawLine(consumer, pose, x, y, z, x + width, y, z, r, g, b, a);
        drawLine(consumer, pose, x, y, z, x, y + height, z, r, g, b, a);
        drawLine(consumer, pose, x, y, z, x, y, z + depth, r, g, b, a);

        drawLine(consumer, pose, x + width, y + height, z + depth, x, y + height, z + depth, r, g, b, a);
    }

    private static void drawLine(
            VertexConsumer consumer,
            org.joml.Matrix4f matrix,
            double x1,
            double y1,
            double z1,
            double x2,
            double y2,
            double z2,
            float r,
            float g,
            float b,
            float a
    ) {
        consumer.addVertex(matrix, (float) x1, (float) y1, (float) z1)
                .setColor(r, g, b, a);
        consumer.addVertex(matrix, (float) x2, (float) y2, (float) z2)
                .setColor(r, g, b, a);
    }
}