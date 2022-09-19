package com.sekwah.narutomod.network.c2s;

import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.gameevents.NarutoGameEvents;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Tells the server that the user is entering jutsu keys
 */
public class ServerToggleNinjaPacket {

    private final boolean enableNinja;

    public ServerToggleNinjaPacket(boolean jutsuKey) {
        this.enableNinja = jutsuKey;
    }

    public static void encode(ServerToggleNinjaPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeBoolean(msg.enableNinja);
    }

    public static ServerToggleNinjaPacket decode(FriendlyByteBuf inBuffer) {
        boolean enableNinja = inBuffer.readBoolean();
        return new ServerToggleNinjaPacket(enableNinja);
    }

    public static class Handler {
        public static void handle(ServerToggleNinjaPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if(player != null) {
                    player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {
                       ninjaData.setIsNinja(msg.enableNinja);
                    });
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
