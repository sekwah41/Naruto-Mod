package sekwah.mods.narutomod.packets.serverbound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import sekwah.mods.narutomod.packets.NarutoMessage;
import sekwah.mods.narutomod.packets.ServerPacketHandler;

public class ServerEyePacket extends NarutoMessage implements IMessageHandler<ServerEyePacket, IMessage> {
    public ServerEyePacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ServerEyePacket() {
    }

    public IMessage onMessage(ServerEyePacket message, MessageContext ctx) {
        ServerPacketHandler.handleEyePacket(message.packet, ctx.getServerHandler().playerEntity);
        return null;
    }
}