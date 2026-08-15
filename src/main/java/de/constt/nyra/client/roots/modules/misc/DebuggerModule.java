package de.constt.nyra.client.roots.modules.misc;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.BooleanSettingImplementation;

@ModuleInfoAnnotation(name = "Debugger", description = "Logs when modules get toggled", category = CategoryImplementation.Categories.MISC, internalModuleName = "debugger")
public class DebuggerModule extends ModuleImplementation {
    private final BooleanSettingImplementation logModuleStatus;

    public DebuggerModule() {
        logModuleStatus = new BooleanSettingImplementation("Log Module Status", true);

        registerSetting(logModuleStatus);
    }
}
