package sekwah.mods.narutomod.client.gui.Jutsus;

import net.minecraft.item.ItemStack;
import sekwah.mods.narutomod.common.items.NarutoItems;

import java.util.ArrayList;
import java.util.List;

public class JutsuList {
    /**
     * Is the smallest column used to display a Jutsus on the GUI.
     */
    public static int minDisplayColumn;

    /**
     * Is the smallest row used to display a Jutsus on the GUI.
     */
    public static int minDisplayRow;

    /**
     * Is the biggest column used to display a Jutsus on the GUI.
     */
    public static int maxDisplayColumn;

    /**
     * Is the biggest row used to display a Jutsus on the GUI.
     */
    public static int maxDisplayRow;

    /**
     * Holds a list of all registered Jutsuss.
     */
    public static List JutsusList = new ArrayList();

    public static Jutsus chakraControl = (new Jutsus("Jutsu.Chakra_Control", "Chakra_Control", 0, 0, new ItemStack(NarutoItems.narutoIcons, 1, 0), null)).setIndependent().registerJutsus();

    public static Jutsus fireBallJutsu = (new Jutsus("Jutsu.Shadow_Clone_Jutsu", "Shadow_Clone_Jutsu", 2, 1, new ItemStack(NarutoItems.narutoIcons, 1, 1), chakraControl)).setIndependent().registerJutsus();

    public static Jutsus shadowCloneJutsu = (new Jutsus("Jutsu.Fire_Ball_Jutsu", "Fire_Ball_Jutsu", 2, -1, new ItemStack(NarutoItems.narutoIcons, 1, 2), chakraControl)).setIndependent().registerJutsus();

    public static Jutsus waterWalk = (new Jutsus("Jutsu.Water_Walk", "Water_Walk", -2, 1, new ItemStack(NarutoItems.narutoIcons, 1, 3), chakraControl)).setIndependent().registerJutsus();

    public static Jutsus chakraDash = (new Jutsus("Jutsu.Chakra_Dash", "Chakra_Dash", -2, -1, new ItemStack(NarutoItems.narutoIcons, 1, 5), chakraControl)).setIndependent().registerJutsus();

    public static Jutsus substitution = (new Jutsus("Jutsu.Substitution", "Substitution", 1, -2, new ItemStack(NarutoItems.narutoIcons, 1, 6), chakraControl)).setIndependent().registerJutsus();

    public static Jutsus waterBullet = (new Jutsus("Jutsu.Water_Bullet", "Water_Bullet", 1, 2, new ItemStack(NarutoItems.narutoIcons, 1, 7), chakraControl)).setIndependent().registerJutsus();

    public static Jutsus earthWall = (new Jutsus("Jutsu.Earth_Wall", "Earth_Wall", 3, 0, new ItemStack(NarutoItems.narutoIcons, 1, 8), chakraControl)).setIndependent().registerJutsus();

