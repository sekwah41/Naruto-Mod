package com.sekwah.sekclib.network.s2c;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Structure
 * int how many capabilities
 *
 * int capability sync id
 * int how many fields to sync
 *
 * int id for which field on the capability to sync. (this stops types needing to by synced with a registry)
 * (push to the tracker to handle the encoding and decoding)
 */
public class ClientCapabilitySyncPacket {

    private final int id;

    public ClientCapabilitySyncPacket(int entityId) {
        this.id = entityId;
    }

    public static void encode(ClientCapabilitySyncPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(msg.id);
    }

    public static ClientCapabilitySyncPacket decode(FriendlyByteBuf inBuffer) {

        int id = inBuffer.readInt();

        return new ClientCapabilitySyncPacket(id);
    }

    public static class Handler {
        public static void handle(ClientCapabilitySyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            /*ctx.get().enqueueWork(() ->
                    ClientSkinManager.setSkin(UUID.fromString(msg.uuid), msg.url, msg.isTransparent));*/
            ctx.get().setPacketHandled(true);
        }
    }
}
