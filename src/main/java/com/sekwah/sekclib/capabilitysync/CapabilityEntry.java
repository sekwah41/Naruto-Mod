package com.sekwah.sekclib.capabilitysync;

import com.sekwah.narutomod.jutsus.Jutsu;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapabilityEntry extends ForgeRegistryEntry<CapabilityEntry> {
    private Capability<?> capability;

    private List<SyncEntry> syncEntries = new ArrayList<>();

    private Map<String, SyncEntry> syncEntryHashMap = new HashMap<>();

    public CapabilityEntry(ResourceLocation resourceSyncName, Capability<?> capability) {
        this.capability = capability;
        this.setRegistryName(resourceSyncName);
    }

    public Capability<?> getCapability() {
        return capability;
    }

    public List<SyncEntry> getSyncEntries() {
        return this.syncEntries;
    }

    public SyncEntry getSyncEntryByName(String key) {
        return this.syncEntryHashMap.get(key);
    }

    public void addSyncEntry(SyncEntry entry) {
        if(!this.syncEntryHashMap.containsKey(entry.getName())) {
            this.syncEntries.add(entry);
            this.syncEntryHashMap.put(entry.getName(), entry);
        }
    }
}
