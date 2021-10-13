package com.sekwah.narutomod.abilities;

import com.sekwah.narutomod.capabilities.INinjaData;
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
    public long defaultCombo() {
        return -1;
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
    public abstract boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount);

    /**
     * Do not overwrite this, use {@link Ability#handleCost(Player, INinjaData, int)}
     * @param player
     */
    public boolean handleCost(Player player, INinjaData ninjaData) {
        return handleCost(player, ninjaData, 0);
    }

    /**
     *
     * @param player the entity casting the jutsu. Will just be players for now. Though may be entity in the future.
     */
    public abstract void perform(Player player, INinjaData ninjaData, int chargeAmount);

    /**
     * Do not overwrite this, use {@link Ability#perform(Player, INinjaData, int)}
     * @param player
     */
    public void perform(Player player, INinjaData ninjaData) {
        this.perform(player, ninjaData, 0);
    }

}
