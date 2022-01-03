package com.sekwah.narutomod.damagesource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;

import javax.annotation.Nullable;

public class NarutoDamageSource {

    public static DamageSource jutsuProjectile(AbstractHurtingProjectile projectile, @Nullable Entity owner) {
        return (new IndirectEntityDamageSource("arrow", projectile, owner)).setProjectile();
    }

    /**
     * Because the default method only takes fireballs, this method will take any entity.
     * @param fireball
     * @param shooter
     * @return
     */
    public static DamageSource fireball(Entity fireball, @Nullable Entity shooter) {
        return shooter == null ? (new IndirectEntityDamageSource("onFire", fireball, fireball)).setProjectile() : (new IndirectEntityDamageSource("fireball", fireball, shooter)).setProjectile();
    }

}
