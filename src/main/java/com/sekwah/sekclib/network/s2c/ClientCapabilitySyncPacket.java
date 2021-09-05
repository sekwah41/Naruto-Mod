package com.sekwah.sekclib.network.s2c;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

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
