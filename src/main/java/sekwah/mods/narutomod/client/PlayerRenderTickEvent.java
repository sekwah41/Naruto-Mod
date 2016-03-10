package sekwah.mods.narutomod.client;

import cpw.mods.fml.common.gameevent.TickEvent;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.animation.NarutoAnimator;
import sekwah.mods.narutomod.player.RenderNinjaPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import sekwah.mods.narutomod.client.gui.GuiNarutoMainMenu;

public class PlayerRenderTickEvent {

    public static float delta = 0;
    public static boolean hasFiredAnimationUpdate = false;
    private static GuiScreen guiToShow = null;
    private static int renderDelay = 0;
    private long lastTime;

    @SubscribeEvent
    public void tick(RenderTickEvent event) {
        // add code to get if the game is paused and keeps setting to 0(else could cause problems in singleplayer
        // there are 1000000000D nanoseconds in a second 1ns = 1x10^-9 s. This divides the value to be a double in reguards to 1 second as 1
        // And then multiplies by 120 so 120 delta values pass each second :)
        if(event.phase == TickEvent.Phase.START){
            double nsPerTick = 1000000000D / 120D;



            long now = System.nanoTime();
            delta = (float) ((now - lastTime) / nsPerTick);
            lastTime = now;
            if (delta < 0) {
                lastTime = now;
                delta = 0;
                NarutoMod.logger.error(" Your computer seems to have traveled back in time :O");
            }

            GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
            if (guiscreen instanceof GuiMainMenu) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiNarutoMainMenu());
            } else if (guiscreen == null || guiscreen instanceof GuiInventory || guiscreen instanceof GuiChat) {

                if(guiToShow != null){
                    if(renderDelay <= 0){
                        NarutoMod.logger.info("Show gui");
                        Minecraft.getMinecraft().displayGuiScreen(guiToShow);
                        guiToShow = null;
                        return;
                    }
                    else{
                        renderDelay--;
                    }
                }

                // Time code used to be here

                EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;
                DataWatcher dw = playerMP.getDataWatcher();

                //

                // TODO get if the game is paused
                //double nsPerTick = 1000000000D / 120D; change that number to increase the update rate.

            /*Render renderer = RenderManager.instance.getEntityClassRenderObject(EntityPlayer.class);
            if (!(renderer instanceof RenderNinjaPlayer)) {
                RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, NarutoAnimator.playerRenderer);
            }*/

                NarutoAnimator.updateClient(playerMP, NarutoAnimator.playerPoses);

                // TODO finish this code so it works with the morph mod
                // also make sure that if the first person is set to false in the config it igores this completely
                //Render renderer = RenderManager.instance.getEntityRenderObject(playerMP);
                //if(renderer instanceof RenderPlayer){
                //	NarutoMod.experimentalFirstPerson = true;
                //}
                //else{
                //	NarutoMod.experimentalFirstPerson = false;
                //}

                //GL11.glTranslatef(0F, 0F, 0F);
            }
            if (guiscreen == null) {
                if(NarutoKeyHandler.isVanillaPressed[0] != Minecraft.getMinecraft().gameSettings.keyBindLeft.getIsKeyPressed()){
                    NarutoKeyHandler.isVanillaPressed[0] = Minecraft.getMinecraft().gameSettings.keyBindLeft.getIsKeyPressed();
                    NarutoKeyHandler.keyVanillaPressed("key.left", NarutoKeyHandler.isVanillaPressed[0]);
                }
                if(NarutoKeyHandler.isVanillaPressed[1] != Minecraft.getMinecraft().gameSettings.keyBindRight.getIsKeyPressed()){
                    NarutoKeyHandler.isVanillaPressed[1] = Minecraft.getMinecraft().gameSettings.keyBindRight.getIsKeyPressed();
                    NarutoKeyHandler.keyVanillaPressed("key.right", NarutoKeyHandler.isVanillaPressed[1]);
                }

                if(NarutoKeyHandler.isVanillaPressed[2] != Minecraft.getMinecraft().gameSettings.keyBindBack.getIsKeyPressed()){
                    NarutoKeyHandler.isVanillaPressed[2] = Minecraft.getMinecraft().gameSettings.keyBindBack.getIsKeyPressed();
                    NarutoKeyHandler.keyVanillaPressed("key.back", NarutoKeyHandler.isVanillaPressed[2]);
                }

                for (int i = 0; i < NarutoKeyHandler.keys.length; i++) {
                    if (NarutoKeyHandler.isPressed[i] != NarutoKeyHandler.keys[i].getIsKeyPressed()) {
                        NarutoKeyHandler.isPressed[i] = NarutoKeyHandler.keys[i].getIsKeyPressed();
                        NarutoKeyHandler.keyPressed(i);
                    }
                }
            }
        }
    }

    // Will show when there is no gui open
    public static void showGUIScreen(GuiScreen guiShow) {
        guiToShow = guiShow;
        renderDelay = 5;
    }
}