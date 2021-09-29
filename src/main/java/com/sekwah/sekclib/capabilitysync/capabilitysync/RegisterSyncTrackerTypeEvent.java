package com.sekwah.sekclib.capabilitysync.capabilitysync;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

public class RegisterSyncTrackerTypeEvent extends Event implements IModBusEvent {

    public void registerSyncTracker(Class clazz, SyncTrackerSerializer syncTracker) {
        CapabilitySyncRegistry.registerSyncTrackerType(clazz, syncTracker);
    }
}
