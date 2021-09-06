package com.sekwah.sekclib.capabilitysync;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;

public class SyncEntry {
    private String name;
    private MethodHandle getter;
    private MethodHandle setter;
    private CallSite getterCall;
    private CallSite setterCall;
    private int minTicks;
    private boolean syncGlobally;

    public SyncEntry(String name, MethodHandle getter, MethodHandle setter, CallSite getterCall, CallSite setterCall, int minTicks, boolean syncGlobally) {
        this.name = name;
        this.getter = getter;
        this.setter = setter;
        this.getterCall = getterCall;
        this.setterCall = setterCall;
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

    public CallSite getGetterCall() {
        return this.getterCall;
    }

    public CallSite getSetterCall() {
        return this.setterCall;
    }
}
