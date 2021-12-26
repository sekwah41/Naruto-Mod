package com.sekwah.narutomod.entity.jutsuprojectile;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public class FireballJutsuEntity extends AbstractHurtingProjectile {

    public int time;
    private int rainFizz = 20;

    public FireballJutsuEntity(EntityType<FireballJutsuEntity> entityConstructor, Level level) {
        super(entityConstructor, level);
        this.time = 0;
    }

    public EntityDimensions getDimensions(Pose p_19721_) {
        float scaleTime = 2 * 20;
        return EntityDimensions.scalable(1.5f, 1.5f).scale(Math.min(0.1f + (0.9f - (0.9f * ((scaleTime - time) / scaleTime))), 1.0f));
    }

    @Override
    public void tick() {
        super.tick();
        ++this.time;
        this.refreshDimensions();
    }

    @Override
    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }

    @Override
    public boolean hurt(DamageSource p_36910_, float p_36911_) {
        return false;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SMOKE;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }
}
