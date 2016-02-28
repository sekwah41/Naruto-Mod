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
import sekwah.mods.narutomod.blocks.NarutoBlocks;
import sekwah.mods.narutomod.common.NarutoEffects;
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
	public static Item FightingSpirit;
	public static Item NejiTheme;
	public static Item CopperRyo;
	public static Item SilverRyo;
	public static Item GoldRyo;
	public static Item Katana;
	public static Item PaperBombItem;
	public static Item ExplosiveKunai;
	public static Item Gunbai;
	public static Item Samehada;
	public static Item Ramen;
	public static Item Summon_Scroll;
	public static Item Noodles;
	public static Item military_pills;
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
	// TODO Finish adding the code and complete tomorrow(tomorrow has passed, get yo lazy ass to work you bum)
	//  recipe needed
	public static Item armourPlate;
	public static EnumAction Throw = EnumHelper.addAction("Throw");
	// Armour materials
	private static ArmorMaterial flakArmour = EnumHelper.addArmorMaterial("FLAK", 35, new int[]{2, 7, 5, 2}, 10);
	private static ArmorMaterial shinobiArmour = EnumHelper.addArmorMaterial("SHINOBI", 35, new int[]{2, 8, 5, 2}, 10);
    // Same as iron atm
	private static ArmorMaterial headbandArmour = EnumHelper.addArmorMaterial("HEADBAND", 35, new int[]{2, 8, 5, 2}, 10);
	// Weapon and tool materials
	private static ToolMaterial gunbaiTool = EnumHelper.addToolMaterial("GUNBAI", 0, 100, 2.0F, 0, 15);
	private static ToolMaterial samehadaTool = EnumHelper.addToolMaterial("SAMEHADA", 0, 3000, 8.0F, 4, 3);
	private static ToolMaterial kubikiriTool = EnumHelper.addToolMaterial("KUBOKIRIBOCHO", 0, 3000, 8.0F, 5, 3);
	private static ToolMaterial bokkenTool = EnumHelper.addToolMaterial("BOKKEN", 0, 59, 2.0F, -1.0F, 15);

	public static void addItems(Configuration config) {

		FightingSpirit = (new ItemNinjaRecord("FightingSpirit")).setUnlocalizedName("record_FightingSpirit");
		NejiTheme = (new ItemNinjaRecord("NejiTheme")).setUnlocalizedName("record_NejiTheme");

		Kunai = (new ItemKunai()).setUnlocalizedName("Kunai");
		Shuriken = (new ItemShuriken()).setUnlocalizedName("Shuriken");

		CopperRyo = (new BaseItem()).setUnlocalizedName("copper_ryo").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(32);
		SilverRyo = (new BaseItem()).setUnlocalizedName("silver_ryo").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(32);
		GoldRyo = (new BaseItem()).setUnlocalizedName("gold_ryo").setCreativeTab(CreativeTabs.tabMisc).setMaxStackSize(32);


		Katana = (new ItemNinjaSword(ToolMaterial.IRON)).setUnlocalizedName("Katana");

		bokken = (new ItemNinjaSword(bokkenTool)).setUnlocalizedName("Bokken");

		PaperBombItem = (new ItemBlockPlacer(NarutoBlocks.PaperBombBlock)).setUnlocalizedName("ExplosiveTag").setTextureName("ExplosiveTag").setCreativeTab(NarutoItems.ninjaWeapons).setMaxStackSize(16);

		ExplosiveKunai = (new ItemExplosiveKunai()).setUnlocalizedName("ExplosiveKunai").setTextureName("ExplosiveKunai");

		Gunbai = (new ItemGunbai(gunbaiTool)).setUnlocalizedName("gunbai").setTextureName("gunbai");

		Ramen = (new ItemFoodWithReturnItem(10, 0.5F, Items.bowl, false).setUnlocalizedName("Ramen").setTextureName("Ramen").setMaxStackSize(1));

		Noodles = (new BaseItem()).setUnlocalizedName("Noodles").setTextureName("Noodles").setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(64);

		Summon_Scroll = (new ItemBlockPlacer(NarutoBlocks.Summoning_CircleCenter)).setUnlocalizedName("Scroll").setTextureName("Scroll").setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1);

		Samehada = (new ItemSamehada(samehadaTool)).setFull3D().setUnlocalizedName("Samehada").setTextureName("Samehada");

		military_pills = (new ItemFoodBase(1, 0.1F, false)).setAlwaysEdible().setPotionEffect(NarutoEffects.chakraRestore.id, 30, 0, 1.0F).setUnlocalizedName("military_pills").setTextureName("military_pills").setCreativeTab(CreativeTabs.tabFood).setMaxStackSize(64);

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

		flakJacket = (new ItemArmourFlakJacket(flakArmour, 4, 1)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("flakJacket");

		kubikiribocho = (new ItemKubikiribocho(kubikiriTool)).setFull3D().setUnlocalizedName("Kubikiribocho").setTextureName("Kubikiribocho");

		shinobiChestplate = (new ItemArmourShinobiChestplate(shinobiArmour, 4, 1)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("madarasChestplate");

		shinobiLeggings = (new ItemArmourShinobiLeggings(shinobiArmour, 4, 2)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("madarasLeggings");

		armourPlate = (new BaseItem()).setUnlocalizedName("armourPlate").setCreativeTab(ninjaMaterials).setMaxStackSize(32);

		redAnbuMask = (new ItemArmourAnbuMask(headbandArmour, 4, 0)).setCreativeTab(CreativeTabs.tabCombat).setMaxStackSize(1).setUnlocalizedName("redAnbuMask");

		// TODO add the masks from the anbu and rouge

		// TODO update gunbai

		//other stuff

		GameRegistry.registerItem(Kunai, "Kunai");
		GameRegistry.registerItem(Shuriken, "Shuriken");
		GameRegistry.registerItem(FightingSpirit, "MusicDiscSpirit");

		GameRegistry.registerItem(NejiTheme, "MusicDiscNeji");

		GameRegistry.registerItem(CopperRyo, "Kan");
		GameRegistry.registerItem(SilverRyo, "Rin");
		GameRegistry.registerItem(GoldRyo, "Ryo");

		GameRegistry.registerItem(Katana, "Katana");
		GameRegistry.registerItem(PaperBombItem, "PaperBomb");
		GameRegistry.registerItem(ExplosiveKunai, "ExplosiveKunai");
		GameRegistry.registerItem(Ramen, "Ramen");
		GameRegistry.registerItem(Gunbai, "Gunbai");
		GameRegistry.registerItem(bokken, "Bokken");
		GameRegistry.registerItem(Summon_Scroll, "SummoningWolf");
		GameRegistry.registerItem(backScroll, "backScroll");
		GameRegistry.registerItem(Noodles, "Noodles");
		GameRegistry.registerItem(Samehada, "Samehada");
		GameRegistry.registerItem(military_pills, "Military Pills");
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
		GameRegistry.addRecipe(new ItemStack(Gunbai, 1), "WSW", "WSW", " S ", 'W', Blocks.wool, 'S', Items.stick);
		//Noodles
		GameRegistry.addRecipe(new ItemStack(Noodles, 3), "W", "W", "W", 'W', Items.wheat);
		//Ramen
		GameRegistry.addRecipe(new ItemStack(Ramen, 1), "NNN", "NNN", " B ", 'N', Noodles, 'B', Items.bowl);
		//Wolf Summoning scroll
		GameRegistry.addRecipe(new ItemStack(Summon_Scroll, 1), "PPP", "PBP", "PPP", 'P', Items.paper, 'B', Items.bone);

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
