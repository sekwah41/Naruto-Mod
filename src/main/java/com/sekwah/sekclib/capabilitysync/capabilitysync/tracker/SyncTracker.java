package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

import com.sekwah.sekclib.capabilitysync.SyncEntry;

import java.util.Objects;

/**
 * For tracking the specific data entry. This will contain the main logic,
 * though each supported type should have its own special sync tracker.
 *
 * It will also contain the logic on how to add and remove the data from the packet.
 *
 * If you are going to add tracker types that are not custom classes please consider making a pr to the main library.
 */
public class SyncTracker implements ISyncTrackerData {

    protected SyncEntry syncEntry;
    /**
     * Will always start as null. Will only update when the value when ticks reach 0 and the value has been updated.
     * If data is forced to be sent, then this will sync up all the clients to exactly the same state even if joined later.
     */
    protected Object sendValue;

    /**
     * Whenever the data is sent this will be set back to the minTicks value from this.syncEntry.
     */
    protected int minTicksLeft = 0;

    protected boolean markedForSend = false;

    public SyncTracker(SyncEntry syncEntry) {
        this.syncEntry = syncEntry;
    }

    /**
     * Update the ticks left and clear any info on if it should be sent this tick.
     * @param data
     */
    public void tick(Object data) throws Throwable {
        if(--this.minTicksLeft <= 0) {
            Object currentData = syncEntry.getGetter().invoke(data);
            if(this.shouldSend(currentData)) {
                this.markedForSend = true;
                if(syncEntry.getSerializer() instanceof SyncTrackerClone cloner) {
                    this.sendValue = cloner.clone(currentData);
                } else {
                    this.sendValue = currentData;
                }
                this.minTicksLeft = this.syncEntry.getMinTicks();
            } else {
                this.markedForSend = false;
            }
        } else {
            this.markedForSend = false;
        }
    }

    public SyncEntry getSyncEntry() {
        return this.syncEntry;
    }

    @Override
    public Object getSendValue() {
        return this.sendValue;
    }

    /**
     * This will only be called when the minTicks has passed.
     * @param currentValue - The current server side value
     * @return
     */
    protected boolean shouldSend(Object currentValue) {
        return !Objects.equals(this.sendValue, currentValue);
    }

    public boolean isMarkedForSend() {
        return markedForSend;
    }
}
