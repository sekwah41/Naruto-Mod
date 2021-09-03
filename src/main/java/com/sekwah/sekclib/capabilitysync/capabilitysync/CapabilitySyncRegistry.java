package com.sekwah.sekclib.capabilitysync.capabilitysync;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerFactory;
import com.sekwah.sekclib.capabilitysync.capabilitysync.annotation.Sync;
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

    private static final Map<Class, SyncTrackerFactory> CLASS_SYNC_TRACKER_FACTORY_MAP = new HashMap<>();

    /**
     * @param capability the capability to process
     * @param clazz so that the fields can be pre-grabbed.
     */
    @java.lang.SuppressWarnings("squid:S3011")
    static void registerPlayerCap(Capability<?> capability, Class<?> clazz) {
        Field[] values = Arrays.stream(clazz.getDeclaredFields())
                .filter(value -> value.isAnnotationPresent(Sync.class)).toArray(Field[]::new);
        CapabilityEntry capabilityEntry = new CapabilityEntry(capability);
        SYNC_PLAYER_CAPABILITIES_MAP.put(capability.getName(), capabilityEntry);
        List<ModLoadingException> errors = new ArrayList<>();
        for (Field field : values) {
            Sync sync = field.getAnnotation(Sync.class);
            if(!CLASS_SYNC_TRACKER_FACTORY_MAP.containsKey(field.getType())) {
                String message = String.format("@Sync used on unsupported type %s. (Class: %s, Field: %s)", field.getType().getName(), clazz.getName(), field.getName());
                SekCLib.LOGGER.error(message);
                errors.add(new ModLoadingException(null, ModLoadingStage.COMMON_SETUP, message, null));
            }
            // So that it doesnt have to be accessible
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
        CLASS_SYNC_TRACKER_FACTORY_MAP.put(clazz, syncTracker);
    }


    /**
     * @return list of capabilities for syncing later
     */
    public static Collection<CapabilityEntry> getPlayerCapabilities() {
        return SYNC_PLAYER_CAPABILITIES_MAP.values();
    }
}
