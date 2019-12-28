package sekwah.mods.narutomod.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import sekwah.mods.narutomod.common.NarutoEffects;
import sekwah.mods.narutomod.common.block.NarutoBlocks;
import sekwah.mods.narutomod.common.items.creativetabs.CreativeTabNinja;
import sekwah.mods.narutomod.common.items.dispenserbehavior.DispenserBehaviorExplosiveKunai;
import sekwah.mods.narutomod.common.items.dispenserbehavior.DispenserBehaviorKunai;
import sekwah.mods.narutomod.common.items.dispenserbehavior.DispenserBehaviorSenbon;
import sekwah.mods.narutomod.common.items.dispenserbehavior.DispenserBehaviorShuriken;

import static sekwah.mods.narutomod.common.items.ItemArmourHeadbands.SUB_HEADBAND.*;

public class NarutoItems {

	public static final CreativeTabNinja ninjaWeapons = new CreativeTabNinja(CreativeTabs.getNextID(), "narutoWeapons");
	public static final CreativeTabNinja ninjaMaterials = new CreativeTabNinja(CreativeTabs.getNextID(), "narutoMaterials");
	public static final CreativeTabNinja ninjaArmour = new CreativeTabNinja(CreativeTabs.getNextID(), "narutoArmour");
	public static final CreativeTabNinja ninjaHeadband = new CreativeTabNinja(CreativeTabs.getNextID(), "narutoHeadband");
	public static Item Kunai;
	public static Item Shuriken;
	//public static Item FightingSpirit;
	public static Item LONELY_MARCH;
	public static Item CopperRyo;
	public static Item SilverRyo;
	public static Item GoldRyo;
	public static Item Katana;
	public static Item PaperBombItem;
	public static Item ExplosiveKunai;
	public static Item GUNBAI;
	public static Item SAMEHADA;
	public static Item RAMEN;
	public static Item SUMMON_SCROLL;
	public static Item NOODLES;
	public static Item MILITARY_PILLS;
	public static Item bokken;
	public static Item senbon;
	public static Item narutoIcons;
	public static Item gourd;
	public static Item backScroll;
	public static Item backBokken;
	public static Item backKatana;
	public static Item backStraps;
	public static Item kubikiribocho;
	public static Item headBand;
	public static Item fabric;
	public static Item flakJacket;
	public static Item shinobiChestplate;
	public static Item shinobiLeggings;
	public static Item redAnbuMask;
	public static Item narutoKid;

	public static Item JONIN_ARMOUR;
	public static Item NARUTO_SHIPPUDEN_ARMOUR;
	public static Item SASUKE_KID_ARMOUR;
	public static Item BORUTO_KID_ARMOUR;
	public static Item BORUTO_KAKASHI_ARMOUR;
	public static Item BORUTO_KID_MOVIE_ARMOUR;
	public static Item ANBU_ARMOUR;
	public static Item KID_NARUTO_ARMOUR;
	public static Item HAN_HAT;

	public static Item SAGE_ARMOUR;
	public static Item SASUKE_BORUTO;
	public static Item KAKASHI_NARUTO;
	public static Item TOBI_MASK;
	public static Item HOKAGE_CLOAK;
	public static Item AKATSUKI_CLOAK;

	// TODO Finish adding the code and complete tomorrow(tomorrow has passed, get yo lazy ass to work you bum)
	//  recipe needed
	public static Item armourPlate;
	public static EnumAction Throw = EnumHelper.addAction("Throw");
	// Armour materials
	private static ArmorMaterial FLACK_ARMOUR = EnumHelper.addArmorMaterial("FLAK", 35, new int[]{2, 7, 5, 2}, 5);
	private static ArmorMaterial JONIN_ARMOUR_MATERIAL = EnumHelper.addArmorMaterial("JONIN", 35, new int[]{2, 7, 5, 2}, 6);

	private static ArmorMaterial SHINOBI_ARMOUR = EnumHelper.addArmorMaterial("SHINOBI", 35, new int[]{2, 8, 8, 2}, 8);

	private static ArmorMaterial ANBU_ARMOUR_MAT = EnumHelper.addArmorMaterial("SHINOBI", 35, new int[]{2, 8, 5, 2}, 8);

	private static ArmorMaterial CHARACTER_CLOTHES = EnumHelper.addArmorMaterial("CHARACTER_CLOTHES", 35, new int[]{2, 14, 5, 2}, 6);

