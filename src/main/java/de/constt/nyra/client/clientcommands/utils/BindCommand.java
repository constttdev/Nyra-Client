package de.constt.nyra.client.clientcommands.utils;

import de.constt.nyra.client.annotations.CommandInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CommandImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.utils.ConfigManagerUtils;
import de.constt.nyra.client.utils.MessageUtils;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;
import org.lwjgl.glfw.GLFW;

@CommandInfoAnnotation(
        command = "bind",
        description = "Binds a module key",
        name = "bind"
)
public class BindCommand extends CommandImplementation {

    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);

        if (parts.length < 2) {
            MessageUtils.sendCSMessageNeutral("Usage: $bind <module> <key>");
            return;
        }

        String moduleName = parts[0];
        String keyName = parts[1].toUpperCase();

        for (ModuleImplementation module : ModuleManager.getModules()) {

            String internalName = ModuleAnnotationUtils.getInternalModuleName(module.getClass());

            if (internalName.equalsIgnoreCase(moduleName)) {

                int key = getKey(keyName);

                if (key == GLFW.GLFW_KEY_UNKNOWN) {
                    MessageUtils.sendCSMessageNeutral("Unknown key: " + keyName);
                    return;
                }

                module.keyBindingCode = key;
                ConfigManagerUtils.addKeybind(module, key);

                MessageUtils.sendCSMessageNeutral(
                        "Bound " + moduleName + " to " + keyName
                );
                return;
            }
        }

        MessageUtils.sendCSMessageNeutral(
                "Module not found: " + moduleName
        );
    }

    private int getKey(String key) {

        for (int i = GLFW.GLFW_KEY_A; i <= GLFW.GLFW_KEY_Z; i++) {
            String name = GLFW.glfwGetKeyName(i, 0);

            if (name != null && name.equalsIgnoreCase(key)) {
                return i;
            }
        }

        return switch (key) {
            case "SPACE" -> GLFW.GLFW_KEY_SPACE;
            case "SHIFT" -> GLFW.GLFW_KEY_LEFT_SHIFT;
            case "CTRL" -> GLFW.GLFW_KEY_LEFT_CONTROL;
            case "ALT" -> GLFW.GLFW_KEY_LEFT_ALT;
            default -> GLFW.GLFW_KEY_UNKNOWN;
        };
    }
}