package sekwah.mods.narutomod.assets;

import net.minecraftforge.common.config.Configuration;
import sekwah.mods.narutomod.NarutoMod;

import java.io.File;

public class JutsuDataServer {

    private static final String GENERAL = Configuration.CATEGORY_GENERAL;
    private static final String JUTSUS = "jutsus";
    public static int substitutionCost;
    public static boolean substitutionEnabled;

    public static int chibakuTenseiCost;
    public static boolean chibakuTenseiEnabled;

    public static int chidoriCost;
    public static boolean chidoriEnabled;

    public static int fireballCost;
    public static boolean fireballEnabled;

    public static int waterBulletCost;
    public static boolean waterBulletEnabled;

    public static int wallCost;
    public static boolean wallEnabled;

    public static int shadowCloneCost;
    public static boolean shadowCloneEnabled;

    public static int multiShadowCloneCost;
    public static boolean multiShadowCloneEnabled;

    public static int chibiShadowCloneCost;
    public static boolean chibiShadowCloneEnabled;

    public static int sekcCost;
    public static boolean sekcEnabled;
    //TODO implement
    //public static boolean sprintingEnabled;
    //public static boolean leapingEnabled;
    //public static float leapAmount;
    //public static float leapUp;
    //public static boolean dodgeEnabled;
    //public static float dodgeAmount;

    /**
     * the configuration object
     */
    private static Configuration config;

    public static void init(File configFile) {
        config = new Configuration(configFile, NarutoMod.version);
        config.load();
        refreshConfig();
        //FMLCommonHandler.instance().bus().register(JutsuData.class);
    }

    /**
     * read all config values here
     */
    public static void refreshConfig() {
        substitutionEnabled = config.getBoolean("substitutionEnabled", JUTSUS, true, "whether the substitution jutsu is enabled");
        substitutionCost = config.getInt("substitutionCost", JUTSUS, 15, 0, 500, "how much chakra the substitution jutsu consumes");
        //
        chibakuTenseiEnabled = config.getBoolean("chibakuTenseiEnabled", JUTSUS, true, "whether the chibaku tensei jutsu is enabled");
        chibakuTenseiCost = config.getInt("chibakuTenseiCost", JUTSUS, 100, 0, 500, "how much chakra the chibaku tensei jutsu consumes");
        //
        chidoriEnabled = config.getBoolean("chidoriEnabled", JUTSUS, true, "whether the chidori jutsu is enabled");
        chidoriCost = config.getInt("chidoriCost", JUTSUS, 100, 0, 500, "how much chakra the chidori jutsu consumes");
        //
        fireballEnabled = config.getBoolean("fireballEnabled", JUTSUS, true, "whether the fireball jutsu is enabled");
        fireballCost = config.getInt("fireballCost", JUTSUS, 30, 0, 500, "how much chakra the fireball jutsu consumes");
        //
        waterBulletEnabled = config.getBoolean("waterBulletEnabled", JUTSUS, true, "whether the water bullet jutsu is enabled");
        waterBulletCost = config.getInt("waterBulletCost", JUTSUS, 30, 0, 500, "how much chakra the water bullet jutsu consumes");
        //
        wallEnabled = config.getBoolean("earthWallEnabled", JUTSUS, true, "whether the earth wall jutsu is enabled");
        wallCost = config.getInt("earthWallCost", JUTSUS, 30, 0, 500, "how much chakra the earth wall jutsu consumes");
        //
        shadowCloneEnabled = config.getBoolean("shadowCloneEnabled", JUTSUS, true, "whether the shadow clone jutsu is enabled");
        shadowCloneCost = config.getInt("shadowCloneCost", JUTSUS, 20, 0, 500, "how much chakra the shadow clone jutsu consumes");
        //
        multiShadowCloneEnabled = config.getBoolean("multiShadowCloneEnabled", JUTSUS, true, "whether the multi shadow clone jutsu is enabled");
        multiShadowCloneCost = config.getInt("multiShadowCloneCost", JUTSUS, 60, 0, 500, "how much chakra the multi shadow clone jutsu consumes");
        //
        chibiShadowCloneEnabled = config.getBoolean("chibiShadowCloneEnabled", JUTSUS, true, "whether the chibi shadow clone jutsu is enabled");
        chibiShadowCloneCost = config.getInt("chibiShadowCloneCost", JUTSUS, 15, 0, 500, "how much chakra the chibi shadow clone jutsu consumes");
        //
        sekcEnabled = config.getBoolean("sekcEnabled", JUTSUS, true, "whether the sekc jutsu is enabled");
        sekcCost = config.getInt("sekcCost", JUTSUS, 3, 0, 500, "how much chakra the sekc jutsu consumes");
        //
        //sprintingEnabled = config.getBoolean("sprintingEnabled", GENERAL, true, "whether weeaboo sprinting is enabled");
        //leapingEnabled = config.getBoolean("leapingEnabled", GENERAL, true, "whether leaping is enabled");
        //leapAmount = config.getFloat("leapAmount", GENERAL, 2.5F, 0.0F, 100.0F, "the sideways movement factor for leaping");
        //leapUp = config.getFloat("leapUp", GENERAL, 0.7F, 0.0F, 100.0F, "the upwards movement factor for leaping");
        //dodgeEnabled = config.getBoolean("dodgeEnabled", GENERAL, true, "whether dodging is enabled");
        //dodgeAmount = config.getFloat("dodgeAmount", GENERAL, 1.7F, 0.0F, 100.0F, "the movement factor for dodging");
        if(config.hasChanged()) {
            config.save();
        }
    }

    /*@SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if(NarutoMod.modid.equals(event.modID)) {
            refreshConfig();
        }
    }*/
}
