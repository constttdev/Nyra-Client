package de.constt.nyra.client.events;

import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.screens.BaseScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class InputEventHandler {
    private static final Map<ModuleImplementation, Boolean> moduleKeyStates = new HashMap<>();

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            long window = Minecraft.getInstance().getWindow().handle();
            Screen screen = Minecraft.getInstance().gui.screen();

            for (ModuleImplementation module : ModuleManager.getModules()) {
                int key = module.getKeybindingCode();
                if (key <= 0) continue;

                boolean isPressed  = GLFW.glfwGetKey(window, key) == GLFW.GLFW_PRESS;
                boolean wasPressed = moduleKeyStates.getOrDefault(module, false);

                if (isPressed && !wasPressed) {
                    if (screen instanceof ChatScreen || screen instanceof BaseScreen) return;
                    module.toggle();
                }

                moduleKeyStates.put(module, isPressed);
            }
        });
    }
}