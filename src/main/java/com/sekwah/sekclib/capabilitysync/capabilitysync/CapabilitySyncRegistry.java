package com.sekwah.sekclib.capabilitysync.capabilitysync;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerFactory;
import com.sekwah.sekclib.capabilitysync.capabilitysync.annotation.Sync;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.LoadingFailedException;
import net.minecraftforge.fml.ModLoadingException;
import net.minecraftforge.fml.ModLoadingStage;

import java.lang.invoke.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Currently only designed for players though if there is demand we can add entity syncing too.
 */
public class CapabilitySyncRegistry {

    private static final Map<Class, SyncTrackerFactory> CLASS_SYNC_TRACKER_FACTORY_MAP = new HashMap<>();

    /**
     * @param capability the capability to process
     * @param clazz so that the fields can be pre-grabbed.
     */
    @java.lang.SuppressWarnings("squid:S3011")
    static void registerPlayerCap(ResourceLocation resourceSyncName, Capability<?> capability, Class<?> clazz) {
        List<Field> values = Arrays.stream(clazz.getDeclaredFields())
                .filter(value -> value.isAnnotationPresent(Sync.class))
                .sorted(Comparator.comparing(Field::getName)).toList();
        CapabilityEntry capabilityEntry = new CapabilityEntry(resourceSyncName, capability);
        SekCLibRegistries.capabilityRegistry.register(capabilityEntry);
        List<ModLoadingException> errors = new ArrayList<>();
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        for (Field field : values) {
            Sync sync = field.getAnnotation(Sync.class);
            if(!CLASS_SYNC_TRACKER_FACTORY_MAP.containsKey(field.getType())) {
                String message = String.format("@Sync used on unsupported type %s. (Class: %s, Field: %s)", field.getType().getName(), clazz.getName(), field.getName());
                SekCLib.LOGGER.error(message);
                errors.add(new ModLoadingException(null, ModLoadingStage.COMMON_SETUP, message, null));
            }
            // So that it doesnt have to be accessible
            field.setAccessible(true);
            MethodHandle getter;
            MethodHandle setter;
            try {
                getter = lookup.unreflectGetter(field);
                CallSite getterCall = LambdaMetafactory.metafactory(lookup,
                        "getter", MethodType.methodType(field.getType()),
                        MethodType.methodType(field.getType()),
                        getter, getter.type());
                setter = lookup.unreflectSetter(field);
                CallSite setterCall = LambdaMetafactory.metafactory(lookup,
                        "setter", MethodType.methodType(field.getType()),
                        MethodType.methodType(field.getType()),
                        setter, setter.type());

                SyncEntry entry = new SyncEntry(field.getName(), getter, setter, getterCall, setterCall, sync.minTicks(), sync.syncGlobally());
                capabilityEntry.addSyncEntry(entry);
            } catch (IllegalAccessException | LambdaConversionException e) {
                String message = String.format("There was a problem un-reflecting (Class: %s, Field: %s)", clazz.getName(), field.getName());
                SekCLib.LOGGER.error(message);
                e.printStackTrace();
                errors.add(new ModLoadingException(null, ModLoadingStage.COMMON_SETUP, message, null));
            }
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
        return SekCLibRegistries.capabilityRegistry.getValues();
    }
}
