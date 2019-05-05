package sekwah.mods.narutomod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import org.apache.commons.io.IOUtils;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.assets.JutsuData;
import sekwah.mods.narutomod.jutsu.Jutsu;
import sekwah.mods.narutomod.jutsu.Jutsus;
import sekwah.mods.narutomod.packets.PacketAnimationUpdate;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerJutsuPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

public class JutsuClient {

    private static Random rand = new Random();

    public static void execute(int jutsuCombo, EntityClientPlayerMP playerMP) {

        Vec3 lookVector;

        switch (jutsuCombo) {

            case Jutsus.CHAKRA_RESTORE:
                break;
            case Jutsus.CHAKRA_DASH:
                if (PlayerClientTickEvent.chakraDash) {
                    PlayerClientTickEvent.chakraDash = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraDash.deactivate")));
                } else {
                    PlayerClientTickEvent.chakraDash = true;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.chakraDash.activate")));
                }
                break;
            case Jutsus.WATER_WALK:
                if (PlayerClientTickEvent.waterWalking) {
                    PlayerClientTickEvent.waterWalking = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.waterWalk.deactivate")));
                } else {
                    PlayerClientTickEvent.waterWalking = true;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.waterWalk.activate")));
                }
                break;
            case Jutsus.RESET_FALL_DAMAGE:
                break;
            case Jutsus.CHIBAKU_TENSEI:
                playerMP.addChatMessage(new ChatComponentText("ENJOY DESTRUCTION"));
                break;
            case Jutsus.LEAP_START:
                PlayerClientTickEvent.stamina -= 5;
                PlayerClientTickEvent.setStaminaCooldown(80);
                PacketAnimationUpdate.animationUpdate("leapforward" + (rand.nextInt(2) + 1), playerMP);
                break;
            case Jutsus.DODGE_BACK_START:
                PlayerClientTickEvent.stamina -= 5;
                PlayerClientTickEvent.setStaminaCooldown(80);
                PacketAnimationUpdate.animationUpdate("leapback" + (rand.nextInt(2) + 1), playerMP);
                break;
            case Jutsus.DODGE_LEFT_START:
                PlayerClientTickEvent.stamina -= 5;
                PlayerClientTickEvent.setStaminaCooldown(80);
                PacketAnimationUpdate.animationUpdate("leapleft", playerMP);
                break;
            case Jutsus.DODGE_RIGHT_START:
                PlayerClientTickEvent.stamina -= 5;
                PlayerClientTickEvent.setStaminaCooldown(80);
                PacketAnimationUpdate.animationUpdate("leapright", playerMP);
                break;
            case Jutsus.LEAP_STOP:
                lookVector = playerMP.getLookVec();
                playerMP.setVelocity(playerMP.motionX + lookVector.xCoord * 2F, (lookVector.yCoord + 0.8F) * 1F
                        , playerMP.motionZ + lookVector.zCoord * 2F);
                break;
            case Jutsus.DODGE_BACK_STOP:
                lookVector = playerMP.getLookVec();
                lookVector.rotateAroundY((float) (Math.PI));
                playerMP.setVelocity(playerMP.motionX + lookVector.xCoord * 1.5F, 0.5F, playerMP.motionZ + lookVector.zCoord * 1.5F);
                break;
            case Jutsus.DODGE_LEFT_STOP:
                lookVector = playerMP.getLookVec();
                lookVector.rotateAroundY((float) (Math.PI / 2F));
                playerMP.setVelocity(playerMP.motionX + lookVector.xCoord * 1.7F, 0.5F, playerMP.motionZ + lookVector.zCoord * 1.7F);
                break;
            case Jutsus.DODGE_RIGHT_STOP:
                lookVector = playerMP.getLookVec();
                lookVector.rotateAroundY((float) (-Math.PI / 2F));
                playerMP.setVelocity(playerMP.motionX + lookVector.xCoord * 1.7F, 0.5F, playerMP.motionZ + lookVector.zCoord * 1.7F);
                break;
            case Jutsus.SUBSTITUTION:// change the combo at some point and make sure its the same as in jutsu common
                // add code to execute client side  here!
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.substitution")));
                ParticleEffects.addEffect(4, playerMP);
                PlayerClientTickEvent.chakra -= JutsuData.substitutionCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case Jutsus.CHAKRA_INFUSE_START:
                if(playerMP.isSprinting()) {
                    PacketAnimationUpdate.animationUpdate("chakraSprintCharging", playerMP);
                }
                else {
                    PacketAnimationUpdate.animationUpdate("chakraCharging", playerMP);
                }
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.chakraCharge.activate")));
                break;
            case Jutsus.CHAKRA_INFUSE_STOP:
                PacketAnimationUpdate.animationUpdate("default", playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraCharge.deactivate")));
                break;
            case Jutsus.FIREBALL:
                PacketAnimationUpdate.animationUpdate("fireBall", playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.fireball")));
                PlayerClientTickEvent.chakra -= JutsuData.fireballCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case Jutsus.FIREBALL_STOP:
                break;
            case Jutsus.WATER_BULLET:
                PacketAnimationUpdate.animationUpdate("waterBullet", playerMP); // change to a water ball pose
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.waterBullet")));
                PlayerClientTickEvent.chakra -= JutsuData.waterBulletCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case Jutsus.EYES:
                break;
            case Jutsus.EARTH_RELEASE: // TODO set the combo for the earth style
                PacketAnimationUpdate.animationUpdate("earthWall", playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.earthWall")));
                PlayerClientTickEvent.chakra -= JutsuData.wallCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case Jutsus.EARTH_WALL_STOP:
                break;
            case Jutsus.SEKC:
                PacketAnimationUpdate.animationUpdate("sexyjutsu1", playerMP);
                break;
            case Jutsus.SEKC_STOP:
                break;
            case Jutsus.WATER_BULLET_STOP:
                break;
            case Jutsus.REGULAR_SHADOW_CLONE:
                ParticleEffects.addEffect(4, playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.shadowClone")));
                PlayerClientTickEvent.chakra -= JutsuData.shadowCloneCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case Jutsus.CHIBI_SHADOW_CLONE:
                ParticleEffects.addEffect(4, playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.chibiClone")));
                PlayerClientTickEvent.chakra -= JutsuData.shadowCloneCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case Jutsus.MULTI_SHADOW_CLONE:
                ParticleEffects.addEffect(4, playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.multiShadowClone")));
                PlayerClientTickEvent.chakra -= JutsuData.multiShadowCloneCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;

            // add shadow clone summon client side
            default:
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.unrecognised")));
                break;
        }


    }

    public static void executeRemote(int jutsuCombination) {
        if (canCast(jutsuCombination, Minecraft.getMinecraft().thePlayer)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try {
                outputStream.writeInt(jutsuCombination);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));
            IOUtils.closeQuietly(bos);
        }
    }

    //moved here so the server doesn't crash when registering the packet
    public static void displayJutsuList() {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        Jutsu.getRegisteredJutsuCombinations().forEach((name, combination) -> player.addChatMessage(new ChatComponentTranslation("naruto.jutsu.combo." + name, Jutsu.translateToKeyCombo(combination))));
    }

    public static boolean canCast(int jutsuCombo, EntityClientPlayerMP playerMP /*  add int to say if its chakra, stamina or both. or add a new method*/) {
        NarutoMod.logger.debug("Can Cast: " + jutsuCombo);
        switch (jutsuCombo) {
            case Jutsus.CHAKRA_RESTORE:
                return true;
            case Jutsus.CHAKRA_DASH:
                return true;
            case Jutsus.WATER_WALK:
                return true;
            case Jutsus.SUBSTITUTION:
                if (PlayerClientTickEvent.chakra >= JutsuData.substitutionCost) return true;
                break;
            case Jutsus.CHAKRA_INFUSE_START:
                return true;
            case Jutsus.CHAKRA_INFUSE_STOP:
                return true;
            case Jutsus.CHIBAKU_TENSEI:
                return true;
            case Jutsus.FIREBALL:
                if (PlayerClientTickEvent.chakra >= JutsuData.fireballCost) return true;
                break;
            case Jutsus.WATER_BULLET:
                if (PlayerClientTickEvent.chakra >= JutsuData.waterBulletCost) return true;
                break;
            case Jutsus.EYES:
                return false;// TODO possibly the toggle for liams eyes, will be true once done
            case Jutsus.EARTH_RELEASE:
                if (PlayerClientTickEvent.chakra >= JutsuData.wallCost) return true;
                break;
            case Jutsus.SEKC:
                return true;
            case Jutsus.REGULAR_SHADOW_CLONE:
                if (PlayerClientTickEvent.chakra >= JutsuData.shadowCloneCost) return true;
                break;
            case Jutsus.CHIBI_SHADOW_CLONE:
                if (PlayerClientTickEvent.chakra >= JutsuData.shadowCloneCost) return true;
                break;
            case Jutsus.MULTI_SHADOW_CLONE:
                if (PlayerClientTickEvent.chakra >= JutsuData.multiShadowCloneCost) return true;
                break;
            default:
                return true;
        }

        playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.nochakra")));
        return false;
    }

}
