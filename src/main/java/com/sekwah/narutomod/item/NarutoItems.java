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
            return new ItemStack(ANBU_MASK.get());
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

    public static final RegistryObject<Item> ANBU_MASK = ITEMS.register("anbu_mask", ()
            -> new NarutoArmorItem(NarutoArmorMaterial.ANBU_MAT, EquipmentSlotType.HEAD,  new Item.Properties().tab(NINJA_ARMOR)));

    // Materials

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
