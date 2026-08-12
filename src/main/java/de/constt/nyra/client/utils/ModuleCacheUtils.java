package de.constt.nyra.client.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.SettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleCacheUtils {
    private static final String CACHE_FILENAME = "modules.json";
    private static final Type MODULE_DATA_LIST_TYPE = new TypeToken<List<ModuleData>>(){}.getType();

    private static class ModuleData {
        String name;
        boolean enabled;
        Map<String, JsonElement> settings = new HashMap();

        ModuleData() {}

        ModuleData(String name, boolean enabled, Map<String, JsonElement> settings) {
            this.name = name;
            this.enabled = enabled;
            this.settings = settings;
        }
    }

    public static void saveAll() {
        List<ModuleData> dataList = new ArrayList<>();

        for (ModuleImplementation module : ModuleManager.getModules()) {
            dataList.add(serializeModule(module));
        }

        CacheUtils.save(CACHE_FILENAME, dataList);
    }

    public static void loadAll() {
        System.out.println("[Nyra] ModuleCacheUtils.loadAll()");

        List<ModuleData> dataList = CacheUtils.load(
                CACHE_FILENAME,
                MODULE_DATA_LIST_TYPE,
                ArrayList::new
        );

        System.out.println("[Nyra] Saved modules: " + dataList.size());
        System.out.println("[Nyra] Registered modules: " + ModuleManager.getModules().size());

        if (dataList.isEmpty()) {
            System.out.println("[Nyra] No saved module data found");
            return;
        }

        for (ModuleData data : dataList) {
            System.out.println("[Nyra] Loading module: " + data.name);

            ModuleImplementation module = findModuleByName(data.name);

            if (module == null) {
                System.out.println("[Nyra] Module not found: " + data.name);
                continue;
            }

            System.out.println("[Nyra] Found module: " + module.getTranslatableText());

            boolean shouldBeEnabled = data.enabled;

            if (shouldBeEnabled && !module.getEnabledStatus()) {
                module.toggle();
            } else if (!shouldBeEnabled && module.getEnabledStatus()) {
                module.toggle();
            }

            applySettings(module, data.settings);
        }
    }

    public static void saveModule(ModuleImplementation module) {
        if (!CacheUtils.exists(CACHE_FILENAME)) {
            saveAll();
            return;
        }

        List<ModuleData> dataList = CacheUtils.load(
                CACHE_FILENAME,
                MODULE_DATA_LIST_TYPE,
                ArrayList::new
        );

        dataList.removeIf(data -> data.name.equals(module.getTranslatableText()));
        dataList.add(serializeModule(module));

        CacheUtils.save(CACHE_FILENAME, dataList);
    }

    private static ModuleData serializeModule(ModuleImplementation module) {
        Map<String, JsonElement> settingsMap = new HashMap<>();

        for (SettingImplementation<?> setting : module.getSettings()) {
            settingsMap.put(setting.getName(), serializeSetting(setting));
        }

        return new ModuleData(
                module.getTranslatableText(),
                module.getEnabledStatus(),
                settingsMap
        );
    }

    private static JsonElement serializeSetting(SettingImplementation<?> setting) {
        Object value = setting.get();
        if (value instanceof Boolean) {
            return new JsonPrimitive((Boolean) value);
        } else if (value instanceof Number) {
            return new JsonPrimitive((Number) value);
        } else if (value instanceof String) {
            return new JsonPrimitive((String) value);
        } else {
            return new JsonPrimitive(value.toString());
        }
    }

    private static void applySettings(ModuleImplementation module, Map<String, JsonElement> settingsMap) {
        for (Map.Entry<String, JsonElement> entry : settingsMap.entrySet()) {
            System.out.println("[Nyra] Loading setting: " + entry.getKey() + " = " + entry.getValue());

            SettingImplementation<?> setting = module.getSetting(entry.getKey());

            if (setting == null) {
                System.out.println("[Nyra] Setting NOT FOUND: " + entry.getKey());
                continue;
            }

            System.out.println(
                    "[Nyra] Found setting: " +
                            setting.getName() +
                            " current=" +
                            setting.get()
            );

            try {
                applySettingValue(setting, entry.getValue());

                System.out.println(
                        "[Nyra] Applied setting: " +
                                setting.getName() +
                                " new=" +
                                setting.get()
                );
            } catch (Exception e) {
                System.err.println(
                        "[Nyra] Failed to apply setting " +
                                entry.getKey() +
                                ": " +
                                e.getMessage()
                );
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> void applySettingValue(SettingImplementation<T> setting, JsonElement value) {
        Object currentValue = setting.get();

        if (currentValue instanceof Boolean) {
            setting.set((T) Boolean.valueOf(value.getAsBoolean()));
        } else if (currentValue instanceof Integer) {
            setting.set((T) Integer.valueOf(value.getAsInt()));
        } else if (currentValue instanceof Double) {
            setting.set((T) Double.valueOf(value.getAsDouble()));
        } else if (currentValue instanceof Float) {
            setting.set((T) Float.valueOf(value.getAsFloat()));
        } else if (currentValue instanceof Long) {
            setting.set((T) Long.valueOf(value.getAsLong()));
        } else if (currentValue instanceof String) {
            setting.set((T) value.getAsString());
        } else {
            System.err.println(
                    "Unsupported setting type for " +
                            setting.getName() +
                            ": " +
                            (currentValue == null ? "null" : currentValue.getClass().getName())
            );
        }
    }

    private static ModuleImplementation findModuleByName(String name) {
        for (ModuleImplementation module : ModuleManager.getModules()) {
            if (module.getTranslatableText().equals(name)) {
                return module;
            }
        }
        return null;
    }
}