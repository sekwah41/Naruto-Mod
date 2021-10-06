package com.sekwah.narutomod.jutsus.abilities;

import com.sekwah.narutomod.jutsus.Ability;
import net.minecraft.world.entity.player.Player;

public class LeapAbility extends Ability {
    @Override
    public ActivationType activationType() {
        return ActivationType.INSTANT;
    }

    @Override
    public boolean handleCost(Player player, int chargeAmount) {
        return false;
    }

    @Override
    public void perform(Player player, int chargeAmount) {

    }
}
