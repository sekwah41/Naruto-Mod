package com.sekwah.narutomod.client;

import com.sekwah.narutomod.NarutoMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NarutoClientEvents {

    /**
     * {@link net.minecraftforge.client.event.RenderPlayerEvent}
     * @param event
     */
    @SubscribeEvent
    public static void playerRenderEvent(RenderPlayerEvent.Pre event) {
        event.setCanceled(true);
    }

}
