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
        command = "unbind",
        description = "Removes a module keybind",
        name = "unbind"
)
public class UnbindCommand extends CommandImplementation {

    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);

        if (parts.length < 1) {
            MessageUtils.sendCSMessageNeutral("Usage: $unbind <module>");
            return;
        }

        String moduleName = parts[0].toLowerCase();

        for (ModuleImplementation module : ModuleManager.getModules()) {

            if (ModuleAnnotationUtils.getInternalModuleName(module.getClass())
                    .equalsIgnoreCase(moduleName)) {

                module.keyBindingCode = GLFW.GLFW_KEY_UNKNOWN;
                ConfigManagerUtils.removeKeybind(module);

                MessageUtils.sendCSMessageNeutral(
                        "Unbound " + moduleName
                );
                return;
            }
        }

        MessageUtils.sendCSMessageNeutral(
                "Module not found: " + moduleName
        );
    }
}