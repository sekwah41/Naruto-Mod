package com.sekwah.narutomod.client.keybinds;

import com.mojang.logging.LogUtils;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.abilities.NarutoAbilities;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.client.gui.JutsuScreen;
import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.network.PacketHandler;
import com.sekwah.narutomod.network.c2s.ServerAbilityChannelPacket;
import com.sekwah.narutomod.network.c2s.ServerJutsuCastingPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NarutoKeyHandler {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Map<String, KeyBindingTickHeld> keys = new HashMap<>();

    private static final String NARUTO_KEY_CATEGORY = "narutomod.keys.category";

    private static final List<KeyBindingTickHeld> JUTSU_KEYS = new ArrayList<>();

    /**
     * Limit the jutsu combo max to about 10 characters or so. We are storing as a long as there isn't much point of
     * storing it as a giant long string.
     */
    private static long currentJutsuCombo = 0;
    private static Ability currentJutsuComboAbility = null;
    /**
     * This will limit the jutsu value to 10 keys long
     */
    private static final long MAX_JUTSU_VALUE = 9999999999L;
    private static int ticksSinceLastKey = 0;
    private static boolean isCurrentlyChargingAbility = false;

    private static KeyBindingTickHeld LEAP_KEY;

    private static KeyBindingTickHeld JUTSU_C_KEY;
    private static KeyBindingTickHeld JUTSU_V_KEY;
    private static KeyBindingTickHeld JUTSU_B_KEY;

    private static KeyBindingTickHeld JUTSU_MENU_KEY;

    /**
     * Attached on the main NarutoMod file to the mod bus as this isn't a forge bus event
     * @param event
     */
    @SubscribeEvent
    public static void registerKeyBinds(RegisterKeyMappingsEvent event) {
        LEAP_KEY = registerKeyBind("naruto.keys.leap", KeyEvent.VK_X, event);
        JUTSU_C_KEY = registerKeyBind("naruto.keys.key1", KeyEvent.VK_C, event);
        JUTSU_V_KEY = registerKeyBind("naruto.keys.key2", KeyEvent.VK_V, event);
        JUTSU_B_KEY = registerKeyBind("naruto.keys.key3", KeyEvent.VK_B, event);
        JUTSU_MENU_KEY = registerKeyBind("naruto.keys.jutsu_menu", KeyEvent.VK_J, event);

        JUTSU_KEYS.add(LEAP_KEY);
        JUTSU_KEYS.add(JUTSU_C_KEY);
        JUTSU_KEYS.add(JUTSU_V_KEY);
        JUTSU_KEYS.add(JUTSU_B_KEY);
        JUTSU_KEYS.add(JUTSU_MENU_KEY);

        LEAP_KEY.registerClickConsumer( () -> {
            Minecraft mc = Minecraft.getInstance();
            if(mc.player != null ) {
                NarutoAbilities.triggerAbility(NarutoAbilities.LEAP.getId());
            }
        });

        JUTSU_MENU_KEY.registerClickConsumer( () -> {
            Minecraft mc = Minecraft.getInstance();
            if(mc.player != null ) {
                mc.setScreen(new JutsuScreen());
                //mc.player.displayClientMessage(Component.translatable("naruto.gui.jutsu.placeholder"), true);
            }
        });

        JUTSU_C_KEY.registerClickConsumer(() -> handleJustuKey(1));
        JUTSU_V_KEY.registerClickConsumer(() -> handleJustuKey(2));
        JUTSU_B_KEY.registerClickConsumer(() -> handleJustuKey(3));
    }

    public static KeyBindingTickHeld registerKeyBind(String name, int keyCode, RegisterKeyMappingsEvent event) {
        if (keys.containsKey(name)) {
            return keys.get(name);
        }

        KeyBindingTickHeld key = new KeyBindingTickHeld(name, keyCode, NARUTO_KEY_CATEGORY);
        keys.put(name, key);
        event.register(key);
        return key;
    }

    /**
     * When a jutsu key is pressed
     * @param i - jutsu key id
     */
    public static void handleJustuKey(int i) {
        // To cancel accidentally altering the value while the ability is charging
        if(isCurrentlyChargingAbility) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if(mc.player == null ) {
            return;
        }
        var player = mc.player;
        player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {
            if (!ninjaData.isNinjaModeEnabled()) {
                mc.player.displayClientMessage(Component.translatable("jutsu.not_a_ninja").withStyle(ChatFormatting.RED), true);
                return;
            }
            PacketHandler.sendToServer(new ServerJutsuCastingPacket(i));
            ticksSinceLastKey = 0;
            if (currentJutsuCombo < MAX_JUTSU_VALUE) {
                currentJutsuCombo *= 10;
                currentJutsuCombo += i;
                currentJutsuComboAbility = NarutoAbilities.getAbilityFromCombo(currentJutsuCombo);
            } else {
                LOGGER.info("Combo too long, ignoring keypress");
            }
        });
    }

    /**
     * TODO Configure a held key threshold and block other keys from being able to be pressed while keys are held (to stop messing up combos)
     */
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {

        if(currentJutsuCombo != 0) {
            ticksSinceLastKey++;
        }

        int lastKey = (int) (currentJutsuCombo % 10);
        boolean lastKeyHeld = false;
        boolean isPossibleAbilityCharged = currentJutsuComboAbility != null && currentJutsuComboAbility.activationType() == Ability.ActivationType.CHANNELED;
        KeyBindingTickHeld currentKey = null;

        // TODO fix keys held states
        if(lastKey > 0 && lastKey < JUTSU_KEYS.size()) {
            currentKey = JUTSU_KEYS.get(lastKey);
            if(currentKey.isDown()) {
                lastKeyHeld = true;
            }
            //NarutoMod.LOGGER.info("Key " + lastKey + "key" + key.getName() + " is held: " + key.isCurrentlyHeld() + " held: " + key.heldTicks + " isDown: " + key.isDown());
            // TODO check if above a current held threshold in case animations should be started.
            //lastKeyHeld = true;
        }

        if(isPossibleAbilityCharged) {
            checkCharging(currentKey, lastKeyHeld);
        } else {
            checkNonCharging();
        }

        //NarutoMod.LOGGER.info("Value is " + (currentJutsuCombo % 10));

        for (KeyBindingTickHeld key : JUTSU_KEYS) {
            key.update();
        }
    }

    private static void checkCharging(KeyBindingTickHeld currentKey, boolean isHoldingLastKey) {
        if(isHoldingLastKey && currentKey.heldTicks >= NarutoConfig.jutsuKeybindHoldThreshold) {
            if(!isCurrentlyChargingAbility) {
                NarutoAbilities.handleCharging(currentJutsuCombo, ServerAbilityChannelPacket.ChannelStatus.START);
            }
            isCurrentlyChargingAbility = true;
        } else if(!isHoldingLastKey) {
            // Just saying that the held amount has been handled. Though may want to use in the future.
            currentKey.consumeReleaseDuration();
            if(isCurrentlyChargingAbility) {
                NarutoAbilities.handleCharging(currentJutsuCombo, ServerAbilityChannelPacket.ChannelStatus.STOP);
                resetJutsuCasting();
            } else if(ticksSinceLastKey > NarutoConfig.jutsuCastDelay){
                NarutoAbilities.handleCharging(currentJutsuCombo, ServerAbilityChannelPacket.ChannelStatus.MIN_ACTIVATE);
                resetJutsuCasting();
            }
            isCurrentlyChargingAbility = false;
        }
    }

    private static void resetJutsuCasting() {
        ticksSinceLastKey = 0;
        currentJutsuCombo = 0;
        currentJutsuComboAbility = null;
        isCurrentlyChargingAbility = false;
    }

    /**
     * Check the logic if a charging jutsu isn't found or casting.
     */
    private static void checkNonCharging() {
        if(ticksSinceLastKey > NarutoConfig.jutsuCastDelay) {
            //NarutoMod.LOGGER.info("Would cast jutsu {}", currentJutsuCombo);
            Minecraft mc = Minecraft.getInstance();
            if(mc.player != null ) {
                LOGGER.debug("Casting jutsu {}", currentJutsuCombo);
                if(!NarutoAbilities.triggerAbility(currentJutsuCombo)) {
                    mc.player.displayClientMessage((Component.translatable("jutsu.error.notfound", currentJutsuCombo)).withStyle(ChatFormatting.RED), true);
                } else {
                    //mc.player.sendMessage(Component.translatable("trying.jutsu", currentJutsuCombo), true);
                }
            }
            resetJutsuCasting();
        }
    }
}
