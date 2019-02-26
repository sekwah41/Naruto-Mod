package sekwah.mods.narutomod.settings;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import sekwah.mods.narutomod.client.gui.GuiChakraAndStaminaBar;

public class NarutoSettings {


    public static Configuration config = null;


    // need to readd when possible, if it becomes possible wall walk and stuf should become also possible :)
    public static boolean experimentalFirstPerson = false;

    public static int experimentalFirstPersonMode = 0;

    /**
     * Switch between using the new arms and sticking to the normal. Possibly even make the lower arms slightly smaller
     * so they can bend back like captain sparkles old videos(e.g.  but plan to change it back to yours once its more efficient :3
     */
    public static boolean betterArms = false;

    public static int settingsPage = 1;

    public static int chakraBarOffsetX = 2;

    public static int chakraBarOffsetY = 2;

    public static int chakraGUICorner = 1;

    public static int jutsuDelay = 10; // Delay time in ticks, from when the last button is presset till the jutsu casts

    public static int usageReportMode = 0;

    //public static String usageUUID = null;

    public static boolean rainbowChakra = false;

    public static int chakraRed = 20;

    public static int chakraGreen = 179;

    public static int chakraBlue = 255;

    // Create brightness slider, use linear equation to darken and lighten, below 50 is making it a fraction
    // above 50 is reversing it and making a fraction so its pulled up instead.
    public static int chakraHue = 187;

    public static int chakraBrightness = 50;

    public static int chakraBarDesign = 1;

    public static boolean dodgesEnabled = true;

