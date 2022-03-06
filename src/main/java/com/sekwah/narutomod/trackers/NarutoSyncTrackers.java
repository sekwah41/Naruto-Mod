package com.sekwah.narutomod.trackers;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.capabilities.DoubleJumpData;
import com.sekwah.narutomod.capabilities.toggleabilitydata.ToggleAbilityData;
import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoSyncTrackers {
    @SubscribeEvent
    public static void registerSyncTrackerEvent(RegisterSyncTrackerTypeEvent event) {
        event.registerSyncTracker(ToggleAbilityData.class, new ToggleAbilityDataSyncTracker());
        event.registerSyncTracker(DoubleJumpData.class, new DoubleJumpDataSyncTracker());
    }
}
