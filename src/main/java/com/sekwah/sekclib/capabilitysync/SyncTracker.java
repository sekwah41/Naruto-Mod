package com.sekwah.sekclib.capabilitysync;

import java.lang.reflect.Field;

/**
 * For tracking the specific data entry
 */
public class SyncTracker {
    private String name;
    private Field field;

    public SyncTracker(String name, Field field) {
        this.name = name;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }
}
