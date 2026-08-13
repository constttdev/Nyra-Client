package de.constt.nyra.client.mixins;

import de.constt.nyra.client.roots.implementations.settings.BooleanSettingImplementation;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.movement.NoSlowModule;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> effect);

    @Inject(method = "getSpeed", at = @At("RETURN"), cancellable = true)
    private void nyra$removeSlowness(CallbackInfoReturnable<Float> cir) {
        NoSlowModule module = ModuleManager.getModule(NoSlowModule.class);

        if (module == null || !module.isEnabled()) {
            return;
        }

        BooleanSettingImplementation setting =
                (BooleanSettingImplementation) module.getSetting("Slowness");

        if (!setting.get() || !this.hasEffect(MobEffects.SLOWNESS)) {
            return;
        }

        LivingEntity entity = (LivingEntity) (Object) this;

        float speed = (float) entity.getAttributeValue(
                net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED
        );

        cir.setReturnValue(speed);
    }
}