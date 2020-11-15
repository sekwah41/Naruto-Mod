package com.sekwah.narutomod.client;

import com.sekwah.narutomod.NarutoMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoClientModEvents {

    @SubscribeEvent
    public static void colorHandlerEvent(ColorHandlerEvent.Item event) {
        // Add color handlers here
    }

}
