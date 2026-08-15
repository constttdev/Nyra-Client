package de.constt.nyra.client;

import de.constt.nyra.client.clientcommands.CCommandManager;
import de.constt.nyra.client.libs.render.RenderingLibrary;
import de.constt.nyra.client.managers.EventManager;
import de.constt.nyra.client.payloads.PayloadManager;
import de.constt.nyra.client.registries.HudElementRegistry;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.utils.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NyraMod implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("nyra");
    public static final String VERSION = /*$ mod_version*/ "0.0.1";
    public static final String MINECRAFT = /*$ minecraft*/ "26.2";

    @Override
    public void onInitializeClient() {
        LOGGER.info("Nyra {} for MC {} initializing...", VERSION, MINECRAFT);


        // -- Payloads --
        PayloadManager.init();

        // -- Variable Setting --
        MessageUtils.setPrefix(Component.literal("Nyra"));

        // -- Events --
        EventManager.registerEvents();

        // -- Modules --
        ModuleManager.init();

        // -- Rendering --
        RenderingLibrary.register();

        // -- Commands --
        CCommandManager.init();

        // -- Config Managers --
        ConfigManagerUtils.loadAllModuleKeybinds();

        String title = "Nyra | CL V. " + VERSION + " | MC V. " + MINECRAFT + " | #" + InstanceUtils.getInstanceId();

        ClientTickEvents.END_CLIENT_TICK.register(client -> client.getWindow().setTitle(title));

        // Array List
        HudElementRegistry.register();
    }

    /**
     * Adapts to the {@link Identifier} changes introduced in 1.21.
     */
    public static Identifier id(String namespace, String path) {
        //? if <1.21 {
        /*return new Identifier(namespace, path);
         *///?} else
        return Identifier.fromNamespaceAndPath(namespace, path);
    }
}