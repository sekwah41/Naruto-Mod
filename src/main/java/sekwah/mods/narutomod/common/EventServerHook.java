package sekwah.mods.narutomod.common;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.common.player.extendedproperties.PlayerInfo;


public class EventServerHook {

    // High should run before the others so people dont always think its my mod causing the issue...
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handleConstruction(EntityConstructing event) {


        if (event.entity instanceof EntityPlayer) {
            try {
                DataWatcher dw = event.entity.getDataWatcher();
                dw.addObject(DataWatcherIDs.jutsuPose /*20*/, "default"); // jutsu pose id (such as charging)
                dw.addObject(DataWatcherIDs.clan, "Undefined"); // current clan
                // current player
                //dw.addObject(22, 50);
                dw.addObject(DataWatcherIDs.eyerenderer /*23*/, Integer.valueOf(0)); // Eye renders (LIAMS SHITTY EYE TOGGLES)
                //dw.addObject(23, 50); // amount of chakra
                //dw.addObject(24, 0); // amount of kunai in player
                // Float.valueOf(0)
                dw.addObject(DataWatcherIDs.animationTick /*25*/, Float.valueOf(0)); // animationTick (used to add smooth animation for players to different poses, is currently edited by the client :P)
                dw.addObject(DataWatcherIDs.lastPose /*26*/, "default"); // lastpose (so the smooth animation works between poses)
                dw.addObject(DataWatcherIDs.poseClient /*27*/, "default"); // poseClient (the last pose the client updated(so it can change the animationTick back to 0))
                //dw.addObject(DataWatcherIDs.poseClient, 0); // could also possibly add a kunai throw tick.
            }
            catch(IllegalArgumentException e) {
                NarutoMod.logger.error("Problem with data watchers");
            }

            PlayerInfo.register((EntityPlayer) event.entity);

            /*Side side = FMLCommonHandler.instance().getEffectiveSide();
            if (side == Side.CLIENT) {
                event.entity.getEntityData().setString("lastposeClient", "default"); // this stores the last pose for the client but only client side
            }*/
        }
        // May be for whenever the player first joins

    }

    // Takes damage after 3
    @SubscribeEvent
    public void livingFall(LivingFallEvent event)
    {
        if (event.entityLiving instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            //NarutoMod.logger.info(event.distance);
            if(event.distance < 9){
                event.distance /= 3;
            }
            if(event.distance > 3){
                event.distance -= 3f;
                event.distance *= 0.7f;
                event.distance += 3f;
            }


        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving.isPotionActive(NarutoEffects.chakraRestore)) {
            if (event.entityLiving.getActivePotionEffect(NarutoEffects.chakraRestore).getDuration() == 0) {
                event.entityLiving.removePotionEffect(NarutoEffects.chakraRestore.id);
            }
            /**else{
             // Could add code to add effects here
             }*/
        }
    }

    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone event) {
        PlayerInfo.get(event.entityPlayer).copyData(PlayerInfo.get(event.original));
    }

    @SubscribeEvent
    public void onJoinWorld(EntityJoinWorldEvent event) {
        // If you have any non-DataWatcher fields in your extended properties that
        // need to be synced to the client, you must send a packet each time the
        // player joins the world; this takes care of dying, changing dimensions, etc.
        if (event.entity instanceof EntityPlayerMP) {
            EntityPlayer player = (EntityPlayer) event.entity;
            PlayerInfo.get(player).reloadDW();
            //PacketDispatcher.sendTo(new SyncPlayerPropsMessage((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
        }
    }

    @SubscribeEvent
    public void playerPunch(AttackEntityEvent event) {
        //EntityLivingBase attackedEntity = event.entityLiving;
        //event.target.attackEntityFrom(new EntityDamageSource("player", event.entityPlayer), 1);
    }

}
