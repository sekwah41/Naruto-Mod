package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class FloatSyncTracker implements SyncTrackerSerializer {

    @Override
    public void encode(Object objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeFloat((Float) objectToSend);
    }

    @Override
    public Object decode(FriendlyByteBuf inBuffer) {
        return inBuffer.readFloat();
    }
}
