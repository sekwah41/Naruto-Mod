package com.sekwah.narutomod.network.server;

import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Tells the server that the user is entering jutsu keys
 */
public class JutsuCastingPacket {

    private final int jutsuKey;

    public JutsuCastingPacket(int jutsuKey) {
        this.jutsuKey = jutsuKey;
    }

    public static void encode(JutsuCastingPacket msg, PacketBuffer outBuffer) {
        outBuffer.writeInt(msg.jutsuKey);
    }

    public static JutsuCastingPacket decode(PacketBuffer inBuffer) {
        int jutsuKey = inBuffer.readInt();
        return new JutsuCastingPacket(jutsuKey);
    }

    public static class Handler {
        public static void handle(JutsuCastingPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity player = ctx.get().getSender();
                if(player != null) {
                    SoundEvent playSound;
                    switch (msg.jutsuKey) {
                        case 1:
                            playSound = NarutoSounds.SEAL_A.get();
                            break;
                        case 2:
                            playSound = NarutoSounds.SEAL_B.get();
                            break;
                        case 3:
                            playSound = NarutoSounds.SEAL_C.get();
                            break;
                        default:
                            playSound = null;
                            break;
                    }
                    if(playSound != null) {
                        player.getCommandSenderWorld().playSound(null,
                                player.getX(), player.getY(), player.getZ(),
                                playSound, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    }
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
