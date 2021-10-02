package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

import com.sekwah.sekclib.capabilitysync.SyncEntry;

public class SyncTrackerData implements ISyncTrackerData {

    private final SyncEntry syncEntry;
    private final Object sendValue;

    public SyncTrackerData(SyncEntry syncEntry, Object sendValue) {
        this.syncEntry = syncEntry;
        this.sendValue = sendValue;
    }

    @Override
    public SyncEntry getSyncEntry() {
        return this.syncEntry;
    }

    @Override
    public Object getSendValue() {
        return this.sendValue;
    }
}
