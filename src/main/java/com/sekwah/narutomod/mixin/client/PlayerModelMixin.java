package com.sekwah.narutomod.mixin.client;

import com.sekwah.narutomod.accessors.NinjaPlayerModelAccessor;
import com.sekwah.narutomod.anims.PlayerAnimHandler;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public class PlayerModelMixin<T extends LivingEntity> extends HumanoidModel<T> implements NinjaPlayerModelAccessor {

    private boolean isNinjaRunning = false;

    public PlayerModelMixin(ModelPart p_170677_) {
        super(p_170677_, RenderType::entityTranslucent);
    }

    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V", at = @At(value = "TAIL"))
    public void setupAnim(Entity player, float par2, float par3, float par4, float par5, float par6, CallbackInfo ci) {
        PlayerAnimHandler.sprintingAnim(player, (PlayerModel) (Object) this);
    }

    @Override
    public boolean isNinjaRunning() {
        return this.isNinjaRunning;
    }

    @Override
    public void setNinjaRunning(boolean running) {
        this.isNinjaRunning = running;
    }
}
