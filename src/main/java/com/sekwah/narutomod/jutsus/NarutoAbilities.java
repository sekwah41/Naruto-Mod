package com.sekwah.narutomod.jutsus;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.jutsus.abilities.LeapAbility;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ObjectHolder;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

@ObjectHolder(NarutoMod.MOD_ID)
@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoAbilities {

    public static final DeferredRegister<Ability> ABILITY = DeferredRegister.create(Ability.class, MOD_ID);
    public static RegistryObject<LeapAbility> LEAP = ABILITY.register("leap", () -> new LeapAbility());

    public static void register(IEventBus eventBus) {
        ABILITY.register(eventBus);
    }
}
