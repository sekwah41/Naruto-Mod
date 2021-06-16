package com.sekwah.narutomod.item;

import com.sekwah.narutomod.item.armor.NarutoArmorMaterial;
import com.sekwah.narutomod.item.armor.NarutoArmorItem;
import com.sekwah.narutomod.item.weapons.ExplosiveKunaiItem;
import com.sekwah.narutomod.item.weapons.KunaiItem;
import com.sekwah.narutomod.item.weapons.SenbonItem;
import com.sekwah.narutomod.item.weapons.ShurikenItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoItems {

    public static ItemGroup NINJA_WEAPONS = new ItemGroup("narutomod_weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(KUNAI.get());
        }
    };

    public static ItemGroup NINJA_ARMOR = new ItemGroup("narutomod_armor") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(RED_ANBU_MASK.get());
        }
    };

    public static ItemGroup HEADBANDS = new ItemGroup("narutomod_headbands") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BLUE_HEADBAND.get());
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
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlotType.HEAD,  new Item.Properties().tab(NINJA_ARMOR)));

    public static final RegistryObject<Item> YELLOW_ANBU_MASK = ITEMS.register("yellow_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlotType.HEAD,  new Item.Properties().tab(NINJA_ARMOR)));

    public static final RegistryObject<Item> GREEN_ANBU_MASK = ITEMS.register("green_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlotType.HEAD,  new Item.Properties().tab(NINJA_ARMOR)));

    public static final RegistryObject<Item> BLUE_ANBU_MASK = ITEMS.register("blue_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlotType.HEAD,  new Item.Properties().tab(NINJA_ARMOR)));

    public static final RegistryObject<Item> MIST_ANBU_MASK = ITEMS.register("mist_anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlotType.HEAD,  new Item.Properties().tab(NINJA_ARMOR)));




    // Ninja Masks

    public static final RegistryObject<Item> BLUE_HEADBAND = ITEMS.register("blue_headband", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.HEADBAND, EquipmentSlotType.HEAD,  new Item.Properties().tab(HEADBANDS)));

    public static final RegistryObject<Item> BLACK_HEADBAND = ITEMS.register("black_headband", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.HEADBAND, EquipmentSlotType.HEAD,  new Item.Properties().tab(HEADBANDS)));

    public static final RegistryObject<Item> RED_HEADBAND = ITEMS.register("red_headband", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.HEADBAND, EquipmentSlotType.HEAD,  new Item.Properties().tab(HEADBANDS)));

    // Materials

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
