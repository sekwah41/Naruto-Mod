package com.sekwah.narutomod.abilities;

import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;


public class TestAbility extends Ability {
    @Override
    public ActivationType activationType() {
        return ActivationType.INSTANT;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        player.sendMessage(new TextComponent("Cost stuff"), null);
        if(ninjaData.getStamina() > 20) {
            ninjaData.useStamina(20, 40);
            return true;
        }
        player.sendMessage(new TextComponent("Not enough"), null);
        return false;
    }

    @Override
    public long defaultCombo() {
        return 1;
    }

    @Override
    public void perform(Player player, INinjaData ninjaData, int chargeAmount) {
        player.sendMessage(new TextComponent("This is from the stuff"), null);
        player.push(0, 1, 0);
    }
}
