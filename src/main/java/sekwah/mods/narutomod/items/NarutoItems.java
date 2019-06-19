package sekwah.mods.narutomod.items;

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
import sekwah.mods.narutomod.items.creativetabs.CreativeTabNinjaMaterials;
import sekwah.mods.narutomod.items.creativetabs.CreativeTabNinjaWeapons;
import sekwah.mods.narutomod.items.dispenserbehavior.DispenserBehaviorExplosiveKunai;
import sekwah.mods.narutomod.items.dispenserbehavior.DispenserBehaviorKunai;
import sekwah.mods.narutomod.items.dispenserbehavior.DispenserBehaviorSenbon;
import sekwah.mods.narutomod.items.dispenserbehavior.DispenserBehaviorShuriken;

public class NarutoItems {

	public static final CreativeTabs ninjaWeapons = new CreativeTabNinjaWeapons(CreativeTabs.getNextID(), "narutoWeapons");
	public static final CreativeTabs ninjaMaterials = new CreativeTabNinjaMaterials(CreativeTabs.getNextID(), "narutoMaterials");
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
	public static ItemNinjaArmor flakJacket;
	public static Item shinobiChestplate;
	public static Item shinobiLeggings;
	public static Item redAnbuMask;

	public static ItemNinjaArmor JONIN_ARMOR;
	public static ItemNinjaArmor NARUTO_SHIPPUDEN_ARMOR;
	public static ItemNinjaArmor SASUKE_KID_ARMOR;
	public static ItemNinjaArmor BORUTO_KID_ARMOR;
	public static ItemNinjaArmor BORUTO_KID_MOVIE_ARMOR;
	public static ItemNinjaArmor ANBU_ARMOR;

	// TODO Finish adding the code and complete tomorrow(tomorrow has passed, get yo lazy ass to work you bum)
	//  recipe needed
	public static Item armourPlate;
	public static EnumAction Throw = EnumHelper.addAction("Throw");
	// Armour materials
	private static ArmorMaterial FLACK_ARMOR = EnumHelper.addArmorMaterial("FLAK", 35, new int[]{2, 7, 5, 2}, 10);
	private static ArmorMaterial JONIN_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("JONIN", 35, new int[]{2, 7, 5, 2}, 10);
	private static ArmorMaterial SHINOBI_ARMOUR = EnumHelper.addArmorMaterial("SHINOBI", 35, new int[]{2, 8, 5, 2}, 10);

	private static ArmorMaterial ANBU_ARMOUR = EnumHelper.addArmorMaterial("SHINOBI", 35, new int[]{2, 8, 5, 2}, 10);

	private static ArmorMaterial KID_ARMOUR = EnumHelper.addArmorMaterial("KID_CLOTHES", 35, new int[]{2, 8, 5, 2}, 10);
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

