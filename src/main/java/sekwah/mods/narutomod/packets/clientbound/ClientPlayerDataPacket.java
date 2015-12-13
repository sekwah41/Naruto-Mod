package sekwah.mods.narutomod.packets.clientbound;

import sekwah.mods.narutomod.packets.NarutoMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import sekwah.mods.narutomod.packets.ClientPacketHandler;

public class ClientPlayerDataPacket extends NarutoMessage implements IMessageHandler<ClientPlayerDataPacket, IMessage> {
    public ClientPlayerDataPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ClientPlayerDataPacket() {
    }

    public IMessage onMessage(ClientPlayerDataPacket message, MessageContext ctx) {
        ClientPacketHandler.handlePlayerDataPacket(message.packet);
        return null;
    }
}