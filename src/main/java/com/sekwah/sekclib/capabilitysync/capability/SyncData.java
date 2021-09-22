package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.CapabilityTracker;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SyncData implements ISyncData, ICapabilityProvider {

    private final LazyOptional<ISyncData> holder = LazyOptional.of(() -> this);

    private List<CapabilityTracker> capabilityTrackers = new ArrayList<>();

    public void addCapabilityTracker(CapabilityTracker syncTracker) {
        capabilityTrackers.add(syncTracker);
    }

    public List<CapabilityTracker> getCapabilityTrackers() {
        return capabilityTrackers;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return SyncDataCapabilityHandler.SYNC_DATA.orEmpty(cap, holder);
    }
}
