package com.sekwah.narutomod.item;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
