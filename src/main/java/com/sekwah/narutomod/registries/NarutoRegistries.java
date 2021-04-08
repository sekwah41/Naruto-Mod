package com.sekwah.narutomod.registries;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.clans.Clans;
import com.sekwah.narutomod.jutsus.Jutsu;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.RegistryBuilder;

/**
 * can be retrieved using GameRegistry.findRegistry
 * e.g. GameRegistry.findRegistry(Jutsu.class)
 */
public class NarutoRegistries {

    /**
     * To be called during
     */
    public static void init() {
        RegistryBuilder<Jutsu> jutsu = new RegistryBuilder<>();
        jutsu.setName(new ResourceLocation(NarutoMod.MOD_ID, "jutsus"));
        jutsu.setType(Jutsu.class);
        jutsu.create();

        RegistryBuilder<Clans> clans = new RegistryBuilder<>();
        clans.setName(new ResourceLocation(NarutoMod.MOD_ID, "clans"));
        clans.setType(Clans.class);
        clans.create();

    }
}
