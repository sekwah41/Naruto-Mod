package sekwah.mods.narutomod.packets;

import sekwah.mods.narutomod.common.JutsuCommon;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayerMP;
import sekwah.mods.narutomod.packets.clientbound.ClientJutsuPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientParticleEffectPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientSoundPacket;

import java.io.*;

public class ServerPacketHandler {

    public static void handleJutsuPacket(byte[] packet, EntityPlayerMP playerEntity) {
        ByteArrayInputStream bais = new ByteArrayInputStream(packet);
        DataInputStream dis = new DataInputStream(bais);
        int JutsuCombo = 0;
        int dimension = 0;
        try {
            JutsuCombo = dis.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // We are on the server side.
        if (JutsuCommon.execute(JutsuCombo, playerEntity)) {
            PacketDispatcher.sendPacketToPlayer(new ClientJutsuPacket(packet), playerEntity);
        } else {
            // Makes sure that the server also has the jutsu, this way if the feature like
            //  magic spells is added it doesn't get activated on the server and mess up

            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try {
                outputStream.writeInt(0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            PacketDispatcher.sendPacketToPlayer(new ClientJutsuPacket(bos.toByteArray()), playerEntity);

        }
    }

    public static void handleParticlePacket(byte[] packet) {
        ByteArrayInputStream bais = new ByteArrayInputStream(packet);
        DataInputStream dis = new DataInputStream(bais);
        int effectID = 0;
        double x = -1.0D;
        double y = -1.0D;
        double z = -1.0D;
        int dimension = 0;
        try {
            effectID = dis.readInt();
            x = dis.readDouble();
            y = dis.readDouble();
            z = dis.readDouble();
            dimension = dis.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PacketDispatcher.sendPacketToAllAround(new ClientParticleEffectPacket(packet), new TargetPoint(dimension, x, y, z, 64));
    }

    public static void handleSoundPacket(byte[] packet) {
        ByteArrayInputStream bais = new ByteArrayInputStream(packet);
        DataInputStream dis = new DataInputStream(bais);
        int soundID = 0;
        double x = -1.0D;
        double y = -1.0D;
        double z = -1.0D;
        int dimension = 0;
        try {
            soundID = dis.readInt();
            x = dis.readDouble();
            y = dis.readDouble();
            z = dis.readDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }

        PacketDispatcher.sendPacketToAllAround(new ClientSoundPacket(packet), new TargetPoint(dimension, x, y, z, 64));
    }

    public static void handleAnimationPacket(byte[] packet, EntityPlayerMP playerEntity) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet));
        String jutsuPose = "default";
        try {
            jutsuPose = inputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataWatcher dw = playerEntity.getDataWatcher();
        //NarutoMod.logger.info(jutsuPose);

        dw.updateObject(20, jutsuPose);
        dw.updateObject(25, Float.valueOf(0));
    }

    public static void handlePlayerDataPacket(byte[] packet) {

    }
}
