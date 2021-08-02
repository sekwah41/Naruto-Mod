package com.sekwah.narutomod.item;

import com.sekwah.narutomod.item.armor.NarutoArmorItem;
import com.sekwah.narutomod.item.armor.NarutoArmorMaterial;
import com.sekwah.narutomod.item.weapons.ExplosiveKunaiItem;
import com.sekwah.narutomod.item.weapons.KunaiItem;
import com.sekwah.narutomod.item.weapons.SenbonItem;
import com.sekwah.narutomod.item.weapons.ShurikenItem;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoItems {

    public static CreativeModeTab NINJA_WEAPONS = new CreativeModeTab("narutomod_weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(KUNAI.get());
        }
    };

    public static CreativeModeTab NINJA_ARMOR = new CreativeModeTab("narutomod_armor") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RED_ANBU_MASK.get());
        }
    };

    public static CreativeModeTab HEADBANDS = new CreativeModeTab("narutomod_headbands") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(HEADBAND_BLUE.get());
        }
    };

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Weapons
    public static final RegistryObject<Item> KUNAI = ITEMS.register("kunai", ()
            -> new KunaiItem(new Item.Properties().tab(NINJA_WEAPONS)));

    public static final RegistryObject<Item> SENBON = ITEMS.register("senbon", ()
            -> new SenbonItem(new Item.Properties().tab(NINJA_WEAPONS)));

    public static final RegistryObject<Item> EXPLOSIVE_KUNAI = ITEMS.register("explosive_kunai", ()
            -> new ExplosiveKunaiItem(new Item.Properties().stacksTo(16).tab(NINJA_WEAPONS)));

    public static final RegistryObject<Item> SHURIKEN = ITEMS.register("shuriken", ()
            -> new ShurikenItem(new Item.Properties().tab(NINJA_WEAPONS)));


    // Armor

    public static final RegistryObject<Item> RED_ANBU_MASK = ITEMS.register("red_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD,  new Item.Properties().tab(NINJA_ARMOR))
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> YELLOW_ANBU_MASK = ITEMS.register("yellow_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD,  new Item.Properties().tab(NINJA_ARMOR))
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> GREEN_ANBU_MASK = ITEMS.register("green_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD,  new Item.Properties().tab(NINJA_ARMOR))
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> BLUE_ANBU_MASK = ITEMS.register("blue_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD,  new Item.Properties().tab(NINJA_ARMOR))
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> MIST_ANBU_MASK = ITEMS.register("mist_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlot.HEAD,  new Item.Properties().tab(NINJA_ARMOR))
            .setShouldHideNameplate(true));

    public static final RegistryObject<Item> LONELY_MARCH = ITEMS.register("lonely_march", ()
            -> new RecordItem(41, NarutoSounds.LONELY_MARCH,  new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE)));


    // Ninja Masks

    public static final RegistryObject<Item> HEADBAND_BLUE = ITEMS.register("headband_blue", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.HEADBAND, EquipmentSlot.HEAD,  new Item.Properties().tab(HEADBANDS)));

    public static final RegistryObject<Item> HEADBAND_BLACK = ITEMS.register("headband_black", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.HEADBAND, EquipmentSlot.HEAD,  new Item.Properties().tab(HEADBANDS)));

    public static final RegistryObject<Item> HEADBAND_RED = ITEMS.register("headband_red", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.HEADBAND, EquipmentSlot.HEAD,  new Item.Properties().tab(HEADBANDS)));

    public static final RegistryObject<Item> HEADBAND_CUSTARD = ITEMS.register("headband_custard", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.HEADBAND, EquipmentSlot.HEAD,  new Item.Properties().tab(HEADBANDS)));

    // Materials

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
