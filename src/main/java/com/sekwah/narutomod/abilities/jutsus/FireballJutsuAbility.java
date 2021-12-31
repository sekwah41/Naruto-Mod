package com.sekwah.narutomod.abilities.jutsus;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.narutomod.entity.jutsuprojectile.FireballJutsuEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

/**
 * More of a slight speed boost than an actual dash
 */
public class FireballJutsuAbility extends Ability {

    @Override
    public ActivationType activationType() {
        return ActivationType.INSTANT;
    }

    @Override
    public long defaultCombo() {
        return 121;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        if(ninjaData.getChakra() < 30) {
            player.displayClientMessage(new TranslatableComponent("jutsu.fail.notenoughchakra", new TranslatableComponent(this.getTranslationKey()).withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }
        ninjaData.useChakra(30, 30);
        return true;
    }

    @Override
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        Vec3 shootSpeed = player.getLookAngle();
        FireballJutsuEntity fireball = new FireballJutsuEntity(player, shootSpeed.x, shootSpeed.y, shootSpeed.z);
        player.getLevel().addFreshEntity(fireball);
    }
}
