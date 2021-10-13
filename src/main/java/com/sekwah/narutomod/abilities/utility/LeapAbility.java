package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.sekclib.player.PlayerUtil;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class LeapAbility extends Ability {
    @Override
    public ActivationType activationType() {
        return ActivationType.INSTANT;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        player.sendMessage(new TextComponent("Cost stuff"), null);
        if(ninjaData.getStamina() > 20) {
            ninjaData.useStamina(20);
            return true;
        }
        player.sendMessage(new TextComponent("Not enough"), null);
        return false;
    }

    @Override
    public void perform(Player player, INinjaData ninjaData, int chargeAmount) {
        player.sendMessage(new TextComponent("Leap"), null);
        PlayerUtil.setVelocity(player, new Vec3(0, 2, 0));
    }
}
