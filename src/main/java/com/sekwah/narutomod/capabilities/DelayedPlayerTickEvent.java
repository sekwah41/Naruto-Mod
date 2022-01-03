package com.sekwah.narutomod.capabilities;

import net.minecraft.world.entity.player.Player;

import java.util.function.Consumer;

public class DelayedPlayerTickEvent {

    public Consumer<Player> consumer;
    public int ticks;

    public DelayedPlayerTickEvent(Consumer<Player> consumer, int ticks) {
        this.consumer = consumer;
        this.ticks = ticks;
    }

    public void tick() {
        this.ticks--;
    }

    public boolean shouldRun() {
        return this.ticks <= 0;
    }

    public void run(Player player) {
        this.consumer.accept(player);
    }
}
