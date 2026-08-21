package de.constt.nyra.client.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.constt.nyra.client.roots.implementations.settings.NumberSliderSettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.movement.NoSlowModule;
import de.constt.nyra.client.roots.modules.render.HandViewModule;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void nyra$noSneakSlow(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;

        var attribute = player.getAttribute(Attributes.SNEAKING_SPEED);
        if (attribute == null) {
            return;
        }

        NoSlowModule module = ModuleManager.getModule(NoSlowModule.class);

        if (module != null
                && ModuleManager.isEnabled(NoSlowModule.class)
                && (Boolean) module.getSetting("Sneaking").get()) {
            attribute.setBaseValue(1.0);
        } else {
            attribute.setBaseValue(0.3);
        }
    }
    @Inject(
            method = "swing",
            at = @At("TAIL")
    )
    private void nyra$modifySwingSpeed(
            InteractionHand hand,
            CallbackInfo ci
    ) {
        if (!ModuleManager.isEnabled(HandViewModule.class)) {
            return;
        }

        LivingEntity entity = (LivingEntity) (Object) this;

        if (!entity.level().isClientSide()) {
            return;
        }

        HandViewModule module = ModuleManager.getModule(HandViewModule.class);

        if (module == null) {
            return;
        }

        double speed = ((NumberSliderSettingImplementation)
                module.getSetting("Swing Speed")).get();

        double multiplier = 1.0 + (speed / 100.0);

        if (multiplier <= 0.01) {
            multiplier = 0.01;
        }

        entity.swingTime = Math.max(
                1,
                (int) (entity.swingTime / multiplier)
        );
    }
}