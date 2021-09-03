package com.sekwah.sekclib.capabilitysync.synctracker.capability;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SyncData implements ISyncData, ICapabilityProvider {

    private final LazyOptional<ISyncData> holder = LazyOptional.of(() -> this);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return SyncDataCapabilityHandler.SYNC_DATA.orEmpty(cap, holder);
    }
}
