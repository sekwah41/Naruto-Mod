package com.sekwah.narutomod.damagetypes;

import com.sekwah.narutomod.NarutoMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class NarutoDamageTypes {
    public static final ResourceKey<DamageType> FIREBALL = create("fireball");
    public static final ResourceKey<DamageType> WATER_BULLET = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(NarutoMod.MOD_ID, "water_bullet"));

    public static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(NarutoMod.MOD_ID, name));
    }

    public static void register(BootstapContext<DamageType> context, ResourceKey<DamageType> key, float exhaustion) {
        context.register(key, new DamageType(key.location().getNamespace() + "." + key.location().getPath(), exhaustion));
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type) {
        return getDamageSource(level, type, null, null, null);
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity source) {
        return getDamageSource(level, type, source, source, null);
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity source, @Nullable Entity indirectSource) {
        return getDamageSource(level, type, source, indirectSource, null);
    }

    public static DamageSource getDamageSource(Level level, ResourceKey<DamageType> type, @Nullable Entity source, @Nullable Entity indirectSource, Vec3 position) {
        return new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), source, indirectSource, position);
    }

    public static void register(BootstapContext<DamageType> context, ResourceKey<DamageType> key) {
        register(context, key, 0.0F);
    }

    public static void bootstrap(BootstapContext<DamageType> context) {
        register(context, FIREBALL);
        register(context, WATER_BULLET);
    }
}
