package com.sekwah.narutomod.registries;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.clans.Clans;
import com.sekwah.narutomod.abilities.Ability;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

/**
 * can be retrieved using GameRegistry.findRegistry
 * e.g. GameRegistry.findRegistry(Jutsu.class)
 */
public class NarutoRegistries {

    private static IForgeRegistry<Ability> ABILITY;
    private static IForgeRegistry<Clans> CLANS;

    public static void init(RegistryEvent.NewRegistry event) {
        RegistryBuilder<Ability> abilities = new RegistryBuilder<>();
        abilities.setName(new ResourceLocation(NarutoMod.MOD_ID, "abilities"));
        abilities.setType(Ability.class);
        ABILITY = abilities.create();

        RegistryBuilder<Clans> clans = new RegistryBuilder<>();
        clans.setName(new ResourceLocation(NarutoMod.MOD_ID, "clans"));
        clans.setType(Clans.class);
        CLANS = clans.create();

    }
}
