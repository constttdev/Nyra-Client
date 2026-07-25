package de.constt.nyra.client.clientcommands.utils;

import de.constt.nyra.client.annotations.CommandInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CommandImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.misc.ClickGUIModule;
import net.minecraft.client.Minecraft;


@CommandInfoAnnotation(
        command = "clickgui",description = "Opens the click gui", name = "clickgui"
)
public class ClickGuiCommand extends CommandImplementation {
    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);

        Minecraft.getInstance().execute(() -> {
            ModuleManager.toggle(ClickGUIModule.class);
        });
    }
}
