package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class IntSyncTracker implements SyncTrackerSerializer {

    @Override
    public void encode(Object objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt((Integer) objectToSend);
    }

    @Override
    public Object decode(FriendlyByteBuf inBuffer) {
        return inBuffer.readInt();
    }
}
