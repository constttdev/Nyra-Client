package de.constt.nyra.client.clientcommands.utils;

import de.constt.nyra.client.annotations.CommandInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CommandImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.utils.MessageUtils;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;

@CommandInfoAnnotation(
        command = "toggle",
        description = "Toggles a module",
        name = "toggle"
)
public class ToggleCommand extends CommandImplementation {
    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);

        if (parts.length == 0) {
            MessageUtils.sendCSMessageError("Usage: .toggle <module>");
            return;
        }

        String moduleName = parts[0];

        for (var module : ModuleManager.getModules()) {
            if (ModuleAnnotationUtils.getInternalModuleName(module.getClass()).equalsIgnoreCase(moduleName)) {
                ModuleManager.toggle(module.getClass());

                if (module.getEnabledStatus()) {
                    MessageUtils.sendCSMessageNeutral(
                            ModuleAnnotationUtils.getName(module.getClass()) + " §7(§aon§r§7)"
                    );
                } else {
                    MessageUtils.sendCSMessageNeutral(
                            ModuleAnnotationUtils.getName(module.getClass()) + " §7(§coff§r§7)"
                    );
                }

                return;
            }
        }

        MessageUtils.sendCSMessageError("Module: " + moduleName + " not found!");
    }
}
