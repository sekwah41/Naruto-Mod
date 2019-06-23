package sekwah.mods.narutomod.common;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.player.EntityPlayerMP;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.clientbound.ClientParticleEffectPacket;

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
            outputStream.writeDouble(playerMP.worldObj.getWorldInfo().getDimension());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToAllAround(new ClientParticleEffectPacket(bos.toByteArray()), new TargetPoint(playerMP.worldObj.getWorldInfo().getDimension(), playerMP.posX, playerMP.posY, playerMP.posZ, 64));

    }

}
