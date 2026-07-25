package de.constt.nyra.client.panels;

import de.constt.nyra.client.utils.FriendUtils;
import de.constt.nyra.client.utils.PlayerUtils;
import imgui.ImGui;
import imgui.type.ImString;
import net.minecraft.client.Minecraft;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FriendsPanel extends ClickGUIPanel {

    private final ImString userName = new ImString(256);
    private final Map<UUID, String> usernameCache = new ConcurrentHashMap<>();
    private static final String PENDING = "\0";

    public FriendsPanel() {
        super("Friends");
    }

    @Override
    protected void renderContent() {
        ImGui.spacing();

        ImGui.text("Friends");

        ImGui.spacing();

        ImGui.inputText("Username", userName);

        if (ImGui.button("Add")) {
            String username = userName.get().trim();
            if (!username.isEmpty()) {
                if(!(username.equals(Minecraft.getInstance().getUser().getName()))) {
                    FriendUtils.addFriend(username);
                    usernameCache.clear();
                }
            }
        }

        ImGui.spacing();

        for (UUID friendUUID : FriendUtils.getFriendUUIDs()) {
            ImGui.text(FriendUtils.getFriendName(friendUUID));

            ImGui.sameLine();

            if (ImGui.button("X##" + friendUUID)) {
                FriendUtils.removeFriend(friendUUID);
            }
        }
    }
}