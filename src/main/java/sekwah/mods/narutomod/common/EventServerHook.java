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
import org.apache.commons.io.IOUtils;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.assets.JutsuData;
import sekwah.mods.narutomod.assets.JutsuDataServer;
import sekwah.mods.narutomod.common.player.extendedproperties.PlayerInfo;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.clientbound.ClientJutsuStatsPacket;
import sekwah.mods.narutomod.settings.NarutoSettings;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;


public class EventServerHook {

    // High should run before the others so people dont always think its my mod causing the issue...
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void handleConstruction(EntityConstructing event) {


        if (event.entity instanceof EntityPlayer) {
            try {
                DataWatcher dw = event.entity.getDataWatcher();
                dw.addObject(DataWatcherIDs.jutsuPose, "default"); // jutsu pose id (such as charging)
                dw.addObject(DataWatcherIDs.clan, "Undefined"); // current clan
                dw.addObject(DataWatcherIDs.eyerenderer, Integer.valueOf(0)); // Eye renders (LIAMS SHITTY EYE TOGGLES)
                dw.addObject(DataWatcherIDs.animationTick , Float.valueOf(0)); // animationTick (used to add smooth animation for players to different poses, is currently edited by the client :P)
                dw.addObject(DataWatcherIDs.lastPose , "default"); // lastpose (so the smooth animation works between poses)
                dw.addObject(DataWatcherIDs.poseClient , "default"); // poseClient (the last pose the client updated(so it can change the animationTick back to 0))
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
                event.distance *= 0.3f;
            }
            if(event.distance > 3) {
                event.distance -= 5f;
                event.distance *= 0.6f;
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

            //JutsuDataServer.refreshConfig();

            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try {
                outputStream.writeDouble(NarutoSettings.maxChakra);
                outputStream.writeDouble(NarutoSettings.maxStamina);

                outputStream.writeInt(JutsuDataServer.substitutionCost);
                outputStream.writeBoolean(JutsuDataServer.substitutionEnabled);

                outputStream.writeInt(JutsuDataServer.chibakuTenseiCost);
                outputStream.writeBoolean(JutsuDataServer.chibakuTenseiEnabled);

                outputStream.writeInt(JutsuDataServer.chidoriCost);
                outputStream.writeBoolean(JutsuDataServer.chidoriEnabled);

                outputStream.writeInt(JutsuDataServer.fireballCost);
                outputStream.writeBoolean(JutsuDataServer.fireballEnabled);

                outputStream.writeInt(JutsuDataServer.waterBulletCost);
                outputStream.writeBoolean(JutsuDataServer.waterBulletEnabled);

                outputStream.writeInt(JutsuDataServer.wallCost);
                outputStream.writeBoolean(JutsuDataServer.wallEnabled);

                outputStream.writeInt(JutsuDataServer.shadowCloneCost);
                outputStream.writeBoolean(JutsuDataServer.shadowCloneEnabled);

                outputStream.writeInt(JutsuDataServer.multiShadowCloneCost);
                outputStream.writeBoolean(JutsuDataServer.multiShadowCloneEnabled);

                outputStream.writeInt(JutsuDataServer.chibiShadowCloneCost);
                outputStream.writeBoolean(JutsuDataServer.chibiShadowCloneEnabled);

                outputStream.writeInt(JutsuDataServer.sekcCost);
                outputStream.writeBoolean(JutsuDataServer.sekcEnabled);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            // Issues are from client side though dont want to remove the customisability
            PacketDispatcher.sendPacketToPlayer(new ClientJutsuStatsPacket(bos.toByteArray()), (EntityPlayerMP) event.entity);
            IOUtils.closeQuietly(bos);

        }
    }

    @SubscribeEvent
    public void playerPunch(AttackEntityEvent event) {
        //EntityLivingBase attackedEntity = event.entityLiving;
        //event.target.attackEntityFrom(new EntityDamageSource("player", event.entityPlayer), 1);
    }

}
