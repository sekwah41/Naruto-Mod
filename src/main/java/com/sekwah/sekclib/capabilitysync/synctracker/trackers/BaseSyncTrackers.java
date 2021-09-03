package com.sekwah.sekclib.capabilitysync.synctracker.trackers;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SekCLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BaseSyncTrackers {
    @SubscribeEvent
    public static void onServerStarting(RegisterSyncTrackerTypeEvent event) {
        event.registerSyncTracker(int.class, IntSyncTracker::new);
        event.registerSyncTracker(float.class, FloatSyncTracker::new);
    }
}
