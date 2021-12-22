package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.world.entity.player.Player;

/**
 * More of a slight speed boost than an actual dash
 */
public class ChakraChargeAbility extends Ability {

    @Override
    public ActivationType activationType() {
        return ActivationType.CHARGED;
    }

    @Override
    public long defaultCombo() {
        return 1;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("Check Cost!!!");
        return true;
    }

    @Override
    public void handleAbilityEnded(Player player, INinjaData ninjaData, int ticksActive) {
        System.out.println("Ability ended");
    }

    @Override
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        System.out.println("CHANNELLING Dash!!!!");
    }
}
