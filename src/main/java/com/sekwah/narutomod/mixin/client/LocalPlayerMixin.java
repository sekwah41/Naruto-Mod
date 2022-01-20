package com.sekwah.narutomod.mixin.client;

import com.mojang.authlib.GameProfile;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.abilities.NarutoAbilities;
import com.sekwah.narutomod.capabilities.DoubleJumpData;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.sekwah.narutomod.capabilities.NinjaCapabilityHandler.NINJA_DATA;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin extends AbstractClientPlayer {

    public LocalPlayerMixin(ClientLevel p_108548_, GameProfile p_108549_) {
        super(p_108548_, p_108549_);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", ordinal = 0,
            target = "Lnet/minecraft/client/player/LocalPlayer;getItemBySlot(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;"))
    public void aiStep(CallbackInfo ci) {
        ItemStack itemstack = this.getItemBySlot(EquipmentSlot.CHEST);
        if (!itemstack.canElytraFly(this) && !this.onGround && !this.isFallFlying() && !this.isInWater() && !this.hasEffect(MobEffects.LEVITATION)) {
            this.getCapability(NINJA_DATA).ifPresent(ninjaData -> {
                DoubleJumpData doubleJumpData = ninjaData.getDoubleJumpData();
                if(doubleJumpData != null) {
                    if(doubleJumpData.canDoubleJumpClient) {
                        doubleJumpData.clientJump();
                        Vec3 movement = this.getDeltaMovement();
                        this.lerpMotion(movement.x, 0.5F, movement.z);
                        NarutoAbilities.triggerAbility(NarutoAbilities.DOUBLE_JUMP.getId());
                    }
                }
            });
        }
    }

}
