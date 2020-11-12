package com.sekwah.narutomod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoItems {

    public static ItemGroup NINJA_WEAPONS = new ItemGroup("narutomod_weapons") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(KUNAI.get());
        }
    };

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Item> KUNAI = ITEMS.register("kunai", ()
            -> new KunaiItem(new Item.Properties().group(NINJA_WEAPONS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
