package com.sekwah.narutomod.network;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.network.client.ClientTestPacket;
import com.sekwah.narutomod.network.server.JutsuCastingPacket;
import com.sekwah.narutomod.network.server.ServerTestPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel NARUTO_CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(NarutoMod.MOD_ID, "naruto_data"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    public static void init() {
        // Server to client packs
        NARUTO_CHANNEL.registerMessage(0, ClientTestPacket.class, ClientTestPacket::encode, ClientTestPacket::decode, ClientTestPacket.Handler::handle);

        // Client to server packets
        NARUTO_CHANNEL.registerMessage(100, ServerTestPacket.class, ServerTestPacket::encode, ServerTestPacket::decode, ServerTestPacket.Handler::handle);
        NARUTO_CHANNEL.registerMessage(101, JutsuCastingPacket.class, JutsuCastingPacket::encode, JutsuCastingPacket::decode, JutsuCastingPacket.Handler::handle);
    }


    public static void sendToServer(Object obj) {
        NARUTO_CHANNEL.sendToServer(obj);
    }

    // These are for handling from server side to players.
    public static void sendToPlayer(Object obj, ServerPlayerEntity player) {
        NARUTO_CHANNEL.sendTo(obj, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sentToTrackingAndSelf(Object obj, ServerPlayerEntity player) {
        NARUTO_CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), obj);
    }

    public static void sentToTracking(Object obj, Entity entity) {
        NARUTO_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), obj);
    }

}
