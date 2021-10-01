package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.ISyncTrackerData;

import java.util.ArrayList;
import java.util.List;

public class CapabilityInfo {
    public int capabilityId;
    public List<ISyncTrackerData> changedEntries = new ArrayList<>();
    /**
     * Only will be used for speeding up sending, there is no need to separate them on read.
     */
    public List<ISyncTrackerData> changedPrivateEntries = new ArrayList<>();

    public CapabilityInfo(int capabilityId) {
        this.capabilityId = capabilityId;
    }
}
