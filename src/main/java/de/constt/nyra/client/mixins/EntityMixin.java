package de.constt.nyra.client.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.movement.NoSlowModule;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = {"isInWater", "isInLava"}, at = @At("HEAD"), cancellable = true)
    private void nyra$noSlowFluid(CallbackInfoReturnable<Boolean> cir) {
        if(!ModuleManager.isEnabled(NoSlowModule.class)) return;

        if((boolean) Objects.requireNonNull(ModuleManager.getModule(NoSlowModule.class)).getSetting("Fluids").get()) {
            cir.cancel();
        }
    }

    @Inject(
            method = "makeStuckInBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void nyra$noSlow(BlockState blockState, Vec3 speedMultiplier, CallbackInfo ci) {
        if (!ModuleManager.isEnabled(NoSlowModule.class)) {
            return;
        }

        NoSlowModule module = ModuleManager.getModule(NoSlowModule.class);

        if (blockState.is(Blocks.COBWEB) &&
                (boolean) module.getSetting("Cobweb").get()) {
            ci.cancel();
        }
    }
}