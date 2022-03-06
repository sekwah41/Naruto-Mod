package com.sekwah.narutomod.trackers;

import com.sekwah.narutomod.capabilities.DoubleJumpData;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerClone;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;

public class DoubleJumpDataSyncTracker implements SyncTrackerSerializer<DoubleJumpData>, SyncTrackerClone<DoubleJumpData> {

    @Override
    public void encode(DoubleJumpData objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeBoolean(objectToSend.canDoubleJumpServer);
    }

    @Override
    public DoubleJumpData decode(FriendlyByteBuf inBuffer) {
        return new DoubleJumpData(inBuffer.readBoolean());
    }

    @Override
    public DoubleJumpData clone(DoubleJumpData data) {
        DoubleJumpData cloned = new DoubleJumpData(data.canDoubleJumpServer);
        return cloned;
    }
}
