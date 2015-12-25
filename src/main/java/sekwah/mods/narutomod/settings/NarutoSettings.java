package sekwah.mods.narutomod.settings;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import sekwah.mods.narutomod.client.gui.EnumNarutoOptions;
import sekwah.mods.narutomod.client.gui.GuiChakraAndStaminaBar;

import java.util.UUID;

public class NarutoSettings {


    public static Configuration config = null;


    // need to readd when possible, if it becomes possible wall walk and stuf should become also possible :)
    public static boolean experimentalFirstPerson = false;

    public static int experimentalFirstPersonMode = 0;

    /**
     * Switch between using the new arms and sticking to the normal. Possibly even make the lower arms slightly smaller
     * so they can bend back like captain sparkles old videos(e.g.  but plan to change it back to yours once its more efficient :3
     */
    public static boolean betterArms = true;

    public static int settingsPage = 1;

    public static int chakraBarOffsetX = 2;

    public static int chakraBarOffsetY = 2;

    public static int chakraGUICorner = 1;

    public static int jutsuDelay = 10; // Delay time in ticks, from when the last button is presset till the jutsu casts

    public static int usageReportMode = 0;

    public static String usageUUID = null;

    // TODO change chakra to hue rather than individual sliders :)
    public static float chakraRed = 20;

    public static float chakraGreen = 179;

    public static float chakraBlue = 255;

    public static int chakraBarDesign = 1;

    public static void changeSetting() {
    }

    /**
     * public static void changeSettingBoolean(EnumNarutoOptions setting, boolean bool) {
     * if(setting == EnumNarutoOptions.FIRSTPERSON){
     * experimentalFirstPerson = bool;
     * config.get(Configuration.CATEGORY_GENERAL, "experimentalFirstPersonEnabled", false).set(bool);
     * }
     * }
     */

    public static void changeSettingInt(EnumNarutoOptions setting, int value) {
        if (setting == EnumNarutoOptions.CHAKRA_BAR_CORNER) {
            chakraGUICorner = value;
            config.get(Configuration.CATEGORY_GENERAL, "chakraGUICorner", 1).set(value);
        }
        else if (setting == EnumNarutoOptions.CHAKRA_BAR_DESIGN) {
            chakraBarDesign = value;
            config.get(Configuration.CATEGORY_GENERAL, "chakraBarDesign", 1).set(value);
        }
        else if (setting == EnumNarutoOptions.FIRSTPERSON) {
            experimentalFirstPersonMode = value;
            if (value == 0) {
                experimentalFirstPerson = true;
            } else if (value == 1) {
                experimentalFirstPerson = false;
            } else if (value == 2) {
                experimentalFirstPerson = false;
            }
            config.get(Configuration.CATEGORY_GENERAL, "experimentalFirstPersonMode", 0).set(value);
        }
        NarutoSettings.saveConfig();
    }

