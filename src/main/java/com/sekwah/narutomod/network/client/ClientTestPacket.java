package com.sekwah.narutomod.network.client;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Packets to send to the client
 */
public class ClientTestPacket {

    public final String uuid;
    public final String url;
    public final boolean isTransparent;

    public ClientTestPacket(String uuid, String url, boolean isTransparent) {
        this.uuid = uuid;
        this.url = url;
        this.isTransparent = isTransparent;
    }

    public static void encode(ClientTestPacket msg, PacketBuffer outBuffer) {
        outBuffer.writeString(msg.uuid);
        outBuffer.writeString(msg.url);
        outBuffer.writeBoolean(msg.isTransparent);
    }

    public static ClientTestPacket decode(PacketBuffer inBuffer) {

        String uuid = inBuffer.readString();
        String url = inBuffer.readString();
        boolean isTransparent = inBuffer.readBoolean();

        return new ClientTestPacket(uuid, url, isTransparent);
    }

    public static class Handler {
        public static void handle(ClientTestPacket msg, Supplier<NetworkEvent.Context> ctx) {
            /*ctx.get().enqueueWork(() ->
                    ClientSkinManager.setSkin(UUID.fromString(msg.uuid), msg.url, msg.isTransparent));*/
            ctx.get().setPacketHandled(true);
        }
    }
}
