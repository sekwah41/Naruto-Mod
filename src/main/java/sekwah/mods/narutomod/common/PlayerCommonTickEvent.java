package sekwah.mods.narutomod.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class PlayerCommonTickEvent {

    @SubscribeEvent
    public void tick(ServerTickEvent event) {
        /**DataWatcher dw = player.getDataWatcher();
         if((!dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals("default") || !dw.getWatchableObjectString(DataWatcherIDs.lastPose).equals("default")) && !dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals(dw.getWatchableObjectString(DataWatcherIDs.lastPose))){
         if(PlayerPoseAnimator.getAnimTicks(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose)) > dw.getWatchableObjectInt(25)){
         dw.updateObject(DataWatcherIDs.animationTick, dw.getWatchableObjectInt(25) + 1); // used to progress the animation.
         }
         else{
         dw.updateObject(DataWatcherIDs.animationTick, 0);
         }

         if(PlayerPoseAnimator.getAnimTicks(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose)) <= dw.getWatchableObjectInt(25)){
         dw.updateObject(DataWatcherIDs.lastPose, dw.getWatchableObjectString(DataWatcherIDs.jutsuPose)); // used to progress the animation.
         }
         }*/
    }
}
