package com.sekwah.narutomod.network;

import com.sekwah.narutomod.NarutoMod;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import com.sekwah.narutomod.network.client.ClientTestPacket;
import com.sekwah.narutomod.network.server.ServerTestPacket;

public class PacketHandler {

    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel NARUTO_CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(NarutoMod.MOD_ID, "naruto_data"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    public static void init() {
        NARUTO_CHANNEL.registerMessage(0, ClientTestPacket.class, ClientTestPacket::encode, ClientTestPacket::decode, ClientTestPacket.Handler::handle);
        NARUTO_CHANNEL.registerMessage(100, ServerTestPacket.class, ServerTestPacket::encode, ServerTestPacket::decode, ServerTestPacket.Handler::handle);
    }

    public static void sendToPlayer(Object obj, ServerPlayerEntity player) {
        NARUTO_CHANNEL.sendTo(obj, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }

}
