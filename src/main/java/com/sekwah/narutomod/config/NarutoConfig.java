package com.sekwah.narutomod.config;

import com.sekwah.narutomod.NarutoMod;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoConfig {

    public static final String CATEGORY_WEAPONS = "weapons";

    public static final ForgeConfigSpec MOD_CONFIG;

    private static final ForgeConfigSpec.BooleanValue CONFIG_KUNAI_BLOCK_DAMAGE;
    public static boolean KUNAI_BLOCK_DAMAGE;

    private static final ForgeConfigSpec.DoubleValue CONFIG_KUNAI_EXPLOSION_RADIUS;
    public static float KUNAI_EXPLOSION_RADIUS;

    private static final ForgeConfigSpec.DoubleValue CONFIG_PAPERBOMB_EXPLOSION_RADIUS;
    public static float PAPERBOMB_EXPLOSION_RADIUS;

    private static final ForgeConfigSpec.BooleanValue CONFIG_PAPERBOMB_BLOCK_DAMAGE;
    public static boolean PAPERBOMB_BLOCK_DAMAGE;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();

        configBuilder.comment("Variables for weapons").push(CATEGORY_WEAPONS);

        CONFIG_KUNAI_BLOCK_DAMAGE = configBuilder.comment("Explosive Kunai block damage")
                .define("kunaiExplosionBreakBlocks ", true);

        CONFIG_KUNAI_EXPLOSION_RADIUS = configBuilder.comment("Explosive Kunai explosion radius")
                .defineInRange("kunaiExplosionRadius ",  3d ,  1d, 100d);

        CONFIG_PAPERBOMB_EXPLOSION_RADIUS = configBuilder.comment("Paper Bomb explosion radius")
                .defineInRange("paperBombExplosionRadius ",  4.0F ,  1d, 100d);

        CONFIG_PAPERBOMB_BLOCK_DAMAGE = configBuilder.comment("Paper Bomb block damage")
                .define("paperBombExplosionBreakBlocks ", true);

        configBuilder.pop();

        MOD_CONFIG = configBuilder.build();
    }

    public static void loadVariables() {
        KUNAI_BLOCK_DAMAGE = CONFIG_KUNAI_BLOCK_DAMAGE.get();
        KUNAI_EXPLOSION_RADIUS = CONFIG_KUNAI_EXPLOSION_RADIUS.get().floatValue();
        PAPERBOMB_EXPLOSION_RADIUS = CONFIG_PAPERBOMB_EXPLOSION_RADIUS.get().floatValue();
        PAPERBOMB_BLOCK_DAMAGE = CONFIG_PAPERBOMB_BLOCK_DAMAGE.get();
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
