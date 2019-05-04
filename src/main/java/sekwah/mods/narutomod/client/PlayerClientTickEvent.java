package sekwah.mods.narutomod.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import sekwah.mods.narutomod.animation.NarutoAnimator;
import sekwah.mods.narutomod.client.gui.GuiNotificationUpdate;
import sekwah.mods.narutomod.client.player.RenderNinjaPlayer;
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.common.NarutoEffects;
import sekwah.mods.narutomod.items.NarutoItems;
import sekwah.mods.narutomod.jutsu.Jutsus;
import sekwah.mods.narutomod.packets.PacketAnimationUpdate;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerJutsuPacket;
import sekwah.mods.narutomod.settings.NarutoSettings;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class PlayerClientTickEvent {
    // to do with the keypress data for jutsus
    public static String JutsuCombo = "";

    public static int JutsuKeyDelay = 0;

    public static boolean JutsuCasting = false;

    public static boolean ChargingChakra = false;

    public static int[] doubleTapTime = {0,0,0};

    public static int leapCooldown = 20;


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

    private boolean rendersToReset = false;

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

        if (!(RenderManager.instance.entityRenderMap.get(EntityPlayer.class) instanceof RenderNinjaPlayer)) {
            RenderManager.instance.entityRenderMap.put(EntityPlayer.class, NarutoAnimator.playerRenderer);
            NarutoAnimator.playerRenderer.setRenderManager(RenderManager.instance);
        }

        GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
        if (guiscreen == null || guiscreen instanceof GuiInventory || guiscreen instanceof GuiChat) {

            if (staminaCooldown > 0) {
                staminaCooldown--;
            } else if (stamina < maxStamina) {
                stamina += 0.3;
            }
            //NarutoMod.logger.info(stamina);

            boolean ChakraFocus = false;

            int spaceKeyCode = Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode();

            EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;

            //PlayerInfo playerInfo = PlayerInfo.get(playerMP); // Possibly move all data over to this

            if (chakraCooldown > 0) {
                chakraCooldown--;
            } else if (chakra < maxChakra) {
                chakra += 0.025;
            }

            if(NarutoSettings.rainbowChakra){
                if(++NarutoSettings.chakraHue > 360){
                    NarutoSettings.chakraHue = 1;
                }
                NarutoSettings.recalculateHue();
            }

            // Create a potion with an image using custom forge code.
            // Not activating for some reason
            if(playerMP.isPotionActive(NarutoEffects.chakraRestore) && chakra < maxChakra){
                int regenMultiplyer = playerMP.getActivePotionEffect(NarutoEffects.chakraRestore).getAmplifier();
                if(regenMultiplyer > 10){
                    chakra += 0.1 + 0.1 * 10;
                }
                else{
                    chakra += 0.1 + 0.1 * regenMultiplyer;
                }
             }

            playerMoved = !(lastX == playerMP.posX && lastY == playerMP.posY && lastZ == playerMP.posZ);

            // Sorts out temp anims
            // Ends the gliding in air pose(possibly make a more efficient system or nicer looking one
            DataWatcher dw = playerMP.getDataWatcher();
            //NarutoMod.logger.info("20:" + dw.getWatchableObjectString(DataWatcherIDs.jutsuPose) + " 27:" + dw.getWatchableObjectString(DataWatcherIDs.jutsuPose));
            if(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals(dw.getWatchableObjectString(DataWatcherIDs.poseClient)) && (dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).startsWith("leapingforwardglide")
                    || dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).startsWith("leapingbackglide") || dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals("leapingleftglide") || dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals("leapingrightglide")) ){
                animTime++;
                if(animTime > 10){
                    if(!firedChangeBack){
                        if(playerMP.onGround || playerMP.isInWater() || playerMP.capabilities.isFlying){
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
                String currentPose = playerMP.getDataWatcher().getWatchableObjectString(DataWatcherIDs.jutsuPose);
                if(playerMP.isSprinting()) {
                    if(currentPose.equals("chakraCharging")) {
                        PacketAnimationUpdate.animationUpdate("chakraSprintCharging", playerMP);
                    }
                    chakra += 0.02;
                }
                else {
                    if(currentPose.equals("chakraSprintCharging")) {
                        PacketAnimationUpdate.animationUpdate("chakraCharging", playerMP);
                    }
                    chakra += 0.08;
                }
                if (ChakraChargeDelay >= 0) {
                    ChakraChargeDelay--;
                } else if (!ChakraCharge) {
                    ChakraCharge = true;

                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try {
                        outputStream.writeInt(101);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));
                }/* else if (ChakraCharge && playerMoved) {

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

                }*/ else {

                    if (chakra < maxChakra) {
                        if(playerMoved) {
                            if(playerMP.isSprinting()) {
                                chakra += 0.02;
                            }
                            else {
                                chakra += 0.08;
                            }
                        }
                        else {
                            chakra += 0.55;
                        }
                    }
                    ChakraFocus = true;

                    double possibility = Math.random();
                    if (possibility >= 0.3F) {
                        if(playerMP.isPotionActive(NarutoEffects.chakraRestore)){
                            ParticleEffects.addEffect(2, playerMP);
                        }
                        else{
                            //ParticleEffects.addEffect(1, playerMP);
                            ParticleEffects.addPlayersColouredSmoke(playerMP);
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
                    if(JutsuCombo.equals(Jutsus.RAINBOW_CHAKRA)){
                        NarutoSettings.rainbowChakra = !NarutoSettings.rainbowChakra;
                        if(NarutoSettings.rainbowChakra){
                            GuiNotificationUpdate.queueNotification(I18n.format("naruto.gui.settings"),
                                    "Rainbow chakra!! :D ",
                                    new ItemStack(NarutoItems.Kunai));
                        }
                        else{
                            GuiNotificationUpdate.queueNotification(I18n.format("naruto.gui.settings"),
                                    "Awww :(",
                                    new ItemStack(NarutoItems.Kunai));
                        }
                    }
                    else if (JutsuClient.canCast(Integer.parseInt(JutsuCombo), playerMP)) {
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

            if (chakraDash) {
                if (chakra > 0.5F) {
                    setChakraCooldown(30);

                    double possibility = Math.random();
                    if (possibility >= 0.2F) {
                            if(playerMP.isPotionActive(NarutoEffects.chakraRestore)){
                                ParticleEffects.addEffect(2, playerMP);
                            }
                            else{
                                ParticleEffects.addPlayersColouredSmoke(playerMP);
                            }
                    }
                } else {
                    chakraDash = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraDash.deactivate")));
                }
            }
            if (chakraDash) {
                if(stamina > 0.5f){
                    setStaminaCooldown(80);
                }
                else{
                    chakraDash = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.chakraDash.deactivate")));
                }
            }


            if (waterWalking) {
                if (chakra > 0.1F) {
                    chakraCooldown = 30;
                    //NarutoMod.logger.info(playerMP.getYOffset());
                    int i = (int) Math.round(playerMP.posY - playerMP.getYOffset() - 0.96f);
                    Block j = playerMP.worldObj.getBlock((int) playerMP.posX, i, (int) playerMP.posZ);


                    int c = (int) Math.round(playerMP.posY - playerMP.getYOffset() - 0.4f);
                    Block k = playerMP.worldObj.getBlock((int) playerMP.posX, c, (int) playerMP.posZ);

                    int h = (int) Math.round(playerMP.posY - playerMP.getYOffset() - 0.87f);
                    Block n = playerMP.worldObj.getBlock((int) playerMP.posX, h, (int) playerMP.posZ);

                    if (!((j == Blocks.water || j == Blocks.flowing_water) && playerMP.motionY < 0.0D) && onWater && Keyboard.isKeyDown(spaceKeyCode)) {
                        useChakra(3F);
                    }

                    onWater = false;

                    if (k == Blocks.water || k == Blocks.flowing_water) {
                        useChakra(1F);
                        playerMP.motionY += 0.2D;
                        if (playerMP.motionY > 0.6D) {
                            playerMP.motionY = 0.6D;
                        }
                    }
                    else if (n == Blocks.water || n == Blocks.flowing_water) {
                        useChakra(0.2F);
                        playerMP.motionY += 0.1D;
                        if (playerMP.motionY > 0.2D) {
                            playerMP.motionY = 0.2D;
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
                            ParticleEffects.addPlayersColouredSmoke(playerMP);
                        }
                    }
                } else {
                    waterWalking = false;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough chakra so Water Walking was disabled!"));
                }
            }

            isChakraFocus = ChakraFocus;

            if (chakraDash) {
                if(playerMoved){
                    useChakra(0.05F);
                    useStamina(0.2f);
                }
                //this.chakra -= 0.1;
            }

            if (chakraDash) {
                if (playerMP.onGround && !playerMP.isInWater()) {
                    if((Math.pow(playerMP.motionX,2) + Math.pow(playerMP.motionZ,2)) < 1.3f){
                        playerMP.motionX *= 1.18F;
                        playerMP.motionZ *= 1.18F;
                    }
                }
            }

            if(leapCooldown > 0){
                leapCooldown -= 1;
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
                    && /*useChakra(3F)*/ chakra >= 2F && stamina >= 5F) {

                playerMP.setVelocity(playerMP.motionX, 0.5F, playerMP.motionZ);
                hasDoubleJumped = true;
                doubleJumpReady = false;
                playerMP.fallDistance = 0.0F;
                ParticleEffects.addEffect(3, playerMP);
                chakra -= 2F;
                setChakraCooldown(30);
                stamina -= 5F;
                setStaminaCooldown(80);

                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                try {
                    outputStream.writeInt(4);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));

                SoundEffects.sendSound(playerMP, 7);

            } else if (Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed() && !hasDoubleJumped && chakra <= 5F && doubleJumpReady) {
                playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You do not have enough chakra to double jump."));
                doubleJumpReady = false;
            }

            lastX = playerMP.posX;
            lastY = playerMP.posY;
            lastZ = playerMP.posZ;

            if (chakra > maxChakra) {
                chakra = maxChakra;
            }
            if(stamina > maxStamina){
                stamina = maxStamina;
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
