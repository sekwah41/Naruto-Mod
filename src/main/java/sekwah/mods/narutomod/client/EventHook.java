package sekwah.mods.narutomod.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.client.render.DelayedRender;
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.common.player.extendedproperties.PlayerInfo;

public class EventHook {

    @SubscribeEvent
    public void postRender(TickEvent.RenderTickEvent event) {
        if(event.phase == TickEvent.Phase.START) {
            NarutoMod.delayedRenders.clear();
        }
    }

    @SubscribeEvent
    public void postRender(RenderWorldLastEvent event) {
        for(DelayedRender delayedRender : NarutoMod.delayedRenders) {
            delayedRender.render();
        }
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        int ticksPerFrame = 2;
        if(event.phase == TickEvent.Phase.START) {
            PlayerInfo playerInfo = PlayerInfo.get(event.player);
            int eyeStatus = event.player.getDataWatcher().getWatchableObjectInt(DataWatcherIDs.eyerenderer);
            if(eyeStatus == 0) {
                playerInfo.animateEyes = -1;
            }
            else {
                if(playerInfo.animateEyes == -1) {
                    if(eyeStatus == 1) {
                        playerInfo.animateEyes = 0;
                    }
                    else {
                        playerInfo.animateEyes = 5;
                    }
                }
                else if(eyeStatus == 1 && playerInfo.animateEyes > 0) {
                    /*if(++playerInfo.animateEyeTicks > ticksPerFrame) {
                        playerInfo.animateEyeTicks = 0;
                        playerInfo.animateEyes--;
                    }*/
                    playerInfo.animateEyes--;
                }
                else if((eyeStatus == 2 || eyeStatus == 3) && playerInfo.animateEyes < 5) {
                    /*if(++playerInfo.animateEyeTicks > ticksPerFrame) {
                        playerInfo.animateEyeTicks = 0;
                    }*/
                    playerInfo.animateEyes++;
                }
            }
        }
    }

    @SubscribeEvent
    public void onJoinWorld(EntityJoinWorldEvent event) {
        // If there are any non-DataWatcher fields in your extended properties that
        // need to be synced to the client, they must be sent in a packet each time the
        // player joins the world; this takes care of dying, changing dimensions, etc.
        if (event.entity instanceof EntityPlayer) {

            EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;
            if(event.entity == playerMP){
                PlayerInfo playerInfo = PlayerInfo.get((EntityPlayer) event.entity);
                if(!playerInfo.hasAskedToSetClan){
                    // Store more about if asked this session and stuff ^.^
                    playerInfo.hasAskedToSetClan = true;
                    // Some sorta code to detect the first spawn in
                    // TODO finish clan thing
                    //PlayerRenderTickEvent.showGUIScreen(new GuiClanSelectionMenu());
                }
            }

        }
    }

    //@SubscribeEvent
    //public void renderHand(RenderHandEvent event) {
        //System.out.println(NarutoSettings.experimentalFirstPerson);
        //GL11.glTranslated(0,50F,0);
        //event.setCanceled(NarutoSettings.experimentalFirstPerson);

    //}

    // so fog and stuffs
    // Litteraly all usefull stuff for rendering this and possibly camera transformations can be found in the EntityRenderer
    //  found at net.minecraft.client.renderer

    //FogColors
    // FogDensity possibly for genjutsu

    // One of the events may be able to be used to change the camera position, also look at the player render event and other stuff
    // to see which is appropriate to render the player :)
    //@SubscribeEvent
    //public void tick(EntityViewRenderEvent event) {
        //System.out.println(NarutoSettings.experimentalFirstPerson);
        //GL11.glTranslated(0,50F,0);
        //event.setCanceled(NarutoSettings.experimentalFirstPerson);

    //}

}
