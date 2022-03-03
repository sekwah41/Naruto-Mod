package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.capability.SyncDataCapabilityHandler;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import com.sekwah.sekclib.network.SekCPacketHandler;
import com.sekwah.sekclib.network.s2c.ClientCapabilitySyncPacket;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
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
    public static void checkCapData(LivingEntity player) {
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

        broadcastCapChanges(player, false);
    }

    private static List<CapabilityInfo> collectEntries(LivingEntity entity, boolean returnAll) {
        List<CapabilityInfo> capInfoList = new ArrayList<>();
        entity.getCapability(SyncDataCapabilityHandler.SYNC_DATA).ifPresent(syncData -> {
            List<CapabilityTracker> capTrackers = syncData.getCapabilityTrackers();
            for (CapabilityTracker capTracker : capTrackers) {
                CapabilityEntry capEntry = capTracker.getCapabilityEntry();
                CapabilityInfo capInfo = new CapabilityInfo(SekCLibRegistries.CAPABILITY_REGISTRY.getID(capEntry), capEntry);
                for (SyncTracker syncTracker : capTracker.getSyncTrackers()) {
                    if ((returnAll || syncTracker.isMarkedForSend())) {
                        if(syncTracker.getSyncEntry().isSyncGlobally()) {
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
     * @param entity - what player to get the data off to check the syncing.
     */
    public static void broadcastCapChanges(LivingEntity entity, boolean sendAll) {

        List<CapabilityInfo> dataToSend = collectEntries(entity, sendAll);

        if(dataToSend.isEmpty()) {
            return;
        }

        if(entity instanceof ServerPlayer serverPlayer) { {
            ClientCapabilitySyncPacket selfData = new ClientCapabilitySyncPacket(entity, dataToSend, true);
            if(!selfData.capabilityData.isEmpty()) {
                SekCPacketHandler.sendToPlayer(selfData, serverPlayer);
            }
        }}

        ClientCapabilitySyncPacket forOthers = new ClientCapabilitySyncPacket(entity, dataToSend, false);
        if(!forOthers.capabilityData.isEmpty()) {
            SekCPacketHandler.sendToTracking(forOthers, entity);
        }

    }

    public static void broadcastCapToPlayer(LivingEntity entity, ServerPlayer player) {
        List<CapabilityInfo> dataToSend = collectEntries(entity, true);

        if(dataToSend.isEmpty()) {
            return;
        }
        ClientCapabilitySyncPacket forOthers = new ClientCapabilitySyncPacket(entity, dataToSend, false);
        if(!forOthers.capabilityData.isEmpty()) {
            SekCPacketHandler.sendToPlayer(forOthers, player);
        }
    }
}
