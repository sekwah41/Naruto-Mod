package sekwah.mods.narutomod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import sekwah.mods.narutomod.entitys.particles.EntityColouredSmokeFX;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerParticleEffectPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ParticleEffects {

    public static void execute(int effectID, EntityClientPlayerMP playerMP, double posX, double posY, double posZ) {

        //NarutoMod.LOGGER.debug(effectID);

        if (effectID == 1) {
            double particleX = Math.random() - 0.5;
            double particleY = (Math.random()) * 2 - 1.8F;
            double particleZ = Math.random() - 0.5;
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityColouredSmokeFX(playerMP.worldObj, posX + particleX, posY + particleY, posZ + particleZ, 0, 0, 0, 0.08F, 0.7F, 1F));
        } else if (effectID == 2) {
            double particleX = Math.random() - 0.5;
            double particleY = (Math.random()) * 2 - 1.8F;
            double particleZ = Math.random() - 0.5;
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityColouredSmokeFX(playerMP.worldObj, posX + particleX, posY + particleY, posZ + particleZ, 0, 0, 0, 1F, 0.3F, 0.3F));
        } else if (effectID == 3) {
            for (int i = 1; i < 30; i++) {
                double particleX = Math.random();
                double particleZ = Math.random();
                double speedX = Math.random();
                double speedZ = Math.random();
                playerMP.worldObj.spawnParticle("cloud", posX - 0.5 + particleX, posY - 1.3F, posZ - 0.5 + particleZ, speedX - 0.5D, 0.0D, speedZ - 0.5D);
            }
        } else if (effectID == 4) {
            for (int i = 1; i < 25; i++) {
                // fix the spawn position
                double particleX = Math.random() - 0.5;
                double particleY = (Math.random()) * 2.5 - 2.5F;
                double particleZ = Math.random() - 0.5;
                playerMP.worldObj.spawnParticle("explode", posX + particleX, posY + particleY, posZ + particleZ, 0, 0, 0);
            }
        } else {

        }

    }

    public static void addEffect(int i, EntityPlayerMP playerEntity) {

        if (i == 1) {
            //if(playerMP.isPotionActive(Potion.chakraRestore)){
            //	i = 2;
            //}
        }


        ByteArrayOutputStream bos = new ByteArrayOutputStream(16);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            outputStream.writeInt(i);
            outputStream.writeDouble(playerEntity.posX);
            outputStream.writeDouble(playerEntity.posY);
            outputStream.writeDouble(playerEntity.posZ);
            outputStream.writeInt(playerEntity.dimension);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToServer(new ServerParticleEffectPacket(bos.toByteArray()));

    }

    public static void addEffect(int i, EntityClientPlayerMP playerEntity) {

        if (i == 1) {
            //if(playerMP.isPotionActive(Potion.chakraRestore)){
            //	i = 2;
            //}
        }


        ByteArrayOutputStream bos = new ByteArrayOutputStream(16);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            outputStream.writeInt(i);
            outputStream.writeDouble(playerEntity.posX);
            outputStream.writeDouble(playerEntity.posY);
            outputStream.writeDouble(playerEntity.posZ);
            outputStream.writeInt(playerEntity.dimension);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToServer(new ServerParticleEffectPacket(bos.toByteArray()));

    }

}
