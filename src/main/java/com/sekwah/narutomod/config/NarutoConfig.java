package com.sekwah.narutomod.config;

import com.sekwah.narutomod.NarutoMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoConfig {

    public static final String CATEGORY_WEAPONS = "weapons";

    public static final ForgeConfigSpec SERVER_CONFIG;

    private static final ForgeConfigSpec.BooleanValue CONFIG_KUNAI_BLOCK_DAMAGE;
    public static boolean KUNAI_BLOCK_DAMAGE;

    private static final ForgeConfigSpec.DoubleValue CONFIG_KUNAI_EXPLOSION_RADIUS;
    public static double KUNAI_EXPLOSION_RADIUS;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();

        configBuilder.comment("Server side variable allowing transparent skins to be set.").push(CATEGORY_WEAPONS);

        CONFIG_KUNAI_BLOCK_DAMAGE = configBuilder.comment("Explosive Kunai block damage")
                .define("kunaiExplosionBreakBlocks ", true);

        CONFIG_KUNAI_EXPLOSION_RADIUS = configBuilder.comment("Explosive Kunai explosion radius")
                .defineInRange("kunaiExplosionRadius ",  3d ,  1d, 100d);

        configBuilder.pop();

        SERVER_CONFIG = configBuilder.build();
    }

    public static void loadVariables() {
        KUNAI_BLOCK_DAMAGE = CONFIG_KUNAI_BLOCK_DAMAGE.get();
        KUNAI_EXPLOSION_RADIUS = CONFIG_KUNAI_EXPLOSION_RADIUS.get();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        loadVariables();
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
        loadVariables();
    }
}
