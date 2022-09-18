package com.sekwah.narutomod.mixin.client;

import com.sekwah.narutomod.accessors.NinjaPlayerModelAccessor;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PlayerRenderer.class)
public class PlayerRenderMixin {

    @Inject(method = "setModelProperties", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void setModelProperties(AbstractClientPlayer player, CallbackInfo ci, PlayerModel playermodel) {
        NinjaPlayerModelAccessor ninjaPlayerModel = (NinjaPlayerModelAccessor) playermodel;

        ninjaPlayerModel.setNinjaRunning(player.isSprinting());
    }


}
