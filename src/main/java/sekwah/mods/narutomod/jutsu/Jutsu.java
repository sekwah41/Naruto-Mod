package sekwah.mods.narutomod.jutsu;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import sekwah.mods.narutomod.client.NarutoKeyHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Not implemented in this version, the mod still has the disgusting old builder.
 */
public class Jutsu {

    public static final String KEY_ONE = "1";
    public static final String KEY_TWO = "2";
    public static final String KEY_THREE = "3";

    public static final int FORWARD = 0;
    public static final int BACK = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    private static final Map<String, Integer> JUTSUS = new HashMap<String, Integer>() {{
        put("substitution", Jutsus.SUBSTITUTION);
        put("chibaku_tensei", Jutsus.CHIBAKU_TENSEI);
        put("fireball", Jutsus.FIREBALL);
        put("water_bullet", Jutsus.WATER_BULLET);
        //put("eyes", Jutsus.EYES); //TODO not implemented
        put("earth_release", Jutsus.EARTH_RELEASE);
        put("sekc", Jutsus.SEKC);
        put("chibi_shadow_clone", Jutsus.CHIBI_SHADOW_CLONE);
        put("shadow_clone", Jutsus.REGULAR_SHADOW_CLONE);
        put("multi_shadow_clone", Jutsus.MULTI_SHADOW_CLONE);
    }};

    @SideOnly(Side.CLIENT)
    public static String translateToKeyCombo(int keyCombo) {
        return String.valueOf(keyCombo)
                .replaceAll(KEY_ONE, Keyboard.getKeyName(NarutoKeyHandler.keys[0].getKeyCode()))
                .replaceAll(KEY_TWO, Keyboard.getKeyName(NarutoKeyHandler.keys[1].getKeyCode()))
                .replaceAll(KEY_THREE, Keyboard.getKeyName(NarutoKeyHandler.keys[2].getKeyCode()));
    }

    public static Map<String, Integer> getRegisteredJutsuCombinations() {
        return JUTSUS;
    }
}
