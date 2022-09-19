package com.sekwah.narutomod.network;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.network.c2s.*;
import com.sekwah.narutomod.network.s2c.ClientTestPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger("NarutoMod:PacketHandler");

    /**
     * Forces the mod to have the same version as the server.
     */
    public static final String PROTOCOL_VERSION = ModList.get().getModFileById(NarutoMod.MOD_ID).versionString();

    public static final SimpleChannel NARUTO_CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(NarutoMod.MOD_ID, "naruto_data"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(version -> {
                if (version.equals(PROTOCOL_VERSION)) {
                    return true;
                } else {
                    LOGGER.error("Client attempted to connect with a different version of the mod. Client Version: " + PROTOCOL_VERSION + " Server Version: " + version);
                    return false;
                }
            })
            .serverAcceptedVersions(version -> {
                if (version.equals(PROTOCOL_VERSION)) {
                    return true;
                } else {
                    LOGGER.error("Client attempted to connect with a different version of the mod. Server Version: " + PROTOCOL_VERSION + " Client Version: " + version);
                    return false;
                }
            })
            .simpleChannel();

    public static void init() {
        // Server to client packs
        NARUTO_CHANNEL.registerMessage(getPacketID(), ClientTestPacket.class, ClientTestPacket::encode, ClientTestPacket::decode, ClientTestPacket.Handler::handle);

        // Client to server packets
        NARUTO_CHANNEL.registerMessage(getPacketID(), ServerJutsuCastingPacket.class, ServerJutsuCastingPacket::encode, ServerJutsuCastingPacket::decode, ServerJutsuCastingPacket.Handler::handle);
        NARUTO_CHANNEL.registerMessage(getPacketID(), ServerAbilityActivatePacket.class, ServerAbilityActivatePacket::encode, ServerAbilityActivatePacket::decode, ServerAbilityActivatePacket.Handler::handle);
        NARUTO_CHANNEL.registerMessage(getPacketID(), ServerAbilityChannelPacket.class, ServerAbilityChannelPacket::encode, ServerAbilityChannelPacket::decode, ServerAbilityChannelPacket.Handler::handle);
        NARUTO_CHANNEL.registerMessage(getPacketID(), ServerToggleNinjaPacket.class, ServerToggleNinjaPacket::encode, ServerToggleNinjaPacket::decode, ServerToggleNinjaPacket.Handler::handle);
    }

    private static int packetId = 0;
    /**
     * When called grabs a new packet id and increments.
     *
     * Only really usable because we force the mods to have to be the same version via PROTOCOL_VERSION
     * @return
     */
    private static int getPacketID() {
        return packetId++;
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
