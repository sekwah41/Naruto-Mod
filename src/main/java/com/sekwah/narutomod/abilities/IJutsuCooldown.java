package com.sekwah.narutomod.abilities;

import com.sekwah.narutomod.capabilities.CooldownTickEvent;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

/**
 * Interface class to append a cooldown to a jutsu.
 */
public interface IJutsuCooldown
{

    /**
     * Method to  get the cooldown  value.
     * @return the cooldown specified.
     */
    int getCooldown();

    /**
     * Check if a cooldown exists for this jutus.
     * @param player - the player the jutsu is being cased from.
     * @param ninjaData - the ninjaData capability attached to the player.
     * @param translationKey - the translation key for the jutsu / unique name.
     * @return  return true if a cooldown exists or false if no cooldown exists.
     */
    default boolean checkCooldown(Player player, INinjaData ninjaData, String translationKey) {
        if  (getCooldown() > 0 && ninjaData.getCooldownEvents().containsKey(translationKey)) {
            player.displayClientMessage(new TranslatableComponent("jutsu.fail.cooldown", new TranslatableComponent(translationKey).withStyle(ChatFormatting.YELLOW)), true);
            return  true;
        }
        return  false;
    }

    /**
     * Registers that a cooldown should exist for this jutus and sets a cooldown timer in the NinjaData
     * @param ninjaData - the ninjaData capability attached to the player.
     * @param translationKey - the translation key for the jutsu / unique name.
     */
    default void registerCooldown(INinjaData ninjaData, String translationKey) {
        if (getCooldown() > 0)  {
            ninjaData.getCooldownEvents().put(translationKey, new CooldownTickEvent(getCooldown()));
        }
    }
}
