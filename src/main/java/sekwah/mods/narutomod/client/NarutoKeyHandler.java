package sekwah.mods.narutomod.client;

import sekwah.mods.narutomod.client.gui.GuiJutsuMenu;
import sekwah.mods.narutomod.client.gui.GuiOptionsMenu;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerJutsuPacket;
import sekwah.mods.narutomod.packets.serverbound.ServerSoundPacket;
import sekwah.mods.narutomod.settings.NarutoSettings;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class NarutoKeyHandler {
    /**
     * Key index
     */
    public static final int JUTSU_KEY1 = 0;
    public static final int JUTSU_KEY2 = 1;
    public static final int JUTSU_KEY3 = 2;

    public static final int JUTSU_MENU = 3;
    public static final int OPTIONS_KEY = 4;

    public static final int LEAP_KEY = 5;
    /**
     * Key descriptions; use a language file to localize the description later
     */
    public static final String[] keyDesc = {"naruto.keys.key1", "naruto.keys.key2", "naruto.keys.key3", "naruto.keys.jutsumenu", "naruto.keys.options", "naruto.keys.leap"/*, "naruto.keys.sharingan"*/};
    public static final KeyBinding[] keys = new KeyBinding[keyDesc.length];
    public static boolean[] ispressed = {false, false, false, false, false, false/*, false*/};
    //private static boolean[] repeat = {false, false, false, false, false/**, false*/};

    public static boolean[] isVanillaPressed = {false, false, false};

    private static final int leapDelay = 10;

    /**
     * Default key values
     */
    private final int[] keyButtons = {Keyboard.KEY_C, Keyboard.KEY_V, Keyboard.KEY_B, Keyboard.KEY_J, Keyboard.KEY_O, Keyboard.KEY_X/*, Keyboard.KEY_NUMPAD1*/};

    public NarutoKeyHandler() {
        for (int i = 0; i < keyDesc.length; ++i) {
            keys[i] = new KeyBinding(keyDesc[i], keyButtons[i], "narutomod.keys.category");
            ClientRegistry.registerKeyBinding(keys[i]);
        }
        //keys[keyDesc.length - 1] = Minecraft.getMinecraft().gameSettings.keyBindLeft;
        //keys[keyDesc.length - 2] = Minecraft.getMinecraft().gameSettings.keyBindRight;
    }

    static void keyPressed(int keyID) {
        KeyBinding keyPressed = keys[keyID];
        boolean keyDown = keys[keyID].getIsKeyPressed();

        if (keyDown) {
            if (keys[keyID].getKeyDescription().equals("naruto.keys.key1") || keys[keyID].getKeyDescription().equals("naruto.keys.key2") || keys[keyID].getKeyDescription().equals("naruto.keys.key3")) {
                if (keys[keyID].getKeyDescription().equals("naruto.keys.key1")) {
                    PlayerClientTickEvent.ChargingChakra = true;
                }
                if (PlayerClientTickEvent.JutsuCombo.length() < 9) {
                    EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;

                    int jutsuSound = 0;
                    if (keys[keyID].getKeyDescription().equals("naruto.keys.key1")) {
                        PlayerClientTickEvent.JutsuCombo += "1";
                        jutsuSound = 1;
                    } else if (keys[keyID].getKeyDescription().equals("naruto.keys.key2")) {
                        PlayerClientTickEvent.JutsuCombo += "2";
                        jutsuSound = 2;
                    } else if (keys[keyID].getKeyDescription().equals("naruto.keys.key3")) {
                        PlayerClientTickEvent.JutsuCombo += "3";
                        jutsuSound = 3;
                    }

                    PlayerClientTickEvent.JutsuKeyDelay = NarutoSettings.jutsuDelay;
                    PlayerClientTickEvent.JutsuCasting = true;

                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try {
                        outputStream.writeInt(jutsuSound);
                        outputStream.writeDouble(playerMP.posX);
                        outputStream.writeDouble(playerMP.posY);
                        outputStream.writeDouble(playerMP.posZ);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    PacketDispatcher.sendPacketToServer(new ServerSoundPacket(bos.toByteArray()));
                } else {
                    EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;
                    playerMP.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + I18n.format("naruto.jutsu.comboLimit")));
                }
            } else if (keys[keyID].getKeyDescription().equals("naruto.keys.jutsumenu")) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiJutsuMenu());
            } else if (keys[keyID].getKeyDescription().equals("naruto.keys.options")) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiOptionsMenu());
                //NarutoMod.experimentalFirstPerson = !NarutoMod.experimentalFirstPerson;
                //System.out.println(NarutoMod.experimentalFirstPerson);
            } else if(keys[keyID].getKeyDescription().equals("naruto.keys.leap")) {
                // TODO add leap code and change the fall distance with a packet
                EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;
                if(playerMP.onGround){
                    // TODO add some falling damage stuff so you take some damage
                    /*if(PlayerClientTickEvent.stamina >= 20) {
                        if (PlayerClientTickEvent.onWater) {
                            PlayerClientTickEvent.useChakra(10F);
                        }
                        playerMP.setVelocity(playerMP.motionX + playerMP.getLookVec().xCoord * 1.5F, (playerMP.getLookVec().yCoord + 0.8F) * 0.7F
                         *//*1.2F*//*, playerMP.motionZ + playerMP.getLookVec().zCoord * 1.5F);
                        PlayerClientTickEvent.stamina -= 20;
                        PlayerClientTickEvent.setStaminaCooldown(80);
                    }*/
                    if(PlayerClientTickEvent.leapCooldown <= 0) {
                        PlayerClientTickEvent.leapCooldown = leapDelay;
                        if (PlayerClientTickEvent.stamina >= 20) {
                            if (PlayerClientTickEvent.onWater) {
                                PlayerClientTickEvent.useChakra(10F);
                            }
                            sendJutsuPacket(401);
                        }
                    }
                }
            }/* else if(keys[keyID].getKeyDescription().equals("naruto.keys.sharingan")){
                EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;
                if(playerMP.getCommandSenderName().endsWith("sekwah41")){

                }
            }*/
            //else if(keys[keyID].getKeyDescription().equals("Naruto Emotes")){
            //Minecraft.getMinecraft().displayGuiScreen(new GuiEmotes());
            //EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;
            //PacketAnimationUpdate.animationUpdate("waveEmote",playerMP);
            //}
        } else {
            if (keys[keyID].getKeyDescription().equals("naruto.keys.key1")) {
                PlayerClientTickEvent.ChargingChakra = false;
            }
        }
    }

    private static void sendJutsuPacket(int i) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            outputStream.writeInt(i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));
    }

    static void keyVanillaPressed(String keyDesc, boolean pressedDown) {
        if(pressedDown){
            EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;
            if(keyDesc.equals("key.left")) {
                // TODO add leap code and change the fall distance with a packet
                if(PlayerClientTickEvent.doubleTapTime[0] <= 0){
                    PlayerClientTickEvent.doubleTapTime[0] = 10;
                }
                else{
                    if(PlayerClientTickEvent.leapCooldown <= 0){
                        PlayerClientTickEvent.doubleTapTime[0] = 0;
                        if (playerMP.onGround && PlayerClientTickEvent.stamina >= 20) {
                            if (PlayerClientTickEvent.onWater) {
                                PlayerClientTickEvent.useChakra(10F);
                            }
                            sendJutsuPacket(403);
                        }
                    }

                    /*if(PlayerClientTickEvent.stamina >= 20){
                        if(playerMP.onGround){
                            if(PlayerClientTickEvent.onWater){
                                PlayerClientTickEvent.useChakra(5F);
                            }
                            Vec3 lookVector = playerMP.getLookVec();
                            lookVector.rotateAroundY((float) (Math.PI / 2F));
                            playerMP.setVelocity(playerMP.motionX + lookVector.xCoord * 1.5F, 0.5F *//*1.2F*//*, playerMP.motionZ + lookVector.zCoord * 1.5F);
                            PlayerClientTickEvent.stamina -= 20;
                            PlayerClientTickEvent.setStaminaCooldown(80);
                        }
                    }*/
                }
            }
            else if(keyDesc.equals("key.right")) {
                // TODO add leap code and change the fall distance with a packet
                if(PlayerClientTickEvent.doubleTapTime[1] <= 0){
                    PlayerClientTickEvent.doubleTapTime[1] = 10;
                }
                else{
                    if(PlayerClientTickEvent.leapCooldown <= 0) {
                        PlayerClientTickEvent.doubleTapTime[1] = 0;
                        if (playerMP.onGround && PlayerClientTickEvent.stamina >= 20) {
                            if (PlayerClientTickEvent.onWater) {
                                PlayerClientTickEvent.useChakra(10F);
                            }
                            sendJutsuPacket(404);
                        }
                    }
                    /*if(PlayerClientTickEvent.stamina >= 20) {
                        if (playerMP.onGround) {
                            if (PlayerClientTickEvent.onWater) {
                                PlayerClientTickEvent.useChakra(5F);
                            }
                            Vec3 lookVector = playerMP.getLookVec();
                            lookVector.rotateAroundY((float) (-Math.PI / 2F));
                            playerMP.setVelocity(playerMP.motionX + lookVector.xCoord * 1.1F, 0.5F *//*1.2F*//*, playerMP.motionZ + lookVector.zCoord * 1.1F);
                            PlayerClientTickEvent.stamina -= 20;
                            PlayerClientTickEvent.setStaminaCooldown(80);
                        }
                    }*/
                }
            }
            else if(keyDesc.equals("key.back")) {
                // TODO add leap code and change the fall distance with a packet, also find why going backwards isnt working
                if(PlayerClientTickEvent.doubleTapTime[2] <= 0){
                    PlayerClientTickEvent.doubleTapTime[2] = 10;
                }
                else{
                    if(PlayerClientTickEvent.leapCooldown <= 0) {
                        PlayerClientTickEvent.doubleTapTime[2] = 0;
                        if (playerMP.onGround && PlayerClientTickEvent.stamina >= 20) {
                            if (PlayerClientTickEvent.onWater) {
                                PlayerClientTickEvent.useChakra(10F);
                            }
                            sendJutsuPacket(402);
                        }
                    }
                    /*if(PlayerClientTickEvent.stamina >= 20) {
                        if (playerMP.onGround) {
                            if (PlayerClientTickEvent.onWater) {
                                PlayerClientTickEvent.useChakra(5F);
                            }
                            Vec3 lookVector = playerMP.getLookVec();
                            lookVector.rotateAroundY((float) (Math.PI));
                            playerMP.setVelocity(playerMP.motionX + lookVector.xCoord * 1.1F, 0.5F 1.2F, playerMP.motionZ + lookVector.zCoord * 1.1F);
                            PlayerClientTickEvent.stamina -= 20;
                            PlayerClientTickEvent.setStaminaCooldown(80);
                        }
                    }*/
                }
            }
        }
    }

    /**
     * KeyInputEvent is in the FML package, so we must register to the FML event bus
     */

    public KeyBinding[] getKeys() {
        return keys;
    }

    /*@SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        // This disables keypresses when in menus or the character can't be controlled
        if (FMLClientHandler.instance().getClient().inGameHasFocus) {
            if(Minecraft.getMinecraft().gameSettings.keyBindLeft.getIsKeyPressed()){

            }
            for (int i = 0; i < keys.length; i++) {
                if (ispressed[i] != keys[i].getIsKeyPressed()) {
                    ispressed[i] = keys[i].getIsKeyPressed();
                    keyPressed(i);
                }
            }
        }
        // if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
        //if (keys[CUSTOM_INV].getIsKeyPressed()) {
        //TutorialMain.packetPipeline.sendToServer(new OpenGuiPacket(TutorialMain.GUI_CUSTOM_INV));
        //}
        //}
    }*/
}
