package com.sekwah.sekclib.capabilitysync;

import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CapabilityEntry {
    private Capability<?> capability;

    private List<SyncEntry> syncEntries = new ArrayList<>();

    private Map<String, SyncEntry> syncEntryHashMap = new HashMap<>();

    public CapabilityEntry(Capability<?> capability) {
        this.capability = capability;
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
