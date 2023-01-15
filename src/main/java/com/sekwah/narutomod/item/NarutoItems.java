package com.sekwah.narutomod.item;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.item.armor.NarutoArmorItem;
import com.sekwah.narutomod.item.armor.NarutoArmorMaterial;
import com.sekwah.narutomod.item.weapons.ExplosiveKunaiItem;
import com.sekwah.narutomod.item.weapons.KunaiItem;
import com.sekwah.narutomod.item.weapons.SenbonItem;
import com.sekwah.narutomod.item.weapons.ShurikenItem;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Weapons
    public static final RegistryObject<Item> KUNAI = ITEMS.register("kunai", ()
            -> new KunaiItem(new Item.Properties()));

    public static final RegistryObject<Item> SENBON = ITEMS.register("senbon", ()
            -> new SenbonItem(new Item.Properties()));

    public static final RegistryObject<Item> EXPLOSIVE_KUNAI = ITEMS.register("explosive_kunai", ()
            -> new ExplosiveKunaiItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> SHURIKEN = ITEMS.register("shuriken", ()
            -> new ShurikenItem(new Item.Properties()));


    // Armor

    public static final RegistryObject<Item> RED_ANBU_MASK = ITEMS.register("red_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD, new Item.Properties())
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> YELLOW_ANBU_MASK = ITEMS.register("yellow_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD, new Item.Properties())
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> GREEN_ANBU_MASK = ITEMS.register("green_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD, new Item.Properties())
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> BLUE_ANBU_MASK = ITEMS.register("blue_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD, new Item.Properties())
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> MIST_ANBU_MASK = ITEMS.register("mist_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD, new Item.Properties())
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> FLAK_JACKET_NEW = ITEMS.register("flak_jacket_new", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.FLAK_MAT, EquipmentSlot.CHEST, new Item.Properties()));

    public static final RegistryObject<Item> FLAK_JACKET = ITEMS.register("flak_jacket", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.FLAK_STRONGER_MAT, EquipmentSlot.CHEST, new Item.Properties()));

    public static final RegistryObject<Item> ANBU_ARMOR = ITEMS.register("anbu_armor", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.FLAK_STRONGER_MAT, EquipmentSlot.CHEST, new Item.Properties()));

    public static final RegistryObject<Item> AKATSUKI_CLOAK = ITEMS.register("akatsuki_cloak", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.CHARACTER_CLOTHES, EquipmentSlot.CHEST, new Item.Properties()));

    public static final RegistryObject<Item> LONELY_MARCH = ITEMS.register("lonely_march", ()
            -> new RecordItem(41, NarutoSounds.LONELY_MARCH, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 46 * 20));

    public static final RegistryObject<Item> FABRIC = ITEMS.register("fabric", ()
            -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FABRIC_REINFORCED = ITEMS.register("fabric_reinforced", ()
            -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FABRIC_REINFORCED_GREEN = ITEMS.register("fabric_reinforced_green", ()
            -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FABRIC_REINFORCED_BLACK = ITEMS.register("fabric_reinforced_black", ()
            -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_PLATE = ITEMS.register("armor_plate", ()
            -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARMOR_PLATE_GREEN = ITEMS.register("armor_plate_green", ()
            -> new Item(new Item.Properties()));

    // Ninja Masks

    private static RegistryObject<Item> createHeadband(String itemName) {
        return ITEMS.register(itemName, ()
                -> new NarutoArmorItem(NarutoArmorMaterial.HEADBAND, EquipmentSlot.HEAD, new Item.Properties()));
    }

    //    BLANK(0, "blankBlueHeadBand", "headband_blank);"),
    public static final RegistryObject<Item> HEADBAND_BLUE = createHeadband("headband_blue");
    //    BLANK_BLACK(1, "blankBlackHeadBand", "headband_black);"),
    public static final RegistryObject<Item> HEADBAND_BLACK = createHeadband("headband_black");
    //    BLANK_RED(2, "blankRedHeadBand", "headband_kred);"),
    public static final RegistryObject<Item> HEADBAND_RED = createHeadband("headband_red");
    //    CUSTARD(4, "custardHeadband", "headband_custard);"),
    public static final RegistryObject<Item> HEADBAND_CUSTARD = createHeadband("headband_custard");


    public static final RegistryObject<Item> HEADBAND_LEAF = createHeadband("headband_leaf");
    public static final RegistryObject<Item> HEADBAND_LEAF_SCRATCHED = createHeadband("headband_leaf_scratched");
    public static final RegistryObject<Item> HEADBAND_LEAF_BLACK = createHeadband("headband_leaf_black");
    public static final RegistryObject<Item> HEADBAND_LEAF_BLACK_SCRATCHED = createHeadband("headband_leaf_black_scratched");

    public static final RegistryObject<Item> HEADBAND_ROCK = createHeadband("headband_rock");
    public static final RegistryObject<Item> HEADBAND_ROCK_SCRATCHED = createHeadband("headband_rock_scratched");

    public static final RegistryObject<Item> HEADBAND_SAND = createHeadband("headband_sand");
    public static final RegistryObject<Item> HEADBAND_SAND_SCRATCHED = createHeadband("headband_sand_scratched");

    public static final RegistryObject<Item> HEADBAND_SOUND = createHeadband("headband_sound");
    public static final RegistryObject<Item> HEADBAND_SOUND_SCRATCHED = createHeadband("headband_sound_scratched");

    public static final RegistryObject<Item> HEADBAND_MIST = createHeadband("headband_mist");
    public static final RegistryObject<Item> HEADBAND_MIST_SCRATCHED = createHeadband("headband_mist_scratched");

    public static final RegistryObject<Item> HEADBAND_WATERFALL = createHeadband("headband_waterfall");
    public static final RegistryObject<Item> HEADBAND_WATERFALL_SCRATCHED = createHeadband("headband_waterfall_scratched");

    public static final RegistryObject<Item> HEADBAND_CLOUD = createHeadband("headband_cloud");
    public static final RegistryObject<Item> HEADBAND_CLOUD_SCRATCHED = createHeadband("headband_cloud_scratched");

    public static final RegistryObject<Item> HEADBAND_RAIN = createHeadband("headband_rain");
    public static final RegistryObject<Item> HEADBAND_RAIN_SCRATCHED = createHeadband("headband_rain_scratched");

    public static final RegistryObject<Item> HEADBAND_GRASS = createHeadband("headband_grass");
    public static final RegistryObject<Item> HEADBAND_GRASS_SCRATCHED = createHeadband("headband_grass_scratched");


    public static final RegistryObject<Item> HEADBAND_PRIDE = createHeadband("headband_pride");
    public static final RegistryObject<Item> HEADBAND_YOUTUBE = createHeadband("headband_youtube");
    public static final RegistryObject<Item> HEADBAND_LAVA = createHeadband("headband_lava");


    public static CreativeModeTab NINJA_WEAPONS = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0)
            .icon(() -> registryObjectToItemStack(KUNAI)).displayItems((features, output, hasPermissions) -> {
                output.accept(KUNAI.get());
                output.accept(SENBON.get());
                output.accept(EXPLOSIVE_KUNAI.get());
                output.accept(SHURIKEN.get());
                output.accept(NarutoBlocks.ITEM_PAPER_BOMB.get());
            }).title(Component.translatable("narutomod_weapons")).build();

    public static CreativeModeTab NINJA_ARMOR = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0)
            .icon(() -> registryObjectToItemStack(RED_ANBU_MASK)).displayItems((features, output, hasPermissions) -> {
                output.accept(RED_ANBU_MASK.get());
                output.accept(YELLOW_ANBU_MASK.get());
                output.accept(GREEN_ANBU_MASK.get());
                output.accept(BLUE_ANBU_MASK.get());
                output.accept(MIST_ANBU_MASK.get());
                output.accept(FLAK_JACKET_NEW.get());
                output.accept(FLAK_JACKET.get());
                output.accept(ANBU_ARMOR.get());
                output.accept(AKATSUKI_CLOAK.get());
            }).title(Component.translatable("narutomod_armor")).build();

    public static CreativeModeTab HEADBANDS = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0)
            .icon(() -> registryObjectToItemStack(HEADBAND_BLUE)).displayItems((features, output, hasPermissions) -> {
                output.accept(HEADBAND_BLUE.get());
                output.accept(HEADBAND_BLACK.get());
                output.accept(HEADBAND_RED.get());
                output.accept(HEADBAND_CUSTARD.get());
                output.accept(HEADBAND_LEAF.get());
                output.accept(HEADBAND_LEAF_SCRATCHED.get());
                output.accept(HEADBAND_LEAF_BLACK.get());
                output.accept(HEADBAND_LEAF_BLACK_SCRATCHED.get());
                output.accept(HEADBAND_ROCK.get());
                output.accept(HEADBAND_ROCK_SCRATCHED.get());
                output.accept(HEADBAND_SAND.get());
                output.accept(HEADBAND_SAND_SCRATCHED.get());
                output.accept(HEADBAND_SOUND.get());
                output.accept(HEADBAND_SOUND_SCRATCHED.get());
                output.accept(HEADBAND_MIST.get());
                output.accept(HEADBAND_MIST_SCRATCHED.get());
                output.accept(HEADBAND_WATERFALL.get());
                output.accept(HEADBAND_WATERFALL_SCRATCHED.get());
                output.accept(HEADBAND_CLOUD.get());
                output.accept(HEADBAND_CLOUD_SCRATCHED.get());
                output.accept(HEADBAND_RAIN.get());
                output.accept(HEADBAND_RAIN_SCRATCHED.get());
                output.accept(HEADBAND_GRASS.get());
                output.accept(HEADBAND_GRASS_SCRATCHED.get());
                output.accept(HEADBAND_PRIDE.get());
                output.accept(HEADBAND_YOUTUBE.get());
                output.accept(HEADBAND_LAVA.get());
            }).title(Component.translatable("narutomod_headbands")).build();

    public static CreativeModeTab NINJA_MATERIALS = CreativeModeTab.builder(CreativeModeTab.Row.BOTTOM, 0)
            .icon(() -> registryObjectToItemStack(FABRIC)).displayItems((features, output, hasPermissions) -> {
                output.accept(FABRIC.get());
                output.accept(FABRIC_REINFORCED.get());
                output.accept(FABRIC_REINFORCED_GREEN.get());
                output.accept(FABRIC_REINFORCED_BLACK.get());
                output.accept(ARMOR_PLATE.get());
                output.accept(ARMOR_PLATE_GREEN.get());
            }).title(Component.translatable("narutomod_materials")).build();


    private static ItemStack registryObjectToItemStack(RegistryObject<Item> item) {
        return new ItemStack(item.get());
    }

    // Materials

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
