package de.constt.nyra.client.roots.modules;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.modules.combat.*;
import de.constt.nyra.client.roots.modules.exploits.AutoBookBanSetupModule;
import de.constt.nyra.client.roots.modules.exploits.BlockedServerBypassModule;
import de.constt.nyra.client.roots.modules.exploits.BypasserModule;
import de.constt.nyra.client.roots.modules.misc.*;
import de.constt.nyra.client.roots.modules.movement.*;
import de.constt.nyra.client.roots.modules.player.*;
import de.constt.nyra.client.roots.modules.render.*;
import de.constt.nyra.client.roots.modules.world.*;
import de.constt.nyra.client.utils.ModuleCacheUtils;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private static final List<ModuleImplementation> MODULES =
            new ArrayList<>();

    public static void init() {

        // = = MODULES = =

        // -- COMBAT --

        // -- EXPLOITS --
        MODULES.add(new BypasserModule());

        // -- MISC --
        MODULES.add(new ClickGUIModule());
        MODULES.add(new PearlThrowModule());

        // -- MOVEMENT --
        MODULES.add(new NoSlowModule());

        // -- PLAYER --

        // -- RENDER --
        MODULES.add(new ESPModule());

        // -- WORLD --

        // = =  = =
    }

    public static List<ModuleImplementation> getModules() {
        return MODULES;
    }

    public static void setBind(
            ModuleImplementation module,
            int keyBinding
    ) {
        module.keyBindingCode = keyBinding;
    }

    public static int numModules() {
        return getModules().size();
    }

    public static <T extends ModuleImplementation> T getModule(
            Class<T> moduleClass
    ) {
        for (var module : getModules()) {
            if (module.getClass() == moduleClass) {
                return moduleClass.cast(module);
            }
        }

        return null;
    }

    public static boolean isEnabled(
            Class<? extends ModuleImplementation> moduleClass
    ) {
        var module = getModule(moduleClass);

        return module != null &&
                module.getEnabledStatus();
    }

    public static void toggle(
            Class<? extends ModuleImplementation> moduleClass
    ) {
        var module = getModule(moduleClass);

        if (module != null) {
            module.toggle();
        }
    }

    public static CategoryImplementation.Categories getCategory(
            Class<?> clazz
    ) {
        return clazz
                .getAnnotation(ModuleInfoAnnotation.class)
                .category();
    }
}