package com.aetherteam.aetherii.mixin.mixins.common;

import com.aetherteam.aetherii.world.DynamicWorldLights;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.*;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IBlockStateExtension.class)
public interface IBlockStateExtensionMixin {
    @Inject(method = "getLightEmission(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)I", at = @At(value = "RETURN"), cancellable = true)
    private void getLightEmission(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        int dynamicLight = DynamicWorldLights.getDynamicLight(pos);
        if (dynamicLight > 0) {
            cir.setReturnValue(DynamicWorldLights.calculateLightEmission(cir.getReturnValue(), dynamicLight));
        }
    }
}
