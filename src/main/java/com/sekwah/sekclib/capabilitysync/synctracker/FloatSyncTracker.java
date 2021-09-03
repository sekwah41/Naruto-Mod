package com.sekwah.sekclib.capabilitysync.synctracker;

import com.sekwah.sekclib.capabilitysync.SyncEntry;

public class FloatSyncTracker extends SyncTracker<Float> {

    public FloatSyncTracker(SyncEntry syncEntry) {
        super(syncEntry);
    }

    @Override
    public boolean equalsLastSentValue(Float newValue) {
        return false;
    }
}
