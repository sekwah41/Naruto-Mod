package com.sekwah.sekclib.network.s2c;

import com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster.CapabilityInfo;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

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
    private List<CapabilityInfo> capabilityData;
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
        this.capabilityData = capabilityData;
        this.includePrivate = includePrivate;
    }


    public ClientCapabilitySyncPacket(int player) {
        // TODO populate data
        this.playerId = 0;
    }

    public static void encode(ClientCapabilitySyncPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(msg.playerId);
        outBuffer.writeInt(msg.capabilityData.size());
        for (CapabilityInfo capInfo :
                msg.capabilityData) {
            if (!msg.includePrivate && !capInfo.changedEntries.isEmpty()) {
                
            }
        }
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
