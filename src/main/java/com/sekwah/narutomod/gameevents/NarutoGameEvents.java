package com.sekwah.narutomod.gameevents;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoGameEvents {

    public static final DeferredRegister<GameEvent> GAME_EVENTS = DeferredRegister.create(Registries.GAME_EVENT, MOD_ID);

    public static final RegistryObject<GameEvent> JUTSU_CASTING = GAME_EVENTS.register("jutsu_casting", () -> wardenAlert("jutsu_casting"));

    public static final RegistryObject<GameEvent> LEAP = GAME_EVENTS.register("leap", () -> wardenAlert("leap"));

    public static final RegistryObject<GameEvent> DOUBLE_JUMP = GAME_EVENTS.register("double_jump", () -> wardenAlert("double_jump"));

    public static GameEvent wardenAlert(String name) {
        return new GameEvent(name, 16);
    }


    public static void register(IEventBus eventBus) {
        GAME_EVENTS.register(eventBus);
    }
}
