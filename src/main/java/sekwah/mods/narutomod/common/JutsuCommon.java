package sekwah.mods.narutomod.common;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;
import sekwah.mods.narutomod.common.entity.EntityShadowClone;
import sekwah.mods.narutomod.common.entity.EntitySubstitution;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityChibakuTensei;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityFlameFireball;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityWaterBullet;
import sekwah.mods.narutomod.common.entity.specials.EntityMovingBlock;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.clientbound.ClientSoundPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

public class JutsuCommon {

    public static boolean execute(int jutsuCombo, EntityPlayerMP playerMP) {
        Vec3 looking = playerMP.getLookVec();
        int eyeStatus = 0;
        switch (jutsuCombo) {
            case 1: // chakra restore
                return true;
            case 2: // chakra dash
                // blue smoke new EntityColouredSmokeFX(this.theWorld, par2, par4, par6, par8, par10, par12, 0.3F, 0.3F, 1F);

                // red smoke new EntityColouredSmokeFX(this.theWorld, par2, par4, par6, par8, par10, par12, 1F, 0.3F, 0.3F);

                // change it to code and add a client side particle spawner using packets and client code
                // ParticleEffectsHandler.addEffect(1, playerMP);
                return true;
            case 3: // water walk
                return true;
            case 4: // resets falling damage server side
                //playerMP.attackEntityFrom(DamageSource.fall, playerMP.fallDistance / 10);
                playerMP.fallDistance = 0.0F;
                return true;
            case 401:
                return true;
            case 402:
                return true;
            case 403:
                return true;
            case 404:
                return true;
            case 411:
                jutsuSound(8, playerMP);
                return true;
            case 412:
                jutsuSound(8, playerMP);
                return true;
            case 413:
                jutsuSound(8, playerMP);
                return true;
            case 414:
                jutsuSound(8, playerMP);
                return true;
            case 12:

                eyeStatus = playerMP.getDataWatcher().getWatchableObjectInt(DataWatcherIDs.eyerenderer);

                playerMP.addPotionEffect((new PotionEffect(Potion.invisibility.getId(), 10, 0)));

                EntitySubstitution substitution = new EntitySubstitution(playerMP.worldObj, playerMP.getGameProfile());

                substitution.setLocationAndAngles(playerMP.posX, playerMP.posY, playerMP.posZ, playerMP.rotationYaw, playerMP.rotationPitch);
                substitution.setCustomNameTag(playerMP.getCommandSenderName());

                substitution.setCurrentItemOrArmor(0, playerMP.getCurrentEquippedItem());

                substitution.setCurrentItemOrArmor(1, playerMP.getCurrentArmor(3));
                substitution.setCurrentItemOrArmor(2, playerMP.getCurrentArmor(2));
                substitution.setCurrentItemOrArmor(3, playerMP.getCurrentArmor(1));
                substitution.setCurrentItemOrArmor(4, playerMP.getCurrentArmor(0));

                substitution.getDataWatcher().updateObject(DataWatcherIDs.eyerenderer, eyeStatus);

                if (playerMP.isSprinting()) {
                    substitution.setMovement((float) (Math.cos(Math.toRadians(playerMP.rotationYaw - 90)) * -0.3F), (float) (Math.sin(Math.toRadians(playerMP.rotationYaw - 90)) * -0.3F));
                    substitution.setSprinting(true);
                } else {
                    substitution.setMovement((float) (Math.cos(Math.toRadians(playerMP.rotationYaw - 90)) * -0.2F), (float) (Math.sin(Math.toRadians(playerMP.rotationYaw - 90)) * -0.2F));
                }

                substitution.lifetime = 80;

                // at the moment the stuff for this jutsu is just a prototype

                for (int loops = 0; loops < 40; loops++) {
                    if (teleportRandomly(playerMP)) {
                        playerMP.fallDistance = 0.0F;
                        break;
                    }
                }

                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                try {
                    outputStream.writeInt(4);
                    outputStream.writeDouble(playerMP.posX);
                    outputStream.writeDouble(playerMP.posY);
                    outputStream.writeDouble(playerMP.posZ);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                PacketDispatcher.sendPacketToPlayer(new ClientSoundPacket(bos.toByteArray()), playerMP);

                playerMP.worldObj.spawnEntityInWorld(substitution);
                return true;
            case 101: // charging chakra
                jutsuSound(4, playerMP);
                return true;
            case 110: // charging chakra stopped
                return true;
            case 111:
                EntityChibakuTensei bigBoomBoomBall = new EntityChibakuTensei(playerMP.worldObj);
                bigBoomBoomBall.setPosition(playerMP.posX, playerMP.posY, playerMP.posZ);
                playerMP.worldObj.spawnEntityInWorld(bigBoomBoomBall);
                return true;
            case 121:
                jutsuSound(4, playerMP);
                return true;
            case 1210:
                jutsuSound(5, playerMP);

                EntityFlameFireball fireball = new EntityFlameFireball(playerMP.worldObj, playerMP, 1, 1, 1);
                fireball.setLocationAndAngles(playerMP.posX + looking.xCoord * 1,
                        playerMP.posY + 0.85F + looking.yCoord * 0.2,
                        playerMP.posZ + looking.zCoord * 1,
                        playerMP.rotationYaw,
                        playerMP.rotationPitch);

                fireball.accelerationX = looking.xCoord * 0.2;
                fireball.accelerationY = looking.yCoord * 0.2;
                fireball.accelerationZ = looking.zCoord * 0.2;
                //fireball.life = 70;

                playerMP.worldObj.spawnEntityInWorld(fireball);
                return true;
            case 132:
                jutsuSound(4, playerMP);
                return true;
            case 1320:
                EntityWaterBullet waterBullet = new EntityWaterBullet(playerMP.worldObj, playerMP, 1, 1, 1);
                waterBullet.setLocationAndAngles(playerMP.posX + looking.xCoord * 1,
                        playerMP.posY + 1.2F + looking.yCoord * 0.2,
                        playerMP.posZ + looking.zCoord * 1,
                        playerMP.rotationYaw - 180,
                        playerMP.rotationPitch);
                waterBullet.accelerationX = looking.xCoord * 0.2;
                waterBullet.accelerationY = looking.yCoord * 0.2;
                waterBullet.accelerationZ = looking.zCoord * 0.2;

                playerMP.worldObj.spawnEntityInWorld(waterBullet);
                return true;
            case 311: // possibly the toggle for liams eyes
                return true;
            case 312:
                // TODO STOP PLAYER MOVEMENT
                return true;
            case 3120:
                activateEarthWall(playerMP);
                return true;
            case 333:
                return true;
            case 3330:
                return true;
            case 1332:
                jutsuSound(4, playerMP);
                eyeStatus = playerMP.getDataWatcher().getWatchableObjectInt(DataWatcherIDs.eyerenderer);
                spawnClones(playerMP, (int) (Math.random() * 1.2 + 1.9), eyeStatus, false);
                return true;
            case 1322:
                jutsuSound(4, playerMP);
                eyeStatus = playerMP.getDataWatcher().getWatchableObjectInt(DataWatcherIDs.eyerenderer);
                spawnClones(playerMP, (int) (Math.random() * 1.2 + 1.9), eyeStatus, true);
                return true;
            case 133231:
                jutsuSound(4, playerMP);
                eyeStatus = playerMP.getDataWatcher().getWatchableObjectInt(DataWatcherIDs.eyerenderer);
                spawnClones(playerMP, (int) (Math.random() * 2.2 + 8.9), eyeStatus, false);
                return true;
        }

        return false;


    }

    private static void activateEarthWall(EntityPlayerMP playerMP) {
        jutsuSound(4, playerMP);
        Vec3 posVec = Vec3.createVectorHelper(playerMP.posX,playerMP.posY + playerMP.getEyeHeight(), playerMP.posZ);
        Vec3 lookVec = playerMP.getLookVec();
        int wallRange = 10;
        MovingObjectPosition movingObject = playerMP.worldObj.rayTraceBlocks(posVec, posVec.addVector(lookVec.xCoord * wallRange,
                lookVec.yCoord * wallRange, lookVec.zCoord * wallRange));
        short xDir = 0;
        short zDir = 0;
        if(movingObject != null) {
            //playerMP.worldObj.setBlockToAir(movingObject.blockX,movingObject.blockY, movingObject.blockZ);
            //playerMP.worldObj.getBlock(movingObject.blockX,movingObject.blockY, movingObject.blockZ);
            int dir = MathHelper.floor_double((double) ((playerMP.rotationYaw * 8F) / 360F) + 0.5D) % 4;
            if(dir < 0) {
                dir += 4;
            }

            switch (dir) {
                case 0:
                    xDir = 1;
                    break;
                case 1:
                    xDir = 1;
                    zDir = 1;
                    break;
                case 2:
                    zDir = 1;
                    break;
                case 3:
                    xDir = 1;
                    zDir = -1;
                    break;
            }
            for(int i = -2; i <= 2; i++) {
                earthWallPillar(playerMP.worldObj, movingObject.blockX + xDir * i,movingObject.blockY, movingObject.blockZ + zDir * i);
            }

            //System.out.printf("%s %s %s%n",movingObject.blockX,movingObject.blockY, movingObject.blockZ);
        }
        else {
            playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.earthWall.failed")));
            //System.out.println("NO BLOCK");
        }
    }

    private static List blockList = Arrays.asList(allowedBlocks(Blocks.stone, Blocks.coal_ore, Blocks.diamond_ore,
            Blocks.log, Blocks.gravel, Blocks.sand, Blocks.sandstone));

    private static String[] allowedBlocks(Block... blocks) {
        String[] names = new String[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            names[i] = blocks[i].getUnlocalizedName();
        }
        return names;
    }

    private static void earthWallPillar(World worldObj, int x, int y, int z) {
        if(!Blocks.dirt.canPlaceBlockAt(worldObj,x,y,z)) y++;
        else if(Blocks.dirt.canPlaceBlockAt(worldObj,x,y-1,z)) y++;
        //worldObj.setBlock(x,y,z,Blocks.bedrock);
        for(int yW = 0; yW < 5; yW ++) {
            if(worldObj.getBlock(x,y+ yW,z) != Blocks.air) continue;
            Block block = worldObj.getBlock(x,y-5 + yW,z);
            int meta = worldObj.getBlockMetadata(x,y-5 + yW,z);
            if(!blockList.contains(block.getUnlocalizedName())) {
                block = Blocks.dirt;
                meta = 0;
            }
            EntityMovingBlock blockEntity = new EntityMovingBlock(worldObj,x,y + yW,z,
                    Block.getIdFromBlock(/*Blocks.dirt*/block), meta, 20 * 9 + (int) (Math.random() * 40));
            worldObj.spawnEntityInWorld(blockEntity);
        }

    }

    private static void spawnClones(EntityPlayerMP playerMP, int cloneCount, int eyeStatus, boolean baby) {
        System.out.println(cloneCount);
        for(int i = 0; i < cloneCount; i++) {
            EntityShadowClone shadowClone = new EntityShadowClone(playerMP.worldObj, playerMP.getGameProfile());

            shadowClone.setLocationAndAngles(playerMP.posX, playerMP.posY, playerMP.posZ, playerMP.rotationYaw, playerMP.rotationPitch);
            shadowClone.setCustomNameTag(playerMP.getCommandSenderName());

            if(baby) {
                shadowClone.setChild(true);
            }
            else {
                shadowClone.setCurrentItemOrArmor(0, playerMP.getCurrentEquippedItem());

                shadowClone.setCurrentItemOrArmor(1, playerMP.getCurrentArmor(3));
                shadowClone.setCurrentItemOrArmor(2, playerMP.getCurrentArmor(2));
                shadowClone.setCurrentItemOrArmor(3, playerMP.getCurrentArmor(1));
                shadowClone.setCurrentItemOrArmor(4, playerMP.getCurrentArmor(0));
            }

            shadowClone.getDataWatcher().updateObject(DataWatcherIDs.eyerenderer, eyeStatus);

            shadowClone.setVelocity((Math.random() - 0.5D) / 3, 0, (Math.random() - 0.5D) / 3);

            playerMP.worldObj.spawnEntityInWorld(shadowClone);
        }
    }

    private static void jutsuSound(int i, EntityPlayerMP playerMP) {

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

        PacketDispatcher.sendPacketToAllAround(new ClientSoundPacket(bos.toByteArray()), new TargetPoint(playerMP.dimension, playerMP.posX, playerMP.posY, playerMP.posZ, 64));

    }

    protected static boolean teleportRandomly(EntityPlayerMP playerMP) {
        double differenceX = playerMP.posX + (playerMP.worldObj.rand.nextDouble() - 0.5D) * 32.0D;
        double differenceY = playerMP.posY + (double) (playerMP.worldObj.rand.nextInt(30) - 10);
        double differenceZ = playerMP.posZ + (playerMP.worldObj.rand.nextDouble() - 0.5D) * 32.0D;

        double distance = Math.sqrt(differenceX * differenceX + differenceY * differenceY + differenceZ * differenceZ);
        return distance > 6 && teleportTo(differenceX, differenceY, differenceZ, playerMP);
    }

    protected static boolean teleportTo(double posX, double posY, double posZ, EntityPlayerMP playerMP) {

        double d3 = playerMP.posX;
        double d4 = playerMP.posY;
        double d5 = playerMP.posZ;
        playerMP.posX = posX;
        playerMP.posY = posY;
        playerMP.posZ = posZ;
        boolean flag = false;
        int i = MathHelper.floor_double(playerMP.posX);
        int j = MathHelper.floor_double(playerMP.posY);
        int k = MathHelper.floor_double(playerMP.posZ);
        Block l;

        if (playerMP.worldObj.blockExists(i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
                l = playerMP.worldObj.getBlock(i, j - 1, k);

                if (l != Blocks.air && l.getMaterial().blocksMovement()) {
                    flag1 = true;
                } else {
                    --playerMP.posY;
                    --j;
                }
            }

            if (flag1) {
                playerMP.setPositionAndUpdate(playerMP.posX, playerMP.posY, playerMP.posZ);
                //playerMP.setPosition(playerMP.posX, playerMP.posY, playerMP.posZ);

                if (playerMP.worldObj.getCollidingBoundingBoxes(playerMP, playerMP.boundingBox).isEmpty() && !playerMP.worldObj.isAnyLiquid(playerMP.boundingBox)) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            playerMP.setPosition(d3, d4, d5);
            return false;
        } else {
            short short1 = 128;
            return true;
        }
    }


}
