package com.sekwah.narutomod.events;

import com.sekwah.narutomod.NarutoMod;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
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
    @SubscribeEvent
    public static void livingFall(LivingFallEvent event) {
        if (event.getEntity() instanceof Player player){
          //NarutoMod.logger.info(event.distance);
            float distance = event.getDistance();
            if(distance < 9){
                distance *= 0.3f;
            }
            if(distance > 3) {
                distance -= 5f;
                distance *= 0.6f;
            }
            event.setDistance(distance);
        }
    }

}
