package sekwah.mods.narutomod.generic;

import net.minecraft.potion.Potion;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.generic.potioneffects.PotionChakraRestore;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Alastair on 02/02/2015.
 */
public class NarutoEffects {

    public static Potion chakraRestore;

    public static void editBasePotion(){
        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[])f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    // Gets all current potions and makes a larger array with the current potions in them, should not interfear with other mods
                    //  unless there are clashing potion id's
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            }
            catch (Exception e) {
                // Replace with the logger and do the error stuff
                NarutoMod.LOGGER.error("Severe error, please report this:");
                NarutoMod.LOGGER.error(e);
            }
        }
    }

    /**
     * Potion effects and stuff
     */
    public static void addEffects(){

        chakraRestore = (new PotionChakraRestore(32, false, (new Color(20, 180, 255)).getRGB())).setIconIndex(2, 2).setPotionName("potion.chakraRestore");
    }

}
