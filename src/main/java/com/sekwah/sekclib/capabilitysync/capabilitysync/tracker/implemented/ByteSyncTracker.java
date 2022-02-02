package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class ByteSyncTracker implements SyncTrackerSerializer<Byte> {

    @Override
    public void encode(Byte objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeByte(objectToSend);
    }

    @Override
    public Byte decode(FriendlyByteBuf inBuffer) {
        return inBuffer.readByte();
    }
}
