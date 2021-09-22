package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.capability.SyncDataCapabilityHandler;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;

import java.util.List;

/**
 * Handle sending the capabilities to users.
 */
public class CapabilityBroadcaster {

    /**
     * Also tick the sync trackers to make sure the data is up to date and flag what should be sent.
     * @param player
     */
    public static void checkPlayerCapData(Player player) {
        player.getCapability(SyncDataCapabilityHandler.SYNC_DATA).ifPresent(syncData -> {
            List<CapabilityTracker> capTrackers = syncData.getCapabilityTrackers();
            for(CapabilityTracker capTracker: capTrackers) {
                Capability<?> cap = capTracker.getCapability();
                List<SyncTracker> syncTrackers = capTracker.getSyncTrackers();
                player.getCapability(cap).ifPresent(data -> {
                    for(SyncTracker syncTracker : syncTrackers) {
                        try {
                            syncTracker.tick(data);
                        } catch (Throwable e) {
                            SekCLib.LOGGER.error("There was a problem updating a sync tracker", e);
                        }
                    }
                });
            }
        });
        // TODO change back, tho to make sure data sends force send always
        sendPlayerCapData(player, true);
    }

    /**
     *
     * @param player - what player to get the data off to check the syncing.
     * @param forceSend - send all fields they should have even if it's not time to.
     */
    public static void sendPlayerCapData(Player player, boolean forceSend) {
        /*player.getCapability(SyncDataCapabilityHandler.SYNC_DATA).ifPresent(syncData -> {
            List<CapabilityTracker> capTrackers = syncData.getCapabilityTrackers();
            for(CapabilityTracker capTracker: capTrackers) {
                Capability<?> cap = capTracker.getCapability();
                List<SyncTracker> syncTrackers = capTracker.getSyncTrackers();
                player.getCapability(cap).ifPresent(data -> {
                    for(SyncTracker syncTracker : syncTrackers) {
                        try {
                            syncTracker.tick(data);
                        } catch (Throwable e) {
                            SekCLib.LOGGER.error(e);
                        }
                    }
                });
            }
            SekCLib.LOGGER.info(syncData);
        });*/

        // TODO check if there is any data to send and if there is none do not send the packet.
    }


    // TODO add a function for creating a sync packet, for self or for others. Then the data can be broadcasted to different targets.
}
