package sekwah.mods.narutomod.packets.clientbound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import sekwah.mods.narutomod.packets.ClientPacketHandler;
import sekwah.mods.narutomod.packets.NarutoMessage;

public class ClientMaxStatsPacket extends NarutoMessage implements IMessageHandler<ClientMaxStatsPacket, IMessage> {
    public ClientMaxStatsPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ClientMaxStatsPacket() {
    }

    public IMessage onMessage(ClientMaxStatsPacket message, MessageContext ctx) {
        ClientPacketHandler.handleMaxStats(message.packet);
        return null;
    }
}