package sekwah.mods.narutomod.assets;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import sekwah.mods.narutomod.NarutoMod;

import java.io.File;

public class JutsuData {

    private static final String GENERAL = Configuration.CATEGORY_GENERAL;
    private static final String JUTSUS = "jutsus";

    public static int substitutionCost = 9999999;
    public static boolean substitutionEnabled = false;

    public static int chibakuTenseiCost = 9999999;
    public static boolean chibakuTenseiEnabled = false;

    public static int chidoriCost = 9999999;
    public static boolean chidoriEnabled = false;

    public static int fireballCost = 9999999;
    public static boolean fireballEnabled = false;

    public static int waterBulletCost = 9999999;
    public static boolean waterBulletEnabled = false;

    public static int wallCost = 9999999;
    public static boolean wallEnabled = false;

    public static int shadowCloneCost = 9999999;
    public static boolean shadowCloneEnabled = false;

    public static int multiShadowCloneCost = 9999999;
    public static boolean multiShadowCloneEnabled = false;

    public static int chibiShadowCloneCost = 9999999;
    public static boolean chibiShadowCloneEnabled = false;

    public static int sekcCost = 9999999;
    public static boolean sekcEnabled = false;

}
