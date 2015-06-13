package sekwah.mods.narutomod.packets.serverbound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import sekwah.mods.narutomod.packets.NarutoMessage;
import sekwah.mods.narutomod.packets.ServerPacketHandler;

public class ServerParticleEffectPacket extends NarutoMessage implements IMessageHandler<ServerParticleEffectPacket, IMessage> {
    public ServerParticleEffectPacket(byte[] payload) {
        this.packet = payload;
        this.packetLength = payload.length;
    }

    public ServerParticleEffectPacket() {
    }

    public IMessage onMessage(ServerParticleEffectPacket message, MessageContext ctx) {
        ServerPacketHandler.handleParticlePacket(message.packet);
        return null;
    }
}