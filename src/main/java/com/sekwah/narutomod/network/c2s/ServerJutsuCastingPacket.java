package com.sekwah.narutomod.network.c2s;

import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.gameevents.NarutoGameEvents;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Tells the server that the user is entering jutsu keys
 */
public class ServerJutsuCastingPacket {

    private final int jutsuKey;

    public ServerJutsuCastingPacket(int jutsuKey) {
        this.jutsuKey = jutsuKey;
    }

    public static void encode(ServerJutsuCastingPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(msg.jutsuKey);
    }

    public static ServerJutsuCastingPacket decode(FriendlyByteBuf inBuffer) {
        int jutsuKey = inBuffer.readInt();
        return new ServerJutsuCastingPacket(jutsuKey);
    }

    public static class Handler {
        public static void handle(ServerJutsuCastingPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if(player != null) {
                    player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {
                        if (!ninjaData.isNinjaModeEnabled()) {
                            player.displayClientMessage(Component.translatable("jutsu.not_a_ninja").withStyle(ChatFormatting.RED), true);
                            return;
                        }
                        if(player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR) {
                            return;
                        }
                        SoundEvent playSound = switch (msg.jutsuKey) {
                            case 1 -> NarutoSounds.SEAL_A.get();
                            case 2 -> NarutoSounds.SEAL_B.get();
                            case 3 -> NarutoSounds.SEAL_C.get();
                            default -> null;
                        };
                        if(playSound != null) {
                            player.getCommandSenderWorld().playSound(null,
                                    player.getX(), player.getY(), player.getZ(),
                                    playSound, SoundSource.PLAYERS, 1.0f, 1.0f);
                            player.getLevel().gameEvent(player, NarutoGameEvents.JUTSU_CASTING.get(), player.position().add(0, player.getEyeHeight() * 0.7, 0));
                        }
                    });
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
