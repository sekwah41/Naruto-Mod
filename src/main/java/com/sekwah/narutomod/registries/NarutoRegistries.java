package com.sekwah.narutomod.registries;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.clans.Clans;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

/**
 * can be retrieved using GameRegistry.findRegistry
 * e.g. GameRegistry.findRegistry(Jutsu.class)
 */
public class NarutoRegistries {

    public static ForgeRegistry<Ability> ABILITIES;
    public static ForgeRegistry<Clans> CLANS;

    public static final ResourceLocation ABILITY_REGISTRY_LOC = new ResourceLocation(NarutoMod.MOD_ID, "abilities");
    public static final ResourceLocation CLAN_REGISTRY_LOC = new ResourceLocation(NarutoMod.MOD_ID, "clans");

    public static void init(NewRegistryEvent event) {
        RegistryBuilder<Ability> abilities = new RegistryBuilder<>();
        abilities.setName(ABILITY_REGISTRY_LOC);
        event.create(abilities, (registry) -> ABILITIES = (ForgeRegistry<Ability>) registry);

        RegistryBuilder<Clans> clans = new RegistryBuilder<>();
        clans.setName(CLAN_REGISTRY_LOC);
        event.create(clans, (registry) -> CLANS = (ForgeRegistry<Clans>) registry);

    }
}
