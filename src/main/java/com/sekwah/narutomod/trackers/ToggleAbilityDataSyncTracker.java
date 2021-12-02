package com.sekwah.narutomod.trackers;

import com.sekwah.narutomod.capabilities.ToggleAbilityData;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerClone;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class ToggleAbilityDataSyncTracker implements SyncTrackerSerializer<ToggleAbilityData>, SyncTrackerClone<ToggleAbilityData> {

    @Override
    public void encode(ToggleAbilityData objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(objectToSend.abilities.size());
        for (ResourceLocation ability : objectToSend.abilities) {
            outBuffer.writeUtf(ability.toString());
        }
    }

    @Override
    public ToggleAbilityData decode(FriendlyByteBuf inBuffer) {
        final int size = inBuffer.readInt();
        ToggleAbilityData data = new ToggleAbilityData(size);
        for(int i = 0; i < size; i++) {
            data.abilities.add(new ResourceLocation(inBuffer.readUtf()));
        }
        return data;
    }

    @Override
    public ToggleAbilityData clone(ToggleAbilityData data) {
        ToggleAbilityData cloned = new ToggleAbilityData(data.abilities.size());
        cloned.abilities.addAll(data.abilities);
        return cloned;
    }
}
