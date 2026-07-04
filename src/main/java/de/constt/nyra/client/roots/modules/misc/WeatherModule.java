package de.constt.nyra.client.roots.modules.misc;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Weather", description = "Enables the Weather module.", category = CategoryImplementation.Categories.MISC, internalModuleName = "weather")
public class WeatherModule extends ModuleImplementation {
}