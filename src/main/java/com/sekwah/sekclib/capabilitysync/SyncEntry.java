package com.sekwah.sekclib.capabilitysync;

import java.lang.reflect.Field;

public class SyncEntry {
    private String name;
    private Field field;

    public SyncEntry(String name, Field field) {
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