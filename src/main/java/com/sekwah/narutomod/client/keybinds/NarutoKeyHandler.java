package com.sekwah.narutomod.client.keybinds;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.config.NarutoConfig;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NarutoKeyHandler {

    private static final Map<String, KeyBindingTickHeld> keys = new HashMap<>();

    private static final String NARUTO_KEY_CATEGORY = "narutomod.keys.category";

    private static String currentJutsuCombo = "";
    private static int ticksSinceLastKey = 0;

    private static KeyBinding LEAP_KEY;
    private static KeyBinding JUTSU_C_KEY;
    private static KeyBinding JUTSU_V_KEY;
    private static KeyBinding JUTSU_B_KEY;

    public static void registerKeyBinds() {
        LEAP_KEY = registerKeyBind("naruto.keys.leap", KeyEvent.VK_X);
        JUTSU_C_KEY = registerKeyBind("naruto.keys.key1", KeyEvent.VK_C);
        JUTSU_V_KEY = registerKeyBind("naruto.keys.key2", KeyEvent.VK_V);
        JUTSU_B_KEY = registerKeyBind("naruto.keys.key3", KeyEvent.VK_B);
    }

    public static KeyBinding registerKeyBind(String name, int keyCode) {
        if (!keys.containsKey(name)) {
            KeyBindingTickHeld key = new KeyBindingTickHeld(name, keyCode, NARUTO_KEY_CATEGORY);
            keys.put(name, key);
            ClientRegistry.registerKeyBinding(key);
            return key;
        }
        return null;
    }

    public static void handleKeyPress(int keyBind) {

    }

    /**
     * TODO Configure a held key threshold and block other keys from being able to be pressed while keys are held (to stop messing up combos)
     * @param event
     */
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        ticksSinceLastKey++;

        int keyHoldThreshold = NarutoConfig.JUTSU_KEYBIND_HOLD_THRESHOLD;

        Set<Map.Entry<String, KeyBindingTickHeld>> keySet = keys.entrySet();

        // TODO handle when this state should be true. Make sure when the held threshold is met that is the last key in the combo.
        // System.out.printf("CURRENT COMBO '%s'%n", currentJutsuCombo);
        for (Map.Entry<String, KeyBindingTickHeld> entry:
                keys.entrySet()) {
            KeyBindingTickHeld key = entry.getValue();
            KeyBindingTickHeld.KeyState test = key.consumeClickState();
            key.update();
            if(test != KeyBindingTickHeld.KeyState.NOT_PRESSED) {
                System.out.printf("KEYBIND %s %s %s%n", test.toString(), (char)key.getKey().getValue(), key.currentHeldValue());
            }
            int releaseDuration = key.consumeReleaseDuration();
            if(releaseDuration > 0) {
                System.out.printf("RELEASED %s %s%n", (char)key.getKey().getValue(), releaseDuration);
            }
        }
    }
}
