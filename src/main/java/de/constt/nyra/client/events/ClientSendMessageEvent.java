package de.constt.nyra.client.events;

import de.constt.nyra.client.clientcommands.CCommandManager;
import de.constt.nyra.client.utils.CommandAnnotationUtils;
import de.constt.nyra.client.utils.MessageUtils;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;

import java.util.Arrays;

public class ClientSendMessageEvent {

    public static void register() {

        ClientSendMessageEvents.ALLOW_CHAT.register((message) -> {

            if (message.startsWith(CCommandManager.cmdPrefix)) {
                LocalPlayer player = Minecraft.getInstance().player;
                if (player == null) return false;

                String raw = message.substring(CCommandManager.cmdPrefix.length()).trim();

                if (raw.isEmpty()) {
                    MessageUtils.sendCSMessageNeutral("Command cannot be empty");
                    return false;
                }

                String[] split = raw.split(" ");

                String cmdName = split[0];
                String[] args = split.length > 1
                        ? Arrays.copyOfRange(split, 1, split.length)
                        : new String[0];

                boolean found = false;

                for (var command : CCommandManager.getCommands()) {
                    String name = CommandAnnotationUtils.getCommand(command.getClass());

                    if (name != null && name.equalsIgnoreCase(cmdName)) {
                        found = true;
                        command.executeCommand(args);
                        break;
                    }
                }

                if (!found) {
                    MessageUtils.sendCSMessageNeutral(
                            "Unknown command: " + cmdName
                    );
                }

                return false;
            }

            if (message.startsWith("#")) {

                String msg;

                if (message.length() > 1 && message.charAt(1) == ' ') {
                    msg = message.substring(2);
                } else {
                    msg = message.substring(1);
                }

                MessageUtils.sendCSMessageNeutral(
                        "Message started with #, sending this message to IPC: " + msg
                );

                return false;
            }

            return true;
        });
    }
}