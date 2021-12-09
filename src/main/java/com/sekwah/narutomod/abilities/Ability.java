package com.sekwah.narutomod.abilities;

import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Ability extends ForgeRegistryEntry<Ability> {

    public Ability() {
    }

    public enum ActivationType {
        INSTANT,
        TOGGLE,
        // TODO needs implementation to be activatable and handled, they may end up using the same logic
        //CHANNELED,
        //CHARGED,
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
     * @param ninjaData
     * @param chargeAmount
     * @return if the jutsu cost was able to be fufilled. If this is true then perform will be triggered.
     */
    public abstract boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount);

    /**
     * Do not overwrite this, use {@link Ability#handleCost(Player, INinjaData, int)}
     *
     * This is just to allow code to call the cost without the charge amount.
     * @param player
     * @param ninjaData
     */
    public boolean handleCost(Player player, INinjaData ninjaData) {
        return handleCost(player, ninjaData, 0);
    }

    /**
     * This will trigger on the player when they are no longer able to cast the ability or when they re-active
     * the ability to cancel it.
     *
     * This will be triggered on ActivationType.TOGGLE, ActivationType.CHANNELED and ActivationType.CHARGED.
     *
     * @param player
     * @param ninjaData
     */
    public void handleAbilityEnded(Player player, INinjaData ninjaData) {}

    /**
     *
     * @param player the entity casting the jutsu. Will just be players for now. Though may be entity in the future.
     * @param ninjaData
     * @param chargeAmount
     */
    public abstract void perform(Player player, INinjaData ninjaData, int chargeAmount);

    /**
     *
     * For if abilities need to do anything client side (atm its only triggered with toggle to stop a spam of packets)
     *
     * @param player
     * @param ninjaData
     */
    public void performToggleClient(Player player, INinjaData ninjaData) {

    }

    /**
     * Do not overwrite this, use {@link Ability#perform(Player, INinjaData, int)}
     * @param player
     * @param ninjaData
     */
    public void perform(Player player, INinjaData ninjaData) {
        this.perform(player, ninjaData, 0);
    }

}