    /** Is the 'getting wood' Jutsus. *//**
     public static Jutsus mineWood = (new Jutsus(1, "mineWood", 2, 1, Block.wood, openInventory)).registerJutsus();

     /** Is the 'benchmarking' Jutsus. *//**
     public static Jutsus buildWorkBench = (new Jutsus(2, "buildWorkBench", 4, -1, Block.workbench, mineWood)).registerJutsus();

     /** Is the 'time to mine' Jutsus. *//**
     public static Jutsus buildPickaxe = (new Jutsus(3, "buildPickaxe", 4, 2, Item.pickaxeWood, buildWorkBench)).registerJutsus();

     /** Is the 'hot topic' Jutsus. *//**
     public static Jutsus buildFurnace = (new Jutsus(4, "buildFurnace", 3, 4, Block.furnaceIdle, buildPickaxe)).registerJutsus();

     /** Is the 'acquire hardware' Jutsus. *//**
     public static Jutsus acquireIron = (new Jutsus(5, "acquireIron", 1, 4, Item.ingotIron, buildFurnace)).registerJutsus();

     /** Is the 'time to farm' Jutsus. *//**
     public static Jutsus buildHoe = (new Jutsus(6, "buildHoe", 2, -3, Item.hoeWood, buildWorkBench)).registerJutsus();

     /** Is the 'bake bread' Jutsus. *//**
     public static Jutsus makeBread = (new Jutsus(7, "makeBread", -1, -3, Item.bread, buildHoe)).registerJutsus();

     /** Is the 'the lie' Jutsus. *//**
     public static Jutsus bakeCake = (new Jutsus(8, "bakeCake", 0, -5, Item.cake, buildHoe)).registerJutsus();

     /** Is the 'getting a upgrade' Jutsus. *//**
     public static Jutsus buildBetterPickaxe = (new Jutsus(9, "buildBetterPickaxe", 6, 2, Item.pickaxeStone, buildPickaxe)).registerJutsus();

     /** Is the 'delicious fish' Jutsus. *//**
     public static Jutsus cookFish = (new Jutsus(10, "cookFish", 2, 6, Item.fishCooked, buildFurnace)).registerJutsus();

     /** Is the 'on a rail' Jutsus *//**
     public static Jutsus onARail = (new Jutsus(11, "onARail", 2, 3, Block.rail, acquireIron)).setSpecial().registerJutsus();

     /** Is the 'time to strike' Jutsus. *//**
     public static Jutsus buildSword = (new Jutsus(12, "buildSword", 6, -1, Item.swordWood, buildWorkBench)).registerJutsus();

     /** Is the 'monster hunter' Jutsus. *//**
     public static Jutsus killEnemy = (new Jutsus(13, "killEnemy", 8, -1, Item.bone, buildSword)).registerJutsus();

     /** is the 'cow tipper' Jutsus. *//**
     public static Jutsus killCow = (new Jutsus(14, "killCow", 7, -3, Item.leather, buildSword)).registerJutsus();

     /** Is the 'when pig fly' Jutsus. *//**
     public static Jutsus flyPig = (new Jutsus(15, "flyPig", 8, -4, Item.saddle, killCow)).setSpecial().registerJutsus();

     /** The Jutsus for killing a Skeleton from 50 meters aways. *//**
     public static Jutsus snipeSkeleton = (new Jutsus(16, "snipeSkeleton", 7, 0, Item.bow, killEnemy)).setSpecial().registerJutsus();

     /** Is the 'DIAMONDS!' Jutsus *//**
     public static Jutsus diamonds = (new Jutsus(17, "diamonds", -1, 5, Item.diamond, acquireIron)).registerJutsus();

     /** Is the 'We Need to Go Deeper' Jutsus *//**
     public static Jutsus portal = (new Jutsus(18, "portal", -1, 7, Block.obsidian, diamonds)).registerJutsus();

     /** Is the 'Return to Sender' Jutsus *//**
     public static Jutsus ghast = (new Jutsus(19, "ghast", -4, 8, Item.ghastTear, portal)).setSpecial().registerJutsus();

     /** Is the 'Into Fire' Jutsus *//**
     public static Jutsus blazeRod = (new Jutsus(20, "blazeRod", 0, 9, Item.blazeRod, portal)).registerJutsus();

     /** Is the 'Local Brewery' Jutsus *//**
     public static Jutsus potion = (new Jutsus(21, "potion", 2, 8, Item.potion, blazeRod)).registerJutsus();

     /** Is the 'The End?' Jutsus *//**
     public static Jutsus theEnd = (new Jutsus(22, "theEnd", 3, 10, Item.eyeOfEnder, blazeRod)).setSpecial().registerJutsus();

     /** Is the 'The End.' Jutsus *//**
     public static Jutsus theEnd2 = (new Jutsus(23, "theEnd2", 4, 13, Block.dragonEgg, theEnd)).setSpecial().registerJutsus();

     /** Is the 'Enchanter' Jutsus *//**
     public static Jutsus enchantments = (new Jutsus(24, "enchantments", -4, 4, Block.enchantmentTable, diamonds)).registerJutsus();
     public static Jutsus overkill = (new Jutsus(25, "overkill", -4, 1, Item.swordDiamond, enchantments)).setSpecial().registerJutsus();

     /** Is the 'Librarian' Jutsus */
    /**
     * public static Jutsus bookcase = (new Jutsus(26, "bookcase", -3, 6, Block.bookShelf, enchantments)).registerJutsus();
     * <p/>
     * /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {
    }
}