package com.sekwah.narutomod.network.client;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Packets to send to the client
 */
public class ClientSyncNinjaData {

    public final String uuid;
    public final String url;
    public final boolean isTransparent;

    public ClientSyncNinjaData(String uuid, String url, boolean isTransparent) {
        this.uuid = uuid;
        this.url = url;
        this.isTransparent = isTransparent;
    }

    public static void encode(ClientSyncNinjaData msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeUtf(msg.uuid);
        outBuffer.writeUtf(msg.url);
        outBuffer.writeBoolean(msg.isTransparent);
    }

    public static ClientSyncNinjaData decode(FriendlyByteBuf inBuffer) {

        String uuid = inBuffer.readUtf();
        String url = inBuffer.readUtf();
        boolean isTransparent = inBuffer.readBoolean();

        return new ClientSyncNinjaData(uuid, url, isTransparent);
    }

    public static class Handler {
        public static void handle(ClientSyncNinjaData msg, Supplier<NetworkEvent.Context> ctx) {
            /*ctx.get().enqueueWork(() ->
                    ClientSkinManager.setSkin(UUID.fromString(msg.uuid), msg.url, msg.isTransparent));*/
            ctx.get().setPacketHandled(true);
        }
    }
}
