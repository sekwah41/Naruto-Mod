package com.sekwah.sekclib.network.s2c;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Tells the client that they should be moving and at what velocity
 */
public class ClientVelocityPacket {

    private final int xa;
    private final int ya;
    private final int za;

    public ClientVelocityPacket(int x, int y, int z) {
        this.xa = x;
        this.ya = y;
        this.za = z;
    }

    public ClientVelocityPacket(Vec3 velocity) {
        double maxVelocity = 3.9D;
        double d1 = Mth.clamp(velocity.x, -maxVelocity, maxVelocity);
        double d2 = Mth.clamp(velocity.y, -maxVelocity, maxVelocity);
        double d3 = Mth.clamp(velocity.z, -maxVelocity, maxVelocity);
        this.xa = (int)(d1 * 8000.0D);
        this.ya = (int)(d2 * 8000.0D);
        this.za = (int)(d3 * 8000.0D);
    }

    public static void encode(ClientVelocityPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeShort(msg.xa);
        outBuffer.writeShort(msg.ya);
        outBuffer.writeShort(msg.za);
    }

    public static ClientVelocityPacket decode(FriendlyByteBuf inBuffer) {
        short x = inBuffer.readShort();
        short y = inBuffer.readShort();
        short z = inBuffer.readShort();
        return new ClientVelocityPacket(x, y, z);
    }

    public static class Handler {
        public static void handle(ClientVelocityPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                LocalPlayer player = Minecraft.getInstance().player;
                if(player != null) {
                    if(msg.ya > 0) {
                        player.setOnGround(false);
                    }
                    player.lerpMotion(msg.xa / 8000.0D, msg.ya / 8000.0D, msg.za / 8000.0D);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
