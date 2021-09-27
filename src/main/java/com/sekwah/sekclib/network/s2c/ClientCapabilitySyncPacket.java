package com.sekwah.sekclib.network.s2c;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster.CapabilityInfo;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Structure
 * int what player it targets
 * int how many capabilities
 * <p>
 * int capability sync id
 * int how many fields to sync
 * <p>
 * int id for which field on the capability to sync. (this stops types needing to by synced with a registry)
 * (push to the tracker to handle the encoding and decoding)
 */
public class ClientCapabilitySyncPacket {

    private final int playerId;
    public List<CapabilityInfo> capabilityData;
    private boolean includePrivate;

    /**
     * To construct the data for serialisation
     *
     * @param player
     * @param capabilityData
     * @param includePrivate - should include non global tags
     */
    public ClientCapabilitySyncPacket(Player player, List<CapabilityInfo> capabilityData, boolean includePrivate) {
        this.playerId = player.getId();
        this.capabilityData = new ArrayList<>(capabilityData);
        this.includePrivate = includePrivate;
        if (!includePrivate) {
            this.capabilityData.removeIf(data -> data.changedEntries.isEmpty());
        }
    }

    private ClientCapabilitySyncPacket(int player, List<CapabilityInfo> capabilityData) {
        this.playerId = player;
        this.capabilityData = capabilityData;
    }

    public static void encodeSyncTrackers(List<SyncTracker> trackers, FriendlyByteBuf outBuffer) {
        for (SyncTracker tracker: trackers) {
            outBuffer.writeByte(tracker.getSyncEntry().getTrackerId());
            tracker.encode(outBuffer);
            // TODO lookup tracker encode logic
        }
    }

    public static List<SyncTracker> decodeSyncTrackers(FriendlyByteBuf inBuffer) {
        int trackerCount = inBuffer.readInt();
        for (int i = 0; i < trackerCount; i++) {
            int trackerId = inBuffer.readByte();

            SekCLib.LOGGER.info("Received Tracker Id {}", trackerId);
            // TODO lookup sync tracker and find decode logic
        }
        return null;
    }

    public static void encode(ClientCapabilitySyncPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(msg.playerId);
        outBuffer.writeInt(msg.capabilityData.size());
        for (CapabilityInfo capInfo : msg.capabilityData) {
            outBuffer.writeInt(capInfo.capabilityId);
            int count = capInfo.changedEntries.size();
            if (msg.includePrivate) count += capInfo.changedPrivateEntries.size();
            outBuffer.writeInt(count);

            encodeSyncTrackers(capInfo.changedEntries, outBuffer);
            if (msg.includePrivate) encodeSyncTrackers(capInfo.changedPrivateEntries, outBuffer);
        }
    }

    public static ClientCapabilitySyncPacket decode(FriendlyByteBuf inBuffer) {

        int playerId = inBuffer.readInt();
        int capCount = inBuffer.readInt();
        for (int i = 0; i < capCount; i++) {
            int capId = inBuffer.readInt();
            List<SyncTracker> syncTrackers = decodeSyncTrackers(inBuffer);
            SekCLib.LOGGER.info("Received Id {} syncTrackers {}", capId, syncTrackers);
        }

        return new ClientCapabilitySyncPacket(playerId, null);
    }

    public static class Handler {
        public static void handle(ClientCapabilitySyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            /*ctx.get().enqueueWork(() ->
                    ClientSkinManager.setSkin(UUID.fromString(msg.uuid), msg.url, msg.isTransparent));*/
            NetworkEvent.Context context = ctx.get();
            context.enqueueWork(() ->
                    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                        ClientLevel level = Minecraft.getInstance().level;
                        Entity entity = level.getEntity(msg.playerId);
                        SekCLib.LOGGER.info(entity);
                    }));
            context.setPacketHandled(true);
        }
    }
}
