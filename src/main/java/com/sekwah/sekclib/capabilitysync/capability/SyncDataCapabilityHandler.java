package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerFactory;
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


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player)
            if (!event.getObject().level.isClientSide()) {
                SyncData syncData = new SyncData();

                // TODO handle creating the capability trackers.

                // TODO loop over this rather than the created capabilities

                Map<ResourceLocation, ICapabilityProvider> registeredCaps = event.getCapabilities();


                /*for (CapabilityEntry capabilityEntry : CapabilitySyncRegistry.getPlayerCapabilities()) {
                    player.getCapability(capabilityEntry.getCapability()).ifPresent(data -> {
                        for (SyncEntry entry : capabilityEntry.getSyncEntries()) {
                            SyncTrackerFactory tracker = CapabilitySyncRegistry.getTrackerFactory(entry.getField().getType());
                            System.out.println(tracker);
                        }
                    });
                }*/

                event.addCapability(new ResourceLocation(SekCLib.MOD_ID, "sync_data"), syncData);
            }
    }
}
