package com.sekwah.mods.narutomod.packets.serverbound;

import com.sekwah.mods.narutomod.packets.NarutoMessage;
import com.sekwah.mods.narutomod.packets.ServerPacketHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ServerPlayerDataPacket extends NarutoMessage implements IMessageHandler<ServerPlayerDataPacket, IMessage> {
    public ServerPlayerDataPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ServerPlayerDataPacket() {
    }

    public IMessage onMessage(ServerPlayerDataPacket message, MessageContext ctx) {
        ServerPacketHandler.handlePlayerDataPacket(message.packet);
        return null;
    }
}