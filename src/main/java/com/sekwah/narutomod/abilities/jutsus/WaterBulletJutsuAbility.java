package com.sekwah.narutomod.abilities.jutsus;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.narutomod.entity.jutsuprojectile.FireballJutsuEntity;
import com.sekwah.narutomod.entity.jutsuprojectile.WaterBulletJutsuEntity;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

/**
 * More of a slight speed boost than an actual dash
 */
public class WaterBulletJutsuAbility extends Ability implements Ability.Cooldown {

    @Override
    public ActivationType activationType() {
        return ActivationType.INSTANT;
    }

    @Override
    public long defaultCombo() {
        return 132;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        if(ninjaData.getChakra() < 30) {
            player.displayClientMessage(new TranslatableComponent("jutsu.fail.notenoughchakra", new TranslatableComponent(this.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }
        ninjaData.useChakra(30, 30);
        return true;
    }

    @Override
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        for (int i = 0; i < 3; i++) {
            ninjaData.scheduleDelayedTickEvent((delayedPlayer) -> {
                Vec3 shootSpeed = player.getLookAngle();
                WaterBulletJutsuEntity waterBullet = new WaterBulletJutsuEntity(player, shootSpeed.x, shootSpeed.y, shootSpeed.z);
                waterBullet.setYRot(player.getYRot() - 180);
                player.getLevel().addFreshEntity(waterBullet);
                player.getLevel().playSound(null, player, NarutoSounds.WATER_BULLET_SHOOT.get(), SoundSource.PLAYERS, 1f, 1.0f);
            }, 10 + i * 15);
        }

    }

    @Override
    public int getCooldown() {
        return 4 * 20;
    }
}
