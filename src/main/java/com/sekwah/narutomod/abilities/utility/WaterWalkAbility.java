package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.world.entity.player.Player;

/**
 * More of a slight speed boost than an actual dash
 */
public class WaterWalkAbility extends Ability {

    @Override
    public ActivationType activationType() {
        return ActivationType.TOGGLE;
    }

    @Override
    public long defaultCombo() {
        return 3;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("Cost Walk???");
        return true;
    }

    @Override
    public void handleAbilityEnded(Player player, INinjaData ninjaData) {
        System.out.println("Ability ended");
    }

    @Override
    public void perform(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("CHANNELLING Water!!!!");
    }
}
