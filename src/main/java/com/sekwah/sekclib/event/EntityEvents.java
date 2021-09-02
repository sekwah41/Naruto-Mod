package com.sekwah.sekclib.event;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.api.capability.CapabilitySyncRegistry;
import com.sekwah.sekclib.capability.SyncData;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SekCLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        if(event.side.isServer()) {
            Player player = event.player;
            for (CapabilityEntry capabilityEntry : CapabilitySyncRegistry.getPlayerCapabilities()) {
                player.getCapability(capabilityEntry.getCapability()).ifPresent(data -> {
                    for (SyncEntry entry: capabilityEntry.getSyncEntries()) {
                        /*try {
                            SekCLib.LOGGER.info("Field data {} {}", entry.getName(), entry.getField().get(data));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }*/
                    }
                });
            }
        }
    }

    /**
     * Server side event
     * @param event
     */
    @SubscribeEvent
    public static void playerTracking(PlayerEvent.StartTracking event) {
        // TODO triggers on any entity. trigger on players send over the original data
    }

    /**
     * Server side event
     * @param event
     */
    @SubscribeEvent
    public static void playerStopTracking(PlayerEvent.StopTracking event) {
        // TODO trigger on players send over the original data
    }

}
