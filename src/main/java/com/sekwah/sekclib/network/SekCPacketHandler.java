package com.sekwah.sekclib.network;
import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.network.s2c.ClientCapabilitySyncPacket;
import com.sekwah.sekclib.network.s2c.ClientVelocityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class SekCPacketHandler {

    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel SYNC_CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(SekCLib.MOD_ID, "sync_channel"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    public static void init() {
        SYNC_CHANNEL.registerMessage(0, ClientCapabilitySyncPacket.class, ClientCapabilitySyncPacket::encode, ClientCapabilitySyncPacket::decode, ClientCapabilitySyncPacket.Handler::handle);
        SYNC_CHANNEL.registerMessage(1, ClientVelocityPacket.class, ClientVelocityPacket::encode, ClientVelocityPacket::decode, ClientVelocityPacket.Handler::handle);
    }

    public static void sendToPlayer(Object obj, ServerPlayer player) {
        SYNC_CHANNEL.sendTo(obj, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToAll(Object obj) {
        SYNC_CHANNEL.send(PacketDistributor.ALL.noArg(), obj);
    }

    public static void sendToTracking(Object obj, ServerPlayer player) {
        SYNC_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), obj);
    }

}
