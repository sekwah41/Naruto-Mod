package com.sekwah.narutomod.jutsus;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Ability extends ForgeRegistryEntry<Ability> {

    public Ability() {
    }

    public enum ActivationType {
        INSTANT,
        CHANNELED,
        CHARGED,
        TOGGLE,
    }

    /**
     * For now the combo to register the ability with. May be overrideable in the future.
     * @return
     */
    public int defaultCombo() {
        return 0;
    }

    public abstract ActivationType activationType();

    /**
     * Also tell the player why they can't cast the ability.
     *
     * Handle the activation message in perform.
     *
     * In channeled abilities, this will be triggered every tick. And the charge amount will be increased by 1 each tick.
     *
     * @param player
     * @return if the jutsu cost was able to be fufilled. If this is true then perform will be triggered.
     */
    public abstract boolean handleCost(Player player, int chargeAmount);

    /**
     *
     * @param player the entity casting the jutsu. Will just be players for now. Though may be entity in the future.
     */
    public abstract void perform(Player player, int chargeAmount);

}
