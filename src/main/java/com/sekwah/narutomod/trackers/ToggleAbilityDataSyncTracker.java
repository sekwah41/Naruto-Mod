package com.sekwah.narutomod.trackers;

import com.sekwah.narutomod.capabilities.toggleabilitydata.ToggleAbilityData;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerClone;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class ToggleAbilityDataSyncTracker implements SyncTrackerSerializer<ToggleAbilityData>, SyncTrackerClone<ToggleAbilityData> {

    @Override
    public void encode(ToggleAbilityData objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(objectToSend.getAbilitiesHashSet().size());
        for (ResourceLocation ability : objectToSend.getAbilitiesHashSet()) {
            outBuffer.writeUtf(ability.toString());
        }
    }

    @Override
    public ToggleAbilityData decode(FriendlyByteBuf inBuffer) {
        final int size = inBuffer.readInt();
        ToggleAbilityData data = new ToggleAbilityData(size);
        for(int i = 0; i < size; i++) {
            data.getAbilitiesHashSet().add(new ResourceLocation(inBuffer.readUtf()));
        }
        return data;
    }

    @Override
    public ToggleAbilityData clone(ToggleAbilityData data) {
        ToggleAbilityData cloned = new ToggleAbilityData(data.getAbilitiesHashSet().size());
        cloned.getAbilitiesHashSet().addAll(data.getAbilitiesHashSet());
        return cloned;
    }
}
