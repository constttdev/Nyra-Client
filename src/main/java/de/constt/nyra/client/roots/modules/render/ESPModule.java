package de.constt.nyra.client.roots.modules.render;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.libs.render.CustomRenderingPipeline;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.ColorSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.EntitySettingImplementation;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelExtractionEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityTypes;

@ModuleInfoAnnotation(
        name = "ESP",
        description = "Displays esp information on screen.",
        category = CategoryImplementation.Categories.RENDER,
        internalModuleName = "esp"
)
public class ESPModule extends ModuleImplementation {

    public EntitySettingImplementation entitySetting =
            new EntitySettingImplementation("Entity", EntityTypes.ZOMBIE);

    public ColorSettingImplementation colorSetting =
            new ColorSettingImplementation("ESP Color", 0xFF1C1C1C);

    public ESPModule() {
        registerSetting(entitySetting);
        registerSetting(colorSetting);

        LevelExtractionEvents.END_EXTRACTION.register(context -> render());
    }

    private void render() {
        if (!getEnabledStatus()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null || mc.player == null) {
            return;
        }

        float[] rgba = colorSetting.getRGBA();

        for (var entity : mc.level.entitiesForRendering()) {
            if (entity == mc.player) {
                continue;
            }

            if (!entitySetting.matches(entity)) {
                continue;
            }

            CustomRenderingPipeline.addEntity(
                    entity,
                    rgba[0],
                    rgba[1],
                    rgba[2],
                    rgba[3]
            );
        }
    }
}