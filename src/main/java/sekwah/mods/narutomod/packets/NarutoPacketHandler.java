package sekwah.mods.narutomod.packets;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

public class NarutoPacketHandler implements ChannelHandler {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub

    }

    // may be deprecated but is forced to be included atm by the channelhandler
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
    }

}
