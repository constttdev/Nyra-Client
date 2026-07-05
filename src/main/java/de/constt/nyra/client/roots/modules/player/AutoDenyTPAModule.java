package de.constt.nyra.client.roots.modules.player;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Auto Deny TPA", description = "Automatically denies TPA requests for you", category = CategoryImplementation.Categories.PLAYER, internalModuleName = "autodenytpa")
public class AutoDenyTPAModule extends ModuleImplementation {
}