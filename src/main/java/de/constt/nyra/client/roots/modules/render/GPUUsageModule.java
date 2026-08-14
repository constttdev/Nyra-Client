package de.constt.nyra.client.roots.modules.misc;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "GPU Usage", description = "Shows your GPU usage on screen", category = CategoryImplementation.Categories.MISC, internalModuleName = "gpuusage")
public class GPUUsageModule extends ModuleImplementation {
}