	private static ArmorMaterial FULL_CHARACTER_CLOTHES = EnumHelper.addArmorMaterial("FULL_CHARACTER_CLOTHES", 35, new int[]{2, 18, 5, 2}, 6);
    // Same as iron atm
	private static ArmorMaterial headbandArmour = EnumHelper.addArmorMaterial("HEADBAND", 35, new int[]{2, 8, 5, 2}, 10);
	// Weapon and tool materials
	private static ToolMaterial gunbaiTool = EnumHelper.addToolMaterial("GUNBAI", 0, 100, 2.0F, 0, 15);
	private static ToolMaterial samehadaTool = EnumHelper.addToolMaterial("SAMEHADA", 0, 3000, 8.0F, 4, 3);
	private static ToolMaterial kubikiriTool = EnumHelper.addToolMaterial("KUBOKIRIBOCHO", 0, 3000, 8.0F, 5, 3);
	private static ToolMaterial bokkenTool = EnumHelper.addToolMaterial("BOKKEN", 0, 59, 2.0F, -1.0F, 15);

	public static void addItems(Configuration config) {

		//FightingSpirit = (new ItemNinjaRecord("FightingSpirit")).setUnlocalizedName("record_FightingSpirit");
		//NejiTheme = (new ItemNinjaRecord("NejiTheme")).setUnlocalizedName("record_NejiTheme");

		LONELY_MARCH = (new ItemNinjaRecord("LonelyMarch")).setUnlocalizedName("record_lonelymarch");

		Kunai = (new ItemKunai()).setUnlocalizedName("Kunai");
		Shuriken = (new ItemShuriken()).setUnlocalizedName("Shuriken");

		CopperRyo = (new BaseItem()).setUnlocalizedName("copper_ryo").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(32);
		SilverRyo = (new BaseItem()).setUnlocalizedName("silver_ryo").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(32);
		GoldRyo = (new BaseItem()).setUnlocalizedName("gold_ryo").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(32);


		Katana = (new ItemNinjaSword(ToolMaterial.IRON)).setUnlocalizedName("Katana");

		bokken = (new ItemNinjaSword(bokkenTool)).setUnlocalizedName("Bokken");

		PaperBombItem = (new ItemBlockPlacer(NarutoBlocks.PaperBombBlock)).setUnlocalizedName("ExplosiveTag").setTextureName("ExplosiveTag").setCreativeTab(NarutoItems.ninjaWeapons).setMaxStackSize(16);

		ExplosiveKunai = (new ItemExplosiveKunai()).setUnlocalizedName("ExplosiveKunai").setTextureName("ExplosiveKunai");

		GUNBAI = (new ItemGunbai(gunbaiTool)).setUnlocalizedName("gunbai").setTextureName("gunbai");

		RAMEN = (new ItemFoodWithReturnItem(10, 0.5F, Items.bowl, false).setUnlocalizedName("Ramen").setTextureName("Ramen").setMaxStackSize(1));

		NOODLES = (new BaseItem()).setUnlocalizedName("Noodles").setTextureName("Noodles").setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(64);

		SUMMON_SCROLL = (new ItemBlockPlacer(NarutoBlocks.Summoning_CircleCenter)).setUnlocalizedName("Scroll").setTextureName("Scroll").setCreativeTab(ninjaArmour).setMaxStackSize(1);

		SAMEHADA = (new ItemSamehada(samehadaTool)).setFull3D().setUnlocalizedName("Samehada").setTextureName("Samehada");

		MILITARY_PILLS = (new ItemFoodBase(1, 0.1F, false)).setAlwaysEdible().setPotionEffect(NarutoEffects.chakraRestore.id, 30, 0, 1.0F).setUnlocalizedName("military_pills").setTextureName("military_pills").setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(64);

		senbon = (new ItemSenbon()).setUnlocalizedName("Senbon").setTextureName("Senbon");

		// icons for the menus

		narutoIcons = (new IconItem()).setUnlocalizedName("narutoIcons").setMaxStackSize(1);

		gourd = (new ItemArmourGourd(ArmorMaterial.IRON, 4, 1)).setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("gourd");

		backScroll = (new ItemArmourScroll(ArmorMaterial.IRON, 4, 1)).setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("backScroll");

		backBokken = (new ItemArmourBokken(ArmorMaterial.IRON, 4, 1)).setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("backBokken");

		backKatana = (new ItemArmourKatana(ArmorMaterial.IRON, 4, 1)).setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("backKatana");

		backStraps = (new BaseItem()).setUnlocalizedName("backStraps").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(1);

		headBand = (new ItemArmourHeadbands(headbandArmour, 4, 0)).setName("headProtector").setCreativeTab(ninjaHeadband).setMaxStackSize(1);

		fabric = (new BaseItemAndSub("Reinforced", "ReinforcedGreen", "ReinforcedBlack", "ReinforcedRed", "ReinforcedBlue", "ReinforcedOrange")).setName("fabric").setCreativeTab(ninjaMaterials);

		armourPlate = (new BaseItemAndSub("Red", "Green")).setName("armourPlate").setCreativeTab(ninjaMaterials).setMaxStackSize(32);

		flakJacket = new ItemNinjaArmour(FLACK_ARMOUR, 4, 1, "textures/armour/flak_jacket.png");
		flakJacket.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("flakJacket");

		kubikiribocho = (new ItemKubikiribocho(kubikiriTool)).setFull3D().setUnlocalizedName("Kubikiribocho").setTextureName("Kubikiribocho");

		shinobiChestplate = (new ItemArmourShinobiChestplate(SHINOBI_ARMOUR, 4, 1)).setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("madarasChestplate");

		shinobiLeggings = (new ItemArmourShinobiLeggings(SHINOBI_ARMOUR, 4, 2)).setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("madarasLeggings");

		redAnbuMask = (new ItemArmourAnbuMask(headbandArmour, 4, 0)).setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("redAnbuMask");

		JONIN_ARMOUR = (new ItemNinjaArmour(JONIN_ARMOUR_MATERIAL, 4, 1, "textures/armour/JoninArmour.png"));
		JONIN_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("joninArmour");

		NARUTO_SHIPPUDEN_ARMOUR = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/NarutoShippudenArmour.png"));
		NARUTO_SHIPPUDEN_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("narutoShippudenArmour");

		SASUKE_KID_ARMOUR = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/SasukeKidArmour.png"));
		SASUKE_KID_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("sasukeKidArmour");

		BORUTO_KID_ARMOUR = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/BorutoKidArmour.png"));
		BORUTO_KID_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("borutoKidArmour");

		BORUTO_KAKASHI_ARMOUR = (new ItemNinjaArmour(FULL_CHARACTER_CLOTHES, 4, 1, "textures/armour/BorutoKakashiArmor.png"));
		BORUTO_KAKASHI_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("borutoKakashiArmour");

		KID_NARUTO_ARMOUR = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/NarutoKidArmour.png"));
		KID_NARUTO_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("narutoKidArmour");

		BORUTO_KID_MOVIE_ARMOUR = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/BorutoMovieKidArmour.png"));
		BORUTO_KID_MOVIE_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("borutoKidMovieArmour");

		ANBU_ARMOUR = (new ItemNinjaArmour(ANBU_ARMOUR_MAT, 4, 1, "textures/armour/AnbuArmour.png"));
		ANBU_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("anbuArmour");

		ANBU_ARMOUR = (new ItemNinjaArmour(ANBU_ARMOUR_MAT, 4, 1, "textures/armour/AnbuArmour.png"));
		ANBU_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("anbuArmour");

		HAN_HAT = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 0, "textures/armour/hanhat.png"));
		HAN_HAT.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("hanHat");

		SAGE_ARMOUR = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/SageArmour.png"));
		SAGE_ARMOUR.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("sage");
