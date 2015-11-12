package com.sekwah.mods.narutomod.settings;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import com.sekwah.mods.narutomod.client.gui.EnumNarutoOptions;

public class NarutoSettings {


    public static Configuration config = null;

    public static boolean experimentalFirstPerson = false;

    public static int experimentalFirstPersonMode = 0;

    public static int settingsPage = 1;

    public static int chakraBarOffsetX = 2;

    public static int chakraBarOffsetY = 2;

    public static int chakraGUICorner = 1;

    public static int jutsuDelay = 10; // Delay time in ticks, from when the last button is presset till the jutsu casts

    public static int usageReportMod = 0;

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
        if (setting == EnumNarutoOptions.FIRSTPERSON) {
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


        NarutoSettings.saveConfig();
    }


    public static String getSettingValueString(EnumNarutoOptions setting) {
        if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETX) {
            return Integer.toString(chakraBarOffsetX);
        } else if (setting == EnumNarutoOptions.CHAKRA_BAR_OFFSETY) {
            return Integer.toString(chakraBarOffsetY);
        } else if (setting == EnumNarutoOptions.JUTSU_DELAY) {
            return Integer.toString(jutsuDelay);
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

        chakraGUICorner = config.get(Configuration.CATEGORY_GENERAL, "chakraGUICorner", 1).getInt(2);

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
        usageReportMod = configUsageReportMod.getInt(0);
        configUsageReportMod.comment = "This sets the usage report mode 0 = Enabled, 1 = No data sent but says its " +
                "online, 2 = Disabled (please leave this on 1 at least just for me :3 the data is anonymous and it " +
                "lets me see how many people are playing. If you dont like data being sent about your pc such as " +
                "operaing system and cores then please set it to 1 so we have a usage count)";

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


        config.save();

    }

}
