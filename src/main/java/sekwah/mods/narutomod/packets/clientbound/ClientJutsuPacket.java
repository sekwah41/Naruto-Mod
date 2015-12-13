package sekwah.mods.narutomod.packets.clientbound;

import sekwah.mods.narutomod.packets.ClientPacketHandler;
import sekwah.mods.narutomod.packets.NarutoMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientJutsuPacket extends NarutoMessage implements IMessageHandler<ClientJutsuPacket, IMessage> {
    public ClientJutsuPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ClientJutsuPacket() {
    }

    public IMessage onMessage(ClientJutsuPacket message, MessageContext ctx) {
        ClientPacketHandler.handleJutsuData(message.packet);
        return null;
    }
}