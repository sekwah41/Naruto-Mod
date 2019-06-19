package sekwah.mods.narutomod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import sekwah.mods.narutomod.common.entity.particles.EntityBirbFX;
import sekwah.mods.narutomod.common.entity.particles.EntityColouredSmokeFX;
import sekwah.mods.narutomod.common.entity.particles.EntityColouredSmokeTrackingFX;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerParticleEffectPacket;
import sekwah.mods.narutomod.settings.NarutoSettings;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ParticleEffects {

    /*public static void execute(int effectID, EntityClientPlayerMP playerMP, double posX, double posY, double posZ) {

        //NarutoMod.logger.debug(effectID);

        if (effectID == 1) {
            double particleX = Math.random() - 0.5;
            double particleY = (Math.random()) * 2 - 1.8F;
            double particleZ = Math.random() - 0.5;
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityColouredSmokeFX(playerMP.worldObj, posX + particleX, posY + particleY, posZ + particleZ, 0, 0, 0, 0.08F, 0.7F, 1F));
        } else if (effectID == 2) {
            double particleX = Math.random() - 0.5;
            double particleY = (Math.random()) * 2 - 1.8F;
            double particleZ = Math.random() - 0.5;
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityColouredSmokeFX(playerMP.worldObj, posX + particleX, posY + particleY, posZ + particleZ, 0, 0, 0, 1F, 0.5F, 0.1F));
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

    }*/

    // One of the client side only activated methods.
    public static void addTrackingParticle(Particle particle, World worldObj, double x, double y, double z, Entity entity, float... args) {
        switch(particle){
            case COLOURED_SMOKE:
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityColouredSmokeTrackingFX(worldObj, x,y,z,args[0], args[1],args[2], args[3], args[4], args[5],  entity));
                return;
        }
    }



    // TODO add an enum class with ids and stuff to track effect ids easier and add a simple case statement
    public static void execute(DataInputStream dis, EntityClientPlayerMP playerMP) {

        int effectID = 0;
        double posX = -1.0D;
        double posY = -1.0D;
        double posZ = -1.0D;
        int dimension = 0;
        try {
            effectID = dis.readInt();
            posX = dis.readDouble();
            posY = dis.readDouble();
            posZ = dis.readDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (effectID == 1) {
            double particleX = Math.random() - 0.5;
            double particleY = (Math.random()) * 2 - 1.8F;
            double particleZ = Math.random() - 0.5;
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityColouredSmokeFX(playerMP.worldObj, posX + particleX, posY + particleY, posZ + particleZ, 0, 0, 0, 0.08F, 0.7F, 1F));
        } else if (effectID == 2) {
            double particleX = Math.random() - 0.5;
            double particleY = (Math.random()) * 2 - 1.8F;
            double particleZ = Math.random() - 0.5;
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityColouredSmokeFX(playerMP.worldObj, posX + particleX, posY + particleY, posZ + particleZ, 0, 0, 0, 1F, 0.5F, 0.1F));
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
        }
        else if (effectID == 5) {
            double particleX = Math.random() - 0.5;
            double particleY = (Math.random()) * 2 - 1.8F;
            double particleZ = Math.random() - 0.5;
            float red = 1f;
            float green = 1f;
            float blue = 1f;
            try {
                red = dis.readFloat();
                green = dis.readFloat();
                blue = dis.readFloat();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityColouredSmokeFX(playerMP.worldObj, posX + particleX, posY + particleY, posZ + particleZ, 0, 0, 0, red, green, blue));
        }
        else if (effectID == 6) {
            for (int i = 1; i < 1000; i++) {
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBirbFX(playerMP.worldObj, posX, posY, posZ).setMotion(Math.random() - 0.5D, Math.random() - 0.5D, Math.random() - 0.5D));
            }
        }
        else {

        }

    }

    /**
     * Used to spawn chakra particles of the colour set for the characters
     * @param playerEntity
     */
    public static void addPlayersColouredSmoke(EntityClientPlayerMP playerEntity){
        ByteArrayOutputStream bos = new ByteArrayOutputStream(16);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            outputStream.writeInt(5);
            outputStream.writeDouble(playerEntity.posX);
            outputStream.writeDouble(playerEntity.posY);
            outputStream.writeDouble(playerEntity.posZ);
            outputStream.writeInt(playerEntity.dimension);
            outputStream.writeFloat(NarutoSettings.chakraRedFloat());
            outputStream.writeFloat(NarutoSettings.chakraGreenFloat());
            outputStream.writeFloat(NarutoSettings.chakraBlueFloat());
            /*outputStream.writeFloat(0.08F);
            outputStream.writeFloat(0.7F);
            outputStream.writeFloat(1F);*/
            /*double fullCircle = Math.PI * 2;
            double hue = Math.random() * fullCircle;
            double sixth = Math.PI / 3d;
            double gradient = 3d / Math.PI;
            float red = 0;
            float blue = 0;
            float green = 0;
            if(hue == (fullCircle / 3D)){
                red = 1;

            }
            else
            if(hue == 3d * (fullCircle / 3D)){
                blue = 1;
            }
            if(hue == 3d * (fullCircle / 3D)){
                green = 1;
            }
            if(red > 1) red = 1;
            if(blue > 1) blue = 1;
            if(green > 1) green = 1;
            if(red < 0) red = 0;
            if(blue < 0) blue = 0;
            if(green < 0) green = 0;
            outputStream.writeFloat(red);
            outputStream.writeFloat(blue);
            outputStream.writeFloat(green);*/
            /*outputStream.writeFloat(1F);
            outputStream.writeFloat(0.5F);
            outputStream.writeFloat(0.9F);*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToServer(new ServerParticleEffectPacket(bos.toByteArray()));
    }

    public static void addColouredSmoke(float red, float green, float blue, EntityClientPlayerMP playerEntity){
        ByteArrayOutputStream bos = new ByteArrayOutputStream(16);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            outputStream.writeInt(5);
            outputStream.writeDouble(playerEntity.posX);
            outputStream.writeDouble(playerEntity.posY);
            outputStream.writeDouble(playerEntity.posZ);
            outputStream.writeInt(playerEntity.dimension);
            outputStream.writeFloat(red);
            outputStream.writeFloat(green);
            outputStream.writeFloat(blue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToServer(new ServerParticleEffectPacket(bos.toByteArray()));
    }

    public static void addEffect(int i, EntityPlayerMP playerEntity) {


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
