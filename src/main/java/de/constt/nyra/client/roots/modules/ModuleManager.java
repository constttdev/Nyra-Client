package de.constt.nyra.client.roots.modules;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.modules.combat.*;
import de.constt.nyra.client.roots.modules.exploits.AutoBookBanSetup;
import de.constt.nyra.client.roots.modules.render.FullbrightModule;
import de.constt.nyra.client.utils.ModuleCacheUtils;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final List<ModuleImplementation> MODULES = new ArrayList<>();

    public static void init() {
        // MODULES
        // -- COMBAT --
        MODULES.add(new AimAssistModule());
        MODULES.add(new AnchorAuraModule());
        MODULES.add(new AntiAnchorModule());
        MODULES.add(new AntiAnvilModule());
        MODULES.add(new AntiBedModule());
        MODULES.add(new AttributeSwapModule());
        MODULES.add(new AutoAnvilModule());
        MODULES.add(new AutoArmorModule());
        MODULES.add(new AutoEXPModule());
        MODULES.add(new AutoLogModule());
        MODULES.add(new AutoTotemModule());
        MODULES.add(new AutoTrapModule());
        MODULES.add(new AutoWeaponModule());
        MODULES.add(new AutoWebModule());
        MODULES.add(new BedAuraModule());
        MODULES.add(new BowAimbotModule());
        MODULES.add(new BowSpamModule());
        MODULES.add(new BurrowModule());
        MODULES.add(new CriticalsModule());
        MODULES.add(new CrystalAuraModule());
        MODULES.add(new HitboxesModule());
        MODULES.add(new HitCrystalModule());
        MODULES.add(new HoleFillerModule());
        MODULES.add(new KillAuraModule());
        MODULES.add(new MaceArmorBreakerModule());
        MODULES.add(new OffhandModule());
        MODULES.add(new PearlCatcherModule());
        MODULES.add(new SafeAnchorModule());
        MODULES.add(new SelfAnvilModule());
        MODULES.add(new SelfTrapModule());
        MODULES.add(new SelfWebModule());
        MODULES.add(new ShieldBreakerModule());
        MODULES.add(new SpearDashModule());
        MODULES.add(new SurroundModule());
        MODULES.add(new TriggerbotModule());

        // -- EXPLOITS --
        MODULES.add(new AutoBookBanSetup());

        // RENDER
        MODULES.add(new FullbrightModule());

        ModuleCacheUtils.loadAll();
    }

    public static List<ModuleImplementation> getModules() {
        return MODULES;
    }

    public static void setBind(ModuleImplementation module, int keyBinding) {
        module.keyBindingCode = keyBinding;
    }

    public static int numModules() {
        return getModules().size();
    }

    public static <T extends ModuleImplementation> T getModule(Class<T> moduleClass) {
        for (var module : getModules()) {
            if (module.getClass() == moduleClass) {
                return moduleClass.cast(module);
            }
        }
        return null;
    }

    public static boolean isEnabled(Class<? extends ModuleImplementation> moduleClass) {
        var module = getModule(moduleClass);
        return module != null && module.getEnabledStatus();
    }

    public static void toggle(Class<? extends ModuleImplementation> moduleClass) {
        var module = getModule(moduleClass);
        if (module != null) {
            module.toggle();
        }
    }

    public static CategoryImplementation.Categories getCategory(Class<?> clazz) {
        return clazz.getAnnotation(ModuleInfoAnnotation.class).category();
    }
}