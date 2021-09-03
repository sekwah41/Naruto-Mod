package com.sekwah.sekclib.api.capability;

import com.sekwah.sekclib.capabilitysync.synctracker.SyncTrackerFactory;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

public class RegisterSyncTrackerTypeEvent extends Event implements IModBusEvent {

    public void registerSyncTracker(Class clazz, SyncTrackerFactory syncTracker) {
        CapabilitySyncRegistry.registerSyncTrackerType(clazz, syncTracker);

    }
}
