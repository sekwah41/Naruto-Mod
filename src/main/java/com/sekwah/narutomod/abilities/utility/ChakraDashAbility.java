package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.narutomod.sounds.NarutoSounds;
import com.sekwah.sekclib.player.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

/**
 * More of a slight speed boost than an actual dash
 */
public class ChakraDashAbility extends Ability {

    @Override
    public ActivationType activationType() {
        return ActivationType.TOGGLE;
    }

    @Override
    public long defaultCombo() {
        return 2;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("Cost Dash???");
        return true;
    }

    @Override
    public void handleAbilityEnded(Player player, INinjaData ninjaData) {
        System.out.println("Ability ended");
    }

    @Override
    public void perform(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("CHANNELLING Dash!!!!");
    }
}
