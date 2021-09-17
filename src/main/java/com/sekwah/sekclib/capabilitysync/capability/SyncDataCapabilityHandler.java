package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
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

        checkCapability:
        for (Map.Entry<ResourceLocation, ICapabilityProvider> capEntry : registeredCaps.entrySet()) {
            for (CapabilityEntry capabilityEntry : CapabilitySyncRegistry.getPlayerCapabilities()) {
                Class<? extends ICapabilityProvider> capClass = capEntry.getValue().getClass();
                if (capabilityEntry.getCapabilityClass() == capClass) {
                    CapabilityTracker capabilityTracker = new CapabilityTracker();
                    for (SyncEntry entry : capabilityEntry.getSyncEntries()) {
                        SyncTracker syncTracker = CapabilitySyncRegistry.createTracker(entry);
                        capabilityTracker.addSyncTracker(syncTracker);

                        // TODO possibly trigger trackers so they can get their initial values
                    }
                    syncData.addCapabilityTracker(capabilityTracker);

                    continue checkCapability;
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
}
