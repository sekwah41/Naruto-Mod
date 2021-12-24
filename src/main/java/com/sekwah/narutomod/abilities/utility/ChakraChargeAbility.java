package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.world.entity.player.Player;

/**
 * More of a slight speed boost than an actual dash
 */
public class ChakraChargeAbility extends Ability implements Ability.Channeled {

    @Override
    public ActivationType activationType() {
        return ActivationType.CHANNELED;
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
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        System.out.println("CHANNELLING Dash!!!!");
    }

    @Override
    public void handleChannelling(Player player, INinjaData ninjaData, int ticksChanneled) {
        System.out.println("Channelling");
    }

    @Override
    public boolean canActivateBelowMinCharge() {
        return false;
    }
}
