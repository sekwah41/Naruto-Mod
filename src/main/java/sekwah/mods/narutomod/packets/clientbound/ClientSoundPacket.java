package sekwah.mods.narutomod.packets.clientbound;

import sekwah.mods.narutomod.packets.NarutoMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import sekwah.mods.narutomod.packets.ClientPacketHandler;

public class ClientSoundPacket extends NarutoMessage implements IMessageHandler<ClientSoundPacket, IMessage> {
    public ClientSoundPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ClientSoundPacket() {
    }

    public IMessage onMessage(ClientSoundPacket message, MessageContext ctx) {
        ClientPacketHandler.handleSoundData(message.packet);
        return null;
    }
}