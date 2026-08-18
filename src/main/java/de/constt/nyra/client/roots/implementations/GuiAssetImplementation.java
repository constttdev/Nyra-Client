package de.constt.nyra.client.roots.implementations;

public abstract class GuiAssetImplementation<T> {
    private final String name;
    protected T value;

    public String getName() {
        return name;
    }

    protected GuiAssetImplementation(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
    }

    public T get() {
        return value;
    }

    public abstract void renderImGui();
}
