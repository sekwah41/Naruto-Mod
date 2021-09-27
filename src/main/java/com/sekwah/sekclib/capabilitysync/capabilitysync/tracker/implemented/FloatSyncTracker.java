package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Objects;

public class FloatSyncTracker implements SyncTrackerSerializer<Float> {

    @Override
    public void encode(Float objectToSend, FriendlyByteBuf outBuffer) {

    }

    @Override
    public Float decode(FriendlyByteBuf inBuffer) {
        return null;
    }
}
