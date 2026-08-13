package de.constt.nyra.client.roots.modules.movement;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.BooleanSettingImplementation;


@ModuleInfoAnnotation(name = "No Slow", description = "Disables slowing effects for you", category = CategoryImplementation.Categories.MOVEMENT, internalModuleName = "noslow")
public class NoSlowModule extends ModuleImplementation {
    BooleanSettingImplementation cobWeb = new BooleanSettingImplementation("Cobweb", true);
    BooleanSettingImplementation soulSand = new BooleanSettingImplementation("Soulsand", true);
    BooleanSettingImplementation slowness = new BooleanSettingImplementation("Slowness", true);
    // BooleanSettingImplementation hunger = new BooleanSettingImplementation("Hunger", true);
    BooleanSettingImplementation sneaking = new BooleanSettingImplementation("Sneaking", true);
    BooleanSettingImplementation slimeBlock = new BooleanSettingImplementation("Slime Blocks", true);
    BooleanSettingImplementation honeyBlock = new BooleanSettingImplementation("Honey Blocks", true);
    BooleanSettingImplementation fluids = new BooleanSettingImplementation("Fluids", true);
    BooleanSettingImplementation items = new BooleanSettingImplementation("Items", true);

    public NoSlowModule() {
        registerSetting(cobWeb);
        registerSetting(soulSand);
        registerSetting(slowness);
        // registerSetting(hunger); --- wierdly when testing hunger didnt slow me down, so it wont be registered as a working setting currently
        registerSetting(sneaking);
        registerSetting(slimeBlock);
        registerSetting(honeyBlock);
        registerSetting(fluids);
        registerSetting(items);
    }

    // TODO: Make Slowness, Items and Soulsand work in this Module somehow.
}