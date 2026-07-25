package de.constt.nyra.client.roots.implementations.settings;

import de.constt.nyra.client.roots.implementations.SettingImplementation;
import imgui.ImGui;
import imgui.type.ImInt;

public final class ListSettingImplementation extends SettingImplementation<String> {

    private String[] options;
    private final ImInt imIndex;

    public ListSettingImplementation(String name, String[] options, String defaultValue) {
        super(name, defaultValue);
        this.options = options;
        this.imIndex = new ImInt(indexOf(defaultValue));
    }

    public void setOptions(String[] options) {
        this.options = options;

        if (options.length > 0 && !contains(value)) {
            set(options[0]);
        }
    }

    public String[] getOptions() {
        return options;
    }

    @Override
    public void renderImGui() {
        imIndex.set(indexOf(value));

        if (ImGui.combo(getName(), imIndex, options)) {
            set(options[imIndex.get()]);
        }
    }

    private int indexOf(String val) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(val)) {
                return i;
            }
        }

        return 0;
    }

    private boolean contains(String val) {
        for (String option : options) {
            if (option.equals(val)) {
                return true;
            }
        }

        return false;
    }
}