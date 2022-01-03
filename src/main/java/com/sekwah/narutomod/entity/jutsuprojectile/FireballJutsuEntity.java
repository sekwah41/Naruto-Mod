package com.sekwah.narutomod.entity.jutsuprojectile;

import com.sekwah.narutomod.damagesource.NarutoDamageSource;
import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.HitResult;

public class FireballJutsuEntity extends AbstractHurtingProjectile {

    public int time;
    // Ticks in rain before destroying
    public int lifeSpan = 70;
    // For now flat but may want to alter or make customisable later
    public float explosionPower = 2;

    public static final float INITIAL_SCALE = 0.1f;
    public static final float GROW_SCALE = 1 - INITIAL_SCALE;
    public static final float GROW_TIME = 2 * 20;
    public static final float ENTITY_SIZE = 1.5f;

    public FireballJutsuEntity(EntityType<FireballJutsuEntity> entityConstructor, Level level) {
        super(entityConstructor, level);
        this.time = 0;
    }

    public FireballJutsuEntity(EntityType<? extends AbstractHurtingProjectile> p_36817_, double p_36818_, double p_36819_, double p_36820_, double p_36821_, double p_36822_, double p_36823_, Level p_36824_) {
        super(p_36817_, p_36824_);
        this.moveTo(p_36818_, p_36819_, p_36820_, this.getYRot(), this.getXRot());
        this.reapplyPosition();
        double d0 = Math.sqrt(p_36821_ * p_36821_ + p_36822_ * p_36822_ + p_36823_ * p_36823_);
        if (d0 != 0.0D) {
            this.xPower = p_36821_ / d0 * 0.2D;
            this.yPower = p_36822_ / d0 * 0.2D;
            this.zPower = p_36823_ / d0 * 0.2D;
        }

    }

    public FireballJutsuEntity(LivingEntity player, double xVel, double yVel, double zVel) {
        this(NarutoEntities.FIREBALL_JUTSU.get(), player.getX(), player.getEyeY() - 0.2f, player.getZ(),  xVel, yVel, zVel, player.getLevel());
        this.setOwner(player);
        this.setRot(player.getYRot(), player.getXRot());
    }

    public EntityDimensions getDimensions(Pose p_19721_) {
        return EntityDimensions.scalable(ENTITY_SIZE, ENTITY_SIZE)
                .scale(Math.min(INITIAL_SCALE + (GROW_SCALE - (GROW_SCALE * ((GROW_TIME - time) / GROW_TIME))), 1.0f));
    }

    @Override
    public void tick() {
        super.tick();
        ++this.time;
        this.refreshDimensions();


        if (this.isInWaterOrRain()) {
            lifeSpan--;
            if (lifeSpan % 5 == 0) {
                this.playSound(SoundEvents.FIRE_EXTINGUISH, 1F, 1.0F);
            }
        }

        if (this.isInWater() || lifeSpan-- <= 0) {

            if(this.level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.CLOUD,
                        this.getX(),
                        this.getY() + this.getBbHeight() + 1,
                        this.getZ(),
                        100,
                        0.5, 0.2, 0.5, 0);
            }
            this.playSound(SoundEvents.FIRE_EXTINGUISH, 1F, 1.0F);
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if(this.level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.FLAME,
                    this.getX() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(),
                    this.getY() + this.getBbHeight() / 2 + (double) (this.random.nextFloat() * this.getBbHeight()),
                    this.getZ() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(),
                    200,
                    this.getBbWidth(), this.getBbWidth(), this.getBbHeight(), 1);
        }

        if (!this.level.isClientSide) {

            int flameRadius = 12;
            this.level.getEntities(this, this.getBoundingBox().inflate(flameRadius, flameRadius, flameRadius)).forEach(entity -> {
                double distance = this.position().distanceToSqr(entity.position());
                // Remember increasing the division reduces the falloff (I keep accidentally moving it the wrong way)
                float fireSecs = (float) (8f - (distance / 6f)) * 20;
                float fireDamage = (float) (12f - (distance / 4f));
                if(fireDamage > 0) {
                    Entity entity1 = this.getOwner();
                    entity.hurt(NarutoDamageSource.fireball(this, entity1), fireDamage);
                    if (entity1 instanceof LivingEntity) {
                        this.doEnchantDamageEffects((LivingEntity)entity1, entity);
                    }
                }
                if(entity.getRemainingFireTicks() < fireSecs) {
                    entity.setRemainingFireTicks(Math.round(fireSecs));
                }
            });

            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());

            if(flag) {
                int fireSpread = 2;
                for (int x = (int) this.getX() - fireSpread; x < (int) this.getX() + fireSpread - 1; x++) {
                    for (int y = (int) this.getY() - fireSpread + 1; y < (int) this.getY() + fireSpread; y++) {
                        for (int z = (int) this.getZ() - fireSpread + 1; z < (int) this.getZ() + fireSpread; z++) {
                            BlockPos blockPos = new BlockPos(x, y, z);
                            if (this.random.nextInt(2) == 0 && this.level.getBlockState(blockPos).isAir()) {
                                this.level.setBlockAndUpdate(blockPos, BaseFireBlock.getState(this.level, blockPos));
                            }
                        }
                    }
                }
            }

            this.playSound(SoundEvents.GENERIC_EXPLODE, 4f, 1.0f);

            this.discard();
        }

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
        return this.isInWaterOrRain() ? ParticleTypes.CLOUD : ParticleTypes.LARGE_SMOKE;
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

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket p_150128_) {
        super.recreateFromPacket(p_150128_);
        double d0 = p_150128_.getXa();
        double d1 = p_150128_.getYa();
        double d2 = p_150128_.getZa();
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        if (d3 != 0.0D) {
            this.xPower = d0 / d3 * 0.2D;
            this.yPower = d1 / d3 * 0.2D;
            this.zPower = d2 / d3 * 0.2D;
        }

    }
}
