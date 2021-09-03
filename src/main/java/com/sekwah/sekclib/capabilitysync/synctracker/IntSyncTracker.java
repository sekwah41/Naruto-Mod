package com.sekwah.sekclib.capabilitysync.synctracker;

import com.sekwah.sekclib.capabilitysync.SyncEntry;

public class IntSyncTracker extends SyncTracker<Integer> {

    public IntSyncTracker(SyncEntry syncEntry) {
        super(syncEntry);
    }

    @Override
    public boolean equalsLastSentValue(Integer newValue) {
        return false;
    }
}
