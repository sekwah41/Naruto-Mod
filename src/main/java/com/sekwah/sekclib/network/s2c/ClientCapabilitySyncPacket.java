package com.sekwah.sekclib.network.s2c;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.CapabilitySyncRegistry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Structure
 * int what player it targets
 * int how many capabilities
 *
 * int capability sync id
 * int how many fields to sync
 *
 * int id for which field on the capability to sync. (this stops types needing to by synced with a registry)
 * (push to the tracker to handle the encoding and decoding)
 */
public class ClientCapabilitySyncPacket {

    private final int playerId;

    private List<CapabilityInfo> capabilityInfoList;

    private class CapabilityInfo {
        public int capabilityId;
        public List<SyncTracker> changedEntries = new ArrayList<>();

        public CapabilityInfo(int capabilityId) {
            this.capabilityId = capabilityId;
        }
    }

    /**
     * To construct the data for serialisation
     * @param player
     * @param forSelf
     */
    public ClientCapabilitySyncPacket(Player player) {

        this.playerId = player.getId();
        capabilityInfoList = new ArrayList<>();

        for (CapabilityEntry capabilityEntry : CapabilitySyncRegistry.getPlayerCapabilities()) {
            player.getCapability(capabilityEntry.getCapability()).ifPresent(data -> {
                for (SyncEntry entry: capabilityEntry.getSyncEntries()) {
                    try {
                        System.out.println(entry.getGetter().invoke(data));
                        // TODO run against setup trackers.
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                        /*try {
                            SekCLib.LOGGER.info("Field data {} {}", entry.getName(), entry.getField().get(data));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }*/
                }
            });
        }
    }


    public ClientCapabilitySyncPacket(int player) {
        // TODO populate data
        this.playerId = 0;
    }

    public static void encode(ClientCapabilitySyncPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(msg.playerId);
    }

    public static ClientCapabilitySyncPacket decode(FriendlyByteBuf inBuffer) {

        int playerId = inBuffer.readInt();

        return null;//new ClientCapabilitySyncPacket(id);
    }

    public static class Handler {
        public static void handle(ClientCapabilitySyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            /*ctx.get().enqueueWork(() ->
                    ClientSkinManager.setSkin(UUID.fromString(msg.uuid), msg.url, msg.isTransparent));*/
            ctx.get().setPacketHandled(true);
        }
    }
}
