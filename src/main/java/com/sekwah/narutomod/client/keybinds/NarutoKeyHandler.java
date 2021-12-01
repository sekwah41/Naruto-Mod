package com.sekwah.narutomod.client.keybinds;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.abilities.NarutoAbilities;
import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.network.PacketHandler;
import com.sekwah.narutomod.network.c2s.ServerJutsuCastingPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NarutoKeyHandler {

    private static final Map<String, KeyBindingTickHeld> keys = new HashMap<>();

    private static final String NARUTO_KEY_CATEGORY = "narutomod.keys.category";

    private static final List<KeyBindingTickHeld> JUTSU_KEYS = new ArrayList<>();

    /**
     * Limit the jutsu combo max to about 10 characters or so. We are storing as a long as there isn't much point of
     * storing it as a giant long string.
     */
    private static long currentJutsuCombo = 0;
    /**
     * This will limit the jutsu value to 10 keys long
     */
    private static final long MAX_JUTSU_VALUE = 9999999999L;
    private static int ticksSinceLastKey = 0;

    private static KeyBindingTickHeld LEAP_KEY;

    private static KeyBindingTickHeld JUTSU_C_KEY;
    private static KeyBindingTickHeld JUTSU_V_KEY;
    private static KeyBindingTickHeld JUTSU_B_KEY;

    public static void registerKeyBinds() {
        LEAP_KEY = registerKeyBind("naruto.keys.leap", KeyEvent.VK_X);
        JUTSU_C_KEY = registerKeyBind("naruto.keys.key1", KeyEvent.VK_C);
        JUTSU_V_KEY = registerKeyBind("naruto.keys.key2", KeyEvent.VK_V);
        JUTSU_B_KEY = registerKeyBind("naruto.keys.key3", KeyEvent.VK_B);

        JUTSU_KEYS.add(JUTSU_C_KEY);
        JUTSU_KEYS.add(JUTSU_V_KEY);
        JUTSU_KEYS.add(JUTSU_B_KEY);
        JUTSU_KEYS.add(LEAP_KEY);

        LEAP_KEY.registerClickConsumer( () -> {
            Minecraft mc = Minecraft.getInstance();
            if(mc.player != null ) {
                NarutoAbilities.triggerAbility(NarutoAbilities.LEAP.getId());
            }
        });

        JUTSU_C_KEY.registerClickConsumer(() -> handleJustuKey(1));
        JUTSU_V_KEY.registerClickConsumer(() -> handleJustuKey(2));
        JUTSU_B_KEY.registerClickConsumer(() -> handleJustuKey(3));
    }

    public static KeyBindingTickHeld registerKeyBind(String name, int keyCode) {
        if (keys.containsKey(name)) {
            return keys.get(name);
        }

        KeyBindingTickHeld key = new KeyBindingTickHeld(name, keyCode, NARUTO_KEY_CATEGORY);
        keys.put(name, key);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }

    /**
     * When a jutsu key is pressed
     * @param i - jutsu key id
     */
    public static void handleJustuKey(int i) {
        PacketHandler.sendToServer(new ServerJutsuCastingPacket(i));
        ticksSinceLastKey = 0;
        if (currentJutsuCombo < MAX_JUTSU_VALUE) {
            currentJutsuCombo *= 10;
            currentJutsuCombo += i;
        } else {
            NarutoMod.LOGGER.info("Combo too long, ignoring keypress");
        }
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

        // TODO fix keys held states
        /*if(lastKey > 0 && lastKey < JUTSU_KEYS.size()) {
            KeyBindingTickHeld key = JUTSU_KEYS.get(lastKey);
            key.isCurrentlyHeld();
            System.out.println(key.heldTicks);
            // TODO check if above a current held threshold in case animations should be started.
            lastKeyHeld = true;
        }*/

        if(/*!lastKeyHeld &&*/ ticksSinceLastKey > NarutoConfig.jutsuCastDelay) {
            NarutoMod.LOGGER.info("Would cast jutsu {}", currentJutsuCombo);
            Minecraft mc = Minecraft.getInstance();
            if(mc.player != null ) {
                mc.player.sendMessage(new TranslatableComponent("jutsu.cast.debug", currentJutsuCombo), null);
            }
            if(!NarutoAbilities.triggerAbility(currentJutsuCombo)) {
                mc.player.sendMessage(new TranslatableComponent("no.combo.found", currentJutsuCombo), null);
            } else {
                mc.player.sendMessage(new TranslatableComponent("trying.jutsu", currentJutsuCombo), null);
            }

            ticksSinceLastKey = 0;
            currentJutsuCombo = 0;
        }

        //NarutoMod.LOGGER.info("Value is " + (currentJutsuCombo % 10));

        for (KeyBindingTickHeld key : JUTSU_KEYS) {
            key.update();
        }
    }
}
