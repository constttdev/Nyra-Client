package de.constt.nyra.client.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.constt.nyra.client.roots.modules.ModuleManager;
import de.constt.nyra.client.roots.modules.movement.NoSlowModule;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Block.class)
public abstract class BlockMixin {

    @ModifyReturnValue(method = "getFriction", at = @At("RETURN"))
    private float nyra$modifyFriction(float original) {
        NoSlowModule module = ModuleManager.getModule(NoSlowModule.class);

        if (!ModuleManager.isEnabled(NoSlowModule.class) || module == null) {
            return original;
        }

        Block block = (Block) (Object) this;

        if (block == Blocks.SLIME_BLOCK
                && (boolean) module.getSetting("Slime Blocks").get()) {
            return 0.01F;
        }

        if (block == Blocks.HONEY_BLOCK
                && (boolean) module.getSetting("Honey Blocks").get()) {
            return 0.01F;
        }

        return original;
    }
}