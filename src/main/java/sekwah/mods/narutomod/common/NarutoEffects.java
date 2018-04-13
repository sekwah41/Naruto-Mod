package sekwah.mods.narutomod.common;

import net.minecraft.potion.Potion;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.common.potioneffects.PotionChakraRestore;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Alastair on 02/02/2015.
 */
public class NarutoEffects {

    public static Potion chakraRestore;

    private static int potionStart = 0;

    // TODO add some way of registering them then adding them
    private static int potionCountToAdd = 1;


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

                    potionStart = potionTypes.length;
                    int newLength = potionTypes.length + potionCountToAdd;
                    NarutoMod.logger.info("Extending length of potion array from " +
                            potionTypes.length + " to " + newLength);
                    final Potion[] newPotionTypes = new Potion[newLength];
                    // Gets all current potions and makes a larger array with the current potions in them, should not interfear with other mods
                    //  unless there are clashing potion id's
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            }
            catch (Exception e) {
                // Replace with the logger and do the error stuff
                NarutoMod.logger.error("Severe error, please report this:");
                NarutoMod.logger.error(e);
            }
        }
    }

    /**
     * Potion effects and stuff
     */
    public static void addEffects(){
        int currentID = potionStart;
        NarutoMod.logger.info("Chakra restore ID: " + currentID);
        chakraRestore = (new PotionChakraRestore(currentID++, false, (new Color(255, 129, 26)).getRGB())).setIconIndex(2, 2).setPotionName("potion.chakraRestore");

    }

}
