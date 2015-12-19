package sekwah.mods.narutomod.common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class PlayerCommonTickEvent {

    @SubscribeEvent
    public void tick(ServerTickEvent event) {
        /**DataWatcher dw = player.getDataWatcher();
         if((!dw.getWatchableObjectString(20).equals("default") || !dw.getWatchableObjectString(26).equals("default")) && !dw.getWatchableObjectString(20).equals(dw.getWatchableObjectString(26))){
         if(PlayerPoseAnimator.getAnimTicks(dw.getWatchableObjectString(20)) > dw.getWatchableObjectInt(25)){
         dw.updateObject(25, dw.getWatchableObjectInt(25) + 1); // used to progress the animation.
         }
         else{
         dw.updateObject(25, 0);
         }

         if(PlayerPoseAnimator.getAnimTicks(dw.getWatchableObjectString(20)) <= dw.getWatchableObjectInt(25)){
         dw.updateObject(26, dw.getWatchableObjectString(20)); // used to progress the animation.
         }
         }*/
    }
}
