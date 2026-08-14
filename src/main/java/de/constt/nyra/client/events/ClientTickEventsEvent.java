package de.constt.nyra.client.events;

import com.mojang.blaze3d.platform.NativeImage;
import de.constt.nyra.client.clientcommands.CCommandManager;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.utils.ModuleCacheUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ClientTickEventsEvent {

    private static boolean iconSet = false;
    private static boolean loaded = false;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register((minecraft) -> {
            if (!iconSet) {
                try {
                    ClientTickEventsEvent.setWindowIcon("/assets/nyra/logo-64.png");
                    iconSet = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ModuleManager.getModules().forEach(module -> {
                if (module.getEnabledStatus())
                    module.postTick();
            });
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!loaded) {
                ModuleCacheUtils.loadAll();
                loaded = true;
            }
        });

        ClientTickEvents.START_CLIENT_TICK.register(c ->
                ModuleManager.getModules().forEach(module -> {
                    if (module.getEnabledStatus())
                        module.tick();
                })
        );

        ClientTickEvents.START_CLIENT_TICK.register(c ->
                CCommandManager.getCommands().forEach(command -> {
                    if (command.getEnabledStatus()) {
                        command.tick();
                    }
                })
        );

    }

    // TODO: Add version changes via stonecutter if needed
    public static void setWindowIcon(String pathInJar) throws IOException {
        try (
                InputStream inputStream = Minecraft.class.getResourceAsStream(pathInJar);
                MemoryStack stack = MemoryStack.stackPush()
        ) {
            if (inputStream == null) throw new IOException("Missing Icon: " + pathInJar);

            NativeImage image = NativeImage.read(inputStream);

            ByteBuffer pixelBuffer = MemoryUtil.memAlloc(image.getWidth() * image.getHeight() * 4);
            //~if 1.21.1 getPixelsABGR -> getPixelsRGBA
            pixelBuffer.asIntBuffer().put(image.getPixelsABGR());

            GLFWImage.Buffer buf = GLFWImage.malloc(1, stack);
            buf.width(image.getWidth());
            buf.height(image.getHeight());
            buf.pixels(pixelBuffer);

            GLFW.glfwSetWindowIcon(
                    //~ if <=1.21.8 'getWindow().handle()' -> 'getWindow().getWindow()'
                    Minecraft.getInstance().getWindow().handle(),
                    buf
            );

            image.close();
            MemoryUtil.memFree(pixelBuffer);
        }
    }
}
