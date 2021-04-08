package com.sekwah.narutomod.jutsus;

import com.sekwah.narutomod.NarutoMod;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(NarutoMod.MOD_ID)
@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID)
public class NarutoJutsus {

    public static Jutsu TEST_JUTSU;

    @SubscribeEvent
    public static void registerJutsus(RegistryEvent.Register<Jutsu> event) {

        TEST_JUTSU = new Jutsu();
        event.getRegistry().register(TEST_JUTSU);

    }
}
