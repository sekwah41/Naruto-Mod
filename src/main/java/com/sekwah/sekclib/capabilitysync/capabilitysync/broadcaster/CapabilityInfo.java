package com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;

import java.util.ArrayList;
import java.util.List;

public class CapabilityInfo {
    public int capabilityId;
    public List<SyncTracker> changedEntries = new ArrayList<>();
    public List<SyncTracker> changedPrivateEntries = new ArrayList<>();

    public CapabilityInfo(int capabilityId) {
        this.capabilityId = capabilityId;
    }
}
