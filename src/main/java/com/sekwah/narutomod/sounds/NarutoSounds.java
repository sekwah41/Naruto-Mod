package com.sekwah.narutomod.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);

    public static final RegistryObject<SoundEvent> KUNAI_THUD = register("kunai_thud");

    public static final RegistryObject<SoundEvent> NEEDLE_HIT = register("needle_hit");

    public static final RegistryObject<SoundEvent> SIZZLE = register("sizzle");

    public static final RegistryObject<SoundEvent> SEAL_A = register("seal_a");
    public static final RegistryObject<SoundEvent> SEAL_B = register("seal_b");
    public static final RegistryObject<SoundEvent> SEAL_C = register("seal_c");

    public static final RegistryObject<SoundEvent> LEAP = register("leap");

    public static final RegistryObject<SoundEvent> LONELY_MARCH = register("lonely_march");

    private static RegistryObject<SoundEvent> register(String key) {
        return SOUNDS.register(key, () -> new SoundEvent(new ResourceLocation(MOD_ID, key)));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
