package sekwah.mods.narutomod.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class NarutoMessage implements IMessage {

    public byte[] packet;
    public int packetLength;

    @Override
    public void fromBytes(ByteBuf buf) {
        this.packetLength = buf.readInt();
        this.packet = buf.readBytes(this.packetLength).array();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.packet.length);
        buf.writeBytes(this.packet);
    }

}
