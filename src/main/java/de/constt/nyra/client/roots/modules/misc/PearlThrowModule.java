package de.constt.nyra.client.roots.modules.misc;

import de.constt.nyra.client.annotations.ModuleInfoAnnotation;
import de.constt.nyra.client.roots.implementations.CategoryImplementation;
import de.constt.nyra.client.roots.implementations.ModuleImplementation;
import de.constt.nyra.client.roots.implementations.settings.BooleanSettingImplementation;
import de.constt.nyra.client.roots.implementations.settings.NumberSettingImplementation;
import de.constt.nyra.client.utils.InventoryUtils;
import imgui.ImGui;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@ModuleInfoAnnotation(
        name = "Pearl Throw",
        description = "Automatically tries to throw a pearl for you",
        category = CategoryImplementation.Categories.MISC,
        internalModuleName = "pearlthrow"
)
public class PearlThrowModule extends ModuleImplementation {

    private final BooleanSettingImplementation autoswap =
            new BooleanSettingImplementation(
                    "Auto swap slot",
                    false
            );

    private final BooleanSettingImplementation swapBackToSlot =
            new BooleanSettingImplementation(
                    "Swap Back to Slot",
                    true
            );

    private final NumberSettingImplementation slotSwapDelay =
            new NumberSettingImplementation(
                    "Slot Swap Delay",
                    0,
                    0,
                    100
            );

    public PearlThrowModule() {
        registerSetting(autoswap);
        registerSetting(swapBackToSlot);
        registerSetting(slotSwapDelay);
    }

    @Override
    public void renderCustomSettings() {
        super.renderCustomSettings();

        ImGui.text(
                "Enable the \"Disable on Toggle\" Setting to make the module work without any complications."
        );
    }

    @Override
    public void onEnable() {
        super.onEnable();

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.gameMode == null) {
            return;
        }

        if (!InventoryUtils.hasItemInHotbar(Items.ENDER_PEARL)) {
            return;
        }

        int previousSlot =
                mc.player.getInventory().getSelectedSlot();

        for (int slot = 0; slot < 9; slot++) {

            ItemStack stack =
                    mc.player.getInventory().getItem(slot);

            if (!stack.is(Items.ENDER_PEARL)) {
                continue;
            }

            if (!autoswap.get() && previousSlot != slot) {
                return;
            }

            if (autoswap.get()) {
                mc.player.getInventory().setSelectedSlot(slot);
            }

            mc.gameMode.useItem(
                    mc.player,
                    InteractionHand.MAIN_HAND
            );

            if (autoswap.get() && swapBackToSlot.get()) {
                long delay = slotSwapDelay.get().longValue();

                Thread.startVirtualThread(() -> {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }

                    mc.execute(() -> {
                        if (mc.player != null) {
                            mc.player.getInventory().setSelectedSlot(previousSlot);
                        }
                    });
                });
            }

            return;
        }
    }
}