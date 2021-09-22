package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import net.minecraft.network.FriendlyByteBuf;

public class IntSyncTracker extends SyncTracker<Integer> {

    public IntSyncTracker(SyncEntry syncEntry) {
        super(syncEntry);
    }

    @Override
    public void encode(Integer objectToSend, FriendlyByteBuf outBuffer) {

    }

    @Override
    public Integer decode(FriendlyByteBuf inBuffer) {
        return null;
    }
}
