package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capability.SyncDataCapabilityHandler;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Handle sending the capabilities to users.
 */
public class CapabilityBroadcaster {

    /**
     * Also tick the sync trackers to make sure the data is up to date and flag what should be sent.
     *
     * @param player
     */
    public static void checkPlayerCapData(Player player) {
        player.getCapability(SyncDataCapabilityHandler.SYNC_DATA).ifPresent(syncData -> {
            List<CapabilityTracker> capTrackers = syncData.getCapabilityTrackers();
            for (CapabilityTracker capTracker : capTrackers) {
                Capability<?> cap = capTracker.getCapability();
                player.getCapability(cap).ifPresent(data -> {
                    for (SyncTracker syncTracker : capTracker.getSyncTrackers()) {
                        try {
                            syncTracker.tick(data);
                        } catch (Throwable e) {
                            SekCLib.LOGGER.error("There was a problem updating a sync tracker", e);
                        }
                    }
                });
            }
        });

        broadcastCapChanges(player);
    }


    private static List<CapabilityInfo> collectEntries(Player player) {
        return collectEntries(player, false);
    }

    private static List<CapabilityInfo> collectEntries(Player player, boolean returnAll) {
        List<CapabilityInfo> capInfoList = new ArrayList<>();
        player.getCapability(SyncDataCapabilityHandler.SYNC_DATA).ifPresent(syncData -> {
            List<CapabilityTracker> capTrackers = syncData.getCapabilityTrackers();
            for (CapabilityTracker capTracker : capTrackers) {
                CapabilityInfo capInfo = new CapabilityInfo(SekCLibRegistries.capabilityRegistry.getID(capTracker.getCapabilityEntry()));
                for (SyncTracker syncTracker : capTracker.getSyncTrackers()) {
                    if (returnAll || syncTracker.isMarkedForSend()) {
                        SyncEntry syncEntry = syncTracker.getSyncEntry();
                        if(syncEntry.isSyncGlobally()) {
                            capInfo.changedEntries.add(syncTracker);
                        } else {
                            capInfo.changedPrivateEntries.add(syncTracker);
                        }
                    }
                }
                if(!capInfo.changedEntries.isEmpty() || !capInfo.changedPrivateEntries.isEmpty()) {
                    capInfoList.add(capInfo);
                }
            }
        });
        return capInfoList;
    }


    /**
     * Send all the data about this player to another.
     *
     * @param tracked
     * @param reciever
     */
    public static void broadcastCapability(Player tracked, Player reciever) {

    }

    /**
     * Collect player
     *
     * @param player - what player to get the data off to check the syncing.
     */
    public static void broadcastCapChanges(Player player) {

        List<CapabilityInfo> dataToSend = collectEntries(player);



    }
}
