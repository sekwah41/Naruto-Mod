package com.sekwah.narutomod.trackers;

import com.sekwah.narutomod.capabilities.DoubleJumpData;
import com.sekwah.narutomod.capabilities.toggleabilitydata.ToggleAbilityData;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerClone;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerUpdater;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

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
