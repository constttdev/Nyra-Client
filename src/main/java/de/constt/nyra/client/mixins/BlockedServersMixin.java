package de.constt.nyra.client.mixins;


import com.mojang.patchy.BlockedServers;
import de.constt.nyra.client.roots.implementations.DomainsImplementation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockedServers.class)
public class BlockedServersMixin {

    @Inject(method = "isBlockedServerHostName", at = @At("RETURN"), cancellable = true, remap = false)
    public void isBlockedServerHostName(String server, CallbackInfoReturnable<Boolean> cir) {
        boolean contains = DomainsImplementation.contains(server);
        if (contains) {
            cir.setReturnValue(false);
        }
    }
}