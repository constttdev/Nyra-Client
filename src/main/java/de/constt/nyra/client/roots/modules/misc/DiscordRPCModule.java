package de.constt.nyra.client.roots.modules.misc;
import com.jagrosh.discordipc.entities.ActivityType;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.discordRpc.DiscordIPCCore;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.ListSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.StringSettingImplementation;


@ModuleInfoAnnotation(name = "Discord RPC", description = "Toggles Nyra Clients Discord Rich Presence", category = CategoryImplementation.Categories.MISC, internalModuleName = "discordrpc")
public class DiscordRPCModule extends ModuleImplementation {

    public final StringSettingImplementation state;
    public final StringSettingImplementation details;
    public final ListSettingImplementation activityType;

    public DiscordRPCModule() {
        state = new StringSettingImplementation("State", "Being Open-Source");
        details = new StringSettingImplementation("Details", "%mc_version%");
        activityType = new ListSettingImplementation("Activity Type", new String[]{"Competing", "Listening", "Playing", "Streaming", "Watching"}, "Playing");

        registerSetting(state);
        registerSetting(details);
        registerSetting(activityType);
    }

    public ActivityType getActivityType() {
        return switch (activityType.get()) {
            case "Competing" -> ActivityType.Competing;
            case "Listening" -> ActivityType.Listening;
            case "Playing" -> ActivityType.Playing;
            case "Streaming" -> ActivityType.Streaming;
            case "Watching" -> ActivityType.Watching;
            default -> ActivityType.Playing;
        };
    }

    @Override
    public void onEnable() {
        super.onEnable();
        DiscordIPCCore.start();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        DiscordIPCCore.shutdown();
    }
}