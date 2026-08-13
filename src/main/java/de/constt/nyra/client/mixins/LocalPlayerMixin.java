package de.constt.nyra.client.mixins;

import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.movement.NoSlowModule;
import net.minecraft.client.player.LocalPlayer;
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
}