package sekwah.mods.narutomod.packets.clientbound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import sekwah.mods.narutomod.packets.ClientPacketHandler;
import sekwah.mods.narutomod.packets.NarutoMessage;

public class ClientJutsuStatsPacket extends NarutoMessage implements IMessageHandler<ClientJutsuStatsPacket, IMessage> {
    public ClientJutsuStatsPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ClientJutsuStatsPacket() {
    }

    public IMessage onMessage(ClientJutsuStatsPacket message, MessageContext ctx) {
        ClientPacketHandler.handleJutsuStats(message.packet);
        return null;
    }
}