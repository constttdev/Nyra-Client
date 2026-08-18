package de.constt.nyra.client.roots.implementations.settings;

import de.constt.nyra.client.roots.implementations.SettingImplementation;
import imgui.ImGui;
import imgui.type.ImInt;

public final class NumberSliderSettingImplementation extends SettingImplementation<Integer> {

    private final int min;
    private final int max;
    private final int step;
    private final ImInt imValue;

    public NumberSliderSettingImplementation(String name, int defaultValue, int min, int max, int step) {
        super(name, defaultValue);
        this.min = min;
        this.max = max;
        this.step = step;
        this.imValue = new ImInt(defaultValue);
    }

    @Override
    public void renderImGui() {
        int value = imValue.get();

        if (ImGui.sliderInt(getName(), imValue.getData(), min, max)) {
            int steppedValue = Math.round((float) (imValue.get() - min) / step) * step + min;
            steppedValue = Math.clamp(steppedValue, min, max);

            if (steppedValue != value) {
                set(steppedValue);
            } else {
                imValue.set(steppedValue);
            }
        }
    }

    @Override
    public void set(Integer value) {
        int steppedValue = Math.round((float) (value - min) / step) * step + min;
        steppedValue = Math.clamp(steppedValue, min, max);

        super.set(steppedValue);
        imValue.set(steppedValue);
    }
}