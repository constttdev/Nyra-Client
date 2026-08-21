package de.constt.nyra.client.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import de.constt.nyra.client.roots.implementations.settings.DoubleSettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.render.HandViewModule;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Unique
    private final HandViewModule handView = ModuleManager.getModule(HandViewModule.class);

    @Unique
    private double setting(String name) {
        assert handView != null;
        return ((DoubleSettingImplementation) handView.getSetting(name)).get();
    }

    @Unique
    private void applyMainHand(PoseStack poseStack) {
        poseStack.translate(
                setting("Pos (X) - MH"),
                setting("Pos (Y) - MH"),
                setting("Pos (Z) - MH")
        );

        poseStack.scale(
                (float) setting("Size (X) - MH"),
                (float) setting("Size (Y) - MH"),
                (float) setting("Size (Z) - MH")
        );
    }

    @Unique
    private void applyOffHand(PoseStack poseStack) {
        poseStack.translate(
                setting("Pos (X) - OH"),
                setting("Pos (Y) - OH"),
                setting("Pos (Z) - OH")
        );

        poseStack.scale(
                (float) setting("Size (X) - OH"),
                (float) setting("Size (Y) - OH"),
                (float) setting("Size (Z) - OH")
        );
    }

    @Unique
    private void applyHands(PoseStack poseStack) {
        poseStack.translate(
                setting("Pos (X) - H"),
                setting("Pos (Y) - H"),
                setting("Pos (Z) - H")
        );

        poseStack.scale(
                (float) setting("Size (X) - H"),
                (float) setting("Size (Y) - H"),
                (float) setting("Size (Z) - H")
        );
    }

    @Inject(
            method = "renderItem",
            at = @At("HEAD")
    )
    private void nyra$transformItem(
            LivingEntity mob,
            ItemStack itemStack,
            ItemDisplayContext type,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            CallbackInfo ci
    ) {
        if (!ModuleManager.isEnabled(HandViewModule.class)) {
            return;
        }

        if (type == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND) {
            if (mob.getMainHandItem() == itemStack) {
                applyMainHand(poseStack);
            } else {
                applyOffHand(poseStack);
            }
        }

        if (type == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
            if (mob.getMainHandItem() == itemStack) {
                applyMainHand(poseStack);
            } else {
                applyOffHand(poseStack);
            }
        }
    }

    @Inject(
            method = "renderPlayerArm",
            at = @At("HEAD")
    )
    private void nyra$transformHand(
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            float equipProgress,
            float swingProgress,
            HumanoidArm arm,
            CallbackInfo ci
    ) {
        if (!ModuleManager.isEnabled(HandViewModule.class)) {
            return;
        }

        applyHands(poseStack);
    }
}