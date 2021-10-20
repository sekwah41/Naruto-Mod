package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.world.entity.player.Player;

public class ChakraChargeAbility extends Ability {

    @Override
    public ActivationType activationType() {
        return ActivationType.CHANNELED;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("Cost???");
        return true;
    }

    @Override
    public long defaultCombo() {
        return 1;
    }

    @Override
    public void perform(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("CHANNELLING!!!!");
    }
}
