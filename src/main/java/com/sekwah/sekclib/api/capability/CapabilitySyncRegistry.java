package com.sekwah.sekclib.api.capability;

import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.annotation.Sync;
import net.minecraftforge.common.capabilities.Capability;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Currently only designed for players though if there is demand we can add entity syncing too.
 */
public class CapabilitySyncRegistry {

    private static final Map<String, CapabilityEntry> SYNC_PLAYER_CAPABILITIES_MAP = new HashMap<>();

    private static final List<CapabilityEntry> CAPABILITY_ENTRIES = new ArrayList<>();

    /**
     * @param capability the capability to process
     * @param clazz so that the fields can be pre-grabbed.
     */
    @java.lang.SuppressWarnings("squid:S3011")
    public static void registerPlayerCap(Capability<?> capability, Class<?> clazz) {
        Field[] values = Arrays.stream(clazz.getDeclaredFields())
                .filter(value -> value.isAnnotationPresent(Sync.class)).toArray(Field[]::new);
        CapabilityEntry capabilityEntry = new CapabilityEntry(capability);
        CAPABILITY_ENTRIES.add(capabilityEntry);
        SYNC_PLAYER_CAPABILITIES_MAP.put(capability.getName(), capabilityEntry);
        for (Field field : values) {
            // So that it doesnt have to be accessable
            field.setAccessible(true);
            SyncEntry entry = new SyncEntry(field.getName(), field);
            capabilityEntry.addSyncEntry(entry);
        }
    }


    /**
     * @return list of capabilities for syncing later
     */
    @java.lang.SuppressWarnings("squid:S1452")
    public static List<CapabilityEntry> getPlayerCapabilities() {
        return CAPABILITY_ENTRIES;
    }
}
