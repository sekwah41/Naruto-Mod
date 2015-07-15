package sekwah.mods.narutomod.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import sekwah.mods.narutomod.client.gui.GuiClanSelectionMenu;
import sekwah.mods.narutomod.client.gui.GuiJutsuMenu;
import sekwah.mods.narutomod.player.extendedproperties.PlayerInfo;
import sekwah.mods.narutomod.settings.NarutoSettings;

public class EventHook {

    /**@ForgeSubscribe
     @SideOnly(Side.CLIENT)
     public void onLivingJumpEvent(LivingJumpEvent event)
     {
     if (event.entity instanceof EntityPlayer)
     {
     if (PlayerClientTickHandler.chakraDash)
     {
     event.entity.motionY += 0.2D;
     PlayerClientTickHandler.chakra -= 0.04F;

     ParticleEffects.addEffect(3, (EntityClientPlayerMP) event.entity);
     }
     }
     }*/


    // The tick event is in its own class
    /**@SubscribeEvent public void tick(ClientTickEvent event) {
    PlayerClientTickHandeler.firePlayerTick();
    //TickRegistry.registerTickHandler(new PlayerCommonTickHandler(), Side.SERVER);
    }*/

    /**@ForgeSubscribe public void renderWorldLastEvent(RenderWorldLastEvent evt)
    {
    // for extra render effects
    }*/

    /**@ForgeSubscribe
     @SideOnly(Side.CLIENT)
     public void entityAttacked(LivingAttackEvent event)
     {
     EntityLiving attackedEnt = event.entityLiving;
     DamageSource attackSource = event.source;
     }*/


    // First person and player render hooks.

    // http://www.minecraftforge.net/forum/index.php?topic=13315.0

    @SubscribeEvent
    public void onJoinWorld(EntityJoinWorldEvent event) {
        // If there are any non-DataWatcher fields in your extended properties that
        // need to be synced to the client, they must be sent in a packet each time the
        // player joins the world; this takes care of dying, changing dimensions, etc.
        if (event.entity instanceof EntityPlayer) {

            EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;
            if((EntityPlayer) event.entity == (EntityPlayer) playerMP){
                PlayerInfo playerInfo = PlayerInfo.get((EntityPlayer) event.entity);
                if(!playerInfo.hasAskedToSetClan){
                    // Store more about if asked this session and stuff ^.^
                    playerInfo.hasAskedToSetClan = true;
                    // Some sorta code to detect the first spawn in
                    PlayerRenderTickEvent.showGUIScreen(new GuiClanSelectionMenu());
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
