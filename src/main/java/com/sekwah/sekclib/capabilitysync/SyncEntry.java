package com.sekwah.sekclib.capabilitysync;

import java.lang.invoke.MethodHandle;

public class SyncEntry {
    private String name;
    private MethodHandle getter;
    private MethodHandle setter;
    private int minTicks;
    private boolean syncGlobally;

    public SyncEntry(String name, MethodHandle getter, MethodHandle setter, int minTicks, boolean syncGlobally) {
        this.name = name;
        this.getter = getter;
        this.setter = setter;
        this.minTicks = minTicks;
        this.syncGlobally = syncGlobally;
    }

    public String getName() {
        return this.name;
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
}
