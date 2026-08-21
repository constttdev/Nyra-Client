package de.constt.nyra.client.roots.implementations;

import de.constt.nyra.client.roots.implementations.settings.BooleanSettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.misc.DebuggerModule;
import de.constt.nyra.client.utils.MessageUtils;
import de.constt.nyra.client.utils.ModuleAnnotationUtils;
import de.constt.nyra.client.utils.ModuleCacheUtils;
import net.minecraft.network.protocol.Packet;
import org.lwjgl.glfw.GLFW;

import java.util.*;

public abstract class ModuleImplementation {

    protected boolean enabled = false;
    public int keyBindingCode = GLFW.GLFW_KEY_UNKNOWN;

    private final Map<String, SettingImplementation<?>> settings = new LinkedHashMap<>();
    private final Collection<GuiAssetImplementation<?>> guiAssets = new ArrayList<>();
    private final List<Object> guiElements = new ArrayList<>();

    protected ModuleImplementation() {
        registerSetting(new BooleanSettingImplementation("Disable on toggle", false));
    }

    protected void registerSetting(SettingImplementation<?> setting) {
        settings.put(setting.getName(), setting);
        guiElements.add(setting);
        setting.addChangeListener(this::onSettingChanged);
    }

    protected void registerGuiAsset(GuiAssetImplementation<?> asset) {
        guiAssets.add(asset);
        guiElements.add(asset);
    }

    public List<Object> getGuiElements() {
        return guiElements;
    }

    protected void onSettingChanged(SettingImplementation<?> setting) {
        ModuleCacheUtils.saveModule(this);
    }

    public void renderCustomSettings() {
    }

    public SettingImplementation<?> getSetting(String name) {
        return settings.get(name);
    }

    public Collection<SettingImplementation<?>> getSettings() {
        return settings.values();
    }

    public Collection<GuiAssetImplementation<?>> getGuiAssets() {
        return guiAssets;
    }

    public void toggle() {
        BooleanSettingImplementation disableOnToggle =
                (BooleanSettingImplementation) getSetting("Disable on toggle");

        if (enabled) {
            if (disableOnToggle != null && disableOnToggle.get()) {
                enabled = false;
                onDisable();
                ModuleCacheUtils.saveModule(this);
                return;
            }

            enabled = false;
            onDisable();
            ModuleCacheUtils.saveModule(this);
            return;
        }

        enabled = true;
        onEnable();
        ModuleCacheUtils.saveModule(this);

        String status = this.getEnabledStatus() ? "on" : "off";
        String statusColorCoded;

        if (status.equals("on")) {
            statusColorCoded = "§aon";
        } else {
            statusColorCoded = "§coff";
        }

        if (ModuleManager.isEnabled(DebuggerModule.class)) {
            if ((boolean) Objects.requireNonNull(
                    ModuleManager.getModule(DebuggerModule.class)
            ).getSetting("Log Module Status").get()) {
                MessageUtils.sendCSMessageNeutral(
                        "§8Toggled§r " +
                                ModuleAnnotationUtils.getName(this.getClass()) +
                                " (" +
                                statusColorCoded +
                                ")"
                );
            }
        }
    }

    public int getKeybindingCode() {
        return keyBindingCode;
    }

    public String getTranslatableText() {
        return ModuleAnnotationUtils.getName(this.getClass());
    }

    public boolean getEnabledStatus() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void tick() {
    }

    public void postTick() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public boolean modifyPacket(Packet<?> packet) {
        return false;
    }
}