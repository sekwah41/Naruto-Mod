package com.sekwah.narutomod.network.server;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

/**
 * Packets to send to teh server
 */
public class ServerTestPacket {

    public static void encode(ServerTestPacket msg, PacketBuffer outBuffer) {
    }

    public static ServerTestPacket decode(PacketBuffer inBuffer) {
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
