package sekwah.mods.narutomod.jutsu;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import sekwah.mods.narutomod.client.NarutoKeyHandler;

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

    @SideOnly(Side.CLIENT)
    public static String translateToKeyCombo(String input) {
        input = input.replaceAll(KEY_ONE, Keyboard.getKeyName(NarutoKeyHandler.keys[0].getKeyCode()));
        input = input.replaceAll(KEY_TWO, Keyboard.getKeyName(NarutoKeyHandler.keys[1].getKeyCode()));
        input = input.replaceAll(KEY_THREE, Keyboard.getKeyName(NarutoKeyHandler.keys[2].getKeyCode()));
        return input;
    }
}
