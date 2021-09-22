package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.List;

public class CapabilityTracker {

    private List<SyncTracker> syncTrackerList = new ArrayList<>();
    private Capability<?> capability;

    public CapabilityTracker(Capability<?> capClass) {
        this.capability = capClass;
    }

    public void addSyncTracker(SyncTracker syncTracker) {
        this.syncTrackerList.add(syncTracker);
    }

    public Capability<?> getCapability() {
        return this.capability;
    }

    public List<SyncTracker> getSyncTrackers() {
        return this.syncTrackerList;
    }
}
