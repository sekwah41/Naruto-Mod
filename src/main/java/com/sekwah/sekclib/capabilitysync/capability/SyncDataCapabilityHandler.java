package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster.CapabilityBroadcaster;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = SekCLib.MOD_ID)
public class SyncDataCapabilityHandler {

    @CapabilityInject(ISyncData.class)
    public static final Capability<ISyncData> SYNC_DATA = null;

    public static void createSyncData(AttachCapabilitiesEvent<Entity> event) {
        SyncData syncData = new SyncData();

        Map<ResourceLocation, ICapabilityProvider> registeredCaps = event.getCapabilities();


        for (Map.Entry<ResourceLocation, ICapabilityProvider> capEntry : registeredCaps.entrySet()) {
            for (CapabilityEntry capabilityEntry : CapabilitySyncRegistry.getPlayerCapabilities()) {
                Class<? extends ICapabilityProvider> capClass = capEntry.getValue().getClass();
                if (capabilityEntry.getCapabilityClass() == capClass) {
                    CapabilityTracker capabilityTracker = new CapabilityTracker(capabilityEntry);
                    for (SyncEntry entry : capabilityEntry.getSyncEntries()) {
                        SyncTracker syncTracker = new SyncTracker(entry);
                        capabilityTracker.addSyncTracker(syncTracker);
                    }
                    syncData.addCapabilityTracker(capabilityTracker);

                    break;
                }
            }
        }

        event.addCapability(new ResourceLocation(SekCLib.MOD_ID, "sync_data"), syncData);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player && !event.getObject().level.isClientSide()) {
            createSyncData(event);
        }
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        if(event.side.isServer() && event.player instanceof ServerPlayer serverPlayer) {
            CapabilityBroadcaster.checkPlayerCapData(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void dimensionChange(EntityJoinWorldEvent event) {
        // TODO Handle syncing the players own data
        if(event.getEntity() instanceof Player) {
            System.out.println("JOINED WORLD");
        }
    }

    @SubscribeEvent
    public static void dimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        // TODO Handle syncing the players own data
        System.out.println("DIMENSION");
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
