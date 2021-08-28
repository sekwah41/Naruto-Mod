package com.sekwah.sekclib.event;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilitySyncRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SekCLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntity() instanceof Player player) {
            for (Capability<?> cap : CapabilitySyncRegistry.getPlayerCapabilities()) {
                player.getCapability(cap).ifPresent(data -> {
                    SekCLib.LOGGER.info(data);
                });
            }
        }
    }

}