		SUMMON_SCROLL = (new ItemBlockPlacer(NarutoBlocks.Summoning_CircleCenter)).setUnlocalizedName("Scroll").setTextureName("Scroll").setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1);

		SAMEHADA = (new ItemSamehada(samehadaTool)).setFull3D().setUnlocalizedName("Samehada").setTextureName("Samehada");

		MILITARY_PILLS = (new ItemFoodBase(1, 0.1F, false)).setAlwaysEdible().setPotionEffect(NarutoEffects.chakraRestore.id, 30, 0, 1.0F).setUnlocalizedName("military_pills").setTextureName("military_pills").setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(64);

		senbon = (new ItemSenbon()).setUnlocalizedName("Senbon").setTextureName("Senbon");

		// icons for the menus

		narutoIcons = (new IconItem()).setUnlocalizedName("narutoIcons").setMaxStackSize(1);

		gourd = (new ItemArmourGourd(ArmorMaterial.IRON, 4, 1)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("gourd");

		backScroll = (new ItemArmourScroll(ArmorMaterial.IRON, 4, 1)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("backScroll");

		backBokken = (new ItemArmourBokken(ArmorMaterial.IRON, 4, 1)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("backBokken");
		// armour items

		backKatana = (new ItemArmourKatana(ArmorMaterial.IRON, 4, 1)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("backKatana");

		backStraps = (new BaseItem()).setUnlocalizedName("backStraps").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(1);

		// TODO add durability onto the headbands and back scroll so register as seperate items or look how leather has a colour value
		// TODO change liams hidden in the tress to hidden in the leaf and add other village headbands.
		headBand = (new ItemArmourHeadband(headbandArmour, 4, 0)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("headProtector");

		fabric = (new ItemFabric()).setUnlocalizedName("fabric").setCreativeTab(ninjaMaterials);

		flakJacket = new ItemNinjaArmor(FLACK_ARMOR, 4, 1, "textures/armour/flak_jacket.png");
		flakJacket.setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("flakJacket");

		kubikiribocho = (new ItemKubikiribocho(kubikiriTool)).setFull3D().setUnlocalizedName("Kubikiribocho").setTextureName("Kubikiribocho");

		shinobiChestplate = (new ItemArmourShinobiChestplate(SHINOBI_ARMOUR, 4, 1)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("madarasChestplate");

		shinobiLeggings = (new ItemArmourShinobiLeggings(SHINOBI_ARMOUR, 4, 2)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("madarasLeggings");

		armourPlate = (new BaseItem()).setUnlocalizedName("armourPlate").setCreativeTab(ninjaMaterials).setMaxStackSize(32);

		redAnbuMask = (new ItemArmourAnbuMask(headbandArmour, 4, 0)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("redAnbuMask");

		JONIN_ARMOR = (new ItemNinjaArmor(JONIN_ARMOR_MATERIAL, 4, 1, "textures/armour/JoninArmor.png"));
		JONIN_ARMOR.setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("joninArmour");

		NARUTO_SHIPPUDEN_ARMOR = (new ItemNinjaArmor(KID_ARMOUR, 4, 1, "textures/armour/NarutoShippudenArmor.png"));
		NARUTO_SHIPPUDEN_ARMOR.setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("narutoShippudenArmor");

		SASUKE_KID_ARMOR = (new ItemNinjaArmor(KID_ARMOUR, 4, 1, "textures/armour/SasukeKidArmor.png"));
		SASUKE_KID_ARMOR.setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("sasukeKidArmor");

		BORUTO_KID_ARMOR = (new ItemNinjaArmor(KID_ARMOUR, 4, 1, "textures/armour/BorutoKidArmor.png"));
		BORUTO_KID_ARMOR.setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("borutoKidArmor");

		BORUTO_KID_MOVIE_ARMOR = (new ItemNinjaArmor(KID_ARMOUR, 4, 1, "textures/armour/BorutoMovieKidArmor.png"));
		BORUTO_KID_MOVIE_ARMOR.setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("borutoKidMovieArmor");

		ANBU_ARMOR = (new ItemNinjaArmor(ANBU_ARMOUR, 4, 1, "textures/armour/AnbuArmor.png"));
		ANBU_ARMOR.setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("anbuArmor");

		// TODO add the masks from the anbu and rouge

		// TODO update gunbai

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

		GameRegistry.registerItem(JONIN_ARMOR,"joninArmour");

		GameRegistry.registerItem(NARUTO_SHIPPUDEN_ARMOR,"narutoShippudenArmor");
		GameRegistry.registerItem(SASUKE_KID_ARMOR,"sasukeKidArmor");
		GameRegistry.registerItem(BORUTO_KID_ARMOR,"borutoKidArmor");
		GameRegistry.registerItem(BORUTO_KID_MOVIE_ARMOR,"borutoKidMovieArmor");
		GameRegistry.registerItem(ANBU_ARMOR,"anbuArmor");

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

		// Reinforced Fabric
		GameRegistry.addRecipe(new ItemStack(fabric, 1, 1), "IGI", "CCC", "   ", 'C', new ItemStack(fabric, 1, 0), 'I', Items.iron_ingot, 'G', new ItemStack(Items.dye, 1, 2));

		GameRegistry.addRecipe(new ItemStack(fabric, 1, 1), "   ", "IGI", "CCC", 'C', new ItemStack(fabric, 1, 0), 'I', Items.iron_ingot, 'G', new ItemStack(Items.dye, 1, 2));

		// Flak Jacket

		GameRegistry.addRecipe(new ItemStack(flakJacket, 1), "R R", "RRR", "RRR", 'R', new ItemStack(fabric, 1, 1));

		// Senju Armour

		GameRegistry.addRecipe(new ItemStack(shinobiChestplate, 1), "R R", "RRR", "RRR", 'R', armourPlate);

		// Armour plate

		/*GameRegistry.addRecipe(new ItemStack(armourPlate, 3), "   ", "III", "III", 'I', Items.iron_ingot);

		GameRegistry.addRecipe(new ItemStack(armourPlate, 3), "III", "III", "   ", 'I', Items.iron_ingot);*/

	}

}