    public static void changeSettingFromSlider(EnumNarutoOptions setting, float sliderValue) {
        if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETX) {
            chakraBarOffsetX = (int) (sliderValue * 300);
            config.get(Configuration.CATEGORY_GENERAL, "chakraBarOffsetX", 2).set(chakraBarOffsetX);
        } else if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETY) {
            chakraBarOffsetY = (int) (sliderValue * 300);
            config.get(Configuration.CATEGORY_GENERAL, "chakraBarOffsetY", 2).set(chakraBarOffsetX);
        } else if (setting == EnumNarutoOptions.JUTSU_DELAY) {
            jutsuDelay = (int) (sliderValue * 20) + 5;
            config.get(Configuration.CATEGORY_GENERAL, "jutsuDelay", 10).set(jutsuDelay);
        }
        else if( setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETY){
            // sliderValue is from 0 to 1
        }
        else if( setting == EnumNarutoOptions.CHAKRA_RED){
            chakraRed = (int) (sliderValue * 255);
        }
        else if( setting == EnumNarutoOptions.CHAKRA_GREEN){
            chakraGreen = (int) (sliderValue * 255);
        }
        else if( setting == EnumNarutoOptions.CHAKRA_BLUE){
            chakraBlue = (int) (sliderValue * 255);
        }


        NarutoSettings.saveConfig();
    }


    public static String getSettingValueString(EnumNarutoOptions setting) {
        if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETX) {
            return Integer.toString(chakraBarOffsetX);
        } else if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETY) {
            return Integer.toString(chakraBarOffsetY);
        } else if (setting == EnumNarutoOptions.JUTSU_DELAY) {
            return Integer.toString(jutsuDelay);
        } else if (setting == EnumNarutoOptions.CHAKRA_RED) {
            return Float.toString(chakraRed);
        } else if (setting == EnumNarutoOptions.CHAKRA_GREEN) {
            return Float.toString(chakraGreen);
        } else if (setting == EnumNarutoOptions.CHAKRA_BLUE) {
            return Float.toString(chakraBlue);
        }
        return null;
    }

    public static float returnSliderValue(EnumNarutoOptions setting) {
        if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETX) {
            return (float) (chakraBarOffsetX) / 300F;
        } else if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETY) {
            return (float) (chakraBarOffsetY) / 300F;
        } else if (setting == EnumNarutoOptions.JUTSU_DELAY) {
            return (float) (jutsuDelay - 5) / 20F;
        } else if (setting == EnumNarutoOptions.CHAKRA_RED) {
            return chakraRed / 255F;
        } else if (setting == EnumNarutoOptions.CHAKRA_GREEN) {
            return chakraGreen / 255F;
        } else if (setting == EnumNarutoOptions.CHAKRA_BLUE) {
            return chakraBlue / 255F;
        }

        return 1F;
    }

    public static void saveConfig() {
        config.save();
    }


    public static void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());

        config.load();

        Property configExperimentalFirstPersonMode = config.get(Configuration.CATEGORY_GENERAL, "experimentalFirstPersonMode", 0);
        experimentalFirstPersonMode = configExperimentalFirstPersonMode.getInt(0);
        configExperimentalFirstPersonMode.comment = "This sets the first person mode. 0 = Enabled, 1 = Disabled, 2 = Jutsu Toggle(on when casting justsus)";

        if (experimentalFirstPersonMode == 0) {
            experimentalFirstPerson = true;
        } else if (experimentalFirstPersonMode == 1) {
            experimentalFirstPerson = false;
        } else if (experimentalFirstPersonMode == 2) {
            experimentalFirstPerson = false;
        }

        Property configExperimentalFirstPerson = config.get(Configuration.CATEGORY_GENERAL, "experimentalFirstPersonEnabled", true);
         experimentalFirstPerson = configExperimentalFirstPerson.getBoolean(true);
         configExperimentalFirstPerson.comment = "If this is set to true then you can see your arms and legs in first person.";

        chakraBarOffsetX = config.get(Configuration.CATEGORY_GENERAL, "chakraBarOffsetX", 2).getInt(2);

        chakraBarOffsetY = config.get(Configuration.CATEGORY_GENERAL, "chakraBarOffsetY", 2).getInt(2);

        chakraGUICorner = config.get(Configuration.CATEGORY_GENERAL, "chakraGUICorner", 1).getInt(1);

        Property configJutsuDelay = config.get(Configuration.CATEGORY_GENERAL, "jutsuDelay", 10);
        jutsuDelay = configJutsuDelay.getInt(10);
        configJutsuDelay.comment = "Sets the time in ticks for the jutsu delay, minimum is 5(lightining refrexes(ninja)) and max is 25(slowpoke) default 10";
        if(jutsuDelay > 25){
            jutsuDelay = 25;
        }
        else if(jutsuDelay < 0){
            jutsuDelay = 0;
        }

        Property configUsageReportMod = config.get(Configuration.CATEGORY_GENERAL, "usageReportMod", 0);
        usageReportMode = configUsageReportMod.getInt(0);
        configUsageReportMod.comment = "This sets the usage report mode 0 = Enabled, 1 = No data sent but says its " +
                "online, 2 = Disabled (please leave this on 1 at least just for me :3 the data is anonymous and it " +
                "lets me see how many people are playing. If you dont like data being sent about your pc such as " +
                "operaing system and cores then please set it to 1 so we have a usage count) also may make some fun " +
                "stats such as how many fireballs have been shot and other things.";


        Property configUsageUUID = config.get(Configuration.CATEGORY_GENERAL, "usageReportUUID", UUID.randomUUID().toString());
        usageUUID = configUsageUUID.getString();
        configUsageUUID.comment = "Used to stop duplicates of users and used to make data completely anonymous. Specific data " +
                "is kept on the database for a max of 30 mins and a min of 15 mins after logout.";

        //int randomBlockID = config.getBlock("RandomBlock", 200).getInt();

        //int randomItemID = config.getItem("RandomItem", 20000).getInt();

        // Since this flag is a boolean, we can read it into the variable directly from the config.
        //someConfigFlag = config.get(Configuration.CATEGORY_GENERAL, "SomeConfigFlag", false).getBoolean(false);

        //Notice there is nothing that gets the value of this property so the expression results in a Property object.
        //Property someProperty = config.get(Configuration.CATEGORY_GENERAL, "SomeConfigString", "nothing");

        // Here we add a comment to our new property.
        //someProperty.comment = "This value can be read as a string!";

        //String someConfigString = someProperty.value;
        // this could also be:
        // int someInt = someProperty.getInt();
        // boolean someBoolean = someProperty.getBoolean(true);

        Property configBetterArms = config.get(Configuration.CATEGORY_GENERAL, "betterArms", true);
        betterArms = configBetterArms.getBoolean();
        // (or possibly changed to be like captain sparkles old videos if changed)
        configBetterArms.comment = "Can't be changed in game as the corners would be stuck at angles. True = uses new arms like general blender models. False = corners like the old versions of the mod";

        chakraBarDesign = config.get(Configuration.CATEGORY_GENERAL, "chakraBarDesign", 1).getInt(1);
        // Stop out of bounds exceptions.
        if(event.getSide().isClient()){
            if(chakraBarDesign > GuiChakraAndStaminaBar.getDesignCount()){
                chakraBarDesign = GuiChakraAndStaminaBar.getDesignCount();
            }
        }

        config.save();

    }

    public static float chakraRedFloat() {
        return chakraRed / 255F;
    }

    public static float chakraGreenFloat() {
        return chakraGreen / 255F;
    }

    public static float chakraBlueFloat() {
        return chakraBlue / 255F;
    }
}
