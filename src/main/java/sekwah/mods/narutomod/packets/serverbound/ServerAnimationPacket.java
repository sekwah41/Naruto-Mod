package sekwah.mods.narutomod.packets.serverbound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import sekwah.mods.narutomod.packets.NarutoMessage;
import sekwah.mods.narutomod.packets.ServerPacketHandler;

public class ServerAnimationPacket extends NarutoMessage implements IMessageHandler<ServerAnimationPacket, IMessage> {
    public ServerAnimationPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ServerAnimationPacket() {
    }

    public IMessage onMessage(ServerAnimationPacket message, MessageContext ctx) {
        ServerPacketHandler.handleAnimationPacket(message.packet, ctx.getServerHandler().playerEntity);
        return null;
    }
}