package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.List;

public class CapabilityTracker {

    private List<SyncTracker> syncTrackerList = new ArrayList<>();
    private CapabilityEntry capabilityEntry;

    public CapabilityTracker(CapabilityEntry capClass) {
        this.capabilityEntry = capClass;
    }

    public void addSyncTracker(SyncTracker syncTracker) {
        this.syncTrackerList.add(syncTracker);
    }

    public CapabilityEntry getCapabilityEntry() {
        return this.capabilityEntry;
    }

    public Capability<?> getCapability() {
        return this.capabilityEntry.getCapability();
    }

    public List<SyncTracker> getSyncTrackers() {
        return this.syncTrackerList;
    }
}
