package sekwah.mods.narutomod.jutsu;

public class Jutsus {

    //general functionality
    public static final int CHAKRA_RESTORE = 1;
    public static final int CHAKRA_DASH = 2;
    public static final int WATER_WALK = 3;
    public static final int RESET_FALL_DAMAGE = 4;

    //dodging/leaping
    public static final int LEAP_START = 401;
    public static final int DODGE_BACK_START = 402;
    public static final int DODGE_LEFT_START = 403;
    public static final int DODGE_RIGHT_START = 404;
    public static final int LEAP_STOP = 411;
    public static final int DODGE_BACK_STOP = 412;
    public static final int DODGE_LEFT_STOP = 413;
    public static final int DODGE_RIGHT_STOP = 414;

    //actual jutsu combinations
    public static final int SUBSTITUTION = 12; // cv
    public static final int CHIBAKU_TENSEI = 111; // ccc
    public static final int FIREBALL = 121; // cvc
    public static final int WATER_BULLET = 132; // cbv
    public static final int EYES = 311; // bcc //possibly the toggle for liams eyes
    public static final int EARTH_RELEASE = 312; //bcv
    public static final int SEKC = 333; // bbb
    public static final int CHIBI_SHADOW_CLONE = 1322; // cbvv
    public static final int REGULAR_SHADOW_CLONE = 1332; // cbbv
    public static final int MULTI_SHADOW_CLONE = 133231; // cbbvbc

    //animation start
    public static final int CHAKRA_INFUSE_START = 101;

    //animation stop
    public static final int CHAKRA_INFUSE_STOP = 110;
    public static final int FIREBALL_STOP = 1210;
    public static final int WATER_BULLET_STOP = 1320;
    public static final int EARTH_WALL_STOP = 3120;
    public static final int SEKC_STOP = 3330;

    //special because client-only
    public static final String RAINBOW_CHAKRA = "1333223"; // cbbbvvb
}
