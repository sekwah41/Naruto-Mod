package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.capabilitysync.capability.SyncDataCapabilityHandler;
import net.minecraft.world.entity.player.Player;

/**
 * Handle sending the capabilities to users.
 */
public class CapabilityBroadcaster {

    public static void checkPlayerCapData(Player player) {
        sendPlayerCapData(player, false);
    }

    /**
     *
     * @param player - what player to get the data off to check the syncing.
     * @param forceSend - send all fields even if they don't wanna update.
     */
    public static void sendPlayerCapData(Player player, boolean forceSend) {
        player.getCapability(SyncDataCapabilityHandler.SYNC_DATA).ifPresent(syncData -> {
        });
    }


    // TODO add a function for creating a sync packet, for self or for others. Then the data can be broadcasted to different targets.
}
