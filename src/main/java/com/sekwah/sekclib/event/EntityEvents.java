package com.sekwah.sekclib.event;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capability.SyncDataCapabilityHandler;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SekCLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEvents {

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        if(event.side.isServer()) {
            Player player = event.player;
            player.getCapability(SyncDataCapabilityHandler.SYNC_DATA).ifPresent(syncData -> {
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
            });
        }
    }

    @SubscribeEvent
    public static void playerJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof Player player) {

        }
    }

    @SubscribeEvent
    public static void dimensionChange(EntityJoinWorldEvent event) {
        // TODO Handle syncing the players own data
    }

    @SubscribeEvent
    public static void dimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        // TODO Handle syncing the players own data
    }

    /**
     * Server side event
     * @param event
     */
    @SubscribeEvent
    public static void playerTracking(PlayerEvent.StartTracking event) {
        if(event.getTarget() instanceof Player) {
            SekCLib.LOGGER.info("HELLO");
        }
        // TODO triggers on any entity. trigger on players send over the original data
    }

    /**
     * Server side event
     * @param event
     */
    @SubscribeEvent
    public static void playerStopTracking(PlayerEvent.StopTracking event) {
        if(event.getTarget() instanceof Player) {
            SekCLib.LOGGER.info("STOP TRACKING");
        }
        // TODO trigger on players send over the original data
    }

}
