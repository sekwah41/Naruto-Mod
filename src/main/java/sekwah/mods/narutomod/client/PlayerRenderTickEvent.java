package sekwah.mods.narutomod.client;

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
import net.minecraft.entity.player.EntityPlayer;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.client.gui.GuiNarutoMainMenu;
import sekwah.mods.narutomod.player.RenderNinjaPlayer;

public class PlayerRenderTickEvent {

    public static float delta = 0;
    public static boolean hasFiredAnimationUpdate = false;
    private long lastTime;

    @SubscribeEvent
    public void tick(RenderTickEvent event) {
        GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
        if (guiscreen instanceof GuiMainMenu) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiNarutoMainMenu());
        } else if (guiscreen == null || guiscreen instanceof GuiInventory || guiscreen instanceof GuiChat) {

            double nsPerTick = 1000000000D / 120D;

            delta = 0;

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            if (delta >= 1) {
                lastTime = now;
            } else if (delta < 0) {
                lastTime = now;
                NarutoMod.LOGGER.error("[Naruto Mod] Your computer seems to have traveled back in time :O");
            }

            EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;

            // TODO get if the game is paused
            //double nsPerTick = 1000000000D / 120D; change that number to increase the update rate.

            Render renderer = RenderManager.instance.getEntityClassRenderObject(EntityPlayer.class);
            if (!(renderer instanceof RenderNinjaPlayer)) {
                RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderNinjaPlayer());
            }

            NarutoMod.entityAnimator.updateClient(playerMP, NarutoMod.entityAnimator.playerPoses);

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

            for (int i = 0; i < NarutoKeyHandler.keys.length; i++) {
                if (NarutoKeyHandler.ispressed[i] != NarutoKeyHandler.keys[i].getIsKeyPressed()) {
                    NarutoKeyHandler.ispressed[i] = NarutoKeyHandler.keys[i].getIsKeyPressed();
                    NarutoKeyHandler.keyPressed(i);
                }
            }
        }
    }
}