    public static boolean nonDestructiveMode = true;

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
        //NarutoSettings.saveConfig();
    }

    public static void booleanChange() {
        config.get(Configuration.CATEGORY_GENERAL, "betterArms", betterArms).set(betterArms);
        config.get(Configuration.CATEGORY_GENERAL, "dodgesEnabled", dodgesEnabled).set(dodgesEnabled);
    }

    public static void changeSettingFromSlider(EnumNarutoOptions setting, float sliderValue) {
        if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETX) {
            chakraBarOffsetX = (int) (sliderValue * 300);
            config.get(Configuration.CATEGORY_GENERAL, "chakraBarOffsetX", 2).set(chakraBarOffsetX);
        } else if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETY) {
            chakraBarOffsetY = (int) (sliderValue * 300);
            config.get(Configuration.CATEGORY_GENERAL, "chakraBarOffsetY", 2).set(chakraBarOffsetX);
        } else if (setting == EnumNarutoOptions.JUTSU_DELAY) {
            jutsuDelay = (int) (sliderValue * 25) + 5;
            config.get(Configuration.CATEGORY_GENERAL, "jutsuDelay", 10).set(jutsuDelay);
        }
        // TODO once hue slider complete remove the red blue and green and have it calculate the values whenever hue changed or loaded.
        else if( setting == EnumNarutoOptions.CHAKRA_HUE){
            chakraHue = (int) (sliderValue * 360); // from red to red
            recalculateHue();
            config.get(Configuration.CATEGORY_GENERAL, "chakraColourHue", 187).set(chakraHue);
            // Save hue value
        }
        else if( setting == EnumNarutoOptions.CHAKRA_BRIGHTNESS){
            chakraBrightness = (int) (sliderValue * 100); // from red to red
            recalculateHue();
            config.get(Configuration.CATEGORY_GENERAL, "chakraColourBrightness", 50).set(chakraBrightness);
            // Save hue value
        }
        // maybe save only when closing the menu... or add a save button.
        //NarutoSettings.saveConfig();
    }

    public static void recalculateHue() {
        // check
        // http://stackoverflow.com/questions/25713206/calculate-hue-rotation-from-color-a-to-color-b
        double radiansHue = Math.toRadians(chakraHue);
        chakraRed = (int) (Math.sqrt(Math.cos(radiansHue)+1/2) * 255);
        chakraGreen = (int) (Math.sqrt(Math.cos(radiansHue-(Math.PI+1)/2)+1/2) * 255);
        chakraBlue = (int) (Math.sqrt(Math.cos(radiansHue+(Math.PI+1)/2)+1/2) * 255);
        if(chakraBrightness > 50){
            float multiValue = (float) (chakraBrightness - 50) / 50f;
            chakraRed = (int) ((255 - chakraRed) * multiValue + chakraRed);
            chakraGreen = (int) ((255 - chakraGreen) * multiValue + chakraGreen);
            chakraBlue = (int) ((255 - chakraBlue) * multiValue + chakraBlue);
        }
        else if(chakraBrightness < 50){
            float multiValue = (float) chakraBrightness / 50f;
            chakraRed *= multiValue;
            chakraGreen *= multiValue;
            chakraBlue *= multiValue;
        }

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
        } else if (setting == EnumNarutoOptions.CHAKRA_HUE) {
            return Float.toString(chakraHue);
        } else if (setting == EnumNarutoOptions.CHAKRA_BRIGHTNESS) {
            return Float.toString(chakraBrightness);
        }
        return null;
    }

    public static float returnSliderValue(EnumNarutoOptions setting) {
        if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETX) {
            return (float) (chakraBarOffsetX) / 300F;
        } else if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETY) {
            return (float) (chakraBarOffsetY) / 300F;
        } else if (setting == EnumNarutoOptions.JUTSU_DELAY) {
            return (float) (jutsuDelay - 5) / 25F;
        } else if (setting == EnumNarutoOptions.CHAKRA_RED) {
            return chakraRed / 255F;
        } else if (setting == EnumNarutoOptions.CHAKRA_GREEN) {
            return chakraGreen / 255F;
        } else if (setting == EnumNarutoOptions.CHAKRA_BLUE) {
            return chakraBlue / 255F;
        } else if (setting == EnumNarutoOptions.CHAKRA_HUE) {
            return chakraHue / 360F;
        } else if (setting == EnumNarutoOptions.CHAKRA_BRIGHTNESS) {
            return chakraBrightness / 100F;
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

         Property configNonDestructiveMode = config.get(Configuration.CATEGORY_GENERAL, "nonDestructiveMode", false);
        nonDestructiveMode = configNonDestructiveMode.getBoolean(true);
        configNonDestructiveMode.comment = "Server side setting: If true jutsus will not damage the world e.g. fire or block damage.";

        chakraBarOffsetX = config.get(Configuration.CATEGORY_GENERAL, "chakraBarOffsetX", 2).getInt(2);

        chakraBarOffsetY = config.get(Configuration.CATEGORY_GENERAL, "chakraBarOffsetY", 2).getInt(2);

        chakraGUICorner = config.get(Configuration.CATEGORY_GENERAL, "chakraGUICorner", 1).getInt(1);

        Property configJutsuDelay = config.get(Configuration.CATEGORY_GENERAL, "jutsuDelay", 10);
        jutsuDelay = configJutsuDelay.getInt(10);

        configJutsuDelay.comment = "Sets the time in ticks for the jutsu delay, minimum is 5(lightining refrexes(ninja)) and max is 25(slowpoke) default 10";

        if(jutsuDelay > 30){
            jutsuDelay = 30;
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


        /*Property configUsageUUID = config.get(Configuration.CATEGORY_GENERAL, "usageReportUUID", UUID.randomUUID().toString());
        usageUUID = configUsageUUID.getString();
        configUsageUUID.comment = "Used to stop duplicates of users and used to make data completely anonymous. Specific data " +
                "is kept on the database for a max of 30 mins and a min of 15 mins after logout.";*/

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

        Property configBetterArms = config.get(Configuration.CATEGORY_GENERAL, "betterArms", false);
        betterArms = configBetterArms.getBoolean();

        Property configDodges = config.get(Configuration.CATEGORY_GENERAL, "dodgesEnabled", true);
        dodgesEnabled = configDodges.getBoolean();

        // (or possibly changed to be like captain sparkles old videos if changed)
        configBetterArms.comment = "Can't be changed in game as the corners would be stuck at angles. True = uses new arms like general blender models. False = corners like the old versions of the mod";

        chakraBarDesign = config.get(Configuration.CATEGORY_GENERAL, "chakraBarDesign", 1).getInt(1);
        // Stop out of bounds exceptions.
        if(event.getSide().isClient()){
            if(chakraBarDesign > GuiChakraAndStaminaBar.getDesignCount()){
                chakraBarDesign = GuiChakraAndStaminaBar.getDesignCount();
            }
        }

        chakraHue = config.get(Configuration.CATEGORY_GENERAL, "chakraColourHue", 187).getInt(187);
        chakraBrightness = config.get(Configuration.CATEGORY_GENERAL, "chakraColourBrightness", 50).getInt(50);
        recalculateHue();

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
