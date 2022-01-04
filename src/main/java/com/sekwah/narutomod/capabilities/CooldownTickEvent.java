package com.sekwah.narutomod.capabilities;

public class CooldownTickEvent {
    public int ticks;

    public CooldownTickEvent(int ticks) {
        this.ticks = ticks;
    }

    public void tick() {
        this.ticks--;
    }

    public boolean isComplete() {
        return this.ticks <= 0;
    }
}
