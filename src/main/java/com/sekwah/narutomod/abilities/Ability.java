package com.sekwah.narutomod.abilities;

import com.sekwah.narutomod.capabilities.CooldownTickEvent;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.narutomod.registries.NarutoRegistries;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

public abstract class Ability {

    public Ability() {
    }

    public enum ActivationType {
        INSTANT,
        TOGGLE,
        CHANNELED
    }

    /**
     * For now the combo to register the ability with. May be overrideable in the future.
     * @return
     */
    public long defaultCombo() {
        return -1;
    }

    /**
     * When returning different activation types make sure you have the correct methods implemented otherwise certain things may not happen
     * see {@link Toggled} and {@link Channeled}
     * @return
     */
    public abstract ActivationType activationType();

    /**
     * Also tell the player why they can't cast the ability.
     *
     * Handle the activation message in perform.
     *
     * In channeled abilities, this will be triggered every tick. And the charge amount will be increased by 1 each tick.
     *
     * If this fails and is above 0 for charge amount the last successful will call perform.
     *
     * If channeled and chargeAmount is -1, it will either be a minCast (if enabled) or the stop packet is
     * received at the same time. If this returns false then perform will not be called.
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
     * If the ability should say its status in chat
     * @return
     */
    public boolean logInChat() {
        return true;
    }

    /**
     *
     * @return sound to play, if null no sound should be played
     */
    public SoundEvent castingSound() {
       return NarutoSounds.JUTSU_CAST.get();
    }

    /**
     * Sound to play when an ability fails to cast.
     * @return
     */
    public SoundEvent castingFailSound() {
        return NarutoSounds.JUTSU_FAIL.get();
    }

    public String getTranslationKey(INinjaData ninjaData) {
        var resourceKey = NarutoRegistries.ABILITIES.getResourceKey(this);
        return this.getTranslationKey(ninjaData, 0);
    }

    /**
     * If something should be added to the end of the translation string to modify what is said.
     *
     * No longer can replace based on registry name as those are not longer provided by the forge entries.
     * @param ticksActive
     * @return
     */
    public String getTranslationKey(INinjaData ninjaData, int ticksActive) {
        var resourceKey = NarutoRegistries.ABILITIES.getResourceKey(this);
        if(resourceKey.isPresent()) {
            return resourceKey.get().location().toString();
        }
        return "";
    }

    /**
     *
     * @param player the entity casting the jutsu. Will just be players for now. Though may be entity in the future.
     * @param ninjaData
     * @param ticksActive
     */
    public abstract void performServer(Player player, INinjaData ninjaData, int ticksActive);

    /**
     * Do not overwrite this, use {@link Ability#performServer(Player, INinjaData, int)}
     * @param player
     * @param ninjaData
     */
    public void performServer(Player player, INinjaData ninjaData) {
        this.performServer(player, ninjaData, 0);
    }

    public interface Toggled {

        /**
         *
         * For if abilities need to do anything client side (atm its only triggered with toggle to stop a spam of packets)
         *
         * @param player
         * @param ninjaData
         */
        void performToggleClient(Player player, INinjaData ninjaData);

    }

    /**
     * Channeled and charged abilities are handled the same way as there is so much overlap.
     */
    public interface Channeled {

        /**
         * If the jutsu is not channeled at all, should the jutsu activate? Stops abilities like the channeling activating for a single tick.
         *
         * @return
         */
        default boolean canActivateBelowMinCharge() {
            return true;
        }

        /**
         * If to use the charged translation strings instead of charged.
         *
         * This alters if the chat should show the ability as stopped or activated or charged and cast.
         *
         * @return
         */
        default boolean useChargedMessages() {
            return false;
        }

        /**
         * In case of other use cases where you don't want the messages. for custom states e.g. substitution.
         * @return
         */
        default boolean hideChannelMessages() {
            return false;
        }

        /**
         * Call every tick handleCost passes on server side.
         *
         * This is the main behavior that seperates a "channeled" ability from a "charged" ability as the behaviors are the same.
         *  @param player
         * @param ninjaData
         * @param ticksChanneled
         */
        default void handleChannelling(Player player, INinjaData ninjaData, int ticksChanneled) {}
    }

    public interface HandleEnded {
        /**
         * This will trigger on the player when they are no longer able to cast the ability or when they re-active
         * the ability to cancel it.
         *
         * For now the toggle abilities will return 0, though will return how long they lasted in the future
         * e.g. if a jutsu should hurt you more if it was left on for longer.
         *
         * This will be triggered on ActivationType.TOGGLE and ActivationType.CHARGED.
         *
         * @param player
         * @param ninjaData
         */
        void handleAbilityEnded(Player player, INinjaData ninjaData, int ticksActive);
    }

    /**
     * Interface class to append a cooldown to a jutsu.
     */
    public interface Cooldown
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
                player.displayClientMessage(Component.translatable("jutsu.fail.cooldown",
                        Component.translatable(translationKey).withStyle(ChatFormatting.YELLOW),
                        Component.literal(String.valueOf((int) Math.ceil(ninjaData.getCooldownEvents().get(translationKey).ticks / 20f))).withStyle(ChatFormatting.YELLOW)
                ), true);
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
}
