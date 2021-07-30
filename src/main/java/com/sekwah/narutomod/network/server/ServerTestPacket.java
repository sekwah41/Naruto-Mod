package com.sekwah.narutomod.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Packets to send to teh server
 */
public class ServerTestPacket {

    public static void encode(ServerTestPacket msg, FriendlyByteBuf outBuffer) {
    }

    public static ServerTestPacket decode(FriendlyByteBuf inBuffer) {
        return new ServerTestPacket();
    }

    public static class Handler {
        public static void handle(ServerTestPacket msg, Supplier<NetworkEvent.Context> ctx) {
            /*ctx.get().enqueueWork(() ->
                    CustomSkinManager.sendAllToPlayer(ctx.get().getSender(), false));*/
            ctx.get().setPacketHandled(true);
        }
    }
}
