package de.constt.nyra.client.clientcommands.utils;

import de.constt.nyra.client.annotations.CommandInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CommandImplementation;
import de.constt.nyra.client.utils.FriendUtils;
import de.constt.nyra.client.utils.MessageUtils;

@CommandInfoAnnotation(
        command = "friend",
        description = "Add / Remove or List your friends",
        name = "friend"
)
public class FriendCommand extends CommandImplementation {
    public FriendCommand() {
        setArgs(new String[]{"add", "remove", "list"});
    }

    @Override
    public void executeCommand(String[] parts) {
        super.executeCommand(parts);

        if (parts.length == 0) {
            MessageUtils.sendCSMessageError("Usage: .friend <add|remove|list> [player]");
            return;
        }

        String action = parts[0];

        if (action.equalsIgnoreCase("list")) {
            MessageUtils.sendCSMessageNeutral("Listing your friends:");

            FriendUtils.getFriendUUIDs().forEach(uuid -> {
                MessageUtils.sendCSMessageNeutral(String.valueOf(uuid));
            });

            return;
        }

        if (parts.length < 2) {
            MessageUtils.sendCSMessageError("Usage: .friend <add|remove> <player>");
            return;
        }

        String friend = parts[1];

        if (action.equalsIgnoreCase("add")) {
            if (!FriendUtils.isFriend(friend)) {
                FriendUtils.addFriend(friend);
                MessageUtils.sendCSMessageSucess("Added " + friend + " as your friend");
            } else {
                MessageUtils.sendCSMessageError(friend + " is already your friend!");
            }
        } else if (action.equalsIgnoreCase("remove")) {
            if (FriendUtils.isFriend(friend)) {
                FriendUtils.removeFriend(FriendUtils.getUUIDfromName(friend));
                MessageUtils.sendCSMessageSucess("Removed " + friend + " from your friends list");
            } else {
                MessageUtils.sendCSMessageError(friend + " is not found in your friends list!");
            }
        } else {
            MessageUtils.sendCSMessageError("Unknown action: " + action);
        }
    }
}
