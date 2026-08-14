package de.constt.nyra.client.roots.implementations.settings;

import de.constt.nyra.client.roots.implementations.SettingImplementation;
import imgui.ImGui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SelectSettingImplementation extends SettingImplementation<String[]> {

    private String[] options;

    public SelectSettingImplementation(String name, String[] options, String[] defaultValues) {
        super(name, defaultValues.clone());
        this.options = options.clone();
    }

    public SelectSettingImplementation(String name, String[] options) {
        this(name, options, new String[0]);
    }

    public void setOptions(String[] options) {
        this.options = options.clone();

        List<String> valid = new ArrayList<>();

        for (String value : get()) {
            if (containsOption(value)) {
                valid.add(value);
            }
        }

        set(valid.toArray(new String[0]));
    }

    public String[] getOptions() {
        return options.clone();
    }

    public String[] getSelected() {
        return get().clone();
    }

    public boolean isSelected(String value) {
        for (String selected : get()) {
            if (selected.equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void add(String value) {
        if (!containsOption(value) || isSelected(value)) {
            return;
        }

        String[] current = get();
        String[] updated = Arrays.copyOf(current, current.length + 1);
        updated[current.length] = value;

        set(updated);
    }

    public void remove(String value) {
        String[] current = get();
        List<String> updated = new ArrayList<>(Arrays.asList(current));

        if (!updated.remove(value)) {
            return;
        }

        set(updated.toArray(new String[0]));
    }

    @Override
    public void renderImGui() {
        ImGui.text(getName());

        ImGui.text("Available");

        for (String option : options) {
            if (!isSelected(option)) {
                ImGui.pushID("available_" + option);

                if (ImGui.button("+")) {
                    add(option);
                }

                ImGui.sameLine();
                ImGui.text(option);

                ImGui.popID();
            }
        }

        ImGui.separator();

        ImGui.text("Selected");

        for (String option : get().clone()) {
            ImGui.pushID("selected_" + option);

            if (ImGui.button("-")) {
                remove(option);
            }

            ImGui.sameLine();
            ImGui.text(option);

            ImGui.popID();
        }
    }

    private boolean containsOption(String value) {
        for (String option : options) {
            if (option.equals(value)) {
                return true;
            }
        }

        return false;
    }
}