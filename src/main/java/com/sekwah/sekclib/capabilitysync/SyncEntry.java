package com.sekwah.sekclib.capabilitysync;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

public class SyncEntry {

    private final Field field;
    private final SyncTrackerSerializer syncTrackerSerializer;

    /**
     * Which id for the current class. Used for encoding and decoding over the network.
     */
    private final int trackerId;
    private final String name;
    private final MethodHandle getter;
    private final MethodHandle setter;
    private final int minTicks;
    private final boolean syncGlobally;

    public SyncEntry(String name, Field field, int minTicks, int trackerId, boolean syncGlobally, SyncTrackerSerializer syncTrackerSerializer) throws IllegalAccessException {
        this.name = name;
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        this.getter = lookup.unreflectGetter(field);
        this.setter = lookup.unreflectSetter(field);
        this.field = field;
        this.trackerId = trackerId;
        this.minTicks = minTicks;
        this.syncGlobally = syncGlobally;
        this.syncTrackerSerializer = syncTrackerSerializer;
    }

    public String getName() {
        return this.name;
    }

    public Field getField() {
        return this.field;
    }

    public int getMinTicks() {
        return this.minTicks;
    }

    public boolean isSyncGlobally() {
        return this.syncGlobally;
    }

    public MethodHandle getGetter() {
        return this.getter;
    }

    public MethodHandle getSetter() {
        return this.setter;
    }

    public SyncTrackerSerializer getSerializer() {
        return this.syncTrackerSerializer;
    }

    public int getTrackerId() {
        return trackerId;
    }
}
