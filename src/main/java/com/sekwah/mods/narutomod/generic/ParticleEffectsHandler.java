package com.sekwah.mods.narutomod.generic;

import com.sekwah.mods.narutomod.packets.PacketDispatcher;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.player.EntityPlayerMP;
import com.sekwah.mods.narutomod.packets.clientbound.ClientParticleEffectPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ParticleEffectsHandler {

    public static void addEffect(int i, EntityPlayerMP playerMP) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            outputStream.writeInt(i);
            outputStream.writeDouble(playerMP.posX);
            outputStream.writeDouble(playerMP.posY);
            outputStream.writeDouble(playerMP.posZ);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToAllAround(new ClientParticleEffectPacket(bos.toByteArray()), new TargetPoint(playerMP.worldObj.getWorldInfo().getVanillaDimension(), playerMP.posX, playerMP.posY, playerMP.posZ, 64));

    }

}
