package com.sekwah.mods.narutomod.client;

import com.sekwah.mods.narutomod.NarutoMod;
import com.sekwah.mods.narutomod.assets.JutsuData;
import com.sekwah.mods.narutomod.packets.PacketAnimationUpdate;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Random;

public class JutsuClient {

    private static Random rand = new Random();

    public static void execute(int jutsuCombo, EntityClientPlayerMP playerMP) {

        switch (jutsuCombo) {
            case 1:
                break;
            case 2:
                if (PlayerClientTickEvent.chakraDash) {
                    PlayerClientTickEvent.chakraDash = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraDash.deactivate")));
                } else {
                    PlayerClientTickEvent.chakraDash = true;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.chakraDash.activate")));
                }
                break;
            case 3:
                if (PlayerClientTickEvent.waterWalking) {
                    PlayerClientTickEvent.waterWalking = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.waterWalk.deactivate")));
                } else {
                    PlayerClientTickEvent.waterWalking = true;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.waterWalk.activate")));
                }
                break;
            case 4:
                break;
            case 401:
                if (PlayerClientTickEvent.onWater) {
                    PlayerClientTickEvent.useChakra(10F);
                }
                PlayerClientTickEvent.stamina -= 20;
                PlayerClientTickEvent.setStaminaCooldown(80);
                PacketAnimationUpdate.animationUpdate("leapforward" + (rand.nextInt(2) + 1), playerMP);
                break;
            case 411:
                playerMP.setVelocity(playerMP.motionX + playerMP.getLookVec().xCoord * 1.5F, (playerMP.getLookVec().yCoord + 0.8F) * 0.7F
                        , playerMP.motionZ + playerMP.getLookVec().zCoord * 1.5F);
                break;
            case 12:// change the combo at some point and make sure its the same as in jutsu common
                // add code to execute client side  here!
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.substitution")));
                ParticleEffects.addEffect(4, playerMP);
                PlayerClientTickEvent.chakra -= JutsuData.substitutionCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case 101:
                PacketAnimationUpdate.animationUpdate("chakraCharging", playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.chakraCharge.activate")));
                break;
            case 110:
                PacketAnimationUpdate.animationUpdate("default", playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraCharge.deactivate")));
                break;
            case 121:
                PacketAnimationUpdate.animationUpdate("fireBall", playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.fireball")));
                PlayerClientTickEvent.chakra -= JutsuData.fireballCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case 1210:
                // Doesnt do anything
                break;
            case 132:
                PacketAnimationUpdate.animationUpdate("waterBullet", playerMP); // change to a water ball pose
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.waterBullet")));
                PlayerClientTickEvent.chakra -= JutsuData.waterBulletCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;
            case 311: // TODO possibly the toggle for liams eyes

                break;
            case 312: // TODO set the combo for the earth style

                break;
            case 1320:
                // Doesnt do anything
                break;
            case 1332:
                ParticleEffects.addEffect(4, playerMP);
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + I18n.format("naruto.jutsu.shadowClone")));
                PlayerClientTickEvent.chakra -= JutsuData.shadowCloneCost;
                PlayerClientTickEvent.chakraCooldown = 30;
                break;

            // add shadow clone summon client side
            default:
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.unrecognised")));
                break;
        }


    }

    public static boolean canCast(int jutsuCombo, EntityClientPlayerMP playerMP /*  add int to say if its chakra, stamina or both. or add a new method*/) {
        NarutoMod.LOGGER.debug("Can Cast: " + jutsuCombo);
        switch (jutsuCombo) {
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 12:
                if (PlayerClientTickEvent.chakra >= JutsuData.substitutionCost) return true;
                break;
            case 101:
                return true;
            case 110:
                return true;
            case 121:
                if (PlayerClientTickEvent.chakra >= JutsuData.fireballCost) return true;
                break;
            case 132:
                if (PlayerClientTickEvent.chakra >= JutsuData.waterBulletCost) return true;
                break;
            case 311:
                return false;// TODO possibly the toggle for liams eyes, will be true once done
            case 312:
                return true; // TODO set the combo for the earth style
            case 1332:
                if (PlayerClientTickEvent.chakra >= JutsuData.shadowCloneCost) return true;
                break;
            default:
                return true;
        }

        playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.nochakra")));
        return false;
    }

}
