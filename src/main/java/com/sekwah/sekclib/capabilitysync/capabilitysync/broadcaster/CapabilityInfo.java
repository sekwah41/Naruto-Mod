package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.ISyncTrackerData;

import java.util.ArrayList;
import java.util.List;

public class CapabilityInfo {
    public int capabilityId;
    public CapabilityEntry capability;
    public List<ISyncTrackerData> changedEntries = new ArrayList<>();
    /**
     * Only will be used for speeding up sending, there is no need to separate them on read.
     */
    public List<ISyncTrackerData> changedPrivateEntries = new ArrayList<>();

    public CapabilityInfo(int capabilityId, CapabilityEntry capability) {
        this.capabilityId = capabilityId;
        this.capability = capability;
    }
}
