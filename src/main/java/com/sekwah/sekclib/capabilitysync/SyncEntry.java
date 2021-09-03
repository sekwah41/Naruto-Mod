package com.sekwah.sekclib.capabilitysync;

import java.lang.reflect.Field;

public class SyncEntry {
    private String name;
    private Field field;
    private int minTicks;
    private boolean syncGlobally;

    public SyncEntry(String name, Field field, int minTicks, boolean syncGlobally) {
        this.name = name;
        this.field = field;
        this.minTicks = minTicks;
        this.syncGlobally = syncGlobally;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

    public int getMinTicks() {
        return minTicks;
    }

    public boolean isSyncGlobally() {
        return syncGlobally;
    }
}
