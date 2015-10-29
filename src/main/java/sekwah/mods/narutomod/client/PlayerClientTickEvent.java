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
import net.minecraft.entity.DataWatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import sekwah.mods.narutomod.generic.NarutoEffects;
import sekwah.mods.narutomod.packets.PacketAnimationUpdate;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerJutsuPacket;

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
    public static int staminaCooldown;

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
    private boolean firedChangeBack = false;
    private int animTime = 0; // checks how long the pose has been active for for certain poses(stops early change back)

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

            if (this.staminaCooldown > 0) {
                this.staminaCooldown--;
            } else if (this.stamina < this.maxStamina) {
                this.stamina += 0.3;
            }
            //NarutoMod.LOGGER.info(stamina);

            boolean ChakraFocus = false;

            int spaceKeyCode = Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode();

            EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;

            //PlayerInfo playerInfo = PlayerInfo.get(playerMP); // Possibly move all data over to this

            if (this.chakraCooldown > 0) {
                this.chakraCooldown--;
            } else if (this.chakra < this.maxChakra) {
                this.chakra += 0.025;
            }

            // Create a potion with an image using custom forge code.
            // Not activating for some reason
            if(playerMP.isPotionActive(NarutoEffects.chakraRestore) && this.chakra < this.maxChakra){
                int regenMultiplyer = playerMP.getActivePotionEffect(NarutoEffects.chakraRestore).getAmplifier();
                if(regenMultiplyer > 10){
                    this.chakra += 0.1 + 0.1 * 10;
                }
                else{
                    this.chakra += 0.1 + 0.1 * regenMultiplyer;
                }
             }

            if (lastX == playerMP.posX && lastY == playerMP.posY && lastZ == playerMP.posZ) {
                playerMoved = false;
            } else {
                playerMoved = true;
            }

            // Sorts out temp anims
            // Ends the gliding in air pose(possibly make a more efficient system or nicer looking one
            DataWatcher dw = playerMP.getDataWatcher();
            //NarutoMod.LOGGER.info("20:" + dw.getWatchableObjectString(20) + " 27:" + dw.getWatchableObjectString(20));
            if(dw.getWatchableObjectString(20).equals(dw.getWatchableObjectString(27)) && dw.getWatchableObjectString(20).equals("leapingforwardglide") ){
                animTime++;
                if(animTime > 10){
                    if(!firedChangeBack){
                        if(playerMP.onGround){
                            PacketAnimationUpdate.animationUpdate("default", playerMP);
                            firedChangeBack = true;
                        }

                    }
                }


            }
            else{
                firedChangeBack = false;
                animTime = 0;
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

                    if (this.chakra < this.maxChakra) {
                        this.chakra += 0.15;
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

            if (this.chakraDash) {
                if (this.chakra > 0.5F) {
                    this.setChakraCooldown(30);

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
                    this.chakraDash = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraDash.deactivate")));
                }
            }
            if (this.chakraDash) {
                if(this.stamina > 0.5f){
                    this.setStaminaCooldown(80);
                }
                else{
                    this.chakraDash = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraDash.deactivate")));
                }
            }


            if (this.waterWalking) {
                if (this.chakra > 0.1F) {
                    this.chakraCooldown = 30;

                    int i = (int) Math.round(playerMP.posY - 0.4);
                    Block j = playerMP.worldObj.getBlock((int) playerMP.posX, i - 2, (int) playerMP.posZ);


                    int c = (int) Math.round(playerMP.posY - 0.4);
                    Block k = playerMP.worldObj.getBlock((int) playerMP.posX, c - 1, (int) playerMP.posZ);

                    if (!((j == Blocks.water || j == Blocks.flowing_water) && playerMP.motionY < 0.0D) && onWater && Keyboard.isKeyDown(spaceKeyCode)) {
                        useChakra(5F);
                    }

                    onWater = false;

                    if (k == Blocks.water || k == Blocks.flowing_water) {
                        this.chakra -= 1F;
                        playerMP.motionY += 0.2D;
                        if (playerMP.motionY > 0.7D) {
                            playerMP.motionY = 0.7D;

                        }
                    } else if ((j == Blocks.water || j == Blocks.flowing_water) && playerMP.motionY < 0.0D) {
                        useChakra(0.12F);
                        //this.chakra -= 0.12F;
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
                    this.waterWalking = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough chakra so Water Walking was disabled!"));
                }
            }

            this.isChakraFocus = ChakraFocus;

            if (this.chakraDash) {
                if(playerMoved){
                    useChakra(0.05F);
                    useStamina(0.3f);
                }
                //this.chakra -= 0.1;
            }

            if (this.chakraDash) {
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
            } else if (Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed() && !hasDoubleJumped && playerMP.isAirBorne && doubleJumpReady
                    && /*useChakra(3F)*/ this.chakra >= 3F && this.stamina >= 15F) {

                playerMP.setVelocity(playerMP.motionX, 0.5F, playerMP.motionZ);
                hasDoubleJumped = true;
                doubleJumpReady = false;
                playerMP.fallDistance = 0.0F;
                ParticleEffects.addEffect(3, playerMP);
                this.chakra -= 3F;
                this.setChakraCooldown(30);
                this.stamina -= 15F;
                this.setStaminaCooldown(80);

                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                try {
                    outputStream.writeInt(4);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));

                SoundEffects.sendSound(playerMP, 7);

            } else if (Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed() && !hasDoubleJumped && this.chakra <= 5F && doubleJumpReady) {
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough chakra to double jump."));
                doubleJumpReady = false;
            }

            lastX = playerMP.posX;
            lastY = playerMP.posY;
            lastZ = playerMP.posZ;

            if (this.chakra > this.maxChakra) {
                this.chakra = this.maxChakra;
            }
            if(this.stamina > this.maxStamina){
                this.stamina = this.maxStamina;
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

    // Takes seeing as most of where this is used takes away chakra, only charging adds, so put it as a -
    public static boolean useStamina(float staminaUse) {
        if(stamina - staminaUse < 0){
            stamina = 0;
            return false;
        }
        else if(stamina - staminaUse > maxStamina){
            stamina = maxStamina;
            return false;
        }
        else{
            stamina -= staminaUse;
            return true;
        }
    }

    public static void setStaminaCooldown(int cooldown){
        if(staminaCooldown < cooldown){
            staminaCooldown = cooldown;
        }
    }

    public static void setChakraCooldown(int cooldown){
        if(chakraCooldown < cooldown){
            chakraCooldown = cooldown;
        }
    }
}
