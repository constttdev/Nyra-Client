package de.constt.nyra.client.libs.render;

import com.mojang.blaze3d.PrimitiveTopology;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.render.ArrayListModule;
import de.constt.nyra.client.utils.KeybindingUtils;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;
import de.constt.nyra.client.utils.VarUtils;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.StagedVertexBuffer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.*;

public class RenderingLibrary {

    private static final RenderPipeline FILLED_THROUGH_WALLS = RenderPipelines.register(
            RenderPipeline.builder(RenderPipelines.DEBUG_FILLED_SNIPPET)
                    .withLocation(Identifier.fromNamespaceAndPath(
                            VarUtils.getModID(),
                            "pipeline/debug_filled_box_through_walls"
                    ))
                    .withDepthStencilState(Optional.empty())
                    .build()
    );

    private static final List<RenderBox> renderState = new ArrayList<>();

    public static void drawText(GuiGraphicsExtractor guiGraphicsExtractor, DeltaTracker deltaTracker, Integer color, int x, int y, String text) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        guiGraphicsExtractor.text(
                minecraft.font,
                String.format(text),
                x,
                y,
                color,
                true
        );
    }

    private record RenderBox(
            double x,
            double y,
            double z,
            double width,
            double height,
            double depth,
            float r,
            float g,
            float b,
            float a,
            boolean outline
    ) {
    }

    public static void extractRenders(LevelExtractionContext context) {
        renderState.clear();
    }

    public static void addBox(
            double x,
            double y,
            double z,
            double width,
            double height,
            double depth,
            float r,
            float g,
            float b,
            float a
    ) {
        renderState.add(new RenderBox(
                x,
                y,
                z,
                width,
                height,
                depth,
                r,
                g,
                b,
                a,
                false
        ));
    }

    public static void addBoxOutline(
            double x,
            double y,
            double z,
            double width,
            double height,
            double depth,
            float r,
            float g,
            float b,
            float a
    ) {
        renderState.add(new RenderBox(
                x,
                y,
                z,
                width,
                height,
                depth,
                r,
                g,
                b,
                a,
                true
        ));
    }

    public static void addEntity(
            Entity entity,
            float r,
            float g,
            float b,
            float a
    ) {
        AABB box = entity.getBoundingBox();

        addBox(
                box.minX,
                box.minY,
                box.minZ,
                box.getXsize(),
                box.getYsize(),
                box.getZsize(),
                r,
                g,
                b,
                a
        );
    }

    private static final Vector4f COLOR_MODULATOR = new Vector4f(1f, 1f, 1f, 1f);
    private static final Vector3f MODEL_OFFSET = new Vector3f();
    private static final Matrix4f TEXTURE_MATRIX = new Matrix4f();

    private static final StagedVertexBuffer stagedBuffer =
            new StagedVertexBuffer(
                    () -> "Rendering Buffer",
                    RenderType.SMALL_BUFFER_SIZE
            );

    public static void renderAndDraw(LevelRenderContext context) {
        RenderPipeline renderPipeline = FILLED_THROUGH_WALLS;

        VertexFormat formatBinding = renderPipeline.getVertexFormatBinding(0);

        if (formatBinding == null) {
            return;
        }

        PrimitiveTopology primitive = renderPipeline.getPrimitiveTopology();

        StagedVertexBuffer.Draw draw = stagedBuffer.appendDraw(
                formatBinding,
                primitive,
                primitive == PrimitiveTopology.QUADS
                        ? RenderSystem.getProjectionType().vertexSorting()
                        : null
        );

        renderBoxes(context, draw);

        stagedBuffer.upload();

        StagedVertexBuffer.ExecuteInfo info = stagedBuffer.getExecuteInfo(draw);

        if (info != null) {
            draw(Minecraft.getInstance(), info, renderPipeline);
        }

        stagedBuffer.endFrame();
        renderState.clear();
    }

    private static void renderBoxes(
            LevelRenderContext context,
            StagedVertexBuffer.Draw draw
    ) {
        PoseStack matrices = context.poseStack();
        Vec3 camera = context.levelState().cameraRenderState.pos;

        matrices.pushPose();
        matrices.translate(-camera.x, -camera.y, -camera.z);

        VertexConsumer builder = stagedBuffer.getVertexBuilder(draw);

        for (RenderBox box : renderState) {
            if (box.outline()) {
                renderOutlineBox(
                        matrices.last().pose(),
                        builder,
                        (float) box.x(),
                        (float) box.y(),
                        (float) box.z(),
                        (float) (box.x() + box.width()),
                        (float) (box.y() + box.height()),
                        (float) (box.z() + box.depth()),
                        box.r(),
                        box.g(),
                        box.b(),
                        box.a()
                );
            } else {
                renderFilledBox(
                        matrices.last().pose(),
                        builder,
                        (float) box.x(),
                        (float) box.y(),
                        (float) box.z(),
                        (float) (box.x() + box.width()),
                        (float) (box.y() + box.height()),
                        (float) (box.z() + box.depth()),
                        box.r(),
                        box.g(),
                        box.b(),
                        box.a()
                );
            }
        }

        matrices.popPose();
    }

    private static void renderOutlineBox(
            Matrix4fc positionMatrix,
            VertexConsumer buffer,
            float minX,
            float minY,
            float minZ,
            float maxX,
            float maxY,
            float maxZ,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        float thickness = 0.02f;

        renderLine(buffer, positionMatrix, minX, minY, minZ, maxX, minY, minZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, maxX, minY, minZ, maxX, minY, maxZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, maxX, minY, maxZ, minX, minY, maxZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, minX, minY, maxZ, minX, minY, minZ, thickness, red, green, blue, alpha);

        renderLine(buffer, positionMatrix, minX, maxY, minZ, maxX, maxY, minZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, maxX, maxY, minZ, maxX, maxY, maxZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, maxX, maxY, maxZ, minX, maxY, maxZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, minX, maxY, maxZ, minX, maxY, minZ, thickness, red, green, blue, alpha);

        renderLine(buffer, positionMatrix, minX, minY, minZ, minX, maxY, minZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, maxX, minY, minZ, maxX, maxY, minZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, maxX, minY, maxZ, maxX, maxY, maxZ, thickness, red, green, blue, alpha);
        renderLine(buffer, positionMatrix, minX, minY, maxZ, minX, maxY, maxZ, thickness, red, green, blue, alpha);
    }

    private static void renderLine(
            VertexConsumer buffer,
            Matrix4fc positionMatrix,
            float x1,
            float y1,
            float z1,
            float x2,
            float y2,
            float z2,
            float thickness,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        float minX = Math.min(x1, x2) - thickness;
        float minY = Math.min(y1, y2) - thickness;
        float minZ = Math.min(z1, z2) - thickness;

        float maxX = Math.max(x1, x2) + thickness;
        float maxY = Math.max(y1, y2) + thickness;
        float maxZ = Math.max(z1, z2) + thickness;

        renderFilledBox(
                positionMatrix,
                buffer,
                minX,
                minY,
                minZ,
                maxX,
                maxY,
                maxZ,
                red,
                green,
                blue,
                alpha
        );
    }

    private static void renderFilledBox(
            Matrix4fc positionMatrix,
            VertexConsumer buffer,
            float minX,
            float minY,
            float minZ,
            float maxX,
            float maxY,
            float maxZ,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        buffer.addVertex(positionMatrix, minX, minY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, minY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, maxY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, minX, maxY, maxZ).setColor(red, green, blue, alpha);

        buffer.addVertex(positionMatrix, maxX, minY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, minX, minY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, minX, maxY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, maxY, minZ).setColor(red, green, blue, alpha);

        buffer.addVertex(positionMatrix, minX, minY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, minX, minY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, minX, maxY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, minX, maxY, minZ).setColor(red, green, blue, alpha);

        buffer.addVertex(positionMatrix, maxX, minY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, minY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, maxY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, maxY, maxZ).setColor(red, green, blue, alpha);

        buffer.addVertex(positionMatrix, minX, maxY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, maxY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, maxY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, minX, maxY, minZ).setColor(red, green, blue, alpha);

        buffer.addVertex(positionMatrix, minX, minY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, minY, minZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, maxX, minY, maxZ).setColor(red, green, blue, alpha);
        buffer.addVertex(positionMatrix, minX, minY, maxZ).setColor(red, green, blue, alpha);
    }

    private static void draw(
            Minecraft client,
            StagedVertexBuffer.ExecuteInfo info,
            RenderPipeline pipeline
    ) {
        GpuBufferSlice dynamicTransforms = RenderSystem.getDynamicUniforms()
                .writeTransform(
                        RenderSystem.getModelViewMatrixCopy(),
                        COLOR_MODULATOR,
                        MODEL_OFFSET,
                        TEXTURE_MATRIX
                );

        RenderTarget mainTarget = client.gameRenderer.mainRenderTarget();

        GpuTextureView colorTexture = mainTarget.getColorTextureView();

        assert colorTexture != null;

        try (RenderPass renderPass = RenderSystem.getDevice()
                .createCommandEncoder()
                .createRenderPass(
                        () -> VarUtils.getModID() + " rendering",
                        colorTexture,
                        Optional.empty(),
                        mainTarget.getDepthTextureView(),
                        OptionalDouble.empty()
                )) {

            renderPass.setPipeline(pipeline);

            RenderSystem.bindDefaultUniforms(renderPass);

            renderPass.setUniform(
                    "DynamicTransforms",
                    dynamicTransforms
            );

            renderPass.setVertexBuffer(
                    0,
                    info.vertexBuffer().slice()
            );

            renderPass.setIndexBuffer(
                    info.indexBuffer(),
                    info.indexType()
            );

            renderPass.drawIndexed(
                    info.indexCount(),
                    1,
                    info.firstIndex(),
                    info.baseVertex(),
                    0
            );
        }
    }

    public static void drawModulesList(GuiGraphicsExtractor guiGraphicsExtractor, DeltaTracker deltaTracker) {
        ModuleImplementation arrayListModule = ModuleManager.getModule(ArrayListModule.class);

        if (arrayListModule == null || !arrayListModule.getEnabledStatus()) {
            return;
        }

        int yOffset = (int) arrayListModule.getSetting("Module Text Offset").get();
        int textColor = (int) arrayListModule.getSetting("Text Color").get();
        boolean showBind = (boolean) arrayListModule.getSetting("Show Bind").get();

        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();

        for (ModuleImplementation module : ModuleManager.getModules()) {
            String moduleName = ModuleAnnotationUtils.getName(module.getClass());

            if (!Objects.equals(moduleName, "Array List") && module.getEnabledStatus()) {
                String moduleString = moduleName;

                if (showBind) {
                    moduleString += " ["
                            + KeybindingUtils.getKeyName(
                            module.getKeybindingCode(),
                            true
                    )
                            + "]";
                }

                drawText(
                        guiGraphicsExtractor,
                        deltaTracker,
                        textColor,
                        screenWidth - Minecraft.getInstance().font.width(moduleString) - 5,
                        yOffset,
                        moduleString
                );

                yOffset += 10;
            }
        }
    }


    public static void close() {
        stagedBuffer.close();
    }

    public static void register() {
        LevelRenderEvents.END_MAIN.register(RenderingLibrary::renderAndDraw);
    }
}