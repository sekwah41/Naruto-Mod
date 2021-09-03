package com.sekwah.sekclib.api.capability;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.synctracker.SyncTrackerFactory;
import com.sekwah.sekclib.capabilitysync.annotation.Sync;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.LoadingFailedException;
import net.minecraftforge.fml.ModLoadingException;
import net.minecraftforge.fml.ModLoadingStage;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Currently only designed for players though if there is demand we can add entity syncing too.
 */
public class CapabilitySyncRegistry {

    private static final Map<String, CapabilityEntry> SYNC_PLAYER_CAPABILITIES_MAP = new HashMap<>();

    private static final List<CapabilityEntry> CAPABILITY_ENTRIES = new ArrayList<>();

    private static final List<Class> supportedTypes = new ArrayList();

    /**
     * @param capability the capability to process
     * @param clazz so that the fields can be pre-grabbed.
     */
    @java.lang.SuppressWarnings("squid:S3011")
    static void registerPlayerCap(Capability<?> capability, Class<?> clazz) {
        Field[] values = Arrays.stream(clazz.getDeclaredFields())
                .filter(value -> value.isAnnotationPresent(Sync.class)).toArray(Field[]::new);
        CapabilityEntry capabilityEntry = new CapabilityEntry(capability);
        CAPABILITY_ENTRIES.add(capabilityEntry);
        SYNC_PLAYER_CAPABILITIES_MAP.put(capability.getName(), capabilityEntry);
        List<ModLoadingException> errors = new ArrayList<>();
        for (Field field : values) {
            Sync sync = field.getAnnotation(Sync.class);
            if(!supportedTypes.contains(field.getType())) {
                String message = String.format("@Sync used on unsupported type %s. (Class: %s, Field: %s)", field.getType().getName(), clazz.getName(), field.getName());
                SekCLib.LOGGER.error(message);
                errors.add(new ModLoadingException(null, ModLoadingStage.COMMON_SETUP, message, null));
            }
            // So that it doesnt have to be accessable
            field.setAccessible(true);
            SyncEntry entry = new SyncEntry(field.getName(), field, sync.minTicks(), sync.syncGlobally());
            capabilityEntry.addSyncEntry(entry);
        }
        if(!errors.isEmpty()) {
            throw new LoadingFailedException(errors);
        }
    }

    static void registerSyncTrackerType(Class clazz, SyncTrackerFactory syncTracker) {
        // TODO start registering the map.
        supportedTypes.add(clazz);
    }


    /**
     * @return list of capabilities for syncing later
     */
    @java.lang.SuppressWarnings("squid:S1452")
    public static List<CapabilityEntry> getPlayerCapabilities() {
        return CAPABILITY_ENTRIES;
    }
}
