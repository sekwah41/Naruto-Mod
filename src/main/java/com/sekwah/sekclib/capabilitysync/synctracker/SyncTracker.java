package com.sekwah.sekclib.capabilitysync.synctracker;

import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.annotation.Sync;

/**
 * For tracking the specific data entry. This will contain the main logic,
 * though each supported type should have its own special sync tracker.
 *
 * It will also contain the logic on how to add and remove the data from the packet.
 *
 * If you are going to add tracker types that are not custom classes please consider making a pr to the main library.
 */
public abstract class SyncTracker<T extends Object> {
    private SyncEntry syncEntry;
    private T lastSentValue;
    private int minTicksLeft = 0;

    public SyncTracker(SyncEntry syncEntry) {
        this.syncEntry = syncEntry;
    }

    public void setSyncEntry(SyncEntry syncEntry) {
        this.syncEntry = syncEntry;
    }

    public abstract boolean equalsLastSentValue(T newValue);
}
