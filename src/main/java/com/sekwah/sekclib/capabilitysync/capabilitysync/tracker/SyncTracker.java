package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

import com.sekwah.sekclib.capabilitysync.SyncEntry;
import net.minecraft.network.FriendlyByteBuf;

/**
 * For tracking the specific data entry. This will contain the main logic,
 * though each supported type should have its own special sync tracker.
 *
 * It will also contain the logic on how to add and remove the data from the packet.
 *
 * If you are going to add tracker types that are not custom classes please consider making a pr to the main library.
 */
public abstract class SyncTracker<T> {
    private SyncEntry syncEntry;
    /**
     * Will always start as null.
     */
    private T lastSentValue;

    /**
     * Whenever the data is sent this will be set back to the minTicks value from this.syncEntry.
     */
    private int minTicksLeft = 0;

    public SyncTracker(SyncEntry syncEntry) {
        this.syncEntry = syncEntry;
    }

    public void setSyncEntry(SyncEntry syncEntry) {
        this.syncEntry = syncEntry;
    }

    public abstract void encode(T objectToSend, FriendlyByteBuf outBuffer);

    public abstract T decode(FriendlyByteBuf inBuffer);

    public void sent() {
        this.minTicksLeft = this.syncEntry.getMinTicks();
    }

    /**
     * This will only be called when the minTicks has passed.
     * @param currentValue - The current server side value
     * @return
     */
    public abstract boolean shouldSend(T currentValue);
}
