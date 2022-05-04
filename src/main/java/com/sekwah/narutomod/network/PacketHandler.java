package com.sekwah.narutomod.network;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.network.c2s.ServerAbilityActivatePacket;
import com.sekwah.narutomod.network.c2s.ServerAbilityChannelPacket;
import com.sekwah.narutomod.network.s2c.ClientTestPacket;
import com.sekwah.narutomod.network.c2s.ServerJutsuCastingPacket;
import com.sekwah.narutomod.network.c2s.ServerTestPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {

    /**
     * Forces the mod to have the same version as the server.
     */
    public static final String PROTOCOL_VERSION = ModList.get().getModFileById(NarutoMod.MOD_ID).versionString();

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
        NARUTO_CHANNEL.registerMessage(101, ServerJutsuCastingPacket.class, ServerJutsuCastingPacket::encode, ServerJutsuCastingPacket::decode, ServerJutsuCastingPacket.Handler::handle);
        NARUTO_CHANNEL.registerMessage(102, ServerAbilityActivatePacket.class, ServerAbilityActivatePacket::encode, ServerAbilityActivatePacket::decode, ServerAbilityActivatePacket.Handler::handle);
        NARUTO_CHANNEL.registerMessage(103, ServerAbilityChannelPacket.class, ServerAbilityChannelPacket::encode, ServerAbilityChannelPacket::decode, ServerAbilityChannelPacket.Handler::handle);
    }


    public static void sendToServer(Object obj) {
        NARUTO_CHANNEL.sendToServer(obj);
    }

    // These are for handling from server side to players.
    public static void sendToPlayer(Object obj, ServerPlayer player) {
        NARUTO_CHANNEL.sendTo(obj, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sentToTrackingAndSelf(Object obj, ServerPlayer player) {
        NARUTO_CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), obj);
    }

    public static void sentToTracking(Object obj, Entity entity) {
        NARUTO_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), obj);
    }

}
