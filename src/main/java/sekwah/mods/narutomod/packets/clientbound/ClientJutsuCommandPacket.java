package sekwah.mods.narutomod.packets.clientbound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import sekwah.mods.narutomod.client.JutsuClient;
import sekwah.mods.narutomod.packets.NarutoMessage;

public class ClientJutsuCommandPacket extends NarutoMessage implements IMessageHandler<ClientJutsuCommandPacket, IMessage> {

    private int combination;

    /**
     * workaround for remotely activating a jutsu on the client
     */
    public ClientJutsuCommandPacket(int combination) {
        this.combination = combination;
    }

    @SuppressWarnings("unused")
    public ClientJutsuCommandPacket() {
        //NO-OP
    }

    @Override
    public IMessage onMessage(ClientJutsuCommandPacket message, MessageContext ctx) {
        if(message.combination > 0) {
            JutsuClient.executeRemote(message.combination);
        }
        else JutsuClient.displayJutsuList();
        return null;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.combination = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.combination);
    }
}
