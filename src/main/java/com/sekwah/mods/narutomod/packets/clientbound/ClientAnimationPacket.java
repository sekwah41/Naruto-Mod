package com.sekwah.mods.narutomod.packets.clientbound;

import com.sekwah.mods.narutomod.packets.ClientPacketHandler;
import com.sekwah.mods.narutomod.packets.NarutoMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientAnimationPacket extends NarutoMessage implements IMessageHandler<ClientAnimationPacket, IMessage> {
    public ClientAnimationPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ClientAnimationPacket() {
    }

    public IMessage onMessage(ClientAnimationPacket message, MessageContext ctx) {
        ClientPacketHandler.handleAnimationData(message.packet);
        return null;
    }
}