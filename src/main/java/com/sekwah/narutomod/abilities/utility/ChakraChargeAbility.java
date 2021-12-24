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
        // This jutsu can always be cast
        return true;
    }

    @Override
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        // Add particle effects n stuff
    }


    @Override
    public void handleChannelling(Player player, INinjaData ninjaData, int ticksChanneled) {
       if(player.isSprinting() || !player.isOnGround()) {
           ninjaData.addChakra(0.2f);
       } else {
           ninjaData.addChakra(1f);
       }
    }

    @Override
    public boolean canActivateBelowMinCharge() {
        return false;
    }
}
