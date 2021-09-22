package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTracker;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Objects;

public class FloatSyncTracker extends SyncTracker<Float> {

    public FloatSyncTracker(SyncEntry syncEntry) {
        super(syncEntry);
    }

    @Override
    public void encode(Float objectToSend, FriendlyByteBuf outBuffer) {

    }

    @Override
    public Float decode(FriendlyByteBuf inBuffer) {
        return null;
    }
}
