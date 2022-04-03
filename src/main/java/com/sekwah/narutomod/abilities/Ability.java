package com.sekwah.narutomod.abilities;

import com.sekwah.narutomod.capabilities.CooldownTickEvent;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Ability extends ForgeRegistryEntry<Ability> {

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
     * If channeled and chargeAmount is 0, it will either be a minCast (if enabled) or the stop packet is
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

    public String getTranslationKey() {
        return this.getTranslationKey(0);
    }

    /**
     * Mostly for channeled abilities. but in case an ability evolves as its channeled.
     * @param ticksActive
     * @return
     */
    public String getTranslationKey(int ticksActive) {
        return this.getRegistryName().toString();
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
                player.displayClientMessage(new TranslatableComponent("jutsu.fail.cooldown",
                        new TranslatableComponent(translationKey).withStyle(ChatFormatting.YELLOW),
                        new TextComponent(String.valueOf((int) Math.ceil(ninjaData.getCooldownEvents().get(translationKey).ticks / 20f))).withStyle(ChatFormatting.YELLOW)
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
