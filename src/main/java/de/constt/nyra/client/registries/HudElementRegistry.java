package de.constt.nyra.client.registries;

import de.constt.nyra.client.libs.render.RenderingLibrary;
import de.constt.nyra.client.utils.VarUtils;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;

public class HudElementRegistry {
    public static void register() {
        net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry.attachElementBefore(
                VanillaHudElements.CROSSHAIR,
                Identifier.fromNamespaceAndPath(VarUtils.getModID(), "arraylist"),
                RenderingLibrary::drawModulesList
        );
    }
}