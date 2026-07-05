package de.constt.nyra.client.roots.modules.misc;
import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;


@ModuleInfoAnnotation(name = "Packet Counter", description = "Counts the packets being sent/received and shows it to you", category = CategoryImplementation.Categories.MISC, internalModuleName = "packetcounter")
public class PacketCounterModule extends ModuleImplementation {
}