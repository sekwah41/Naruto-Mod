package sekwah.mods.narutomod.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import sekwah.mods.narutomod.generic.NarutoEffects;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerJutsuPacket;
import sekwah.mods.narutomod.packets.serverbound.ServerSoundPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class PlayerClientTickEvent {
    // to do with the keypress data for jutsus
    public static String JutsuCombo = "";

    public static int JutsuKeyDelay = 0;

    public static boolean JutsuCasting = false;

    public static boolean ChargingChakra = false;

    public static int[] doubleTapTime = {0,0,0};


    public static boolean chakraDash = false;

    public static boolean waterWalking = false;
    public static float chakra = 100;
    public static float maxChakra = 100;

    public static float stamina = 100;
    public static float maxStamina = 100;

    public static int chakraCooldown;
    public static boolean isChakraFocus;
    public static String jutsuPoseID = "default"; // finish this so it is set by the different jutsu, for chakra charge it when its turned off and on. and for other jutsus set a delay untill its changed back
    private static boolean ChakraCharge = false;
    private static int ChakraChargeDelay = 20;
    private static double lastX;
    private static double lastY;
    private static double lastZ;
    private static boolean playerMoved;
    public int substitCooldown = 0; // the cooldown for the substitution jutsu, so it cant be spammed
    public boolean hasDoubleJumped = false;

    public boolean doubleJumpReady = false;

    public int animationUpdate; // use at some point to update animations after they have been set.
    public static boolean onWater = false;

    public static String getJutsuPoseID() {
        if (jutsuPoseID == "default") {
            //if(isChakraFocus){
            //	return "chakraCharging";
            //}
            //else{
            return "default";
            //}
        }
        return jutsuPoseID;
    }

    @SubscribeEvent
    public void tick(ClientTickEvent event) {
        GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
        if (guiscreen == null || guiscreen instanceof GuiInventory || guiscreen instanceof GuiChat) {

            // TEMP ADDITION FOR GINGERS SHITTY STUFF
            stamina += 0.22;

            if(stamina > maxStamina){
                stamina = maxStamina;
            }

            //NarutoMod.LOGGER.info(stamina);

            boolean ChakraFocus = false;

            int spaceKeyCode = Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode();

            EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;

            if (PlayerClientTickEvent.chakraCooldown > 0) {
                PlayerClientTickEvent.chakraCooldown--;
            } else if (PlayerClientTickEvent.chakra < PlayerClientTickEvent.maxChakra) {
                PlayerClientTickEvent.chakra += 0.025;
            }

            // Create a potion with an image using custom forge code.
            // Not activating for some reason
            if(playerMP.isPotionActive(NarutoEffects.chakraRestore) && PlayerClientTickEvent.chakra < PlayerClientTickEvent.maxChakra){
                int regenMultiplyer = playerMP.getActivePotionEffect(NarutoEffects.chakraRestore).getAmplifier();
                if(regenMultiplyer > 10){
                    PlayerClientTickEvent.chakra += 0.1 + 0.1 * 10;
                }
                else{
                    PlayerClientTickEvent.chakra += 0.1 + 0.1 * regenMultiplyer;
                }
             }

            if (lastX == playerMP.posX && lastY == playerMP.posY && lastZ == playerMP.posZ) {
                playerMoved = false;
            } else {
                playerMoved = true;
            }

            if (ChargingChakra) {
                if (ChakraChargeDelay >= 0) {
                    ChakraChargeDelay--;
                } else if (!ChakraCharge && playerMoved) {
                    ChakraChargeDelay = 30;
                } else if (!ChakraCharge && !playerMoved) {
                    ChakraCharge = true;

                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try {
                        outputStream.writeInt(101);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));
                } else if (ChakraCharge && playerMoved) {

                    ChakraChargeDelay = 30;
                    ChakraCharge = false;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try {
                        outputStream.writeInt(110);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));

                } else {

                    if (PlayerClientTickEvent.chakra < PlayerClientTickEvent.maxChakra) {
                        PlayerClientTickEvent.chakra += 0.15;
                    }
                    ChakraFocus = true;

                    double possibility = Math.random();
                    if (possibility >= 0.3F) {
                        if(playerMP.isPotionActive(NarutoEffects.chakraRestore)){
                            ParticleEffects.addEffect(2, playerMP);
                        }
                        else{
                            ParticleEffects.addEffect(1, playerMP);
                        }
                    }
                    // add code for chakra charging such as the animation
                }
            } else if (ChakraCharge) {
                ChakraChargeDelay = 30;
                ChakraCharge = false;
                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                try {
                    outputStream.writeInt(110);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));
            } else if (ChakraChargeDelay != 30) {
                ChakraChargeDelay = 30;
            }
            if (JutsuCasting) {
                if (JutsuKeyDelay <= 0) {
                    JutsuCasting = false;
                    if (JutsuClient.canCast(Integer.parseInt(JutsuCombo), playerMP)) {
                        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                        DataOutputStream outputStream = new DataOutputStream(bos);
                        try {
                            outputStream.writeInt(Integer.parseInt(JutsuCombo));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));

                    }
                    JutsuCombo = "";
                } else {
                    JutsuKeyDelay -= 1;
                }
            }

            if (PlayerClientTickEvent.chakraDash) {
                if (PlayerClientTickEvent.chakra > 0.5F) {
                    PlayerClientTickEvent.chakraCooldown = 30;

                    double possibility = Math.random();
                    if (possibility >= 0.2F) {
                            if(playerMP.isPotionActive(NarutoEffects.chakraRestore)){
                                ParticleEffects.addEffect(2, playerMP);
                            }
                            else{
                                ParticleEffects.addEffect(1, playerMP);
                            }
                    }
                } else {
                    PlayerClientTickEvent.chakraDash = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraDash.nochakra")));
                }
            }

            if (PlayerClientTickEvent.waterWalking) {
                if (PlayerClientTickEvent.chakra > 0.1F) {
                    PlayerClientTickEvent.chakraCooldown = 30;

                    int i = (int) Math.round(playerMP.posY - 0.4);
                    Block j = playerMP.worldObj.getBlock((int) playerMP.posX, i - 2, (int) playerMP.posZ);


                    int c = (int) Math.round(playerMP.posY - 0.4);
                    Block k = playerMP.worldObj.getBlock((int) playerMP.posX, c - 1, (int) playerMP.posZ);

                    if (!((j == Blocks.water || j == Blocks.flowing_water) && playerMP.motionY < 0.0D) && onWater && Keyboard.isKeyDown(spaceKeyCode)) {
                        useChakra(5F);
                    }

                    onWater = false;

                    if (k == Blocks.water || k == Blocks.flowing_water) {
                        PlayerClientTickEvent.chakra -= 1F;
                        playerMP.motionY += 0.2D;
                        if (playerMP.motionY > 0.7D) {
                            playerMP.motionY = 0.7D;

                        }
                    } else if ((j == Blocks.water || j == Blocks.flowing_water) && playerMP.motionY < 0.0D) {
                        useChakra(0.12F);
                        //PlayerClientTickEvent.chakra -= 0.12F;
                        playerMP.motionY = 0.0D;
                        playerMP.onGround = true;

                        onWater = true;

                        if (playerMP.fallDistance > 4F) {
                            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                            DataOutputStream outputStream = new DataOutputStream(bos);
                            try {
                                outputStream.writeInt(4);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));
                        }
                    }

                    double possibility = Math.random();
                    if (possibility >= 0.2F) {
                        if(playerMP.isPotionActive(NarutoEffects.chakraRestore)){
                            ParticleEffects.addEffect(2, playerMP);
                        }
                        else{
                            ParticleEffects.addEffect(1, playerMP);
                        }
                    }
                } else {
                    PlayerClientTickEvent.waterWalking = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough chakra so Water Walking was disabled!"));
                }
            }

            PlayerClientTickEvent.isChakraFocus = ChakraFocus;

            if (PlayerClientTickEvent.chakraDash) {
                useChakra(0.1F);
                //PlayerClientTickEvent.chakra -= 0.1;
            }

            if (PlayerClientTickEvent.chakraDash) {
                if (playerMP.onGround) {
                    if (!playerMP.isInWater()) {
                        int i = (int) Math.round(playerMP.posY - 0.4);
                        Block j = playerMP.worldObj.getBlock((int) playerMP.posX, i - 2, (int) playerMP.posZ);
                        if (!(j.getMaterial() == Material.water)) {
                            playerMP.motionX *= 1.18F;
                            playerMP.motionZ *= 1.18F;
                        }
                    }
                }
            }

            for(int i = 0; i < doubleTapTime.length; i++){
                if(doubleTapTime[i] > 0){
                    doubleTapTime[i] -= 1;
                }

            }

            if (playerMP.onGround) {
                hasDoubleJumped = false;
                doubleJumpReady = false;
            } else if (!Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed() && !hasDoubleJumped && !doubleJumpReady) {
                doubleJumpReady = true;
            } else if (Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed() && !hasDoubleJumped && playerMP.isAirBorne && doubleJumpReady && useChakra(3F) /**PlayerClientTickEvent.chakra >= 5F*/) {

                playerMP.setVelocity(playerMP.motionX, 0.5F, playerMP.motionZ);
                hasDoubleJumped = true;
                doubleJumpReady = false;
                playerMP.fallDistance = 0.0F;
                ParticleEffects.addEffect(3, playerMP);
                //PlayerClientTickEvent.chakra -= 3F;
                PlayerClientTickEvent.chakraCooldown = 30;

                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                try {
                    outputStream.writeInt(4);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));

                bos = new ByteArrayOutputStream(8);
                outputStream = new DataOutputStream(bos);
                try {
                    outputStream.writeInt(7);
                    outputStream.writeDouble(playerMP.posX);
                    outputStream.writeDouble(playerMP.posY);
                    outputStream.writeDouble(playerMP.posZ);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                PacketDispatcher.sendPacketToServer(new ServerSoundPacket(bos.toByteArray()));

            } else if (Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed() && !hasDoubleJumped && PlayerClientTickEvent.chakra <= 5F && doubleJumpReady) {
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough chakra to double jump."));
                doubleJumpReady = false;
            }

            lastX = playerMP.posX;
            lastY = playerMP.posY;
            lastZ = playerMP.posZ;

            if (PlayerClientTickEvent.chakra > PlayerClientTickEvent.maxChakra) {
                PlayerClientTickEvent.chakra = PlayerClientTickEvent.maxChakra;
            }
        }
    }

    // Takes seeing as most of where this is used takes away chakra, only charging adds, so put it as a -
    public static boolean useChakra(float chakraUse) {
        if(chakra - chakraUse < 0){
            chakra = 0;
            return false;
        }
        else if(chakra - chakraUse > maxChakra){
            chakra = maxChakra;
            return false;
        }
        else{
            chakra -= chakraUse;
            return true;
        }
    }
}
