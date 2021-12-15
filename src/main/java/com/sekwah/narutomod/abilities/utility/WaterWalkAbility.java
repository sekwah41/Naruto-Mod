package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
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

    private final float CHAKRA_COST = 0.1F;
    private final int CHARKA_COOLDOWN = 15;

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("Cost Walk???");
        if(ninjaData.getChakra() < CHAKRA_COST) {
            player.displayClientMessage(new TranslatableComponent("jutsu.fail.notenoughchakra", new TranslatableComponent("jutsu.waterwalk").withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }
        ninjaData.useChakra(CHAKRA_COST, CHARKA_COOLDOWN);
        return true;
    }

    @Override
    public void handleAbilityEnded(Player player, INinjaData ninjaData) {
        System.out.println("Ability ended");
    }

    @Override
    public void perform(Player player, INinjaData ninjaData, int chargeAmount) {
        // TODO validation that the player is above water
        player.fallDistance = 0.0F;
    }

    @Override
    public void performToggleClient(Player player, INinjaData ninjaData) {
        player.setPos(player.getX(), player.getY() + 0.2f, player.getZ());
    }
}