//
		SASUKE_BORUTO = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/SasukeBoruto.png"));
		SASUKE_BORUTO.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("sasuke_boruto");

		KAKASHI_NARUTO = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/KakashiArmorNaruto.png"));
		KAKASHI_NARUTO.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("naruto_kakashi_armour");

		TOBI_MASK = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 0, "textures/armour/TobiMask.png"));
		TOBI_MASK.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("tobi_mask");

		HOKAGE_CLOAK = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/HokageCloak.png"));
		HOKAGE_CLOAK.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("hogake_cloak");

		AKATSUKI_CLOAK = (new ItemNinjaArmour(CHARACTER_CLOTHES, 4, 1, "textures/armour/AkatsukiCloak.png"));
		AKATSUKI_CLOAK.setCreativeTab(ninjaArmour).setMaxStackSize(1).setUnlocalizedName("akatsuki_cloak");
//
		//other stuff

		GameRegistry.registerItem(Kunai, "Kunai");
		GameRegistry.registerItem(Shuriken, "Shuriken");
		//GameRegistry.registerItem(FightingSpirit, "MusicDiscSpirit");

		//GameRegistry.registerItem(NejiTheme, "MusicDiscNeji");

		GameRegistry.registerItem(LONELY_MARCH, "MusicLonelyMarch");

		GameRegistry.registerItem(CopperRyo, "Kan");
		GameRegistry.registerItem(SilverRyo, "Rin");
		GameRegistry.registerItem(GoldRyo, "Ryo");

		GameRegistry.registerItem(Katana, "Katana");
		GameRegistry.registerItem(PaperBombItem, "PaperBomb");
		GameRegistry.registerItem(ExplosiveKunai, "ExplosiveKunai");
		GameRegistry.registerItem(RAMEN, "Ramen");
		GameRegistry.registerItem(GUNBAI, "Gunbai");
		GameRegistry.registerItem(bokken, "Bokken");
		GameRegistry.registerItem(SUMMON_SCROLL, "SummoningWolf");
		GameRegistry.registerItem(backScroll, "backScroll");
		GameRegistry.registerItem(NOODLES, "Noodles");
		GameRegistry.registerItem(SAMEHADA, "Samehada");
		GameRegistry.registerItem(MILITARY_PILLS, "Military Pills");
		GameRegistry.registerItem(senbon, "Senbon");

        GameRegistry.registerItem(gourd, "Gourd");

        GameRegistry.registerItem(backKatana, "backKatana");
        GameRegistry.registerItem(backBokken, "backBokken");

        GameRegistry.registerItem(backStraps, "backStraps");

		GameRegistry.registerItem(flakJacket,"flakJacket");

		GameRegistry.registerItem(kubikiribocho, "Kubikiribocho");

		GameRegistry.registerItem(shinobiChestplate, "Madara's Chestplate");

		GameRegistry.registerItem(shinobiLeggings, "Madara's Leggings");

		GameRegistry.registerItem(armourPlate, "Armour Plate");

        GameRegistry.registerItem(headBand, "Headband");

		GameRegistry.registerItem(fabric, "fabric");

		GameRegistry.registerItem(narutoIcons, "naruto_icons");

		GameRegistry.registerItem(redAnbuMask, "redAnbuMask");

		GameRegistry.registerItem(JONIN_ARMOUR,"joninArmour");

		GameRegistry.registerItem(NARUTO_SHIPPUDEN_ARMOUR,"narutoShippudenArmour");
		GameRegistry.registerItem(SASUKE_KID_ARMOUR,"sasukeKidArmour");
		GameRegistry.registerItem(BORUTO_KID_ARMOUR,"borutoKidArmour");
		GameRegistry.registerItem(BORUTO_KAKASHI_ARMOUR,"borutoKakashiArmour");
		GameRegistry.registerItem(BORUTO_KID_MOVIE_ARMOUR,"borutoKidMovieArmour");
		GameRegistry.registerItem(ANBU_ARMOUR,"anbuArmour");
		GameRegistry.registerItem(KID_NARUTO_ARMOUR,"narutoKidArmour");
		GameRegistry.registerItem(HAN_HAT,"hanHat");
		GameRegistry.registerItem(SAGE_ARMOUR,"sageArmour");

		GameRegistry.registerItem(SASUKE_BORUTO,"sasukeBoruto");
		GameRegistry.registerItem(KAKASHI_NARUTO,"kakashiNaruto");
		GameRegistry.registerItem(TOBI_MASK,"tobiMask");
		GameRegistry.registerItem(HOKAGE_CLOAK,"hokageCloak");
		GameRegistry.registerItem(AKATSUKI_CLOAK,"akatsukiCloak");


		ninjaArmour.setItemStack(new ItemStack(redAnbuMask));
		ninjaMaterials.setItemStack(new ItemStack(fabric));
		ninjaWeapons.setItemStack(new ItemStack(Kunai));
		ninjaHeadband.setItemStack(new ItemStack(headBand));

	}

	public static void addDispenserBehavior() {

		BlockDispenser.dispenseBehaviorRegistry.putObject(Kunai, new DispenserBehaviorKunai());

		BlockDispenser.dispenseBehaviorRegistry.putObject(Shuriken, new DispenserBehaviorShuriken());

		BlockDispenser.dispenseBehaviorRegistry.putObject(ExplosiveKunai, new DispenserBehaviorExplosiveKunai());

		BlockDispenser.dispenseBehaviorRegistry.putObject(senbon, new DispenserBehaviorSenbon());
	}


	public static void addCrafting() {
		//Kunai
		GameRegistry.addRecipe(new ItemStack(Kunai, 8), " I", "S ", 'I', Items.iron_ingot, 'S', Items.stick);
		//Shuriken
		GameRegistry.addRecipe(new ItemStack(Shuriken, 16), " F ", "FSF", " F ", 'S', Items.iron_ingot, 'F', Items.flint);
		//Katana
		GameRegistry.addRecipe(new ItemStack(Katana, 1), "  I", " I ", "S  ", 'I', Items.iron_ingot, 'S', Items.stick);
		//Katana
		GameRegistry.addRecipe(new ItemStack(Katana, 1), "I  ", " I ", "  S", 'I', Items.iron_ingot, 'S', Items.stick);
		//Bokken
		GameRegistry.addRecipe(new ItemStack(bokken, 1), "  S", " S ", "S  ", 'S', Items.stick);
		//Bokken
		GameRegistry.addRecipe(new ItemStack(bokken, 1), "S  ", " S ", "  S", 'S', Items.stick);
		//Explosive Kunai
		GameRegistry.addShapelessRecipe(new ItemStack(ExplosiveKunai, 1), Kunai, PaperBombItem);
		//Explosive Tag
		GameRegistry.addRecipe(new ItemStack(PaperBombItem, 1), "GGG", "PPP", "GGG", 'P', Items.paper, 'G', Items.gunpowder);
		//Gunbai
		GameRegistry.addRecipe(new ItemStack(GUNBAI, 1), "WSW", "WSW", " S ", 'W', Blocks.wool, 'S', Items.stick);
		//Noodles
		GameRegistry.addRecipe(new ItemStack(NOODLES, 3), "W", "W", "W", 'W', Items.wheat);
		//Ramen
		GameRegistry.addRecipe(new ItemStack(RAMEN, 1), "NNN", "NNN", " B ", 'N', NOODLES, 'B', Items.bowl);
		//Wolf Summoning scroll
		GameRegistry.addRecipe(new ItemStack(SUMMON_SCROLL, 1), "PPP", "PBP", "PPP", 'P', Items.paper, 'B', Items.bone);

		//Senbon
		GameRegistry.addRecipe(new ItemStack(senbon, 32), "I", "I", 'I', Items.iron_ingot);

		GameRegistry.addShapelessRecipe(new ItemStack(LONELY_MARCH, 1), new ItemStack(Blocks.obsidian, 1, 0), new ItemStack(Items.dye, 1, 12));

		// Back Straps
		GameRegistry.addRecipe(new ItemStack(backStraps, 1), " LL", "   ", "LL ", 'L', Items.leather);

		// Gourd
		GameRegistry.addRecipe(new ItemStack(gourd, 1), "SSS", "SLS", "SSS", 'S', Blocks.sandstone, 'L', backStraps);

		// Gourd
		GameRegistry.addRecipe(new ItemStack(backScroll, 1, 0), "PSP", "PLP", "PSP", 'P', Items.paper, 'S', Items.stick, 'L', backStraps);

		// Back Katana
		GameRegistry.addShapelessRecipe(new ItemStack(backKatana, 1), backStraps, Katana);
		GameRegistry.addShapelessRecipe(new ItemStack(Katana, 1), backKatana);

		// Back Bokken
		GameRegistry.addShapelessRecipe(new ItemStack(backBokken, 1), backStraps, bokken);
		GameRegistry.addShapelessRecipe(new ItemStack(bokken, 1), backBokken);

		// Fabric
		GameRegistry.addRecipe(new ItemStack(fabric, 6, 0), "WWW", "WWW", 'W', Blocks.wool);

		GameRegistry.addShapelessRecipe(new ItemStack(fabric, 1, 2), new ItemStack(fabric, 1, 1), new ItemStack(Items.dye, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(fabric, 1, 3), new ItemStack(fabric, 1, 1), new ItemStack(Items.dye, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(fabric, 1, 4), new ItemStack(fabric, 1, 1), new ItemStack(Items.dye, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(fabric, 1, 5), new ItemStack(fabric, 1, 1), new ItemStack(Items.dye, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(fabric, 1, 6), new ItemStack(fabric, 1, 1), new ItemStack(Items.dye, 1, 14));

		// Reinforced Fabric
		GameRegistry.addRecipe(new ItemStack(fabric, 3, 1), "III", "CCC", 'C', new ItemStack(fabric, 1, 0), 'I', Items.iron_ingot);

		// Armour Plate
		GameRegistry.addRecipe(new ItemStack(armourPlate, 3, 0), "III", "RRR", 'R', new ItemStack(fabric, 1, 1), 'I', Items.iron_ingot);

		// Naruto Shippuden clothes
		GameRegistry.addRecipe(new ItemStack(NARUTO_SHIPPUDEN_ARMOUR), "B B", "BBB", "OOO", 'B', new ItemStack(fabric, 1, 3), 'O', new ItemStack(fabric, 1, 6));

		// Naruto clothes
		GameRegistry.addRecipe(new ItemStack(KID_NARUTO_ARMOUR), "B B", "OOO", "OOO", 'B', new ItemStack(fabric, 1, 5), 'O', new ItemStack(fabric, 1, 6));

		// Jounin Armour
		GameRegistry.addRecipe(new ItemStack(JONIN_ARMOUR), "A A", "AAA", "AAA", 'A', new ItemStack(armourPlate, 1, 2));

		// Anbu Armour
		GameRegistry.addRecipe(new ItemStack(ANBU_ARMOUR), "B B", "AAA", "AAA", 'B', new ItemStack(fabric, 1, 3), 'A', armourPlate);

		// Boruto
		GameRegistry.addRecipe(new ItemStack(BORUTO_KID_MOVIE_ARMOUR), "B B", "BFB", "BFB", 'B', new ItemStack(fabric, 1, 3), 'F', new ItemStack(fabric));
		GameRegistry.addShapelessRecipe(new ItemStack(BORUTO_KID_ARMOUR), new ItemStack(BORUTO_KID_MOVIE_ARMOUR), new ItemStack(Items.dye, 1, 9));

		// Headband
		GameRegistry.addRecipe(new ItemStack(headBand, 1, BLANK.getSubId()), "BAB", 'B', new ItemStack(fabric, 1, 5), 'A', armourPlate);
		GameRegistry.addRecipe(new ItemStack(headBand, 1, CUSTARD.getSubId()), "SAS", 'S', Items.sugar, 'A', armourPlate);
		GameRegistry.addRecipe(new ItemStack(headBand, 1, BLANK_RED.getSubId()), "RAR", 'R', new ItemStack(fabric, 1, 4), 'A', armourPlate);
		GameRegistry.addRecipe(new ItemStack(headBand, 1, BLANK_BLACK.getSubId()), "BAB", 'B', new ItemStack(fabric, 1, 3), 'A', armourPlate);

		addReverseableHeadband(GRASS, GRASS_SCRATCHED);
		addReverseableHeadband(LEAVES, SCRATCHED);
		addReverseableHeadband(LEAVES_BLACK, LEAVES_BLACK_SCRATCHED);
		addReverseableHeadband(ROCK, ROCK_SCRATCHED);
		addReverseableHeadband(SAND, SAND_SCRATCHED);
		addReverseableHeadband(SOUND, SOUND_SCRATCHED);
		addReverseableHeadband(MIST, MIST_SCRATCHED);
		addReverseableHeadband(WATERFALL, WATERFALL_SCRATCHED);
		addReverseableHeadband(CLOUD, CLOUD_SCRATCHED);
		addReverseableHeadband(RAIN, RAIN_SCRATCHED);

		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, LEAVES.getSubId()), Blocks.leaves, new ItemStack(headBand, 1, BLANK.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, LEAVES.getSubId()), Blocks.leaves2, new ItemStack(headBand, 1, BLANK.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, LEAVES_BLACK.getSubId()), Blocks.leaves, new ItemStack(headBand, 1, BLANK_BLACK.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, LEAVES_BLACK.getSubId()), Blocks.leaves2, new ItemStack(headBand, 1, BLANK_BLACK.getSubId()));

		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, LAVA.getSubId()), Items.lava_bucket, new ItemStack(headBand, 1, BLANK_RED.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, ROCK.getSubId()), Blocks.stone, new ItemStack(headBand, 1, BLANK_RED.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, SAND.getSubId()), Blocks.sand, new ItemStack(headBand, 1, BLANK_BLACK.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, SOUND.getSubId()), Blocks.noteblock, new ItemStack(headBand, 1, BLANK_RED.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, WATERFALL.getSubId()), Items.water_bucket, new ItemStack(headBand, 1, BLANK_BLACK.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, MIST.getSubId()), Items.snowball, new ItemStack(headBand, 1, BLANK_BLACK.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, RAIN.getSubId()), new ItemStack(Items.potionitem, 1, 0), new ItemStack(headBand, 1, BLANK_BLACK.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, GRASS.getSubId()), new ItemStack(Blocks.tallgrass, 1, 1), new ItemStack(headBand, 1, BLANK_BLACK.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, CLOUD.getSubId()), new ItemStack(Blocks.wool, 1, 0), new ItemStack(headBand, 1, BLANK_BLACK.getSubId()));



		// Anbu Mask
		GameRegistry.addRecipe(new ItemStack(redAnbuMask), "IRI", "III", " I ", 'R', new ItemStack(Items.dye, 1, 1), 'I', new ItemStack(armourPlate, 1, 0));

		// Colored Armour Plates
		GameRegistry.addShapelessRecipe(new ItemStack(armourPlate, 1, 1), new ItemStack(armourPlate, 1, 0), new ItemStack(Items.dye, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(armourPlate, 1, 2), new ItemStack(armourPlate, 1, 0), new ItemStack(Items.dye, 1, 2));

		// Flak Jacket

		GameRegistry.addRecipe(new ItemStack(flakJacket, 1), "R R", "RRR", "RRR", 'R', new ItemStack(fabric, 1, 2));

		// Senju Armour

		GameRegistry.addRecipe(new ItemStack(shinobiChestplate, 1), "R R", "RRR", "RRR", 'R', new ItemStack(armourPlate, 1, 1));

		GameRegistry.addRecipe(new ItemStack(shinobiLeggings, 1), "RRR", "B B", "B B", 'R', new ItemStack(armourPlate, 1, 1), 'B', new ItemStack(fabric, 1, 3));

		// Back scrolls
		GameRegistry.addShapelessRecipe(new ItemStack(backScroll, 1, 1), new ItemStack(backScroll, 1, 0), new ItemStack(Items.dye, 1, 11));
		GameRegistry.addShapelessRecipe(new ItemStack(backScroll, 1, 2), new ItemStack(backScroll, 1, 0), new ItemStack(Items.dye, 1, 5));
		GameRegistry.addShapelessRecipe(new ItemStack(backScroll, 1, 3), new ItemStack(backScroll, 1, 0), new ItemStack(Items.dye, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(backScroll, 1, 4), new ItemStack(backScroll, 1, 0), new ItemStack(Items.dye, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(backScroll, 1, 5), new ItemStack(backScroll, 1, 0), new ItemStack(Items.dye, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(backScroll, 1, 6), new ItemStack(backScroll, 1, 0), new ItemStack(Items.dye, 1, 0));

		// Armour plate

		/*GameRegistry.addRecipe(new ItemStack(armourPlate, 3), "   ", "III", "III", 'I', Items.iron_ingot);

		GameRegistry.addRecipe(new ItemStack(armourPlate, 3), "III", "III", "   ", 'I', Items.iron_ingot);*/

	}

	public static void addReverseableHeadband(ItemArmourHeadbands.SUB_HEADBAND headband1, ItemArmourHeadbands.SUB_HEADBAND headband2) {
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, headband1.getSubId()), new ItemStack(headBand, 1, headband2.getSubId()));
		GameRegistry.addShapelessRecipe(new ItemStack(headBand, 1, headband2.getSubId()), new ItemStack(headBand, 1, headband1.getSubId()));

	}

	public static boolean shouldHideHair(Item item) {
		return item == NarutoItems.BORUTO_KAKASHI_ARMOUR || item == NarutoItems.TOBI_MASK || item == NarutoItems.KAKASHI_NARUTO;
	}
}
