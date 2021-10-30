package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class FloatSyncTracker implements SyncTrackerSerializer<Float> {

    @Override
    public void encode(Float objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeFloat(objectToSend);
    }

    @Override
    public Float decode(FriendlyByteBuf inBuffer) {
        return inBuffer.readFloat();
    }
}
