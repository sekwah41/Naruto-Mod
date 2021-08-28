package com.sekwah.narutomod.events;

import com.sekwah.narutomod.NarutoMod;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEvents {

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        // Do chakra updates and other stuff here unless handled in capabilities
    }

// Handle if they have some agility perk or leaps.
//
//    @SubscribeEvent
//    public void livingFall(LivingFallEvent event) {
//        if (event.getEntity() instanceof Player player){
//          //NarutoMod.logger.info(event.distance);
//            if(event.distance < 9){
//                event.distance *= 0.3f;
//            }
//            if(event.distance > 3) {
//                event.distance -= 5f;
//                event.distance *= 0.6f;
//            }
//        }
//    }

}
